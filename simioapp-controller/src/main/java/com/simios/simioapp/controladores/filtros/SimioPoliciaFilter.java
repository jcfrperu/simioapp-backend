package com.simios.simioapp.controladores.filtros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcfr.utiles.DateTime;
import com.simios.simioapp.comunes.beans.DataJsonBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.Credencial;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.seguridad.UsuarioSessionImpl;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.controladores.util.HttpUtil;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;
import com.simios.simioapp.dominio.entidades.UsuarioSessionEntity;
import com.simios.simioapp.negocio.seguridad.SeguridadUtil;
import com.simios.simioapp.negocio.seguridad.TokenData;
import com.simios.simioapp.negocio.seguridad.TokenGenerator;
import com.simios.simioapp.servicios.UsuarioService;
import com.simios.simioapp.servicios.UsuarioSessionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

@Component("simioPoliciaFilter")
public class SimioPoliciaFilter implements Filter {

    private static final Logger log = Logger.getLogger(SimioPoliciaFilter.class.getName());

    @Autowired
    @Qualifier("usuarioSessionService")
    protected UsuarioSessionService usuarioSessionService;

    @Autowired
    @Qualifier("usuarioService")
    protected UsuarioService usuarioService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        // el simio policia no se presta para w3badas
        if (!(servletRequest instanceof HttpServletRequest) || !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("This filter just supports HTTP requests");
        }

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = StringUtils.trimToEmpty(request.getRequestURI());

        // solo filtrar las urls que tienen logica de negocio: *.htm (por el servlet), *.jsp (por si desea escribir un jsp de la url)
        // NOTA IMPORTANTE: todos los simio generic ajax (comprobar conexion y otras operaciones dummies) no se consideran como parte de la logica
        boolean esSimioGenericAjaxServlet = uri.endsWith(".generic");
        boolean esUrlConLogica = (uri.endsWith(".htm") || uri.endsWith(".htm")) && !esSimioGenericAjaxServlet;

        // si es una url con logica (que es procesada por un controller)
        if (esUrlConLogica) {

            String action = request.getParameter("action");

            // las unicas urls que no deben filtrarse son las de login
            boolean esLogin = uri.endsWith("admin.htm") && ("login".equals(action) || "mostrarLogin".equals(action));
            boolean esCerrarSession = uri.endsWith("admin.htm") && ("cerrarSesion".equals(action) || "cerrarSesionDesdeOffline".equals(action));

            boolean aplicarFiltro = !(esLogin || esCerrarSession);

            if (aplicarFiltro) {

                // recoge la session SIN crearla
                HttpSession session = request.getSession(false);

                // si tiene session
                if (session != null) {

                    // recoger el usuario en session
                    UsuarioSession usuarioSession = (UsuarioSession) session.getAttribute(Constantes.USUARIO_SESSION_NAME);

                    // el usuario ha pasado por el login y su session aun no se ha perdido
                    if (usuarioSession != null) {

                        // el usuario ha pasado por el login, pero hay que validar si su session ya esta inactivada y/o bloqueada
                        // TODO/FIXME


                        // actualizando la ultima accion
                        DateTime now = DateTime.getNow();

                        List<UsuarioSessionEntity> sesiones = buscarSesiones(usuarioSession);

                        if (CollectionUtils.isNotEmpty(sesiones)) {
                            // si hay sessiones, ver si esta borrada o no
                            UsuarioSessionEntity sesion = sesiones.get(0);

                            sesion.setUltimaAccion(now.toDate());
                            sesion.setFechaAct(now.toDate());
                            sesion.setUsuAct(usuarioSession.getUsuarioSessionID());
                            sesion.setDatosIpAccion(getIPCliente(request));

                            updateSesiones(sesion);
                        }

                        chain.doFilter(servletRequest, servletResponse);

                    } else {

                        // el usuario no ha pasado por el login
                        handleDebeAutenticarse(request, response);
                    }

                    return;

                } else {

                    if (vieneToken(request)) {

                        Credencial credenciales = validarToken(request);

                        if (credenciales != null) {

                            loguearseAlSistema(request, credenciales);

                            // el usuario ha pasado por el login, pero hay que validar si su session ya esta inactivada y/o bloqueada
                            // TODO/FIXME: posiblemente aqui se le haga pasar directamente sin considerar las validacion de:
                            //             si su session ya esta inactivada y/o bloqueada, etc

                            chain.doFilter(servletRequest, servletResponse);

                            return;
                        }

                    }

                    // si no tiene session (es la primera vez que carga la url, o si ya perdio la session)
                    // NOTA: cuando ingresa un action=xxx directamente desde la url,
                    // la primera vez la session es null, por eso aseguramos que siempre vaya al login
                    handleDebeAutenticarse(request, response);

                    return;
                }
            }
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    private void updateSesiones(UsuarioSessionEntity sesion) {

        try {

            usuarioSessionService.update(sesion);
        } catch (Exception ignored) {
            // no se puede hacer mucho
            log.severe("SimioPolicia-002: " + ignored.getMessage());
        }
    }

    private UsuarioEntity buscarUsuarioQuietly(String codigoUsuario) {

        UsuarioEntity filter = new UsuarioEntity();

        filter.setUsuarioID(codigoUsuario);

        try {
            List<UsuarioEntity> results = usuarioService.select(filter);

            if (CollectionUtils.isNotEmpty(results)) {
                return results.get(0);
            }

        } catch (Exception e) {
            log.severe("SimioPolicia-003: " + e.getMessage());
        }

        return null;
    }

    protected String getIPCliente(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = request.getRemoteAddr(); // 0:0:0:0:0:0:0:1
        }

        return StringUtils.trimToEmpty(ipAddress);
    }

    private List<UsuarioSessionEntity> buscarSesiones(UsuarioSession usuarioSession) {

        try {

            UsuarioSessionEntity filter = new UsuarioSessionEntity();
            filter.setUsuarioID(usuarioSession.getUsuarioSessionID());
            filter.setIndDel(Constantes.REGISTRO_ACTIVO);

            return usuarioSessionService.select(filter);

        } catch (Exception ignored) {
            // no se puede hacer mucho
            log.severe("SimioPolicia-005: " + ignored.getMessage());
        }

        return null;
    }

    private void loguearseAlSistema(HttpServletRequest request, Credencial credencial) {

        // NOTA: credenciales nunca viene null, validacion por seguridad
        if (credencial == null) throw new IllegalArgumentException("credencial cannot be null");

        // TODO/FIXME: aqui deberia ir a BD y crear una fila en usuario_session o actualizar dicha fila si es necesario

        UsuarioSessionImpl usuarioSession = new UsuarioSessionImpl();

        UsuarioEntity usuarioEntity = buscarUsuarioQuietly(credencial.getUsuario());

        usuarioSession.setCodigo(usuarioEntity.getUsuarioID());
        usuarioSession.setCodigoEmpresa(String.valueOf(usuarioEntity.getEntidadID()));
        usuarioSession.setApellidos(usuarioEntity.getApellidos());
        usuarioSession.setNombres(usuarioEntity.getApellidos());

        // setear el usuario en session
        WebUtils.setSessionAttribute(request, Constantes.USUARIO_SESSION_NAME, usuarioSession);

        // TODO/FIXME: NOTAR que el que setee el atributo en session en solo referencial,
        // en verdad deberia grabarlo en la tabla de bd especial para ese manejo de sessiones
    }

    private boolean vieneToken(HttpServletRequest request) {

        return request != null && StringUtils.isNotBlank(request.getParameter("token"));
    }

    private Credencial validarToken(HttpServletRequest request) {

        try {

            if (request == null) throw new Exception("request cannot be null");

            return HttpUtil.checkCredencialesToken(request);

        } catch (Exception ex) {
            log.severe("SimioPolicia-001: " + ex.getMessage());
            return null;
        }
    }

    private void handleDebeAutenticarse(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // librerias como jQuery, Mootools, Prototype, etc, setean el header "X-Requested-With"; si la peticion ajax se hace a mano pueden optar no setearla
        boolean esPeticionAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if (esPeticionAjax) {

            // es ajax: enviar una respuesta error en formato JSON que debe loguearse
            DataJsonBean dataJSON = new DataJsonBean();

            dataJSON.setRespuesta("error", "Debe autenticarse nuevamente", null);

            handleJSONResponse(dataJSON, response);


        } else {

            // no es ajax: un redirect al mostrar login
            response.sendRedirect("admin.htm?action=mostrarLogin");
        }
    }

    private void handleJSONResponse(DataJsonBean dataJsonBean, HttpServletResponse response) {

        try {

            response.setContentType("text/plain;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");

            String dataJsonString = new ObjectMapper().writeValueAsString(dataJsonBean);

            PrintWriter writer = response.getWriter();

            if (writer != null) {
                writer.write(dataJsonString);
                // writer.close();
            }

        } catch (Exception ignored) {
            // no se puede hacer mucho aqui
            log.severe("SimioPolicia-010: " + ignored.getMessage());
        }
    }

}
