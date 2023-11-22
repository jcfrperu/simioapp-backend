package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.CatalogoEntity;

import java.util.List;
import java.util.Map;

public interface CatalogoService {

    CatalogoEntity selectByID(Integer catalogoID) throws Exception;

    List<CatalogoEntity> select(CatalogoEntity filter) throws Exception;

    List<CatalogoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(CatalogoEntity entity) throws Exception;

    void update(CatalogoEntity entity) throws Exception;

    void delete(CatalogoEntity entity) throws Exception;

    List<ListaItem> selectCatalogoCombo(String catalogo, boolean soloDetalles, boolean soloActivos) throws Exception;
}
