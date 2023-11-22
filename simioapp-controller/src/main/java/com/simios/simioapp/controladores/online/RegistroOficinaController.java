package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.AreaEntity;
import com.simios.simioapp.dominio.entidades.LocalesEntity;
import com.simios.simioapp.dominio.entidades.OficinaEntity;
import com.simios.simioapp.servicios.AreaService;
import com.simios.simioapp.servicios.LocalesService;
import com.simios.simioapp.servicios.OficinaService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RegistroOficinaController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroOficinaController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-oficina";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("oficinaService")
    private OficinaService oficinaService;

    @Autowired
    @Qualifier("areaService")
    private AreaService areaService;

    @Autowired
    @Qualifier("localesService")
    private LocalesService localesService;

    public ModelAndView buscarOficina(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String abreviaturaOficina = JS.toUpperBlank(request.getParameter("txt_abreviaturaOficina"));
            String localesID = JS.toUpperBlank(request.getParameter("cbo_local"));
            String areaID = JS.toUpperBlank(request.getParameter("cbo_area"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));
            String pisoOficina = JS.toUpperBlank(request.getParameter("txt_pisoOficina"));
            String entidadID = JS.toBlank(request.getParameter("cboEntidad"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "oficina-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(pisoOficina)) params.put("pisoOficina", pisoOficina);
            if (JS._numeroEntero(entidadID)) params.put("entidadID", JS.toInt(entidadID));
            if (!JS._vacio(abreviaturaOficina)) params.put("abreviaturaOficina", "%" + abreviaturaOficina + "%");
            if (JS._numeroEntero(localesID)) params.put("localesID", JS.toInt(localesID));
            if (JS._numeroEntero(areaID)) params.put("areaID", JS.toInt(areaID));
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaOficina = oficinaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaOficina);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("ROC-BCA-189", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private OficinaEntity buscarRegistroPorUnique(String abreviaturaOficina, Integer entidadID, Integer localesID, Integer areaID) throws Exception {

        OficinaEntity filtro = new OficinaEntity();

        filtro.setAbreviaturaOficina(abreviaturaOficina);
        filtro.setEntidadID(entidadID);
        filtro.setLocalesID(localesID);
        filtro.setAreaID(areaID);

        List<OficinaEntity> resultados = oficinaService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarOficina(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String oficinaID = JS.toBlank(request.getParameter("oficinaID"));
            if (!JS._numeroEntero(oficinaID)) throw new SimioException("ROC-ECA-190", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            OficinaEntity entity = new OficinaEntity();

            entity.setOficinaID(JS.toInt(oficinaID));

            oficinaService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("ROC-ELI-191", dataJSON, sos);
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
                throw new SimioException("ROC-GED-192", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            OficinaEntity registro = (OficinaEntity) result.get("registro");

            oficinaService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("ROC-GUA-193", dataJSON, sos);
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
                throw new SimioException("ROC-GNU-194", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            OficinaEntity registro = (OficinaEntity) result.get("registro");

            oficinaService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getOficinaID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("ROC-GNU-195", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "oficina-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // cargar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboTodos(request, "cboLocal", itemsLocal);

            // cargar combo areas
            ComboWeb comboWebTodos = new ComboWeb();
            comboWebTodos.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);
            request.setAttribute("cboArea", comboWebTodos);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);
            params.put("entidadID", JS.toInt(usuarioSession.getCodigoEntidad()));

            List<Map<String, Object>> lista = oficinaService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", lista);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("ROC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String oficinaID = JS.toBlank(request.getParameter("oficinaID"));
            if (!JS._numeroEntero(oficinaID)) throw new SimioException("ROC-MED-196", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "oficina-editar", modulo, plantilla);

            // delegar logica a servicios
            OficinaEntity registro = oficinaService.selectByID(JS.toInt(oficinaID));
            if (registro == null) throw new SimioException("ROC-MED-197", "Oficina no encontrada");

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // cargar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(registro.getEntidadID());
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboSeleccione(request, "cboLocal", itemsLocal, registro.getLocalesID());

            // cargar combo areas
            AreaEntity filtroArea = new AreaEntity();
            filtroArea.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroArea.setEntidadID(registro.getEntidadID());
            filtroArea.setLocalesID(registro.getLocalesID());
            List<ListaItem> itemsArea = areaService.selectAreaCombo(filtroArea);
            cargarComboSeleccione(request, "cboArea", itemsArea, registro.getAreaID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("ROC-MED-212", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarNuevo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            // cargar combo local
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboSeleccione(request, "cboLocal", itemsLocal);

            // cargar combo area
            ComboWeb comboWebTodos = new ComboWeb();
            comboWebTodos.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            request.setAttribute("cboArea", comboWebTodos);

            // setear siguiente vista
            setVistaTemplate(model, "oficina-nuevo", modulo, plantilla);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("ROC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String localesID = JS.toUpperBlank(request.getParameter("cbo_local"));
            String areaID = JS.toUpperBlank(request.getParameter("cbo_area"));
            String entidadID = JS.toBlank(request.getParameter("cboEntidad"));

            Map<String, Object> result = new HashMap<>();

            // validacion para cboEntidad
            if (!JS._vacio(entidadID)) {

                String msg = JS._numeroEnteroNoNegativoString(entidadID, "Entidad");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cboEntidad");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cbo_local
            if (!JS._vacio(localesID)) {

                String msg = JS._numeroEnteroNoNegativoString(localesID, "Local");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cbo_local");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para cbo_area
            if (!JS._vacio(areaID)) {

                String msg = JS._numeroEnteroNoNegativoString(areaID, "Área");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "cbo_area");
                    result.put("msgError", msg);

                    throw new SimioException("ROC-VBU-198", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("ROC-VAB-199", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView cargarAreas(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String entidadID = JS.toBlank(request.getParameter("cboEntidad"));
            String localesID = JS.toBlank(request.getParameter("cbo_local"));
            String formulario = JS.toBlank(request.getParameter("formulario"));

            List<ListaItem> items = new ArrayList<>();

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            if (JS._numeroEntero(entidadID) && JS._numeroEntero(localesID)) {

                AreaEntity filtroArea = new AreaEntity();

                filtroArea.setIndDel(Constantes.REGISTRO_ACTIVO);
                // FIXME: posible bug, validar que locales siempre viene numero (ver si el combo no es TODOS porque habia vendria un cadena vacia)
                filtroArea.setLocalesID(JS.toInt(localesID));
                filtroArea.setEntidadID(JS.toInt(entidadID));

                items = areaService.selectAreaCombo(filtroArea);
            }

            if (formulario.equalsIgnoreCase("NUEVO") || formulario.equalsIgnoreCase("EDITAR")) {

                items.add(0, new ListaItem(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL));

            } else {

                items.add(0, new ListaItem(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL));
            }

            model.put("items", items);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RDC-GNU-143", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        Map<String, Object> result = new HashMap<>();

        String oficinaId = request.getParameter("int_oficinaID");
        String abreviaturaOficina = JS.toUpperBlank(request.getParameter("txt_abreviaturaOficina"));
        String entidadID = request.getParameter("cboEntidad");
        String indDel = request.getParameter("cbo_indDel");
        String areaID = request.getParameter("cbo_area");
        String localesID = request.getParameter("cbo_local");
        String nombreOficina = JS.toUpperBlank(request.getParameter("txt_nombreOficina"));
        String pisoOficina = JS.toUpperBlank(request.getParameter("txt_pisoOficina"));

        if (JS._vacio(abreviaturaOficina)) {
            result.put("campoError", "txt_abreviaturaOficina");
            result.put("msgError", "Abreviatura de Oficina no puede ser vacío");
            return result;
        }

        if (JS._vacio(entidadID) || JS._equiv(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cboEntidad");
            result.put("msgError", "Por favor seleccione una entidad");
            return result;
        }

        if (JS._vacio(localesID) || JS._equiv(localesID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_local");
            result.put("msgError", "Por favor seleccione un local");
            return result;
        }

        if (JS._vacio(areaID) || JS._equiv(areaID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_area");
            result.put("msgError", "Por favor seleccione una áera");
            return result;
        }

        if (JS._vacio(nombreOficina)) {
            result.put("campoError", "txt_nombreOficina");
            result.put("msgError", "Nombre de Oficina no puede ser vacío");
            return result;
        }

        if (JS._vacio(pisoOficina)) {
            result.put("campoError", "txt_pisoOficina");
            result.put("msgError", "Piso de Oficina no puede ser vacío");
            return result;
        }

        // validar combo indDel
        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        OficinaEntity found = buscarRegistroPorUnique(abreviaturaOficina, JS.toInt(entidadID), JS.toInt(localesID), JS.toInt(areaID));
        if (found != null && !JS._equiv(found.getOficinaID(), JS.toInt(oficinaId))) {
            result.put("campoError", "general");
            result.put("msgError", "Oficina ya fue registrada");

            return result;
        }

        // armar entity
        OficinaEntity registro = new OficinaEntity();

        registro.setIndDel(indDel);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setAbreviaturaOficina(abreviaturaOficina);
        registro.setLocalesID(JS.toInt(localesID));
        registro.setAreaID(JS.toInt(areaID));
        registro.setNombreOficina(nombreOficina);
        registro.setPisoOficina(pisoOficina);
        registro.setOficinaID(JS.toInt(oficinaId));

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        Map<String, Object> result = new HashMap<>();

        String areaID = request.getParameter("cbo_area");
        String localesID = request.getParameter("cbo_local");
        String abreviaturaOficina = JS.toUpperBlank(request.getParameter("txt_abreviaturaOficina"));
        String nombreOficina = JS.toUpperBlank(request.getParameter("txt_nombreOficina"));
        String pisoOficina = JS.toUpperBlank(request.getParameter("txt_pisoOficina"));
        String entidadID = request.getParameter("cboEntidad");

        // cboEntidad
        if (JS._vacio(entidadID) || JS._equiv(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cboEntidad");
            result.put("msgError", "Por favor seleccione una entidad");
            return result;
        }

        if (JS._vacio(abreviaturaOficina)) {
            result.put("campoError", "txt_abreviaturaOficina");
            result.put("msgError", "Abreviatura de Oficina no puede ser vacío");
            return result;
        }

        if (JS._vacio(localesID) || JS._equiv(localesID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_local");
            result.put("msgError", "Por favor seleccione un local");
            return result;
        }

        if (JS._vacio(areaID) || JS._equiv(areaID, Constantes.COMBO_SELECCIONE_VALUE)) {
            result.put("campoError", "cbo_area");
            result.put("msgError", "Por favor seleccione una área");
            return result;
        }

        if (JS._vacio(nombreOficina)) {
            result.put("campoError", "txt_nombreOficina");
            result.put("msgError", "Nombre de Oficina no puede ser vacío");
            return result;
        }

        if (JS._vacio(pisoOficina)) {
            result.put("campoError", "txt_pisoOficina");
            result.put("msgError", "Piso de Oficina no puede ser vacío");
            return result;
        }

        OficinaEntity found = buscarRegistroPorUnique(abreviaturaOficina, JS.toInt(entidadID), JS.toInt(localesID), JS.toInt(areaID));
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Oficina ya fue registrada");

            return result;
        }

        // armar entity
        OficinaEntity registro = new OficinaEntity();

        registro.setIndDel(Constantes.REGISTRO_ACTIVO);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setAbreviaturaOficina(abreviaturaOficina);
        registro.setLocalesID(JS.toInt(localesID));
        registro.setAreaID(JS.toInt(areaID));
        registro.setNombreOficina(nombreOficina);
        registro.setPisoOficina(pisoOficina);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verOficina(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String oficinaID = JS.toBlank(request.getParameter("oficinaID"));
            if (!JS._numeroEntero(oficinaID)) throw new SimioException("ROC-VCA-200", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "oficina-ver", modulo, plantilla);

            // delegar logica a servicios
            OficinaEntity registro = oficinaService.selectByID(JS.toInt(oficinaID));
            if (registro == null) throw new SimioException("ROC-VEC-201", "Oficina no encontrada");

            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);

            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);

            cargarComboSeleccione(request, "cboEntidad", itemsEntidad, registro.getEntidadID());

            // cargar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroLocal.setEntidadID(registro.getEntidadID());
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboSeleccione(request, "cboLocal", itemsLocal, registro.getLocalesID());

            // cargar combo areas
            AreaEntity filtroArea = new AreaEntity();
            filtroArea.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroArea.setEntidadID(registro.getEntidadID());
            filtroArea.setLocalesID(registro.getLocalesID());
            List<ListaItem> itemsArea = areaService.selectAreaCombo(filtroArea);
            cargarComboSeleccione(request, "cboArea", itemsArea, registro.getAreaID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("ROC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
