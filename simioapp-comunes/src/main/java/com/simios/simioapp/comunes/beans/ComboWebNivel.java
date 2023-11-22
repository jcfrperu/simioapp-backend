package com.simios.simioapp.comunes.beans;


import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class ComboWebNivel implements Serializable {

    // id seleccionado
    private String selectedID;

    // default ID
    private String defaultID;

    // default label
    private String defaultLabel;

    public ComboWebNivel() {
        this(StringUtils.EMPTY, StringUtils.EMPTY, "(TODOS)");
    }

    public ComboWebNivel(String selectedID, String defaultID, String defaultLabel) {
        this.selectedID = selectedID;
        this.defaultID = defaultID;
        this.defaultLabel = defaultLabel;
    }

    public String getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(String selectedID) {
        this.selectedID = selectedID;
    }

    public String getDefaultID() {
        return defaultID;
    }

    public void setDefaultID(String defaultID) {
        this.defaultID = defaultID;
    }

    public String getDefaultLabel() {
        return defaultLabel;
    }

    public void setDefaultLabel(String defaultLabel) {
        this.defaultLabel = defaultLabel;
    }
}
