package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.DateTime;
import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
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

public class AperturaController extends BaseController {

    private static final Logger log = Logger.getLogger(AperturaController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-apertura";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    public ModelAndView mostrarPanelApertura(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "panel-apertura", modulo, plantilla);

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);

            List<ListaItem> itemsEntidad = getListaEntidades(usuarioSession);

            cargarCombo(request, "cboEntidad", itemsEntidad, false, false, JS.toInt(usuarioSession.getCodigoEntidad()));

            model.put("today", DateTime.getToday().getFechaString());

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


    private Map<String, Object> validarAperturarInventario(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String nombre = JS.toUpperBlank(request.getParameter("txt_nombre"));
        String entidadID = JS.toUpperBlank(request.getParameter("int_entidadID"));

        Map<String, Object> result = new HashMap<>();

        // validar entidadID
        if (JS._equiv(entidadID, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "int_entidadID");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        // validar nombre
        String msg = JS._campoNoVacio(nombre, "Nombre");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_nombre");
            result.put("msgError", msg);

            return result;
        }

        // validar que no exista otro igual
        InventarioEntity found = buscarRegistroPorUnique(nombre, JS.toInt(entidadID));
        if (found != null) {
            result.put("campoError", "txt_nombre");
            result.put("msgError", "Inventario ya fue registrado");

            return result;
        }

        List<InventarioEntity> inventariosAbiertos = buscarInventariosAbiertos(JS.toInt(entidadID));
        if (CollectionUtils.isNotEmpty(inventariosAbiertos)) {
            result.put("campoError", "general");
            result.put("msgError", "Existe otro inventario aperturado (" + inventariosAbiertos.get(0).getNombre() + "). No puede aperturar otro al menos que cierre o elimine el primero");

            return result;
        }

        // armar entity
        InventarioEntity registro = new InventarioEntity();

        registro.setEntidadID(JS.toInt(entidadID));
        registro.setEstado(catalogo.ESTADO_INVENTARIO_ABIERTO);
        registro.setFechaApertura(DateTime.getToday().toDate());
        registro.setFechaCierre(null);
        registro.setNombre(nombre);
        registro.setIndDel(Constantes.REGISTRO_ACTIVO);

        setCamposAuditoria(request, registro, true);

        // setearlo al mapa resultado
        result.put("registro", registro);

        return result;
    }

    public ModelAndView aperturarInventario(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            Map<String, Object> result = validarAperturarInventario(request);

            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("API-GED-314", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            // delegar logica a servicios
            InventarioEntity registro = (InventarioEntity) result.get("registro");

            inventarioService.aperturarInventario(registro, getUsuarioSession(request));

            // limpiar session
            limpiarSession(request);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("API-GUA-315", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
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

    private List<InventarioEntity> buscarInventariosAbiertos(Integer entidadID) throws Exception {

        InventarioEntity filtro = new InventarioEntity();

        filtro.setEntidadID(entidadID);
        filtro.setIndDel(Constantes.REGISTRO_ACTIVO);
        filtro.setEstado(catalogo.ESTADO_INVENTARIO_ABIERTO);

        return inventarioService.select(filtro);
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

    }
}
