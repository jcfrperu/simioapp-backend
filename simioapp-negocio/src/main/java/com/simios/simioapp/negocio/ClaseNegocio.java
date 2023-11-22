package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.ClaseDAO;
import com.simios.simioapp.dominio.entidades.ClaseEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("claseNegocio")
public class ClaseNegocio extends BaseNegocio {

    @Autowired
    private ClaseDAO claseDAO;

    public ClaseEntity selectByID(Integer claseID) throws Exception {
        ClaseEntity clase = new ClaseEntity();

        clase.setClaseID(claseID);

        return claseDAO.selectByID(clase);
    }

    public List<ClaseEntity> select(ClaseEntity filter) throws Exception {

        return claseDAO.select(filter);
    }

    public List<ClaseEntity> selectByMap(Map<String, Object> params) throws Exception {

        return claseDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return claseDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(ClaseEntity entity) throws Exception {

        claseDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(ClaseEntity entity) throws Exception {

        claseDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(ClaseEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        claseDAO.update(entity);
    }

    public List<ListaItem> selectClaseCombo(ClaseEntity filter) throws Exception {

        ListaItem itemCombo = null;
        List<ListaItem> itemsCombo = new ArrayList<ListaItem>();

        List<ClaseEntity> clases = claseDAO.select(filter);

        if (CollectionUtils.isNotEmpty(clases)) {

            for (ClaseEntity clase : clases) {

                itemCombo = new ListaItem();

                itemCombo.setId(String.valueOf(clase.getClaseID()));
                itemCombo.setLabel(clase.getClase() + " - " + clase.getDescripcion());

                itemsCombo.add(itemCombo);
            }
        }

        // ordenar el combo por el label
        Collections.sort(itemsCombo, new Comparator<ListaItem>() {

            @Override
            public int compare(ListaItem item01, ListaItem item02) {
                if (item01 == null && item02 == null) return 0;
                if (item01 == null && item02 != null) return -1;
                if (item01 != null && item02 == null) return 1;

                return item01.getLabel().compareTo(item02.getLabel());
            }

        });

        return itemsCombo;
    }
}
