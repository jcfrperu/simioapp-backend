package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.OficinaEntity;

import java.util.List;
import java.util.Map;

public interface OficinaService {

    OficinaEntity selectByID(Integer oficinaID) throws Exception;

    List<OficinaEntity> select(OficinaEntity filter) throws Exception;

    List<OficinaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(OficinaEntity entity) throws Exception;

    void update(OficinaEntity entity) throws Exception;

    void delete(OficinaEntity entity) throws Exception;

    List<ListaItem> selectOficinaCombo(OficinaEntity filter) throws Exception;
}
