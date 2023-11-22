package com.simios.simioapp.comunes.utiles;

// FIXME/NOTA: candidatda a ir al framework/jcfr-utiles
public class NumberUtil {

    // compara dos objetos integer null safe
    public static boolean equalsInteger(Integer val01, Integer val02) {

        if (val01 == null) {
            if (val02 == null) return true;

            return val02.equals(val01);
        }

        return val01.equals(val02);
    }

    public static boolean equalsLong(Long val01, Long val02) {

        if (val01 == null) {
            if (val02 == null) return true;

            return val02.equals(val01);
        }

        return val01.equals(val02);
    }
}
