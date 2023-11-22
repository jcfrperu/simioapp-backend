package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.OpcionSistemaEntity;
import com.simios.simioapp.negocio.OpcionSistemaNegocio;
import com.simios.simioapp.servicios.OpcionSistemaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("opcionSistemaService")
public class OpcionSistemaServiceImpl extends BaseService implements OpcionSistemaService {

    private static final Logger log = Logger.getLogger(OpcionSistemaServiceImpl.class.getName());

    @Autowired
    @Qualifier("opcionSistemaNegocio")
    private OpcionSistemaNegocio opcionSistemaNegocio;

    public OpcionSistemaEntity selectByID(Integer opcionSistemaID) throws Exception {

        try {

            return opcionSistemaNegocio.selectByID(opcionSistemaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBI-078", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<OpcionSistemaEntity> select(OpcionSistemaEntity filter) throws Exception {

        try {

            return opcionSistemaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SEL-079", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<OpcionSistemaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return opcionSistemaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBM-080", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return opcionSistemaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBG-081", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(OpcionSistemaEntity entity) throws Exception {

        try {

            opcionSistemaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-INS-082", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(OpcionSistemaEntity entity) throws Exception {

        try {

            opcionSistemaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-UPD-083", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(OpcionSistemaEntity entity) throws Exception {

        try {

            opcionSistemaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-DEL-084", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
