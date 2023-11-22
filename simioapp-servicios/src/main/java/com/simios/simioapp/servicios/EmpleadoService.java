package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.EmpleadoEntity;

import java.util.List;
import java.util.Map;

public interface EmpleadoService {

    EmpleadoEntity selectByID(Integer empleadoID) throws Exception;

    List<EmpleadoEntity> select(EmpleadoEntity filter) throws Exception;

    List<EmpleadoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(EmpleadoEntity entity) throws Exception;

    void update(EmpleadoEntity entity) throws Exception;

    void delete(EmpleadoEntity entity) throws Exception;

    List<ListaItem> selectEmpleadoCombo(EmpleadoEntity filtro) throws Exception;
}
