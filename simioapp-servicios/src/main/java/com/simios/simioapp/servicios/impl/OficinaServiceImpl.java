package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.OficinaEntity;
import com.simios.simioapp.negocio.OficinaNegocio;
import com.simios.simioapp.servicios.OficinaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("oficinaService")
public class OficinaServiceImpl extends BaseService implements OficinaService {

    private static final Logger log = Logger.getLogger(OficinaServiceImpl.class.getName());

    @Autowired
    @Qualifier("oficinaNegocio")
    private OficinaNegocio oficinaNegocio;

    public OficinaEntity selectByID(Integer oficinaID) throws Exception {

        try {

            return oficinaNegocio.selectByID(oficinaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBI-057", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<OficinaEntity> select(OficinaEntity filter) throws Exception {

        try {

            return oficinaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SEL-058", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<OficinaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return oficinaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBM-059", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return oficinaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SBG-060", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(OficinaEntity entity) throws Exception {

        try {

            oficinaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-INS-061", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(OficinaEntity entity) throws Exception {

        try {

            oficinaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-UPD-062", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(OficinaEntity entity) throws Exception {

        try {

            oficinaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-DEL-063", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectOficinaCombo(OficinaEntity filter) throws Exception {

        try {

            return oficinaNegocio.selectOficinaCombo(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("OSI-SOC-064", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
