package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.EmpleadoUbicacionEntity;

import java.util.List;
import java.util.Map;

public interface EmpleadoUbicacionService {

    EmpleadoUbicacionEntity selectByID(Integer empleadoUbicacionID) throws Exception;

    List<EmpleadoUbicacionEntity> select(EmpleadoUbicacionEntity filter) throws Exception;

    List<EmpleadoUbicacionEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(EmpleadoUbicacionEntity entity) throws Exception;

    void update(EmpleadoUbicacionEntity entity) throws Exception;

    void delete(EmpleadoUbicacionEntity entity) throws Exception;

}
