package com.simios.simioapp.negocio.seguridad;

public class TokenData {

    private String usuario;
    private String entidad;
    private String inventario;
    private int minutosValidez;
    private long fechaInicioToken;
    private long fechaFinToken;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public int getMinutosValidez() {
        return minutosValidez;
    }

    public void setMinutosValidez(int minutosValidez) {
        this.minutosValidez = minutosValidez;
    }

    public long getFechaInicioToken() {
        return fechaInicioToken;
    }

    public void setFechaInicioToken(long fechaInicioToken) {
        this.fechaInicioToken = fechaInicioToken;
    }

    public long getFechaFinToken() {
        return fechaFinToken;
    }

    public void setFechaFinToken(long fechaFinToken) {
        this.fechaFinToken = fechaFinToken;
    }

}
