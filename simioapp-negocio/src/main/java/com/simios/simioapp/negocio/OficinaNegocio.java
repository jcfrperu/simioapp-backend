package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.comparators.ComboComparatorByLabel;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.OficinaDAO;
import com.simios.simioapp.dominio.entidades.OficinaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("oficinaNegocio")
public class OficinaNegocio extends BaseNegocio {

    @Autowired
    private OficinaDAO oficinaDAO;

    public OficinaEntity selectByID(Integer oficinaID) throws Exception {
        OficinaEntity oficina = new OficinaEntity();

        oficina.setOficinaID(oficinaID);

        return oficinaDAO.selectByID(oficina);
    }

    public List<OficinaEntity> select(OficinaEntity filter) throws Exception {

        return oficinaDAO.select(filter);
    }

    public List<OficinaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return oficinaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return oficinaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(OficinaEntity entity) throws Exception {

        oficinaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(OficinaEntity entity) throws Exception {

        oficinaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(OficinaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        oficinaDAO.update(entity);
    }

    public List<ListaItem> selectOficinaCombo(OficinaEntity filter) throws Exception {

        List<ListaItem> listaItem = new ArrayList<>();

        List<OficinaEntity> itemsEntidad = oficinaDAO.select(filter);

        if (CollectionUtils.isNotEmpty(itemsEntidad)) {

            for (OficinaEntity oficinaEntity : itemsEntidad) {

                ListaItem itemCombo = new ListaItem();

                itemCombo.setId(String.valueOf(oficinaEntity.getOficinaID()));
                itemCombo.setLabel(JS.toBlank(oficinaEntity.getNombreOficina()));

                listaItem.add(itemCombo);
            }

            Collections.sort(listaItem, new ComboComparatorByLabel());

        }

        return listaItem;

    }

    public Map<Integer, OficinaEntity> selectKeyObject(OficinaEntity filter) throws Exception {

        List<OficinaEntity> entityList = oficinaDAO.select(filter);

        HashMap<Integer, OficinaEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (OficinaEntity entity : entityList) {
                keyObjectMap.put(entity.getOficinaID(), entity);
            }
        }

        return keyObjectMap;
    }
}
