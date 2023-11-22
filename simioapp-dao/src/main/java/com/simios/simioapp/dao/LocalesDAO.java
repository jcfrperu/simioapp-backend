package com.simios.simioapp.dao;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.LocalesEntity;

import java.util.List;
import java.util.Map;

public interface LocalesDAO {

    LocalesEntity selectByID(LocalesEntity entityID) throws Exception;

    List<LocalesEntity> select(LocalesEntity filter) throws Exception;

    List<LocalesEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(LocalesEntity entity) throws Exception;

    void update(LocalesEntity entity) throws Exception;

    List<ListaItem> selectLocalesCombo(LocalesEntity filter) throws Exception;

    Integer buscarMaximoCodigo(LocalesEntity filter) throws Exception;

}
