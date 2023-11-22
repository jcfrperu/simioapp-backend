package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.ParametroEntity;

public interface ParametroDAO {

    ParametroEntity selectByID(ParametroEntity entityID) throws Exception;

    List<ParametroEntity> select(ParametroEntity filter) throws Exception;

    List<ParametroEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(ParametroEntity entity) throws Exception;

    void update(ParametroEntity entity) throws Exception;

}
