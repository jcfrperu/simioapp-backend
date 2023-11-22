package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.negocio.EntidadNegocio;
import com.simios.simioapp.servicios.EntidadService;
import com.simios.simioapp.servicios.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("entidadService")
public class EntidadServiceImpl extends BaseService implements EntidadService {

    private static final Logger log = Logger.getLogger(EntidadServiceImpl.class.getName());

    @Autowired
    @Qualifier("entidadNegocio")
    private EntidadNegocio entidadNegocio;

    public EntidadEntity selectByID(Integer entidadID) throws Exception {

        try {

            return entidadNegocio.selectByID(entidadID);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBI-043", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<EntidadEntity> select(EntidadEntity filter) throws Exception {

        try {

            return entidadNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SEL-044", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<EntidadEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return entidadNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBM-045", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return entidadNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBG-046", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void insert(EntidadEntity entity) throws Exception {

        try {

            entidadNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-INS-047", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void update(EntidadEntity entity) throws Exception {

        try {

            entidadNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-UPD-048", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    public void delete(EntidadEntity entity) throws Exception {

        try {

            entidadNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-DEL-049", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> listarEntidades(EntidadEntity filter) throws Exception {

        try {

            List<ListaItem> listaItem = new ArrayList<ListaItem>();

            // servicio ya implementado con el framework
            List<EntidadEntity> itemsEntidad = entidadNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(itemsEntidad)) {

                for (EntidadEntity entidad : itemsEntidad) {

                    ListaItem itemCombo = new ListaItem();

                    itemCombo.setId(entidad.getEntidadID() + "");
                    itemCombo.setLabel(entidad.getEntidad());

                    listaItem.add(itemCombo);
                }

            }

            return listaItem;

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBI-043", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectEntidadCombo(EntidadEntity filtro) throws Exception {

        try {

            return entidadNegocio.selectEntidadCombo(filtro);

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SEC-044", sos);
            log.severe(msgError);
            throw handleError(sos);
        }

    }

}
