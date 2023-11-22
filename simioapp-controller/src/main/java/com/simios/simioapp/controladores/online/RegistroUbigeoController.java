package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.UbigeoEntity;
import com.simios.simioapp.servicios.UbigeoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RegistroUbigeoController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroUbigeoController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-ubigeo";

    @Autowired
    @Qualifier("ubigeoService")
    private UbigeoService ubigeoService;

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    public ModelAndView buscarUbigeo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String codigoUbigeo = JS.toUpperBlank(request.getParameter("txt_codigoUbigeo"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));
            String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "ubigeo-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(codigoUbigeo)) {
                params.put("codigoUbigeo", JS.toInt(codigoUbigeo));
                params.put("codigoUbigeo_type", "igual");
                // codigoUbigeo_type

            }
            if (JS._numero(indDel)) {
                params.put("indDel", JS.toInt(indDel));
                params.put("indDel_type", "igual");

            }
            if (!JS._vacio(descripcion)) params.put("descripcion", descripcion + "%");

            List<UbigeoEntity> listaUbigeo = ubigeoService.selectByMap(params);
            // List<Map<String, Object>> listaUbigeo =
            // ubigeoService.selectByM(params);

            // poblar modelo
            model.put("lista", listaUbigeo);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RUC-BCA-228", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private UbigeoEntity buscarRegistroPorUnique(String campoUnique) throws Exception {

        UbigeoEntity filtro = new UbigeoEntity();

        filtro.setCodigoUbigeo(campoUnique);

        List<UbigeoEntity> resultados = ubigeoService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarUbigeo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String ubigeoID = JS.toBlank(request.getParameter("ubigeoID"));
            if (!JS._numeroEntero(ubigeoID)) throw new SimioException("RUC-ECA-229", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            UbigeoEntity entity = new UbigeoEntity();

            entity.setUbigeoID(JS.toInt(ubigeoID));

            ubigeoService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RUC-ELI-230", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView guardarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            Map<String, Object> result = validarEditar(request);

            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("RUC-GED-231", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            UbigeoEntity registro = (UbigeoEntity) result.get("registro");

            ubigeoService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RUC-GUA-232", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView guardarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            Map<String, Object> result = validarGuardar(request);
            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("RUC-GNU-233", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            UbigeoEntity registro = (UbigeoEntity) result.get("registro");

            ubigeoService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getUbigeoID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RUC-GNU-234", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

        // limpiar los atributos de session
        // setSessionAttribute(request,
        // Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);
    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "ubigeo-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL).setSelID(Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaUbigeo = ubigeoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaUbigeo);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RUC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String ubigeoID = JS.toBlank(request.getParameter("ubigeoID"));
            if (!JS._numeroEntero(ubigeoID)) throw new SimioException("RUC-MED-235", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "ubigeo-editar", modulo, plantilla);

            // delegar logica a servicios
            UbigeoEntity registro = ubigeoService.selectByID(JS.toInt(ubigeoID));
            if (registro == null) throw new SimioException("RUC-MED-236", "Ubigeo no encontrada");

            // loadComboTipoSeleccione(request, "cboEstados",
            // catalogo.INDEL).setSelID(registro.getIndDel());
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());
            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RUC-MED-212", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "ubigeo-nuevo", modulo, plantilla);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RUC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String codigoUbigeo = JS.toUpperBlank(request.getParameter("txt_codigoUbigeo"));
            // FIXME/TODO: recoger los demas campos
            // si son campos numeros o fechas en la vista se debe generar dos
            // controles HTML INICIO - FIN

            Map<String, Object> result = new HashMap<>();

            // validacion para ubigeoID
            if (!JS._vacio(codigoUbigeo)) {

                String msg = JS._numeroEnteroNoNegativoString(codigoUbigeo, "codigo de ubigeo");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "codigoUbigeo");
                    result.put("msgError", msg);

                    throw new SimioException("RUC-VBU-237", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RUC-VAB-238", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        Map<String, Object> result = new HashMap<>();

        // validar que no exista otro igual
        String codigoUbigeo = request.getParameter("txt_codigoUbigeo");

        if (!JS._numeroEnteroPositivoString(codigoUbigeo, "Ubigeo").equals(StringUtils.EMPTY)) {
            result.put("campoError", "general");
            result.put("msgError", "Ubigeo ya fue registrada");

            return result;

        }
        UbigeoEntity registro = new UbigeoEntity();

        // TODO/FIXME: armar el entity ->
        // registro.setUbigeoID(JS.toInt(ubigeoID));
        // registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        Map<String, Object> result = new HashMap<>();

        String codigoUbigeo = JS.toUpperBlank(request.getParameter("txt_codigoUbigeo"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        UbigeoEntity found = buscarRegistroPorUnique(codigoUbigeo);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Ubigeo ya fue registrada");

            return result;
        }
        if (!JS._numeroEnteroPositivoString(codigoUbigeo, "Ubigeo").equals(StringUtils.EMPTY)) {
            result.put("campoError", "general");
            result.put("msgError", "Ubigeo ya fue registrada");

            return result;

        }

        // armar entity
        UbigeoEntity registro = new UbigeoEntity();

        registro.setIndDel(Constantes.REGISTRO_ACTIVO);
        registro.setCodigoUbigeo(codigoUbigeo);
        registro.setDescripcion(descripcion);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    protected ComboWeb cargarComboUbigeoTodos(HttpServletRequest request, String nombreCombo, String catalogo, Object valorSeleccionado) throws Exception {

        // TODO/FIXME: revisar este codigo si se usa, items siempre es null y se envia a crearCombo y sabe Dios que pasara

        // trae los items de un catalogo
        List<ListaItem> items = null;// ubigeoService.selectListaItem(catalogo,true, true);

        // y crea el combo segun los items adicionales
        ComboWeb comboWeb = crearCombo(items, false, true);

        comboWeb.setSelID(valorSeleccionado);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;

    }

    private ComboWeb crearCombo(List<ListaItem> items, boolean agregerItemSeleccione, boolean agregarItemTodos) throws Exception {

        ComboWeb comboWeb = new ComboWeb(items);

        if (CollectionUtils.isNotEmpty(items)) {

            if (agregerItemSeleccione) {
                comboWeb.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }

            if (agregarItemTodos) {
                comboWeb.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);
            }

        }

        return comboWeb;
    }

    public ModelAndView verUbigeo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String ubigeoID = JS.toBlank(request.getParameter("ubigeoID"));
            if (!JS._numeroEntero(ubigeoID)) throw new SimioException("RUC-VCA-239", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "ubigeo-ver", modulo, plantilla);

            // delegar logica a servicios
            UbigeoEntity registro = ubigeoService.selectByID(JS.toInt(ubigeoID));
            if (registro == null) throw new SimioException("RUC-VEC-240", "Ubigeo no encontrada");

            // loadComboTipoSeleccione(request, "cboEstados",
            // catalogo.INDEL).setSelID(registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RUC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
