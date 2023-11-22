package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

import java.util.Date;

public class InventarioEntity extends AuditoriaFields {

    private Integer inventarioID; // inventario_id
    private String nombre; // nombre
    private Date fechaApertura; // fecha_apertura
    private Date fechaCierre; // fecha_cierre
    private String estado; // estado
    private Integer entidadID; // entidad_id

    public InventarioEntity() {

    }

    public Integer getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

}