package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.LocalesEntity;

import java.util.List;
import java.util.Map;

public interface LocalesService {

    LocalesEntity selectByID(Integer localesID) throws Exception;

    List<LocalesEntity> select(LocalesEntity filter) throws Exception;

    List<LocalesEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(LocalesEntity entity) throws Exception;

    void update(LocalesEntity entity) throws Exception;

    void delete(LocalesEntity entity) throws Exception;

    List<ListaItem> listarLocales(LocalesEntity filter) throws Exception;

    List<ListaItem> selectLocalesCombo(LocalesEntity filtro) throws Exception;

    Integer buscarMaximoCodigo(Integer entidadID) throws Exception;
}
