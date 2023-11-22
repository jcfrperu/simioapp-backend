package com.simios.simioapp.negocio.seguridad;

import com.jcfr.utiles.DateTime;
import com.simios.simioapp.negocio.seguridad.SimpleCoder.Result;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

public class TokenGenerator {

    // bloque de codificaciones de numeros [0, POS_MAX_BLOQUE_NUMEROS]
    private static final int POS_MAX_BLOQUE_NUMEROS = 1024;

    // bloque de codificacion de letras [POS_MAX_BLOQUE_NUMEROS + 1,
    // POS_MAX_BLOQUE_CARACTERES - xxx]
    private static final int POS_MAX_BLOQUE_CARACTERES = 2048;

    // agregamos el inicio de validez de token (par)
    private static final int POS_FECHA_INICIO_TOKEN = 382;

    // agregamos el nro de minutos de validez del token (impar)
    private static final int POS_MINUTOS_VALIDEZ_TOKEN = 411;

    // agregamos el termino de validez de token (par)
    private static final int POS_FECHA_FIN_TOKEN = 930;

    // agregamos usuario (mayor a POS_MAX_BLOQUE_NUMEROS)
    private static final int POS_USUARIO = 1082;

    // agregamos entidad (mayor a POS_MAX_BLOQUE_NUMEROS)
    private static final int POS_ENTIDAD = 1401;

    // agregamos identificador del inventario en ejecucion (mayor a
    // POS_MAX_BLOQUE_NUMEROS)
    private static final int POS_INVENTARIO = 1703;

    // numero maximo de minutos de validez ( 30 * 24 * 60 -> 30 dias )
    private static final int NUMERO_DE_MINUTOS_MAXIMO_VALIDEZ = 30 * 24 * 60;

    private static String getParametrosIniciales() {

        // el metodo de ofuscacion, necesita una cadena separada por comas con
        // los parametros iniciales de codificacion

        StringBuilder sb = new StringBuilder();

        sb.append(POS_FECHA_INICIO_TOKEN).append("-");
        sb.append(POS_MINUTOS_VALIDEZ_TOKEN).append("-");
        sb.append(POS_FECHA_FIN_TOKEN).append("-");
        sb.append(POS_USUARIO).append("-");
        sb.append(POS_ENTIDAD).append("-");
        sb.append(POS_INVENTARIO).append("-");
        sb.append(POS_MAX_BLOQUE_NUMEROS).append("-");
        sb.append(POS_MAX_BLOQUE_CARACTERES);

        return sb.toString();
    }

    public static String generarToken(TokenData dataToken) throws Exception {

        if (dataToken == null) throw new IllegalArgumentException("dataToken no puede ser null");

        // validar los minutos de validez
        if (!(dataToken.getMinutosValidez() > 0)) throw new IllegalArgumentException("dataToken.minutosValidez debe ser mayor a cero");
        if (!(dataToken.getMinutosValidez() < NUMERO_DE_MINUTOS_MAXIMO_VALIDEZ)) throw new IllegalArgumentException("dataToken.minutosValidez debe ser menor que " + NUMERO_DE_MINUTOS_MAXIMO_VALIDEZ);

        // fechas de inicio y fin de validez del token
        DateTime inicioValidezToken = DateTime.getNow();

        DateTime finValidezToken = inicioValidezToken.addMinutos(dataToken.getMinutosValidez());

        SimpleCoder codificador = new SimpleCoder(POS_MAX_BLOQUE_NUMEROS, POS_MAX_BLOQUE_CARACTERES);

        // agregamos el inicio de validez de token (par)
        codificador.addLongInfo(POS_FECHA_INICIO_TOKEN, inicioValidezToken.toLong());

        // agregamos el nro de minutos de validez del token (impar)
        codificador.addIntegerInfo(POS_MINUTOS_VALIDEZ_TOKEN, dataToken.getMinutosValidez());

        // agregamos el termino de validez de token (par)
        codificador.addLongInfo(POS_FECHA_FIN_TOKEN, finValidezToken.toLong());

        // agregamos usuario
        codificador.addStringInfo(POS_USUARIO, dataToken.getUsuario());

        // agregamos entidad
        codificador.addStringInfo(POS_ENTIDAD, dataToken.getEntidad());

        // agregamos identificado del inventario en ejecucion
        codificador.addStringInfo(POS_INVENTARIO, dataToken.getInventario());

        Result token = codificador.codificar();

        return token.getResult();
    }

    public static TokenData traducirToken(String token) {

        Map<Integer, Object> dataDecoded = null;

        try {

            dataDecoded = SimpleDecoder.decodificar(getParametrosIniciales(), token);

        } catch (Exception sos) {

            // si hubo error en la traduccion
            dataDecoded = null;
        }

        if (dataDecoded != null) {

            TokenData tokenData = new TokenData();

            tokenData.setUsuario(MapUtils.getString(dataDecoded, POS_USUARIO));
            tokenData.setEntidad(MapUtils.getString(dataDecoded, POS_ENTIDAD));
            tokenData.setInventario(MapUtils.getString(dataDecoded, POS_INVENTARIO));
            tokenData.setMinutosValidez(MapUtils.getInteger(dataDecoded, POS_MINUTOS_VALIDEZ_TOKEN, 0));
            tokenData.setFechaInicioToken(MapUtils.getLong(dataDecoded, POS_FECHA_INICIO_TOKEN, 0L));
            tokenData.setFechaFinToken(MapUtils.getLong(dataDecoded, POS_FECHA_FIN_TOKEN, 0L));

            return tokenData;
        }

        // si hay error retorna null
        return null;
    }

}
