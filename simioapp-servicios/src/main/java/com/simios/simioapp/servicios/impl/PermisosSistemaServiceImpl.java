package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.PermisosSistemaEntity;
import com.simios.simioapp.negocio.PermisosSistemaNegocio;
import com.simios.simioapp.servicios.PermisosSistemaService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("permisosSistemaService")
public class PermisosSistemaServiceImpl extends BaseService implements PermisosSistemaService {

    private static final Logger log = Logger.getLogger(PermisosSistemaServiceImpl.class.getName());

    @Autowired
    @Qualifier("permisosSistemaNegocio")
    private PermisosSistemaNegocio permisosSistemaNegocio;

    public PermisosSistemaEntity selectByID(String usuarioID, Integer entidadID, Integer opcionID, Integer botonID) throws Exception {

        try {

            return permisosSistemaNegocio.selectByID(usuarioID, entidadID, opcionID, botonID);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SBI-092", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<PermisosSistemaEntity> select(PermisosSistemaEntity filter) throws Exception {

        try {

            return permisosSistemaNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SEL-093", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<PermisosSistemaEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return permisosSistemaNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SBM-094", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return permisosSistemaNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-SBG-095", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(PermisosSistemaEntity entity) throws Exception {

        try {

            permisosSistemaNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-INS-096", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(PermisosSistemaEntity entity) throws Exception {

        try {

            permisosSistemaNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-UPD-097", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(PermisosSistemaEntity entity) throws Exception {

        try {

            permisosSistemaNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("PSI-DEL-098", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
