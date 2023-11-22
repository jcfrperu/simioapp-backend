package com.simios.simioapp.servicios;


import com.simios.simioapp.comunes.seguridad.LoginResult;

public interface LoginService {

    LoginResult validateLogin(String empresa, String login, String password) throws Exception;
}
