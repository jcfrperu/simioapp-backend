package com.simios.simioapp.negocio;

import com.jcfr.utiles.DateTime;
import com.simios.simioapp.comunes.seguridad.LoginResult;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.NumberUtil;
import com.simios.simioapp.dao.ClaveDAO;
import com.simios.simioapp.dao.EntidadDAO;
import com.simios.simioapp.dao.UsuarioDAO;
import com.simios.simioapp.dao.UsuarioSessionDAO;
import com.simios.simioapp.dominio.entidades.ClaveEntity;
import com.simios.simioapp.dominio.entidades.EntidadEntity;
import com.simios.simioapp.dominio.entidades.UsuarioEntity;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static com.simios.simioapp.comunes.seguridad.LoginResult.*;

@Component("loginNegocio")
public class LoginNegocio extends BaseNegocio {

    // solo esta clase de negocio tiene log aqui mismo (la mayoria lanza excepciones al service que es el que imprime el log)
    private static final Logger log = Logger.getLogger(LoginNegocio.class.getName());

    @Autowired
    private EntidadDAO entidadDAO;

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private UsuarioSessionDAO usuarioSessionDAO;

    @Autowired
    private ClaveDAO claveDAO;

    public LoginResult validateLogin(String empresaParam, String loginParam, String passwordParam) {

        LoginResult loginResult = new LoginResult();

        // seteando todo OK
        loginResult.setError(false);
        loginResult.setErrorCode(null);
        loginResult.setErrorMsg(StringUtils.EMPTY);
        loginResult.setException(null);

        try {

            String empresa = StringUtils.trimToEmpty(empresaParam);
            String login = StringUtils.trimToEmpty(loginParam);
            String password = StringUtils.trimToEmpty(passwordParam);

            log.info("login intento -> empresa: " + empresa + ", login: " + login + ", password: ********");

            if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_USUARIO_PASSWORD_BLANCOS);
                loginResult.setErrorMsg("Debe ingresar usuario y password");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: " + password);

                return loginResult;
            }

            // validaciones basicas
            if (StringUtils.isBlank(empresa)) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_USUARIO_NO_CONTIENE_EMPRESA);
                loginResult.setErrorMsg("Usuario no tiene formato correcto (usuario.numero)");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            // que la empresa exista y que sea un numero
            if (!JS._numeroEntero(empresa)) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_EMPRESA_NO_ES_NUMERO_ENTERO);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            EntidadEntity entidadFilter = new EntidadEntity();
            entidadFilter.setEntidadID(JS.toInt(empresa));
            entidadFilter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<EntidadEntity> entidades = entidadDAO.select(entidadFilter);

            if (CollectionUtils.isEmpty(entidades)) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_EMPRESA_NO_EXISTE_O_ENTIDAD_NO_ESTA_ACTIVA);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            // que el usuario exista
            UsuarioEntity usuarioFilter = new UsuarioEntity();
            usuarioFilter.setUsuarioID(login);
            usuarioFilter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<UsuarioEntity> usuarios = usuarioDAO.select(usuarioFilter);

            if (CollectionUtils.isEmpty(usuarios)) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_USUARIO_NO_EXISTE_O_NO_ESTA_ACTIVO);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            // que el usuario corresponda con la entidad
            EntidadEntity entidad = entidades.get(0);
            UsuarioEntity usuario = usuarios.get(0);

            if (!NumberUtil.equalsInteger(usuario.getEntidadID(), entidad.getEntidadID())) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_EMPRESA_NO_CORRESPONDE_AL_USUARIO);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            if (usuario.getClaveID() == null) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_USUARIO_NO_TIENE_ASOCIADA_CLAVE);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            // que el usuario tenga clave
            ClaveEntity claveFilter = new ClaveEntity();

            claveFilter.setClaveID(usuario.getClaveID());
            claveFilter.setIndDel(Constantes.REGISTRO_ACTIVO);

            List<ClaveEntity> claves = claveDAO.select(claveFilter);
            if (CollectionUtils.isEmpty(claves)) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_CLAVE_ASOCIADA_AL_USUARIO_NO_EXISTE);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            // que la clave este correcta
            boolean isOK = isClaveOK(claves.get(0), password);

            if (!isOK) {

                loginResult.setError(true);
                loginResult.setErrorCode(LOGIN_ERROR_CLAVE_INCORRECTA);
                loginResult.setErrorMsg("Usuario y password incorrectos");

                log.info("login fallido -> empresa: " + empresa + ", login: " + login + ", password: ********");

                return loginResult;
            }

            // login OK
            loginResult.setDatos(new HashMap<String, Object>());
            loginResult.getDatos().put("usuario.nombres", usuario.getNombres());
            loginResult.getDatos().put("usuario.apellidos", usuario.getApellidos());

            log.info("login correcto -> empresa: " + empresa + ", login: " + login + ", password: ********");
            log.info("usuario " + usuario.getUsuarioID() + " ingreso al sistema " + DateTime.getNow().getFechaHoraString());

        } catch (Exception e) {

            log.severe(e.getMessage() + (e.getCause() != null ? e.getCause().getMessage() : StringUtils.EMPTY));

            loginResult.setError(true);
            loginResult.setErrorCode(LOGIN_ERROR_GENERAL_POR_EXCEPTION_SERVICIO);
            loginResult.setErrorMsg("Usuario y password incorrectos");

        }

        return loginResult;
    }

    private boolean isClaveOK(ClaveEntity clave, String password) {

        // metodo que retorna true o false segun el tipo de clave que tiene el usuario y el metodo de cifrado (plantext)
        if (clave != null && password != null) {

            // caso que sea textplain
            if (StringUtils.equalsIgnoreCase(clave.getClaveMetodo(), "textplain")) {
                if (StringUtils.equals(clave.getClave(), password)) {
                    return true;
                }
            }

            // TODO/FIXME: falta implementar los otros casos
        }

        return false;
    }

}
