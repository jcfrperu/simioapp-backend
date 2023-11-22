package com.simios.simioapp.comunes.beans;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ResultadoMigracionBean {

    private String nombreTabla;       // nombre de tabla / archivo dbf migrado
    private int nroFilasEncontradas;  // numero de filas encontradas en el archivo dbf
    private int nroFilasMigradas;     // numero de filas insertas en las tablas de mysql
    private int nroFilasNoMigradas;   // numero de filas no migradas

    // estos campos se llena si ocurrio una excepcion irrecuperable en el proceso de migracion que impide seguir
    // (no es el error individual de cada fila)
    private boolean ocurrioError;     // indicador si ocurrio un error general en el proceso de la tabla y no pudo terminar con exito
    private String msgError;          // mensaje de la excepcion general ocurrida al procesar la tabla

    // lista de errores
    private List<ErrorLineaMigracionBean> listaErrores;

    // otros mensajes que pueden ser advertencias o notas de que algo paso y hay que revisar
    private List<MensajeLineaMigracionBean> listaMensajes;

    public ResultadoMigracionBean() {
        this.nombreTabla = StringUtils.EMPTY;
        this.nroFilasEncontradas = 0;
        this.nroFilasMigradas = 0;
        this.nroFilasNoMigradas = 0;
        this.ocurrioError = false;
        this.msgError = StringUtils.EMPTY;
        this.listaErrores = new ArrayList<>();
        this.listaMensajes = new ArrayList<>();
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public int getNroFilasEncontradas() {
        return nroFilasEncontradas;
    }

    public void setNroFilasEncontradas(int nroFilasEncontradas) {
        this.nroFilasEncontradas = nroFilasEncontradas;
    }

    public int getNroFilasMigradas() {
        return nroFilasMigradas;
    }

    public void setNroFilasMigradas(int nroFilasMigradas) {
        this.nroFilasMigradas = nroFilasMigradas;
    }

    public int getNroFilasNoMigradas() {
        return nroFilasNoMigradas;
    }

    public void setNroFilasNoMigradas(int nroFilasNoMigradas) {
        this.nroFilasNoMigradas = nroFilasNoMigradas;
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

    public List<ErrorLineaMigracionBean> getListaErrores() {
        return listaErrores;
    }

    public void setListaErrores(List<ErrorLineaMigracionBean> listaErrores) {
        this.listaErrores = listaErrores;
    }

    public List<MensajeLineaMigracionBean> getListaMensajes() {
        return listaMensajes;
    }

    public void setListaMensajes(List<MensajeLineaMigracionBean> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }
}
