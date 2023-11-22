package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class GrupoClaseEntity extends AuditoriaFields {

    private Integer grupoID; // grupo_id
    private Integer claseID; // clase_id
    private String cuenta; // cuenta

    public GrupoClaseEntity() {

    }

    public Integer getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(Integer grupoID) {
        this.grupoID = grupoID;
    }

    public Integer getClaseID() {
        return claseID;
    }

    public void setClaseID(Integer claseID) {
        this.claseID = claseID;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

}
