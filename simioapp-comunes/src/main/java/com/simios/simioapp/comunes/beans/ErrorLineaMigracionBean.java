package com.simios.simioapp.comunes.beans;

public class ErrorLineaMigracionBean {

    // nota: estos campos son solo descriptivos, no tienen ningun formato especifico, basta con que cumplan con su objetivo
    private String linea; // permite identificar la linea del dbf que no se pudo migrar (indicar los valores del campo clave en el dbf)
    private String descripcionError; // mensaje descriptivo con los motivos por los que no se pudo migrar la linea

    public ErrorLineaMigracionBean(String linea, String descripcionError) {
        this.linea = linea;
        this.descripcionError = descripcionError;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

}
