package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.ParametroEntity;

import java.util.List;
import java.util.Map;

public interface ParametroService {

    ParametroEntity selectByID(Integer parametroID) throws Exception;

    List<ParametroEntity> select(ParametroEntity filter) throws Exception;

    List<ParametroEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(ParametroEntity entity) throws Exception;

    void update(ParametroEntity entity) throws Exception;

    void delete(ParametroEntity entity) throws Exception;

}
