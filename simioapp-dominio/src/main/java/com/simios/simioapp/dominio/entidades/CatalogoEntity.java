package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class CatalogoEntity extends AuditoriaFields {

    private Integer catalogoID; // catalogo_id
    private String catalogo; // catalogo
    private String datacatalogo; // datacatalogo
    private String tipo; // tipo
    private String descripcion; // descripcion
    private String descripcionCorta; // descripcion_corta

    public CatalogoEntity() {

    }

    public Integer getCatalogoID() {
        return catalogoID;
    }

    public void setCatalogoID(Integer catalogoID) {
        this.catalogoID = catalogoID;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public String getDatacatalogo() {
        return datacatalogo;
    }

    public void setDatacatalogo(String datacatalogo) {
        this.datacatalogo = datacatalogo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

}
