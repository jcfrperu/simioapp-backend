package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.BotonesSistemaEntity;

import java.util.List;
import java.util.Map;

public interface BotonesSistemaService {

    BotonesSistemaEntity selectByID(Integer botonesSistemaID) throws Exception;

    List<BotonesSistemaEntity> select(BotonesSistemaEntity filter) throws Exception;

    List<BotonesSistemaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(BotonesSistemaEntity entity) throws Exception;

    void update(BotonesSistemaEntity entity) throws Exception;

    void delete(BotonesSistemaEntity entity) throws Exception;

}
