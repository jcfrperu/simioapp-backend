package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.ClaveEntity;
import java.util.Map;
import java.util.List;

public interface ClaveDAO {

    ClaveEntity selectByID(ClaveEntity entityID) throws Exception;

    List<ClaveEntity> select(ClaveEntity filter) throws Exception;

    List<ClaveEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(ClaveEntity entity) throws Exception;

    void update(ClaveEntity entity) throws Exception;

}
