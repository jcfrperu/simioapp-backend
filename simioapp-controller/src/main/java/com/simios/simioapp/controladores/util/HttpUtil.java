package com.simios.simioapp.controladores.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcfr.utiles.DateTime;
import com.jcfr.utiles.enums.JExceptionEnum;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.negocio.seguridad.SeguridadUtil;
import com.simios.simioapp.negocio.seguridad.TokenData;
import com.simios.simioapp.negocio.seguridad.TokenGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class HttpUtil {

    private static final Logger log = Logger.getLogger(HttpUtil.class.getName());

    private HttpUtil() {
        // sealed
    }

    public static Credencial checkCredencialesTokenQuiet(HttpServletRequest request) {

        try {

            return checkCredencialesToken(request);

        } catch (Exception ignored) {
            // no se puede hacer mucho
        }

        return null;
    }

    public static Credencial checkCredencialesToken(HttpServletRequest request) throws Exception {

        // recoger parametros que forman la credencial
        String usuario = StringUtils.trimToEmpty(request.getParameter("usuario"));
        String entidad = StringUtils.trimToEmpty(request.getParameter("entidad"));
        String inventario = StringUtils.trimToEmpty(request.getParameter("inventario"));
        String token = StringUtils.trimToEmpty(request.getParameter("token"));

        try {

            // TODO/FIXME: revisar la validez del site de donde viene la peticion

            // PARA QUE PERMITE PETICIONES AJAX DESDE UN ARCHIVITO DE TEXTO
//            if (AppConfig.esModoDesarrollo()) {
//                response.setHeader("Access-Control-Allow-Origin", "*");
//            }

            // validar que todos los datos se seteen
            if (StringUtils.isBlank(usuario) || StringUtils.isBlank(entidad) || StringUtils.isBlank(inventario) || StringUtils.isBlank(token)) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_NO_SETEADAS);
            }

            TokenData tokenData = TokenGenerator.traducirToken(token);

            // que no haya ocurrido ningun error en el parseo del token
            if (tokenData == null) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_FORMATO_INVALIDO);
            }

            // que todos los parametros cadena del token vengan seteados
            if (StringUtils.isBlank(tokenData.getUsuario()) || StringUtils.isBlank(tokenData.getEntidad()) || StringUtils.isBlank(tokenData.getInventario())) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_DATA_INCONSISTENTE);
            }

            // que todos los parametros numeros del token sean mayores que cero
            if (tokenData.getMinutosValidez() <= 0 || tokenData.getFechaInicioToken() <= 0 || tokenData.getFechaFinToken() <= 0) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_DATA_INCONSISTENTE);
            }

            // que la fecha final sea mayor que la fecha inicial
            if (tokenData.getFechaFinToken() <= tokenData.getFechaInicioToken()) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_DATA_INCONSISTENTE);
            }

            // que los usuarios sean iguales
            if (!StringUtils.equals(usuario, tokenData.getUsuario())) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_USUARIO_INVALIDO);
            }

            // que las entidades sean iguales
            if (!StringUtils.equals(entidad, tokenData.getEntidad())) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_ENTIDAD_INVALIDA);
            }

            // que los inventarios sean iguales
            if (!StringUtils.equals(inventario, tokenData.getInventario())) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_INVENTARIO_INVALIDO);
            }

            // convirtiendo a DateTime y haciendo validaciones paranoicas de
            // fechas
            DateTime fechaInicioToken = DateTime.getInstancia(tokenData.getFechaInicioToken());
            DateTime fechaFinToken = DateTime.getInstancia(tokenData.getFechaFinToken());

            if (fechaInicioToken == null || fechaFinToken == null) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_DATA_INCONSISTENTE);
            }

            if (fechaFinToken.antesIgualQue(fechaInicioToken)) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_DATA_INCONSISTENTE);
            }

            // que cumpla: fechaFinal = fechaInicial + validezToken
            if (fechaFinToken.toLong() != fechaInicioToken.addMinutos(tokenData.getMinutosValidez()).toLong()) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_DATA_INCONSISTENTE);
            }

            // valida que no haya expirado: fechaFinToken <= NOW
            // if (AppConfig.esModoProduccion()) {
            if (DateTime.getNow().despuesQue(fechaFinToken)) {
                throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_TOKEN_EXPIRADO);
            }

            // retornar las credenciales por si desea usar afuera
            return new Credencial(token, usuario, entidad, inventario);

        } catch (Exception sos) {

            log.severe(handleMsgError(true, "HU-CC-001", sos));

            if (sos instanceof SimioException) {
                throw sos;
            }

            throw new SimioException(SeguridadUtil.MENSAJES.CREDENCIALES_EXCEPCION_INESPERADA);
        }
    }

    public static final String handleJSONError(String codigoError, DataJsonBean dataJsonBean, Exception sos) {

        boolean esValidacion = sos instanceof SimioException
                && JExceptionEnum.VALIDACION.equals(((SimioException) sos).getTipo());

        String msgError = handleMsgError(!esValidacion, codigoError, sos);

        if (esValidacion) {
            // error validacion
            dataJsonBean.setRespuesta("errorValidacion", msgError, ((SimioException) sos).getInfo());
        } else {
            // error caso normal
            dataJsonBean.setRespuesta("error", msgError, null);
        }

        return msgError;
    }

    public static final String handleMsgError(boolean incluirCodigoError, String codigoError, Exception sos) {

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

    public static final String getHeaderAcceso(HttpServletResponse response) {

        // TODO/FIXME: REVISAR MUY BIEN ESTO, PERMITIRIA ACCEDER POR AJAX DESDE UN DOMINIO QUE NO ES EL NUESTRO
        // headers
        // "Access-Control-Allow-Origin"
        // "Access-Control-Allow-Methods"
        // "Access-Control-Allow-Headers"
        // "Access-Control-Max-Age"

        String header = response.getHeader("Access-Control-Allow-Origin");

        return StringUtils.trimToEmpty(header);
    }


    public static final ModelAndView handleJSONResponse(DataJsonBean dataJsonBean, HttpServletResponse response) {

        try {

            response.setContentType("text/plain;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");

            String dataJsonString = new ObjectMapper().writeValueAsString(dataJsonBean);

            PrintWriter writer = response.getWriter();

            if (writer != null) {
                writer.write(dataJsonString);
                // writer.close();
            }

            return null;

        } catch (Exception sos) {

            return null;
        }
    }

    public static final String getIPCliente(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr(); // 0:0:0:0:0:0:0:1
        }

        // TODO/FIXME: existe un mejor metodo para esto, revisar fuentes del scotia

        return StringUtils.trimToEmpty(ipAddress);
    }
}
