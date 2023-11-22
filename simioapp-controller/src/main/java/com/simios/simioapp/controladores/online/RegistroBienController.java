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
//import com.simios.simioapp.comunes.beans.DataJsonBean;
//import com.simios.simioapp.comunes.utiles.Catalogo;
//import com.simios.simioapp.comunes.utiles.Constantes;
//import com.simios.simioapp.dominio.entidades.BienEntity;
//import com.simios.simioapp.servicios.BienService;
//
public class RegistroBienController extends BaseController {
//
//    private static final Logger log = Logger.getLogger(RegistroBienController.class.getName());
//
//    private static final String plantilla = "default";
//    private static final String modulo = "registro-bien";
//
//    @Autowired
//    @Qualifier("catalogo")
//    private Catalogo catalogo;
//
//    @Autowired
//    @Qualifier("bienService")
//    private BienService bienService;
//
//    public ModelAndView buscarBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros
//            // TODO/FIXME: recoger los atributos:
//            String bienID = JS.toUpperBlank(request.getParameter("int_bienID"));
//
//            // crear modelo
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaPage(model, "bien-buscar-grilla", modulo, plantilla);
//
//            // delegar logica a servicios
//            HashMap<String, Object> params = new HashMap<>();
//
//            // TODO/FIXME: armar el mapa con los parametros
//            if (JS._numeroEntero(bienID)) params.put("bienID", JS.toLong(bienID));
//            if (JS._numero(bienID)) params.put("bienID", JS.toDouble(bienID));
//            if (JS._fecha(bienID)) params.put("bienID", JS.toDate(bienID));
//            if (!JS._vacio(bienID)) params.put("bienID", bienID + "%");
//            if (!JS._vacio(bienID)) params.put("bienID", bienID);
//
//            // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//            // List<BienEntity> listaBien = bienService.selectByMap(params);
//            List<Map<String, Object>> listaBien = bienService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaBien);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RBC-BCA-168", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
//
//    private BienEntity buscarRegistroPorUnique(String campoUnique) throws Exception {
//
//        BienEntity filtro = new BienEntity();
//
//        // FIXME/TODO: poner el set del campo unique que corresponde
//        filtro.setXXX(campoUnique);
//
//        List<BienEntity> resultados = bienService.select(filtro);
//
//        if (CollectionUtils.isNotEmpty(resultados)) {
//            return resultados.get(0);
//        }
//
//        return null;
//
//    }
//
//    public ModelAndView eliminarBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        DataJsonBean dataJSON = new DataJsonBean();
//
//        try {
//
//            // recoger parametros y validar
//            String bienID = JS.toBlank(request.getParameter("bienID"));
//            if (!JS._numeroEntero(bienID)) throw new AppException("RBC-ECA-169", "Argumento ilegal del request");
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<>();
//
//            // delegar logica a servicios
//            BienEntity entity = new BienEntity();
//
//            entity.setBienID(JS.toLong(bienID));
//
//            bienService.delete(entity);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RBC-ELI-170", dataJSON, sos);
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
//                throw new AppException("RBC-GED-171", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<>();
//
//            // delegar logica a servicios
//            BienEntity registro = (BienEntity) result.get("registro");
//
//            bienService.update(registro);
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RBC-GUA-172", dataJSON, sos);
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
//                throw new AppException("RBC-GNU-173", msgError, JExceptionEnum.VALIDACION, result);
//            }
//
//            // crear modelo
//            HashMap<String, Object> model = new HashMap<>();
//
//            // delegar logica a servicios
//            BienEntity registro = (BienEntity) result.get("registro");
//
//            bienService.insert(registro);
//
//            // poblar modelo
//            model.put("idGenerado", String.valueOf(registro.getBienID()));
//
//            // limpiar session
//            limpiarSession(request);
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RBC-GNU-174", dataJSON, sos);
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
//            setVistaTemplate(model, "bien-buscar", modulo, plantilla);
//
//            // delegar logica a servicios
//            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
//
//            HashMap<String, Object> params = new HashMap<>();
//
//            params.put("indDel", Constantes.REGISTRO_ACTIVO);
//
//            // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y la segunda para mostrar descripciones usando subconsultas)
//            // List<BienEntity> listaBien = bienService.selectByMap(params);
//            List<Map<String, Object>> listaBien = bienService.selectByMapGrilla(params);
//
//            // poblar modelo
//            model.put("lista", listaBien);
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
//            String msgError = handleMsgError("RBC-MBU-001", sos);
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
//            String bienID = JS.toBlank(request.getParameter("bienID"));
//            if (!JS._numeroEntero(bienID)) throw new AppException("RBC-MED-175", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "bien-editar", modulo, plantilla);
//
//            // delegar logica a servicios
//            BienEntity registro = bienService.selectByID(JS.toLong(bienID));
//            if (registro == null) throw new AppException("RBC-MED-176", "Bien no encontrada");
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
//            String msgError = handleMsgError("RBC-MED-212", sos);
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
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "bien-nuevo", modulo, plantilla);
//
//            // renderizar vista
//            return handleModelAndView(model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleMsgError("RBC-MNU-017", sos);
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
//            String bienID = JS.toUpperBlank(request.getParameter("int_bienID"));
//            // FIXME/TODO: recoger los demas campos
//            // si son campos numeros o fechas en la vista se debe generar dos controles HTML INICIO - FIN
//
//            String msg = StringUtils.EMPTY;
//            Map<String, Object> result = new HashMap<>();
//
//            // validacion para bienID
//            if (!JS._vacio(bienID)) {
//
//                msg = JS._numeroEnteroNoNegativoString(bienID, Constantes.VALIDACION_CAMPO);
//                if (!JS._vacio(msg)) {
//                    result.put("campoError", "int_bienID");
//                    result.put("msgError", msg);
//
//                    throw new AppException("RBC-VBU-177", msg, JExceptionEnum.VALIDACION, result);
//                }
//            }
//            // FIXME/TODO: colocar el resto de validaciones, lanzando el throw new AppException() de ejemplo de arriba
//
//            HashMap<String, Object> model = new HashMap<>();
//
//            dataJSON.setRespuesta("ok", null, model);
//
//        } catch (Exception sos) {
//
//            // manejo centralizado de errores
//            String msgError = handleJSONError("RBC-VAB-178", dataJSON, sos);
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
//        Map<String, Object> result = new HashMap<>();
//
//        // validacion para bienID
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(bienID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        BienEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null && !JS._equiv(found.getBienID(), JS.toLong(bienID))) {
//            result.put("campoError", "general");
//            result.put("msgError", "Bien ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        BienEntity registro = new BienEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setBienID(JS.toLong(bienID));
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
//        // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(bienID, Constantes.VALIDACION_CAMPO); ... return result
//
//        // validar que no exista otro igual
//        BienEntity found = buscarRegistroPorUnique(nombre);
//        if (found != null) {
//            result.put("campoError", "general");
//            result.put("msgError", "Bien ya fue registrada");
//
//            return result;
//        }
//
//        // armar entity
//        BienEntity registro = new BienEntity();
//
//        // TODO/FIXME: armar el entity -> registro.setBienID(JS.toLong(bienID));
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
//    public ModelAndView verBien(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        Map<String, Object> model = null;
//
//        try {
//
//            // recoger parametros y validar
//            String bienID = JS.toBlank(request.getParameter("bienID"));
//            if (!JS._numeroEntero(bienID)) throw new AppException("RBC-VCA-179", "Argumento ilegal del request");
//
//            // crear modelo
//            model = new HashMap<>();
//
//            // setear siguiente vista
//            setVistaTemplate(model, "bien-ver", modulo, plantilla);
//
//            // delegar logica a servicios
//            BienEntity registro = bienService.selectByID(JS.toLong(bienID));
//            if (registro == null) throw new AppException("RBC-VEC-180", "Bien no encontrada");
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
//            String msgError = handleMsgError("RBC-VCA-123", sos);
//            log.severe(msgError);
//            return handleErrorModelAndView(model, msgError);
//        }
//    }
}
