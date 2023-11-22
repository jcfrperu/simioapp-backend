package com.simios.simioapp.negocio;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.PermisosSistemaDAO;
import com.simios.simioapp.dominio.entidades.PermisosSistemaEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Component("permisosSistemaNegocio")
public class PermisosSistemaNegocio extends BaseNegocio {

    @Autowired
    private PermisosSistemaDAO permisosSistemaDAO;

    public PermisosSistemaEntity selectByID(String usuarioID, Integer entidadID, Integer opcionID, Integer botonID) throws Exception {
        PermisosSistemaEntity permisosSistema = new PermisosSistemaEntity();

        permisosSistema.setUsuarioID(usuarioID);
        permisosSistema.setEntidadID(entidadID);
        permisosSistema.setOpcionID(opcionID);
        permisosSistema.setBotonID(botonID);

        return permisosSistemaDAO.selectByID(permisosSistema);
    }

    public List<PermisosSistemaEntity> select(PermisosSistemaEntity filter) throws Exception {

        return permisosSistemaDAO.select(filter);
    }

    public List<PermisosSistemaEntity> selectByMap(Map<String, Object> params) throws Exception {

        return permisosSistemaDAO.selectByMap(params);
    }

    public List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception {

        return permisosSistemaDAO.selectByMapGrilla(params);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void insert(PermisosSistemaEntity entity) throws Exception {

        permisosSistemaDAO.insert(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void update(PermisosSistemaEntity entity) throws Exception {

        permisosSistemaDAO.update(entity);
    }

    @Transactional(rollbackFor=Throwable.class)
    public void delete(PermisosSistemaEntity entity) throws Exception {

        entity.setIndDel(Constantes.REGISTRO_INACTIVO);
        permisosSistemaDAO.update(entity);
    }

}
