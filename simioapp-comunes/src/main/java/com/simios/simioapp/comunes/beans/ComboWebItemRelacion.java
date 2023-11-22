package com.simios.simioapp.comunes.beans;


import com.jcfr.utiles.web.ComboWebItem;

import java.util.ArrayList;
import java.util.List;

public class ComboWebItemRelacion extends ComboWebItem {

    private List<ComboWebItemRelacion> relacionados;

    public ComboWebItemRelacion() {
        super("", "", true);
        relacionados = new ArrayList<ComboWebItemRelacion>();
    }

    public ComboWebItemRelacion(String id, String label) {
        super(id, label, true);
        relacionados = new ArrayList<ComboWebItemRelacion>();
    }

    public ComboWebItemRelacion(String id, String label, boolean enabled) {
        super(id, label, enabled);
        relacionados = new ArrayList<ComboWebItemRelacion>();
    }


}
