package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.DateTime;
import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.dominio.utiles.EntityUtil;
import com.simios.simioapp.negocio.utiles.Catalogo;
import com.simios.simioapp.negocio.utiles.SyncUtil;
import com.simios.simioapp.servicios.BienService;
import com.simios.simioapp.servicios.InventarioBienService;
import com.simios.simioapp.servicios.InventarioService;
import com.simios.simioapp.servicios.LocalesService;
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

public class CierreController extends BaseController {

    private static final Logger log = Logger.getLogger(CierreController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-cierre";

    @Autowired
    @Qualifier("catalogo")
    private Catalogo catalogo;

    @Autowired
    @Qualifier("localesService")
    private LocalesService localesService;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;

    @Autowired
    @Qualifier("bienService")
    private BienService bienService;

    @Autowired
    @Qualifier("inventarioBienService")
    private InventarioBienService inventarioBienService;

    @Autowired
    @Qualifier("syncUtil")
    private SyncUtil syncUtil;


    public ModelAndView mostrarPanelCierre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            // crear modelo
            model = new HashMap<>();

            // setear siguiente vista
            setVistaTemplate(model, "panel-cierre", modulo, plantilla);

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
            cargarComboCatalogoTodos(request, "cboEstadoSubida", catalogo.ESTADO_SUBIDA);

            // cargar combo estado sobrante faltante
            cargarComboCatalogoTodos(request, "cboSobranteFaltante", catalogo.SOBRANTE_FALTANTE);

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

            // carga combo de usuarios (en este caso, inventariador)
            UsuarioEntity filtroUsuario = new UsuarioEntity();

            filtroUsuario.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroUsuario.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));

            List<ListaItem> itemsInventariador = usuarioService.listarUsuarios(filtroUsuario);

            cargarComboTodos(request, "cboInventariador", itemsInventariador);

            cargarComboSeleccione(request, "cboInventariadorAsignar", itemsInventariador);

            // para el cierre no se muestra el mensaje de inventarios activos
            // handleNoExistenInventariosActivos(model, JS.toInt(usuarioSession.getCodigoEntidad()));

            // poblar modelo (se muestra una lista vacia)
            model.put("lista", null);

            // limpiar session
            limpiarSession(request);

            // renderizar vista
            return handleModelAndView(model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleMsgError("CC-MBU-001", sos);
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
            model.put("msgHidden", "No existen inventarios abiertos para cerrar");
        }
    }

    public ModelAndView validarCierre(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            // recoger parametros y validar
            String cboEntidad = JS.toUpperBlank(request.getParameter("cboEntidad"));
            String int_inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));

            Map<String, Object> result = new HashMap<>();

            String msg = JS._numeroEnteroNoNegativoString(cboEntidad, "Entidad");
            if (!JS._vacio(msg)) {
                result.put("campoError", "cboEntidad");
                result.put("msgError", "Por favor seleccione uno");

                throw new SimioException("CC-VBU-360", "Por favor seleccione uno", JExceptionEnum.VALIDACION, result);
            }

            msg = JS._numeroEnteroNoNegativoString(int_inventarioID, "Inventario");
            if (!JS._vacio(msg)) {
                result.put("campoError", "int_inventarioID");
                result.put("msgError", "Por favor seleccione uno");

                throw new SimioException("CC-VBU-361", "Por favor seleccione uno", JExceptionEnum.VALIDACION, result);
            }

            // validar que el inventario este en estado aperturado
            validarQueSeaInventarioAbierto(cboEntidad, int_inventarioID, result);

            // buscar los bienes (trae todos, activos e inactivos)
            BienEntity bienFilter = new BienEntity();
            bienFilter.setEntidadID(JS.toInt(cboEntidad));
            List<BienEntity> bienes = bienService.select(bienFilter);

            // buscar los bienes de la toma de inventario (trae todos, activos e inactivos)
            InventarioBienEntity bienTomaFilter = new InventarioBienEntity();
            bienTomaFilter.setEntidadID(JS.toInt(cboEntidad));
            bienTomaFilter.setInventarioID(JS.toInt(int_inventarioID));
            List<InventarioBienEntity> bienesToma = inventarioBienService.select(bienTomaFilter);

            // validar que todos los bienes de la tabla_inventario (registros activos), esten subidos
            validarQueTodosBienesEstenSubidos(bienesToma, result);

            // validar que todos en inventario_bien (activos) y bien (activos) deben tener su codigo de patrimonial
            validarQueTodosLosBienesTenganCodigoPatrimonial(bienes, bienesToma, result);

            // validar que todos los bienes en la tabla inventario_bien (registros activos) esten en activos en la tabla bien.
            validarQueTodosLosBienesDeLaTomaEstenActivosEnLaTablaBien(bienes, bienesToma, result);

            // validar que todos los bienes de la tabla_inventario con bien_id = null (registros activos), deben estar marcado como sobrante
            validarQueTodosLosBienesDeLaTomaConBienIDNullDebenMarcarseComoSobrantes(bienesToma, result);

            // validar que todos los bienes de la tabla_inventario marcados como sobrantes (registros activos), su bien_id debe ser null
            validarQueTodosLosBienesDeLaTomaMarcadosComoSobrantesDebenTenerBienIDNull(bienesToma, result);

            // validar que no deben existir bienes activos en la tabla bien, que no esten en la tabla inventario_bien
            validarQueTodosLosBienesActivosEnTablaBienEstenEnTablaInventarioBien(bienes, bienesToma, result);

            HashMap<String, Object> model = new HashMap<>();

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("CC-VAB-361", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private void validarQueTodosBienesEstenSubidos(List<InventarioBienEntity> bienesToma, Map<String, Object> result) throws Exception {

        // validar que todos los bienes de la tabla_inventario (registros activos), esten subidos
        if (CollectionUtils.isNotEmpty(bienesToma)) {

            for (InventarioBienEntity bienToma : bienesToma) {

                // solo deja pasar los registros activos
                if (bienToma == null || !StringUtils.equals(bienToma.getIndDel(), Constantes.REGISTRO_ACTIVO)) continue;

                // si hay alguno que no este subido, mostrar error
                if (!syncUtil.isSubido(bienToma)) {

                    String msg = "El inventario tiene bienes sin subir (" + bienToma.getCodigoPatrimonial() + ")";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VBS-123", msg, JExceptionEnum.VALIDACION, result);
                }
            }
        }
    }

    private void validarQueTodosLosBienesTenganCodigoPatrimonial(List<BienEntity> bienes, List<InventarioBienEntity> bienesToma, Map<String, Object> result) throws Exception {

        // validar que todos en la tabla bien (registros activos) tengan su codigo de patrimonial
        if (CollectionUtils.isNotEmpty(bienes)) {

            // recorrer todos los bienes en la tabla bien
            for (BienEntity bien : bienes) {

                // solo deja pasar los registros activos
                if (bien == null || !StringUtils.equals(bien.getIndDel(), Constantes.REGISTRO_ACTIVO)) continue;

                // si su codigo de patrimonial esta vacio
                if (StringUtils.isBlank(bien.getCodigoPatrimonial())) {

                    String msg = "Existen registros en la tabla bien sin código de patrimonial (bien_id: " + bien.getBienID() + ")";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VBS-234", msg, JExceptionEnum.VALIDACION, result);
                }
            }
        }

        // validar que todos en la tabla inventario_bien (registros activos) tengan su codigo de patrimonial
        if (CollectionUtils.isNotEmpty(bienesToma)) {

            // recorrer todos los bienes en la tabla inventario_bien
            for (InventarioBienEntity bienToma : bienesToma) {

                // solo deja pasar los registros activos
                if (bienToma == null || !syncUtil.isActivo(bienToma)) continue;

                // si su codigo de patrimonial esta vacio
                if (StringUtils.isBlank(bienToma.getCodigoPatrimonial())) {

                    String msg = "El inventario tiene bienes sin código de patrimonial (bienID:" + bienToma.getBienID() + ")";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VBS-123", msg, JExceptionEnum.VALIDACION, result);
                }
            }
        }
    }

    private void validarQueTodosLosBienesDeLaTomaConBienIDNullDebenMarcarseComoSobrantes(List<InventarioBienEntity> bienesToma, Map<String, Object> result) throws Exception {

        // validar que todos los bienes de la tabla_inventario con bien_id = null (registros activos), deben estar marcado como sobrante
        if (CollectionUtils.isNotEmpty(bienesToma)) {

            // recorrer todos los bienes en la tabla inventario_bien
            for (InventarioBienEntity bienToma : bienesToma) {

                // solo deja pasar los registros activos
                if (bienToma == null || !syncUtil.isActivo(bienToma)) continue;

                // si bien_id es null, sino esta como sobrante, lanzar error
                if (bienToma.getBienID() == null && !syncUtil.isSobrante(bienToma)) {

                    String msg = "El código de patrimonial " + bienToma.getCodigoPatrimonial() + " del inventario no está marcado como sobrante (dado que no tiene bien_id asociado)";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VBB-434", msg, JExceptionEnum.VALIDACION, result);
                }

            }
        }
    }

    private void validarQueTodosLosBienesDeLaTomaMarcadosComoSobrantesDebenTenerBienIDNull(List<InventarioBienEntity> bienesToma, Map<String, Object> result) throws Exception {

        // validar que todos los bienes de la tabla_inventario marcados como sobrantes (registros activos), su bien_id debe ser null
        if (CollectionUtils.isNotEmpty(bienesToma)) {

            // recorrer todos los bienes en la tabla inventario_bien
            for (InventarioBienEntity bienToma : bienesToma) {

                // solo deja pasar los registros activos
                if (bienToma == null || !syncUtil.isActivo(bienToma)) continue;

                // si fue marcado como sobrante y su bien id no esta como null, lanzar error
                if (syncUtil.isSobrante(bienToma) && bienToma.getBienID() != null) {

                    String msg = "El código de patrimonial " + bienToma.getCodigoPatrimonial() + " del inventario ha sido marcado como sobrante pero tiene bien_id asociado";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VBB-980", msg, JExceptionEnum.VALIDACION, result);
                }
            }
        }
    }

    private void validarQueTodosLosBienesActivosEnTablaBienEstenEnTablaInventarioBien(List<BienEntity> bienes, List<InventarioBienEntity> bienesToma, Map<String, Object> result) throws Exception {

        if (CollectionUtils.isNotEmpty(bienes)) {

            // recorrer todos los bienes en la tabla bien
            for (BienEntity bien : bienes) {

                // solo deja pasar los registros activos
                if (bien == null || !StringUtils.equals(bien.getIndDel(), Constantes.REGISTRO_ACTIVO)) continue;

                // si hay algun bien que no este en inventario_bien, mostrar la validacion
                if (existeEnListaPorBienID(bien, bienesToma)) {

                    String msg = "Existen registros en la tabla bien que no están en la tabla inventario_bien (bien_id: " + bien.getBienID() + ")";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VIE-766", msg, JExceptionEnum.VALIDACION, result);
                }

                // si hay algun bien que no este en inventario_bien, mostrar la validacion
                if (existeEnListaPorCodigoPatrimonial(bien, bienesToma)) {

                    String msg = "Existen registros en la tabla bien que no están en la tabla inventario_bien (bien_id: " + bien.getBienID() + ")";

                    result.put("campoError", "general");
                    result.put("msgError", msg);

                    throw new SimioException("CC-VIE-456", msg, JExceptionEnum.VALIDACION, result);
                }
            }
        }
    }

    private void validarQueTodosLosBienesDeLaTomaEstenActivosEnLaTablaBien(List<BienEntity> bienes, List<InventarioBienEntity> bienesToma, Map<String, Object> result) throws Exception {

        // validar que todos los bienes en la tabla inventario_bien (registros activos) esten en activos en la tabla bien.
        if (CollectionUtils.isNotEmpty(bienesToma)) {

            // recorrer todos los bienes en la tabla inventario_bien
            for (InventarioBienEntity bienToma : bienesToma) {

                // solo deja pasar los registros activos
                if (bienToma == null || !syncUtil.isActivo(bienToma)) continue;

                if (bienToma.getBienID() != null) {

                    // al buscarlo en la tabla bien debe estar como registro activo
                    for (BienEntity bien : bienes) {

                        if (bien != null && EntityUtil.tienenMismoBienID(bienToma, bien)) {

                            // si no esta como registro activo, lanzar error
                            if (!StringUtils.equals(bien.getIndDel(), Constantes.REGISTRO_ACTIVO)) {

                                String msg = "El código de patrimonial " + bien.getCodigoPatrimonial() + " se encuentra activo en la tabla inventario_bien pero inactivo en la tabla bien";

                                result.put("campoError", "general");
                                result.put("msgError", msg);

                                throw new SimioException("CC-VBS-755", msg, JExceptionEnum.VALIDACION, result);
                            }
                        }
                    }
                }
            }
        }
    }

    private void validarQueSeaInventarioAbierto(String cboEntidad, String int_inventarioID, Map<String, Object> result) throws Exception {

        // validar que el inventario este en estado aperturado
        InventarioEntity filter = new InventarioEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setEntidadID(JS.toInt(cboEntidad));
        filter.setInventarioID(JS.toInt(int_inventarioID));

        List<InventarioEntity> results = inventarioService.select(filter);

        if (CollectionUtils.isNotEmpty(results)) {

            InventarioEntity inventario = results.get(0);

            // si el inventario encontrado no esta abierto, lanzar error
            if (inventario != null && !StringUtils.equals(inventario.getEstado(), catalogo.ESTADO_INVENTARIO_ABIERTO)) {

                result.put("campoError", "general");
                result.put("msgError", "El inventario '" + inventario.getNombre() + "' debe encontrarse en estado aperturado.");

                throw new SimioException("CC-VIA-343", "El inventario '" + inventario.getNombre() + "' debe encontrarse en estado aperturado.", JExceptionEnum.VALIDACION, result);
            }
        }
    }

    private void limpiarSession(HttpServletRequest request) throws Exception {

    }

    public ModelAndView cerrarInventario(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            String int_inventarioID = JS.toUpperBlank(request.getParameter("int_inventarioID"));

            if (!JS._numeroEntero(int_inventarioID)) {
                throw new IllegalArgumentException("Argumento int_inventarioID es inválido");
            }

            HashMap<String, Object> model = new HashMap<>();

            InventarioEntity update = new InventarioEntity();

            update.setInventarioID(JS.toInt(int_inventarioID));
            update.setFechaCierre(DateTime.getToday().toDate());
            update.setEstado(catalogo.ESTADO_INVENTARIO_CERRADO);

            // NOTA: es importante setear estos campos de auditoria, porque se usan para el resto de tablas afectadas
            setCamposAuditoria(request, update, false);

            inventarioService.cerrarInventario(update);

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = handleJSONError("AC-A-130", dataJSON, sos);
            log.severe(msgError);
        }

        return handleJSONResponse(dataJSON, response);
    }

    private boolean existeEnListaPorBienID(BienEntity bienBuscar, List<InventarioBienEntity> inventarioBienLista) {

        if (bienBuscar == null || CollectionUtils.isEmpty(inventarioBienLista)) return false;

        for (InventarioBienEntity inventarioBien : inventarioBienLista) {

            if (inventarioBien == null) continue;

            if (EntityUtil.tienenMismoBienID(inventarioBien, bienBuscar)) {
                return true;
            }
        }

        return false;
    }

    private boolean existeEnListaPorCodigoPatrimonial(BienEntity bienBuscar, List<InventarioBienEntity> inventarioBienLista) {

        if (bienBuscar == null || CollectionUtils.isEmpty(inventarioBienLista)) return false;

        for (InventarioBienEntity inventarioBien : inventarioBienLista) {

            if (inventarioBien == null) continue;

            if (EntityUtil.tienenMismoCodigoPatrimonialUnique(inventarioBien, bienBuscar)) {
                return true;
            }
        }

        return false;
    }
}
