package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.GrupoClaseEntity;
import com.simios.simioapp.negocio.GrupoClaseNegocio;
import com.simios.simioapp.servicios.GrupoClaseService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("grupoClaseService")
public class GrupoClaseServiceImpl extends BaseService implements GrupoClaseService {

    private static final Logger log = Logger.getLogger(GrupoClaseServiceImpl.class.getName());

    @Autowired
    @Qualifier("grupoClaseNegocio")
    private GrupoClaseNegocio grupoClaseNegocio;

    public GrupoClaseEntity selectByID(Integer grupoID, Integer claseID) throws Exception {

        try {

            return grupoClaseNegocio.selectByID(grupoID, claseID);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SBI-085", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<GrupoClaseEntity> select(GrupoClaseEntity filter) throws Exception {

        try {

            return grupoClaseNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SEL-086", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<GrupoClaseEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return grupoClaseNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SBM-087", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return grupoClaseNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SBG-088", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(GrupoClaseEntity entity) throws Exception {

        try {

            grupoClaseNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-INS-089", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(GrupoClaseEntity entity) throws Exception {

        try {

            grupoClaseNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-UPD-090", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(GrupoClaseEntity entity) throws Exception {

        try {

            grupoClaseNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-DEL-091", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
