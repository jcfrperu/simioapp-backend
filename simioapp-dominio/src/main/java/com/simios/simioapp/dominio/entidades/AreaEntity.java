package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class AreaEntity extends AuditoriaFields {

    private Integer areaID; // area_id
    private String abreviaturaArea; // abreviatura_area
    private Integer entidadID; // entidad_id
    private Integer localesID; // locales_id
    private String nombreArea; // nombre_area

    public AreaEntity() {

    }

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public String getAbreviaturaArea() {
        return abreviaturaArea;
    }

    public void setAbreviaturaArea(String abreviaturaArea) {
        this.abreviaturaArea = abreviaturaArea;
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

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

}
