package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.InventarioBienEntity;

import java.util.List;
import java.util.Map;

public interface InventarioBienDAO {

    InventarioBienEntity selectByID(InventarioBienEntity entityID) throws Exception;

    List<InventarioBienEntity> select(InventarioBienEntity filter) throws Exception;

    List<InventarioBienEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(InventarioBienEntity entity) throws Exception;

    void update(InventarioBienEntity entity) throws Exception;

    void updateNullByInventarioBienID(InventarioBienEntity entity) throws Exception;

    void updateNullByCodigoPatrimonialEntidadID(InventarioBienEntity entity) throws Exception;

    void updateAsignacion(InventarioBienEntity entity) throws Exception;

    void updateSobranteFaltante(InventarioBienEntity entity) throws Exception;

    void updateEstadoSubida(InventarioBienEntity entity) throws Exception;

    void updateBajaDisposicion(InventarioBienEntity entity) throws Exception;
}
