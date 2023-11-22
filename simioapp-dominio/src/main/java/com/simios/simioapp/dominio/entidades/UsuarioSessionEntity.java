package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

import java.util.Date;

public class UsuarioSessionEntity extends AuditoriaFields {

    private String usuarioID; // usuario_id
    private Integer entidadID; // entidad_id
    private Date ultimoLogin; // ultimo_login
    private Date ultimoCierre; // ultimo_cierre
    private Date ultimaAccion; // ultima_accion
    private Integer minutosValidez; // minutos_validez
    private String estado; // estado
    private String indPoliticaInactividad; // ind_politica_inactividad
    private String datosIpLogin; // datos_ip_login
    private String datosIpAccion; // datos_ip_accion
    private String indPoliticaIntentos; // ind_politica_intentos
    private Integer numeroIntentos; // numero_intentos
    private String nombresUsuario; // nombres_usuario
    private String apellidosUsuario; // apellidos_usuario

    public UsuarioSessionEntity() {

    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Date getUltimoCierre() {
        return ultimoCierre;
    }

    public void setUltimoCierre(Date ultimoCierre) {
        this.ultimoCierre = ultimoCierre;
    }

    public Date getUltimaAccion() {
        return ultimaAccion;
    }

    public void setUltimaAccion(Date ultimaAccion) {
        this.ultimaAccion = ultimaAccion;
    }

    public Integer getMinutosValidez() {
        return minutosValidez;
    }

    public void setMinutosValidez(Integer minutosValidez) {
        this.minutosValidez = minutosValidez;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIndPoliticaInactividad() {
        return indPoliticaInactividad;
    }

    public void setIndPoliticaInactividad(String indPoliticaInactividad) {
        this.indPoliticaInactividad = indPoliticaInactividad;
    }

    public String getDatosIpLogin() {
        return datosIpLogin;
    }

    public void setDatosIpLogin(String datosIpLogin) {
        this.datosIpLogin = datosIpLogin;
    }

    public String getDatosIpAccion() {
        return datosIpAccion;
    }

    public void setDatosIpAccion(String datosIpAccion) {
        this.datosIpAccion = datosIpAccion;
    }

    public String getIndPoliticaIntentos() {
        return indPoliticaIntentos;
    }

    public void setIndPoliticaIntentos(String indPoliticaIntentos) {
        this.indPoliticaIntentos = indPoliticaIntentos;
    }

    public Integer getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(Integer numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

}
