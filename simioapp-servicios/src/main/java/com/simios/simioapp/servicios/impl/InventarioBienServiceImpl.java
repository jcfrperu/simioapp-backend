package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import com.simios.simioapp.negocio.InventarioBienNegocio;
import com.simios.simioapp.servicios.InventarioBienService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Service("inventarioBienService")
public class InventarioBienServiceImpl extends BaseService implements InventarioBienService {

    private static final Logger log = Logger.getLogger(InventarioBienServiceImpl.class.getName());

    @Autowired
    @Qualifier("inventarioBienNegocio")
    private InventarioBienNegocio inventarioBienNegocio;

    public InventarioBienEntity selectByID(Integer inventarioBienID) throws Exception {

        try {

            return inventarioBienNegocio.selectByID(inventarioBienID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBI-092", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<InventarioBienEntity> select(InventarioBienEntity filter) throws Exception {

        try {

            return inventarioBienNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SEL-093", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<InventarioBienEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return inventarioBienNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBM-094", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return inventarioBienNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBG-095", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(InventarioBienEntity entity) throws Exception {

        try {

            inventarioBienNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-INS-096", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(InventarioBienEntity entity) throws Exception {

        try {

            inventarioBienNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-UPD-097", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(InventarioBienEntity entity) throws Exception {

        try {

            inventarioBienNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-DEL-098", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void asignarBienes(List<Integer> inventarioBienIDList, String usuarioID) throws Exception {
        try {

            inventarioBienNegocio.asignarBienes(inventarioBienIDList, usuarioID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-ABI-099", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }


    @Override
    public void desasignarBienes(List<Integer> inventarioBienIDList) throws Exception {
        try {

            inventarioBienNegocio.desasignarBienes(inventarioBienIDList);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-DAB-100", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void guardarSobranteFaltante(List<Integer> inventarioBienIDList, String sobranteFaltante) throws Exception {
        try {

            inventarioBienNegocio.guardarSobranteFaltante(inventarioBienIDList, sobranteFaltante);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-GSF-101", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void guardarEstadoSubida(List<Integer> inventarioBienIDList, String sobranteFaltante) throws Exception {
        try {

            inventarioBienNegocio.guardarEstadoSubida(inventarioBienIDList, sobranteFaltante);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-GES-102", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }
    
    @Override
    public void guardarBajaDisposicion(List<Integer> inventarioBienIDList, String resolucionBaja, String fechaResolucionBaja, String causalBaja, String numDocSBN, 
    		String resolucionDisposicion, String fechaResolucionDisposicion, String actoDisposicion, String donatorio) throws Exception {
        try {

            inventarioBienNegocio.guardarBajaDisposicion(inventarioBienIDList, resolucionBaja, fechaResolucionBaja, causalBaja, numDocSBN, 
            		resolucionDisposicion, fechaResolucionDisposicion, actoDisposicion, donatorio);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-GSF-103", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
