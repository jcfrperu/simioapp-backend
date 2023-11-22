package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.UbigeoEntity;

public interface UbigeoDAO {

    UbigeoEntity selectByID(UbigeoEntity entityID) throws Exception;

    List<UbigeoEntity> select(UbigeoEntity filter) throws Exception;

    List<UbigeoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(UbigeoEntity entity) throws Exception;

    void update(UbigeoEntity entity) throws Exception;

    List<ListaItem> selectDepartamentoCombo(UbigeoEntity filter) throws Exception;

    List<ListaItem> selectProvinciaCombo(UbigeoEntity filter) throws Exception;

    List<ListaItem> selectDistritoCombo(UbigeoEntity filter) throws Exception;

}
