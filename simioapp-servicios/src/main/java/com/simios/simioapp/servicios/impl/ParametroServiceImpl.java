package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.ParametroEntity;
import com.simios.simioapp.negocio.ParametroNegocio;
import com.simios.simioapp.servicios.ParametroService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("parametroService")
public class ParametroServiceImpl extends BaseService implements ParametroService {

    private static final Logger log = Logger.getLogger(ParametroServiceImpl.class.getName());

    @Autowired
    @Qualifier("parametroNegocio")
    private ParametroNegocio parametroNegocio;

    public ParametroEntity selectByID(Integer parametroID) throws Exception {

        try {

            return parametroNegocio.selectByID(parametroID);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SBI-064", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ParametroEntity> select(ParametroEntity filter) throws Exception {

        try {

            return parametroNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SEL-065", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ParametroEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return parametroNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SBM-066", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return parametroNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SBG-067", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(ParametroEntity entity) throws Exception {

        try {

            parametroNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-INS-068", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(ParametroEntity entity) throws Exception {

        try {

            parametroNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-UPD-069", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(ParametroEntity entity) throws Exception {

        try {

            parametroNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-DEL-070", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
