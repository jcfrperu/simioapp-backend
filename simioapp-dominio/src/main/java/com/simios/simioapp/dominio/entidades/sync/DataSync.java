package com.simios.simioapp.dominio.entidades.sync;

import java.io.Serializable;

public class DataSync implements Serializable {

    private TablaSync tabla;

    public TablaSync getTabla() {
        return tabla;
    }

    public void setTabla(TablaSync tabla) {
        this.tabla = tabla;
    }
}
