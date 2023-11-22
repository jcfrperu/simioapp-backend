package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.negocio.seguridad.TokenData;
import com.simios.simioapp.negocio.seguridad.TokenGenerator;
import com.simios.simioapp.servicios.InventarioBienService;
import com.simios.simioapp.servicios.InventarioService;
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

public class GeneracionTokenController extends BaseController {

    private static final Logger log = Logger.getLogger(GeneracionTokenController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-generar-token";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    @Autowired
    @Qualifier("inventarioBienService")
    private InventarioBienService inventarioBienService;


    public ModelAndView mostrarPanelToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            UsuarioSession usuarioSession = getUsuarioSession(request);

            // carga combo entidad
            List<ListaItem> cboEntidad = getListaEntidades(getUsuarioSession(request));

            cargarCombo(request, "cboEntidad", cboEntidad, false, false);

            // carga combo de inventarios activos
            InventarioEntity filtroInventario = new InventarioEntity();

            filtroInventario.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroInventario.setEstado(catalogo.ESTADO_INVENTARIO_ABIERTO); // solo los inventarios abiertos
            filtroInventario.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));

            List<ListaItem> itemsInventario = inventarioService.listarInventarios(filtroInventario);

            // si tiene un solo item, marcarlo por default
            if (CollectionUtils.isNotEmpty(itemsInventario) && itemsInventario.size() == 1) {
                cargarComboSeleccione(request, "cboInventario", itemsInventario, itemsInventario.get(0).getId());
            } else {
                cargarComboSeleccione(request, "cboInventario", itemsInventario);
            }

            // registro
            TokenData registro = new TokenData();

            registro.setEntidad(usuarioSession.getCodigoEntidad());
            registro.setUsuario(usuarioSession.getUsuarioSessionID());
            registro.setInventario(StringUtils.EMPTY);

            model.put("registro", registro);

            setVistaTemplate(model, "panel-token", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("GTC-MPT-423", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

    private Map<String, Object> validarGenerarToken(HttpServletRequest request) throws Exception {

        // recoger parametros y validar
        String txt_usuario = StringUtils.trimToEmpty(request.getParameter("txt_usuario"));
        String txt_entidad = StringUtils.trimToEmpty(request.getParameter("txt_entidad"));
        String txt_inventario = StringUtils.trimToEmpty(request.getParameter("txt_inventario"));

        String txt_diasValidez = StringUtils.trimToEmpty(request.getParameter("txt_diasValidez"));
        String txt_horasValidez = StringUtils.trimToEmpty(request.getParameter("txt_horasValidez"));
        String txt_minutosValidez = StringUtils.trimToEmpty(request.getParameter("txt_minutosValidez"));

        Map<String, Object> result = new HashMap<>();

        // validacion txt_usuario
        String msg = JS._campoNoVacio(txt_usuario, "Usuario");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_usuario");
            result.put("msgError", msg);

            return result;
        }

        // validacion txt_entidad
        msg = JS._numeroEnteroString(txt_entidad, "Entidad");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_entidad");
            result.put("msgError", msg);

            return result;
        }

        // validacion txt_inventario
        if (JS._equiv(txt_inventario, Constantes.COMBO_SELECCIONE_VALUE)) {

            result.put("campoError", "txt_inventario");
            result.put("msgError", "Seleccione un elemento");

            return result;
        }

        msg = JS._numeroEnteroString(txt_inventario, "Inventario");
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_inventario");
            result.put("msgError", msg);

            return result;
        }

        // validar que todos los items del inventario esten asignado
        String msgValidaAsignacion = validarQueTodosBienesEstenAsignados(JS.toInt(txt_entidad), JS.toInt(txt_inventario));

        if (StringUtils.isNotBlank(msgValidaAsignacion)) {

            result.put("campoError", "txt_inventario");
            result.put("msgError", msgValidaAsignacion);

            return result;
        }

        // validacion txt_diasValidez
        msg = JS._numeroEnteroString(txt_diasValidez, "Días validez", 0, 29);
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_diasValidez");
            result.put("msgError", msg);

            return result;
        }

        // validacion txt_horasValidez
        msg = JS._numeroEnteroString(txt_horasValidez, "Horas validez", 0, 23);
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_horasValidez");
            result.put("msgError", msg);

            return result;
        }

        // validacion txt_minutosValidez
        msg = JS._numeroEnteroString(txt_minutosValidez, "Minutos validez", 0, 59);
        if (!JS._vacio(msg)) {

            result.put("campoError", "txt_minutosValidez");
            result.put("msgError", msg);

            return result;
        }

        // validacion de numero de minutos generales
        int nroMinutosValidez = JS.toInt(txt_minutosValidez) + 60 * JS.toInt(txt_horasValidez) + 60 * 24 * JS.toInt(txt_diasValidez);
        if (nroMinutosValidez <= 0) {

            result.put("campoError", "general");
            result.put("msgError", "No se pueden generar tokens con 0 días 0 horas y 0 minutos de validez");

            return result;
        }

        TokenData tokenData = new TokenData();

        tokenData.setEntidad(txt_entidad);
        tokenData.setInventario(txt_inventario);
        tokenData.setUsuario(txt_usuario);
        tokenData.setMinutosValidez(nroMinutosValidez);

        // setearlo al mapa resultado
        result.put("tokenData", tokenData);

        return result;
    }

    private String validarQueTodosBienesEstenAsignados(Integer entidad, Integer inventario) throws Exception {

        InventarioBienEntity bienesFilter = new InventarioBienEntity();

        bienesFilter.setEntidadID(entidad);
        bienesFilter.setInventarioID(inventario);
        bienesFilter.setIndDel(Constantes.REGISTRO_ACTIVO);

        List<InventarioBienEntity> bienes = inventarioBienService.select(bienesFilter);

        if (CollectionUtils.isNotEmpty(bienes)) {

            for (InventarioBienEntity bien : bienes) {

                // si hay alguno que no este asignado, mostrar error
                if (bien != null && StringUtils.isBlank(bien.getInventariador())) {

                    return "El inventario tiene bienes sin asignar (" + bien.getCodigoPatrimonial() + ")";
                }
            }
        }

        return StringUtils.EMPTY;
    }

    public ModelAndView generarToken(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            Map<String, Object> result = validarGenerarToken(request);
            String msgError = MapUtils.getString(result, "msgError");
            if (!JS._vacio(msgError)) {
                throw new SimioException("AC-GT-090", msgError, JExceptionEnum.VALIDACION, result);
            }

            // crear modelo
            HashMap<String, Object> model = new HashMap<>();

            TokenData tokenData = (TokenData) result.get("tokenData");

            String token = TokenGenerator.generarToken(tokenData);

            // se envia el token y todas las demas credenciales
            model.put("token", token);
            model.put("usuario", tokenData.getUsuario());
            model.put("entidad", tokenData.getEntidad());
            model.put("inventario", tokenData.getInventario());

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("AC-GT-091", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }
}
