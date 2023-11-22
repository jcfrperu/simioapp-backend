package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.DateTime;
import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.servicios.InventarioService;
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

public class RegistroInventarioController extends BaseController {

    private static final Logger log = Logger.getLogger(RegistroInventarioController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "registro-inventario";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    public ModelAndView buscarInventario(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros
            String inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));
            String nombre = JS.toUpperBlank(request.getParameter("txt_nombre"));
            String fechaApertura = JS.toUpperBlank(request.getParameter("fec_fechaApertura"));
            String fechaCierre = JS.toUpperBlank(request.getParameter("fec_fechaCierre"));
            String estado = JS.toUpperBlank(request.getParameter("cbo_entidad"));
            String entidadID = JS.toUpperBlank(request.getParameter("int_entidadID"));
            String indDel = JS.toUpperBlank(request.getParameter("cbo_indDel"));

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaPage(model, "inventario-buscar-grilla", modulo, plantilla);

            // delegar logica a servicios
            HashMap<String, Object> params = new HashMap<>();

            if (JS._numeroEntero(inventarioID)) params.put("inventarioID", JS.toInt(inventarioID));
            if (!JS._vacio(nombre)) params.put("nombre", "%" + nombre + "%");
            if (JS._fecha(fechaApertura)) params.put("fechaApertura", JS.toDate(fechaApertura));
            if (JS._fecha(fechaCierre)) params.put("fechaCierre", JS.toDate(fechaCierre));
            if (!JS._vacio(estado)) params.put("estado", estado);
            if (JS._numeroEntero(entidadID)) params.put("entidadID", JS.toInt(entidadID));
            if (!JS._vacio(indDel)) params.put("indDel", indDel);

            List<Map<String, Object>> listaInventario = inventarioService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaInventario);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RIC-BCA-311", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    private InventarioEntity buscarRegistroPorUnique(String nombre, Integer entidadID) throws Exception {

        InventarioEntity filtro = new InventarioEntity();

        filtro.setNombre(nombre);
        filtro.setEntidadID(entidadID);

        List<InventarioEntity> resultados = inventarioService.select(filtro);

        if (CollectionUtils.isNotEmpty(resultados)) {
            return resultados.get(0);
        }

        return null;
    }

    public ModelAndView eliminarInventario(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String inventarioID = JS.toBlank(request.getParameter("inventarioID"));
            if (!JS._numeroEntero(inventarioID)) {
                throw new SimioException("RIC-ECA-312", "Argumento ilegal del request");
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            InventarioEntity entity = new InventarioEntity();

            entity.setInventarioID(JS.toInt(inventarioID));

            inventarioService.deleteConBienes(entity);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RIC-ELI-313", dataJSON, sos);
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
                throw new SimioException("RIC-GED-314", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            InventarioEntity registro = (InventarioEntity) result.get("registro");

            inventarioService.updateFechasAperturaCierre(registro);

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RIC-GUA-315", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }


    private void limpiarSession(HttpServletRequest request) throws Exception {

        // limpiar los atributos de session
        // setSessionAttribute(request, Constantes.ADMIN_REGISTRO_CATEGORIAS.SESSION_XXXX, null);
    }

    public ModelAndView mostrarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "inventario-buscar", modulo, plantilla);

            // delegar logica a servicios
            cargarComboCatalogoTodos(request, "cboEstados", catalogo.INDEL, Constantes.REGISTRO_ACTIVO);

            cargarComboCatalogoTodos(request, "cboEstadoInventario", catalogo.ESTADO_INVENTARIO);

            List<ListaItem> cboEntidad = getListaEntidades(getUsuarioSession(request));
            cargarCombo(request, "cboEntidad", cboEntidad, false, false);

            HashMap<String, Object> params = new HashMap<>();

            params.put("indDel", Constantes.REGISTRO_ACTIVO);

            List<Map<String, Object>> listaInventario = inventarioService.selectByMapGrilla(params);

            // poblar modelo
            model.put("lista", listaInventario);

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

    public ModelAndView mostrarEditar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String inventarioID = JS.toBlank(request.getParameter("inventarioID"));
            if (!JS._numeroEntero(inventarioID)) {
                throw new SimioException("RIC-MED-318", "Argumento ilegal del request");
            }

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "inventario-editar", modulo, plantilla);

            // delegar logica a servicios
            InventarioEntity registro = inventarioService.selectByID(JS.toInt(inventarioID));
            if (registro == null) {
                throw new SimioException("RIC-MED-319", "Inventario no encontrado");
            }

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            cargarComboCatalogoSeleccione(request, "cboEstadoInventario", catalogo.ESTADO_INVENTARIO, registro.getEstado());

            // cargar combo entidad
            List<ListaItem> itemsEntidad = getListaEntidades(getUsuarioSession(request));

            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, registro.getEntidadID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RIC-MED-212", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }


    public ModelAndView validarBuscar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));
            String fechaApertura = JS.toUpperBlank(request.getParameter("fec_fechaApertura"));
            String fechaCierre = JS.toUpperBlank(request.getParameter("fec_fechaCierre"));

            Map<String, Object> result = new HashMap<>();

            // validacion para inventarioID
            if (!JS._vacio(inventarioID)) {

                String msg = JS._numeroEnteroNoNegativoString(inventarioID, "Código");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "int_inventarioID");
                    result.put("msgError", msg);

                    throw new SimioException("RIC-VBU-320", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para fechaApertura
            if (!JS._vacio(fechaApertura)) {

                String msg = JS._fechaString(fechaApertura, "Fecha Apertura");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "fec_fechaApertura");
                    result.put("msgError", msg);

                    throw new SimioException("RIC-VBU-320", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            // validacion para fechaCierre
            if (!JS._vacio(fechaCierre)) {

                String msg = JS._fechaString(fechaCierre, "Fecha Cierre");
                if (!JS._vacio(msg)) {
                    result.put("campoError", "fec_fechaCierre");
                    result.put("msgError", msg);

                    throw new SimioException("RIC-VBU-320", msg, JExceptionEnum.VALIDACION, result);
                }
            }

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("RIC-VAB-321", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private Map<String, Object> validarEditar(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));
        String nombre = JS.toUpperBlank(request.getParameter("txt_nombre"));
        String fechaApertura = JS.toUpperBlank(request.getParameter("fec_fechaApertura"));
        String fechaCierre = JS.toUpperBlank(request.getParameter("fec_fechaCierre"));
        String estado = JS.toUpperBlank(request.getParameter("cbo_estadoInventario"));
        String entidadID = JS.toUpperBlank(request.getParameter("int_entidadID"));

        // NOTA: campo indDel se ignora explicitamente, para evitar borrar sus bienes de inventario_bien y apertura_bien

        Map<String, Object> result = new HashMap<>();

        // validar inventarioID
        String msg = JS._numeroEnteroString(inventarioID, "Código");
        if (!JS._vacio(msg)) {

            result.put("campoError", "int_inventarioID");
            result.put("msgError", msg);

            return result;
        }

        // validar entidadID
        if (JS._equiv(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_entidadID");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar nombre
        msg = JS._campoNoVacio(nombre, "Nombre");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombre");
            result.put("msgError", msg);

            return result;
        }

        // validar combo estado inventario
        if (JS._equiv(estado, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "cbo_estadoInventario");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar fechaApertura
        if (!JS._vacio(fechaApertura)) {
            msg = JS._fechaString(fechaApertura, "Fecha Apertura");
            if (!JS._vacio(msg)) {

                result.put("campoError", "fec_fechaApertura");
                result.put("msgError", msg);

                return result;
            }
        }


        // validar fechaCierre
        if (!JS._vacio(fechaCierre)) {
            msg = JS._fechaString(fechaCierre, "Fecha Cierre");
            if (!JS._vacio(msg)) {

                result.put("campoError", "fec_fechaCierre");
                result.put("msgError", msg);

                return result;
            }
        }

        // validar rango de fechas
        if (!JS._vacio(fechaApertura) && !JS._vacio(fechaCierre)) {

            DateTime fechaAperturaDT = DateTime.getInstancia(fechaApertura);
            DateTime fechaCierreDT = DateTime.getInstancia(fechaCierre);

            if (!fechaCierreDT.despuesIgualQue(fechaAperturaDT)) {

                result.put("campoError", "fec_fechaCierre");
                result.put("msgError", "Fecha de Cierre debe ser posterior o igual a Fecha de Apertura");

                return result;
            }
        }

        // validar que no exista otro igual
        InventarioEntity found = buscarRegistroPorUnique(nombre, JS.toInt(entidadID));
        if (found != null && !JS._equiv(found.getInventarioID(), JS.toInt(inventarioID))) {
            result.put("campoError", "general");
            result.put("msgError", "Inventario ya fue registrado");

            return result;
        }

        // armar entity
        InventarioEntity registro = new InventarioEntity();

        registro.setEntidadID(JS.toInt(entidadID));
        registro.setEstado(estado);
        registro.setFechaApertura(JS.toDate(fechaApertura));
        registro.setFechaCierre(JS.toDate(fechaCierre)); // sino es fecha retorna null
        registro.setInventarioID(JS.toInt(inventarioID)); // sino es fecha retorna null
        registro.setNombre(nombre);
        registro.setIndDel(null); // ignorado explicitamente para que no haga nada con el update

        setCamposAuditoria(request, registro, false);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView verInventario(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // recoger parametros y validar
            String inventarioID = JS.toBlank(request.getParameter("inventarioID"));
            if (!JS._numeroEntero(inventarioID)) {
                throw new SimioException("RIC-VCA-322", "Argumento ilegal del request");
            }

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "inventario-ver", modulo, plantilla);

            // delegar logica a servicios
            InventarioEntity registro = inventarioService.selectByID(JS.toInt(inventarioID));
            if (registro == null) {
                throw new SimioException("RIC-VEC-323", "Inventario no encontrado");
            }

            cargarComboCatalogoSeleccione(request, "cboEstados", catalogo.INDEL, registro.getIndDel());

            cargarComboCatalogoSeleccione(request, "cboEstadoInventario", catalogo.ESTADO_INVENTARIO, registro.getEstado());

            List<ListaItem> cboEntidad = getListaEntidades(getUsuarioSession(request));
            cargarComboSeleccione(request, "cboEntidad", cboEntidad, registro.getEntidadID());

            // poblar el modelo
            model.put("registro", registro);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("RIC-VCA-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }
}
