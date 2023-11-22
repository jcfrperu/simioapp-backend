package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.GrupoEntity;

import java.util.List;
import java.util.Map;

public interface GrupoService {

    GrupoEntity selectByID(Integer grupoID) throws Exception;

    List<GrupoEntity> select(GrupoEntity filter) throws Exception;

    List<GrupoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(GrupoEntity entity) throws Exception;

    void update(GrupoEntity entity) throws Exception;

    void delete(GrupoEntity entity) throws Exception;

    List<ListaItem> selectGrupoCombo(GrupoEntity filter) throws Exception;
}
