package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class BotonesSistemaEntity extends AuditoriaFields {

    private Integer botonID; // boton_id
    private String nombreBoton; // nombre_boton
    private String keyBoton; // key_boton

    public BotonesSistemaEntity() {

    }

    public Integer getBotonID() {
        return botonID;
    }

    public void setBotonID(Integer botonID) {
        this.botonID = botonID;
    }

    public String getNombreBoton() {
        return nombreBoton;
    }

    public void setNombreBoton(String nombreBoton) {
        this.nombreBoton = nombreBoton;
    }

    public String getKeyBoton() {
        return keyBoton;
    }

    public void setKeyBoton(String keyBoton) {
        this.keyBoton = keyBoton;
    }

}
