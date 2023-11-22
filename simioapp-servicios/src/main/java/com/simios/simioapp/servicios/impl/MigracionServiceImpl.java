package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionSBNExcelBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.negocio.migracion.*;
import com.simios.simioapp.servicios.*;
import com.simios.simioapp.servicios.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

@Service("migracionService")
public class MigracionServiceImpl extends BaseService implements MigracionService {

    private static final Logger log = Logger.getLogger(AreaServiceImpl.class.getName());

    @Autowired
    @Qualifier("migradorAreaNegocio")
    private MigradorAreaNegocio migradorAreaNegocio;

    @Autowired
    @Qualifier("migradorBienNegocio")
    private MigradorBienNegocio migradorBienNegocio;

    @Autowired
    @Qualifier("migradorExcelSBNNegocio")
    private MigradorExcelSBNNegocio migradorExcelSBNNegocio;

    @Autowired
    @Qualifier("migradorEmpleadoNegocio")
    private MigradorEmpleadoNegocio migradorEmpleadoNegocio;

    @Autowired
    @Qualifier("migradorLocalesNegocio")
    private MigradorLocalesNegocio migradorLocalesNegocio;

    @Autowired
    @Qualifier("migradorOficinaNegocio")
    private MigradorOficinaNegocio migradorOficinaNegocio;

    @Autowired
    private LocalesService localesService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private OficinaService oficinaService;

    @Autowired
    private BienService bienService;

    @Override
    public ResultadoMigracionBean migrarDBFAreas(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) {

        ResultadoMigracionBean result = new ResultadoMigracionBean();

        try {

            AreaEntity areaFilter = new AreaEntity();

            areaFilter.setEntidadID(entidadIDMigracion);

            List<AreaEntity> areasList = areaService.select(areaFilter);

            if (CollectionUtils.isNotEmpty(areasList)) {
                throw new Exception("Tabla de areas debe estar vacía");
            }

            result = migradorAreaNegocio.migrarDBFAreas(inputStreamDBF, usuarioSession, entidadIDMigracion, forzarUsarEntidadDeMigracion);

        } catch (Exception sos) {

            String msgError = handleMsgError("MSI-MA-001", sos);
            log.severe(msgError);

            result.setNombreTabla("area");
            result.setOcurrioError(true);
            result.setMsgError(msgError);
        }

        return result;
    }

    @Override
    public ResultadoMigracionBean migrarDBFLocales(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) {

        ResultadoMigracionBean result = new ResultadoMigracionBean();

        try {

            LocalesEntity localesFilter = new LocalesEntity();

            localesFilter.setEntidadID(entidadIDMigracion);

            List<LocalesEntity> localesList = localesService.select(localesFilter);

            if (CollectionUtils.isNotEmpty(localesList)) {
                throw new Exception("Tabla de locales debe estar vacía");
            }

            result = migradorLocalesNegocio.migrarDBFLocales(inputStreamDBF, usuarioSession, entidadIDMigracion, forzarUsarEntidadDeMigracion);

        } catch (Exception sos) {

            String msgError = handleMsgError("MSI-ML-002", sos);
            log.severe(msgError);

            result.setNombreTabla("locales");
            result.setOcurrioError(true);
            result.setMsgError(msgError);
        }

        return result;
    }

    @Override
    public ResultadoMigracionBean migrarDBFEmpleados(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) {

        ResultadoMigracionBean result = new ResultadoMigracionBean();

        try {

            EmpleadoEntity empleadoFilter = new EmpleadoEntity();

            empleadoFilter.setEntidadID(entidadIDMigracion);

            List<EmpleadoEntity> empleadosList = empleadoService.select(empleadoFilter);

            if (CollectionUtils.isNotEmpty(empleadosList)) {
                throw new Exception("Tabla de empleados debe estar vacía");
            }

            result = migradorEmpleadoNegocio.migrarDBFEmpleados(inputStreamDBF, usuarioSession, entidadIDMigracion, forzarUsarEntidadDeMigracion);

        } catch (Exception sos) {

            String msgError = handleMsgError("MSI-ME-003", sos);
            log.severe(msgError);

            result.setNombreTabla("empleado");
            result.setOcurrioError(true);
            result.setMsgError(msgError);
        }

        return result;
    }

    @Override
    public ResultadoMigracionBean migrarDBFOficinas(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) {

        ResultadoMigracionBean result = new ResultadoMigracionBean();

        try {

            OficinaEntity oficinaFilter = new OficinaEntity();

            oficinaFilter.setEntidadID(entidadIDMigracion);

            List<OficinaEntity> oficinasList = oficinaService.select(oficinaFilter);

            if (CollectionUtils.isNotEmpty(oficinasList)) {
                throw new Exception("Tabla de oficinas debe estar vacía");
            }

            result = migradorOficinaNegocio.migrarDBFOficinas(inputStreamDBF, usuarioSession, entidadIDMigracion, forzarUsarEntidadDeMigracion);

        } catch (Exception sos) {

            String msgError = handleMsgError("MSI-MO-003", sos);
            log.severe(msgError);

            result.setNombreTabla("oficina");
            result.setOcurrioError(true);
            result.setMsgError(msgError);
        }

        return result;
    }

    @Override
    public ResultadoMigracionBean migrarDBFBienes(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) {

        ResultadoMigracionBean result = new ResultadoMigracionBean();

        try {

            BienEntity bienFilter = new BienEntity();

            bienFilter.setEntidadID(entidadIDMigracion);

            List<BienEntity> bienesList = bienService.select(bienFilter);

            if (CollectionUtils.isNotEmpty(bienesList)) {
                throw new Exception("Tabla de bienes debe estar vacía");
            }

            result = migradorBienNegocio.migrarDBFBienes(inputStreamDBF, usuarioSession, entidadIDMigracion, forzarUsarEntidadDeMigracion);

        } catch (Exception sos) {

            String msgError = handleMsgError("MSI-MB-005", sos);
            log.severe(msgError);

            result.setNombreTabla("bien");
            result.setOcurrioError(true);
            result.setMsgError(msgError);
        }

        return result;
    }

    @Override
    public ResultadoMigracionSBNExcelBean migrarSBNExcelV7(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) {
        ResultadoMigracionSBNExcelBean result = new ResultadoMigracionSBNExcelBean();

        try {

            result = migradorExcelSBNNegocio.migrarSBNExcelV7(inputStreamDBF, usuarioSession, entidadIDMigracion, forzarUsarEntidadDeMigracion);

        } catch (Exception sos) {

            String msgError = handleMsgError("MSI-MSV-006", sos);
            log.severe(msgError);

            result.setOcurrioError(true);
            result.setMsgError(msgError);
        }

        return result;
    }

}
