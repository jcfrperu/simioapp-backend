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
import com.simios.simioapp.dominio.entidades.CuentaEntity;
import com.simios.simioapp.dominio.entidades.LocalesEntity;
import com.simios.simioapp.dominio.entidades.UbigeoEntity;
import com.simios.simioapp.servicios.CuentaService;
import com.simios.simioapp.servicios.LocalesService;
import com.simios.simioapp.servicios.UbigeoService;
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

public class RegistroLocalesController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroLocalesController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-locales";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("localesService")
    private LocalesService localesService;

    @Autowired
    @Qualifier("cuentaService")
    private CuentaService cuentaService;

    @Autowired
    @Qualifier("ubigeoService")
    private UbigeoService ubigeoService;

    public ModelAndView buscarLocales(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String codigo = JS.toUpperBlank(request.getParameter("txt_codigo"));
            String entidadID = JS.toUpperBlank(request.getParameter("txt_entidadID"));
            String nombreLocal = JS.toUpperBlank(request.getParameter("txt_nombreLocal"));
            String direccion = JS.toUpperBlank(request.getParameter("txt_direccion"));
            String codigoPredio = JS.toUpperBlank(request.getParameter("txt_codigoPredio"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "locales-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(codigo)) params.put("codigo", "%" + codigo + "%");
            if (JS._numeroEntero(entidadID)) params.put("entidadID", JS.toInt(entidadID));
            if (!JS._vacio(nombreLocal)) params.put("nombreLocal", "%" + nombreLocal + "%");
            if (!JS._vacio(direccion)) params.put("direccion", "%" + direccion + "%");
            if (!JS._vacio(codigoPredio)) params.put("codigoPredio", "%" + codigoPredio + "%");
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaLocales = localesService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaLocales);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RLC-BCA-176", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private LocalesEntity buscarRegistroPorUnique(String codigo, int entidadID) throws Exception {

        LocalesEntity filtro = new LocalesEntity();

        filtro.setCodigo(codigo);
        filtro.setEntidadID(entidadID);

        List<LocalesEntity> resultados = localesService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarLocales(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String localesID = JS.toBlank(request.getParameter("localesID"));
            if (!JS._numeroEntero(localesID)) throw new SimioException("RLC-ECA-177", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            LocalesEntity entity = new LocalesEntity();

            entity.setLocalesID(JS.toInt(localesID));

            localesService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);


        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RLC-ELI-178", dataJSON, sos);
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
                throw new SimioException("RLC-GED-179", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            LocalesEntity registro = (LocalesEntity) result.get("registro");

            localesService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RLC-GUA-180", dataJSON, sos);
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
                throw new SimioException("RLC-GNU-181", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            LocalesEntity registro = (LocalesEntity) result.get("registro");

            localesService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getLocalesID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RLC-GNU-182", dataJSON, sos);
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
            setVistaTemplate(model, "locales-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            UsuarioSession usuarioSession = getUsuarioSession(request);

            // armar combo entidades
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);
            params.put("entidadID", JS.toInt(usuarioSession.getCodigoEntidad()));

            List<Map<String, Object>> listaLocales = localesService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaLocales);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RLC-MBU-001", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String localesID = JS.toBlank(request.getParameter("localesID"));
            if (!JS._numeroEntero(localesID)) throw new SimioException("RLC-MED-183", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "locales-editar", modulo, plantilla);

            // delegar logica a servicios
            LocalesEntity registro = localesService.selectByID(JS.toInt(localesID));
            if (registro == null) throw new SimioException("RLC-MED-184", "Locales no encontrada");

            // armar combo entidades
            List<ListaItem> itemsEntidad = getListaEntidades(getUsuarioSession(request));
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            cargarComboCatalogoSeleccione(request, "cboUnidadMedida", catalogo.UNIDAD_MEDIDA, registro.getFlgUm());
            cargarComboCatalogoSeleccione(request, "cboTipoMoneda", catalogo.TIPO_MONEDA, registro.getFlgTipomo());
            cargarComboCatalogoSeleccione(request, "cboTipoPropiedad", catalogo.TIPO_PROPIEDAD, registro.getFlgPropie());
            cargarComboCatalogoSeleccione(request, "cboTipoDireccion", catalogo.TIPO_DIRECCION, registro.getFlgTipovi());
            cargarComboCatalogoSeleccione(request, "cboOficinaRegistral", catalogo.OFICINA_REGISTRAL, registro.getFlgTipore());
            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.TIPO_CUENTA);

            String departamentoUbigeo = Constantes.COMBO_SELECCIONE_LABEL;
            String provinciaUbigeo = Constantes.COMBO_SELECCIONE_LABEL;
            String distritoUbigeo = Constantes.COMBO_SELECCIONE_LABEL;

            if (registro.getCodigoUbigeo() != null) {

                if (registro.getCodigoUbigeo().length() >= 6) {

                    departamentoUbigeo = registro.getCodigoUbigeo().substring(0, 2);
                    provinciaUbigeo = registro.getCodigoUbigeo().substring(0, 4);
                    distritoUbigeo = registro.getCodigoUbigeo().substring(0, 6);

                }
            }

            /* cargando combo departamento */
            UbigeoEntity filtroDepartamento = new UbigeoEntity();
            List<ListaItem> itemsDepartamento = ubigeoService.selectDepartamentoCombo(filtroDepartamento);
            ComboWeb comboWebDepartamento = new ComboWeb(itemsDepartamento);

            if (CollectionUtils.isNotEmpty(itemsDepartamento)) {
                comboWebDepartamento.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }
            comboWebDepartamento.setIdSeleccionado(departamentoUbigeo);
            request.setAttribute("cboDepartamento", comboWebDepartamento);

            /* cargando combo provincia */
            UbigeoEntity filtroProvincia = new UbigeoEntity();
            filtroProvincia.setCodigoUbigeo(departamentoUbigeo + "%");
            filtroProvincia.setIndDel("0");
            List<ListaItem> itemsProvincia = ubigeoService.selectProvinciaCombo(filtroProvincia);
            ComboWeb comboWebProvincia = new ComboWeb(itemsProvincia);

            if (CollectionUtils.isNotEmpty(itemsProvincia)) {
                comboWebProvincia.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }
            comboWebProvincia.setIdSeleccionado(provinciaUbigeo);
            request.setAttribute("cboProvincia", comboWebProvincia);

            /* cargando combo distrito */
            UbigeoEntity filtroDistrito = new UbigeoEntity();
            filtroDistrito.setCodigoUbigeo(provinciaUbigeo + "%");
            filtroDistrito.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsDistrito = ubigeoService.selectDistritoCombo(filtroDistrito);
            ComboWeb comboWebDistrito = new ComboWeb(itemsDistrito);

            if (CollectionUtils.isNotEmpty(itemsDistrito)) {
                comboWebDistrito.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }
            comboWebDistrito.setIdSeleccionado(distritoUbigeo);
            request.setAttribute("cboDistrito", comboWebDistrito);

            /* Seleccionamos por ID */
            if (JS._numeroEntero(registro.getDscCtactb())) {

                // buscar cuenta
                CuentaEntity itemCuenta = cuentaService.selectByID(Integer.parseInt(registro.getDscCtactb()));

                // si existe cuenta
                if (itemCuenta != null) {

                    CuentaEntity filtro = new CuentaEntity();

                    filtro.setTipoCta(catalogo.CUENTA_TIPOCUENTA_INMUEBLE);
                    filtro.setUsoCta(itemCuenta.getUsoCta());
                    filtro.setFlagCta(catalogo.CUENTA_FLAGCUENTA_VERDADERO);
                    filtro.setIndDel(Constantes.REGISTRO_ACTIVO);

                    List<ListaItem> itemsCuenta = cuentaService.selectCuentaCombo(filtro);
                    ComboWeb comboWebCuenta = new ComboWeb(itemsCuenta);

                    if (CollectionUtils.isNotEmpty(itemsCuenta)) {
                        comboWebCuenta.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
                    }

                    comboWebCuenta.setIdSeleccionado(registro.getDscCtactb());
                    request.setAttribute("cboCuentas", comboWebCuenta);

                    cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.TIPO_CUENTA, itemCuenta.getUsoCta());
                }
            }

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RLC-MED-212", sos);
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
            setVistaTemplate(model, "locales-nuevo", modulo, plantilla);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false);

            cargarComboCatalogoSeleccione(request, "cboUnidadMedida", catalogo.UNIDAD_MEDIDA);
            cargarComboCatalogoSeleccione(request, "cboTipoMoneda", catalogo.TIPO_MONEDA);
            cargarComboCatalogoSeleccione(request, "cboTipoPropiedad", catalogo.TIPO_PROPIEDAD);
            cargarComboCatalogoSeleccione(request, "cboTipoDireccion", catalogo.TIPO_DIRECCION);
            cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.TIPO_CUENTA);
            cargarComboCatalogoSeleccione(request, "cboOficinaRegistral", catalogo.OFICINA_REGISTRAL);

            /* cargando combo departamento */
            UbigeoEntity filtro = new UbigeoEntity();
            List<ListaItem> itemsDepartamento = ubigeoService.selectDepartamentoCombo(filtro);
            ComboWeb comboWebDepartamento = new ComboWeb(itemsDepartamento);

            if (CollectionUtils.isNotEmpty(itemsDepartamento)) {
                comboWebDepartamento.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }

            request.setAttribute("cboDepartamento", comboWebDepartamento);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RLC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String localesID = JS.toUpperBlank(request.getParameter("int_localesID"));

            Map<String, Object> result = new HashMap<>();

            // validacion para localesID
            if (!JS._vacio(localesID)) {

                String msg = JS._numeroEnteroNoNegativoString(localesID, "Locales ID");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_localesID");
                    result.put("msgError", msg);

                    throw new SimioException("RLC-VBU-185", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RLC-VAB-186", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String localesID = JS.toUpperBlank(request.getParameter("int_localesID"));
        String codigo = JS.toUpperBlank(request.getParameter("txt_codigo"));
        String entidadID = JS.toUpperBlank(request.getParameter("txt_entidadID"));
        String nombreLocal = JS.toUpperBlank(request.getParameter("txt_nombreLocal"));
        String direccion = JS.toUpperBlank(request.getParameter("txt_direccion"));
        String dscMz = JS.toBlank(request.getParameter("txt_dscMz"));
        String dscTomo = JS.toBlank(request.getParameter("txt_dscTomo"));
        String ctdArea = JS.toBlank(request.getParameter("dbl_ctdArea"));
        String dscFojas = JS.toBlank(request.getParameter("int_dscFojas"));
        String codigoUbigeo = JS.toBlank(request.getParameter("txt_codigoUbigeo"));
        String flgPropie = JS.toBlank(request.getParameter("txt_flgPropie"));
        String dscBenefi = JS.toBlank(request.getParameter("txt_dscBenefi"));
        String dscAsinab = JS.toBlank(request.getParameter("txt_dscAsinab"));
        String dscPelect = JS.toBlank(request.getParameter("txt_dscPelect"));
        String flgTipovi = JS.toBlank(request.getParameter("txt_flgTipovi"));
        String flgTipore = JS.toBlank(request.getParameter("txt_flgTipore"));
        String dscCtactb = JS.toBlank(request.getParameter("cbo_cuenta"));
        String dscAsient = JS.toBlank(request.getParameter("txt_dscAsient"));
        String codigoPredio = JS.toUpperBlank(request.getParameter("txt_codigoPredio"));
        String ctdValor = JS.toBlank(request.getParameter("dbl_ctdValor"));
        String flgUm = JS.toBlank(request.getParameter("txt_flgUm"));
        String numeroMun = JS.toBlank(request.getParameter("txt_numeroMun"));
        String dscLote = JS.toBlank(request.getParameter("txt_dscLote"));
        String dscPropie = JS.toBlank(request.getParameter("txt_dscPropie"));
        String flgTipomo = JS.toBlank(request.getParameter("txt_flgTipomo"));
        String indDel = JS.toBlank(request.getParameter("cbo_indDel"));

        Map<String, Object> result = new HashMap<>();

        // validacion para localesID
        String msg = JS._numeroEnteroNoNegativoString(localesID, "Locales ID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_localesID");
            result.put("msgError", msg);

            return result;
        }

        // validar codigo
        msg = JS._campoMaxSizeNoVacio(codigo, 3, "Código Local");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigo");
            result.put("msgError", msg);

            return result;
        }

        // validar entidadID
        if (JS._equiv(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_entidadID");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(entidadID, 10, "Entidad ID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_entidadID");
            result.put("msgError", msg);

            return result;
        }

        // validar nombreLocal
        msg = JS._campoMaxSizeNoVacio(nombreLocal, 100, "Nombre Local");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombreLocal");
            result.put("msgError", msg);

            return result;
        }

        // validar direccion
        msg = JS._campoMaxSizeNoVacio(direccion, 100, "Dirección");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_direccion");
            result.put("msgError", msg);

            return result;
        }

        // validar dscMz
        msg = JS._campoMaxSizeNoVacio(dscMz, 20, "Mz");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscMz");
            result.put("msgError", msg);

            return result;
        }

        // validar dscTomo
        msg = JS._campoMaxSizeNoVacio(dscTomo, 100, "Tomo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscTomo");
            result.put("msgError", msg);

            return result;
        }

        // validar ctdArea
        msg = JS._campoMaxSizeNoVacio(ctdArea, 13, "Área");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdArea");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroString(ctdArea, "Área");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdArea");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroNoNegativoString(ctdArea, "Área");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdArea");
            result.put("msgError", msg);

            return result;
        }

        // validar dscFojas
        msg = JS._campoNoVacio(dscFojas, "Fojas");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_dscFojas");
            result.put("msgError", msg);

            return result;
        }


        // validar codigoUbigeo
        msg = JS._campoMaxSizeNoVacio(codigoUbigeo, 6, "Ubigeo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoUbigeo");
            result.put("msgError", msg);

            return result;
        }

        // validar flgPropie
        if (JS._equiv(flgPropie, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgPropie");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgPropie, 10, "flgPropie");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgPropie");
            result.put("msgError", msg);

            return result;
        }

        // validar dscBenefi
        msg = JS._campoMaxSizeNoVacio(dscBenefi, 100, "Beneficiario");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscBenefi");
            result.put("msgError", msg);

            return result;
        }

        // validar dscAsinab
        msg = JS._campoMaxSizeNoVacio(dscAsinab, 100, "Registro SINABIP");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscAsinab");
            result.put("msgError", msg);

            return result;
        }

        // validar dscPelect
        msg = JS._campoMaxSizeNoVacio(dscPelect, 100, "Partida Electrónica");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscPelect");
            result.put("msgError", msg);

            return result;
        }

        // validar flgTipovi
        if (JS._equiv(flgTipovi, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgTipovi");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgTipovi, 20, "flgTipovi");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgTipovi");
            result.put("msgError", msg);

            return result;
        }

        // validar flgTipore
        if (JS._equiv(flgTipore, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgTipore");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgTipore, 100, "flgTipore");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgTipore");
            result.put("msgError", msg);

            return result;
        }

        // validar dscCtactb
        if (JS._equiv(dscCtactb, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_cuenta");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(dscCtactb, 100, "Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "cbo_cuenta");
            result.put("msgError", msg);

            return result;
        }

        // validar dscAsient
        msg = JS._campoMaxSizeNoVacio(dscAsient, 100, "Asiento");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscAsient");
            result.put("msgError", msg);

            return result;
        }

        // validar codigoPredio
        msg = JS._campoMaxSizeNoVacio(codigoPredio, 100, "Código Predio");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoPredio");
            result.put("msgError", msg);

            return result;
        }

        // validar ctdValor
        msg = JS._numeroString(ctdValor, "Valor Contable");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdValor");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroNoNegativoString(ctdValor, "Valor Contable");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdValor");
            result.put("msgError", msg);

            return result;
        }

        if (!JS._numeroDecimal(ctdValor, 0.0, 99999999.99)) {

            result.put("campoError", "dbl_ctdValor");
            result.put("msgError", msg);

            return result;
        }

        // validar flgUm
        if (JS._equiv(flgUm, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgUm");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgUm, 10, "Unidad de Medida");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgUm");
            result.put("msgError", msg);

            return result;
        }

        // validar numeroMun
        msg = JS._campoNoVacio(numeroMun, "N°");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_numeroMun");
            result.put("msgError", msg);

            return result;
        }

        // validar dscLote
        msg = JS._campoMaxSizeNoVacio(dscLote, 100, "Lote");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscLote");
            result.put("msgError", msg);

            return result;
        }

        // validar dscPropie
        msg = JS._campoMaxSizeNoVacio(dscPropie, 100, "Propiedad Registral");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscPropie");
            result.put("msgError", msg);

            return result;
        }

        // validar flgTipomo
        if (JS._equiv(flgTipomo, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgTipomo");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgTipomo, 100, "Tipo de Moneda");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgTipomo");
            result.put("msgError", msg);

            return result;
        }

        // validar que no exista otro igual
        LocalesEntity found = buscarRegistroPorUnique(codigo, JS.toInt(entidadID));
        if (found != null && !JS._equiv(found.getLocalesID(), JS.toInt(localesID))) {
            result.put("campoError", "general");
            result.put("msgError", "Locales ya fue registrada");

            return result;
        }

        // armar entity
        LocalesEntity registro = new LocalesEntity();

        registro.setLocalesID(JS.toInt(localesID));
        registro.setCodigo(codigo);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setNombreLocal(nombreLocal);
        registro.setDireccion(direccion);
        registro.setDscMz(dscMz);
        registro.setDscTomo(dscTomo);
        registro.setCtdArea(JS.toDouble(ctdArea));
        registro.setDscFojas(dscFojas);
        registro.setCodigoUbigeo(codigoUbigeo);
        registro.setFlgPropie(flgPropie);
        registro.setDscBenefi(dscBenefi);
        registro.setDscAsinab(dscAsinab);
        registro.setDscPelect(dscPelect);
        registro.setFlgTipovi(flgTipovi);
        registro.setFlgTipore(flgTipore);
        registro.setDscCtactb(dscCtactb);
        registro.setDscAsient(dscAsient);
        registro.setCodigoPredio(codigoPredio);
        registro.setCtdValor(JS.toDouble(ctdValor));
        registro.setFlgUm(flgUm);
        registro.setNumeroMun(numeroMun);
        registro.setDscLote(dscLote);
        registro.setDscPropie(dscPropie);
        registro.setFlgTipomo(flgTipomo);
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String entidadID = JS.toUpperBlank(request.getParameter("txt_entidadID"));
        String nombreLocal = JS.toUpperBlank(request.getParameter("txt_nombreLocal"));
        String direccion = JS.toUpperBlank(request.getParameter("txt_direccion"));
        String dscMz = JS.toBlank(request.getParameter("txt_dscMz"));
        String dscTomo = JS.toBlank(request.getParameter("txt_dscTomo"));
        String ctdArea = JS.toBlank(request.getParameter("dbl_ctdArea"));
        String dscFojas = JS.toBlank(request.getParameter("int_dscFojas"));
        String codigoUbigeo = JS.toBlank(request.getParameter("txt_codigoUbigeo"));
        String flgPropie = JS.toBlank(request.getParameter("txt_flgPropie"));
        String dscBenefi = JS.toBlank(request.getParameter("txt_dscBenefi"));
        String dscAsinab = JS.toBlank(request.getParameter("txt_dscAsinab"));
        String dscPelect = JS.toBlank(request.getParameter("txt_dscPelect"));
        String flgTipovi = JS.toBlank(request.getParameter("txt_flgTipovi"));
        String flgTipore = JS.toBlank(request.getParameter("txt_flgTipore"));
        String dscCtactb = JS.toBlank(request.getParameter("cbo_cuenta"));
        String dscAsient = JS.toBlank(request.getParameter("txt_dscAsient"));
        String codigoPredio = JS.toUpperBlank(request.getParameter("txt_codigoPredio"));
        String ctdValor = JS.toBlank(request.getParameter("dbl_ctdValor"));
        String flgUm = JS.toBlank(request.getParameter("txt_flgUm"));
        String numeroMun = JS.toBlank(request.getParameter("txt_numeroMun"));
        String dscLote = JS.toBlank(request.getParameter("txt_dscLote"));
        String dscPropie = JS.toBlank(request.getParameter("txt_dscPropie"));
        String flgTipomo = JS.toBlank(request.getParameter("txt_flgTipomo"));

        Map<String, Object> result = new HashMap<>();

        // validar entidadID
        if (JS._equiv(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_entidadID");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        String msg = JS._campoMaxSizeNoVacio(entidadID, 10, "Entidad ID");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_entidadID");
            result.put("msgError", msg);

            return result;
        }

        // validar nombreLocal
        msg = JS._campoMaxSizeNoVacio(nombreLocal, 100, "Nombre Local");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombreLocal");
            result.put("msgError", msg);

            return result;
        }

        // validar direccion
        msg = JS._campoMaxSizeNoVacio(direccion, 100, "Dirección");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_direccion");
            result.put("msgError", msg);

            return result;
        }

        // validar dscMz
        msg = JS._campoMaxSizeNoVacio(dscMz, 20, "Mz");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscMz");
            result.put("msgError", msg);

            return result;
        }

        // validar dscTomo
        msg = JS._campoMaxSizeNoVacio(dscTomo, 100, "Tomo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscTomo");
            result.put("msgError", msg);

            return result;
        }

        // validar ctdArea
        msg = JS._campoMaxSizeNoVacio(ctdArea, 13, "Área");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdArea");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroString(ctdArea, "Área");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdArea");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroNoNegativoString(ctdArea, "Área");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdArea");
            result.put("msgError", msg);

            return result;
        }

        // validar dscFojas
        msg = JS._campoNoVacio(dscFojas, "Fojas");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_dscFojas");
            result.put("msgError", msg);

            return result;
        }


        // validar codigoUbigeo
        msg = JS._campoMaxSizeNoVacio(codigoUbigeo, 6, "Ubigeo");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoUbigeo");
            result.put("msgError", msg);

            return result;
        }

        // validar flgPropie
        if (JS._equiv(flgPropie, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgPropie");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgPropie, 10, "Propiedad");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgPropie");
            result.put("msgError", msg);

            return result;
        }

        // validar dscBenefi
        msg = JS._campoMaxSizeNoVacio(dscBenefi, 100, "Beneficiario");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscBenefi");
            result.put("msgError", msg);

            return result;
        }

        // validar dscAsinab
        msg = JS._campoMaxSizeNoVacio(dscAsinab, 100, "Registro SINABIP");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscAsinab");
            result.put("msgError", msg);

            return result;
        }

        // validar dscPelect
        msg = JS._campoMaxSizeNoVacio(dscPelect, 100, "Partida Electrónica");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscPelect");
            result.put("msgError", msg);

            return result;
        }

        // validar flgTipovi
        if (JS._equiv(flgTipovi, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgTipovi");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgTipovi, 20, "Tipo Dirección");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgTipovi");
            result.put("msgError", msg);

            return result;
        }

        // validar flgTipore
        if (JS._equiv(flgTipore, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgTipore");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgTipore, 100, "Oficina Registral");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgTipore");
            result.put("msgError", msg);

            return result;
        }

        // validar dscCtactb
        if (JS._equiv(dscCtactb, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_cuenta");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(dscCtactb, 100, "Cuenta");
        if (!JS._vacio(msg)) {

            result.put("campoError", "cbo_cuenta");
            result.put("msgError", msg);

            return result;
        }

        // validar dscAsient
        msg = JS._campoMaxSizeNoVacio(dscAsient, 100, "Asiento");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscAsient");
            result.put("msgError", msg);

            return result;
        }

        // validar codigoPredio
        msg = JS._campoMaxSizeNoVacio(codigoPredio, 100, "Código Predio");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoPredio");
            result.put("msgError", msg);

            return result;
        }

        // validar ctdValor
        msg = JS._numeroString(ctdValor, "Valor Contable");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdValor");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroNoNegativoString(ctdValor, "Valor Contable");
        if (!JS._vacio(msg)) {

            result.put("campoError", "dbl_ctdValor");
            result.put("msgError", msg);

            return result;
        }

        if (!JS._numeroDecimal(ctdValor, 0.0, 99999999.99)) {

            result.put("campoError", "dbl_ctdValor");
            result.put("msgError", msg);

            return result;
        }

        // validar flgUm
        if (JS._equiv(flgUm, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgUm");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgUm, 10, "Unidad de Medida");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgUm");
            result.put("msgError", msg);

            return result;
        }

        // validar numeroMun
        msg = JS._campoNoVacio(numeroMun, "N°");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_numeroMun");
            result.put("msgError", msg);

            return result;
        }

        // validar dscLote
        msg = JS._campoMaxSizeNoVacio(dscLote, 100, "Lote");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscLote");
            result.put("msgError", msg);

            return result;
        }

        // validar dscPropie
        msg = JS._campoMaxSizeNoVacio(dscPropie, 100, "Propiedad Registral");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_dscPropie");
            result.put("msgError", msg);

            return result;
        }

        // validar flgTipomo
        if (JS._equiv(flgTipomo, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_flgTipomo");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._campoMaxSizeNoVacio(flgTipomo, 100, "Tipo de Moneda");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_flgTipomo");
            result.put("msgError", msg);

            return result;
        }

        Integer codigoInteger = localesService.buscarMaximoCodigo(JS.toInt(entidadID));

        String codigo = StringUtils.leftPad(String.valueOf(codigoInteger + 1), 3, '0');

        // validar que no exista otro igual
        LocalesEntity found = buscarRegistroPorUnique(codigo, JS.toInt(entidadID));
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Locales ya fue registrada");

            return result;
        }

        // armar entity
        LocalesEntity registro = new LocalesEntity();

        registro.setCodigo(codigo);
        registro.setEntidadID(JS.toInt(entidadID));
        registro.setNombreLocal(nombreLocal);
        registro.setDireccion(direccion);
        registro.setDscMz(dscMz);
        registro.setDscTomo(dscTomo);
        registro.setCtdArea(JS.toDouble(ctdArea));
        registro.setDscFojas(dscFojas);
        registro.setCodigoUbigeo(codigoUbigeo);
        registro.setFlgPropie(flgPropie);
        registro.setDscBenefi(dscBenefi);
        registro.setDscAsinab(dscAsinab);
        registro.setDscPelect(dscPelect);
        registro.setFlgTipovi(flgTipovi);
        registro.setFlgTipore(flgTipore);
        registro.setDscCtactb(dscCtactb);
        registro.setDscAsient(dscAsient);
        registro.setCodigoPredio(codigoPredio);
        registro.setCtdValor(JS.toDouble(ctdValor));
        registro.setFlgUm(flgUm);
        registro.setNumeroMun(numeroMun);
        registro.setDscLote(dscLote);
        registro.setDscPropie(dscPropie);
        registro.setFlgTipomo(flgTipomo);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verLocales(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String localesID = JS.toBlank(request.getParameter("localesID"));
            if (!JS._numeroEntero(localesID)) throw new SimioException("RLC-VCA-187", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "locales-ver", modulo, plantilla);

            // delegar logica a servicios
            LocalesEntity registro = localesService.selectByID(JS.toInt(localesID));
            if (registro == null) throw new SimioException("RLC-VEC-188", "Local no encontrado");

            // cargar combo entidad
            List<ListaItem> itemsEntidad = getListaEntidades(getUsuarioSession(request));
            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            cargarComboCatalogoSeleccione(request, "cboUnidadMedida", catalogo.UNIDAD_MEDIDA, registro.getFlgUm());
            cargarComboCatalogoSeleccione(request, "cboTipoMoneda", catalogo.TIPO_MONEDA, registro.getFlgTipomo());
            cargarComboCatalogoSeleccione(request, "cboTipoPropiedad", catalogo.TIPO_PROPIEDAD, registro.getFlgPropie());
            cargarComboCatalogoSeleccione(request, "cboTipoDireccion", catalogo.TIPO_DIRECCION, registro.getFlgTipovi());
            cargarComboCatalogoSeleccione(request, "cboOficinaRegistral", catalogo.OFICINA_REGISTRAL, registro.getFlgTipore());
            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            String departamentoUbigeo = Constantes.COMBO_SELECCIONE_LABEL;
            String provinciaUbigeo = Constantes.COMBO_SELECCIONE_LABEL;
            String distritoUbigeo = Constantes.COMBO_SELECCIONE_LABEL;

            if (registro.getCodigoUbigeo() != null) {

                if (registro.getCodigoUbigeo().length() >= 6) {

                    departamentoUbigeo = registro.getCodigoUbigeo().substring(0, 2);
                    provinciaUbigeo = registro.getCodigoUbigeo().substring(0, 4);
                    distritoUbigeo = registro.getCodigoUbigeo().substring(0, 6);

                }
            }

            /* cargando combo departamento */
            UbigeoEntity filtroDepartamento = new UbigeoEntity();
            List<ListaItem> itemsDepartamento = ubigeoService.selectDepartamentoCombo(filtroDepartamento);
            ComboWeb comboWebDepartamento = new ComboWeb(itemsDepartamento);

            if (CollectionUtils.isNotEmpty(itemsDepartamento)) {
                comboWebDepartamento.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }
            comboWebDepartamento.setIdSeleccionado(departamentoUbigeo);
            request.setAttribute("cboDepartamento", comboWebDepartamento);

            /* cargando combo provincia */
            UbigeoEntity filtroProvincia = new UbigeoEntity();
            filtroProvincia.setCodigoUbigeo(departamentoUbigeo + "%");
            filtroProvincia.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsProvincia = ubigeoService.selectProvinciaCombo(filtroProvincia);
            ComboWeb comboWebProvincia = new ComboWeb(itemsProvincia);

            if (CollectionUtils.isNotEmpty(itemsProvincia)) {
                comboWebProvincia.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }
            comboWebProvincia.setIdSeleccionado(provinciaUbigeo);
            request.setAttribute("cboProvincia", comboWebProvincia);

            /* cargando combo distrito */
            UbigeoEntity filtroDistrito = new UbigeoEntity();
            filtroDistrito.setCodigoUbigeo(provinciaUbigeo + "%");
            filtroDistrito.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> itemsDistrito = ubigeoService.selectDistritoCombo(filtroDistrito);
            ComboWeb comboWebDistrito = new ComboWeb(itemsDistrito);

            if (CollectionUtils.isNotEmpty(itemsDistrito)) {
                comboWebDistrito.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }
            comboWebDistrito.setIdSeleccionado(distritoUbigeo);
            request.setAttribute("cboDistrito", comboWebDistrito);

            /* Seleccionamos por ID */
            if (JS._numeroEntero(registro.getDscCtactb())) {

                CuentaEntity itemCuenta = cuentaService.selectByID(Integer.parseInt(registro.getDscCtactb()));

                if (itemCuenta != null) {

                    CuentaEntity filtro = new CuentaEntity();

                    filtro.setTipoCta(catalogo.CUENTA_TIPOCUENTA_INMUEBLE);
                    filtro.setUsoCta(itemCuenta.getUsoCta());
                    filtro.setFlagCta(catalogo.CUENTA_FLAGCUENTA_VERDADERO);
                    filtro.setIndDel(Constantes.REGISTRO_ACTIVO);

                    List<ListaItem> itemsCuenta = cuentaService.selectCuentaCombo(filtro);
                    ComboWeb comboWebCuenta = new ComboWeb(itemsCuenta);

                    if (CollectionUtils.isNotEmpty(itemsCuenta)) {
                        comboWebCuenta.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
                    }

                    comboWebCuenta.setIdSeleccionado(registro.getDscCtactb());
                    request.setAttribute("cboCuentas", comboWebCuenta);

                    cargarComboCatalogoSeleccione(request, "cboTipoCuenta", catalogo.TIPO_CUENTA, itemCuenta.getUsoCta());
                }
            }

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RLC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView cargarCuentas(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String tipoCuenta = request.getParameter("cbo_tipo_cuenta");
            String formulario = JS.toBlank(request.getParameter("formulario"));

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            CuentaEntity filtro = new CuentaEntity();

            filtro.setTipoCta(catalogo.CUENTA_TIPOCUENTA_INMUEBLE);
            filtro.setUsoCta(tipoCuenta);
            filtro.setFlagCta(catalogo.CUENTA_FLAGCUENTA_VERDADERO);
            filtro.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<ListaItem> items = cuentaService.selectCuentaCombo(filtro);

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

    public ModelAndView cargarProvincia(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String departamento = request.getParameter("cbo_departamento");
            String formulario = JS.toBlank(request.getParameter("formulario"));

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            UbigeoEntity filtro = new UbigeoEntity();

            filtro.setCodigoUbigeo(departamento + "%");
            filtro.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<ListaItem> items = ubigeoService.selectProvinciaCombo(filtro);

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

    public ModelAndView cargarDistrito(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String provincia = request.getParameter("cbo_provincia");
            String formulario = JS.toBlank(request.getParameter("formulario"));

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            UbigeoEntity filtro = new UbigeoEntity();
            filtro.setCodigoUbigeo(provincia + "%");
            filtro.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<ListaItem> items = ubigeoService.selectDistritoCombo(filtro);

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
}
