package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class OpcionSistemaEntity extends AuditoriaFields {

    private Integer opcionID; // opcion_id
    private String nombreOpcion; // nombre_opcion
    private Integer nivelOpcion; // nivel_opcion
    private Integer numeroOrdenOpcion; // numero_orden_opcion
    private String urlOpcion; // url_opcion
    private Integer parentOpcion; // parent_opcion

    public OpcionSistemaEntity() {

    }

    public Integer getOpcionID() {
        return opcionID;
    }

    public void setOpcionID(Integer opcionID) {
        this.opcionID = opcionID;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public Integer getNivelOpcion() {
        return nivelOpcion;
    }

    public void setNivelOpcion(Integer nivelOpcion) {
        this.nivelOpcion = nivelOpcion;
    }

    public Integer getNumeroOrdenOpcion() {
        return numeroOrdenOpcion;
    }

    public void setNumeroOrdenOpcion(Integer numeroOrdenOpcion) {
        this.numeroOrdenOpcion = numeroOrdenOpcion;
    }

    public String getUrlOpcion() {
        return urlOpcion;
    }

    public void setUrlOpcion(String urlOpcion) {
        this.urlOpcion = urlOpcion;
    }

    public Integer getParentOpcion() {
        return parentOpcion;
    }

    public void setParentOpcion(Integer parentOpcion) {
        this.parentOpcion = parentOpcion;
    }

}
