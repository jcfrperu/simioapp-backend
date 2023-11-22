package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.DependenciaEntity;
import com.simios.simioapp.negocio.DependenciaNegocio;
import com.simios.simioapp.servicios.DependenciaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("dependenciaService")
public class DependenciaServiceImpl extends BaseService implements DependenciaService {

    private static final Logger log = Logger.getLogger(DependenciaServiceImpl.class.getName());

    @Autowired
    @Qualifier("dependenciaNegocio")
    private DependenciaNegocio dependenciaNegocio;

    public DependenciaEntity selectByID(Integer dependenciaID) throws Exception {

        try {

            return dependenciaNegocio.selectByID(dependenciaID);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-SBI-029", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<DependenciaEntity> select(DependenciaEntity filter) throws Exception {

        try {

            return dependenciaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-SEL-030", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<DependenciaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return dependenciaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-SBM-031", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return dependenciaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-SBG-032", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(DependenciaEntity entity) throws Exception {

        try {

            dependenciaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-INS-033", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(DependenciaEntity entity) throws Exception {

        try {

            dependenciaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-UPD-034", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(DependenciaEntity entity) throws Exception {

        try {

            dependenciaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("DSI-DEL-035", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectDependenciaCombo(DependenciaEntity filtro) throws Exception {

        return dependenciaNegocio.selectDependenciaCombo(filtro);
    }

}
