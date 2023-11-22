package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.OpcionBotonesEntity;

import java.util.List;
import java.util.Map;

public interface OpcionBotonesService {

    OpcionBotonesEntity selectByID(Integer opcionBotonesID) throws Exception;

    List<OpcionBotonesEntity> select(OpcionBotonesEntity filter) throws Exception;

    List<OpcionBotonesEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(OpcionBotonesEntity entity) throws Exception;

    void update(OpcionBotonesEntity entity) throws Exception;

    void delete(OpcionBotonesEntity entity) throws Exception;

}
