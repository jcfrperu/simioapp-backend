package com.simios.simioapp.comunes.seguridad;


import java.util.Map;

public class LoginResult {


    public static final String LOGIN_ERROR_GENERAL_POR_EXCEPTION_SERVICIO = "LOGIN-000";
    public static final String LOGIN_ERROR_GENERAL_POR_EXCEPTION_CONTROLADOR = "LOGIN-001";

    public static final String LOGIN_ERROR_USUARIO_PASSWORD_BLANCOS = "LOGIN-010";
    public static final String LOGIN_ERROR_EMPRESA_NO_ES_NUMERO_ENTERO = "LOGIN-020";
    public static final String LOGIN_ERROR_EMPRESA_NO_EXISTE_O_ENTIDAD_NO_ESTA_ACTIVA = "LOGIN-030";
    public static final String LOGIN_ERROR_USUARIO_NO_EXISTE_O_NO_ESTA_ACTIVO = "LOGIN-040";
    public static final String LOGIN_ERROR_EMPRESA_NO_CORRESPONDE_AL_USUARIO = "LOGIN-050";
    public static final String LOGIN_ERROR_USUARIO_NO_TIENE_ASOCIADA_CLAVE = "LOGIN-060";
    public static final String LOGIN_ERROR_CLAVE_ASOCIADA_AL_USUARIO_NO_EXISTE = "LOGIN-065";
    public static final String LOGIN_ERROR_CLAVE_INCORRECTA = "LOGIN-070";
    public static final String LOGIN_ERROR_USUARIO_BLOQUEADO = "LOGIN-080";
    public static final String LOGIN_ERROR_USUARIO_NO_HA_CERRADO_SESION = "LOGIN-081";
    public static final String LOGIN_ERROR_USUARIO_NO_CONTIENE_EMPRESA = "LOGIN-015";


    // resultado alto nivel del proceso de login
    private boolean error;
    private String errorMsg;
    private String errorCode;

    // datos de la excepcion si alguna ocurre
    private Exception exception;

    // datos adicionales
    private Map<String, Object> datos;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, Object> getDatos() {
        return datos;
    }

    public void setDatos(Map<String, Object> datos) {
        this.datos = datos;
    }

}
