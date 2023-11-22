package com.simios.simioapp.controladores.servlets;

import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.util.HttpUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public class SimioGenericAjaxServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(SimioGenericAjaxServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataJsonBean dataJSON = new DataJsonBean();

        try {

            HashMap<String, Object> model = new HashMap<>();

            String uri = StringUtils.trimToEmpty(request.getRequestURI());

            if (StringUtils.endsWith(uri, "comprobar-conexion.generic")) {

                // solo se envia un atributo "status" para indicar que la conexion al servidores esta ok
                model.put("status", Constantes.OK);
            }

            dataJSON.setRespuesta("ok", null, model);

        } catch (Exception sos) {

            // manejo centralizado de errores
            String msgError = HttpUtil.handleJSONError("CCS-DG-001", dataJSON, sos);
            log.severe(msgError);
        }

        HttpUtil.handleJSONResponse(dataJSON, response);
    }

}
