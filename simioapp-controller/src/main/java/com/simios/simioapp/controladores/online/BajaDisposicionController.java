package com.simios.simioapp.controladores.online;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.ListaUtil;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.servicios.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
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

public class BajaDisposicionController extends BaseController {

    private static final Logger log = Logger.getLogger(BajaDisposicionController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-baja-disposicion";
    private static final String SETEAR_NULL_ID = "SET_NULL";
    private static final String SETEAR_NULL_LABEL = "GUARDAR EN BLANCO";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("localesService")
    private LocalesService localesService;

    @Autowired
    @Qualifier("areaService")
    private AreaService areaService;

    @Autowired
    @Qualifier("oficinaService")
    private OficinaService oficinaService;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    @Autowired
    @Qualifier("inventarioBienService")
    private InventarioBienService inventarioBienService;


    public ModelAndView mostrarPanelBajaDisposicion(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "panel-baja-disposicion", modulo, plantilla);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);

            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);

            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, JS.toInt(usuarioSession.getCodigoEntidad()));

            // cargar combo locales
            LocalesEntity filtroLocal = new LocalesEntity();
            filtroLocal.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));
            filtroLocal.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsLocal = localesService.selectLocalesCombo(filtroLocal);
            cargarComboTodos(request, "cboLocal", itemsLocal);

            // combo todos por default
            ComboWeb comboWebTodos = new ComboWeb();
            comboWebTodos.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);

            // cargar combo areas y oficinas
            request.setAttribute("cboArea", comboWebTodos);
            request.setAttribute("cboOficina", comboWebTodos);

            // cargar combo estado de subida
            cargarComboCatalogoTodos(request, "cboEstadoSubida", catalogo.ESTADO_SUBIDA, catalogo.ESTADO_SUBIDA_FALTA_SUBIR);

            // cargar combo estado sobrante faltante
//            cargarComboCatalogoTodos(request, "cboSobranteFaltante", catalogo.SOBRANTE_FALTANTE);

            // carga combo de inventarios activos
            InventarioEntity filtroInventario = new InventarioEntity();

            filtroInventario.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroInventario.setEstado(catalogo.ESTADO_INVENTARIO_ABIERTO);
            filtroInventario.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));

            List<ListaItem> itemsInventario = inventarioService.listarInventarios(filtroInventario);

            // si tiene un solo item, marcarlo por default
            if ( CollectionUtils.isNotEmpty(itemsInventario) && itemsInventario.size() == 1 ) {
                cargarComboSeleccione(request, "cboInventario", itemsInventario, itemsInventario.get(0).getId());
            } else {
                cargarComboSeleccione(request, "cboInventario", itemsInventario);
            }

            // carga combo de usuarios (en este caso, inventariador)
            UsuarioEntity filtroUsuario = new UsuarioEntity();

            filtroUsuario.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroUsuario.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));

            List<ListaItem> itemsInventariador = usuarioService.listarUsuarios(filtroUsuario);

            cargarComboTodos(request, "cboInventariador", itemsInventariador);

            ComboWeb cboGuardar = cargarComboCatalogoSeleccione(request, "cboSobranteFaltanteGuardar", catalogo.SOBRANTE_FALTANTE);
            cboGuardar.addItem(SETEAR_NULL_ID, SETEAR_NULL_LABEL);

            handleNoExistenInventariosActivos(model, JS.toInt(usuarioSession.getCodigoEntidad()));

            // poblar modelo (se muestra una lista vacia)
            model.put("lista", null);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RIC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }

    }

    private void handleNoExistenInventariosActivos(Map<String, Object> model, int entidadID) throws Exception {

        InventarioEntity filter = new InventarioEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setEstado(catalogo.ESTADO_INVENTARIO_ABIERTO);
        filter.setEntidadID(entidadID);

        List<InventarioEntity> results = inventarioService.select(filter);

        if (CollectionUtils.isEmpty(results)) {
            model.put("msgHidden", "No existen inventarios abiertos para modificar");
        }

    }


    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String cboEntidad = JS.toUpperBlank(request.getParameter("cboEntidad"));
            String int_inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));

            Map<String, Object> result = new HashMap<>();

            String msg = JS._numeroEnteroNoNegativoString(cboEntidad, "");
            if (!JS._vacio(msg)) {
                result.put("campoError", "cboEntidad");
                result.put("msgError", "Por favor seleccione uno");

                throw new SimioException("RIC-VBU-360", "Por favor seleccione uno", JExceptionEnum.VALIDACION, result);
            }

            msg = JS._numeroEnteroNoNegativoString(int_inventarioID, "");
            if (!JS._vacio(msg)) {
                result.put("campoError", "int_inventarioID");
                result.put("msgError", "Por favor seleccione uno");

                throw new SimioException("RIC-VBU-360", "Por favor seleccione uno", JExceptionEnum.VALIDACION, result);
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RIC-VAB-361", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


    private void limpiarSession(HttpServletRequest request) throws Exception {

        // limpiar los atributos de session
        // setSessionAttribute(request,
        // Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);

    }

    public ModelAndView buscarInventarioBien(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String inventarioBienID = JS.toUpperBlank(request.getParameter("int_inventarioBienID"));
            String bienID = JS.toUpperBlank(request.getParameter("int_bienID"));
            String inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));
            String estadoSubida = JS.toUpperBlank(request.getParameter("txt_estadoSubida"));
            String sobranteFaltante = JS.toUpperBlank(request.getParameter("txt_sobranteFaltante"));
            String inventariador = JS.toBlank(request.getParameter("txt_inventariador"));
            String codigoPatrimonial = JS.toBlank(request.getParameter("txt_codigoPatrimonial"));
            String entidadID = JS.toUpperBlank(request.getParameter("cboEntidad"));
            String localesID = JS.toUpperBlank(request.getParameter("cbo_local"));
            String areaID = JS.toUpperBlank(request.getParameter("cbo_area"));
            String oficinaID = JS.toUpperBlank(request.getParameter("cbo_oficina"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "panel-sobrante-faltante-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(inventarioBienID)) params.put("inventarioBienID", JS.toLong(inventarioBienID));
            if (JS._numeroEntero(bienID)) params.put("bienID", JS.toLong(bienID));
            if (JS._numeroEntero(inventarioID)) params.put("inventarioID", JS.toLong(inventarioID));
            if (!JS._vacio(estadoSubida)) params.put("estadoSubida", estadoSubida);
            if (!JS._vacio(sobranteFaltante)) params.put("sobranteFaltante", sobranteFaltante);
            if (!JS._vacio(inventariador)) params.put("inventariador", inventariador);
            if (!JS._vacio(codigoPatrimonial)) params.put("codigoPatrimonial", "%" + codigoPatrimonial + "%");
            if (JS._numeroEntero(entidadID)) params.put("entidadID", JS.toLong(entidadID));
            if (JS._numeroEntero(localesID)) params.put("localesID", JS.toLong(localesID));
            if (JS._numeroEntero(areaID)) params.put("areaID", JS.toLong(areaID));
            if (JS._numeroEntero(oficinaID)) params.put("oficinaID", JS.toLong(oficinaID));

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaInventarioBien = inventarioBienService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaInventarioBien);
            model.put("infoExtraHidden", createInfoResumenSobranteFaltante(listaInventarioBien));

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("SFC-BIB-351", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }

    }

    private String createInfoResumenSobranteFaltante(List<Map<String, Object>> bienes) throws Exception {

        Map<String, Object> info = new HashMap<>();

        int nroBlancos = 0;
        int nroSobrantes = 0;
        int nroFaltantes = 0;
        int nroBienes = bienes.size();

        if (CollectionUtils.isNotEmpty(bienes)) {

            // codigo patrimonial de los no asignados
            List<String> listaBlancos = new ArrayList<>(nroBienes);

            // buscando los no asignados
            for (Map<String, Object> bien : bienes) {

                String sobranteFaltante = MapUtils.getString(bien, "sobranteFaltante");

                if (StringUtils.isBlank(sobranteFaltante)) {
                    listaBlancos.add(MapUtils.getString(bien, "codigoPatrimonial", StringUtils.EMPTY));
                    nroBlancos++;
                } else if (StringUtils.equals( sobranteFaltante, catalogo.SOBRANTE_FALTANTE_SOBRANTE)) {
                    nroSobrantes++;
                } else if (StringUtils.equals( sobranteFaltante, catalogo.SOBRANTE_FALTANTE_FALTANTE)) {
                    nroFaltantes++;
                }
            }

            info.put("nroBienes", String.valueOf(nroBienes));
            info.put("nroBlancos", String.valueOf(nroBlancos));
            info.put("nroSobrantes", String.valueOf(nroSobrantes));
            info.put("nroFaltantes", String.valueOf(nroFaltantes));
            info.put("listaBlancos", listaBlancos);
        }

        return new ObjectMapper().writeValueAsString(info);
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
            String msgError = handleJSONError("RDC-CA-143", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


    public ModelAndView cargarOficinas(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String entidadID = JS.toBlank(request.getParameter("cboEntidad"));
            String localesID = JS.toBlank(request.getParameter("cbo_local"));
            String areaID = JS.toBlank(request.getParameter("cbo_area"));
            String formulario = JS.toBlank(request.getParameter("formulario"));

            List<ListaItem> items = new ArrayList<>();

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            if (JS._numeroEntero(entidadID) && JS._numeroEntero(localesID) && JS._numeroEntero(areaID)) {

                OficinaEntity filtroOficina = new OficinaEntity();

                filtroOficina.setIndDel(Constantes.REGISTRO_ACTIVO);
                filtroOficina.setLocalesID(JS.toInt(localesID));
                filtroOficina.setEntidadID(JS.toInt(entidadID));
                filtroOficina.setAreaID(JS.toInt(areaID));

                items = oficinaService.selectOficinaCombo(filtroOficina);
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
            String msgError = handleJSONError("SFC-CO-143", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


    public ModelAndView guardar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String bienesJSON = JS.toBlank(request.getParameter("bienesID"));
            String resolucionBaja = JS.toBlank(request.getParameter("resolucionBaja"));
            String fechaResolucionBaja = JS.toBlank(request.getParameter("fechaResolucionBaja"));
            String causalBaja = JS.toBlank(request.getParameter("causalBaja"));
            String numDocSBN = JS.toBlank(request.getParameter("numDocSBN"));
            String resolucionDisposicion = JS.toBlank(request.getParameter("resolucionDisposicion"));
            String fechaResolucionDisposicion = JS.toBlank(request.getParameter("fechaResolucionDisposicion"));
            String actoDisposicion = JS.toBlank(request.getParameter("actoDisposicion"));
            String donatorio = JS.toBlank(request.getParameter("donatorio"));
            
            fechaResolucionBaja = null;
            fechaResolucionDisposicion = null;

            if (StringUtils.isBlank(bienesJSON)) {
                throw new Exception("Parámetros incorrectos para la petición");
            }

//            if (StringUtils.equals(sobranteFaltante, Constantes.COMBO_SELECCIONE_VALUE)) {
//                throw new Exception("Parámetros incorrectos para la petición");
//            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            ArrayList<String> inventarioBienIDList = new ObjectMapper().readValue(bienesJSON, ArrayList.class);

            List<Integer> ids = ListaUtil.parseToNumberList(inventarioBienIDList);

            inventarioBienService.guardarBajaDisposicion(ids, resolucionBaja, fechaResolucionBaja, causalBaja, numDocSBN, 
            		resolucionDisposicion, fechaResolucionDisposicion, actoDisposicion, donatorio);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("SFC-G-432", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


}
