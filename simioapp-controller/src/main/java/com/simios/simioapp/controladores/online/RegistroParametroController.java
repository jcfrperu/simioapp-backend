package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.ParametroEntity;
import com.simios.simioapp.servicios.ParametroService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RegistroParametroController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroParametroController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-parametro";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("parametroService")
    private ParametroService parametroService;

    public ModelAndView buscarParametro(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String parametroID = JS.toUpperBlank(request.getParameter("int_parametroID"));
            String nombre = JS.toUpperBlank(request.getParameter("txt_nombre"));
            String valor = JS.toBlank(request.getParameter("txt_valor"));
            String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
            String tipoValor = JS.toBlank(request.getParameter("cbo_tipoValor"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "parametro-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(parametroID)) params.put("parametroID", JS.toInt(parametroID));
            if (!JS._vacio(nombre)) params.put("nombre", "%" + nombre + "%");
            if (!JS._vacio(valor)) params.put("valor", "%" + valor + "%");
            if (!JS._vacio(descripcion)) params.put("descripcion", "%" + descripcion + "%");
            if (!JS._vacio(tipoValor)) params.put("tipoValor", tipoValor);
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<ParametroEntity> listaParametro = parametroService.selectByMap(params);

            // poblar modelo
            model.put("lista", listaParametro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RPC-BCA-132", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private ParametroEntity buscarRegistroPorNombre(String nombre) throws Exception {

        ParametroEntity filtro = new ParametroEntity();

        filtro.setNombre(nombre);

        List<ParametroEntity> resultados = parametroService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarParametro(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String parametroID = JS.toUpperBlank(request.getParameter("parametroID"));
            if (!JS._numeroEntero(parametroID)) throw new SimioException("RPC-ECA-133", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            ParametroEntity entity = new ParametroEntity();

            entity.setParametroID(JS.toInt(parametroID));

            parametroService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RPC-ELI-134", dataJSON, sos);
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
                throw new SimioException("RPC-GED-135", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            ParametroEntity registro = (ParametroEntity) result.get("registro");

            parametroService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RPC-GUA-136", dataJSON, sos);
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
                throw new SimioException("RPC-GNU-137", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            ParametroEntity registro = (ParametroEntity) result.get("registro");

            parametroService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getParametroID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RPC-GNU-138", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

        // limpiar los atributos de session
        // setSessionAttribute(request,
        // Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);

    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "parametro-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
            cargarComboCatalogoTodos(request, "cboTipoValor", catalogo.TIPO_VALOR_PARAMETRO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaParametro = parametroService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaParametro);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RPC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String parametroID = JS.toUpperBlank(request.getParameter("parametroID"));
            if (!JS._numeroEntero(parametroID)) throw new SimioException("RPC-MED-139", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "parametro-editar", modulo, plantilla);

            // delegar logica a servicios
            ParametroEntity registro = parametroService.selectByID(JS.toInt(parametroID));
            if (registro == null) throw new SimioException("RPC-MED-140", "Parametro no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipoValor", catalogo.TIPO_VALOR_PARAMETRO, registro.getTipoValor());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RPC-MED-212", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "parametro-nuevo", modulo, plantilla);

            cargarComboCatalogoSeleccione(request, "cboTipoValor", catalogo.TIPO_VALOR_PARAMETRO);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RPC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String parametroID = JS.toUpperBlank(request.getParameter("int_parametroID"));
            String valor = JS.toBlank(request.getParameter("txt_valor"));
            String tipoValor = JS.toBlank(request.getParameter("cbo_tipoValor"));

            Map<String, Object> result = new HashMap<>();

            // validacion para parametroID
            if (!JS._vacio(parametroID)) {

                String msg = JS._numeroEnteroNoNegativoString(parametroID, "Código");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_parametroID");
                    result.put("msgError", msg);

                    throw new SimioException("RPC-VBU-141", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validar valor segun el tipoValor
            if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_CADENA)) {

                if (!JS._vacio(valor)) {

                    String msg = JS._campoNoVacio(valor, "Tipo Valor");
                    if (!JS._vacio(msg)) {

                        result.put("campoError", "txt_valor");
                        result.put("msgError", msg);

                        throw new SimioException("RPC-VBU-230", msg, JExceptionEnum.VALIDACION, result);
                    }
                }

            }

            if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_DECIMAL)) {

                if (!JS._vacio(valor)) {

                    String msg = JS._numeroString(valor, "Tipo Valor");
                    if (!JS._vacio(msg)) {

                        result.put("campoError", "txt_valor");
                        result.put("msgError", msg);

                        throw new SimioException("RPC-VBU-789", msg, JExceptionEnum.VALIDACION, result);
                    }
                }

            }

            if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_ENTERO)) {

                if (!JS._vacio(valor)) {

                    String msg = JS._numeroEnteroString(valor, "Tipo Valor");
                    if (!JS._vacio(msg)) {

                        result.put("campoError", "txt_valor");
                        result.put("msgError", msg);

                        throw new SimioException("RPC-VBU-998", msg, JExceptionEnum.VALIDACION, result);
                    }
                }

            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RPC-VAB-142", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String parametroID = JS.toUpperBlank(request.getParameter("int_parametroID"));
        String nombre = JS.toUpperBlank(request.getParameter("txt_nombre"));
        String valor = JS.toBlank(request.getParameter("txt_valor"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        String tipoValor = JS.toBlank(request.getParameter("cbo_tipoValor"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar parametroID
        String msg = JS._numeroEnteroNoNegativoString(parametroID, "Código");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_parametroID");
            result.put("msgError", msg);

            return result;
        }

        // validar nombre
        msg = JS._campoNoVacio(nombre, "Nombre");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombre");
            result.put("msgError", msg);

            return result;
        }

        // validar combo tipoValor
        if (JS._equiv(tipoValor, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_tipoValor");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar valor
        msg = JS._campoNoVacio(valor, "Valor");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_valor");
            result.put("msgError", msg);

            return result;
        }

        // validar valor segun el tipoValor
        if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_CADENA)) {
            msg = JS._campoNoVacio(valor, "Tipo Valor");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_valor");
                result.put("msgError", msg);

                throw new SimioException("RPC-VBU-230", msg, JExceptionEnum.VALIDACION, result);
            }
        }

        if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_DECIMAL)) {
            msg = JS._numeroString(valor, "Tipo Valor");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_valor");
                result.put("msgError", msg);

                throw new SimioException("RPC-VBU-789", msg, JExceptionEnum.VALIDACION, result);
            }
        }

        if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_ENTERO)) {

            msg = JS._numeroEnteroString(valor, "Tipo Valor");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_valor");
                result.put("msgError", msg);

                throw new SimioException("RPC-VBU-998", msg, JExceptionEnum.VALIDACION, result);
            }
        }

        // validar combo indDel
        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar que no exista otro igual
        ParametroEntity found = buscarRegistroPorNombre(nombre);
        if (found != null && !JS._equiv(found.getParametroID(), JS.toInt(parametroID))) {
            result.put("campoError", "general");
            result.put("msgError", "Parametro ya fue registrada");

            return result;
        }

        // armar entity
        ParametroEntity registro = new ParametroEntity();

        registro.setParametroID(JS.toInt(parametroID));
        registro.setNombre(nombre);
        registro.setTipoValor(tipoValor);
        registro.setValor(valor);
        registro.setDescripcion(descripcion);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String nombre = JS.toUpperBlank(request.getParameter("txt_nombre"));
        String valor = JS.toBlank(request.getParameter("txt_valor"));
        String tipoValor = JS.toBlank(request.getParameter("cbo_tipoValor"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));

        Map<String, Object> result = new HashMap<>();

        // validar nombre
        String msg = JS._campoNoVacio(nombre, "Nombre");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombre");
            result.put("msgError", msg);

            return result;
        }

        // validar combo tipoValor
        if (JS._equiv(tipoValor, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_tipoValor");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar valor
        msg = JS._campoNoVacio(valor, "Valor");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_valor");
            result.put("msgError", msg);

            return result;
        }

        // validar valor segun el tipoValor
        if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_CADENA)) {
            msg = JS._campoNoVacio(valor, "Tipo Valor");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_valor");
                result.put("msgError", msg);

                throw new SimioException("RPC-VBU-230", msg, JExceptionEnum.VALIDACION, result);
            }
        }

        if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_DECIMAL)) {
            msg = JS._numeroString(valor, "Tipo Valor");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_valor");
                result.put("msgError", msg);

                throw new SimioException("RPC-VBU-789", msg, JExceptionEnum.VALIDACION, result);
            }
        }

        if (tipoValor.equals(catalogo.TIPO_VALOR_PARAMETRO_ENTERO)) {

            msg = JS._numeroEnteroString(valor, "Tipo Valor");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_valor");
                result.put("msgError", msg);

                throw new SimioException("RPC-VBU-998", msg, JExceptionEnum.VALIDACION, result);
            }
        }

        // validar que no exista otro igual
        ParametroEntity found = buscarRegistroPorNombre(nombre);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Parametro ya fue registrada");

            return result;
        }

        // armar entity
        ParametroEntity registro = new ParametroEntity();

        registro.setNombre(nombre);
        registro.setTipoValor(tipoValor);
        registro.setValor(valor);
        registro.setDescripcion(descripcion);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verParametro(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String parametroID = JS.toUpperBlank(request.getParameter("parametroID"));
            if (!JS._numeroEntero(parametroID)) throw new SimioException("RPC-VCA-143", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "parametro-ver", modulo, plantilla);

            // delegar logica a servicios
            ParametroEntity registro = parametroService.selectByID(JS.toInt(parametroID));
            if (registro == null) throw new SimioException("RPC-VEC-144", "Parametro no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipoValor", catalogo.TIPO_VALOR_PARAMETRO, registro.getTipoValor());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RPC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
