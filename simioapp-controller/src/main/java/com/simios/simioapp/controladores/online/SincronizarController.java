package com.simios.simioapp.controladores.online;

import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.NumberUtil;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.controladores.util.HttpUtil;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;
import com.simios.simioapp.dominio.entidades.sync.DataSync;
import com.simios.simioapp.dominio.entidades.sync.InventarioBienSync;
import com.simios.simioapp.dominio.utiles.EntityUtil;
import com.simios.simioapp.negocio.utiles.SyncUtil;
import com.simios.simioapp.servicios.InventarioService;
import org.apache.commons.collections.CollectionUtils;
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

public class SincronizarController extends BaseController {

    private static final Logger log = Logger.getLogger(SincronizarController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-sincronizar";

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    @Autowired
    @Qualifier("syncUtil")
    private SyncUtil syncUtil;

    public ModelAndView mostrarSincronizarOnline(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            // solo es un bypass, el jsp "panel-sincronizar-online" invoca al metodo del controller "mostrarSincronizar"
            setVistaTemplate(model, "panel-sincronizar-online", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("SC-MPT-123", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

    public ModelAndView mostrarSincronizar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            // aqui setea los atributos de la sincronizacion: dataSyncJSON, puedeSincronizar, puedeSincronizarMsgError
            validarDatosParaSincronizar(request, model);

            setVistaTemplate(model, "panel-sincronizar", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-MSI-432", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

    private boolean validarDatosParaSincronizar(HttpServletRequest request, Map<String, Object> model) throws Exception {

        if (request == null || model == null) {
            throw new IllegalArgumentException("arguments request and model cannot be null");
        }

        String usuario = StringUtils.trimToEmpty(request.getParameter("usuario"));
        String entidad = StringUtils.trimToEmpty(request.getParameter("entidad"));
        String inventario = StringUtils.trimToEmpty(request.getParameter("inventario"));
        String token = StringUtils.trimToEmpty(request.getParameter("token"));
        String dataSyncJSON = StringUtils.trimToEmpty(request.getParameter("dataSyncJSON"));

        boolean puedeSincronizar = true;

        // NOTA IMPORTANTE: RECIEN AL ENTRAR A ESTE CONTROLLER SE SETEAN LOS ATRIBUTOS *SYNC DEL JSP GLOBAL/PLANTILLA default.jsp
        model.put("usuarioSYNC", usuario);
        model.put("entidadSYNC", entidad);
        model.put("inventarioSYNC", inventario);
        model.put("tokenSYNC", token);
        model.put("dataSyncJSONSYNC", dataSyncJSON);

        model.put("puedeSincronizar", Constantes.SI);
        model.put("puedeSincronizarMsgError", StringUtils.EMPTY);

        try {

            // VALIDAR TOKEN
            HttpUtil.checkCredencialesToken(request);

        } catch (Exception sos) {

            puedeSincronizar = false;
            model.put("puedeSincronizar", Constantes.NO);
            model.put("puedeSincronizarMsgError", "No puedes sincronizar toma de inventario: " + sos.getMessage());
        }

        if (puedeSincronizar && !StringUtils.equals(getUsuarioSession(request).getUsuarioSessionID(), usuario)) {

            puedeSincronizar = false;
            model.put("puedeSincronizar", Constantes.NO);
            model.put("puedeSincronizarMsgError", "La toma de inventario no corresponde a tu usuario, sino al usuario " + usuario);
        }

        if (puedeSincronizar && !StringUtils.equals(getUsuarioSession(request).getCodigoEntidad(), entidad)) {

            puedeSincronizar = false;
            model.put("puedeSincronizar", Constantes.NO);
            model.put("puedeSincronizarMsgError", "La toma de inventario no le corresponde a tu entidad, sino a la entidad " + entidad);
        }

        return puedeSincronizar;
    }

    public ModelAndView sincronizarInventario(HttpServletRequest request, HttpServletResponse response) {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            HashMap<String, Object> model = new HashMap<>();

            String usuario = StringUtils.trimToEmpty(request.getParameter("usuario"));
            String entidad = StringUtils.trimToEmpty(request.getParameter("entidad"));
            String inventario = StringUtils.trimToEmpty(request.getParameter("inventario"));
            String token = StringUtils.trimToEmpty(request.getParameter("token"));
            String dataSyncJSON = request.getParameter("dataSyncJSON");

            Credencial credenciales = HttpUtil.checkCredencialesTokenQuiet(request);

            if (credenciales == null) {
                throw new Exception("Credenciales no válidas para sincronización");
            }

            DataSync dataSync = EntityUtil.parseJSONToDataSync(dataSyncJSON);

            if (dataSync == null) {
                throw new Exception("Data para sincronización vacía");
            }

            if (CollectionUtils.isEmpty(dataSync.getTabla().getInventarioBien())) {
                throw new Exception("La lista de bienes para sincronizar no puede estar vacía");
            }

            // parsear todos los bienes toTrimNull
            if (CollectionUtils.isNotEmpty(dataSync.getTabla().getInventarioBien())) {
                for (InventarioBienSync inventarioBienSync : dataSync.getTabla().getInventarioBien()) {
                    if (inventarioBienSync != null) {
                        EntityUtil.trimToNullInventarioBien(inventarioBienSync);

                        // en el offline no se guardan los campos de audtoria
                        inventarioBienSync.setIndDel(Constantes.REGISTRO_ACTIVO);
                    }
                }
            }

            // post validaciones, que la entidadID y el inventarioID sean los correctos
            // que los indicadores de estadoSubida, sobranteFaltante, revisadoToma tenga los valores validos
            if (CollectionUtils.isNotEmpty(dataSync.getTabla().getInventarioBien())) {
                for (InventarioBienSync inventarioBienSync : dataSync.getTabla().getInventarioBien()) {
                    if (inventarioBienSync == null) {
                        throw new Exception("La lista de bienes a sincronizar contiene items nulos");
                    }

                    if (!NumberUtil.equalsInteger(inventarioBienSync.getEntidadID(), JS.toInt(credenciales.getEntidad()))) {
                        throw new Exception("La lista de bienes a sincronizar contiene items que no corresponden a la entidad " + credenciales.getEntidad() + " (código patrimonial: " + inventarioBienSync.getCodigoPatrimonial() + ")");
                    }

                    if (!NumberUtil.equalsInteger(inventarioBienSync.getInventarioID(), JS.toInt(credenciales.getInventario()))) {
                        throw new Exception("La lista de bienes a sincronizar contiene items que no corresponden al inventario " + credenciales.getInventario() + " (código patrimonial: " + inventarioBienSync.getCodigoPatrimonial() + ")");
                    }

                    if (!syncUtil.isEstadoSubidaOK(inventarioBienSync)) {
                        throw new Exception("El bien con código patrimonial " + inventarioBienSync.getCodigoPatrimonial() + " no tiene un valor válido para el indicador estadoSubida: " + inventarioBienSync.getEstadoSubida());
                    }

                    if (!syncUtil.isSobranteFaltanteOK(inventarioBienSync)) {
                        throw new Exception("El bien con código patrimonial " + inventarioBienSync.getCodigoPatrimonial() + " no tiene un valor válido para el indicador sobranteFaltante: " + inventarioBienSync.getSobranteFaltante());
                    }

                    if (!syncUtil.isRevisadoTomaOK(inventarioBienSync)) {
                        throw new Exception("El bien con código patrimonial " + inventarioBienSync.getCodigoPatrimonial() + " no tiene un valor válido para el indicador revisadoToma: " + inventarioBienSync.getRevisadoToma());
                    }

                    if (!syncUtil.isActivoOK(inventarioBienSync)) {
                        throw new Exception("El bien con código patrimonial " + inventarioBienSync.getCodigoPatrimonial() + " no tiene un valor válido para el indicador indDel: " + inventarioBienSync.getIndDel());
                    }
                }
            }

            inventarioService.sincronizarMonoUsuario(dataSync.getTabla().getInventarioBien(), credenciales);

            model.put("dataSyncJSON", dataSyncJSON);

            model.put("syncIsOK", Constantes.SI);
            model.put("syncMsgError", StringUtils.EMPTY);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("SC-SI-315", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    public ModelAndView mostrarSincronizacionDescarga(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            Credencial credencial;
            String msgCredenciales = StringUtils.EMPTY;

            try {

                credencial = HttpUtil.checkCredencialesToken(request);

            } catch (Exception sos) {

                credencial = null;
                msgCredenciales = sos.getMessage();
            }

            if (credencial == null) {
                throw new Exception("No tiene credenciales válidas. Primero debe generar un token. " + msgCredenciales);
            }

            // se envia el token y todas las demas credenciales
            model.put("token", credencial.getToken());
            model.put("usuario", credencial.getUsuario());
            model.put("entidad", credencial.getEntidad());
            model.put("inventario", credencial.getInventario());

            // buscando la descripcion de usuarioNombre
            model.put("usuarioNombre", StringUtils.EMPTY);

            UsuarioEntity usuarioFilter = new UsuarioEntity();
            usuarioFilter.setEntidadID(JS.toInt(credencial.getEntidad()));
            usuarioFilter.setUsuarioID(credencial.getUsuario());
            usuarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<UsuarioEntity> usuarios = usuarioService.select(usuarioFilter);

            if (CollectionUtils.isNotEmpty(usuarios)) {
                model.put("usuarioNombre", JS.toBlank(usuarios.get(0).getNombres()) + " " + JS.toBlank(usuarios.get(0).getApellidos()));
            }

            // buscando la descripcion de entidad
            model.put("entidadNombre", StringUtils.EMPTY);

            EntidadEntity entidadFilter = new EntidadEntity();
            entidadFilter.setEntidadID(JS.toInt(credencial.getEntidad()));
            entidadFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<EntidadEntity> entidades = entidadService.select(entidadFilter);

            if (CollectionUtils.isNotEmpty(entidades)) {
                model.put("entidadNombre", JS.toBlank(entidades.get(0).getEntidad()));
            }

            // buscando la descripcion de inventarioNombre
            model.put("inventarioNombre", StringUtils.EMPTY);

            InventarioEntity inventarioFilter = new InventarioEntity();
            inventarioFilter.setEntidadID(JS.toInt(credencial.getEntidad()));
            inventarioFilter.setInventarioID(JS.toInt(credencial.getInventario()));
            inventarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<InventarioEntity> inventarios = inventarioService.select(inventarioFilter);

            if (CollectionUtils.isNotEmpty(inventarios)) {
                model.put("inventarioNombre", JS.toBlank(inventarios.get(0).getNombre()));
            }

            setVistaTemplate(model, "panel-sincronizar-descarga", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-MSI-432", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

}

