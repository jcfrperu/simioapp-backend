package com.simios.simioapp.servicios.impl;

import com.simios.simioapp.comunes.seguridad.LoginResult;
import com.simios.simioapp.negocio.LoginNegocio;
import com.simios.simioapp.servicios.LoginService;
import com.simios.simioapp.servicios.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("loginService")
public class LoginServiceImpl extends BaseService implements LoginService {

    private static final Logger log = Logger.getLogger(LoginServiceImpl.class.getName());

    @Autowired
    @Qualifier("loginNegocio")
    private LoginNegocio loginNegocio;

    @Override
    public LoginResult validateLogin(String empresa, String login, String password) throws Exception {

        try {

            return loginNegocio.validateLogin(empresa, login, password);

        } catch (Exception sos) {

            String msgError = handleMsgError("LSI-VAC-010", sos);
            log.severe(msgError);
            throw handleError(sos);
        }
    }

}
