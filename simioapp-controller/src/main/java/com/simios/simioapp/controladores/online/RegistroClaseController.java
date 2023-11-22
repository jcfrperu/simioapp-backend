package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.ClaseEntity;
import com.simios.simioapp.servicios.ClaseService;
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

public class RegistroClaseController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroClaseController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-clase";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    public ModelAndView buscarClase(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String clase = JS.toUpperBlank(request.getParameter("txt_clase"));
            String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "clase-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(clase)) params.put("clase", "%" + clase + "%");
            if (!JS._vacio(descripcion)) params.put("descripcion", "%" + descripcion + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaClase = claseService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaClase);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-BCA-220", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private ClaseEntity buscarRegistroPorUnique(String clase) throws Exception {

        ClaseEntity filtro = new ClaseEntity();

        filtro.setClase(clase);

        List<ClaseEntity> resultados = claseService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarClase(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String claseID = JS.toBlank(request.getParameter("claseID"));
            if (!JS._numeroEntero(claseID)) throw new SimioException("RCC-ECA-221", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            ClaseEntity entity = new ClaseEntity();

            entity.setClaseID(JS.toInt(claseID));

            claseService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-ELI-222", dataJSON, sos);
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
                throw new SimioException("RCC-GED-223", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            ClaseEntity registro = (ClaseEntity) result.get("registro");

            claseService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GUA-224", dataJSON, sos);
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
                throw new SimioException("RCC-GNU-225", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            ClaseEntity registro = (ClaseEntity) result.get("registro");

            claseService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getClaseID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GNU-226", dataJSON, sos);
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
            setVistaTemplate(model, "clase-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaClase = claseService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaClase);

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
            String claseID = JS.toBlank(request.getParameter("claseID"));
            if (!JS._numeroEntero(claseID)) throw new SimioException("RCC-MED-227", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "clase-editar", modulo, plantilla);

            // delegar logica a servicios
            ClaseEntity registro = claseService.selectByID(JS.toInt(claseID));
            if (registro == null) throw new SimioException("RCC-MED-228", "Clase no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

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
            setVistaTemplate(model, "clase-nuevo", modulo, plantilla);

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
            String claseID = JS.toUpperBlank(request.getParameter("int_claseID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para claseID
            if (!JS._vacio(claseID)) {

                String msg = JS._numeroEnteroNoNegativoString(claseID, "Campo");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_claseID");
                    result.put("msgError", msg);

                    throw new SimioException("RCC-VBU-229", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-VAB-230", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String claseID = JS.toBlank(request.getParameter("int_claseID"));
        String clase = JS.toUpperBlank(request.getParameter("txt_clase"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar claseID
        String msg = JS._campoNoVacio(claseID, "claseID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_claseID");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroEnteroNoNegativoString(claseID, "claseID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_claseID");
            result.put("msgError", msg);

            return result;
        }

        // validar clase
        msg = JS._campoMaxSizeNoVacio(clase, 2, "Campo Clase");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_clase");
            result.put("msgError", msg);

            return result;
        }

        // validar descripcion
        msg = JS._campoMaxSizeNoVacio(descripcion, 30, "Campo descripción");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcion");
            result.put("msgError", msg);

            return result;
        }

        // validar indDel
        msg = JS._campoNoVacio(indDel, "indDel");
        if (!JS._vacio(msg)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", msg);

            return result;
        }

        // validar combo indDel
        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione por favor");

            return result;
        }

        // validar que no exista otro igual
        ClaseEntity found = buscarRegistroPorUnique(clase);
        if (found != null && !JS._equiv(found.getClaseID(), JS.toInt(claseID))) {
            result.put("campoError", "general");
            result.put("msgError", "Clase ya fue registrada");

            return result;
        }

        // armar entity
        ClaseEntity registro = new ClaseEntity();

        registro.setClase(clase);
        registro.setClaseID(JS.toInt(claseID));
        registro.setDescripcion(descripcion);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String clase = JS.toUpperBlank(request.getParameter("txt_clase"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));

        Map<String, Object> result = new HashMap<>();

        // validar clase
        String msg = JS._campoMaxSizeNoVacio(clase, 2, "Campo clase");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_clase");
            result.put("msgError", msg);

            return result;
        }

        // validar descripcion
        msg = JS._campoMaxSizeNoVacio(descripcion, 30, "Campo descripción");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcion");
            result.put("msgError", msg);

            return result;
        }

        // validar que no exista otro igual
        ClaseEntity found = buscarRegistroPorUnique(clase);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Clase ya fue registrada");

            return result;
        }

        // armar entity
        ClaseEntity registro = new ClaseEntity();

        registro.setClase(clase);
        registro.setDescripcion(descripcion);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verClase(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String claseID = JS.toBlank(request.getParameter("claseID"));
            if (!JS._numeroEntero(claseID)) throw new SimioException("RCC-VCA-231", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "clase-ver", modulo, plantilla);

            // delegar logica a servicios
            ClaseEntity registro = claseService.selectByID(JS.toInt(claseID));
            if (registro == null) throw new SimioException("RCC-VEC-232", "Clase no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

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
