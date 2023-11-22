package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;
import com.simios.simioapp.negocio.UsuarioSessionNegocio;
import com.simios.simioapp.servicios.UsuarioSessionService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("usuarioSessionService")
public class UsuarioSessionServiceImpl extends BaseService implements UsuarioSessionService {

    private static final Logger log = Logger.getLogger(UsuarioSessionServiceImpl.class.getName());

    @Autowired
    @Qualifier("usuarioSessionNegocio")
    private UsuarioSessionNegocio usuarioSessionNegocio;

    public UsuarioSessionEntity selectByID(String usuarioID) throws Exception {

        try {

            return usuarioSessionNegocio.selectByID(usuarioID);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBI-162", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<UsuarioSessionEntity> select(UsuarioSessionEntity filter) throws Exception {

        try {

            return usuarioSessionNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SEL-163", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<UsuarioSessionEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return usuarioSessionNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBM-164", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return usuarioSessionNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBG-165", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(UsuarioSessionEntity entity) throws Exception {

        try {

            usuarioSessionNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-INS-166", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(UsuarioSessionEntity entity) throws Exception {

        try {

            usuarioSessionNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-UPD-167", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(UsuarioSessionEntity entity) throws Exception {

        try {

            usuarioSessionNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-DEL-168", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
