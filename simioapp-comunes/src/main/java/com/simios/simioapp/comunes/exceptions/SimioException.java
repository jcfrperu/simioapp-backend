package com.simios.simioapp.comunes.exceptions;

import com.jcfr.utiles.enums.JExceptionEnum;
import com.jcfr.utiles.exceptions.JBaseException;

public class SimioException extends JBaseException {

    private static final long serialVersionUID = 182782817336466158L;

    public SimioException(String mensaje) {
        super(mensaje);
    }

    public SimioException(String codigo, String mensaje) {
        super(codigo, mensaje);
    }

    public SimioException(String codigo, String mensaje, JExceptionEnum tipo) {
        super(codigo, mensaje, tipo);
    }

    public SimioException(String codigo, String mensaje, JExceptionEnum tipo, Object infoExtra) {
        super(codigo, mensaje, tipo, infoExtra);
    }

}
