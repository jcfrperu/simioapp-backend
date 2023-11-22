package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.EmpleadoEntity;
import com.simios.simioapp.servicios.EmpleadoService;
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

public class RegistroEmpleadoController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroEmpleadoController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-empleado";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("empleadoService")
    private EmpleadoService empleadoService;

    public ModelAndView buscarEmpleado(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String codigoEntidad = JS.toUpperBlank(request.getParameter("cboEntidad"));
            String codigoTipoDoc = JS.toBlank(request.getParameter("cbo_tipodoc"));
            String numeroDoc = JS.toBlank(request.getParameter("txt_numeroDocIdentPersonal"));
            String nombres = JS.toUpperBlank(request.getParameter("txt_nombres"));
            String apellidoPaterno = JS.toUpperBlank(request.getParameter("txt_apellidoPaterno"));
            String apellidoMaterno = JS.toUpperBlank(request.getParameter("txt_apellidoMaterno"));
            String estadoRegistro = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "empleado-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(codigoEntidad)) params.put("entidadID", JS.toInt(codigoEntidad));
            if (!JS._vacio(codigoTipoDoc)) params.put("tipoDocIdentidad", codigoTipoDoc);
            if (!JS._vacio(numeroDoc)) params.put("numeroDocIdentPersonal", "%" + numeroDoc + "%");
            if (!JS._vacio(nombres)) params.put("nombres", "%" + nombres + "%");
            if (!JS._vacio(apellidoPaterno)) params.put("apellidoPaterno", "%" + apellidoPaterno + "%");
            if (!JS._vacio(apellidoMaterno)) params.put("apellidoMaterno", "%" + apellidoMaterno + "%");
            if (!JS._vacio(estadoRegistro)) params.put("indDel", estadoRegistro);

            List<Map<String, Object>> listaEmpleado = empleadoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaEmpleado);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-BCA-150", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private Map<String, Object> buscarRegistroPorUnique(HashMap<String, Object> filtro, String empleadoID, Boolean esRegistroNuevo) throws Exception {

        List<Map<String, Object>> resultados = empleadoService.selectByMapGrilla(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {

            if (!esRegistroNuevo) {
                for (Map<String, Object> empleado : resultados) {
                    if (!JS._equiv(JS.toInt(empleado.get("empleadoID").toString()), JS.toInt(empleadoID))) {
                        return empleado;
                    }
                }
            }

            return resultados.get(0);
        }

        return null;
    }

    public ModelAndView eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String empleadoID = JS.toBlank(request.getParameter("empleadoID"));
            if (!JS._numeroEntero(empleadoID)) throw new SimioException("REC-ECA-151", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EmpleadoEntity entity = new EmpleadoEntity();

            entity.setEmpleadoID(JS.toInt(empleadoID));

            empleadoService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-ELI-152", dataJSON, sos);
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
                throw new SimioException("REC-GED-153", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EmpleadoEntity registro = (EmpleadoEntity) result.get("registro");

            empleadoService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-GUA-154", dataJSON, sos);
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
                throw new SimioException("REC-GNU-155", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EmpleadoEntity registro = (EmpleadoEntity) result.get("registro");

            empleadoService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getEmpleadoID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-GNU-156", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {
    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "empleado-buscar", modulo, plantilla);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
            cargarComboCatalogoTodos(request, "cboTipoDoc", catalogo.TIPO_DOCUMENTO, Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);
            params.put("entidadID", JS.toInt(usuarioSession.getCodigoEntidad()));

            List<Map<String, Object>> lista = empleadoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", lista);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MBU-433", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String empleadoID = JS.toBlank(request.getParameter("empleadoID"));
            if (!JS._numeroEntero(empleadoID)) throw new SimioException("REC-MED-157", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "empleado-editar", modulo, plantilla);

            // delegar logica a servicios
            EmpleadoEntity registro = empleadoService.selectByID(JS.toInt(empleadoID));

            if (registro == null) throw new SimioException("REC-VEC-162", "Empleado no encontrado");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipoDoc", catalogo.TIPO_DOCUMENTO).setSelID(registro.getTipoDocIdentidad());
            cargarComboCatalogoSeleccione(request, "cboModalidad", catalogo.TIPO_MODALIDAD).setSelID(registro.getModalidadContrato());

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MED-212", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            cargarComboCatalogoSeleccione(request, "cboTipoDoc", catalogo.TIPO_DOCUMENTO, Constantes.REGISTRO_ACTIVO);
            cargarComboCatalogoSeleccione(request, "cboModalidad", catalogo.TIPO_MODALIDAD, Constantes.REGISTRO_ACTIVO);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // setear siguiente vista
            setVistaTemplate(model, "empleado-nuevo", modulo, plantilla);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-VAB-160", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String empleadoID = JS.toUpperBlank(request.getParameter("int_empleadoID"));
        String codigoEmpleado = JS.toUpperBlank(request.getParameter("txt_codigo"));

        UsuarioSession usuarioSession = getUsuarioSession(request);

        String codigoTipoDoc = JS.toBlank(request.getParameter("cbo_tipodoc"));
        String numeroDoc = JS.toBlank(request.getParameter("txt_numeroDocIdentPersonal"));
        String nombres = JS.toUpperBlank(request.getParameter("txt_nombres"));
        String apellidoPaterno = JS.toUpperBlank(request.getParameter("txt_apellidoPaterno"));
        String apellidoMaterno = JS.toUpperBlank(request.getParameter("txt_apellidoMaterno"));
        String apellidos = apellidoPaterno + " " + apellidoMaterno;
        String modalidadContrato = JS.toBlank(request.getParameter("cbo_modalidad"));
        String estadoRegistro = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar codigo empleado
        String msg = JS._campoNoVacio(codigoEmpleado, "Código Empleado");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigo");
            result.put("msgError", msg);

            return result;
        }

        // validar combo tipo documento
        if (JS._equiv(codigoTipoDoc, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_tipodoc");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar numero documento entidad
        msg = JS._campoNoVacio(numeroDoc, "Número documento");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_numeroDocIdentPersonal");
            result.put("msgError", msg);

            return result;
        }

        if (StringUtils.equals(codigoTipoDoc, catalogo.TIPO_DOCUMENTO_DNI)) {
            // DNI
            if (JS.toBlank(numeroDoc).length() != 8) {
                result.put("campoError", "txt_numeroDocIdentPersonal");
                result.put("msgError", "Número documento debe tener 8 caracteres");

                return result;
            }
        } else if (StringUtils.equals(codigoTipoDoc, catalogo.TIPO_DOCUMENTO_CARNET_EXTRANJERIA)) {
            // CARNET DE EXTRANJERIA
            msg = JS._campoMaxSizeNoVacio(numeroDoc, 8, "Número documento");
            if (!JS._vacio(msg)) {
                result.put("campoError", "txt_numeroDocIdentPersonal");
                result.put("msgError", msg);
                return result;
            }
        }

        // validar nombres
        msg = JS._campoNoVacio(nombres, "Nombres");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombres");
            result.put("msgError", msg);

            return result;
        }

        // validar apellido paterno
        msg = JS._campoNoVacio(apellidoPaterno, "Apellido Paterno");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_apellidoPaterno");
            result.put("msgError", msg);

            return result;
        }

        // validar apellido materno
//        msg = JS._campoNoVacio(apellidoMaterno, "Apellido Materno");
//        if (!JS._vacio(msg)) {
//
//            result.put("campoError", "txt_apellidoMaterno");
//            result.put("msgError", msg);
//
//            return result;
//        }

        // validar combo indDel
        if (JS._equiv(estadoRegistro, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar que no exista otro igual
        HashMap<String, Object> params = new HashMap<>();
        params.put("codigo", codigoEmpleado);
        params.put("entidadID", JS.toInt(usuarioSession.getCodigoEntidad()));

        Map<String, Object> found = buscarRegistroPorUnique(params, empleadoID, false);
        if (found != null && !JS._equiv(JS.toInt(found.get("empleadoID").toString()), JS.toInt(empleadoID))) {
            result.put("campoError", "general");
            result.put("msgError", "Ya existe un empleado registrado con el mismo código y entidad.");

            return result;
        }

        // armar entity
        EmpleadoEntity registro = new EmpleadoEntity();
        registro.setEmpleadoID(JS.toInt(empleadoID));
        registro.setCodigo(codigoEmpleado);
        registro.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
        registro.setTipoDocIdentidad(codigoTipoDoc);
        registro.setNumeroDocIdentPersonal(numeroDoc);
        registro.setNombres(nombres);
        registro.setApellidoPaterno(apellidoPaterno);
        registro.setApellidoMaterno(apellidoMaterno);
        registro.setApellidos(apellidos);
        if (!StringUtils.equals(modalidadContrato, Constantes.COMBO_SELECCIONE_VALUE)) {
            registro.setModalidadContrato(modalidadContrato);
        }
        registro.setIndDel(estadoRegistro);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String codigoEmpleado = JS.toUpperBlank(request.getParameter("txt_codigo"));

        UsuarioSession usuarioSession = getUsuarioSession(request);
        String codigoEntidad = usuarioSession.getCodigoEntidad();

        String codigoTipoDoc = JS.toBlank(request.getParameter("cbo_tipodoc"));
        String numeroDoc = JS.toBlank(request.getParameter("txt_numeroDocIdentPersonal"));
        String nombres = JS.toUpperBlank(request.getParameter("txt_nombres"));
        String apellidoPaterno = JS.toUpperBlank(request.getParameter("txt_apellidoPaterno"));
        String apellidoMaterno = JS.toUpperBlank(request.getParameter("txt_apellidoMaterno"));
        String apellidos = apellidoPaterno + " " + apellidoMaterno;
        String modalidadContrato = JS.toUpperBlank(request.getParameter("cbo_modalidad"));

        Map<String, Object> result = new HashMap<>();

        // validar codigo empleado
        String msg = JS._campoNoVacio(codigoEmpleado, "Código Empleado");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigo");
            result.put("msgError", msg);

            return result;
        }

        // validar combo tipo documento
        if (JS._equiv(codigoTipoDoc, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_tipodoc");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar numero documento entidad
        msg = JS._campoNoVacio(numeroDoc, "Número documento");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_numeroDocIdentPersonal");
            result.put("msgError", msg);

            return result;
        }

        if (StringUtils.equals(codigoTipoDoc, catalogo.TIPO_DOCUMENTO_DNI)) {
            // DNI
            if (JS.toBlank(numeroDoc).length() != 8) {
                result.put("campoError", "txt_numeroDocIdentPersonal");
                result.put("msgError", "Número documento debe tener 8 caracteres");

                return result;
            }
        } else if (StringUtils.equals(codigoTipoDoc, catalogo.TIPO_DOCUMENTO_CARNET_EXTRANJERIA)) {
            // CARNET DE EXTRANJERIA
            msg = JS._campoMaxSizeNoVacio(numeroDoc, 8, "Número documento");
            if (!JS._vacio(msg)) {
                result.put("campoError", "txt_numeroDocIdentPersonal");
                result.put("msgError", msg);
                return result;
            }
        }

        // validar nombres
        msg = JS._campoNoVacio(nombres, "Nombres");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombres");
            result.put("msgError", msg);

            return result;
        }

        // validar apellido paterno
        msg = JS._campoNoVacio(apellidoPaterno, "Apellido Paterno");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_apellidoPaterno");
            result.put("msgError", msg);

            return result;
        }

//        // validar apellido materno
//        msg = JS._campoNoVacio(apellidoMaterno, "Apellido Materno");
//        if (!JS._vacio(msg)) {
//
//            result.put("campoError", "txt_apellidoMaterno");
//            result.put("msgError", msg);
//
//            return result;
//        }

        // validar que no exista otro igual
        HashMap<String, Object> params = new HashMap<>();
        params.put("codigo", codigoEmpleado);
        params.put("entidadID", JS.toInt(codigoEntidad));

        Map<String, Object> found = buscarRegistroPorUnique(params, null, true);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Ya existe un empleado registrado con el mismo codigo, y entidad.");

            return result;
        }

        // armar entity
        EmpleadoEntity registro = new EmpleadoEntity();

        registro.setCodigo(codigoEmpleado);
        registro.setEntidadID(JS.toInt(codigoEntidad));
        registro.setTipoDocIdentidad(codigoTipoDoc);
        registro.setNumeroDocIdentPersonal(numeroDoc);
        registro.setNombres(nombres);
        registro.setApellidoPaterno(apellidoPaterno);
        registro.setApellidoMaterno(apellidoMaterno);
        registro.setApellidos(apellidos);
        if (!StringUtils.equals(modalidadContrato, Constantes.COMBO_SELECCIONE_VALUE)) {
            registro.setModalidadContrato(modalidadContrato);
        }
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verEmpleado(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String empleadoID = JS.toBlank(request.getParameter("empleadoID"));
            if (!JS._numeroEntero(empleadoID)) throw new SimioException("REC-VCA-161", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "empleado-ver", modulo, plantilla);

            // delegar logica a servicios
            EmpleadoEntity registro = empleadoService.selectByID(JS.toInt(empleadoID));

            if (registro == null) throw new SimioException("REC-VEC-162", "Empleado no encontrado");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipoDoc", catalogo.TIPO_DOCUMENTO).setSelID(registro.getTipoDocIdentidad());
            cargarComboCatalogoSeleccione(request, "cboModalidad", catalogo.TIPO_MODALIDAD).setSelID(registro.getModalidadContrato());

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
