package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.CatalogoBienEntity;
import com.simios.simioapp.negocio.CatalogoBienNegocio;
import com.simios.simioapp.servicios.CatalogoBienService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("catalogoBienService")
public class CatalogoBienServiceImpl extends BaseService implements CatalogoBienService {

    private static final Logger log = Logger.getLogger(CatalogoBienServiceImpl.class.getName());

    @Autowired
    @Qualifier("catalogoBienNegocio")
    private CatalogoBienNegocio catalogoBienNegocio;

    public CatalogoBienEntity selectByID(Integer catalogoBienID) throws Exception {

        try {

            return catalogoBienNegocio.selectByID(catalogoBienID);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBI-029", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<CatalogoBienEntity> select(CatalogoBienEntity filter) throws Exception {

        try {

            return catalogoBienNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SEL-030", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<CatalogoBienEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return catalogoBienNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBM-031", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return catalogoBienNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-SBG-032", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(CatalogoBienEntity entity) throws Exception {

        try {

            catalogoBienNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-INS-033", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(CatalogoBienEntity entity) throws Exception {

        try {

            catalogoBienNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-UPD-034", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(CatalogoBienEntity entity) throws Exception {

        try {

            catalogoBienNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("CSI-DEL-035", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
