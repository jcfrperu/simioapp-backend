package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class PermisosSistemaEntity extends AuditoriaFields {

    private String usuarioID; // usuario_id
    private Integer entidadID; // entidad_id
    private Integer opcionID; // opcion_id
    private Integer botonID; // boton_id

    public PermisosSistemaEntity() {

    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public Integer getOpcionID() {
        return opcionID;
    }

    public void setOpcionID(Integer opcionID) {
        this.opcionID = opcionID;
    }

    public Integer getBotonID() {
        return botonID;
    }

    public void setBotonID(Integer botonID) {
        this.botonID = botonID;
    }

}
