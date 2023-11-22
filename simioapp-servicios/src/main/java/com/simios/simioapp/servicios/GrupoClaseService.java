package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.GrupoClaseEntity;

import java.util.List;
import java.util.Map;

public interface GrupoClaseService {

    GrupoClaseEntity selectByID(Integer grupoID, Integer claseID) throws Exception;

    List<GrupoClaseEntity> select(GrupoClaseEntity filter) throws Exception;

    List<GrupoClaseEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(GrupoClaseEntity entity) throws Exception;

    void update(GrupoClaseEntity entity) throws Exception;

    void delete(GrupoClaseEntity entity) throws Exception;

}
