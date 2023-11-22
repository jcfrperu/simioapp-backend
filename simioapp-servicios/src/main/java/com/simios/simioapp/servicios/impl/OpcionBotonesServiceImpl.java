package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.OpcionBotonesEntity;
import com.simios.simioapp.negocio.OpcionBotonesNegocio;
import com.simios.simioapp.servicios.OpcionBotonesService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("opcionBotonesService")
public class OpcionBotonesServiceImpl extends BaseService implements OpcionBotonesService {

    private static final Logger log = Logger.getLogger(OpcionBotonesServiceImpl.class.getName());

    @Autowired
    @Qualifier("opcionBotonesNegocio")
    private OpcionBotonesNegocio opcionBotonesNegocio;

    public OpcionBotonesEntity selectByID(Integer opcionBotonesID) throws Exception {

        try {

            return opcionBotonesNegocio.selectByID(opcionBotonesID);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBI-071", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<OpcionBotonesEntity> select(OpcionBotonesEntity filter) throws Exception {

        try {

            return opcionBotonesNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SEL-072", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<OpcionBotonesEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return opcionBotonesNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBM-073", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return opcionBotonesNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBG-074", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(OpcionBotonesEntity entity) throws Exception {

        try {

            opcionBotonesNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-INS-075", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(OpcionBotonesEntity entity) throws Exception {

        try {

            opcionBotonesNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-UPD-076", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(OpcionBotonesEntity entity) throws Exception {

        try {

            opcionBotonesNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-DEL-077", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
