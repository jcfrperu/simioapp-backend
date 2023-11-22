package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.CuentaEntity;

public interface CuentaDAO {

    CuentaEntity selectByID(CuentaEntity entityID) throws Exception;

    List<CuentaEntity> select(CuentaEntity filter) throws Exception;

    List<CuentaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(CuentaEntity entity) throws Exception;

    void update(CuentaEntity entity) throws Exception;

    List<ListaItem> selectCuentaCombo(CuentaEntity filter) throws Exception;

}
