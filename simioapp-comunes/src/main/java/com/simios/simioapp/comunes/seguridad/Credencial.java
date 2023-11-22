package com.simios.simioapp.comunes.seguridad;

// clase que encapsula los atributos que conforman las credenciales de un usuario
public class Credencial {

    private String token;
    private String usuario;
    private String entidad;
    private String inventario;

    public Credencial(String token, String usuario, String entidad, String inventario) {
        this.token = token;
        this.usuario = usuario;
        this.entidad = entidad;
        this.inventario = inventario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

}
