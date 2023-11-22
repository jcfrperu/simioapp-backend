package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.GrupoEntity;
import com.simios.simioapp.servicios.GrupoService;
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

public class RegistroGrupoController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroGrupoController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-grupo";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("grupoService")
    private GrupoService grupoService;

    public ModelAndView buscarGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String grupo = JS.toUpperBlank(request.getParameter("txt_grupo"));
            String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "grupo-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(grupo)) params.put("grupo", "%" + grupo + "%");
            if (!JS._vacio(descripcion)) params.put("descripcion", "%" + descripcion + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaGrupo = grupoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaGrupo);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RGC-BCA-220", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private GrupoEntity buscarRegistroPorUnique(String grupo) throws Exception {

        GrupoEntity filtro = new GrupoEntity();

        filtro.setGrupo(grupo);

        List<GrupoEntity> resultados = grupoService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String grupoID = JS.toBlank(request.getParameter("grupoID"));
            if (!JS._numeroEntero(grupoID)) throw new SimioException("RGC-ECA-221", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            GrupoEntity entity = new GrupoEntity();

            entity.setGrupoID(JS.toInt(grupoID));

            grupoService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-ELI-222", dataJSON, sos);
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
                throw new SimioException("RGC-GED-223", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            GrupoEntity registro = (GrupoEntity) result.get("registro");

            grupoService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-GUA-224", dataJSON, sos);
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
                throw new SimioException("RGC-GNU-225", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            GrupoEntity registro = (GrupoEntity) result.get("registro");

            grupoService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getGrupoID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-GNU-226", dataJSON, sos);
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
            setVistaTemplate(model, "grupo-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaGrupo = grupoService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaGrupo);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RGC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String grupoID = JS.toBlank(request.getParameter("grupoID"));
            if (!JS._numeroEntero(grupoID)) throw new SimioException("RGC-MED-227", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "grupo-editar", modulo, plantilla);

            // delegar logica a servicios
            GrupoEntity registro = grupoService.selectByID(JS.toInt(grupoID));
            if (registro == null) throw new SimioException("RGC-MED-228", "Grupo no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RGC-MED-212", sos);
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
            setVistaTemplate(model, "grupo-nuevo", modulo, plantilla);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RGC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String grupoID = JS.toUpperBlank(request.getParameter("int_grupoID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para grupoID
            if (!JS._vacio(grupoID)) {

                String msg = JS._numeroEnteroNoNegativoString(grupoID, "Campo");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_grupoID");
                    result.put("msgError", msg);

                    throw new SimioException("RGC-VBU-229", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-VAB-230", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
        String grupo = JS.toUpperBlank(request.getParameter("txt_grupo"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar grupoID
        String msg = JS._campoNoVacio(grupoID, "grupoID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_grupoID");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroEnteroNoNegativoString(grupoID, "grupoID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_grupoID");
            result.put("msgError", msg);

            return result;
        }

        // validar grupo
        msg = JS._campoMaxSizeNoVacio(grupo, 2, "Campo Grupo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_grupo");
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
        GrupoEntity found = buscarRegistroPorUnique(grupo);
        if (found != null && !JS._equiv(found.getGrupoID(), JS.toInt(grupoID))) {
            result.put("campoError", "general");
            result.put("msgError", "Grupo ya fue registrado");

            return result;
        }

        // armar entity
        GrupoEntity registro = new GrupoEntity();

        registro.setGrupo(grupo);
        registro.setGrupoID(JS.toInt(grupoID));
        registro.setDescripcion(descripcion);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String grupo = JS.toUpperBlank(request.getParameter("txt_grupo"));
        String descripcion = JS.toUpperBlank(request.getParameter("txt_descripcion"));

        Map<String, Object> result = new HashMap<>();

        // validar grupo
        String msg = JS._campoMaxSizeNoVacio(grupo, 2, "Campo Grupo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_grupo");
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
        GrupoEntity found = buscarRegistroPorUnique(grupo);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Grupo ya fue registrado");

            return result;
        }

        // armar entity
        GrupoEntity registro = new GrupoEntity();

        registro.setGrupo(grupo);
        registro.setDescripcion(descripcion);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verGrupo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String grupoID = JS.toBlank(request.getParameter("grupoID"));
            if (!JS._numeroEntero(grupoID)) throw new SimioException("RGC-VCA-231", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "grupo-ver", modulo, plantilla);

            // delegar logica a servicios
            GrupoEntity registro = grupoService.selectByID(JS.toInt(grupoID));
            if (registro == null) throw new SimioException("RGC-VEC-232", "Grupo no encontrado");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RGC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
