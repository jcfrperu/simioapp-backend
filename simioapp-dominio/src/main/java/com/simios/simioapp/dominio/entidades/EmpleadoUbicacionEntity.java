package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class EmpleadoUbicacionEntity extends AuditoriaFields {

    private Integer empleadoUbicacionID; // empleado_ubicacion_id
    private Integer empleadoID; // empleado_id
    private Integer entidadID; // entidad_id
    private Integer localesID; // locales_id
    private Integer areaID; // area_id
    private Integer oficinaID; // oficina_id
    private String tipoAsociacion; // tipo_asociacion

    public EmpleadoUbicacionEntity() {

    }

    public Integer getEmpleadoUbicacionID() {
        return empleadoUbicacionID;
    }

    public void setEmpleadoUbicacionID(Integer empleadoUbicacionID) {
        this.empleadoUbicacionID = empleadoUbicacionID;
    }

    public Integer getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(Integer empleadoID) {
        this.empleadoID = empleadoID;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public Integer getLocalesID() {
        return localesID;
    }

    public void setLocalesID(Integer localesID) {
        this.localesID = localesID;
    }

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public Integer getOficinaID() {
        return oficinaID;
    }

    public void setOficinaID(Integer oficinaID) {
        this.oficinaID = oficinaID;
    }

    public String getTipoAsociacion() {
        return tipoAsociacion;
    }

    public void setTipoAsociacion(String tipoAsociacion) {
        this.tipoAsociacion = tipoAsociacion;
    }

}
