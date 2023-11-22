package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.CuentaEntity;

import java.util.List;
import java.util.Map;

public interface CuentaService {

    CuentaEntity selectByID(Integer cuentaID) throws Exception;

    List<CuentaEntity> select(CuentaEntity filter) throws Exception;

    List<CuentaEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(CuentaEntity entity) throws Exception;

    void update(CuentaEntity entity) throws Exception;

    void delete(CuentaEntity entity) throws Exception;

    List<ListaItem> selectCuentaCombo(CuentaEntity filtro) throws Exception;

}
