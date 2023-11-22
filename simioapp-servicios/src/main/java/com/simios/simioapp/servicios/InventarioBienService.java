package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.InventarioBienEntity;

import java.util.List;
import java.util.Map;

public interface InventarioBienService {

    InventarioBienEntity selectByID(Integer inventarioBienID) throws Exception;

    List<InventarioBienEntity> select(InventarioBienEntity filter) throws Exception;

    List<InventarioBienEntity> selectByMap(Map<String, Object> params) throws Exception;

    List<Map<String, Object>> selectByMapGrilla(Map<String, Object> params) throws Exception;

    void insert(InventarioBienEntity entity) throws Exception;

    void update(InventarioBienEntity entity) throws Exception;

    void delete(InventarioBienEntity entity) throws Exception;

    void asignarBienes(List<Integer> inventarioBienIDList, String usuarioID) throws Exception;

    void desasignarBienes(List<Integer> inventarioBienIDList) throws Exception;

    void guardarSobranteFaltante(List<Integer> inventarioBienIDList, String sobranteFaltante) throws Exception;

    void guardarEstadoSubida(List<Integer> inventarioBienIDList, String estadoSubida) throws Exception;

    void guardarBajaDisposicion(List<Integer> inventarioBienIDList, String resolucionBaja, String fechaResolucionBaja, String causalBaja, String numDocSBN,
                                String resolucionDisposicion, String fechaResolucionDisposicion, String actoDisposicion, String donatorio) throws Exception;


}
