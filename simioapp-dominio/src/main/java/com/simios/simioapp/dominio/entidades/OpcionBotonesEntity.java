package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class OpcionBotonesEntity extends AuditoriaFields {

    private Integer opcionID; // opcion_id
    private Integer botonID; // boton_id

    public OpcionBotonesEntity() {

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
