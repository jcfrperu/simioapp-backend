package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.dominio.entidades.sync.InventarioBienSync;
import com.simios.simioapp.negocio.InventarioNegocio;
import com.simios.simioapp.negocio.SincronizarNegocio;
import com.simios.simioapp.servicios.InventarioService;
import com.simios.simioapp.servicios.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("inventarioService")
public class InventarioServiceImpl extends BaseService implements InventarioService {

    private static final Logger log = Logger.getLogger(InventarioServiceImpl.class.getName());

    @Autowired
    @Qualifier("inventarioNegocio")
    private InventarioNegocio inventarioNegocio;

    @Autowired
    @Qualifier("sincronizarNegocio")
    private SincronizarNegocio sincronizarNegocio;

    @Override
    public InventarioEntity selectByID(Integer inventarioID) throws Exception {

        try {

            return inventarioNegocio.selectByID(inventarioID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBI-085", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioEntity> select(InventarioEntity filter) throws Exception {

        try {

            return inventarioNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SEL-086", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<InventarioEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return inventarioNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBM-087", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return inventarioNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBG-088", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void insert(InventarioEntity entity) throws Exception {

        try {

            inventarioNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-INS-089", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void update(InventarioEntity entity) throws Exception {

        try {

            inventarioNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-UPD-090", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void delete(InventarioEntity entity) throws Exception {

        try {

            inventarioNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-DEL-091", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void updateFechasAperturaCierre(InventarioEntity entity) throws Exception {

        try {

            inventarioNegocio.updateFechasAperturaCierre(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-UPD-092", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }


    @Override
    public void aperturarInventario(InventarioEntity entity, UsuarioSession usuarioSession) throws Exception {

        try {

            inventarioNegocio.aperturarInventario(entity, usuarioSession);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-UPD-093", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }


    @Override
    public List<ListaItem> listarInventarios(InventarioEntity filter) throws Exception {

        try {

            List<ListaItem> listaItem = new ArrayList<>();

            List<InventarioEntity> itemsInventario = inventarioNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(itemsInventario)) {

                for (InventarioEntity inventario : itemsInventario) {

                    ListaItem itemCombo = new ListaItem();

                    itemCombo.setId(String.valueOf(inventario.getInventarioID()));
                    itemCombo.setLabel(inventario.getNombre());

                    listaItem.add(itemCombo);
                }

            }

            return listaItem;

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBI-94", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void cerrarInventario(InventarioEntity entity) throws Exception {

        try {

            inventarioNegocio.cerrarInventario(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SBI-095", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void deleteConBienes(InventarioEntity entity) throws Exception {

        try {

            inventarioNegocio.deleteConBienes(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-DCB-096", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void sincronizarMonoUsuario(List<InventarioBienSync> inventarioBienList, Credencial credencial) throws Exception {

        try {

            sincronizarNegocio.sincronizarMonoUsuario(inventarioBienList, credencial);

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SMU-098", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void sincronizarMultiUsuario(List<InventarioBienSync> inventarioBienList, Credencial credencial) throws Exception {

        try {

            // sincronizarNegocio.sincronizarMultiUsuario(inventarioBienList, credencial);
            // TODO/FIXME: aun no esta implementado, primero mono usuario pe!

        } catch (Exception sos) {

            String msgError = handleMsgError("ISI-SMU-099", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }
}
