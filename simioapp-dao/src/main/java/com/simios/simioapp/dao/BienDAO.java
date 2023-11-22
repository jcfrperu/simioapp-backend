package com.simios.simioapp.dao;

import java.util.List;
import java.util.Map;

import com.simios.simioapp.dominio.entidades.BienEntity;

public interface BienDAO {

    BienEntity selectByID(BienEntity entityID) throws Exception;

    List<BienEntity> select(BienEntity filter) throws Exception;

    List<BienEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(BienEntity entity) throws Exception;

    void update(BienEntity entity) throws Exception;

    void updateNullByCodigoPatrimonialEntidadID(BienEntity entity) throws Exception;

}
