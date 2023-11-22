package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.DependenciaEntity;

public interface DependenciaDAO {

    DependenciaEntity selectByID(DependenciaEntity entityID) throws Exception;

    List<DependenciaEntity> select(DependenciaEntity filter) throws Exception;

    List<DependenciaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(DependenciaEntity entity) throws Exception;

    void update(DependenciaEntity entity) throws Exception;

    List<ListaItem> selectDependenciaCombo(DependenciaEntity filter) throws Exception;

}
