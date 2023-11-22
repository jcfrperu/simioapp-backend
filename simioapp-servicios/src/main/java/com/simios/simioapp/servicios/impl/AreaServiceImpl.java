package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.AreaEntity;
import com.simios.simioapp.negocio.AreaNegocio;
import com.simios.simioapp.servicios.AreaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("areaService")
public class AreaServiceImpl extends BaseService implements AreaService {

    private static final Logger log = Logger.getLogger(AreaServiceImpl.class.getName());

    @Autowired
    @Qualifier("areaNegocio")
    private AreaNegocio areaNegocio;

    public AreaEntity selectByID(Integer areaID) throws Exception {

        try {

            return areaNegocio.selectByID(areaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-SBI-001", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<AreaEntity> select(AreaEntity filter) throws Exception {

        try {

            return areaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-SEL-002", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<AreaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return areaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-SBM-003", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return areaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-SBG-004", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(AreaEntity entity) throws Exception {

        try {

            areaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-INS-005", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(AreaEntity entity) throws Exception {

        try {

            areaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-UPD-006", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(AreaEntity entity) throws Exception {

        try {

            areaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-DEL-007", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectAreaCombo(AreaEntity filtro) throws Exception {

        try {

            return areaNegocio.selectAreaCombo(filtro);

        } catch (Exception sos) {

            String msgError = handleMsgError("ASI-SAC-008", sos);
            log.severe(msgError);
            throw handleError(sos);
        }


    }

}
