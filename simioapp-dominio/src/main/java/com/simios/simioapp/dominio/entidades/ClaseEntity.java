package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class ClaseEntity extends AuditoriaFields {

    private Integer claseID; // clase_id
    private String clase; // clase
    private String descripcion; // descripcion

    public ClaseEntity() {

    }

    public Integer getClaseID() {
        return claseID;
    }

    public void setClaseID(Integer claseID) {
        this.claseID = claseID;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
