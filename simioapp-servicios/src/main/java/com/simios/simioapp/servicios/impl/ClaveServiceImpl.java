package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.ClaveEntity;
import com.simios.simioapp.negocio.ClaveNegocio;
import com.simios.simioapp.servicios.ClaveService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("claveService")
public class ClaveServiceImpl extends BaseService implements ClaveService {

    private static final Logger log = Logger.getLogger(ClaveServiceImpl.class.getName());

    @Autowired
    @Qualifier("claveNegocio")
    private ClaveNegocio claveNegocio;

    public ClaveEntity selectByID(Integer claveID) throws Exception {

        try {

            return claveNegocio.selectByID(claveID);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBI-043", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ClaveEntity> select(ClaveEntity filter) throws Exception {

        try {

            return claveNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SEL-044", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ClaveEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return claveNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBM-045", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return claveNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBG-046", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(ClaveEntity entity) throws Exception {

        try {

            claveNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-INS-047", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(ClaveEntity entity) throws Exception {

        try {

            claveNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-UPD-048", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(ClaveEntity entity) throws Exception {

        try {

            claveNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-DEL-049", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
