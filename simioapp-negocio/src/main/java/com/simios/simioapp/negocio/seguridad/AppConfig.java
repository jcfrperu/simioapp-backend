package com.simios.simioapp.negocio.seguridad;

import org.apache.commons.lang.StringUtils;

public class AppConfig {

    // CONSTANTES
    private static final String MODO_APLICACION_DESARROLLO = "desarrollo";
    private static final String MODO_APLICACION_PRODUCCION = "produccion";

    // IMPORTANTE
    // CONFIGURACION
    public static final String MODO_APLICACION = MODO_APLICACION_DESARROLLO;

    // METODOS
    public static boolean esModoProduccion() {
        return StringUtils.equals(MODO_APLICACION, MODO_APLICACION_PRODUCCION);
    }

    public static boolean esModoDesarrollo() {
        return StringUtils.equals(MODO_APLICACION, MODO_APLICACION_DESARROLLO);
    }

}
