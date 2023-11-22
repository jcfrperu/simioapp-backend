package com.simios.simioapp.negocio;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.GrupoDAO;
import com.simios.simioapp.dominio.entidades.GrupoEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("grupoNegocio")
public class GrupoNegocio extends BaseNegocio {

    @Autowired
    private GrupoDAO grupoDAO;

    public GrupoEntity selectByID(Integer grupoID) throws Exception {
        GrupoEntity grupo = new GrupoEntity();

        grupo.setGrupoID(grupoID);

        return grupoDAO.selectByID(grupo);
    }

    public List<GrupoEntity> select(GrupoEntity filter) throws Exception {

        return grupoDAO.select(filter);
    }

    public List<GrupoEntity> selectByMap(Map<String, Object> params) throws Exception {

        return grupoDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return grupoDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(GrupoEntity entity) throws Exception {

        grupoDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(GrupoEntity entity) throws Exception {

        grupoDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(GrupoEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        grupoDAO.update(entity);
    }

    public List<ListaItem> selectGrupoCombo(GrupoEntity filter) throws Exception {

        ListaItem itemCombo = null;
        List<ListaItem> itemsCombo = new ArrayList<ListaItem>();

        List<GrupoEntity> grupos = grupoDAO.select(filter);

        if (CollectionUtils.isNotEmpty(grupos)) {

            for (GrupoEntity grupo : grupos) {

                itemCombo = new ListaItem();

                itemCombo.setId(String.valueOf(grupo.getGrupoID()));
                itemCombo.setLabel(grupo.getGrupo() + " - " + grupo.getDescripcion());

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
