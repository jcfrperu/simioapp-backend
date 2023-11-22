package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.UbigeoEntity;
import com.simios.simioapp.negocio.UbigeoNegocio;
import com.simios.simioapp.servicios.UbigeoService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("ubigeoService")
public class UbigeoServiceImpl extends BaseService implements UbigeoService {

    private static final Logger log = Logger.getLogger(UbigeoServiceImpl.class.getName());

    @Autowired
    @Qualifier("ubigeoNegocio")
    private UbigeoNegocio ubigeoNegocio;

    public UbigeoEntity selectByID(Integer ubigeoID) throws Exception {

        try {

            return ubigeoNegocio.selectByID(ubigeoID);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBI-078", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<UbigeoEntity> select(UbigeoEntity filter) throws Exception {

        try {

            return ubigeoNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SEL-079", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<UbigeoEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return ubigeoNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBM-080", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return ubigeoNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBG-081", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(UbigeoEntity entity) throws Exception {

        try {

            ubigeoNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-INS-082", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(UbigeoEntity entity) throws Exception {

        try {

            ubigeoNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-UPD-083", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(UbigeoEntity entity) throws Exception {

        try {

            ubigeoNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-DEL-084", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectDepartamentoCombo(UbigeoEntity filtro) throws Exception {

        return ubigeoNegocio.selectDepartamentoCombo(filtro);
    }

    @Override
    public List<ListaItem> selectProvinciaCombo(UbigeoEntity filtro) throws Exception {

        return ubigeoNegocio.selectProvinciaCombo(filtro);
    }

    @Override
    public List<ListaItem> selectDistritoCombo(UbigeoEntity filtro) throws Exception {

        return ubigeoNegocio.selectDistritoCombo(filtro);
    }

}
