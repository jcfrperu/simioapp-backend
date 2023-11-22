package com.simios.simioapp.dao;

import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;
import java.util.Map;
import java.util.List;

public interface UsuarioSessionDAO {

    UsuarioSessionEntity selectByID(UsuarioSessionEntity entityID) throws Exception;

    List<UsuarioSessionEntity> select(UsuarioSessionEntity filter) throws Exception;

    List<UsuarioSessionEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    Integer selectNextID() throws Exception;

    Integer selectCurrentID() throws Exception;

    void insert(UsuarioSessionEntity entity) throws Exception;

    void update(UsuarioSessionEntity entity) throws Exception;

}
