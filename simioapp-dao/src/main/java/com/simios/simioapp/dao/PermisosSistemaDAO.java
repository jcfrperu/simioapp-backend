package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.PermisosSistemaEntity;

public interface PermisosSistemaDAO {

    PermisosSistemaEntity selectByID(PermisosSistemaEntity entityID) throws Exception;

    List<PermisosSistemaEntity> select(PermisosSistemaEntity filter) throws Exception;

    List<PermisosSistemaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(PermisosSistemaEntity entity) throws Exception;

    void update(PermisosSistemaEntity entity) throws Exception;

}
