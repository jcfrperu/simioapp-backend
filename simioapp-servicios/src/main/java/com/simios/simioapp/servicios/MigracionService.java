package com.simios.simioapp.servicios;

import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionSBNExcelBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;

import java.io.InputStream;

public interface MigracionService {

    ResultadoMigracionBean migrarDBFAreas(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion);

    ResultadoMigracionBean migrarDBFLocales(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion);

    ResultadoMigracionBean migrarDBFEmpleados(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion);

    ResultadoMigracionBean migrarDBFOficinas(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion);

    ResultadoMigracionBean migrarDBFBienes(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion);

    ResultadoMigracionSBNExcelBean migrarSBNExcelV7(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion);

}
