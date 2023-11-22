package com.simios.simioapp.controladores.online;

import com.simios.simioapp.controladores.base.BaseController;

public class RegistroPermisosSistemaController extends BaseController {
    //
    // private static final Logger log =
    // LogManager.getLogger(RegistroPermisosSistemaController.class.getName());
    //
    // private static final String plantilla = "default";
    // private static final String modulo = "registro-permisossistema";
    //
    // @Autowired
    // @Qualifier("catalogo")
    // private Catalogo catalogo;
    //
    // @Autowired
    // @Qualifier("permisossistemaService")
    // private PermisosSistemaService permisossistemaService;
    //
    // public ModelAndView buscarPermisosSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros
    // // TODO/FIXME: recoger los atributos:
    // String usuarioID =
    // JS.toUpperBlank(request.getParameter("int_usuarioID"));
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaPage(model, "permisossistema-buscar-grilla", modulo, plantilla);
    //
    // // delegar logica a servicios
    // HashMap<String, Object> params = new HashMap<String, Object>();
    //
    // // TODO/FIXME: armar el mapa con los parametros
    // if (JS._numeroEntero(usuarioID)) params.put("usuarioID",
    // JS.toLong(usuarioID));
    // if (JS._numero(usuarioID)) params.put("usuarioID",
    // JS.toDouble(usuarioID));
    // if (JS._fecha(usuarioID)) params.put("usuarioID", JS.toDate(usuarioID));
    // if (!JS._vacio(usuarioID)) params.put("usuarioID", usuarioID + "%");
    // if (!JS._vacio(usuarioID)) params.put("usuarioID", usuarioID);
    //
    // // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y
    // la segunda para mostrar descripciones usando subconsultas)
    // // List<PermisosSistemaEntity> listaPermisosSistema =
    // permisossistemaService.selectByMap(params);
    // List<Map<String, Object>> listaPermisosSistema =
    // permisossistemaService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaPermisosSistema);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("RPC-BCA-289", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // private PermisosSistemaEntity buscarRegistroPorUnique(String campoUnique)
    // throws Exception {
    //
    // PermisosSistemaEntity filtro = new PermisosSistemaEntity();
    //
    // // FIXME/TODO: poner el set del campo unique que corresponde
    // filtro.setXXX(campoUnique);
    //
    // List<PermisosSistemaEntity> resultados =
    // permisossistemaService.select(filtro);
    //
    // if (CollectionUtils.isNotEmpty(resultados)) {
    // return resultados.get(0);
    // }
    //
    // return null;
    //
    // }
    //
    // public ModelAndView eliminarPermisosSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
    // if (!JS._numeroEntero(usuarioID)) throw new
    // RestobarException("RPC-ECA-290", "Argumento ilegal del request");
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // PermisosSistemaEntity entity = new PermisosSistemaEntity();
    //
    // entity.setPermisosSistemaID(JS.toLong(usuarioID));
    //
    // permisossistemaService.delete(entity);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RPC-ELI-291", dataJSON, sos);
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
    // throw new RestobarException("RPC-GED-292", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // PermisosSistemaEntity registro = (PermisosSistemaEntity)
    // result.get("registro");
    //
    // permisossistemaService.update(registro);
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RPC-GUA-293", dataJSON, sos);
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
    // throw new RestobarException("RPC-GNU-294", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // PermisosSistemaEntity registro = (PermisosSistemaEntity)
    // result.get("registro");
    //
    // permisossistemaService.insert(registro);
    //
    // // poblar modelo
    // model.put("idGenerado", String.valueOf(registro.getPermisosSistemaID()));
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RPC-GNU-295", dataJSON, sos);
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
    // setVistaTemplate(model, "permisossistema-buscar", modulo, plantilla);
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
    // // List<PermisosSistemaEntity> listaPermisosSistema =
    // permisossistemaService.selectByMap(params);
    // List<Map<String, Object>> listaPermisosSistema =
    // permisossistemaService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaPermisosSistema);
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
    // String msgError = handleMsgError("RPC-MBU-001", sos);
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
    // String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
    // if (!JS._numeroEntero(usuarioID)) throw new
    // RestobarException("RPC-MED-296", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "permisossistema-editar", modulo, plantilla);
    //
    // // delegar logica a servicios
    // PermisosSistemaEntity registro =
    // permisossistemaService.selectByID(JS.toLong(usuarioID));
    // if (registro == null) throw new RestobarException("RPC-MED-297",
    // "PermisosSistema no encontrada");
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
    // String msgError = handleMsgError("RPC-MED-212", sos);
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
    // setVistaTemplate(model, "permisossistema-nuevo", modulo, plantilla);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("RPC-MNU-017", sos);
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
    // String usuarioID =
    // JS.toUpperBlank(request.getParameter("int_usuarioID"));
    // // FIXME/TODO: recoger los demas campos
    // // si son campos numeros o fechas en la vista se debe generar dos
    // controles HTML INICIO - FIN
    //
    // String msg = StringUtils.EMPTY;
    // Map<String, Object> result = new HashMap<String, Object>();
    //
    // // validacion para usuarioID
    // if (!JS._vacio(usuarioID)) {
    //
    // msg = JS._numeroEnteroNoNegativoString(usuarioID,
    // Constantes.VALIDACION_CAMPO);
    // if (!JS._vacio(msg)) {
    // result.put("campoError", "int_usuarioID");
    // result.put("msgError", msg);
    //
    // throw new RestobarException("RPC-VBU-298", msg,
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
    // String msgError = handleJSONError("RPC-VAB-299", dataJSON, sos);
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
    // // validacion para usuarioID
    // // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(usuarioID,
    // Constantes.VALIDACION_CAMPO); ... return result
    //
    // // validar que no exista otro igual
    // PermisosSistemaEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null && !JS._equiv(found.getPermisosSistemaID(),
    // JS.toLong(usuarioID))) {
    // result.put("campoError", "general");
    // result.put("msgError", "PermisosSistema ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // PermisosSistemaEntity registro = new PermisosSistemaEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setPermisosSistemaID(JS.toLong(usuarioID));
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
    // // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(usuarioID,
    // Constantes.VALIDACION_CAMPO); ... return result
    //
    // // validar que no exista otro igual
    // PermisosSistemaEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null) {
    // result.put("campoError", "general");
    // result.put("msgError", "PermisosSistema ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // PermisosSistemaEntity registro = new PermisosSistemaEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setPermisosSistemaID(JS.toLong(usuarioID));
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
    // public ModelAndView verPermisosSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros y validar
    // String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
    // if (!JS._numeroEntero(usuarioID)) throw new
    // RestobarException("RPC-VCA-300", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "permisossistema-ver", modulo, plantilla);
    //
    // // delegar logica a servicios
    // PermisosSistemaEntity registro =
    // permisossistemaService.selectByID(JS.toLong(usuarioID));
    // if (registro == null) throw new RestobarException("RPC-VEC-301",
    // "PermisosSistema no encontrada");
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
    // String msgError = handleMsgError("RPC-VCA-123", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
}
