package com.simios.simioapp.controladores.online;

import com.simios.simioapp.controladores.base.BaseController;

public class RegistroOpcionSistemaController extends BaseController {

    // private static final Logger log =
    // LogManager.getLogger(RegistroOpcionSistemaController.class.getName());
    //
    // private static final String plantilla = "default";
    // private static final String modulo = "registro-opcionsistema";
    //
    // @Autowired
    // @Qualifier("catalogo")
    // private Catalogo catalogo;
    //
    // @Autowired
    // @Qualifier("opcionsistemaService")
    // private OpcionSistemaService opcionsistemaService;
    //
    // public ModelAndView buscarOpcionSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros
    // // TODO/FIXME: recoger los atributos:
    // String opcionID = JS.toUpperBlank(request.getParameter("int_opcionID"));
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaPage(model, "opcionsistema-buscar-grilla", modulo, plantilla);
    //
    // // delegar logica a servicios
    // HashMap<String, Object> params = new HashMap<String, Object>();
    //
    // // TODO/FIXME: armar el mapa con los parametros
    // if (JS._numeroEntero(opcionID)) params.put("opcionID",
    // JS.toLong(opcionID));
    // if (JS._numero(opcionID)) params.put("opcionID", JS.toDouble(opcionID));
    // if (JS._fecha(opcionID)) params.put("opcionID", JS.toDate(opcionID));
    // if (!JS._vacio(opcionID)) params.put("opcionID", opcionID + "%");
    // if (!JS._vacio(opcionID)) params.put("opcionID", opcionID);
    //
    // // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y
    // la segunda para mostrar descripciones usando subconsultas)
    // // List<OpcionSistemaEntity> listaOpcionSistema =
    // opcionsistemaService.selectByMap(params);
    // List<Map<String, Object>> listaOpcionSistema =
    // opcionsistemaService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaOpcionSistema);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("ROC-BCA-263", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // private OpcionSistemaEntity buscarRegistroPorUnique(String campoUnique)
    // throws Exception {
    //
    // OpcionSistemaEntity filtro = new OpcionSistemaEntity();
    //
    // // FIXME/TODO: poner el set del campo unique que corresponde
    // filtro.setXXX(campoUnique);
    //
    // List<OpcionSistemaEntity> resultados =
    // opcionsistemaService.select(filtro);
    //
    // if (CollectionUtils.isNotEmpty(resultados)) {
    // return resultados.get(0);
    // }
    //
    // return null;
    //
    // }
    //
    // public ModelAndView eliminarOpcionSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // String opcionID = JS.toBlank(request.getParameter("opcionID"));
    // if (!JS._numeroEntero(opcionID)) throw new
    // RestobarException("ROC-ECA-264", "Argumento ilegal del request");
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // OpcionSistemaEntity entity = new OpcionSistemaEntity();
    //
    // entity.setOpcionSistemaID(JS.toLong(opcionID));
    //
    // opcionsistemaService.delete(entity);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("ROC-ELI-265", dataJSON, sos);
    // log.severe(msgError);
    // }
    //
    // return handleJSONResponse(dataJSON, response);
    // }
    //
    // public ModelAndView guardarEditar(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // Map<String, Object> result = validarEditar(request);
    //
    // String msgError = MapUtils.getString(result, "msgError");
    // if (!JS._vacio(msgError)) {
    // throw new RestobarException("ROC-GED-266", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // OpcionSistemaEntity registro = (OpcionSistemaEntity)
    // result.get("registro");
    //
    // opcionsistemaService.update(registro);
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("ROC-GUA-267", dataJSON, sos);
    // log.severe(msgError);
    // }
    //
    // return handleJSONResponse(dataJSON, response);
    // }
    //
    // public ModelAndView guardarNuevo(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // Map<String, Object> result = validarGuardar(request);
    // String msgError = MapUtils.getString(result, "msgError");
    // if (!JS._vacio(msgError)) {
    // throw new RestobarException("ROC-GNU-268", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // OpcionSistemaEntity registro = (OpcionSistemaEntity)
    // result.get("registro");
    //
    // opcionsistemaService.insert(registro);
    //
    // // poblar modelo
    // model.put("idGenerado", String.valueOf(registro.getOpcionSistemaID()));
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("ROC-GNU-269", dataJSON, sos);
    // log.severe(msgError);
    // }
    //
    // return handleJSONResponse(dataJSON, response);
    // }
    //
    // private void limpiarSession(HttpServletRequest request) throws Exception
    // {
    //
    // // TODO/FIXME
    // // limpiar los atributos de session
    // // setSessionAttribute(request,
    // Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);
    //
    // }
    //
    // public ModelAndView mostrarBuscar(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "opcionsistema-buscar", modulo, plantilla);
    //
    // // delegar logica a servicios
    // cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL,
    // Constantes.REGISTRO_ACTIVO);
    //
    // HashMap<String, Object> params = new HashMap<String, Object>();
    //
    // params.put("indDel", Constantes.REGISTRO_ACTIVO);
    //
    // // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y
    // la segunda para mostrar descripciones usando subconsultas)
    // // List<OpcionSistemaEntity> listaOpcionSistema =
    // opcionsistemaService.selectByMap(params);
    // List<Map<String, Object>> listaOpcionSistema =
    // opcionsistemaService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaOpcionSistema);
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("ROC-MBU-001", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // public ModelAndView mostrarEditar(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros y validar
    // String opcionID = JS.toBlank(request.getParameter("opcionID"));
    // if (!JS._numeroEntero(opcionID)) throw new
    // RestobarException("ROC-MED-270", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "opcionsistema-editar", modulo, plantilla);
    //
    // // delegar logica a servicios
    // OpcionSistemaEntity registro =
    // opcionsistemaService.selectByID(JS.toLong(opcionID));
    // if (registro == null) throw new RestobarException("ROC-MED-271",
    // "OpcionSistema no encontrada");
    //
    // cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL,
    // registro.getIndDel());
    //
    // // poblar el modelo
    // model.put("registro", registro);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("ROC-MED-212", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // public ModelAndView mostrarNuevo(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "opcionsistema-nuevo", modulo, plantilla);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("ROC-MNU-017", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // public ModelAndView validarBuscar(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // String opcionID = JS.toUpperBlank(request.getParameter("int_opcionID"));
    // // FIXME/TODO: recoger los demas campos
    // // si son campos numeros o fechas en la vista se debe generar dos
    // controles HTML INICIO - FIN
    //
    // String msg = StringUtils.EMPTY;
    // Map<String, Object> result = new HashMap<String, Object>();
    //
    // // validacion para opcionID
    // if (!JS._vacio(opcionID)) {
    //
    // msg = JS._numeroEnteroNoNegativoString(opcionID,
    // Constantes.VALIDACION_CAMPO);
    // if (!JS._vacio(msg)) {
    // result.put("campoError", "int_opcionID");
    // result.put("msgError", msg);
    //
    // throw new RestobarException("ROC-VBU-272", msg,
    // JExceptionEnum.VALIDACION, result);
    // }
    // }
    // // FIXME/TODO: colocar el resto de validaciones, lanzando el throw new
    // RestobarException() de ejemplo de arriba
    //
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("ROC-VAB-273", dataJSON, sos);
    // log.severe(msgError);
    // }
    //
    // return handleJSONResponse(dataJSON, response);
    // }
    //
    // private Map<String, Object> validarEditar(HttpServletRequest request)
    // throws Exception {
    //
    // // recoger parametros y validar
    // // TODO/FIXME: recoger atributos (con el id) del request con toBlank ->
    // String xxxx = JS.toUpperBlank(request.getParameter("int_xxx"));
    //
    // String msg = StringUtils.EMPTY;
    // Map<String, Object> result = new HashMap<String, Object>();
    //
    // // validacion para opcionID
    // // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(opcionID,
    // Constantes.VALIDACION_CAMPO); ... return result
    //
    // // validar que no exista otro igual
    // OpcionSistemaEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null && !JS._equiv(found.getOpcionSistemaID(),
    // JS.toLong(opcionID))) {
    // result.put("campoError", "general");
    // result.put("msgError", "OpcionSistema ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // OpcionSistemaEntity registro = new OpcionSistemaEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setOpcionSistemaID(JS.toLong(opcionID));
    // registro.setIndDel(indDel);
    //
    // setCamposAudit(request, registro, false);
    //
    // // setearlo al mapa resultado
    // result.put("registro", registro);
    //
    // return result;
    // }
    //
    // private Map<String, Object> validarGuardar(HttpServletRequest request)
    // throws Exception {
    //
    // // recoger parametros y validar
    // // TODO/FIXME: recoger atributos (sin el id) del request con toBlank ->
    // String xxxx = JS.toUpperBlank(request.getParameter("int_xxx"));
    //
    // String msg = StringUtils.EMPTY;
    // Map<String, Object> result = new HashMap<String, Object>();
    //
    // // validacion para nombre
    // // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(opcionID,
    // Constantes.VALIDACION_CAMPO); ... return result
    //
    // // validar que no exista otro igual
    // OpcionSistemaEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null) {
    // result.put("campoError", "general");
    // result.put("msgError", "OpcionSistema ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // OpcionSistemaEntity registro = new OpcionSistemaEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setOpcionSistemaID(JS.toLong(opcionID));
    // registro.setIndDel(Constantes.REGISTRO_ACTIVO);
    //
    // setCamposAudit(request, registro, true);
    //
    // // setearlo al mapa resultado
    // result.put("registro", registro);
    //
    // return result;
    // }
    //
    // public ModelAndView verOpcionSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros y validar
    // String opcionID = JS.toBlank(request.getParameter("opcionID"));
    // if (!JS._numeroEntero(opcionID)) throw new
    // RestobarException("ROC-VCA-274", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "opcionsistema-ver", modulo, plantilla);
    //
    // // delegar logica a servicios
    // OpcionSistemaEntity registro =
    // opcionsistemaService.selectByID(JS.toLong(opcionID));
    // if (registro == null) throw new RestobarException("ROC-VEC-275",
    // "OpcionSistema no encontrada");
    //
    // cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL,
    // registro.getIndDel());
    //
    // // poblar el modelo
    // model.put("registro", registro);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("ROC-VCA-123", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
}
