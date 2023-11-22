package com.simios.simioapp.negocio.seguridad;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.jcfr.utiles.string.JSUtil;

public class SimpleDecoder {

    private static final JSUtil JS = JSUtil.JSUtil;

    private SimpleDecoder() {
        // sealed
    }

    public static Map<Integer, Object> decodificar(String password, String inputBase64) throws Exception {

        ByteArrayInputStream bais = null;
        DataInputStream dis = null;

        try {

            if (StringUtils.isBlank(inputBase64)) throw new IllegalArgumentException("inputBase64 no puede ser null ni vacío");

            // valores a retornar la decodificar
            Map<Integer, Object> valores = new LinkedHashMap<Integer, Object>();

            Map<String, Integer> posiciones = initDecoder(password, valores);

            bais = new ByteArrayInputStream(Base64.decodeBase64(inputBase64));
            dis = new DataInputStream(bais);

            int pos = 0;

            // recorrer toda la trama
            while (pos < posiciones.get("posMaximo")) {

                // bloque de numeros
                if (pos <= posiciones.get("posBloque")) {

                    if (pos % 2 == 0) {

                        // manejo de longs
                        if (valores.containsKey(pos)) {
                            valores.put(pos, String.valueOf(leerDataLong(dis, pos)));
                        } else {
                            dis.readLong();
                        }

                    } else {

                        // manejo de ints
                        if (valores.containsKey(pos)) {
                            valores.put(pos, String.valueOf(leerDataInt(dis, pos)));
                        } else {
                            dis.readInt();
                        }
                    }

                    pos++;

                } else {

                    // bloque de letras
                    if (valores.containsKey(pos)) {

                        int posInicio = pos;

                        int sizeCadena = 0;
                        if (pos % 2 == 0) {
                            sizeCadena = (int) leerDataLong(dis, pos);
                        } else {
                            sizeCadena = leerDataInt(dis, pos);
                        }
                        pos++;

                        StringBuilder sb = new StringBuilder(sizeCadena + 10);

                        for (int i = 0; i < sizeCadena; i++) {

                            int intAt = 0;

                            if (pos % 2 == 0) {
                                intAt = (int) leerDataLong(dis, pos);
                            } else {
                                intAt = leerDataInt(dis, pos);
                            }

                            sb.append((char) intAt);

                            pos++;
                        }

                        valores.put(posInicio, sb.toString());

                    } else {

                        if (pos % 2 == 0) {
                            dis.readLong();
                        } else {
                            dis.readInt();
                        }

                        pos++;
                    }
                }

            }

            return valores;

        } catch (Exception ex) {

            throw ex;

        } finally {

            IOUtils.closeQuietly(bais);
            IOUtils.closeQuietly(dis);
        }

    }

    private static Map<String, Integer> initDecoder(String password, Map<Integer, Object> valores) {

        // este método lee el password, inicializa el mapa de valores y retorna
        // los separadores de bloque y final

        if (StringUtils.isBlank(password)) throw new IllegalArgumentException("password no puede ser null ni vacío");

        String[] posiciones = password.split("-");

        if (posiciones == null || posiciones.length < 3) throw new IllegalArgumentException("password inválido");

        int posBloque = 0;
        int posMaximo = 0;

        for (int i = 0; i < posiciones.length; i++) {

            if (!JS._numeroEntero(posiciones[i])) throw new IllegalArgumentException("password inválido");

            if (i == posiciones.length - 1) {

                // si es el ultimo
                posMaximo = JS.toInt(posiciones[i]);

            } else if (i == posiciones.length - 2) {

                // si es el penultimo
                posBloque = JS.toInt(posiciones[i]);

            } else {

                // todos los demas
                valores.put(JS.toInt(posiciones[i]), null);
            }
        }

        if (posMaximo <= posBloque) throw new IllegalArgumentException("password inválido");

        Map<String, Integer> result = new HashMap<String, Integer>();

        result.put("posBloque", posBloque);
        result.put("posMaximo", posMaximo);

        return result;
    }

    private static int leerDataInt(DataInputStream dis, int posicion) throws Exception {

        int value = (int) (dis.readInt() - posicion) / 13;

        return value;
    }

    private static long leerDataLong(DataInputStream dis, int posicion) throws Exception {

        long value = (long) (dis.readLong() - posicion) / 3;

        return value;
    }

}