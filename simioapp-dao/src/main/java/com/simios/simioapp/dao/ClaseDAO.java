package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.ClaseEntity;
import java.util.Map;
import java.util.List;

public interface ClaseDAO {

    ClaseEntity selectByID(ClaseEntity entityID) throws Exception;

    List<ClaseEntity> select(ClaseEntity filter) throws Exception;

    List<ClaseEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(ClaseEntity entity) throws Exception;

    void update(ClaseEntity entity) throws Exception;

}
