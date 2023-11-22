package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.CatalogoBienEntity;

public interface CatalogoBienDAO {

    CatalogoBienEntity selectByID(CatalogoBienEntity entityID) throws Exception;

    List<CatalogoBienEntity> select(CatalogoBienEntity filter) throws Exception;

    List<CatalogoBienEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(CatalogoBienEntity entity) throws Exception;

    void update(CatalogoBienEntity entity) throws Exception;

}
