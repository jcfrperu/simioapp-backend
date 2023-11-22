package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.UbigeoDAO;
import com.simios.simioapp.dominio.entidades.UbigeoEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("ubigeoNegocio")
public class UbigeoNegocio extends BaseNegocio {

    @Autowired
    private UbigeoDAO ubigeoDAO;

    public UbigeoEntity selectByID(Integer ubigeoID) throws Exception {
        UbigeoEntity ubigeo = new UbigeoEntity();

        ubigeo.setUbigeoID(ubigeoID);

        return ubigeoDAO.selectByID(ubigeo);
    }

    public List<UbigeoEntity> select(UbigeoEntity filter) throws Exception {

        return ubigeoDAO.select(filter);
    }

    public List<UbigeoEntity> selectByMap(Map<String, Object> params) throws Exception {

        return ubigeoDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return ubigeoDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(UbigeoEntity entity) throws Exception {

        ubigeoDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(UbigeoEntity entity) throws Exception {

        ubigeoDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(UbigeoEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        ubigeoDAO.update(entity);
    }

    public List<ListaItem> selectDepartamentoCombo(UbigeoEntity filter) throws Exception {
        return ubigeoDAO.selectDepartamentoCombo(filter);
    }

    public List<ListaItem> selectProvinciaCombo(UbigeoEntity filter) throws Exception {
        return ubigeoDAO.selectProvinciaCombo(filter);
    }

    public List<ListaItem> selectDistritoCombo(UbigeoEntity filter) throws Exception {
        return ubigeoDAO.selectDistritoCombo(filter);
    }


    public Map<Integer, UbigeoEntity> selectKeyObject(UbigeoEntity filter) throws Exception {

        List<UbigeoEntity> entityList = ubigeoDAO.select(filter);

        HashMap<Integer, UbigeoEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (UbigeoEntity entity : entityList) {
                keyObjectMap.put(entity.getUbigeoID(), entity);
            }
        }

        return keyObjectMap;
    }
}
