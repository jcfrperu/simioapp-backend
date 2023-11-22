package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;

import java.util.List;
import java.util.Map;

public interface UsuarioService {

    UsuarioEntity selectByID(String usuarioID) throws Exception;

    List<UsuarioEntity> select(UsuarioEntity filter) throws Exception;

    List<UsuarioEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(UsuarioEntity entity) throws Exception;

    void update(UsuarioEntity entity) throws Exception;

    void delete(UsuarioEntity entity) throws Exception;

    List<ListaItem> listarUsuarios(UsuarioEntity filter) throws Exception;
}
