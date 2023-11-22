package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.GrupoClaseDAO;
import com.simios.simioapp.dominio.entidades.GrupoClaseEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("grupoClaseNegocio")
public class GrupoClaseNegocio extends BaseNegocio {

    @Autowired
    private GrupoClaseDAO grupoClaseDAO;

    public GrupoClaseEntity selectByID(Integer grupoID, Integer claseID) throws Exception {
        GrupoClaseEntity grupoClase = new GrupoClaseEntity();

        grupoClase.setGrupoID(grupoID);
        grupoClase.setClaseID(claseID);

        return grupoClaseDAO.selectByID(grupoClase);
    }

    public List<GrupoClaseEntity> select(GrupoClaseEntity filter) throws Exception {

        return grupoClaseDAO.select(filter);
    }

    public List<GrupoClaseEntity> selectByMap(Map<String, Object> params) throws Exception {

        return grupoClaseDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return grupoClaseDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(GrupoClaseEntity entity) throws Exception {

        grupoClaseDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(GrupoClaseEntity entity) throws Exception {

        grupoClaseDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(GrupoClaseEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        grupoClaseDAO.update(entity);
    }

}
