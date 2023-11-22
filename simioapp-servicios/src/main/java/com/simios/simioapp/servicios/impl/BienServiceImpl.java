package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.negocio.BienNegocio;
import com.simios.simioapp.servicios.BienService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("bienService")
public class BienServiceImpl extends BaseService implements BienService {

    private static final Logger log = Logger.getLogger(BienServiceImpl.class.getName());

    @Autowired
    @Qualifier("bienNegocio")
    private BienNegocio bienNegocio;

    public BienEntity selectByID(Integer bienID) throws Exception {

        try {

            return bienNegocio.selectByID(bienID);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SBI-008", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<BienEntity> select(BienEntity filter) throws Exception {

        try {

            return bienNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SEL-009", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<BienEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return bienNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SBM-010", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return bienNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SBG-011", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(BienEntity entity) throws Exception {

        try {

            bienNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-INS-012", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(BienEntity entity) throws Exception {

        try {

            bienNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-UPD-013", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(BienEntity entity) throws Exception {

        try {

            bienNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-DEL-014", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
