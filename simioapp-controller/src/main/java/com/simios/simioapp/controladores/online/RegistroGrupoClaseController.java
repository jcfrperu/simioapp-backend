package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.ClaseEntity;
import com.simios.simioapp.dominio.entidades.GrupoClaseEntity;
import com.simios.simioapp.dominio.entidades.GrupoEntity;
import com.simios.simioapp.servicios.ClaseService;
import com.simios.simioapp.servicios.GrupoClaseService;
import com.simios.simioapp.servicios.GrupoService;
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

public class RegistroGrupoClaseController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroGrupoClaseController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-grupoclase";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("grupoClaseService")
    private GrupoClaseService grupoClaseService;

    @Autowired
    @Qualifier("grupoService")
    private GrupoService grupoService;

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    public ModelAndView buscarGrupoClase(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
            String claseID = JS.toBlank(request.getParameter("int_claseID"));
            String cuenta = JS.toBlank(request.getParameter("txt_cuenta"));
            String indDel = JS.toBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "grupoclase-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(grupoID)) params.put("grupoID", JS.toInt(grupoID));
            if (JS._numeroEntero(claseID)) params.put("claseID", JS.toInt(claseID));
            if (!JS._vacio(cuenta)) params.put("cuenta", "%" + cuenta + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaGrupoClase = grupoClaseService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaGrupoClase);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RGC-BCA-311", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private GrupoClaseEntity buscarRegistroPorUnique(Integer grupoID, Integer claseID) throws Exception {

        GrupoClaseEntity filtro = new GrupoClaseEntity();

        filtro.setGrupoID(grupoID);
        filtro.setClaseID(claseID);

        List<GrupoClaseEntity> resultados = grupoClaseService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarGrupoClase(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String grupoID = JS.toBlank(request.getParameter("grupoID"));
            String claseID = JS.toBlank(request.getParameter("claseID"));

            if (!JS._numeroEntero(grupoID) || !JS._numeroEntero(grupoID))
                throw new SimioException("RGC-ECA-312", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            GrupoClaseEntity entity = new GrupoClaseEntity();

            entity.setGrupoID(JS.toInt(grupoID));
            entity.setClaseID(JS.toInt(claseID));

            grupoClaseService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-ELI-313", dataJSON, sos);
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
                throw new SimioException("RGC-GED-314", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            GrupoClaseEntity registro = (GrupoClaseEntity) result.get("registro");

            grupoClaseService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-GUA-315", dataJSON, sos);
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
                throw new SimioException("RGC-GNU-316", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            GrupoClaseEntity registro = (GrupoClaseEntity) result.get("registro");

            grupoClaseService.insert(registro);

            // poblar modelo
            model.put("idGenerado", registro.getGrupoID() + "," + registro.getClaseID());

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-GNU-317", dataJSON, sos);
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
            setVistaTemplate(model, "grupoclase-buscar", modulo, plantilla);

            // armar combo grupo
            GrupoEntity filtroGrupo = new GrupoEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboGrupo = grupoService.selectGrupoCombo(filtroGrupo);
            cargarComboTodos(request, "cboGrupo", cboGrupo);

            // armar combo clase
            ClaseEntity filtroClase = new ClaseEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboClase = claseService.selectClaseCombo(filtroClase);
            cargarComboTodos(request, "cboClase", cboClase);

            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaGrupoClase = grupoClaseService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaGrupoClase);

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
            String claseID = JS.toBlank(request.getParameter("claseID"));

            if (!JS._numeroEntero(grupoID) || !JS._numeroEntero(claseID))
                throw new SimioException("RGC-MED-318", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "grupoclase-editar", modulo, plantilla);

            // delegar logica a servicios
            GrupoClaseEntity registro = grupoClaseService.selectByID(JS.toInt(grupoID), JS.toInt(claseID));
            if (registro == null) throw new SimioException("RGC-MED-319", "GrupoClase no encontrada");

            // armar combo grupo
            GrupoEntity filtroGrupo = new GrupoEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboGrupo = grupoService.selectGrupoCombo(filtroGrupo);
            cargarComboSeleccione(request, "cboGrupo", cboGrupo, registro.getGrupoID());

            // armar combo clase
            ClaseEntity filtroClase = new ClaseEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboClase = claseService.selectClaseCombo(filtroClase);
            cargarComboSeleccione(request, "cboClase", cboClase, registro.getClaseID());

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
            setVistaTemplate(model, "grupoclase-nuevo", modulo, plantilla);

            // armar combo grupo
            GrupoEntity filtroGrupo = new GrupoEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboGrupo = grupoService.selectGrupoCombo(filtroGrupo);
            cargarComboSeleccione(request, "cboGrupo", cboGrupo);

            // armar combo clase
            ClaseEntity filtroClase = new ClaseEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboClase = claseService.selectClaseCombo(filtroClase);
            cargarComboSeleccione(request, "cboClase", cboClase);

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
            String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
            String claseID = JS.toBlank(request.getParameter("int_claseID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para grupoID
            if (!JS._vacio(grupoID)) {

                String msg = JS._numeroEnteroNoNegativoString(grupoID, "Grupo");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_grupoID");
                    result.put("msgError", msg);

                    throw new SimioException("RGC-VBU-320", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para claseID
            if (!JS._vacio(claseID)) {

                String msg = JS._numeroEnteroNoNegativoString(claseID, "Clase");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_claseID");
                    result.put("msgError", msg);

                    throw new SimioException("RGC-VBU-321", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RGC-VAB-321", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
        String claseID = JS.toBlank(request.getParameter("int_claseID"));
        String cuenta = JS.toBlank(request.getParameter("txt_cuenta"));
        String indDel = JS.toBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar grupoID
        String  msg = JS._campoNoVacio(grupoID, "Grupo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_grupoID");
            result.put("msgError", msg);

            return result;
        }

        // validar que grupoID no sea el seleccione
        if (StringUtils.equals(grupoID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_grupoID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validar claseID
        msg = JS._campoNoVacio(claseID, "Clase");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_claseID");
            result.put("msgError", msg);

            return result;
        }

        // validar que claseID no sea el seleccione
        if (StringUtils.equals(claseID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_claseID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validar cuenta
        msg = JS._campoNoVacio(cuenta, "Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_cuenta");
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
        GrupoClaseEntity found = buscarRegistroPorUnique(JS.toInt(grupoID), JS.toInt(claseID));
        if (found != null && !JS._equiv(found.getGrupoID(), JS.toInt(grupoID)) && !JS._equiv(found.getClaseID(), JS.toInt(claseID))) {
            result.put("campoError", "general");
            result.put("msgError", "Grupo/Clase ya fue registrada");

            return result;
        }

        // armar entity
        GrupoClaseEntity registro = new GrupoClaseEntity();

        registro.setGrupoID(JS.toInt(grupoID));
        registro.setClaseID(JS.toInt(claseID));
        registro.setCuenta(cuenta);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
        String claseID = JS.toBlank(request.getParameter("int_claseID"));
        String cuenta = JS.toBlank(request.getParameter("txt_cuenta"));
        String indDel = JS.toBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar grupoID
        String msg = JS._campoNoVacio(grupoID, "Grupo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_grupoID");
            result.put("msgError", msg);

            return result;
        }

        // validar que grupoID no sea el seleccione
        if (StringUtils.equals(grupoID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_grupoID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validar claseID
        msg = JS._campoNoVacio(claseID, "Clase");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_claseID");
            result.put("msgError", msg);

            return result;
        }

        // validar que claseID no sea el seleccione
        if (StringUtils.equals(claseID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_claseID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validar cuenta
        msg = JS._campoNoVacio(cuenta, "Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_cuenta");
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
        GrupoClaseEntity found = buscarRegistroPorUnique(JS.toInt(grupoID), JS.toInt(claseID));
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Grupo/Clase ya fue registrada");

            return result;
        }

        // armar entity
        GrupoClaseEntity registro = new GrupoClaseEntity();

        registro.setGrupoID(JS.toInt(grupoID));
        registro.setClaseID(JS.toInt(claseID));
        registro.setCuenta(cuenta);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verGrupoClase(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String grupoID = JS.toBlank(request.getParameter("grupoID"));
            String claseID = JS.toBlank(request.getParameter("claseID"));

            if (!JS._numeroEntero(grupoID) || !JS._numeroEntero(claseID))
                throw new SimioException("RGC-VCA-322", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "grupoclase-ver", modulo, plantilla);

            // delegar logica a servicios
            GrupoClaseEntity registro = grupoClaseService.selectByID(JS.toInt(grupoID), JS.toInt(claseID));
            if (registro == null) throw new SimioException("RGC-VEC-323", "Grupo/Clase no encontrada");

            // armar combo grupo
            GrupoEntity filtroGrupo = new GrupoEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboGrupo = grupoService.selectGrupoCombo(filtroGrupo);
            cargarComboSeleccione(request, "cboGrupo", cboGrupo, registro.getGrupoID());

            // armar combo clase
            ClaseEntity filtroClase = new ClaseEntity();
            filtroGrupo.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> cboClase = claseService.selectClaseCombo(filtroClase);
            cargarComboSeleccione(request, "cboClase", cboClase, registro.getClaseID());

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
