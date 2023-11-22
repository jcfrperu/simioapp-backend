package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.BienEntity;

import java.util.List;
import java.util.Map;

public interface BienService {

    BienEntity selectByID(Integer bienID) throws Exception;

    List<BienEntity> select(BienEntity filter) throws Exception;

    List<BienEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(BienEntity entity) throws Exception;

    void update(BienEntity entity) throws Exception;

    void delete(BienEntity entity) throws Exception;

}
