package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.OpcionSistemaEntity;

public interface OpcionSistemaDAO {

    OpcionSistemaEntity selectByID(OpcionSistemaEntity entityID) throws Exception;

    List<OpcionSistemaEntity> select(OpcionSistemaEntity filter) throws Exception;

    List<OpcionSistemaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(OpcionSistemaEntity entity) throws Exception;

    void update(OpcionSistemaEntity entity) throws Exception;

}
