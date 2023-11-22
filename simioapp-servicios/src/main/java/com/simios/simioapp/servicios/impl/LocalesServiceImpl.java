package com.simios.simioapp.servicios.impl;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.LocalesEntity;
import com.simios.simioapp.negocio.LocalesNegocio;
import com.simios.simioapp.servicios.LocalesService;
import com.simios.simioapp.servicios.base.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service("localesService")
public class LocalesServiceImpl extends BaseService implements LocalesService {

    private static final Logger log = Logger.getLogger(LocalesServiceImpl.class.getName());

    @Autowired
    @Qualifier("localesNegocio")
    private LocalesNegocio localesNegocio;

    @Override
    public LocalesEntity selectByID(Integer localesID) throws Exception {

        try {

            return localesNegocio.selectByID(localesID);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-SBI-050", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<LocalesEntity> select(LocalesEntity filter) throws Exception {

        try {

            return localesNegocio.select(filter);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-SEL-051", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<LocalesEntity> selectByMap(Map<String, Object> params) throws Exception {

        try {

            return localesNegocio.selectByMap(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-SBM-052", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        try {

            return localesNegocio.selectByMapGrilla(params);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-SBG-053", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void insert(LocalesEntity entity) throws Exception {

        try {

            localesNegocio.insert(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-INS-054", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void update(LocalesEntity entity) throws Exception {

        try {

            localesNegocio.update(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-UPD-055", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public void delete(LocalesEntity entity) throws Exception {

        try {

            localesNegocio.delete(entity);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-DEL-056", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> listarLocales(LocalesEntity filter) throws Exception {

        try {

            List<ListaItem> listaItem = new ArrayList<ListaItem>();

            // servicio ya implementado con el framework
            List<LocalesEntity> itemsLocales = localesNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(itemsLocales)) {

                for (LocalesEntity entidad : itemsLocales) {

                    ListaItem itemCombo = new ListaItem();

                    itemCombo.setId(entidad.getLocalesID() + "");
                    itemCombo.setLabel(entidad.getNombreLocal());

                    listaItem.add(itemCombo);
                }

            }

            return listaItem;

        } catch (Exception sos) {

            String msgError = handleMsgError("ESI-SBI-057", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

    @Override
    public List<ListaItem> selectLocalesCombo(LocalesEntity filtro) throws Exception {

        try {

            return localesNegocio.selectLocalesCombo(filtro);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-SLC-058", sos);
            log.severe(msgError);
            throw handleError(sos);
        }

    }

    @Override
    public Integer buscarMaximoCodigo(Integer entidadID) throws Exception {

        try {

            return localesNegocio.buscarMaximoCodigo(entidadID);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-BMC-059", sos);
            log.severe(msgError);
            throw handleError(sos);
        }

    }

}
