package com.simios.simioapp.controladores.base;

import com.jcfr.utiles.DateTime;
import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.files.JFUtil;
import com.jcfr.utiles.listas.JLUtil;
import com.jcfr.utiles.string.JSUtil;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.util.HttpUtil;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;
import com.simios.simioapp.servicios.CatalogoService;
import com.simios.simioapp.servicios.EntidadService;
import com.simios.simioapp.servicios.UsuarioService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BaseController extends MultiActionController {

    private static final Logger log = Logger.getLogger(BaseController.class.getName());

    protected static final JSUtil JS = JSUtil.JSUtil;
    protected static final JFUtil JF = JFUtil.JFUtil;
    protected static final JLUtil JL = JLUtil.JLUtil;

    @Autowired
    @Qualifier("catalogoService")
    protected CatalogoService catalogoService;

    @Autowired
    @Qualifier("usuarioService")
    protected UsuarioService usuarioService;

    @Autowired
    @Qualifier("entidadService")
    protected EntidadService entidadService;

    protected HttpSession invalidarSession(HttpServletRequest request, boolean create) {

        // operacion safely y quietly
        HttpSession session = null;

        try {

            // con solo llamar a esta url crea una session si no la hay, e invalida la existente
            session = request == null ? null : request.getSession(create);
            if (session != null) {

                try {
                    session.setAttribute(Constantes.USUARIO_SESSION_NAME, null);
                } catch (Exception sos) {
                }

                try {
                    session.removeAttribute(Constantes.USUARIO_SESSION_NAME);
                } catch (Exception sos) {
                }

                try {
                    session.invalidate();
                } catch (Exception sos) {
                }
            }

        } catch (Exception ignored) {
            // no se puede hacer nada aqui
        }

        return session;
    }

    protected UsuarioSession getUsuarioSession(HttpServletRequest request) throws Exception {

        UsuarioSession usuarioSession = (UsuarioSession) getSessionAttribute(request, Constantes.USUARIO_SESSION_NAME);

        if (usuarioSession == null) {
            throw new SimioException("AC-GUS-918", "Usuario no tiene credenciales de sesión válidas");
        }

        return usuarioSession;
    }

    protected List<ListaItem> getListaEntidades(UsuarioSession usuarioSession) throws Exception {

        if (usuarioSession == null) throw new IllegalArgumentException("usuarioSession cannot be null");

        EntidadEntity filtro = new EntidadEntity();

        filtro.setIndDel(Constantes.REGISTRO_ACTIVO);
        filtro.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));

        return entidadService.listarEntidades(filtro);
    }

    public ModelAndView cargarTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return handleCargar(request, true);
    }

    public ModelAndView cargarPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return handleCargar(request, false);
    }

    protected Object getSessionAttribute(HttpServletRequest request, String name) {
        return WebUtils.getSessionAttribute(request, name);
    }

    protected void setSessionAttribute(HttpServletRequest request, String name, Object value) {
        WebUtils.setSessionAttribute(request, name, value);
    }

    protected String handleMsgError(String codigoError, Exception sos) {

        return handleMsgError(true, codigoError, sos);
    }

    protected String handleMsgError(boolean incluirCodigoError, String codigoError, Exception sos) {

        return HttpUtil.handleMsgError(incluirCodigoError, codigoError, sos);
    }

    protected String handleJSONError(String codigoError, DataJsonBean dataJsonBean, Exception sos) {

        return HttpUtil.handleJSONError(codigoError, dataJsonBean, sos);
    }

    protected String getHeaderAcceso(HttpServletResponse response) {

        return HttpUtil.getHeaderAcceso(response);
    }

    protected String getOrigenCliente(HttpServletRequest request) {
        String clientOrigin = request.getHeader("origin");

        return StringUtils.trimToEmpty(clientOrigin);
    }

    protected String getIPCliente(HttpServletRequest request) {

        return HttpUtil.getIPCliente(request);
    }

    protected ModelAndView handleJSONResponse(DataJsonBean dataJsonBean, HttpServletResponse response) {

        return HttpUtil.handleJSONResponse(dataJsonBean, response);
    }

    protected ModelAndView handleModelAndView(Map<String, Object> model) {
        // PRE: model cannot be null and must contain _next attribute
        String _nextView = MapUtils.getString(model, "_view");

        return new ModelAndView(_nextView, model);
    }

    protected ModelAndView handleErrorModelAndView(Map<String, Object> model, String msgError, String plantilla) {

        Map<String, Object> result = model;

        if (result == null) {
            result = new HashMap<>();
            // model.put("_view", plantilla);
        }

        result.put("_module", null);
        result.put("_msgError", msgError);

        setVistaTemplate(result, "error", null, plantilla);

        return handleModelAndView(result);
    }

    protected void setVistaTemplate(Map<String, Object> model, String pagina, String modulo, String plantilla) {
        setNavigationParams(model, pagina, modulo, plantilla, true);
    }

    protected void setVistaPage(Map<String, Object> model, String pagina, String modulo, String plantilla) {
        setNavigationParams(model, pagina, modulo, plantilla, false);
    }

    private void setNavigationParams(Map<String, Object> model, String pagina, String modulo, String plantilla, boolean useTemplateAsView) {

        // PRE: pagina no puede ser null, plantilla puede ser null pero solo si useTemplateAsView es false
        if (JS._vacio(pagina)) throw new IllegalArgumentException("pagina no puede ser null");
        if (useTemplateAsView && JS._vacio(plantilla)) {
            throw new IllegalArgumentException("plantilla no puede ser null");
        }

        String _view;
        String _page = JS.toBlank(pagina);
        String _module = JS.toBlank(modulo);
        String _template = JS.toBlank(plantilla);

        if (useTemplateAsView) {
            _view = _template;
        } else {
            if (!JS._vacio(_template)) {
                if (!JS._vacio(_module)) {
                    _view = _template + "/" + _module + "/" + _page;
                } else {
                    _view = _template + "/" + _page;
                }
            } else {
                // template puede ser vacio
                if (!JS._vacio(_module)) {
                    _view = _module + "/" + _page;
                } else {
                    _view = _page;
                }
            }
        }

        // setear atributos de navegacion, vista es la siguiente vista
        if (!JS._vacio(_page)) model.put("_page", _page);
        if (!JS._vacio(_module)) model.put("_module", _module);
        if (!JS._vacio(_template)) model.put("_template", _template);
        if (!JS._vacio(_view)) model.put("_view", _view);
    }

    private ModelAndView handleCargar(HttpServletRequest request, boolean useTemplateAsView) throws Exception {

        // INFO: solo busca los atributos en el request y armar el _nextView
        String _page = JS.toBlank(request.getParameter("_page"));
        String _module = JS.toBlank(request.getParameter("_module"));
        String _template = JS.toBlank(request.getParameter("_template"));

        HashMap<String, Object> model = new HashMap<>();

        // usar page
        setNavigationParams(model, _page, _module, _template, useTemplateAsView);

        return handleModelAndView(model);
    }

    protected DateTime setCamposAuditoria(HttpServletRequest request, AuditoriaFields entity, boolean insert) throws Exception {

        DateTime now = DateTime.getNow();

        UsuarioSession usuarioSession = getUsuarioSession(request);

        entity.setFechaAct(now.toDate());
        if (usuarioSession != null) entity.setUsuAct(usuarioSession.getUsuarioSessionID());

        if (insert) {
            entity.setFechaReg(now.toDate());
            if (usuarioSession != null) entity.setUsuReg(usuarioSession.getUsuarioSessionID());
        }

        return now;
    }

    private ComboWeb crearCombo(List<ListaItem> items, boolean agregerItemSeleccione, boolean agregarItemTodos) throws Exception {

        ComboWeb comboWeb = new ComboWeb(items);

        if (CollectionUtils.isNotEmpty(items)) {

            if (agregerItemSeleccione) {
                comboWeb.addItemSelIni(Constantes.COMBO_SELECCIONE_VALUE, Constantes.COMBO_SELECCIONE_LABEL);
            }

            if (agregarItemTodos) {
                comboWeb.addItemSelIni(Constantes.COMBO_TODOS_VALUE, Constantes.COMBO_TODOS_LABEL);
            }
        }

        return comboWeb;
    }

    protected ComboWeb cargarCombo(HttpServletRequest request, String nombreCombo, List<ListaItem> items, boolean agregerItemSeleccione, boolean agregarItemTodos) throws Exception {

        ComboWeb comboWeb = crearCombo(items, agregerItemSeleccione, agregarItemTodos);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboSINO(HttpServletRequest request, String nombreCombo, boolean agregerItemSeleccione, boolean agregarItemTodos) throws Exception {

        // mostrar todas las entidades
        List<ListaItem> items = new ArrayList<>();

        items.add(new ListaItem(Constantes.SI, StringUtils.upperCase(Constantes.SI)));
        items.add(new ListaItem(Constantes.NO, StringUtils.upperCase(Constantes.NO)));

        ComboWeb comboWeb = crearCombo(items, agregerItemSeleccione, agregarItemTodos);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarCombo(HttpServletRequest request, String nombreCombo, List<ListaItem> items, boolean agregerItemSeleccione, boolean agregarItemTodos, Object valorSeleccionado) throws Exception {

        ComboWeb comboWeb = crearCombo(items, agregerItemSeleccione, agregarItemTodos);

        comboWeb.setSelID(valorSeleccionado);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboSeleccione(HttpServletRequest request, String nombreCombo, List<ListaItem> items) throws Exception {

        ComboWeb comboWeb = crearCombo(items, true, false);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboSeleccione(HttpServletRequest request, String nombreCombo, List<ListaItem> items, Object valorSeleccionado) throws Exception {

        ComboWeb comboWeb = crearCombo(items, true, false);

        comboWeb.setSelID(valorSeleccionado);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboTodos(HttpServletRequest request, String nombreCombo, List<ListaItem> items) throws Exception {

        ComboWeb comboWeb = crearCombo(items, false, true);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboTodos(HttpServletRequest request, String nombreCombo, List<ListaItem> items, Object valorSeleccionado) throws Exception {

        ComboWeb comboWeb = crearCombo(items, false, true);

        comboWeb.setSelID(valorSeleccionado);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboCatalogo(HttpServletRequest request, String nombreCombo, String catalogo, boolean agregerItemSeleccione, boolean agregarItemTodos) throws Exception {

        // trae los items de un catalogo
        List<ListaItem> items = catalogoService.selectCatalogoCombo(catalogo, true, true);

        // y crea el combo segun los items adicionales
        ComboWeb comboWeb = crearCombo(items, agregerItemSeleccione, agregarItemTodos);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboCatalogoSeleccione(HttpServletRequest request, String nombreCombo, String catalogo) throws Exception {

        // trae los items de un catalogo
        List<ListaItem> items = catalogoService.selectCatalogoCombo(catalogo, true, true);

        // y crea el combo segun los items adicionales
        ComboWeb comboWeb = crearCombo(items, true, false);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboCatalogoSeleccione(HttpServletRequest request, String nombreCombo, String catalogo, Object valorSeleccionado) throws Exception {

        // trae los items de un catalogo
        List<ListaItem> items = catalogoService.selectCatalogoCombo(catalogo, true, true);

        // y crea el combo segun los items adicionales
        ComboWeb comboWeb = crearCombo(items, true, false);

        // seleccionar un valor
        comboWeb.setSelID(valorSeleccionado);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboCatalogoTodos(HttpServletRequest request, String nombreCombo, String catalogo) throws Exception {

        // trae los items de un catalogo
        List<ListaItem> items = catalogoService.selectCatalogoCombo(catalogo, true, true);

        // y crea el combo segun los items adicionales
        ComboWeb comboWeb = crearCombo(items, false, true);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarComboCatalogoTodos(HttpServletRequest request, String nombreCombo, String catalogo, Object valorSeleccionado) throws Exception {

        // trae los items de un catalogo
        List<ListaItem> items = catalogoService.selectCatalogoCombo(catalogo, true, true);

        // y crea el combo segun los items adicionales
        ComboWeb comboWeb = crearCombo(items, false, true);

        comboWeb.setSelID(valorSeleccionado);

        request.setAttribute(nombreCombo, comboWeb);

        return comboWeb;
    }

    protected ComboWeb cargarCombo(String catalogo) throws Exception {

        List<ListaItem> items = catalogoService.selectCatalogoCombo(catalogo, true, true);

        // y crea el combo segun los items adicionales
        return crearCombo(items, false, false);
    }

}
