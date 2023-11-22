package com.simios.simioapp.comunes.beans;

public class MensajeLineaMigracionBean {

    private String linea;
    private String descripcionLinea;

    public MensajeLineaMigracionBean(String linea, String descripcionLinea) {
        this.linea = linea;
        this.descripcionLinea = descripcionLinea;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getDescripcionLinea() {
        return descripcionLinea;
    }

    public void setDescripcionLinea(String descripcionLinea) {
        this.descripcionLinea = descripcionLinea;
    }

}
