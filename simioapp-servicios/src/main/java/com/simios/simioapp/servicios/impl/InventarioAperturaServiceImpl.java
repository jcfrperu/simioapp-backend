package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;
import com.simios.simioapp.servicios.InventarioAperturaService;
import com.simios.simioapp.negocio.InventarioAperturaNegocio;
import com.simios.simioapp.servicios.base.BaseService;

import java.util.Map;
import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("inventarioAperturaService")
public class InventarioAperturaServiceImpl extends BaseService implements InventarioAperturaService {

    private static final Logger log = Logger.getLogger(InventarioAperturaServiceImpl.class.getName());

    @Autowired
    @Qualifier("inventarioAperturaNegocio")
    private InventarioAperturaNegocio inventarioAperturaNegocio;

    public InventarioAperturaEntity selectByID(Integer inventarioAperturaID) throws Exception {

        try {

            return inventarioAperturaNegocio.selectByID(inventarioAperturaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBI-106", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<InventarioAperturaEntity> select(InventarioAperturaEntity filter) throws Exception {

        try {

            return inventarioAperturaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SEL-107", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<InventarioAperturaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return inventarioAperturaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBM-108", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return inventarioAperturaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBG-109", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(InventarioAperturaEntity entity) throws Exception {

        try {

            inventarioAperturaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-INS-110", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(InventarioAperturaEntity entity) throws Exception {

        try {

            inventarioAperturaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-UPD-111", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(InventarioAperturaEntity entity) throws Exception {

        try {

            inventarioAperturaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-DEL-112", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
