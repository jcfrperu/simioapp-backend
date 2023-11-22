package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class UsuarioEntity extends AuditoriaFields {

    private String usuarioID; // usuario_id
    private Integer entidadID; // entidad_id
    private String nombres; // nombres
    private String apellidos; // apellidos
    private Integer claveID; // clave_id

    public UsuarioEntity() {

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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getClaveID() {
        return claveID;
    }

    public void setClaveID(Integer claveID) {
        this.claveID = claveID;
    }

}
