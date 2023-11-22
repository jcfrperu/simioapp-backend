package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.CatalogoEntity;
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

public class RegistroCatalogoController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroCatalogoController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-catalogo";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    public ModelAndView buscarCatalogo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String catalogo = JS.toUpperBlank(request.getParameter("txt_catalogo"));
            String datacatalogo = JS.toBlank(request.getParameter("txt_datacatalogo"));
            String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
            String descripcionCorta = JS.toUpperBlank(request.getParameter("txt_descripcionCorta"));
            String tipo = JS.toUpperBlank(request.getParameter("cbo_tipo"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "catalogo-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(catalogo)) params.put("catalogo", "%" + catalogo + "%");
            if (!JS._vacio(datacatalogo)) params.put("datacatalogo", "%" + datacatalogo + "%");
            if (!JS._vacio(descripcion)) params.put("descripcion", "%" + descripcion + "%");
            if (!JS._vacio(descripcionCorta)) params.put("descripcionCorta", "%" + descripcionCorta + "%");
            if (!JS._vacio(tipo)) params.put("tipo", tipo + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaCatalogo = catalogoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaCatalogo);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-BCA-145", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private CatalogoEntity buscarRegistroPorUnique(String catalogo, String datacatalogo) throws Exception {

        CatalogoEntity filtro = new CatalogoEntity();

        filtro.setCatalogo(catalogo);
        filtro.setDatacatalogo(datacatalogo);

        List<CatalogoEntity> resultados = catalogoService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarCatalogo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String catalogoID = JS.toBlank(request.getParameter("catalogoID"));
            if (!JS._numeroEntero(catalogoID)) throw new SimioException("RCC-ECA-146", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CatalogoEntity entity = new CatalogoEntity();

            entity.setCatalogoID(JS.toInt(catalogoID));

            catalogoService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-ELI-147", dataJSON, sos);
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
                throw new SimioException("RCC-GED-148", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CatalogoEntity registro = (CatalogoEntity) result.get("registro");

            catalogoService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GUA-149", dataJSON, sos);
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
                throw new SimioException("RCC-GNU-150", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CatalogoEntity registro = (CatalogoEntity) result.get("registro");

            catalogoService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getCatalogoID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GNU-151", dataJSON, sos);
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
            setVistaTemplate(model, "catalogo-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
            cargarComboCatalogoTodos(request, "cboTipos", catalogo.TIPO_CATALOGO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaCatalogo = catalogoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaCatalogo);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String catalogoID = JS.toBlank(request.getParameter("catalogoID"));
            if (!JS._numeroEntero(catalogoID)) throw new SimioException("RCC-MED-152", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "catalogo-editar", modulo, plantilla);

            // delegar logica a servicios
            CatalogoEntity registro = catalogoService.selectByID(JS.toInt(catalogoID));
            if (registro == null) throw new SimioException("RCC-MED-153", "Catálogo no encontrado");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
            cargarComboCatalogoSeleccione(request, "cboTipos", catalogo.TIPO_CATALOGO, registro.getTipo());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-MED-212", sos);
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
            setVistaTemplate(model, "catalogo-nuevo", modulo, plantilla);

            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);
            cargarComboCatalogoSeleccione(request, "cboTipos", catalogo.TIPO_CATALOGO);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String catalogoID = JS.toUpperBlank(request.getParameter("int_catalogoID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para catalogoID
            if (!JS._vacio(catalogoID)) {

                String msg = JS._numeroEnteroNoNegativoString(catalogoID, "Código");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_catalogoID");
                    result.put("msgError", msg);

                    throw new SimioException("RCC-VBU-154", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-VAB-155", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String catalogoID = JS.toUpperBlank(request.getParameter("int_catalogoID"));
        String catalogo = JS.toUpperBlank(request.getParameter("txt_catalogo"));
        String datacatalogo = JS.toBlank(request.getParameter("txt_datacatalogo"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        String descripcionCorta = JS.toUpperBlank(request.getParameter("txt_descripcionCorta"));
        String tipo = JS.toUpperBlank(request.getParameter("cbo_tipo"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        String msg;
        Map<String, Object> result = new HashMap<>();

        // validar catalogoID
        msg = JS._campoNoVacio(catalogoID, "Código");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_catalogoID");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroEnteroNoNegativoString(catalogoID, "Código");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_catalogoID");
            result.put("msgError", msg);

            return result;
        }

        // validar combo tipo
        if (JS._equiv(tipo, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_tipo");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar catalogo

        msg = JS._campoNoVacio(catalogo, "Catálogo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_catalogo");
            result.put("msgError", msg);

            return result;
        }

        // validar datacatalogo
        if (JS._equiv(tipo, Constantes.CATALOGO_TIPO_DETALLE)) {
            msg = JS._campoNoVacio(datacatalogo, "Valor Catálogo");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_datacatalogo");
                result.put("msgError", msg);

                return result;
            }

        }

        // validar descripcion
        msg = JS._campoNoVacio(descripcion, "Descripción");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcion");
            result.put("msgError", msg);

            return result;
        }

        // validar descripcionCorta
        msg = JS._campoNoVacio(descripcionCorta, "Descripción Corta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcionCorta");
            result.put("msgError", msg);

            return result;
        }

        // validar combo indDel
        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar que no exista otro igual
        CatalogoEntity found = buscarRegistroPorUnique(catalogo, datacatalogo);
        if (found != null && !JS._equiv(found.getCatalogoID(), JS.toInt(catalogoID))) {
            result.put("campoError", "general");
            result.put("msgError", "Catálogo ya fue registrado");

            return result;
        }

        // armar entity
        CatalogoEntity registro = new CatalogoEntity();

        registro.setCatalogoID(JS.toInt(catalogoID));
        registro.setCatalogo(catalogo);
        registro.setDatacatalogo(datacatalogo);
        registro.setDescripcion(descripcion);
        registro.setDescripcionCorta(descripcionCorta);
        registro.setTipo(tipo);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String catalogo = JS.toUpperBlank(request.getParameter("txt_catalogo"));
        String datacatalogo = JS.toBlank(request.getParameter("txt_datacatalogo"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        String descripcionCorta = JS.toUpperBlank(request.getParameter("txt_descripcionCorta"));
        String tipo = JS.toUpperBlank(request.getParameter("cbo_tipo"));

        Map<String, Object> result = new HashMap<>();

        // validar combo tipo
        if (JS._equiv(tipo, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_tipo");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar catalogo
        String msg = JS._campoNoVacio(catalogo, "Catálogo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_catalogo");
            result.put("msgError", msg);

            return result;
        }

        // validar datacatalogo
        if (JS._equiv(tipo, Constantes.CATALOGO_TIPO_DETALLE)) {
            msg = JS._campoNoVacio(datacatalogo, "Valor Catálogo");
            if (!JS._vacio(msg)) {

                result.put("campoError", "txt_datacatalogo");
                result.put("msgError", msg);

                return result;
            }
        }

        // validar descripcion
        msg = JS._campoNoVacio(descripcion, "Descripción");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcion");
            result.put("msgError", msg);

            return result;
        }

        // validar descripcionCorta
        msg = JS._campoNoVacio(descripcionCorta, "Descripción Corta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcionCorta");
            result.put("msgError", msg);

            return result;
        }

        // validar que no exista otro igual
        CatalogoEntity found = buscarRegistroPorUnique(catalogo, datacatalogo);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Catalogo ya fue registrado");

            return result;
        }

        // armar entity
        CatalogoEntity registro = new CatalogoEntity();

        registro.setCatalogo(catalogo);
        registro.setDatacatalogo(datacatalogo);
        registro.setDescripcion(descripcion);
        registro.setDescripcionCorta(descripcionCorta);
        registro.setTipo(tipo);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verCatalogo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String catalogoID = JS.toBlank(request.getParameter("catalogoID"));
            if (!JS._numeroEntero(catalogoID)) throw new SimioException("RCC-VCA-156", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "catalogo-ver", modulo, plantilla);

            // delegar logica a servicios
            CatalogoEntity registro = catalogoService.selectByID(JS.toInt(catalogoID));
            if (registro == null) throw new SimioException("RCC-VEC-157", "Catalogo no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());
            cargarComboCatalogoTodos(request, "cboTipos", catalogo.TIPO_CATALOGO, registro.getTipo());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
