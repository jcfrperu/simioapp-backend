package com.simios.simioapp.servicios.base;

import com.jcfr.utiles.files.JFUtil;
import com.jcfr.utiles.listas.JLUtil;
import com.jcfr.utiles.string.JSUtil;
import com.simios.simioapp.comunes.exceptions.SimioException;
import org.apache.commons.lang.StringUtils;

public class BaseService {

    protected static final JSUtil JS = JSUtil.JSUtil;
    protected static final JFUtil JF = JFUtil.JFUtil;
    protected static final JLUtil JL = JLUtil.JLUtil;

    protected String handleMsgError(String codigoError, Exception sos) {

        return handleMsgError(true, codigoError, sos);
    }

    protected String handleMsgError(boolean incluirCodigoError, String codigoError, Exception sos) {

        String codigoErrorResult = StringUtils.EMPTY;

        if (incluirCodigoError) {
            // primero coge el codigo del simioexception
            if (sos instanceof SimioException) {
                codigoErrorResult += (((SimioException) sos).getCodigo() + " ");
            }

            // si el codigo del simioexception vino vacio, tomar el otro
            if (StringUtils.isBlank(codigoErrorResult) && StringUtils.isNotBlank(codigoError)) {
                codigoErrorResult += (codigoError + " ");
            }
        }

        // seteando el mensaje de error
        String msgError = codigoErrorResult + ((sos == null || sos instanceof NullPointerException) ? "Null Pointer Exception" : sos.getMessage());
        if (sos != null && sos.getCause() != null) {
            msgError = msgError + ", CAUSA: " + sos.getCause().getMessage();
        }

        return msgError;
    }

    protected Exception handleError(Exception sos) {
        return sos;
    }
}
