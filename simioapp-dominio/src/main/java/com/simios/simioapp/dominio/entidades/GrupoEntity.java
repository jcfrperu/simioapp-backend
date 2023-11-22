package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class GrupoEntity extends AuditoriaFields {

    private Integer grupoID; // grupo_id
    private String grupo; // grupo
    private String descripcion; // descripcion

    public GrupoEntity() {

    }

    public Integer getGrupoID() {
        return grupoID;
    }

    public void setGrupoID(Integer grupoID) {
        this.grupoID = grupoID;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
