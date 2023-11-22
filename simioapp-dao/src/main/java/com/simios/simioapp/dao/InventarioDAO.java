package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.InventarioEntity;

import java.util.List;
import java.util.Map;

public interface InventarioDAO {

    InventarioEntity selectByID(InventarioEntity entityID) throws Exception;

    List<InventarioEntity> select(InventarioEntity filter) throws Exception;

    List<InventarioEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(InventarioEntity entity) throws Exception;

    void update(InventarioEntity entity) throws Exception;

    void updateFechasAperturaCierre(InventarioEntity entity) throws Exception;
}
