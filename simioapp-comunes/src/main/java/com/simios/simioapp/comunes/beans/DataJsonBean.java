package com.simios.simioapp.comunes.beans;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

public class DataJsonBean implements Serializable {

    private static final long serialVersionUID = 9221199608000723880L;

    private String estado;
    private String msg;
    private Object dataJson;

    public void setRespuesta(String estado, String msg, Object dataJson) {
        setEstado(estado);
        setMsg(msg);
        this.dataJson = dataJson;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado == null ? StringUtils.EMPTY : estado;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? StringUtils.EMPTY : msg;
    }

    public Object getDataJson() {
        return dataJson;
    }

}