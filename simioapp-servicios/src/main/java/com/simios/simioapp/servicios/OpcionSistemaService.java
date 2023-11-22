package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.OpcionSistemaEntity;

import java.util.List;
import java.util.Map;

public interface OpcionSistemaService {

    OpcionSistemaEntity selectByID(Integer opcionSistemaID) throws Exception;

    List<OpcionSistemaEntity> select(OpcionSistemaEntity filter) throws Exception;

    List<OpcionSistemaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(OpcionSistemaEntity entity) throws Exception;

    void update(OpcionSistemaEntity entity) throws Exception;

    void delete(OpcionSistemaEntity entity) throws Exception;

}
