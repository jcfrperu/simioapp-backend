package com.simios.simioapp.controladores.online;

import com.simios.simioapp.controladores.base.BaseController;

public class RegistroBotonesSistemaController extends BaseController {

    // private static final Logger log =
    // LogManager.getLogger(RegistroBotonesSistemaController.class.getName());
    //
    // private static final String plantilla = "default";
    // private static final String modulo = "registro-botonessistema";
    //
    // @Autowired
    // @Qualifier("catalogo")
    // private Catalogo catalogo;
    //
    // @Autowired
    // @Qualifier("botonessistemaService")
    // private BotonesSistemaService botonessistemaService;
    //
    // public ModelAndView buscarBotonesSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros
    // // TODO/FIXME: recoger los atributos:
    // String botonID = JS.toUpperBlank(request.getParameter("int_botonID"));
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaPage(model, "botonessistema-buscar-grilla", modulo, plantilla);
    //
    // // delegar logica a servicios
    // HashMap<String, Object> params = new HashMap<String, Object>();
    //
    // // TODO/FIXME: armar el mapa con los parametros
    // if (JS._numeroEntero(botonID)) params.put("botonID", JS.toLong(botonID));
    // if (JS._numero(botonID)) params.put("botonID", JS.toDouble(botonID));
    // if (JS._fecha(botonID)) params.put("botonID", JS.toDate(botonID));
    // if (!JS._vacio(botonID)) params.put("botonID", botonID + "%");
    // if (!JS._vacio(botonID)) params.put("botonID", botonID);
    //
    // // FIXME/TODO: definir cual usar (el primero es para busquedas simples, y
    // la segunda para mostrar descripciones usando subconsultas)
    // // List<BotonesSistemaEntity> listaBotonesSistema =
    // botonessistemaService.selectByMap(params);
    // List<Map<String, Object>> listaBotonesSistema =
    // botonessistemaService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaBotonesSistema);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("RBC-BCA-146", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
    //
    // private BotonesSistemaEntity buscarRegistroPorUnique(String campoUnique)
    // throws Exception {
    //
    // BotonesSistemaEntity filtro = new BotonesSistemaEntity();
    //
    // // FIXME/TODO: poner el set del campo unique que corresponde
    // filtro.setXXX(campoUnique);
    //
    // List<BotonesSistemaEntity> resultados =
    // botonessistemaService.select(filtro);
    //
    // if (CollectionUtils.isNotEmpty(resultados)) {
    // return resultados.get(0);
    // }
    //
    // return null;
    //
    // }
    //
    // public ModelAndView eliminarBotonesSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // DataJsonBean dataJSON = new DataJsonBean();
    //
    // try {
    //
    // // recoger parametros y validar
    // String botonID = JS.toBlank(request.getParameter("botonID"));
    // if (!JS._numeroEntero(botonID)) throw new
    // RestobarException("RBC-ECA-147", "Argumento ilegal del request");
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // BotonesSistemaEntity entity = new BotonesSistemaEntity();
    //
    // entity.setBotonesSistemaID(JS.toLong(botonID));
    //
    // botonessistemaService.delete(entity);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RBC-ELI-148", dataJSON, sos);
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
    // throw new RestobarException("RBC-GED-149", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // BotonesSistemaEntity registro = (BotonesSistemaEntity)
    // result.get("registro");
    //
    // botonessistemaService.update(registro);
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RBC-GUA-150", dataJSON, sos);
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
    // throw new RestobarException("RBC-GNU-151", msgError,
    // JExceptionEnum.VALIDACION, result);
    // }
    //
    // // crear modelo
    // HashMap<String, Object> model = new HashMap<String, Object>();
    //
    // // delegar logica a servicios
    // BotonesSistemaEntity registro = (BotonesSistemaEntity)
    // result.get("registro");
    //
    // botonessistemaService.insert(registro);
    //
    // // poblar modelo
    // model.put("idGenerado", String.valueOf(registro.getBotonesSistemaID()));
    //
    // // limpiar session
    // limpiarSession(request);
    //
    // dataJSON.setRespuesta("ok", null, model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleJSONError("RBC-GNU-152", dataJSON, sos);
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
    // setVistaTemplate(model, "botonessistema-buscar", modulo, plantilla);
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
    // // List<BotonesSistemaEntity> listaBotonesSistema =
    // botonessistemaService.selectByMap(params);
    // List<Map<String, Object>> listaBotonesSistema =
    // botonessistemaService.selectByMapGrilla(params);
    //
    // // poblar modelo
    // model.put("lista", listaBotonesSistema);
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
    // String msgError = handleMsgError("RBC-MBU-001", sos);
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
    // String botonID = JS.toBlank(request.getParameter("botonID"));
    // if (!JS._numeroEntero(botonID)) throw new
    // RestobarException("RBC-MED-153", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "botonessistema-editar", modulo, plantilla);
    //
    // // delegar logica a servicios
    // BotonesSistemaEntity registro =
    // botonessistemaService.selectByID(JS.toLong(botonID));
    // if (registro == null) throw new RestobarException("RBC-MED-154",
    // "BotonesSistema no encontrada");
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
    // String msgError = handleMsgError("RBC-MED-212", sos);
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
    // setVistaTemplate(model, "botonessistema-nuevo", modulo, plantilla);
    //
    // // renderizar vista
    // return handleModelAndView(model);
    //
    // } catch (Exception sos) {
    //
    // // manejo centralizado de errores
    // String msgError = handleMsgError("RBC-MNU-017", sos);
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
    // String botonID = JS.toUpperBlank(request.getParameter("int_botonID"));
    // // FIXME/TODO: recoger los demas campos
    // // si son campos numeros o fechas en la vista se debe generar dos
    // controles HTML INICIO - FIN
    //
    // String msg = StringUtils.EMPTY;
    // Map<String, Object> result = new HashMap<String, Object>();
    //
    // // validacion para botonID
    // if (!JS._vacio(botonID)) {
    //
    // msg = JS._numeroEnteroNoNegativoString(botonID,
    // Constantes.VALIDACION_CAMPO);
    // if (!JS._vacio(msg)) {
    // result.put("campoError", "int_botonID");
    // result.put("msgError", msg);
    //
    // throw new RestobarException("RBC-VBU-155", msg,
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
    // String msgError = handleJSONError("RBC-VAB-156", dataJSON, sos);
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
    // // validacion para botonID
    // // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(botonID,
    // Constantes.VALIDACION_CAMPO); ... return result
    //
    // // validar que no exista otro igual
    // BotonesSistemaEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null && !JS._equiv(found.getBotonesSistemaID(),
    // JS.toLong(botonID))) {
    // result.put("campoError", "general");
    // result.put("msgError", "BotonesSistema ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // BotonesSistemaEntity registro = new BotonesSistemaEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setBotonesSistemaID(JS.toLong(botonID));
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
    // // TODO/FIXME: llenar aqui todos los -> msg = JS._campoNoVacio(botonID,
    // Constantes.VALIDACION_CAMPO); ... return result
    //
    // // validar que no exista otro igual
    // BotonesSistemaEntity found = buscarRegistroPorUnique(nombre);
    // if (found != null) {
    // result.put("campoError", "general");
    // result.put("msgError", "BotonesSistema ya fue registrada");
    //
    // return result;
    // }
    //
    // // armar entity
    // BotonesSistemaEntity registro = new BotonesSistemaEntity();
    //
    // // TODO/FIXME: armar el entity ->
    // registro.setBotonesSistemaID(JS.toLong(botonID));
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
    // public ModelAndView verBotonesSistema(HttpServletRequest request,
    // HttpServletResponse response) throws Exception {
    //
    // Map<String, Object> model = null;
    //
    // try {
    //
    // // recoger parametros y validar
    // String botonID = JS.toBlank(request.getParameter("botonID"));
    // if (!JS._numeroEntero(botonID)) throw new
    // RestobarException("RBC-VCA-157", "Argumento ilegal del request");
    //
    // // crear modelo
    // model = new HashMap<String, Object>();
    //
    // // setear siguiente vista
    // setVistaTemplate(model, "botonessistema-ver", modulo, plantilla);
    //
    // // delegar logica a servicios
    // BotonesSistemaEntity registro =
    // botonessistemaService.selectByID(JS.toLong(botonID));
    // if (registro == null) throw new RestobarException("RBC-VEC-158",
    // "BotonesSistema no encontrada");
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
    // String msgError = handleMsgError("RBC-VCA-123", sos);
    // log.severe(msgError);
    // return handleErrorModelAndView(model, msgError);
    // }
    // }
}
