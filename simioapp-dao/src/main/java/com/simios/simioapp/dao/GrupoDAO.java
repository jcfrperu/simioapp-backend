package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.GrupoEntity;
import java.util.Map;
import java.util.List;

public interface GrupoDAO {

    GrupoEntity selectByID(GrupoEntity entityID) throws Exception;

    List<GrupoEntity> select(GrupoEntity filter) throws Exception;

    List<GrupoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(GrupoEntity entity) throws Exception;

    void update(GrupoEntity entity) throws Exception;

}
