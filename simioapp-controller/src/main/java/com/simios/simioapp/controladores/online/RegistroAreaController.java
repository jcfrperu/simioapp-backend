package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.AreaEntity;
import com.simios.simioapp.dominio.entidades.LocalesEntity;
import com.simios.simioapp.servicios.AreaService;
import com.simios.simioapp.servicios.LocalesService;
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

public class RegistroAreaController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroAreaController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-area";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("areaService")
    private AreaService areaService;

    @Autowired
    @Qualifier("localesService")
    private LocalesService localesService;

    public ModelAndView buscarArea(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String abreviaturaArea = JS.toUpperBlank(request.getParameter("txt_abreviaturaArea"));
            String entidadID = JS.toBlank(request.getParameter("int_entidadID"));
            String localesID = JS.toBlank(request.getParameter("int_localesID"));
            String nombreArea = JS.toUpperBlank(request.getParameter("txt_nombreArea"));
            String indDel = JS.toBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "area-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(abreviaturaArea)) params.put("abreviaturaArea", "%" + abreviaturaArea + "%");
            if (JS._numeroEntero(entidadID)) params.put("entidadID", JS.toInt(entidadID));
            if (JS._numeroEntero(localesID)) params.put("localesID", JS.toInt(localesID));
            if (!JS._vacio(nombreArea)) params.put("nombreArea", "%" + nombreArea + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaArea = areaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaArea);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RAC-BCA-085", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private AreaEntity buscarRegistroPorUnique(String abreviaturaArea, int entidadID, int localesID) throws Exception {

        AreaEntity filtro = new AreaEntity();

        filtro.setAbreviaturaArea(abreviaturaArea);
        filtro.setEntidadID(entidadID);
        filtro.setLocalesID(localesID);

        List<AreaEntity> resultados = areaService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;
    }

    public ModelAndView eliminarArea(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String areaID = JS.toBlank(request.getParameter("areaID"));
            if (!JS._numeroEntero(areaID)) throw new SimioException("RAC-ECA-086", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            AreaEntity entity = new AreaEntity();

            entity.setAreaID(JS.toInt(areaID));

            areaService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RAC-ELI-087", dataJSON, sos);
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
                throw new SimioException("RAC-GED-088", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            AreaEntity registro = (AreaEntity) result.get("registro");

            areaService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RAC-GUA-089", dataJSON, sos);
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
                throw new SimioException("RAC-GNU-090", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            AreaEntity registro = (AreaEntity) result.get("registro");

            areaService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getAreaID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RAC-GNU-091", dataJSON, sos);
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
            setVistaTemplate(model, "area-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // cargar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> cboLocal = localesService.listarLocales(filtroLocal);
            cargarComboTodos(request, "cboLocal", cboLocal);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);
            params.put("entidadID", JS.toInt(usuarioSession.getCodigoEntidad()));

            List<Map<String, Object>> listaArea = areaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaArea);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RAC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String areaID = JS.toBlank(request.getParameter("areaID"));
            if (!JS._numeroEntero(areaID)) throw new SimioException("RAC-MED-092", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "area-editar", modulo, plantilla);

            // delegar logica a servicios
            AreaEntity registro = areaService.selectByID(JS.toInt(areaID));
            if (registro == null) throw new SimioException("RAC-MED-093", "Área no encontrada");

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // armar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(registro.getEntidadID());
            List<ListaItem> cboLocal = localesService.listarLocales(filtroLocal);
            cargarComboSeleccione(request, "cboLocal", cboLocal, registro.getLocalesID());

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RAC-MED-212", sos);
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
            setVistaTemplate(model, "area-nuevo", modulo, plantilla);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // armar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> cboLocal = localesService.listarLocales(filtroLocal);
            cargarComboSeleccione(request, "cboLocal", cboLocal);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RAC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String entidadID = JS.toBlank(request.getParameter("int_entidadID"));
            String localesID = JS.toBlank(request.getParameter("int_localesID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para int_entidadID
            if (!JS._vacio(entidadID)) {

                String msg = JS._numeroEnteroNoNegativoString(entidadID, "Entidad");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_entidadID");
                    result.put("msgError", msg);

                    throw new SimioException("RAC-VBU-099", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para int_localesID
            if (!JS._vacio(localesID)) {

                String msg = JS._numeroEnteroNoNegativoString(localesID, "Local");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_localesID");
                    result.put("msgError", msg);

                    throw new SimioException("RAC-VBU-099", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RAC-VAB-095", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String areaID = JS.toBlank(request.getParameter("int_areaID"));
        String abreviaturaArea = JS.toUpperBlank(request.getParameter("txt_abreviaturaArea"));
        String entidadID = JS.toBlank(request.getParameter("int_entidadID"));
        String localesID = JS.toBlank(request.getParameter("int_localesID"));
        String nombreArea = JS.toUpperBlank(request.getParameter("txt_nombreArea"));
        String indDel = JS.toBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validar areaID
        String msg = JS._numeroEnteroNoNegativoString(areaID, "C&oacute;digo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_areaID");
            result.put("msgError", msg);

            return result;
        }

        // validacion abreviaturaArea
        msg = JS._campoMaxSizeNoVacio(abreviaturaArea, 15, "Abreviatura del &Aacute;rea");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_abreviaturaArea");
            result.put("msgError", msg);

            return result;
        }

        // validacion nombreArea
        msg = JS._campoMaxSizeNoVacio(nombreArea, 150, "Nombre de &Aacute;rea");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombreArea");
            result.put("msgError", msg);

            return result;
        }

        // validacion entidadID
        msg = JS._campoNoVacio(entidadID, "Entidad");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_entidadID");
            result.put("msgError", msg);

            return result;
        }

        // validar que entidadID no sea el seleccione
        if (StringUtils.equals(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_entidadID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validacion localesID
        msg = JS._campoNoVacio(localesID, "Local");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_localesID");
            result.put("msgError", msg);

            return result;
        }

        // validar que localesID no sea el seleccione
        if (StringUtils.equals(localesID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_localesID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validar que no exista otro igual
        AreaEntity found = buscarRegistroPorUnique(abreviaturaArea, JS.toInt(entidadID), JS.toInt(localesID));
        if (found != null && !JS._equiv(found.getAreaID(), JS.toInt(areaID))) {
            result.put("campoError", "general");
            result.put("msgError", "Área ya fue registrada");

            return result;
        }

        // armar entity
        AreaEntity registro = new AreaEntity();

        registro.setAreaID(JS.toInt(areaID));
        registro.setAbreviaturaArea(abreviaturaArea);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setLocalesID(JS.toInt(localesID));
        registro.setNombreArea(nombreArea);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String abreviaturaArea = JS.toUpperBlank(request.getParameter("txt_abreviaturaArea"));
        String entidadID = JS.toBlank(request.getParameter("int_entidadID"));
        String localesID = JS.toBlank(request.getParameter("int_localesID"));
        String nombreArea = JS.toUpperBlank(request.getParameter("txt_nombreArea"));

        Map<String, Object> result = new HashMap<>();

        // validacion abreviaturaArea
        String msg = JS._campoMaxSizeNoVacio(abreviaturaArea, 15, "Abreviatura del &Aacute;rea");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_abreviaturaArea");
            result.put("msgError", msg);

            return result;
        }

        // validacion nombreArea
        msg = JS._campoMaxSizeNoVacio(nombreArea, 150, "Nombre");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombreArea");
            result.put("msgError", msg);

            return result;
        }

        // validacion entidadID
        msg = JS._campoNoVacio(entidadID, "Entidad");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_entidadID");
            result.put("msgError", msg);

            return result;
        }

        // validar que entidadID no sea el seleccione
        if (StringUtils.equals(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_entidadID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validacion localesID
        msg = JS._campoNoVacio(localesID, "Local");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_localesID");
            result.put("msgError", msg);

            return result;
        }

        // validar que localesID no sea el seleccione
        if (StringUtils.equals(localesID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_localesID");
            result.put("msgError", "Por favor seleccione");

            return result;
        }

        // validar que no exista otro igual
        AreaEntity found = buscarRegistroPorUnique(abreviaturaArea, JS.toInt(entidadID), JS.toInt(localesID));
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Área ya fue registrada");

            return result;
        }

        // armar entity
        AreaEntity registro = new AreaEntity();

        registro.setAbreviaturaArea(abreviaturaArea);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setLocalesID(JS.toInt(localesID));
        registro.setNombreArea(nombreArea);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verArea(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String areaID = JS.toBlank(request.getParameter("areaID"));
            if (!JS._numeroEntero(areaID)) throw new SimioException("RAC-VCA-096", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "area-ver", modulo, plantilla);

            // delegar logica a servicios
            AreaEntity registro = areaService.selectByID(JS.toInt(areaID));
            if (registro == null) throw new SimioException("RAC-VEC-097", "Área no encontrada");

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarComboSeleccione(request, "cboEntidad", itemsEntidad, registro.getEntidadID());

            // armar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> cboLocal = localesService.listarLocales(filtroLocal);
            cargarComboSeleccione(request, "cboLocal", cboLocal, registro.getLocalesID());

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL).setSelID(registro.getIndDel());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RAC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
