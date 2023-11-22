package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.CatalogoBienEntity;

import java.util.List;
import java.util.Map;

public interface CatalogoBienService {

    CatalogoBienEntity selectByID(Integer catalogoBienID) throws Exception;

    List<CatalogoBienEntity> select(CatalogoBienEntity filter) throws Exception;

    List<CatalogoBienEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(CatalogoBienEntity entity) throws Exception;

    void update(CatalogoBienEntity entity) throws Exception;

    void delete(CatalogoBienEntity entity) throws Exception;

}
