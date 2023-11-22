package com.simios.simioapp.comunes.seguridad;

// FIXME: dummy de usuario de session.
public class UsuarioSessionImpl implements UsuarioSession {

    private String codigo;
    private String nombres;
    private String apellidos;
    private String codigoEmpresa; // cod_inst

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String getUsuarioSessionID() {
        return codigo;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    @Override
    public String getCodigoEntidad() {
        return codigoEmpresa;
    }

    // usuario por defecto para test
    public static final UsuarioSession getDefaultTestUser(String entidad) {

        UsuarioSessionImpl usuarioSession = new UsuarioSessionImpl();

        usuarioSession.setCodigo("simiouser");
        usuarioSession.setCodigoEmpresa(entidad);
        usuarioSession.setApellidos("Mono Grande");
        usuarioSession.setNombres("Francisco");

        return usuarioSession;
    }
}
