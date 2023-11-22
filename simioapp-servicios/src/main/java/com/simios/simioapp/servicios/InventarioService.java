package com.simios.simioapp.servicios;

import com.jcfr.utiles.ListaItem;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.dominio.entidades.InventarioEntity;
import com.simios.simioapp.dominio.entidades.sync.InventarioBienSync;

import java.util.List;
import java.util.Map;

public interface InventarioService {

    InventarioEntity selectByID(Integer inventarioID) throws Exception;

    List<InventarioEntity> select(InventarioEntity filter) throws Exception;

    List<InventarioEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(InventarioEntity entity) throws Exception;

    void update(InventarioEntity entity) throws Exception;

    void delete(InventarioEntity entity) throws Exception;

    void deleteConBienes(InventarioEntity entity) throws Exception;

    void updateFechasAperturaCierre(InventarioEntity entity) throws Exception;

    void aperturarInventario(InventarioEntity entity, UsuarioSession usuarioSession) throws Exception;

    List<ListaItem> listarInventarios(InventarioEntity filter) throws Exception;

    void cerrarInventario(InventarioEntity entity) throws Exception;

    void sincronizarMonoUsuario(List<InventarioBienSync> inventarioBienList, Credencial credencial) throws Exception;

    void sincronizarMultiUsuario(List<InventarioBienSync> inventarioBienList, Credencial credencial) throws Exception;
}

