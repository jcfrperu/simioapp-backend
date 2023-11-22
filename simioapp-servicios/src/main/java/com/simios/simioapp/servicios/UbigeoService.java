package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.UbigeoEntity;

import java.util.List;
import java.util.Map;

public interface UbigeoService {

    UbigeoEntity selectByID(Integer ubigeoID) throws Exception;

    List<UbigeoEntity> select(UbigeoEntity filter) throws Exception;

    List<UbigeoEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(UbigeoEntity entity) throws Exception;

    void update(UbigeoEntity entity) throws Exception;

    void delete(UbigeoEntity entity) throws Exception;

    List<ListaItem> selectDepartamentoCombo(UbigeoEntity filtro) throws Exception;

    List<ListaItem> selectProvinciaCombo(UbigeoEntity filtro) throws Exception;

    List<ListaItem> selectDistritoCombo(UbigeoEntity filtro) throws Exception;

}
