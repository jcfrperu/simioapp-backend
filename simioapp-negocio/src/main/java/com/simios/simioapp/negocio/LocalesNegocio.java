package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.comparators.ComboComparatorByLabel;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.LocalesDAO;
import com.simios.simioapp.dominio.entidades.LocalesEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("localesNegocio")
public class LocalesNegocio extends BaseNegocio {

    @Autowired
    private LocalesDAO localesDAO;

    public LocalesEntity selectByID(Integer localesID) throws Exception {
        LocalesEntity locales = new LocalesEntity();

        locales.setLocalesID(localesID);

        return localesDAO.selectByID(locales);
    }

    public List<LocalesEntity> select(LocalesEntity filter) throws Exception {

        return localesDAO.select(filter);
    }

    public List<LocalesEntity> selectByMap(Map<String, Object> params) throws Exception {

        return localesDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return localesDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(LocalesEntity entity) throws Exception {

        localesDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(LocalesEntity entity) throws Exception {

        localesDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(LocalesEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        localesDAO.update(entity);
    }

    public List<ListaItem> selectLocalesCombo(LocalesEntity filter) throws Exception {

        List<ListaItem> locales = localesDAO.selectLocalesCombo(filter);

        if (CollectionUtils.isNotEmpty(locales)) {

            Collections.sort(locales, new ComboComparatorByLabel());
        }

        return locales;
    }

    public Integer buscarMaximoCodigo(Integer entidadID) throws Exception {

        LocalesEntity filter = new LocalesEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setEntidadID(entidadID);

        Integer maximo = localesDAO.buscarMaximoCodigo(filter);

        return maximo == null ? Constantes.CERO_INT : maximo;
    }

    public Map<Integer, LocalesEntity> selectKeyObject(LocalesEntity filter) throws Exception {

        List<LocalesEntity> entityList = localesDAO.select(filter);

        HashMap<Integer, LocalesEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (LocalesEntity entity : entityList) {
                keyObjectMap.put(entity.getLocalesID(), entity);
            }
        }

        return keyObjectMap;
    }
}
