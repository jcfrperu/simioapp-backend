package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class ParametroEntity extends AuditoriaFields {

    private Integer parametroID; // parametro_id
    private String nombre; // nombre
    private String valor; // valor
    private String tipoValor; // tipo_valor
    private String descripcion; // descripcion

    public ParametroEntity() {

    }

    public Integer getParametroID() {
        return parametroID;
    }

    public void setParametroID(Integer parametroID) {
        this.parametroID = parametroID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipoValor() {
        return tipoValor;
    }

    public void setTipoValor(String tipoValor) {
        this.tipoValor = tipoValor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
