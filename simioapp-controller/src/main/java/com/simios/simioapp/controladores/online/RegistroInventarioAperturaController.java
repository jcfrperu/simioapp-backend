package com.simios.simioapp.controladores.online;

//import com.jcfr.utiles.enums.JExceptionEnum;
//import com.simios.simioapp.comunes.beans.DataJsonBean;
//import com.simios.simioapp.comunes.exceptions.SimioException;
//import com.simios.simioapp.comunes.utiles.Catalogo;
//import com.simios.simioapp.comunes.utiles.Constantes;
//import com.simios.simioapp.controladores.base.BaseController;
//import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;
//import com.simios.simioapp.servicios.InventarioAperturaService;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.logging.Logger;

import com.simios.simioapp.controladores.base.BaseController;

public class RegistroInventarioAperturaController extends BaseController {

//    private static final Logger log = Logger.getLogger(RegistroInventarioAperturaController.class.getName());
//
//    private static final String plantilla = "default";
//    private static final String modulo = "registro-inventarioapertura";
//
//    @Autowired
//    @Qualifier("catalogo")
//    private Catalogo catalogo;
//
//    @Autowired
//    @Qualifier("inventarioaperturaService")
//    private InventarioAperturaService inventarioaperturaService;
//
//    public ModelAndView buscarInventarioApertura(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros
//            // TODO/FIXME: recoger los atributos:
//            String inventarioAperturaID = JS.toUpperBlank(request.getParameter("int_inventarioAperturaID"));
//
//            // crear modelo
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaPage(model, "inventarioapertura-buscar-grilla", modulo, plantilla);
//
//            // delegar logica a servicios
//            HashMap<String, Object> params = new HashMap<>();
//
//            // TODO/FIXME: armar el mapa con los parametros
//            if (JS._numeroEntero(inventarioAperturaID))
//                params.put("inventarioAperturaID", JS.toLong(inventarioAperturaID));
//            if (JS._numero(inventarioAperturaID)) params.put("inventarioAperturaID", JS.toDouble(inventarioAperturaID));
//            if (JS._fecha(inventarioAperturaID)) params.put("inventarioAperturaID", JS.toDate(inventarioAperturaID));
//            if (!JS._vacio(inventarioAperturaID)) params.put("inventarioAperturaID", inventarioAperturaID + "%");
//            if (!JS._vacio(inventarioAperturaID)) params.put("inventarioAperturaID", inventarioAperturaID);
//
//            // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//            // List<InventarioAperturaEntity> listaInventarioApertura = inventarioaperturaService.selectByMap(params);
//            List<Map<String, Object>> listaInventarioApertura = inventarioaperturaService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaInventarioApertura);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RIC-BCA-385", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError, plantilla);
//        }
//    }
//
//    private InventarioAperturaEntity buscarRegistroPorUnique(String campoUnique) throws Exception {
//
//        InventarioAperturaEntity filtro = new InventarioAperturaEntity();
//
//        // FIXME/TODO: poner el set del campo unique que corresponde
//        filtro.setXXX(campoUnique);
//
//        List<InventarioAperturaEntity> resultados = inventarioaperturaService.select(filtro);
//
//        if (CollectionUtils.isNotEmpty(resultados)) {
//            return resultados.get(0);
//        }
//
//        return null;
//
//    }
//
//    public ModelAndView eliminarInventarioApertura(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            String inventarioAperturaID = JS.toBlank(request.getParameter("inventarioAperturaID"));
//            if (!JS._numeroEntero(inventarioAperturaID))
//                throw new SimioException("RIC-ECA-386", "Argumento ilegal del request");
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            InventarioAperturaEntity entity = new InventarioAperturaEntity();
//
//            entity.setInventarioAperturaID(JS.toLong(inventarioAperturaID));
//
//            inventarioaperturaService.delete(entity);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-ELI-387", dataJSON, sos);
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
//                throw new SimioException("RIC-GED-388", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<>();
//
//            // delegar logica a servicios
//            InventarioAperturaEntity registro = (InventarioAperturaEntity) result.get("registro");
//
//            inventarioaperturaService.update(registro);
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-GUA-389", dataJSON, sos);
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
//                throw new SimioException("RIC-GNU-390", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<String, Object>();
//
//            // delegar logica a servicios
//            InventarioAperturaEntity registro = (InventarioAperturaEntity) result.get("registro");
//
//            inventarioaperturaService.insert(registro);
//
//            // poblar modelo
//            model.put("idGenerado", String.valueOf(registro.getInventarioAperturaID()));
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-GNU-391", dataJSON, sos);
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
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "inventarioapertura-buscar", modulo, plantilla);
//
//            // delegar logica a servicios
//            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
//
//            HashMap<String, Object> params = new HashMap<String, Object>();
//
//            params.put("indDel", Constantes.REGISTRO_ACTIVO);
//
//            // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//            // List<InventarioAperturaEntity> listaInventarioApertura = inventarioaperturaService.selectByMap(params);
//            List<Map<String, Object>> listaInventarioApertura = inventarioaperturaService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaInventarioApertura);
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
//            return handleErrorModelAndView(model, msgError, plantilla);
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
//            String inventarioAperturaID = JS.toBlank(request.getParameter("inventarioAperturaID"));
//            if (!JS._numeroEntero(inventarioAperturaID))
//                throw new SimioException("RIC-MED-392", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "inventarioapertura-editar", modulo, plantilla);
//
//            // delegar logica a servicios
//            InventarioAperturaEntity registro = inventarioaperturaService.selectByID(JS.toLong(inventarioAperturaID));
//            if (registro == null) throw new SimioException("RIC-MED-393", "InventarioApertura no encontrada");
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
//            setVistaTemplate(model, "inventarioapertura-nuevo", modulo, plantilla);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RIC-MNU-017", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError, plantilla);
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
//            String inventarioAperturaID = JS.toUpperBlank(request.getParameter("int_inventarioAperturaID"));
//            // FIXME/TODO: recoger los demas campos
//            // si son campos numeros o fechas en la vista se debe generar dos controles HTML INICIO - FIN
//
//            String msg = StringUtils.EMPTY;
//            Map<String, Object> result = new HashMap<String, Object>();
//
//            // validacion para inventarioAperturaID
//            if (!JS._vacio(inventarioAperturaID)) {
//
//                msg = JS._numeroEnteroNoNegativoString(inventarioAperturaID, Constantes.VALIDACION_CAMPO);
//                if (!JS._vacio(msg)) {
//                    result.put("campoError", "int_inventarioAperturaID");
//                    result.put("msgError", msg);
//
//                    throw new SimioException("RIC-VBU-394", msg, JExceptionEnum.VALIDACION, result);
//                }
//            }
//            // FIXME/TODO: colocar el resto de validaciones, lanzando el throw new SimioException() de ejemplo de arriba
//
//            HashMap<String, Object> model = new HashMap<>();
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RIC-VAB-395", dataJSON, sos);
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
//        // validacion para inventarioAperturaID
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(inventarioAperturaID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        InventarioAperturaEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null && !JS._equiv(found.getInventarioAperturaID(), JS.toLong(inventarioAperturaID))) {
//            result.put("campoError", "general");
//            result.put("msgError", "InventarioApertura ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        InventarioAperturaEntity registro = new InventarioAperturaEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setInventarioAperturaID(JS.toLong(inventarioAperturaID));
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
//        Map<String, Object> result = new HashMap<>();
//
//        // validacion para nombre
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(inventarioAperturaID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        InventarioAperturaEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null) {
//            result.put("campoError", "general");
//            result.put("msgError", "InventarioApertura ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        InventarioAperturaEntity registro = new InventarioAperturaEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setInventarioAperturaID(JS.toLong(inventarioAperturaID));
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
//    public ModelAndView verInventarioApertura(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros y validar
//            String inventarioAperturaID = JS.toBlank(request.getParameter("inventarioAperturaID"));
//            if (!JS._numeroEntero(inventarioAperturaID))
//                throw new SimioException("RIC-VCA-396", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "inventarioapertura-ver", modulo, plantilla);
//
//            // delegar logica a servicios
//            InventarioAperturaEntity registro = inventarioaperturaService.selectByID(JS.toLong(inventarioAperturaID));
//            if (registro == null) throw new SimioException("RIC-VEC-397", "InventarioApertura no encontrada");
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
//            return handleErrorModelAndView(model, msgError, plantilla);
//        }
//    }
}
