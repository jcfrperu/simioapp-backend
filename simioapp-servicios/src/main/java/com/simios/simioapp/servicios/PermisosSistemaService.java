package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.PermisosSistemaEntity;

import java.util.List;
import java.util.Map;

public interface PermisosSistemaService {

    PermisosSistemaEntity selectByID(String usuarioID, Integer entidadID, Integer opcionID, Integer botonID) throws Exception;

    List<PermisosSistemaEntity> select(PermisosSistemaEntity filter) throws Exception;

    List<PermisosSistemaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(PermisosSistemaEntity entity) throws Exception;

    void update(PermisosSistemaEntity entity) throws Exception;

    void delete(PermisosSistemaEntity entity) throws Exception;

}
