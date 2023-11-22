package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.GrupoClaseEntity;
import java.util.Map;
import java.util.List;

public interface GrupoClaseDAO {

    GrupoClaseEntity selectByID(GrupoClaseEntity entityID) throws Exception;

    List<GrupoClaseEntity> select(GrupoClaseEntity filter) throws Exception;

    List<GrupoClaseEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(GrupoClaseEntity entity) throws Exception;

    void update(GrupoClaseEntity entity) throws Exception;

}
