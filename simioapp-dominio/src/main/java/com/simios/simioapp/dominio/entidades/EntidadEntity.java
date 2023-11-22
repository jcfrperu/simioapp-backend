package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class EntidadEntity extends AuditoriaFields {

    private Integer entidadID; // entidad_id
    private String codigoEntidad; // cod_entidad
    private String entidad; // entidad
    private Integer dependenciaID; // dependencia_id
    private String rucEntidad; // ruc_entidad

    public EntidadEntity() {

    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Integer getDependenciaID() {
        return dependenciaID;
    }

    public void setDependenciaID(Integer dependenciaID) {
        this.dependenciaID = dependenciaID;
    }

    public String getRucEntidad() {
        return rucEntidad;
    }

    public void setRucEntidad(String rucEntidad) {
        this.rucEntidad = rucEntidad;
    }

}
