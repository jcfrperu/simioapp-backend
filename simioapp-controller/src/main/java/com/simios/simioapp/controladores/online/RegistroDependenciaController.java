package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.DependenciaEntity;
import com.simios.simioapp.servicios.DependenciaService;
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

public class RegistroDependenciaController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroDependenciaController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-dependencia";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("dependenciaService")
    private DependenciaService dependenciaService;

    public ModelAndView buscarDependencia(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            String descripcionDependencia = JS.toUpperBlank(request.getParameter("txt_descripcion"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            model = new HashMap<>();

            setVistaPage(model, "dependencia-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(descripcionDependencia)) params.put("descripcion", "%" + descripcionDependencia + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaDependencia = dependenciaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaDependencia);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RDC-BCA-137", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private DependenciaEntity buscarRegistroPorUnique(String campoUnique) throws Exception {

        DependenciaEntity filtro = new DependenciaEntity();

        filtro.setDescripcion(campoUnique);

        List<DependenciaEntity> resultados = dependenciaService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarDependencia(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String dependenciaID = JS.toUpperBlank(request.getParameter("dependenciaID"));
            if (!JS._numeroEntero(dependenciaID)) {
                throw new SimioException("RDC-ECA-138", "Argumento ilegal del request");
            }


            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            DependenciaEntity entity = new DependenciaEntity();

            entity.setDependenciaID(JS.toInt(dependenciaID));

            dependenciaService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-ELI-139", dataJSON, sos);
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
                throw new SimioException("RDC-GED-140", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            DependenciaEntity registro = (DependenciaEntity) result.get("registro");

            dependenciaService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-GUA-141", dataJSON, sos);
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
                throw new SimioException("RDC-GNU-142", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            DependenciaEntity registro = (DependenciaEntity) result.get("registro");

            dependenciaService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getDependenciaID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-GNU-143", dataJSON, sos);
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
            setVistaTemplate(model, "dependencia-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaDependencia = dependenciaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaDependencia);

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
            String dependenciaID = JS.toUpperBlank(request.getParameter("dependenciaID"));
            if (!JS._numeroEntero(dependenciaID))
                throw new SimioException("RDC-MED-144", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "dependencia-editar", modulo, plantilla);

            // delegar logica a servicios
            DependenciaEntity registro = dependenciaService.selectByID(JS.toInt(dependenciaID));
            if (registro == null) throw new SimioException("RDC-MED-145", "Dependenc&iacute;a no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RDC-MED-212", sos);
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
            setVistaTemplate(model, "dependencia-nuevo", modulo, plantilla);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RDC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-VAB-147", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String dependenciaID = JS.toUpperBlank(request.getParameter("int_dependenciaID"));
        String codigoDependencia = JS.toBlank(request.getParameter("txt_codigoDependencia"));
        String descripcionDependencia = JS.toUpperBlank(request.getParameter("txt_descripcion"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar codigo de dependencia
        String msg = JS._campoNoVacio(codigoDependencia, "Codigo Dependencia");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoDependencia");
            result.put("msgError", msg);

            return result;
        }

        // validar descripcion de dependencia
        msg = JS._campoNoVacio(descripcionDependencia, "Descripcion");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcion");
            result.put("msgError", msg);

            return result;
        }

        // validar combo indDel
        if (JS._equiv(indDel, "Estado Registro")) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar que no exista otro igual
        DependenciaEntity found = buscarRegistroPorUnique(descripcionDependencia);
        if (found != null && !JS._equiv(found.getDependenciaID(), JS.toInt(dependenciaID))) {
            result.put("campoError", "general");
            result.put("msgError", "Dependenc&iacute;a ya fue registrada");

            return result;
        }

        // armar entity
        DependenciaEntity registro = new DependenciaEntity();
        registro.setDependenciaID(JS.toInt(dependenciaID));
        registro.setCodigoDependencia(codigoDependencia);
        registro.setDescripcion(descripcionDependencia);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String codigoDependencia = JS.toBlank(request.getParameter("txt_codigoDependencia"));
        String descripcionDependencia = JS.toUpperBlank(request.getParameter("txt_descripcion"));


        Map<String, Object> result = new HashMap<>();

        // validar codigo de dependencia
        String msg = JS._campoNoVacio(codigoDependencia, "Codigo Dependencia");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoDependencia");
            result.put("msgError", msg);

            return result;
        }

        // validar descripcion de dependencia
        msg = JS._campoNoVacio(descripcionDependencia, "Descripcion");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_descripcion");
            result.put("msgError", msg);

            return result;
        }

        // validar que no exista otro igual
        DependenciaEntity found = buscarRegistroPorUnique(descripcionDependencia);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Dependencia ya fue registrada");

            return result;
        }

        // armar entity
        DependenciaEntity registro = new DependenciaEntity();

        registro.setCodigoDependencia(codigoDependencia);
        registro.setDescripcion(descripcionDependencia);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verDependencia(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String dependenciaID = JS.toUpperBlank(request.getParameter("dependenciaID"));
            if (!JS._numeroEntero(dependenciaID))
                throw new SimioException("RDC-VCA-148", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "dependencia-ver", modulo, plantilla);

            // delegar logica a servicios
            DependenciaEntity registro = dependenciaService.selectByID(JS.toInt(dependenciaID));
            if (registro == null) throw new SimioException("RDC-VEC-149", "Dependenc&iacute;a no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RDC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
