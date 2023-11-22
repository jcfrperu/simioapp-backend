package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class OficinaEntity extends AuditoriaFields {

    private Integer oficinaID; // oficina_id
    private String abreviaturaOficina; // abreviatura_oficina
    private Integer entidadID; // entidad_id
    private Integer localesID; // locales_id
    private Integer areaID; // area_id
    private String nombreOficina; // nombre_oficina
    private String pisoOficina; // piso_oficina

    public OficinaEntity() {

    }

    public Integer getOficinaID() {
        return oficinaID;
    }

    public void setOficinaID(Integer oficinaID) {
        this.oficinaID = oficinaID;
    }

    public String getAbreviaturaOficina() {
        return abreviaturaOficina;
    }

    public void setAbreviaturaOficina(String abreviaturaOficina) {
        this.abreviaturaOficina = abreviaturaOficina;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public Integer getLocalesID() {
        return localesID;
    }

    public void setLocalesID(Integer localesID) {
        this.localesID = localesID;
    }

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public String getNombreOficina() {
        return nombreOficina;
    }

    public void setNombreOficina(String nombreOficina) {
        this.nombreOficina = nombreOficina;
    }

    public String getPisoOficina() {
        return pisoOficina;
    }

    public void setPisoOficina(String pisoOficina) {
        this.pisoOficina = pisoOficina;
    }

}
