package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.InventarioAperturaEntity;

import java.util.List;
import java.util.Map;

public interface InventarioAperturaService {

    InventarioAperturaEntity selectByID(Integer inventarioAperturaID) throws Exception;

    List<InventarioAperturaEntity> select(InventarioAperturaEntity filter) throws Exception;

    List<InventarioAperturaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(InventarioAperturaEntity entity) throws Exception;

    void update(InventarioAperturaEntity entity) throws Exception;

    void delete(InventarioAperturaEntity entity) throws Exception;

}
