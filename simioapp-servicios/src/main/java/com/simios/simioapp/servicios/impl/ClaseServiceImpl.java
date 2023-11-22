package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.ClaseEntity;
import com.simios.simioapp.negocio.ClaseNegocio;
import com.simios.simioapp.servicios.ClaseService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("claseService")
public class ClaseServiceImpl extends BaseService implements ClaseService {

    private static final Logger log = Logger.getLogger(ClaseServiceImpl.class.getName());

    @Autowired
    @Qualifier("claseNegocio")
    private ClaseNegocio claseNegocio;

    public ClaseEntity selectByID(Integer claseID) throws Exception {

        try {

            return claseNegocio.selectByID(claseID);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBI-036", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ClaseEntity> select(ClaseEntity filter) throws Exception {

        try {

            return claseNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SEL-037", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ClaseEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return claseNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBM-038", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return claseNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBG-039", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(ClaseEntity entity) throws Exception {

        try {

            claseNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-INS-040", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(ClaseEntity entity) throws Exception {

        try {

            claseNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-UPD-041", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(ClaseEntity entity) throws Exception {

        try {

            claseNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-DEL-042", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<ListaItem> selectClaseCombo(ClaseEntity filter) throws Exception {

        try {

            return claseNegocio.selectClaseCombo(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SCC-043", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
