package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RegistroEntidadController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroCatalogoController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-entidad";

    public static final String DEFAULT_DEPENDENCIA_ID = "1";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    public ModelAndView buscarEntidad(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String codigoEntidad = JS.toUpperBlank(request.getParameter("txt_codigoEntidad"));
            String entidad = JS.toUpperBlank(request.getParameter("txt_entidad"));
            String rucEntidad = JS.toUpperBlank(request.getParameter("txt_rucEntidad"));
            String indel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "entidad-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(codigoEntidad)) params.put("codigoEntidad", "%" + codigoEntidad + "%");
            if (!JS._vacio(entidad)) params.put("entidad", "%" + entidad + "%");
            if (!JS._vacio(rucEntidad)) params.put("rucEntidad", "%" + rucEntidad + "%");
            if (!JS._vacio(indel)) params.put("indDel", indel);

            List<Map<String, Object>> listaEntidad = entidadService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaEntidad);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-BCA-163", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private EntidadEntity buscarRegistroPorUnique(String codigoEntidad) throws Exception {

        EntidadEntity filtro = new EntidadEntity();

        filtro.setCodigoEntidad(codigoEntidad);

        List<EntidadEntity> resultados = entidadService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarEntidad(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String entidadID = JS.toBlank(request.getParameter("entidadID"));
            if (!JS._numeroEntero(entidadID)) throw new SimioException("REC-ECA-164", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EntidadEntity entity = new EntidadEntity();

            entity.setEntidadID(JS.toInt(entidadID));

            entidadService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-ELI-165", dataJSON, sos);
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
                throw new SimioException("REC-GED-166", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EntidadEntity registro = (EntidadEntity) result.get("registro");

            entidadService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-GUA-167", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView guardarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            // int_id_dependencia
            Map<String, Object> result = validarGuardar(request);
            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("REC-GNU-168", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            EntidadEntity registro = (EntidadEntity) result.get("registro");

            entidadService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getEntidadID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-GNU-169", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

        // TODO/FIXME
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
            setVistaTemplate(model, "entidad-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            // poblar modelo
            model.put("lista", null);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MBU-503", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String entidadID = JS.toBlank(request.getParameter("entidadID"));
            if (!JS._numeroEntero(entidadID)) throw new SimioException("REC-MED-170", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "entidad-editar", modulo, plantilla);

            // delegar logica a servicios
            EntidadEntity registro = entidadService.selectByID(JS.toInt(entidadID));
            if (registro == null) throw new SimioException("REC-MED-171", "Entidad no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MED-212", sos);
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
            setVistaTemplate(model, "entidad-nuevo", modulo, plantilla);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String entidadID = JS.toUpperBlank(request.getParameter("int_entidadID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para entidadID
            if (!JS._vacio(entidadID)) {

                String msg = JS._numeroEnteroNoNegativoString(entidadID, "Codigo de Entidad");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_entidadID");
                    result.put("msgError", msg);

                    throw new SimioException("REC-VBU-172", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("REC-VAB-173", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        Map<String, Object> result = new HashMap<>();

        String codigoEntidad = JS.toUpperBlank(request.getParameter("txt_codigoEntidad"));
        String entidad = JS.toUpperBlank(request.getParameter("txt_entidad"));
        String rucEntidad = JS.toUpperBlank(request.getParameter("txt_rucEntidad"));
        String indel = JS.toUpperBlank(request.getParameter("cbo_indDel"));


        if (JS._vacio(codigoEntidad)) {
            result.put("campoError", "txt_codigoEntidad");
            result.put("msgError", "Codigo de Entidad no puede ser vacío");
            return result;
        }

        if (JS._vacio(entidad)) {
            result.put("campoError", "txt_entidad");
            result.put("msgError", "Entidad no puede ser vacío");
            return result;
        }

        if (StringUtils.equals(indel, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Por favor seleccione");
            return result;
        }

        String entidadID = request.getParameter("int_entidadID");
        EntidadEntity found = buscarRegistroPorUnique(codigoEntidad.trim());
        if (found != null && !JS._equiv(found.getEntidadID(), JS.toInt(entidadID))) {
            result.put("campoError", "general");
            result.put("msgError", "Entidad ya fue registrada");

            return result;
        }

        // armar entity
        EntidadEntity registro = new EntidadEntity();

        registro.setIndDel(indel);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setCodigoEntidad(codigoEntidad);
        registro.setEntidad(entidad);
        registro.setDependenciaID(Integer.parseInt(DEFAULT_DEPENDENCIA_ID));
        registro.setRucEntidad(rucEntidad);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        Map<String, Object> result = new HashMap<>();

        String codigoEntidad = JS.toUpperBlank(request.getParameter("txt_codigoEntidad"));
        String entidad = JS.toUpperBlank(request.getParameter("txt_entidad"));
        String rucEntidad = JS.toUpperBlank(request.getParameter("txt_rucEntidad"));

        if (JS._vacio(codigoEntidad)) {
            result.put("campoError", "txt_codigoEntidad");
            result.put("msgError", "Codigo de Entidad vacío");
            return result;
        }

        if (JS._vacio(entidad)) {
            result.put("campoError", "txt_entidad");
            result.put("msgError", "Entidad no puede ser vacío");
            return result;
        }

        if (JS._vacio(rucEntidad)) {
            result.put("campoError", "txt_rucEntidad");
            result.put("msgError", "RUC no puede ser vacío");
            return result;
        }

        EntidadEntity found = buscarRegistroPorUnique(codigoEntidad);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Entidad ya fue registrada");

            return result;
        }

        // armar entity
        EntidadEntity registro = new EntidadEntity();

        registro.setIndDel(Constantes.REGISTRO_ACTIVO);
        registro.setCodigoEntidad(codigoEntidad);
        registro.setDependenciaID(Integer.parseInt(DEFAULT_DEPENDENCIA_ID));
        registro.setEntidad(entidad);
        registro.setRucEntidad(rucEntidad);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verEntidad(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String entidadID = JS.toBlank(request.getParameter("entidadID"));
            if (!JS._numeroEntero(entidadID)) throw new SimioException("REC-VCA-174", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "entidad-ver", modulo, plantilla);

            // delegar logica a servicios
            EntidadEntity registro = entidadService.selectByID(JS.toInt(entidadID));
            if (registro == null) throw new SimioException("REC-VEC-175", "Entidad no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("REC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
