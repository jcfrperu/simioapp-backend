package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.AreaEntity;

import java.util.List;
import java.util.Map;

public interface AreaService {

    AreaEntity selectByID(Integer areaID) throws Exception;

    List<AreaEntity> select(AreaEntity filter) throws Exception;

    List<AreaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(AreaEntity entity) throws Exception;

    void update(AreaEntity entity) throws Exception;

    void delete(AreaEntity entity) throws Exception;

    List<ListaItem> selectAreaCombo(AreaEntity filtro) throws Exception;

}
