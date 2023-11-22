package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.EmpleadoEntity;

public interface EmpleadoDAO {

    EmpleadoEntity selectByID(EmpleadoEntity entityID) throws Exception;

    List<EmpleadoEntity> select(EmpleadoEntity filter) throws Exception;

    List<EmpleadoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(EmpleadoEntity entity) throws Exception;

    void update(EmpleadoEntity entity) throws Exception;

}
