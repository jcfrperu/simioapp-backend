package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;

import java.util.List;
import java.util.Map;

public interface InventarioAperturaDAO {

    InventarioAperturaEntity selectByID(InventarioAperturaEntity entityID) throws Exception;

    List<InventarioAperturaEntity> select(InventarioAperturaEntity filter) throws Exception;

    List<InventarioAperturaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(InventarioAperturaEntity entity) throws Exception;

    void update(InventarioAperturaEntity entity) throws Exception;

}
