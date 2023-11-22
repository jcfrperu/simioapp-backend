package com.simios.simioapp.controladores.online;

import com.simios.simioapp.controladores.base.BaseController;

//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.jcfr.utiles.enums.JExceptionEnum;
//import com.jcfr.utiles.web.ComboWeb;
//import com.simios.simioapp.comunes.beans.DataJsonBean;
//import com.simios.simioapp.comunes.exceptions.AppException;
//import com.simios.simioapp.negocio.utiles.Catalogo;
//import com.simios.simioapp.comunes.utiles.Constantes;
//import com.simios.simioapp.controladores.base.BaseAdminController;
//import com.simios.simioapp.dominio.entidades.ClaveEntity;
//import com.simios.simioapp.servicios.ClaveService;
//
public class RegistroClaveController extends BaseController {
//
//    private static final Logger log = Logger.getLogger(RegistroClaveController.class.getName());
//
//    private static final String plantilla = "default";
//    private static final String modulo = "registro-clave";
//
//    @Autowired
//    @Qualifier("catalogo")
//    private Catalogo catalogo;
//
//    @Autowired
//    @Qualifier("claveService")
//    private ClaveService claveService;
//
//    public ModelAndView buscarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros
//            // TODO/FIXME: recoger los atributos: 
//            String claveID = JS.toUpperBlank(request.getParameter("int_claveID"));
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaPage(model, "clave-buscar-grilla", modulo, plantilla);
//
//            // delegar logica a servicios
//            HashMap<String, Object> params = new HashMap<String, Object>();
//
//            // TODO/FIXME: armar el mapa con los parametros
//			if (JS._numeroEntero(claveID)) params.put("claveID", JS.toLong(claveID));			
//			if (JS._numero(claveID)) params.put("claveID", JS.toDouble(claveID));
//			if (JS._fecha(claveID)) params.put("claveID", JS.toDate(claveID));			
//			if (!JS._vacio(claveID)) params.put("claveID", claveID + "%");
//			if (!JS._vacio(claveID)) params.put("claveID", claveID);
//
//			// FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//			// List<ClaveEntity> listaClave = claveService.selectByMap(params);
//			List<Map<String, Object>> listaClave = claveService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaClave);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RCC-BCA-247", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    private ClaveEntity buscarRegistroPorUnique(String campoUnique) throws Exception {
//
//        ClaveEntity filtro = new ClaveEntity();
//
//        filtro.setXXX(campoUnique);
//
//        List<ClaveEntity> resultados = claveService.select(filtro);
//
//        if (CollectionUtils.isNotEmpty(resultados)) {
//            return resultados.get(0);
//        }
//
//        return null;
//
//    }
//
//    public ModelAndView eliminarClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            String claveID = JS.toBlank(request.getParameter("claveID"));
//            if (!JS._numeroEntero(claveID)) throw new AppException("RCC-ECA-248", "Argumento ilegal del request");
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            ClaveEntity entity = new ClaveEntity();
//
//            entity.setClaveID(JS.toLong(claveID));
//
//            claveService.delete(entity);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RCC-ELI-249", dataJSON, sos);
//            log.severe(msgError);
//        }
//
//        return handleJSONResponse(dataJSON, response);
//    }
//
//    public ModelAndView guardarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            Map<String, Object> result = validarEditar(request);
//
//            String msgError = MapUtils.getString(result, "msgError");
//            if (!JS._vacio(msgError)) {
//                throw new AppException("RCC-GED-250", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            ClaveEntity registro = (ClaveEntity) result.get("registro");
//
//            claveService.update(registro);
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RCC-GUA-251", dataJSON, sos);
//            log.severe(msgError);
//        }
//
//        return handleJSONResponse(dataJSON, response);
//    }
//
//    public ModelAndView guardarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            Map<String, Object> result = validarGuardar(request);
//            String msgError = MapUtils.getString(result, "msgError");
//            if (!JS._vacio(msgError)) {
//                throw new AppException("RCC-GNU-252", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            ClaveEntity registro = (ClaveEntity) result.get("registro");
//
//            claveService.insert(registro);
//
//            // poblar modelo
//            model.put("idGenerado", String.valueOf(registro.getClaveID()));
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RCC-GNU-253", dataJSON, sos);
//            log.severe(msgError);
//        }
//
//        return handleJSONResponse(dataJSON, response);
//    }
//
//    private void limpiarSession(HttpServletRequest request) throws Exception {
//
//        // TODO/FIXME
//        // limpiar los atributos de session
//        // setSessionAttribute(request, Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);
//
//    }
//
//    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "clave-buscar", modulo, plantilla);
//
//            // delegar logica a servicios
//            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
//
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			
//			params.put("indDel", Constantes.REGISTRO_ACTIVO);
//
//			// FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//			// List<ClaveEntity> listaClave = claveService.selectByMap(params);
//			List<Map<String, Object>> listaClave = claveService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaClave);
//
//            // limpiar session
//            limpiarSession(request);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RCC-MBU-001", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros y validar
//            String claveID = JS.toBlank(request.getParameter("claveID"));
//            if (!JS._numeroEntero(claveID)) throw new AppException("RCC-MED-254", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "clave-editar", modulo, plantilla);
//
//            // delegar logica a servicios
//            ClaveEntity registro = claveService.selectByID(JS.toLong(claveID));
//            if (registro == null) throw new AppException("RCC-MED-255", "Clave no encontrada");
//
//            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
//
//            // poblar el modelo
//            model.put("registro", registro);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RCC-MED-212", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    public ModelAndView mostrarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "clave-nuevo", modulo, plantilla);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RCC-MNU-017", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            String claveID = JS.toUpperBlank(request.getParameter("int_claveID"));
//			// FIXME/TODO: recoger los demas campos
//			// si son campos numeros o fechas en la vista se debe generar dos controles HTML INICIO - FIN
//
//            String msg = StringUtils.EMPTY;
//            Map<String, Object> result = new HashMap<String, Object>();
//
//            // validacion para claveID
//            if (!JS._vacio(claveID)) {
//
//                msg = JS._numeroEnteroNoNegativoString(claveID, Constantes.VALIDACION_CAMPO);
//                if (!JS._vacio(msg)) {
//                    result.put("campoError", "int_claveID");
//                    result.put("msgError", msg);
//
//                    throw new AppException("RCC-VBU-256", msg, JExceptionEnum.VALIDACION, result);
//                }
//            }
//			// FIXME/TODO: colocar el resto de validaciones, lanzando el throw new AppException() de ejemplo de arriba
//
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RCC-VAB-257", dataJSON, sos);
//            log.severe(msgError);
//        }
//
//        return handleJSONResponse(dataJSON, response);
//    }
//
//    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {
//
//        // recoger parametros y validar
//        // TODO/FIXME: recoger atributos (con el id) del request con toBlank -> String xxxx = JS.toUpperBlank(request.getParameter("int_xxx"));
//
//        String msg = StringUtils.EMPTY;
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        // validacion para claveID
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(claveID, Constantes.VALIDACION_CAMPO); ... return result
//
//		// validar que no exista otro igual
//		ClaveEntity found = buscarRegistroPorUnique(nombre);
//		if (found != null && !JS._equiv(found.getClaveID(), JS.toLong(claveID))) {
//			result.put("campoError", "general");
//			result.put("msgError", "Clave ya fue registrada");
//
//			return result;
//		}
//		
//        // armar entity
//        ClaveEntity registro = new ClaveEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setClaveID(JS.toLong(claveID));
//        registro.setIndDel(indDel);
//
//        setCamposAuditoria(request, registro, false);
//
//        // setearlo al mapa resultado
//        result.put("registro", registro);
//
//        return result;
//    }
//
//    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {
//
//        // recoger parametros y validar
//        // TODO/FIXME: recoger atributos (sin el id) del request con toBlank -> String xxxx = JS.toUpperBlank(request.getParameter("int_xxx"));
//
//        String msg = StringUtils.EMPTY;
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        // validacion para nombre
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(claveID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        ClaveEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null) {
//            result.put("campoError", "general");
//            result.put("msgError", "Clave ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        ClaveEntity registro = new ClaveEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setClaveID(JS.toLong(claveID));
//        registro.setIndDel(Constantes.REGISTRO_ACTIVO);
//
//        setCamposAuditoria(request, registro, true);
//
//        // setearlo al mapa resultado
//        result.put("registro", registro);
//
//        return result;
//    }
//
//    public ModelAndView verClave(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros y validar
//            String claveID = JS.toBlank(request.getParameter("claveID"));
//            if (!JS._numeroEntero(claveID)) throw new AppException("RCC-VCA-258", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "clave-ver", modulo, plantilla);
//
//            // delegar logica a servicios
//            ClaveEntity registro = claveService.selectByID(JS.toLong(claveID));
//            if (registro == null) throw new AppException("RCC-VEC-259", "Clave no encontrada");
//
//            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
//
//            // poblar el modelo
//            model.put("registro", registro);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RCC-VCA-123", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
}
