package com.simios.simioapp.comunes.seguridad;

import java.io.Serializable;

public interface UsuarioSession extends Serializable {

    String getUsuarioSessionID();

    String getCodigoEntidad();

}
