package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.CatalogoBienEntity;
import com.simios.simioapp.dominio.entidades.ClaseEntity;
import com.simios.simioapp.dominio.entidades.GrupoEntity;
import com.simios.simioapp.servicios.CatalogoBienService;
import com.simios.simioapp.servicios.ClaseService;
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

public class RegistroCatalogoBienController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroCatalogoBienController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-catalogobien";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("catalogoBienService")
    private CatalogoBienService catalogoBienService;

    @Autowired
    @Qualifier("grupoService")
    private GrupoService grupoService;

    @Autowired
    @Qualifier("claseService")
    private ClaseService claseService;

    public ModelAndView buscarCatalogoBien(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String codigoBien = JS.toUpperBlank(request.getParameter("txt_codigoBien"));
            String denominacion = JS.toUpperBlank(request.getParameter("txt_denominacion"));
            String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
            String claseID = JS.toBlank(request.getParameter("int_claseID"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "catalogobien-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (!JS._vacio(codigoBien)) params.put("codigoBien", "%" + codigoBien + "%");
            if (!JS._vacio(denominacion)) params.put("denominacion", "%" + denominacion + "%");
            if (JS._numeroEntero(grupoID)) params.put("grupoID", JS.toInt(grupoID));
            if (JS._numeroEntero(claseID)) params.put("claseID", JS.toInt(claseID));
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaCatalogoBien = catalogoBienService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaCatalogoBien);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RCC-BCA-124", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private CatalogoBienEntity buscarRegistroPorUnique(String codigoBien) throws Exception {

        CatalogoBienEntity filtro = new CatalogoBienEntity();

        filtro.setCodigoBien(codigoBien);

        List<CatalogoBienEntity> resultados = catalogoBienService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;

    }

    public ModelAndView eliminarCatalogoBien(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String catalogoBienID = JS.toUpperBlank(request.getParameter("catalogoBienID"));
            if (!JS._numeroEntero(catalogoBienID))
                throw new SimioException("RCC-ECA-125", "Argumento ilegal del request");

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CatalogoBienEntity entity = new CatalogoBienEntity();

            entity.setCatalogoBienID(JS.toInt(catalogoBienID));

            catalogoBienService.delete(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-ELI-126", dataJSON, sos);
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
                throw new SimioException("RCC-GED-127", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CatalogoBienEntity registro = (CatalogoBienEntity) result.get("registro");

            catalogoBienService.update(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GUA-128", dataJSON, sos);
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
                throw new SimioException("RCC-GNU-129", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            CatalogoBienEntity registro = (CatalogoBienEntity) result.get("registro");

            catalogoBienService.insert(registro);

            // poblar modelo
            model.put("idGenerado", String.valueOf(registro.getCatalogoBienID()));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-GNU-130", dataJSON, sos);
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
            setVistaTemplate(model, "catalogobien-buscar", modulo, plantilla);

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

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            // poblar modelo
            model.put("lista", null);

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
            String catalogoBienID = JS.toUpperBlank(request.getParameter("catalogoBienID"));
            if (!JS._numeroEntero(catalogoBienID))
                throw new SimioException("RCC-MED-131", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "catalogobien-editar", modulo, plantilla);

            // delegar logica a servicios
            CatalogoBienEntity registro = catalogoBienService.selectByID(JS.toInt(catalogoBienID));
            if (registro == null) throw new SimioException("RCC-MED-132", "Catálogo bien no encontrada");

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
            setVistaTemplate(model, "catalogobien-nuevo", modulo, plantilla);

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
            String msgError = handleMsgError("RCC-MNU-017", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String codigoBien = JS.toUpperBlank(request.getParameter("txt_codigoBien"));
            String denominacion = JS.toUpperBlank(request.getParameter("txt_denominacion"));
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

            // por lo menos debe ingresar codigo de bien o denominacion
            if (JS._vacio(codigoBien) && JS._vacio(denominacion)) {

                String msg = "Debe ingresar al menos Código Bien y/o Denominación ";

                result.put("campoError", "general");
                result.put("msgError", msg);

                throw new SimioException("RGC-VBU-321", msg, JExceptionEnum.VALIDACION, result);

            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RCC-VAB-134", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String catalogoBienID = JS.toUpperBlank(request.getParameter("int_catalogoBienID"));
        String codigoBien = JS.toUpperBlank(request.getParameter("txt_codigoBien"));
        String denominacion = JS.toUpperBlank(request.getParameter("txt_denominacion"));
        String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
        String claseID = JS.toBlank(request.getParameter("int_claseID"));
        String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));


        Map<String, Object> result = new HashMap<>();

        // validar catalogoBienID
        String msg = JS._campoNoVacio(catalogoBienID, "Código Bien");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_catalogoBienID");
            result.put("msgError", msg);

            return result;
        }

        msg = JS._numeroEnteroString(catalogoBienID, "Código Bien");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_catalogoBienID");
            result.put("msgError", msg);

            return result;
        }

        // validar codigoBien
        msg = JS._campoNoVacio(codigoBien, "Código Bien");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoBien");
            result.put("msgError", msg);

            return result;
        }

        // validar denominacion
        msg = JS._campoNoVacio(denominacion, "Denominación");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_denominacion");
            result.put("msgError", msg);

            return result;
        }

        // validar grupo
        msg = JS._campoNoVacio(grupoID, "Grupo");
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

        // validar clase
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

        // validar combo indDel
        if (JS._equiv(indDel, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_indDel");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar que no exista otro igual
        CatalogoBienEntity found = buscarRegistroPorUnique(codigoBien);
        if (found != null && !JS._equiv(found.getCatalogoBienID(), JS.toInt(catalogoBienID))) {
            result.put("campoError", "general");
            result.put("msgError", "Catálogo bien ya fue registrado");

            return result;
        }

        // armar entity
        CatalogoBienEntity registro = new CatalogoBienEntity();

        registro.setCatalogoBienID(JS.toInt(catalogoBienID));
        registro.setClaseID(JS.toInt(claseID));
        registro.setCodigoBien(codigoBien);
        registro.setDenominacion(denominacion);
        registro.setGrupoID(JS.toInt(grupoID));
        registro.setIndDel(indDel);

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    private Map<String, Object> validarGuardar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String codigoBien = JS.toUpperBlank(request.getParameter("txt_codigoBien"));
        String denominacion = JS.toUpperBlank(request.getParameter("txt_denominacion"));
        String grupoID = JS.toBlank(request.getParameter("int_grupoID"));
        String claseID = JS.toBlank(request.getParameter("int_claseID"));


        Map<String, Object> result = new HashMap<>();

        // validar codigoBien
        String msg = JS._campoNoVacio(codigoBien, "Código Bien");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_codigoBien");
            result.put("msgError", msg);

            return result;
        }

        // validar denominacion
        msg = JS._campoNoVacio(denominacion, "Denominación");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_denominacion");
            result.put("msgError", msg);

            return result;
        }

        // validar grupo
        msg = JS._campoNoVacio(grupoID, "Grupo");
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

        // validar clase
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

        // validar que no exista otro igual
        CatalogoBienEntity found = buscarRegistroPorUnique(codigoBien);
        if (found != null) {
            result.put("campoError", "general");
            result.put("msgError", "Catálogo bien ya fue registrado");

            return result;
        }

        // armar entity
        CatalogoBienEntity registro = new CatalogoBienEntity();

        registro.setClaseID(JS.toInt(claseID));
        registro.setCodigoBien(codigoBien);
        registro.setDenominacion(denominacion);
        registro.setGrupoID(JS.toInt(grupoID));
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verCatalogoBien(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String catalogoBienID = JS.toBlank(request.getParameter("catalogoBienID"));
            if (!JS._numeroEntero(catalogoBienID))
                throw new SimioException("RCC-VCA-135", "Argumento ilegal del request");

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "catalogobien-ver", modulo, plantilla);

            // delegar logica a servicios
            CatalogoBienEntity registro = catalogoBienService.selectByID(JS.toInt(catalogoBienID));
            if (registro == null) throw new SimioException("RCC-VEC-136", "Catálogo bien no encontrada");

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
            String msgError = handleMsgError("RCC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
