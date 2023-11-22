package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.EmpleadoUbicacionEntity;
import com.simios.simioapp.negocio.EmpleadoUbicacionNegocio;
import com.simios.simioapp.servicios.EmpleadoUbicacionService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("empleadoUbicacionService")
public class EmpleadoUbicacionServiceImpl extends BaseService implements EmpleadoUbicacionService {

    private static final Logger log = Logger.getLogger(EmpleadoUbicacionServiceImpl.class.getName());

    @Autowired
    @Qualifier("empleadoUbicacionNegocio")
    private EmpleadoUbicacionNegocio empleadoUbicacionNegocio;

    public EmpleadoUbicacionEntity selectByID(Integer empleadoUbicacionID) throws Exception {

        try {

            return empleadoUbicacionNegocio.selectByID(empleadoUbicacionID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBI-071", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<EmpleadoUbicacionEntity> select(EmpleadoUbicacionEntity filter) throws Exception {

        try {

            return empleadoUbicacionNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SEL-072", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<EmpleadoUbicacionEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return empleadoUbicacionNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBM-073", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return empleadoUbicacionNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBG-074", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(EmpleadoUbicacionEntity entity) throws Exception {

        try {

            empleadoUbicacionNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-INS-075", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(EmpleadoUbicacionEntity entity) throws Exception {

        try {

            empleadoUbicacionNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-UPD-076", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(EmpleadoUbicacionEntity entity) throws Exception {

        try {

            empleadoUbicacionNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-DEL-077", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
