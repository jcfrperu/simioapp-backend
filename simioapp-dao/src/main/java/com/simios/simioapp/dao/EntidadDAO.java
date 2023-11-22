package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.EntidadEntity;

public interface EntidadDAO {

    EntidadEntity selectByID(EntidadEntity entityID) throws Exception;

    List<EntidadEntity> select(EntidadEntity filter) throws Exception;

    List<EntidadEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(EntidadEntity entity) throws Exception;

    void update(EntidadEntity entity) throws Exception;

    List<ListaItem> selectEntidadCombo(EntidadEntity filter) throws Exception;

}
