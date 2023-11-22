package com.simios.simioapp.comunes.utiles;

public class Constantes {

    // registro activo
    public static final String REGISTRO_ACTIVO = "0"; // indel = 0
    public static final String REGISTRO_INACTIVO = "1"; // indel = 1

    // mensaje standar validar campo
    public static final String CATALOGO_TIPO_DETALLE = "DET";
    public static final String CATALOGO_TIPO_CABECERA = "CAB";

    // constantes string usadas
    public static final String S = "s";
    public static final String N = "n";
    public static final String OK = "ok";
    public static final String SI = "si";
    public static final String NO = "no";

    public static final String UNO = "1";
    public static final String CERO = "0";

    public static final Integer UNO_INT = 1;
    public static final Integer CERO_INT = 0;


    // nombre atributo usuario en session
    public static final String USUARIO_SESSION_NAME = "usuario_session";

    // manejo de combos GENERALES
    public static final String COMBO_SELECCIONE_VALUE = "-1";
    public static final String COMBO_SELECCIONE_LABEL = "(SELECCIONE)";

    public static final String COMBO_TODOS_VALUE = "";
    public static final String COMBO_TODOS_LABEL = "(TODOS)";

    // manejo de combos PERSONALIZADOS
    // algunos combos requieren que el filtro sea que no exista ninguno de los de la lista
    public static final String COMBO_INVENTARIADOR_SIN_ASIGNAR_VALUE = "-2";
    public static final String COMBO_INVENTARIADOR_SIN_ASIGNAR_LABEL = "(SIN INVENTARIADOR)";

    public static class PARAMETROS {
        public static final String EXCEL_SBN_TRANSFORMAR_NULLS = "EXCEL_SBN_TRANSFORMAR_NULLS";
        public static final String SESSION_NUMERO_INTENTOS_DEFAULT = "SESSION_NUMERO_INTENTOS_DEFAULT";
        public static final String SESSION_INDICADOR_POLITICA_DE_INACTIVIDAD = "SESSION_INDICADOR_POLITICA_DE_INACTIVIDAD";
        public static final String SESSION_INDICADOR_POLITICA_DE_INTENTOS_FALLIDOS = "SESSION_INDICADOR_POLITICA_DE_INTENTOS_FALLIDOS";
        public static final String SESSION_MINUTOS_VALIDEZ_DEFAULT = "SESSION_MINUTOS_VALIDEZ_DEFAULT";
    }

}
