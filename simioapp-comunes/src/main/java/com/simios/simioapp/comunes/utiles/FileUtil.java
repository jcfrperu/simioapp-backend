package com.simios.simioapp.comunes.utiles;

import java.io.InputStream;

public class FileUtil {

    // metodo para recoger un archivo dentro de un componente jar independientemente de donde se llame o use. MUY UTIL
    public static InputStream getResource(String filePath) {

        if (filePath == null) new IllegalArgumentException("filePath cannot be null");

        return Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
    }
}
