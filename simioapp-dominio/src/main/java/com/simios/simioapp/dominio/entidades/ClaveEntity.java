package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class ClaveEntity extends AuditoriaFields {

    private Integer claveID; // clave_id
    private String claveMetodo; // clave_metodo
    private String claveParams; // clave_params
    private String clave; // clave
    private byte[] claveBytes; // clave_bytes
    private Integer claveBytesLength; // clave_bytes_length

    public ClaveEntity() {

    }

    public Integer getClaveID() {
        return claveID;
    }

    public void setClaveID(Integer claveID) {
        this.claveID = claveID;
    }

    public String getClaveMetodo() {
        return claveMetodo;
    }

    public void setClaveMetodo(String claveMetodo) {
        this.claveMetodo = claveMetodo;
    }

    public String getClaveParams() {
        return claveParams;
    }

    public void setClaveParams(String claveParams) {
        this.claveParams = claveParams;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public byte[] getClaveBytes() {
        return claveBytes;
    }

    public void setClaveBytes(byte[] claveBytes) {
        this.claveBytes = claveBytes;
    }

    public Integer getClaveBytesLength() {
        return claveBytesLength;
    }

    public void setClaveBytesLength(Integer claveBytesLength) {
        this.claveBytesLength = claveBytesLength;
    }

}
