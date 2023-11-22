package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.EntidadEntity;

import java.util.List;
import java.util.Map;

public interface EntidadService {

    EntidadEntity selectByID(Integer entidadID) throws Exception;

    List<EntidadEntity> select(EntidadEntity filter) throws Exception;

    List<EntidadEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(EntidadEntity entity) throws Exception;

    void update(EntidadEntity entity) throws Exception;

    void delete(EntidadEntity entity) throws Exception;

    List<ListaItem> listarEntidades(EntidadEntity filter) throws Exception;

    List<ListaItem> selectEntidadCombo(EntidadEntity filtro) throws Exception;

}
