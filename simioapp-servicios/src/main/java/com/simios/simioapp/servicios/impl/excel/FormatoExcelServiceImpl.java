package com.simios.simioapp.servicios.impl.excel;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.negocio.BienNegocio;
import com.simios.simioapp.negocio.InventarioAperturaNegocio;
import com.simios.simioapp.negocio.InventarioBienNegocio;
import com.simios.simioapp.negocio.InventarioNegocio;
import com.simios.simioapp.negocio.excel.FormatoExcelNegocio;
import com.simios.simioapp.servicios.base.BaseService;
import com.simios.simioapp.servicios.excel.FormatoExcelService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service("formatoExcelService")
public class FormatoExcelServiceImpl extends BaseService implements FormatoExcelService {

    private static final Logger log = Logger.getLogger(FormatoExcelServiceImpl.class.getName());

    @Autowired
    @Qualifier("formatoExcelNegocio")
    private FormatoExcelNegocio formatoExcelNegocio;

    @Autowired
    @Qualifier("inventarioNegocio")
    private InventarioNegocio inventarioNegocio;

    @Autowired
    @Qualifier("inventarioAperturaNegocio")
    private InventarioAperturaNegocio inventarioAperturaNegocio;

    @Autowired
    @Qualifier("inventarioBienNegocio")
    private InventarioBienNegocio inventarioBienNegocio;

    @Override
    public byte[] generarFormatoExcelV7InventarioBien(int int_inventarioID) throws Exception {

        try {

            InventarioEntity inventarioFilter = new InventarioEntity();
            inventarioFilter.setInventarioID(int_inventarioID);
            inventarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<InventarioEntity> inventarios = inventarioNegocio.select(inventarioFilter);

            if (CollectionUtils.isEmpty(inventarios)) {
                throw new Exception("Inventario " + int_inventarioID + " no existe");
            }

            InventarioBienEntity inventarioBienFilter = new InventarioBienEntity();
            inventarioBienFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            inventarioBienFilter.setEntidadID(inventarios.get(0).getEntidadID());
            inventarioBienFilter.setInventarioID(inventarios.get(0).getInventarioID());
            List<InventarioBienEntity> bienes = inventarioBienNegocio.select(inventarioBienFilter);

            if (CollectionUtils.isEmpty(bienes)) {
                throw new Exception("No hay bienes (inventario_bien) para generar reporte");
            }

            return formatoExcelNegocio.generarFormatoExcelV7(bienes, inventarios.get(0).getEntidadID());

        } catch (Exception sos) {

            String msgError = handleMsgError("FES-GFE-002", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public byte[] generarFormatoExcelV7InventarioApertura(int int_inventarioID) throws Exception {

        try {

            InventarioEntity inventarioFilter = new InventarioEntity();
            inventarioFilter.setInventarioID(int_inventarioID);
            inventarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<InventarioEntity> inventarios = inventarioNegocio.select(inventarioFilter);

            if (CollectionUtils.isEmpty(inventarios)) {
                throw new Exception("Inventario " + int_inventarioID + " no existe");
            }

            InventarioAperturaEntity inventarioBienFilter = new InventarioAperturaEntity();
            inventarioBienFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            inventarioBienFilter.setEntidadID(inventarios.get(0).getEntidadID());
            inventarioBienFilter.setInventarioID(inventarios.get(0).getInventarioID());
            List<InventarioAperturaEntity> bienes = inventarioAperturaNegocio.select(inventarioBienFilter);

            if (CollectionUtils.isEmpty(bienes)) {
                throw new Exception("No hay bienes (inventario_apertura) para generar reporte");
            }

            return formatoExcelNegocio.generarFormatoExcelV7(bienes, inventarios.get(0).getEntidadID());

        } catch (Exception sos) {

            String msgError = handleMsgError("FES-GFE-003", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public byte[] generarFormatoExcelV6InventarioBien(int int_inventarioID) throws Exception {

        try {

            InventarioEntity inventarioFilter = new InventarioEntity();
            inventarioFilter.setInventarioID(int_inventarioID);
            inventarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<InventarioEntity> inventarios = inventarioNegocio.select(inventarioFilter);

            if (CollectionUtils.isEmpty(inventarios)) {
                throw new Exception("Inventario " + int_inventarioID + " no existe");
            }

            InventarioBienEntity inventarioBienFilter = new InventarioBienEntity();
            inventarioBienFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            inventarioBienFilter.setEntidadID(inventarios.get(0).getEntidadID());
            inventarioBienFilter.setInventarioID(inventarios.get(0).getInventarioID());
            List<InventarioBienEntity> bienes = inventarioBienNegocio.select(inventarioBienFilter);

            if (CollectionUtils.isEmpty(bienes)) {
                throw new Exception("No hay bienes (inventario_bien) para generar reporte");
            }

            return formatoExcelNegocio.generarFormatoExcelV6(bienes, inventarios.get(0).getEntidadID());

        } catch (Exception sos) {

            String msgError = handleMsgError("FES-GFE-005", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public byte[] generarFormatoExcelV6InventarioApertura(int int_inventarioID) throws Exception {

        try {

            InventarioEntity inventarioFilter = new InventarioEntity();
            inventarioFilter.setInventarioID(int_inventarioID);
            inventarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            List<InventarioEntity> inventarios = inventarioNegocio.select(inventarioFilter);

            if (CollectionUtils.isEmpty(inventarios)) {
                throw new Exception("Inventario " + int_inventarioID + " no existe");
            }

            InventarioAperturaEntity inventarioBienFilter = new InventarioAperturaEntity();
            inventarioBienFilter.setIndDel(Constantes.REGISTRO_ACTIVO);
            inventarioBienFilter.setEntidadID(inventarios.get(0).getEntidadID());
            inventarioBienFilter.setInventarioID(inventarios.get(0).getInventarioID());
            List<InventarioAperturaEntity> bienes = inventarioAperturaNegocio.select(inventarioBienFilter);

            if (CollectionUtils.isEmpty(bienes)) {
                throw new Exception("No hay bienes (inventario_apertura) para generar reporte");
            }

            return formatoExcelNegocio.generarFormatoExcelV6(bienes, inventarios.get(0).getEntidadID());

        } catch (Exception sos) {

            String msgError = handleMsgError("FES-GFE-006", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }
}
