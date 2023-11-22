package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.comparators.ComboComparatorByLabel;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.AreaDAO;
import com.simios.simioapp.dominio.entidades.AreaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("areaNegocio")
public class AreaNegocio extends BaseNegocio {

    @Autowired
    private AreaDAO areaDAO;

    public AreaEntity selectByID(Integer areaID) throws Exception {
        AreaEntity area = new AreaEntity();

        area.setAreaID(areaID);

        return areaDAO.selectByID(area);
    }

    public List<AreaEntity> select(AreaEntity filter) throws Exception {

        return areaDAO.select(filter);
    }

    public List<AreaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return areaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return areaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(AreaEntity entity) throws Exception {

        areaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(AreaEntity entity) throws Exception {

        areaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(AreaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        areaDAO.update(entity);
    }

    public List<ListaItem> selectAreaCombo(AreaEntity filter) throws Exception {

        List<ListaItem> areas = areaDAO.selectAreaCombo(filter);

        if (CollectionUtils.isNotEmpty(areas)) {
            Collections.sort(areas, new ComboComparatorByLabel());
        }

        return areas;
    }


    public Map<Integer, AreaEntity> selectKeyObject(AreaEntity filter) throws Exception {

        List<AreaEntity> entityList = areaDAO.select(filter);

        HashMap<Integer, AreaEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (AreaEntity entity : entityList) {
                keyObjectMap.put(entity.getAreaID(), entity);
            }
        }

        return keyObjectMap;
    }
}
