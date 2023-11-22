package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.AreaEntity;

public interface AreaDAO {

    AreaEntity selectByID(AreaEntity entityID) throws Exception;

    List<AreaEntity> select(AreaEntity filter) throws Exception;

    List<AreaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(AreaEntity entity) throws Exception;

    void update(AreaEntity entity) throws Exception;

    List<ListaItem> selectAreaCombo(AreaEntity filter) throws Exception;

}
