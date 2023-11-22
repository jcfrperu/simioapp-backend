package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class EmpleadoEntity extends AuditoriaFields {

    private Integer empleadoID; // empleado_id
    private String codigo; // codigo
    private Integer entidadID; // entidad_id
    private String numeroDocIdentPersonal; // nro_doc_ident_personal
    private String tipoDocIdentidad; // tipo_doc_identidad
    private String nombres; // nombres
    private String apellidos; // apellidos
    private String modalidadContrato; // modalidad_contrato
    private String apellidoPaterno; // apellido_paterno
    private String apellidoMaterno; // apellido_materno

    public EmpleadoEntity() {

    }

    public Integer getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(Integer empleadoID) {
        this.empleadoID = empleadoID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public String getNumeroDocIdentPersonal() {
        return numeroDocIdentPersonal;
    }

    public void setNumeroDocIdentPersonal(String numeroDocIdentPersonal) {
        this.numeroDocIdentPersonal = numeroDocIdentPersonal;
    }

    public String getTipoDocIdentidad() {
        return tipoDocIdentidad;
    }

    public void setTipoDocIdentidad(String tipoDocIdentidad) {
        this.tipoDocIdentidad = tipoDocIdentidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getModalidadContrato() {
        return modalidadContrato;
    }

    public void setModalidadContrato(String modalidadContrato) {
        this.modalidadContrato = modalidadContrato;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

}
