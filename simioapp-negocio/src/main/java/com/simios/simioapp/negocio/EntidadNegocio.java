package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.comparators.ComboComparatorByLabel;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.EntidadDAO;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("entidadNegocio")
public class EntidadNegocio extends BaseNegocio {

    @Autowired
    private EntidadDAO entidadDAO;

    public EntidadEntity selectByID(Integer entidadID) throws Exception {
        EntidadEntity entidad = new EntidadEntity();

        entidad.setEntidadID(entidadID);

        return entidadDAO.selectByID(entidad);
    }

    public List<EntidadEntity> select(EntidadEntity filter) throws Exception {

        return entidadDAO.select(filter);
    }

    public List<EntidadEntity> selectByMap(Map<String, Object> params) throws Exception {

        return entidadDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return entidadDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(EntidadEntity entity) throws Exception {

        entidadDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(EntidadEntity entity) throws Exception {

        entidadDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(EntidadEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        entidadDAO.update(entity);
    }

    public List<ListaItem> selectEntidadCombo(EntidadEntity filter) throws Exception {

        List<ListaItem> entidades = entidadDAO.selectEntidadCombo(filter);

        if (CollectionUtils.isNotEmpty(entidades)) {

            Collections.sort(entidades, new ComboComparatorByLabel());
        }

        return entidades;
    }

    public Map<Integer, EntidadEntity> selectKeyObject(EntidadEntity filter) throws Exception {

        List<EntidadEntity> entityList = entidadDAO.select(filter);

        HashMap<Integer, EntidadEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (EntidadEntity entity : entityList) {
                keyObjectMap.put(entity.getEntidadID(), entity);
            }
        }

        return keyObjectMap;
    }
}
