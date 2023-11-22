package com.simios.simioapp.dominio.entidades;

public class InventarioAperturaEntity extends BienEntity {

    private Integer inventarioAperturaID; // inventario_apertura_id
    private Integer inventarioID; // inventario_id
    private String estadoSubida; // estado_subida
    private String sobranteFaltante; // sobrante_faltante
    private String inventariador; // inventariador
    private String inventariadoPor; // inventariado_por
    private String revisadoToma; // revisado_toma
    private String inventariadorAnterior; // inventariador_anterior

    public Integer getInventarioAperturaID() {
        return inventarioAperturaID;
    }

    public void setInventarioAperturaID(Integer inventarioAperturaID) {
        this.inventarioAperturaID = inventarioAperturaID;
    }

    public Integer getInventarioID() {
        return inventarioID;
    }

    public void setInventarioID(Integer inventarioID) {
        this.inventarioID = inventarioID;
    }

    public String getEstadoSubida() {
        return estadoSubida;
    }

    public void setEstadoSubida(String estadoSubida) {
        this.estadoSubida = estadoSubida;
    }

    public String getSobranteFaltante() {
        return sobranteFaltante;
    }

    public void setSobranteFaltante(String sobranteFaltante) {
        this.sobranteFaltante = sobranteFaltante;
    }

    public String getInventariador() {
        return inventariador;
    }

    public void setInventariador(String inventariador) {
        this.inventariador = inventariador;
    }

    public String getInventariadoPor() {
        return inventariadoPor;
    }

    public void setInventariadoPor(String inventariadoPor) {
        this.inventariadoPor = inventariadoPor;
    }

    public String getRevisadoToma() {
        return revisadoToma;
    }

    public void setRevisadoToma(String revisadoToma) {
        this.revisadoToma = revisadoToma;
    }

    public String getInventariadorAnterior() {
        return inventariadorAnterior;
    }

    public void setInventariadorAnterior(String inventariadorAnterior) {
        this.inventariadorAnterior = inventariadorAnterior;
    }

}
