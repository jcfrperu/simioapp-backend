package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.DependenciaEntity;

import java.util.List;
import java.util.Map;

public interface DependenciaService {

    DependenciaEntity selectByID(Integer dependenciaID) throws Exception;

    List<DependenciaEntity> select(DependenciaEntity filter) throws Exception;

    List<DependenciaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(DependenciaEntity entity) throws Exception;

    void update(DependenciaEntity entity) throws Exception;

    void delete(DependenciaEntity entity) throws Exception;

    List<ListaItem> selectDependenciaCombo(DependenciaEntity filtro) throws Exception;

}
