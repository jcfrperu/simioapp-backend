package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.OpcionBotonesEntity;

public interface OpcionBotonesDAO {

    OpcionBotonesEntity selectByID(OpcionBotonesEntity entityID) throws Exception;

    List<OpcionBotonesEntity> select(OpcionBotonesEntity filter) throws Exception;

    List<OpcionBotonesEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(OpcionBotonesEntity entity) throws Exception;

    void update(OpcionBotonesEntity entity) throws Exception;

}
