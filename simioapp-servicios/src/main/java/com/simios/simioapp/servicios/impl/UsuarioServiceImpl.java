package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;
import com.simios.simioapp.negocio.UsuarioNegocio;
import com.simios.simioapp.servicios.UsuarioService;
import com.simios.simioapp.servicios.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("usuarioService")
public class UsuarioServiceImpl extends BaseService implements UsuarioService {

    private static final Logger log = Logger.getLogger(UsuarioServiceImpl.class.getName());

    @Autowired
    @Qualifier("usuarioNegocio")
    private UsuarioNegocio usuarioNegocio;

    public UsuarioEntity selectByID(String usuarioID) throws Exception {

        try {

            return usuarioNegocio.selectByID(usuarioID);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBI-113", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<UsuarioEntity> select(UsuarioEntity filter) throws Exception {

        try {

            return usuarioNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SEL-114", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<UsuarioEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return usuarioNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBM-115", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return usuarioNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-SBG-116", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(UsuarioEntity entity) throws Exception {

        try {

            usuarioNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-INS-117", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(UsuarioEntity entity) throws Exception {

        try {

            usuarioNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-UPD-118", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(UsuarioEntity entity) throws Exception {

        try {

            usuarioNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-DEL-119", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> listarUsuarios(UsuarioEntity filter) throws Exception {

        try {

            List<ListaItem> listaItem = new ArrayList<ListaItem>();

            List<UsuarioEntity> usuarios = usuarioNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(usuarios)) {

                for (UsuarioEntity usuario : usuarios) {

                    ListaItem itemCombo = new ListaItem();

                    itemCombo.setId(String.valueOf(usuario.getUsuarioID()));
                    itemCombo.setLabel(JS.toUpperBlank(usuario.getUsuarioID()) + " - " + JS.toUpperBlank(usuario.getNombres()) + " " + JS.toUpperBlank(usuario.getApellidos()));

                    listaItem.add(itemCombo);
                }

            }

            return listaItem;

        } catch (Exception sos) {

            String msgError = handleMsgError("USI-LU-120", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
