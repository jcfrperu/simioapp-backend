package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.GrupoEntity;
import com.simios.simioapp.negocio.GrupoNegocio;
import com.simios.simioapp.servicios.GrupoService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("grupoService")
public class GrupoServiceImpl extends BaseService implements GrupoService {

    private static final Logger log = Logger.getLogger(GrupoServiceImpl.class.getName());

    @Autowired
    @Qualifier("grupoNegocio")
    private GrupoNegocio grupoNegocio;

    public GrupoEntity selectByID(Integer grupoID) throws Exception {

        try {

            return grupoNegocio.selectByID(grupoID);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SBI-078", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<GrupoEntity> select(GrupoEntity filter) throws Exception {

        try {

            return grupoNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SEL-079", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<GrupoEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return grupoNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SBM-080", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return grupoNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-SBG-081", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(GrupoEntity entity) throws Exception {

        try {

            grupoNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-INS-082", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(GrupoEntity entity) throws Exception {

        try {

            grupoNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-UPD-083", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(GrupoEntity entity) throws Exception {

        try {

            grupoNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("GSI-DEL-084", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ListaItem> selectGrupoCombo(GrupoEntity filter) throws Exception {

        try {

            return grupoNegocio.selectGrupoCombo(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SGC-085", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }
}
