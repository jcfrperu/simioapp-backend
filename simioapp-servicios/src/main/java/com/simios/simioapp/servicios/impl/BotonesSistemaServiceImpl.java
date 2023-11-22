package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.BotonesSistemaEntity;
import com.simios.simioapp.negocio.BotonesSistemaNegocio;
import com.simios.simioapp.servicios.BotonesSistemaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("botonesSistemaService")
public class BotonesSistemaServiceImpl extends BaseService implements BotonesSistemaService {

    private static final Logger log = Logger.getLogger(BotonesSistemaServiceImpl.class.getName());

    @Autowired
    @Qualifier("botonesSistemaNegocio")
    private BotonesSistemaNegocio botonesSistemaNegocio;

    public BotonesSistemaEntity selectByID(Integer botonesSistemaID) throws Exception {

        try {

            return botonesSistemaNegocio.selectByID(botonesSistemaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SBI-015", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<BotonesSistemaEntity> select(BotonesSistemaEntity filter) throws Exception {

        try {

            return botonesSistemaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SEL-016", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<BotonesSistemaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return botonesSistemaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SBM-017", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return botonesSistemaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-SBG-018", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(BotonesSistemaEntity entity) throws Exception {

        try {

            botonesSistemaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-INS-019", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(BotonesSistemaEntity entity) throws Exception {

        try {

            botonesSistemaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-UPD-020", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(BotonesSistemaEntity entity) throws Exception {

        try {

            botonesSistemaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("BSI-DEL-021", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
