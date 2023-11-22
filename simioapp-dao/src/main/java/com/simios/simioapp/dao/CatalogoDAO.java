package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.CatalogoEntity;

public interface CatalogoDAO {

    CatalogoEntity selectByID(CatalogoEntity entityID) throws Exception;

    List<CatalogoEntity> select(CatalogoEntity filter) throws Exception;

    List<CatalogoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(CatalogoEntity entity) throws Exception;

    void update(CatalogoEntity entity) throws Exception;

    List<ListaItem> selectCatalogoCombo(CatalogoEntity filter) throws Exception;

}
