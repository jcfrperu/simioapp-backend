package com.simios.simioapp.comunes.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComboWebRelacion implements Serializable {

    // configuracion de los niveles
    // (esta clase soporta hasta 4 combos en secuencia)
    private ComboWebNivel padre;
    private ComboWebNivel hijo;
    private ComboWebNivel nieto;
    private ComboWebNivel bisnieto;

    // items
    private List<ComboWebItemRelacion> items;

    public ComboWebRelacion() {
        padre = new ComboWebNivel();
        hijo = new ComboWebNivel();

        items = new ArrayList<ComboWebItemRelacion>();
    }

    public ComboWebNivel getPadre() {
        return padre;
    }

    public void setPadre(ComboWebNivel padre) {
        this.padre = padre;
    }

    public ComboWebNivel getHijo() {
        return hijo;
    }

    public void setHijo(ComboWebNivel hijo) {
        this.hijo = hijo;
    }

    public ComboWebNivel getNieto() {
        return nieto;
    }

    public void setNieto(ComboWebNivel nieto) {
        this.nieto = nieto;
    }

    public ComboWebNivel getBisnieto() {
        return bisnieto;
    }

    public void setBisnieto(ComboWebNivel bisnieto) {
        this.bisnieto = bisnieto;
    }

    public List<ComboWebItemRelacion> getItems() {
        return items;
    }

    public void setItems(List<ComboWebItemRelacion> items) {
        this.items = items;
    }
}
