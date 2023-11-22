package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class DependenciaEntity extends AuditoriaFields {

    private Integer dependenciaID; // dependencia_id
    private String codigoDependencia; // cod_dependencia
    private String descripcion; // descripcion

    public DependenciaEntity() {

    }

    public Integer getDependenciaID() {
        return dependenciaID;
    }

    public void setDependenciaID(Integer dependenciaID) {
        this.dependenciaID = dependenciaID;
    }

    public String getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
