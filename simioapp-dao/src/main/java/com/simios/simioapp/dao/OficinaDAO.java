package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.OficinaEntity;

public interface OficinaDAO {

    OficinaEntity selectByID(OficinaEntity entityID) throws Exception;

    List<OficinaEntity> select(OficinaEntity filter) throws Exception;

    List<OficinaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(OficinaEntity entity) throws Exception;

    void update(OficinaEntity entity) throws Exception;

}
