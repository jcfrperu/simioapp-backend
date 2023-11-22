package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.ClaseEntity;

import java.util.List;
import java.util.Map;

public interface ClaseService {

    ClaseEntity selectByID(Integer claseID) throws Exception;

    List<ClaseEntity> select(ClaseEntity filter) throws Exception;

    List<ClaseEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(ClaseEntity entity) throws Exception;

    void update(ClaseEntity entity) throws Exception;

    void delete(ClaseEntity entity) throws Exception;

    List<ListaItem> selectClaseCombo(ClaseEntity filter) throws Exception;
}
