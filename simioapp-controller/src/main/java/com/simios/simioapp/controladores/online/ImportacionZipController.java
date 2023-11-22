package com.simios.simioapp.controladores.online;

import com.jcfr.utiles.ListaItem;
import com.jcfr.utiles.web.ComboWeb;
import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.JSONUtil;
import com.simios.simioapp.controladores.base.BaseController;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.servicios.EntidadService;
import com.simios.simioapp.servicios.MigracionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ImportacionZipController extends BaseController {

    private static final Logger log = Logger.getLogger(ImportacionZipController.class.getName());

    private static final String plantilla = "default";
    private static final String modulo = "panel-importacion-zip";

    @Autowired
    @Qualifier("migracionService")
    private MigracionService migracionService;

    @Autowired
    @Qualifier("entidadService")
    private EntidadService entidadService;

    public ModelAndView mostrarPanelImportarZIP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            setVistaTemplate(model, "panel-importacion", modulo, plantilla);

            UsuarioSession usuario = getUsuarioSession(request);

            // mostrar todas las entidades
            List<ListaItem> listaItem = new ArrayList<>();

            // servicio ya implementado con el framework
            EntidadEntity filter = new EntidadEntity();
            List<EntidadEntity> itemsEntidad = entidadService.select(filter);

            if (CollectionUtils.isNotEmpty(itemsEntidad)) {

                for (EntidadEntity entidad : itemsEntidad) {

                    ListaItem itemCombo = new ListaItem();

                    itemCombo.setId(String.valueOf(entidad.getEntidadID()));
                    itemCombo.setLabel(entidad.getCodigoEntidad() + " - " + entidad.getEntidad());

                    listaItem.add(itemCombo);
                }
            }

            cargarCombo(request, "cboEntidad", listaItem, false, false);

            // cargar combo SI/NO
            ComboWeb cboForzarUsoEntidadSession = cargarComboSINO(request, "cboForzarUsoEntidadSession", false, false);
            cboForzarUsoEntidadSession.setSelID(Constantes.NO);

            model.put("entidad_id", usuario.getCodigoEntidad());

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("IZC-MPT-423", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }

    public ModelAndView importarArchivoZIP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = null;

        try {

            model = new HashMap<>();

            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(request);

            String entidad = StringUtils.EMPTY;
            String cboForzarUsarEntidadDeMigracion = StringUtils.EMPTY;
            byte[] zipBytes = null;

            while (iter.hasNext()) {
                FileItemStream next = iter.next();

                if (next.isFormField() && StringUtils.equals(next.getFieldName(), "txt_entidad")) {
                    entidad = Streams.asString(next.openStream());
                }

                if (next.isFormField() && StringUtils.equals(next.getFieldName(), "cboForzarUsoEntidadSession")) {
                    cboForzarUsarEntidadDeMigracion = Streams.asString(next.openStream());
                }

                if (!next.isFormField() && "file_archivo_zip".equals(next.getFieldName())) {
                    zipBytes = IOUtils.toByteArray(next.openStream());
                }
            }

            if (StringUtils.isBlank(entidad)) {
                throw new Exception("Debe escoger una Entidad");
            }

            if (!JS._numeroEntero(entidad)) {
                throw new Exception("Código de Entidad " + entidad + " no tiene formato correcto");
            }

            // validar que exista la entidad en BD
            EntidadEntity entidadFiltro = new EntidadEntity();

            entidadFiltro.setEntidadID(Integer.parseInt(entidad));
            entidadFiltro.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<EntidadEntity> entidadesResult = entidadService.select(entidadFiltro);
            if (CollectionUtils.isEmpty(entidadesResult)) {
                throw new Exception("Código de Entidad " + entidad + " no existe en BD");
            }

            if (StringUtils.isBlank(cboForzarUsarEntidadDeMigracion)) {
                throw new Exception("Debe escoger Forzar Uso de Entidad");
            }

            if (zipBytes == null || zipBytes.length == 0) {
                throw new Exception("Debe escoger un Archivo ZIP");
            }

            boolean forzarUsarEntidadDeMigracion = StringUtils.equals(cboForzarUsarEntidadDeMigracion, Constantes.SI);

            InputStream is = new ByteArrayInputStream(zipBytes);

            Map<String, InputStream> archivos = new HashMap<>();

            ZipArchiveInputStream in = new ZipArchiveInputStream(is);

            ZipArchiveEntry entry = in.getNextZipEntry();

            while (entry != null) {

                if (entry.isDirectory()) {
                    entry = in.getNextZipEntry();
                    continue;
                }

                if (StringUtils.equalsIgnoreCase(entry.getName(), "LOCALES.DBF")) {
                    archivos.put("locales", new ByteArrayInputStream(IOUtils.toByteArray(in)));
                }

                if (StringUtils.equalsIgnoreCase(entry.getName(), "AREAS.DBF")) {
                    archivos.put("areas", new ByteArrayInputStream(IOUtils.toByteArray(in)));
                }

                if (StringUtils.equalsIgnoreCase(entry.getName(), "OFICINAS.DBF")) {
                    archivos.put("oficinas", new ByteArrayInputStream(IOUtils.toByteArray(in)));
                }

                if (StringUtils.equalsIgnoreCase(entry.getName(), "TEMPLEA0.DBF")) {
                    archivos.put("empleados", new ByteArrayInputStream(IOUtils.toByteArray(in)));
                }

                if (StringUtils.equalsIgnoreCase(entry.getName(), "MBIENES0.DBF")) {
                    archivos.put("bienes", new ByteArrayInputStream(IOUtils.toByteArray(in)));
                }

                entry = in.getNextZipEntry();
            }


            UsuarioSession usuarioSession = getUsuarioSession(request);

            if (archivos.containsKey("locales")) {

                InputStream inputStream = archivos.get("locales");

                ResultadoMigracionBean result = migracionService.migrarDBFLocales(inputStream, usuarioSession, JS.toInt(entidad), forzarUsarEntidadDeMigracion);

                model.put("panel_importacion_resultado_locales", JSONUtil.toJSON(result, false));
            }

            if (archivos.containsKey("areas")) {

                InputStream inputStream = archivos.get("areas");

                ResultadoMigracionBean result = migracionService.migrarDBFAreas(inputStream, usuarioSession, JS.toInt(entidad), forzarUsarEntidadDeMigracion);

                model.put("panel_importacion_resultado_areas", JSONUtil.toJSON(result, false));
            }

            if (archivos.containsKey("oficinas")) {

                InputStream inputStream = archivos.get("oficinas");

                ResultadoMigracionBean result = migracionService.migrarDBFOficinas(inputStream, usuarioSession, JS.toInt(entidad), forzarUsarEntidadDeMigracion);

                model.put("panel_importacion_resultado_oficinas", JSONUtil.toJSON(result, false));
            }

            if (archivos.containsKey("empleados")) {

                InputStream inputStream = archivos.get("empleados");

                ResultadoMigracionBean result = migracionService.migrarDBFEmpleados(inputStream, usuarioSession, JS.toInt(entidad), forzarUsarEntidadDeMigracion);

                model.put("panel_importacion_resultado_empleados", JSONUtil.toJSON(result, false));
            }

            if (archivos.containsKey("bienes")) {

                InputStream inputStream = archivos.get("bienes");

                ResultadoMigracionBean result = migracionService.migrarDBFBienes(inputStream, usuarioSession, JS.toInt(entidad), forzarUsarEntidadDeMigracion);

                model.put("panel_importacion_resultado_bienes", JSONUtil.toJSON(result, false));
            }

            setVistaTemplate(model, "panel-importacion-resultado", modulo, plantilla);

            return handleModelAndView(model);

        } catch (Exception sos) {

            String msgError = handleMsgError("IZC-MPT-223", sos);
            log.severe(msgError);
            return handleErrorModelAndView(model, msgError, plantilla);

        }
    }


}
