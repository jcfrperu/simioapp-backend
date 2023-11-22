package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.EmpleadoEntity;
import com.simios.simioapp.negocio.EmpleadoNegocio;
import com.simios.simioapp.servicios.EmpleadoService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("empleadoService")
public class EmpleadoServiceImpl extends BaseService implements EmpleadoService {

    private static final Logger log = Logger.getLogger(EmpleadoServiceImpl.class.getName());

    @Autowired
    @Qualifier("empleadoNegocio")
    private EmpleadoNegocio empleadoNegocio;

    @Override
    public EmpleadoEntity selectByID(Integer empleadoID) throws Exception {

        try {

            return empleadoNegocio.selectByID(empleadoID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBI-036", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<EmpleadoEntity> select(EmpleadoEntity filter) throws Exception {

        try {

            return empleadoNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SEL-037", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<EmpleadoEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return empleadoNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBM-038", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return empleadoNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBG-039", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void insert(EmpleadoEntity entity) throws Exception {

        try {

            empleadoNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-INS-040", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void update(EmpleadoEntity entity) throws Exception {

        try {

            empleadoNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-UPD-041", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void delete(EmpleadoEntity entity) throws Exception {

        try {

            empleadoNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-DEL-042", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectEmpleadoCombo(EmpleadoEntity filtro) throws Exception {

        try {

            return empleadoNegocio.selectEmpleadoCombo(filtro);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-SEC-043", sos);
            log.severe(msgError);
            throw handleError(sos);
        }

    }
}
