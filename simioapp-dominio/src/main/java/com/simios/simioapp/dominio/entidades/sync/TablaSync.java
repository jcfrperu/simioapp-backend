package com.simios.simioapp.dominio.entidades.sync;

import java.io.Serializable;
import java.util.List;

public class TablaSync implements Serializable {

    private List<InventarioBienSync> inventarioBien;
    private List<OficinaSync> oficina;
    private List<AreaSync> area;
    private List<LocalesSync> locales;
    private List<CatalogoSync> catalogo;

    public List<InventarioBienSync> getInventarioBien() {
        return inventarioBien;
    }

    public void setInventarioBien(List<InventarioBienSync> inventarioBien) {
        this.inventarioBien = inventarioBien;
    }

    public List<OficinaSync> getOficina() {
        return oficina;
    }

    public void setOficina(List<OficinaSync> oficina) {
        this.oficina = oficina;
    }

    public List<AreaSync> getArea() {
        return area;
    }

    public void setArea(List<AreaSync> area) {
        this.area = area;
    }

    public List<LocalesSync> getLocales() {
        return locales;
    }

    public void setLocales(List<LocalesSync> locales) {
        this.locales = locales;
    }

    public List<CatalogoSync> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(List<CatalogoSync> catalogo) {
        this.catalogo = catalogo;
    }
}
