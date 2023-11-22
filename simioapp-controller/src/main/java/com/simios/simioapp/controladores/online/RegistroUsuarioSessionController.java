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
//import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;
//import com.simios.simioapp.servicios.UsuarioSessionService;
//
public class RegistroUsuarioSessionController extends BaseController {
//
//    private static final Logger log = Logger.getLogger(RegistroUsuarioSessionController.class.getName());
//
//    private static final String plantilla = "default";
//    private static final String modulo = "registro-usuariosession";
//
//    @Autowired
//    @Qualifier("catalogo")
//    private Catalogo catalogo;
//
//    @Autowired
//    @Qualifier("usuariosessionService")
//    private UsuarioSessionService usuariosessionService;
//
//    public ModelAndView buscarUsuarioSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros
//            // TODO/FIXME: recoger los atributos: 
//            String usuarioID = JS.toUpperBlank(request.getParameter("int_usuarioID"));
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaPage(model, "usuariosession-buscar-grilla", modulo, plantilla);
//
//            // delegar logica a servicios
//            HashMap<String, Object> params = new HashMap<String, Object>();
//
//            // TODO/FIXME: armar el mapa con los parametros
//			if (JS._numeroEntero(usuarioID)) params.put("usuarioID", JS.toLong(usuarioID));			
//			if (JS._numero(usuarioID)) params.put("usuarioID", JS.toDouble(usuarioID));
//			if (JS._fecha(usuarioID)) params.put("usuarioID", JS.toDate(usuarioID));			
//			if (!JS._vacio(usuarioID)) params.put("usuarioID", usuarioID + "%");
//			if (!JS._vacio(usuarioID)) params.put("usuarioID", usuarioID);
//
//			// FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//			// List<UsuarioSessionEntity> listaUsuarioSession = usuariosessionService.selectByMap(params);
//			List<Map<String, Object>> listaUsuarioSession = usuariosessionService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaUsuarioSession);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RUC-BCA-468", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    private UsuarioSessionEntity buscarRegistroPorUnique(String campoUnique) throws Exception {
//
//        UsuarioSessionEntity filtro = new UsuarioSessionEntity();
//
//        filtro.setXXX(campoUnique);
//
//        List<UsuarioSessionEntity> resultados = usuariosessionService.select(filtro);
//
//        if (CollectionUtils.isNotEmpty(resultados)) {
//            return resultados.get(0);
//        }
//
//        return null;
//
//    }
//
//    public ModelAndView eliminarUsuarioSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
//            if (!JS._numeroEntero(usuarioID)) throw new AppException("RUC-ECA-469", "Argumento ilegal del request");
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            UsuarioSessionEntity entity = new UsuarioSessionEntity();
//
//            entity.setUsuarioSessionID(JS.toLong(usuarioID));
//
//            usuariosessionService.delete(entity);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RUC-ELI-470", dataJSON, sos);
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
//                throw new AppException("RUC-GED-471", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            UsuarioSessionEntity registro = (UsuarioSessionEntity) result.get("registro");
//
//            usuariosessionService.update(registro);
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RUC-GUA-472", dataJSON, sos);
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
//                throw new AppException("RUC-GNU-473", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            UsuarioSessionEntity registro = (UsuarioSessionEntity) result.get("registro");
//
//            usuariosessionService.insert(registro);
//
//            // poblar modelo
//            model.put("idGenerado", String.valueOf(registro.getUsuarioSessionID()));
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RUC-GNU-474", dataJSON, sos);
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
//            setVistaTemplate(model, "usuariosession-buscar", modulo, plantilla);
//
//            // delegar logica a servicios
//            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
//
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			
//			params.put("indDel", Constantes.REGISTRO_ACTIVO);
//
//			// FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//			// List<UsuarioSessionEntity> listaUsuarioSession = usuariosessionService.selectByMap(params);
//			List<Map<String, Object>> listaUsuarioSession = usuariosessionService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaUsuarioSession);
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
//            String msgError = handleMsgError("RUC-MBU-001", sos);
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
//            String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
//            if (!JS._numeroEntero(usuarioID)) throw new AppException("RUC-MED-475", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "usuariosession-editar", modulo, plantilla);
//
//            // delegar logica a servicios
//            UsuarioSessionEntity registro = usuariosessionService.selectByID(JS.toLong(usuarioID));
//            if (registro == null) throw new AppException("RUC-MED-476", "UsuarioSessi&oacute;n no encontrada");
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
//            String msgError = handleMsgError("RUC-MED-212", sos);
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
//            setVistaTemplate(model, "usuariosession-nuevo", modulo, plantilla);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RUC-MNU-017", sos);
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
//            String usuarioID = JS.toUpperBlank(request.getParameter("int_usuarioID"));
//			// FIXME/TODO: recoger los demas campos
//			// si son campos numeros o fechas en la vista se debe generar dos controles HTML INICIO - FIN
//
//
//            Map<String, Object> result = new HashMap<String, Object>();
//
//            // validacion para usuarioID
//            if (!JS._vacio(usuarioID)) {
//
//                msg = JS._numeroEnteroNoNegativoString(usuarioID, Constantes.VALIDACION_CAMPO);
//                if (!JS._vacio(msg)) {
//                    result.put("campoError", "int_usuarioID");
//                    result.put("msgError", msg);
//
//                    throw new AppException("RUC-VBU-477", msg, JExceptionEnum.VALIDACION, result);
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
//            String msgError = handleJSONError("RUC-VAB-478", dataJSON, sos);
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
//
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        // validacion para usuarioID
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(usuarioID, Constantes.VALIDACION_CAMPO); ... return result
//
//		// validar que no exista otro igual
//		UsuarioSessionEntity found = buscarRegistroPorUnique(nombre);
//		if (found != null && !JS._equiv(found.getUsuarioSessionID(), JS.toLong(usuarioID))) {
//			result.put("campoError", "general");
//			result.put("msgError", "UsuarioSessi&oacute;n ya fue registrada");
//
//			return result;
//		}
//		
//        // armar entity
//        UsuarioSessionEntity registro = new UsuarioSessionEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setUsuarioSessionID(JS.toLong(usuarioID));
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
//
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        // validacion para nombre
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(usuarioID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        UsuarioSessionEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null) {
//            result.put("campoError", "general");
//            result.put("msgError", "UsuarioSessi&oacute;n ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        UsuarioSessionEntity registro = new UsuarioSessionEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setUsuarioSessionID(JS.toLong(usuarioID));
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
//    public ModelAndView verUsuarioSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros y validar
//            String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
//            if (!JS._numeroEntero(usuarioID)) throw new AppException("RUC-VCA-479", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "usuariosession-ver", modulo, plantilla);
//
//            // delegar logica a servicios
//            UsuarioSessionEntity registro = usuariosessionService.selectByID(JS.toLong(usuarioID));
//            if (registro == null) throw new AppException("RUC-VEC-480", "UsuarioSessi&oacute;n no encontrada");
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
//            String msgError = handleMsgError("RUC-VCA-123", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
}
