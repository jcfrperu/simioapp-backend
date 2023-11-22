package com.simios.simioapp.servicios;

import com.simios.simioapp.dominio.entidades.*;

import java.util.List;

public interface DescargadorService {

    List<AreaEntity> buscarTablaArea(int entidadID) throws Exception;

    List<BienEntity> buscarTablaBien(int entidadID) throws Exception;

    List<CatalogoEntity> buscarTablaCatalogo() throws Exception;

    List<CatalogoBienEntity> buscarTablaCatalogoBien() throws Exception;

    List<ClaseEntity> buscarTablaClase() throws Exception;

    List<CuentaEntity> buscarTablaCuenta() throws Exception;

    List<DependenciaEntity> buscarTablaDependencia() throws Exception;

    List<EmpleadoEntity> buscarTablaEmpleado(int entidadID) throws Exception;

    List<EmpleadoUbicacionEntity> buscarTablaEmpleadoUbicacion(int entidadID) throws Exception;

    List<EntidadEntity> buscarTablaEntidad(int entidadID) throws Exception;

    List<GrupoEntity> buscarTablaGrupo() throws Exception;

    List<GrupoClaseEntity> buscarTablaGrupoClase() throws Exception;

    List<LocalesEntity> buscarTablaLocales(int entidadID) throws Exception;

    List<OficinaEntity> buscarTablaOficina(int entidadID) throws Exception;

    List<ParametroEntity> buscarTablaParametro() throws Exception;

    List<UbigeoEntity> buscarTablaUbigeo() throws Exception;

    List<UsuarioEntity> buscarUsuario(int entidadID) throws Exception;

    List<InventarioEntity> buscarTablaInventario(int entidadID, int inventarioID) throws Exception;

    List<InventarioBienEntity> buscarTablaInventarioBien(int entidadID, int inventarioID, String inventariador) throws Exception;

    List<InventarioBienEntity> buscarTablaInventarioBien(int entidadID, int inventarioID) throws Exception;

    List<InventarioAperturaEntity> buscarTablaInventarioApertura(int entidadID, int inventarioID, String inventariador) throws Exception;

    List<InventarioAperturaEntity> buscarTablaInventarioApertura(int entidadID, int inventarioID) throws Exception;
}
