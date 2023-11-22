package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.DateTime;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.comunes.seguridad.LoginResult;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.seguridad.UsuarioSessionImpl;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.controladores.util.HttpUtil;
import com.simios.simioapp.dominio.entidades.ParametroEntity;
import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;
import com.simios.simioapp.servicios.LoginService;
import com.simios.simioapp.servicios.ParametroService;
import com.simios.simioapp.servicios.UsuarioSessionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.simios.simioapp.comunes.seguridad.LoginResult.*;

public class AdminController extends BaseController {

    private static final Logger log = Logger.getLogger(AdminController.class.getName());

    private static final String plantilla = "default";
//    private static final String modulo = null; // por lo pronto no tiene su propia carpeta

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;

    @Autowired
    @Qualifier("usuarioSessionService")
    private UsuarioSessionService usuarioSessionService;

    @Autowired
    @Qualifier("parametroService")
    private ParametroService parametroService;

    public ModelAndView mostrarLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            // con solo llamar a esta url crea una session si no la hay, e invalida la existente
            invalidarSession(request, true);

            return new ModelAndView("login", model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-BIE-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            String usuario = StringUtils.trimToEmpty(request.getParameter("usuario"));
            String password = StringUtils.trimToEmpty(request.getParameter("password"));

            // para empresa toma el sufijo <usuario>.xxx
            String empresa = usuario.contains(".") ? StringUtils.substring(usuario, usuario.indexOf(".") + 1) : StringUtils.EMPTY;

            // validacion de empresa, usuario y password
            LoginResult loginResult = loginService.validateLogin(empresa, usuario, password);

            if (loginResult.isError()) {

                model.put("hayValidacion", Constantes.SI);
                model.put("msgGeneral", loginResult.getErrorCode() + ": " + loginResult.getErrorMsg());
                model.put("msgDetalle", StringUtils.EMPTY);

                // TODO/FIXME: si el codigo de error, es especifico que no ingreso el password bien, aqui colocar
                //             la logica para el numero de intentos maximo, advirtiendo el ultimo intento y que se le
                //             bloqueara
                // SE DEBEN APLICAR LAS POLITICAS DEL NUMERO DE INTENTOS

                if (StringUtils.equals(loginResult.getErrorCode(), LOGIN_ERROR_CLAVE_INCORRECTA)) {

                    // decrementar el contador de oportunidades de login

                }

                return new ModelAndView("login", model);
            }

            // ver si ya existe el atributo de session http (si existe el usuario se ha logueado teniendo una session activa)
            // NOTA: segun lo programado, lo "normal" es que nunca se tiene este atributo ya que la accion "mostrarLogin"
            //       invalida la sesion, pero eso no quita el caso paranoico que estando logueado haga una peticion http
            UsuarioSession usuarioSession = (UsuarioSession) getSessionAttribute(request, Constantes.USUARIO_SESSION_NAME);

            // si no tiene session http se le crea una
            if (usuarioSession == null) {

                UsuarioSessionImpl usuarioSessionImpl = new UsuarioSessionImpl();

                usuarioSessionImpl.setCodigo(usuario);
                usuarioSessionImpl.setCodigoEmpresa(empresa);
                usuarioSessionImpl.setApellidos(MapUtils.getString(loginResult.getDatos(), "usuario.apellidos"));
                usuarioSessionImpl.setNombres(MapUtils.getString(loginResult.getDatos(), "usuario.nombres"));

                usuarioSession = usuarioSessionImpl;

                // setear el usuario en session
                WebUtils.setSessionAttribute(request, Constantes.USUARIO_SESSION_NAME, usuarioSession);
            }

            // VALIDACIONES DE LA SESSION
            UsuarioSessionEntity usuarioSessionFilter = new UsuarioSessionEntity();
            usuarioSessionFilter.setUsuarioID(usuario);
            List<UsuarioSessionEntity> sesiones = usuarioSessionService.select(usuarioSessionFilter);

            // si no hay sessiones crear una
            if (CollectionUtils.isEmpty(sesiones)) {

                // crear una nueva instancia, llenar el bean e insertar
                UsuarioSessionEntity sessionInsert = new UsuarioSessionEntity();
                fillUsuarioSessionConDefaults(sessionInsert, request, empresa, usuario, loginResult);
                setCamposAuditoria(request, sessionInsert, true);

                usuarioSessionService.insert(sessionInsert);

            } else {

                // si hay sessiones, ver si esta borrada o no
                UsuarioSessionEntity sesion = sesiones.get(0);

                if (StringUtils.equals(sesion.getIndDel(), Constantes.REGISTRO_ACTIVO)) {

                    // validar que no este bloqueado manual o por superar los intentos
                    if (StringUtils.equals(sesion.getEstado(), catalogo.SESION_USUARIO_ESTADO_BLOQUEADO_SUPERAR_INTENTOS) ||
                            StringUtils.equals(sesion.getEstado(), catalogo.SESION_USUARIO_ESTADO_BLOQUEDO_MANUAL)) {

                        model.put("hayValidacion", Constantes.SI);
                        model.put("msgGeneral", LOGIN_ERROR_USUARIO_BLOQUEADO + ": El usuario se encuentra bloqueado");
                        model.put("msgDetalle", StringUtils.EMPTY);

                        return new ModelAndView("login", model);
                    }

                    // APLICAR LAS POLITICAS DE INACTIVIDAD (SI HA DEJADO UNA SESSION SIN CERRAR, OSEA INACTIVA)
                    if (StringUtils.equals(sesion.getIndPoliticaInactividad(), Constantes.UNO)) {
                        if (StringUtils.equals(sesion.getEstado(), catalogo.SESION_USUARIO_ESTADO_ACTIVO)) {

                            model.put("hayValidacion", Constantes.SI);
                            model.put("msgGeneral", LOGIN_ERROR_USUARIO_NO_HA_CERRADO_SESION + ": El usuario no ha cerrado la sesión. Comuníquese con el área de sistemas");
                            model.put("msgDetalle", StringUtils.EMPTY);

                            return new ModelAndView("login", model);
                        }
                    }

                }

                // sea registro borrado o no borrado, llenar el bean y actualizar la session

                // llenar el bean existente y actualizar todos sus campos haciendolo registro activo
                fillUsuarioSessionConDefaults(sesion, request, empresa, usuario, loginResult);
                setCamposAuditoria(request, sesion, false);

                usuarioSessionService.update(sesion);
            }

            // si alguien ya logueado intenta loguearse nuevamente, solo lo redirecciona a la pantalla por defecto de
            // bienvenidos, y si su session en bd ya caduco sera validada en otra accion que haga en el sistema, no aqui.
            // Adicional: SI ALGUIEN YA ESTA LOGUEADO E INTENTA LOGUEARSE CON OTRO USUARIO. ESTE CASO NO SE ESTA CONTROLANDO

            return new ModelAndView("redirect:admin.htm?action=bienvenidos");

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-BIE-001", sos);
            log.severe(msgError);

            if (model == null) {
                model = new HashMap<>();
            }

            // si ocurrio un error no controlado, regresar a la pagina del login
            model.put("hayValidacion", Constantes.SI);
            model.put("msgGeneral", LoginResult.LOGIN_ERROR_GENERAL_POR_EXCEPTION_CONTROLADOR + ": Ha ocurrido un error inesperado");
            model.put("msgDetalle", StringUtils.EMPTY);

            return new ModelAndView("login", model);
        }
    }

    private void fillUsuarioSessionConDefaults(UsuarioSessionEntity entity, HttpServletRequest request, String empresa, String usuario, LoginResult loginResult) throws Exception {

        if (entity != null && loginResult != null) {

            DateTime now = DateTime.getNow();

            entity.setApellidosUsuario(MapUtils.getString(loginResult.getDatos(), "usuario.apellidos"));
            entity.setDatosIpAccion(getIPCliente(request));
            entity.setDatosIpLogin(getIPCliente(request));
            entity.setEntidadID(JS.toInt(empresa));
            entity.setEstado(catalogo.SESION_USUARIO_ESTADO_ACTIVO); // ESTADO DE SESSION ACTIVO
            entity.setIndPoliticaInactividad(buscarIndicadorPoliticaInactividadDefault());
            entity.setIndPoliticaIntentos(buscarIndicadorPoliticaIntentosFallidosDefault());
            entity.setMinutosValidez(buscarMinutosValidezDefault());
            entity.setNombresUsuario(MapUtils.getString(loginResult.getDatos(), "usuario.nombres"));
            entity.setNumeroIntentos(buscarNumeroDeIntentosMaximoDefault());
            entity.setUltimaAccion(JS.toDate(now));
            entity.setUltimoCierre(null);
            entity.setUltimoLogin(JS.toDate(now));
            entity.setUsuarioID(usuario);
            entity.setIndDel(Constantes.REGISTRO_ACTIVO); // ESTADO DE REGISTRO ACTIVO

        }
    }

    private Integer buscarNumeroDeIntentosMaximoDefault() throws Exception {

        ParametroEntity filter = new ParametroEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setNombre(Constantes.PARAMETROS.SESSION_NUMERO_INTENTOS_DEFAULT);

        List<ParametroEntity> parametros = parametroService.select(filter);

        if (CollectionUtils.isNotEmpty(parametros)) {
            ParametroEntity parametro = parametros.get(0);
            if (StringUtils.equals(parametro.getTipoValor(), catalogo.TIPO_VALOR_PARAMETRO_ENTERO)) {
                return JS.toInt(parametro.getValor());
            }
        }

        return null;
    }

    private Integer buscarMinutosValidezDefault() throws Exception {

        ParametroEntity filter = new ParametroEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setNombre(Constantes.PARAMETROS.SESSION_MINUTOS_VALIDEZ_DEFAULT);

        List<ParametroEntity> parametros = parametroService.select(filter);

        if (CollectionUtils.isNotEmpty(parametros)) {
            ParametroEntity parametro = parametros.get(0);
            if (StringUtils.equals(parametro.getTipoValor(), catalogo.TIPO_VALOR_PARAMETRO_ENTERO)) {
                return JS.toInt(parametro.getValor());
            }
        }

        return null;
    }

    private String buscarIndicadorPoliticaIntentosFallidosDefault() throws Exception {

        ParametroEntity filter = new ParametroEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setNombre(Constantes.PARAMETROS.SESSION_INDICADOR_POLITICA_DE_INTENTOS_FALLIDOS);

        List<ParametroEntity> parametros = parametroService.select(filter);

        if (CollectionUtils.isNotEmpty(parametros)) {
            ParametroEntity parametro = parametros.get(0);
            if (StringUtils.equals(parametro.getTipoValor(), catalogo.TIPO_VALOR_PARAMETRO_BOOLEANO)) {
                boolean valorBoolean = JS.toInt(parametro.getValor()) == Constantes.UNO_INT;
                return valorBoolean ? Constantes.UNO : Constantes.CERO;
            }
        }

        return null;
    }

    private String buscarIndicadorPoliticaInactividadDefault() throws Exception {

        ParametroEntity filter = new ParametroEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setNombre(Constantes.PARAMETROS.SESSION_INDICADOR_POLITICA_DE_INACTIVIDAD);

        List<ParametroEntity> parametros = parametroService.select(filter);

        if (CollectionUtils.isNotEmpty(parametros)) {
            ParametroEntity parametro = parametros.get(0);
            if (StringUtils.equals(parametro.getTipoValor(), catalogo.TIPO_VALOR_PARAMETRO_BOOLEANO)) {
                boolean valorBoolean = JS.toInt(parametro.getValor()) == Constantes.UNO_INT;
                return valorBoolean ? Constantes.UNO : Constantes.CERO;
            }
        }

        return null;
    }

    public ModelAndView bienvenidos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            setVistaTemplate(model, "bienvenidos", null, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-BIE-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView cerrarSesionDesdeOffline(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // NOTA: este metodo es invocado siempre desde el offline cuando carga la pantalla principal.
        //      ¿el motivo? por seguridad, una vez que se entra offline se pierde todo contacto con el servidor web
        //      y mejor dejar las cosas limpias, es como si el usuario online hubiera cerrado session desde el online.
        DataJsonBean dataJSON = new DataJsonBean();

        try {

            HashMap model = new HashMap<>();

            UsuarioSession usuarioSession = (UsuarioSession) getSessionAttribute(request, Constantes.USUARIO_SESSION_NAME);

            Credencial credencial = HttpUtil.checkCredencialesTokenQuiet(request);

            // solo si posee el token cierra la session, sino cualquier persona podria ejecutar la funcion JS cuando quiera
            if (credencial != null) {

                // si no tiene session http se le crea una
                updateUsuarioSession(request, usuarioSession);

                // invalidar la session
                invalidarSession(request, false);

                model.put("closed", Constantes.SI);
            }

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("ACC-CSO-924", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


    public ModelAndView cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            UsuarioSession usuarioSession = (UsuarioSession) getSessionAttribute(request, Constantes.USUARIO_SESSION_NAME);

            // si no tiene session http se le crea una
            updateUsuarioSession(request, usuarioSession);

            // invalidar la session
            invalidarSession(request, false);

            // redirecciona al inicio del sistema, login
            return new ModelAndView("redirect:admin.htm?action=mostrarLogin", model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-BIE-001", sos);
            log.severe(msgError);

            // invalidar la session
            invalidarSession(request, false);

            // si ocurre algun error no se puede hacer mucho mas que redireccionar al login
            return new ModelAndView("redirect:admin.htm?action=mostrarLogin", model);
        }
    }

    private void updateUsuarioSession(HttpServletRequest request, UsuarioSession usuarioSession) throws Exception {

        if (request == null || usuarioSession == null) return;

        UsuarioSessionEntity usuarioSessionFilter = new UsuarioSessionEntity();
        usuarioSessionFilter.setUsuarioID(usuarioSession.getUsuarioSessionID());
        usuarioSessionFilter.setIndDel(Constantes.REGISTRO_ACTIVO); // solo busca el activo (deberia estar activo)

        List<UsuarioSessionEntity> sesiones = usuarioSessionService.select(usuarioSessionFilter);

        if (CollectionUtils.isNotEmpty(sesiones)) {

            DateTime now = DateTime.getNow();

            // si hay sessiones, ver si esta borrada o no
            UsuarioSessionEntity sesion = sesiones.get(0);

            sesion.setEstado(catalogo.SESION_USUARIO_ESTADO_INACTIVO); // cerrar session
            sesion.setUltimoCierre(now.toDate());
            sesion.setUltimaAccion(now.toDate());

            // llenar los campos de auditoria para el update
            setCamposAuditoria(request, sesion, false);

            usuarioSessionService.update(sesion);
        }
    }


}
