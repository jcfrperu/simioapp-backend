package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.ClaveEntity;

import java.util.List;
import java.util.Map;

public interface ClaveService {

    ClaveEntity selectByID(Integer claveID) throws Exception;

    List<ClaveEntity> select(ClaveEntity filter) throws Exception;

    List<ClaveEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(ClaveEntity entity) throws Exception;

    void update(ClaveEntity entity) throws Exception;

    void delete(ClaveEntity entity) throws Exception;

}
