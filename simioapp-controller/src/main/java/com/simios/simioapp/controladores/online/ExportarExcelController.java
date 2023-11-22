package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.servicios.InventarioService;
import com.simios.simioapp.servicios.excel.FormatoExcelService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
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

public class ExportarExcelController extends BaseController {

    private static final Logger log = Logger.getLogger(ExportarExcelController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-exportar-excel";

    @Autowired
    @Qualifier("formatoExcelService")
    private FormatoExcelService formatoExcelService;

    @Autowired
    @Qualifier("inventarioService")
    private InventarioService inventarioService;


    public ModelAndView mostrarExportarFormatoExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            // cargar combo entidad
            UsuarioSession usuarioSession = getUsuarioSession(request);
            List<ListaItem> cboEntidad = getListaEntidades(usuarioSession);

            cargarCombo(request, "cboEntidad", cboEntidad, false, false);

            // carga combo de inventarios (tanto abierto como cerrados para fines de reporte/consulta)
            InventarioEntity filtroInventario = new InventarioEntity();

            filtroInventario.setIndDel(Constantes.REGISTRO_ACTIVO);
            filtroInventario.setEntidadID(JS.toInt(usuarioSession.getCodigoEntidad()));

            List<ListaItem> itemsInventario = inventarioService.listarInventarios(filtroInventario);

            // si tiene un solo item, marcarlo por default
            if (CollectionUtils.isNotEmpty(itemsInventario) && itemsInventario.size() == 1) {
                cargarComboSeleccione(request, "cboInventario", itemsInventario, itemsInventario.get(0).getId());
            } else {
                cargarComboSeleccione(request, "cboInventario", itemsInventario);
            }

            setVistaTemplate(model, "panel-exportar-excel", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-MEFE-342", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }
    }

    public ModelAndView exportarFormatoExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            String version = JS.toBlank(request.getParameter("version"));
            String fuente_reporte = JS.toBlank(request.getParameter("fuente_reporte"));
            String int_inventarioID = JS.toBlank(request.getParameter("int_inventarioID"));

            if (!JS._numeroEntero(int_inventarioID)) {
                throw new IllegalArgumentException("int_inventarioID no es válido");
            }

            if (JS.toInt(int_inventarioID) < 0) {
                throw new IllegalArgumentException("Por favor seleccione un inventario");
            }

            if (JS._vacio(fuente_reporte) || (!StringUtils.equals(fuente_reporte, "IA") && !StringUtils.equals(fuente_reporte, "IB") && !StringUtils.equals(fuente_reporte, "B"))) {
                throw new IllegalArgumentException("fuente_reporte no es válido");
            }

            if (JS._vacio(version) || (!StringUtils.equals(version, "V6") && !StringUtils.equals(version, "V7"))) {
                throw new IllegalArgumentException("version no es válido");
            }

            String anio;
            byte[] bytesExcel;


            if (StringUtils.equals(fuente_reporte, "IA")) {

                if (StringUtils.equals(version, "V6")) {
                    // toma de la tabla inventario_apertura
                    anio = "2016";
                    bytesExcel = formatoExcelService.generarFormatoExcelV6InventarioApertura(JS.toInt(int_inventarioID));

                } else if (StringUtils.equals(version, "V7")) {
                    // toma de la tabla inventario_apertura
                    anio = "2017";
                    bytesExcel = formatoExcelService.generarFormatoExcelV7InventarioApertura(JS.toInt(int_inventarioID));

                } else {
                    throw new IllegalArgumentException("operación no soportada");
                }

            } else if (StringUtils.equals(fuente_reporte, "IB")) {

                if (StringUtils.equals(version, "V6")) {
                    // toma de la tabla inventario_bien
                    anio = "2016";
                    bytesExcel = formatoExcelService.generarFormatoExcelV6InventarioBien(JS.toInt(int_inventarioID));

                } else if (StringUtils.equals(version, "V7")) {
                    // toma de la tabla inventario_bien
                    anio = "2017";
                    bytesExcel = formatoExcelService.generarFormatoExcelV7InventarioBien(JS.toInt(int_inventarioID));
                } else {
                    throw new IllegalArgumentException("operación no soportada");
                }

            } else {

                throw new IllegalArgumentException("operación no soportada");
            }

            // se pone 2015 en duro mostrar explicitamente que es de ese formato
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=Inventario" + anio + "-TABLA-" + fuente_reporte + ".xlsm");

            IOUtils.write(bytesExcel, response.getOutputStream());

        } catch (Exception sos) {

            String msgError = handleMsgError("ADC-EFE-432", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);
        }

        // no hay mucho que se pueda hacer aqui ( este metodo escribe sobre el flujo de salida del servlet )
        return null;
    }
}
