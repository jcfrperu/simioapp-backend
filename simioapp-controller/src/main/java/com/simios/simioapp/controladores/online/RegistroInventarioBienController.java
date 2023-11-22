package com.simios.simioapp.controladores.online;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.simios.simioapp.controladores.base.BaseController;
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
//import com.simios.simioapp.comunes.utiles.Catalogo;
//import com.simios.simioapp.comunes.utiles.Constantes;
//import com.simios.simioapp.controladores.base.BaseAdminController;
//import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
//import com.simios.simioapp.servicios.InventarioBienService;

import com.simios.simioapp.controladores.base.BaseController;

public class RegistroInventarioBienController extends BaseController {
//
//    private static final Logger log = Logger.getLogger(RegistroInventarioBienController.class.getName());
//
//    private static final String plantilla = "default";
//    private static final String modulo = "registro-inventariobien";
//
//    @Autowired
//    @Qualifier("catalogo")
//    private Catalogo catalogo;
//
//    @Autowired
//    @Qualifier("inventariobienService")
//    private InventarioBienService inventariobienService;
//
//    public ModelAndView buscarInventarioBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros
//            // TODO/FIXME: recoger los atributos:
//            String inventarioBienID = JS.toUpperBlank(request.getParameter("int_inventarioBienID"));
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaPage(model, "inventariobien-buscar-grilla", modulo, plantilla);
//
//            // delegar logica a servicios
//            HashMap<String, Object> params = new HashMap<String, Object>();
//
//            // TODO/FIXME: armar el mapa con los parametros
//			if (JS._numeroEntero(inventarioBienID)) params.put("inventarioBienID", JS.toLong(inventarioBienID));
//			if (JS._numero(inventarioBienID)) params.put("inventarioBienID", JS.toDouble(inventarioBienID));
//			if (JS._fecha(inventarioBienID)) params.put("inventarioBienID", JS.toDate(inventarioBienID));
//			if (!JS._vacio(inventarioBienID)) params.put("inventarioBienID", inventarioBienID + "%");
//			if (!JS._vacio(inventarioBienID)) params.put("inventarioBienID", inventarioBienID);
//
//			// FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//			// List<InventarioBienEntity> listaInventarioBien = inventariobienService.selectByMap(params);
//			List<Map<String, Object>> listaInventarioBien = inventariobienService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaInventarioBien);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RIC-BCA-351", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    private InventarioBienEntity buscarRegistroPorUnique(String campoUnique) throws Exception {
//
//        InventarioBienEntity filtro = new InventarioBienEntity();
//
//        filtro.setXXX(campoUnique);
//
//        List<InventarioBienEntity> resultados = inventariobienService.select(filtro);
//
//        if (CollectionUtils.isNotEmpty(resultados)) {
//            return resultados.get(0);
//        }
//
//        return null;
//
//    }
//
//    public ModelAndView eliminarInventarioBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            String inventarioBienID = JS.toBlank(request.getParameter("inventarioBienID"));
//            if (!JS._numeroEntero(inventarioBienID)) throw new AppException("RIC-ECA-352", "Argumento ilegal del request");
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            InventarioBienEntity entity = new InventarioBienEntity();
//
//            entity.setInventarioBienID(JS.toLong(inventarioBienID));
//
//            inventariobienService.delete(entity);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-ELI-353", dataJSON, sos);
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
//                throw new AppException("RIC-GED-354", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            InventarioBienEntity registro = (InventarioBienEntity) result.get("registro");
//
//            inventariobienService.update(registro);
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-GUA-355", dataJSON, sos);
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
//                throw new AppException("RIC-GNU-356", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            InventarioBienEntity registro = (InventarioBienEntity) result.get("registro");
//
//            inventariobienService.insert(registro);
//
//            // poblar modelo
//            model.put("idGenerado", String.valueOf(registro.getInventarioBienID()));
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-GNU-357", dataJSON, sos);
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
//            setVistaTemplate(model, "inventariobien-buscar", modulo, plantilla);
//
//            // delegar logica a servicios
//            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
//
//			HashMap<String, Object> params = new HashMap<String, Object>();
//
//			params.put("indDel", Constantes.REGISTRO_ACTIVO);
//
//			// FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//			// List<InventarioBienEntity> listaInventarioBien = inventariobienService.selectByMap(params);
//			List<Map<String, Object>> listaInventarioBien = inventariobienService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaInventarioBien);
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
//            String msgError = handleMsgError("RIC-MBU-001", sos);
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
//            String inventarioBienID = JS.toBlank(request.getParameter("inventarioBienID"));
//            if (!JS._numeroEntero(inventarioBienID)) throw new AppException("RIC-MED-358", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "inventariobien-editar", modulo, plantilla);
//
//            // delegar logica a servicios
//            InventarioBienEntity registro = inventariobienService.selectByID(JS.toLong(inventarioBienID));
//            if (registro == null) throw new AppException("RIC-MED-359", "InventarioBien no encontrada");
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
//            String msgError = handleMsgError("RIC-MED-212", sos);
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
//            setVistaTemplate(model, "inventariobien-nuevo", modulo, plantilla);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RIC-MNU-017", sos);
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
//            String inventarioBienID = JS.toUpperBlank(request.getParameter("int_inventarioBienID"));
//			// FIXME/TODO: recoger los demas campos
//			// si son campos numeros o fechas en la vista se debe generar dos controles HTML INICIO - FIN
//
//            String msg = StringUtils.EMPTY;
//            Map<String, Object> result = new HashMap<String, Object>();
//
//            // validacion para inventarioBienID
//            if (!JS._vacio(inventarioBienID)) {
//
//                msg = JS._numeroEnteroNoNegativoString(inventarioBienID, Constantes.VALIDACION_CAMPO);
//                if (!JS._vacio(msg)) {
//                    result.put("campoError", "int_inventarioBienID");
//                    result.put("msgError", msg);
//
//                    throw new AppException("RIC-VBU-360", msg, JExceptionEnum.VALIDACION, result);
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
//            String msgError = handleJSONError("RIC-VAB-361", dataJSON, sos);
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
//        // validacion para inventarioBienID
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(inventarioBienID, Constantes.VALIDACION_CAMPO); ... return result
//
//		// validar que no exista otro igual
//		InventarioBienEntity found = buscarRegistroPorUnique(nombre);
//		if (found != null && !JS._equiv(found.getInventarioBienID(), JS.toLong(inventarioBienID))) {
//			result.put("campoError", "general");
//			result.put("msgError", "InventarioBien ya fue registrada");
//
//			return result;
//		}
//
//        // armar entity
//        InventarioBienEntity registro = new InventarioBienEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setInventarioBienID(JS.toLong(inventarioBienID));
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
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(inventarioBienID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        InventarioBienEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null) {
//            result.put("campoError", "general");
//            result.put("msgError", "InventarioBien ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        InventarioBienEntity registro = new InventarioBienEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setInventarioBienID(JS.toLong(inventarioBienID));
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
//    public ModelAndView verInventarioBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros y validar
//            String inventarioBienID = JS.toBlank(request.getParameter("inventarioBienID"));
//            if (!JS._numeroEntero(inventarioBienID)) throw new AppException("RIC-VCA-362", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<String, Object>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "inventariobien-ver", modulo, plantilla);
//
//            // delegar logica a servicios
//            InventarioBienEntity registro = inventariobienService.selectByID(JS.toLong(inventarioBienID));
//            if (registro == null) throw new AppException("RIC-VEC-363", "InventarioBien no encontrada");
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
//            String msgError = handleMsgError("RIC-VCA-123", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
}
