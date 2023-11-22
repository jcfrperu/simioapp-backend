package com.simios.simioapp.servicios.base;

import java.io.File;

public class MigracionTestBase {

    protected String handleMsgError(String codigoError, Exception sos) {

        // seteando el mensaje de error
        String msgError = codigoError + " " + ((sos == null || sos instanceof NullPointerException) ? "Null Pointer Exception" : sos.getMessage());
        if (sos != null && sos.getCause() != null) {
            msgError = msgError + ", CAUSA: " + sos.getCause().getMessage();
        }

        return msgError;
    }

    protected File getFileFromResources(String rutaArchivo) {

        ClassLoader classLoader = this.getClass().getClassLoader();

        File file = new File(classLoader.getResource(rutaArchivo).getFile());

        return file;
    }
}
