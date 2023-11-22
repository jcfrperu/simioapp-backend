package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;

import java.util.List;
import java.util.Map;

public interface UsuarioSessionService {

    UsuarioSessionEntity selectByID(String usuarioID) throws Exception;

    List<UsuarioSessionEntity> select(UsuarioSessionEntity filter) throws Exception;

    List<UsuarioSessionEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(UsuarioSessionEntity entity) throws Exception;

    void update(UsuarioSessionEntity entity) throws Exception;

    void delete(UsuarioSessionEntity entity) throws Exception;

}
