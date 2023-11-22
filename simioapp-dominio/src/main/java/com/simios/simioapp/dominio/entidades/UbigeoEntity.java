package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class UbigeoEntity extends AuditoriaFields {

    private Integer ubigeoID; // ubigeo_id
    private String codigoUbigeo; // cod_ubigeo
    private String descripcion; // descripcion

    public UbigeoEntity() {

    }

    public Integer getUbigeoID() {
        return ubigeoID;
    }

    public void setUbigeoID(Integer ubigeoID) {
        this.ubigeoID = ubigeoID;
    }

    public String getCodigoUbigeo() {
        return codigoUbigeo;
    }

    public void setCodigoUbigeo(String codigoUbigeo) {
        this.codigoUbigeo = codigoUbigeo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
