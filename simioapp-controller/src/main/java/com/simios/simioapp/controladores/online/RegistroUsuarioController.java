package com.simios.simioapp.controladores.online;

import com.simios.simioapp.controladores.base.BaseController;

public class RegistroUsuarioController extends BaseController {
    //
    // private static final Logger log =
    // LogManager.getLogger(RegistroUsuarioController.class.getName());
    //
    // private static final String plantilla = "default";
    // private static final String modulo = "registro-usuario";
    //
    // @Autowired
    // @Qualifier("catalogo")
    // private Catalogo catalogo;
    //
    // @Autowired
    // @Qualifier("usuarioService")
    // private UsuarioService usuarioService;
    //
    // public ModelAndView buscarUsuario(HttpServletRequest request,
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
    // setVistaPage(model, "usuario-buscar-grilla", modulo, plantilla);
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
    // // List<UsuarioEntity> listaUsuario = usuarioService.selectByMap(params);
    // List<Map<String, Object>> listaUsuario =
    // usuarioService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaUsuario);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("RUC-BCA-328", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // private UsuarioEntity buscarRegistroPorUnique(String campoUnique) throws
    // Exception {
    //
    // UsuarioEntity filtro = new UsuarioEntity();
    //
    // filtro.setXXX(campoUnique);
    //
    // List<UsuarioEntity> resultados = usuarioService.select(filtro);
    //
    // if (CollectionUtils.isNotEmpty(resultados)) {
    // return resultados.get(0);
    // }
    //
    // return null;
    //
    // }
    //
    // public ModelAndView eliminarUsuario(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
    // if (!JS._numeroEntero(usuarioID)) throw new
    // RestobarException("RUC-ECA-329", "Argumento ilegal del request");
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // UsuarioEntity entity = new UsuarioEntity();
    //
    // entity.setUsuarioID(JS.toLong(usuarioID));
    //
    // usuarioService.delete(entity);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RUC-ELI-330", dataJSON, sos);
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
    // throw new RestobarException("RUC-GED-331", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // UsuarioEntity registro = (UsuarioEntity) result.get("registro");
    //
    // usuarioService.update(registro);
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RUC-GUA-332", dataJSON, sos);
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
    // throw new RestobarException("RUC-GNU-333", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // UsuarioEntity registro = (UsuarioEntity) result.get("registro");
    //
    // usuarioService.insert(registro);
    //
    // // poblar modelo
    // model.put("idGenerado", String.valueOf(registro.getUsuarioID()));
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RUC-GNU-334", dataJSON, sos);
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
    // setVistaTemplate(model, "usuario-buscar", modulo, plantilla);
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
    // // List<UsuarioEntity> listaUsuario = usuarioService.selectByMap(params);
    // List<Map<String, Object>> listaUsuario =
    // usuarioService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaUsuario);
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
    // String msgError = handleMsgError("RUC-MBU-001", sos);
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
    // RestobarException("RUC-MED-335", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "usuario-editar", modulo, plantilla);
    //
    // // delegar logica a servicios
    // UsuarioEntity registro = usuarioService.selectByID(JS.toLong(usuarioID));
    // if (registro == null) throw new RestobarException("RUC-MED-336", "Usuario
    // no encontrada");
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
    // String msgError = handleMsgError("RUC-MED-212", sos);
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
    // setVistaTemplate(model, "usuario-nuevo", modulo, plantilla);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("RUC-MNU-017", sos);
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
    // throw new RestobarException("RUC-VBU-337", msg,
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
    // String msgError = handleJSONError("RUC-VAB-338", dataJSON, sos);
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
    // UsuarioEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null && !JS._equiv(found.getUsuarioID(),
    // JS.toLong(usuarioID))) {
    // result.put("campoError", "general");
    // result.put("msgError", "Usuario ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // UsuarioEntity registro = new UsuarioEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setUsuarioID(JS.toLong(usuarioID));
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
    // UsuarioEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null) {
    // result.put("campoError", "general");
    // result.put("msgError", "Usuario ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // UsuarioEntity registro = new UsuarioEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setUsuarioID(JS.toLong(usuarioID));
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
    // public ModelAndView verUsuario(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros y validar
    // String usuarioID = JS.toBlank(request.getParameter("usuarioID"));
    // if (!JS._numeroEntero(usuarioID)) throw new
    // RestobarException("RUC-VCA-339", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "usuario-ver", modulo, plantilla);
    //
    // // delegar logica a servicios
    // UsuarioEntity registro = usuarioService.selectByID(JS.toLong(usuarioID));
    // if (registro == null) throw new RestobarException("RUC-VEC-340", "Usuario
    // no encontrada");
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
    // String msgError = handleMsgError("RUC-VCA-123", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
}
