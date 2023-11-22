package com.simios.simioapp.comunes.beans;

import org.apache.commons.lang.StringUtils;

public class ResultadoMigracionSBNExcelBean {

    private ResultadoMigracionBean locales;
    private ResultadoMigracionBean areas;
    private ResultadoMigracionBean oficinas;
    private ResultadoMigracionBean empleados;
    private ResultadoMigracionBean bienes;

    // estos campos se llena si ocurrio una excepcion irrecuperable en el proceso de migracion que impide seguir
    private String msgError;          // mensaje de la excepcion ocurrida al procesar alguna tabla
    private boolean ocurrioError;     // indicador si ocurrio un error en el proceso en alguna tabla y no pudo terminar con exito

    public ResultadoMigracionSBNExcelBean() {
        locales = new ResultadoMigracionBean();
        areas = new ResultadoMigracionBean();
        oficinas = new ResultadoMigracionBean();
        empleados = new ResultadoMigracionBean();
        bienes = new ResultadoMigracionBean();

        msgError = StringUtils.EMPTY;
        ocurrioError = false;
    }

    public ResultadoMigracionBean getLocales() {
        return locales;
    }

    public void setLocales(ResultadoMigracionBean locales) {
        this.locales = locales;
    }

    public ResultadoMigracionBean getAreas() {
        return areas;
    }

    public void setAreas(ResultadoMigracionBean areas) {
        this.areas = areas;
    }

    public ResultadoMigracionBean getOficinas() {
        return oficinas;
    }

    public void setOficinas(ResultadoMigracionBean oficinas) {
        this.oficinas = oficinas;
    }

    public ResultadoMigracionBean getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ResultadoMigracionBean empleados) {
        this.empleados = empleados;
    }

    public ResultadoMigracionBean getBienes() {
        return bienes;
    }

    public void setBienes(ResultadoMigracionBean bienes) {
        this.bienes = bienes;
    }

    public boolean isOcurrioError() {
        return ocurrioError;
    }

    public void setOcurrioError(boolean ocurrioError) {
        this.ocurrioError = ocurrioError;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}
