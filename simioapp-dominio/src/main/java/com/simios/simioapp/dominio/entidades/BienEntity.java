package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

import java.util.Date;

public class BienEntity extends AuditoriaFields {

    private Integer bienID; // bien_id
    private String codigoPatrimonial; // codigo_patrimonial
    private Integer entidadID; // entidad_id
    private Integer localesID; // locales_id
    private Integer areaID; // area_id
    private Integer empleadoID; // empleado_id
    private Integer oficinaID; // oficina_id
    private String tipoCausalAlta; // tipo_causal_alta
    private Integer anho; // anho
    private String matricula; // matricula
    private Double ancho; // ancho
    private Double valorLibro; // valor_libro
    private String cuentaConSeguro; // cuenta_con_seguro
    private String estadoBien; // estado_bien
    private String dimension; // dimension
    private String condicion; // condicion
    private String numeroDocAdquisicion; // nro_doc_adquisicion
    private String color; // color
    private Date fechaAdquisicion; // fecha_adquisicion
    private String entDisp; // ent_disp
    private String entArre; // ent_arre
    private Date fechaVafec; // fec_vafec
    private String edad; // edad
    private String motivoEliminacionBien; // motivo_eliminacion_bien
    private Double valorAdquisicion; // valor_adquisicion
    private String anioFabricacion; // anio_fabricacion
    private String resolAfec; // resol_afec
    private String especie; // especie
    private String raza; // raza
    private Date fechaAfec; // fec_afec
    private String flgActo; // flg_acto
    private String modelo; // modelo
    private String flgCausal; // flg_causal
    private String marca; // marca
    private String numeroResolucionBaja; // nro_resolucion_baja
    private String resolArre; // resol_arre
    private String dscOtros; // dsc_otros
    private Date fechaArre; // fec_arre
    private Date fechaDisposicion; // fecha_disposicion
    private String resolDisp; // resol_disp
    private String altura; // altura
    private String numeroChasis; // nro_chasis
    private String valortasa; // valortasa
    private String placa; // placa
    private String sitBinv; // sit_binv
    private String numeroCuentaContable; // nro_cuenta_contable
    private String serie; // serie
    private String estBien; // est_bien
    private String entAfec; // ent_afec
    private String codanterio; // codanterio
    private String tipoCuenta; // tipo_cuenta
    private String estGestio; // est_gestio
    private String docBaja; // doc_baja
    private String numeroMotor; // nro_motor
    private String longitud; // longitud
    private String pais; // pais
    private Date fechaBaja; // fecha_baja
    private Date fechaVarre; // fec_varre
    private String denominacionBien; // denominacion_bien
    private Date fechaDepreciacion; // fecha_depreciacion
    private Double valorDeprecEjercicio; // valor_deprec_ejercicio
    private Double valorDeprecAcumulado; // valor_deprec_acumulado
    private Double valorNeto; // valor_neto
    private String tipUsoCuenta; // tip_uso_cuenta
    private Integer catalogoBienID; // catalogo_bien_id
    private String otrasCaract; // otras_caract
    private String descripcionUbicacionBien; // descripcion_ubicacion_bien
    private String causalBaja; // causal_baja
    private String actoDisposicionBien; // acto_disposicion_bien
    private String entidadBeneficiadaActoDisposicion; // entidad_beneficiada_acto_disposicion
    private String actoAdministracionBien; // acto_administracion_bien
    private String numeroResolucionAdministracion; // num_resolucion_administracion
    private Date fechaAdministracion; // fecha_administracion
    private Date fechaVencActoAdmin; // fecha_venc_acto_admin
    private String entidadBeneficiadaActoAdmin; // entidad_beneficiada_acto_admin
    private String docAltaSbn; // doc_alta_sbn
    private String docBajaSbn; // doc_baja_sbn
    private String tipo; // tipo
    private String numeroResolucionDisp; // nro_resolucion_disp
    private String codigoBarras; // codigo_barras

    public BienEntity() {

    }

    public Integer getBienID() {
        return bienID;
    }

    public void setBienID(Integer bienID) {
        this.bienID = bienID;
    }

    public String getCodigoPatrimonial() {
        return codigoPatrimonial;
    }

    public void setCodigoPatrimonial(String codigoPatrimonial) {
        this.codigoPatrimonial = codigoPatrimonial;
    }

    public Integer getEntidadID() {
        return entidadID;
    }

    public void setEntidadID(Integer entidadID) {
        this.entidadID = entidadID;
    }

    public Integer getLocalesID() {
        return localesID;
    }

    public void setLocalesID(Integer localesID) {
        this.localesID = localesID;
    }

    public Integer getAreaID() {
        return areaID;
    }

    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    public Integer getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(Integer empleadoID) {
        this.empleadoID = empleadoID;
    }

    public Integer getOficinaID() {
        return oficinaID;
    }

    public void setOficinaID(Integer oficinaID) {
        this.oficinaID = oficinaID;
    }

    public String getTipoCausalAlta() {
        return tipoCausalAlta;
    }

    public void setTipoCausalAlta(String tipoCausalAlta) {
        this.tipoCausalAlta = tipoCausalAlta;
    }

    public Integer getAnho() {
        return anho;
    }

    public void setAnho(Integer anho) {
        this.anho = anho;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getValorLibro() {
        return valorLibro;
    }

    public void setValorLibro(Double valorLibro) {
        this.valorLibro = valorLibro;
    }

    public String getCuentaConSeguro() {
        return cuentaConSeguro;
    }

    public void setCuentaConSeguro(String cuentaConSeguro) {
        this.cuentaConSeguro = cuentaConSeguro;
    }

    public String getEstadoBien() {
        return estadoBien;
    }

    public void setEstadoBien(String estadoBien) {
        this.estadoBien = estadoBien;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getNumeroDocAdquisicion() {
        return numeroDocAdquisicion;
    }

    public void setNumeroDocAdquisicion(String numeroDocAdquisicion) {
        this.numeroDocAdquisicion = numeroDocAdquisicion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getEntDisp() {
        return entDisp;
    }

    public void setEntDisp(String entDisp) {
        this.entDisp = entDisp;
    }

    public String getEntArre() {
        return entArre;
    }

    public void setEntArre(String entArre) {
        this.entArre = entArre;
    }

    public Date getFechaVafec() {
        return fechaVafec;
    }

    public void setFechaVafec(Date fechaVafec) {
        this.fechaVafec = fechaVafec;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getMotivoEliminacionBien() {
        return motivoEliminacionBien;
    }

    public void setMotivoEliminacionBien(String motivoEliminacionBien) {
        this.motivoEliminacionBien = motivoEliminacionBien;
    }

    public Double getValorAdquisicion() {
        return valorAdquisicion;
    }

    public void setValorAdquisicion(Double valorAdquisicion) {
        this.valorAdquisicion = valorAdquisicion;
    }

    public String getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(String anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public String getResolAfec() {
        return resolAfec;
    }

    public void setResolAfec(String resolAfec) {
        this.resolAfec = resolAfec;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Date getFechaAfec() {
        return fechaAfec;
    }

    public void setFechaAfec(Date fechaAfec) {
        this.fechaAfec = fechaAfec;
    }

    public String getFlgActo() {
        return flgActo;
    }

    public void setFlgActo(String flgActo) {
        this.flgActo = flgActo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFlgCausal() {
        return flgCausal;
    }

    public void setFlgCausal(String flgCausal) {
        this.flgCausal = flgCausal;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroResolucionBaja() {
        return numeroResolucionBaja;
    }

    public void setNumeroResolucionBaja(String numeroResolucionBaja) {
        this.numeroResolucionBaja = numeroResolucionBaja;
    }

    public String getResolArre() {
        return resolArre;
    }

    public void setResolArre(String resolArre) {
        this.resolArre = resolArre;
    }

    public String getDscOtros() {
        return dscOtros;
    }

    public void setDscOtros(String dscOtros) {
        this.dscOtros = dscOtros;
    }

    public Date getFechaArre() {
        return fechaArre;
    }

    public void setFechaArre(Date fechaArre) {
        this.fechaArre = fechaArre;
    }

    public Date getFechaDisposicion() {
        return fechaDisposicion;
    }

    public void setFechaDisposicion(Date fechaDisposicion) {
        this.fechaDisposicion = fechaDisposicion;
    }

    public String getResolDisp() {
        return resolDisp;
    }

    public void setResolDisp(String resolDisp) {
        this.resolDisp = resolDisp;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNumeroChasis() {
        return numeroChasis;
    }

    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    public String getValortasa() {
        return valortasa;
    }

    public void setValortasa(String valortasa) {
        this.valortasa = valortasa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSitBinv() {
        return sitBinv;
    }

    public void setSitBinv(String sitBinv) {
        this.sitBinv = sitBinv;
    }

    public String getNumeroCuentaContable() {
        return numeroCuentaContable;
    }

    public void setNumeroCuentaContable(String numeroCuentaContable) {
        this.numeroCuentaContable = numeroCuentaContable;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getEstBien() {
        return estBien;
    }

    public void setEstBien(String estBien) {
        this.estBien = estBien;
    }

    public String getEntAfec() {
        return entAfec;
    }

    public void setEntAfec(String entAfec) {
        this.entAfec = entAfec;
    }

    public String getCodanterio() {
        return codanterio;
    }

    public void setCodanterio(String codanterio) {
        this.codanterio = codanterio;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getEstGestio() {
        return estGestio;
    }

    public void setEstGestio(String estGestio) {
        this.estGestio = estGestio;
    }

    public String getDocBaja() {
        return docBaja;
    }

    public void setDocBaja(String docBaja) {
        this.docBaja = docBaja;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaVarre() {
        return fechaVarre;
    }

    public void setFechaVarre(Date fechaVarre) {
        this.fechaVarre = fechaVarre;
    }

    public String getDenominacionBien() {
        return denominacionBien;
    }

    public void setDenominacionBien(String denominacionBien) {
        this.denominacionBien = denominacionBien;
    }

    public Date getFechaDepreciacion() {
        return fechaDepreciacion;
    }

    public void setFechaDepreciacion(Date fechaDepreciacion) {
        this.fechaDepreciacion = fechaDepreciacion;
    }

    public Double getValorDeprecEjercicio() {
        return valorDeprecEjercicio;
    }

    public void setValorDeprecEjercicio(Double valorDeprecEjercicio) {
        this.valorDeprecEjercicio = valorDeprecEjercicio;
    }

    public Double getValorDeprecAcumulado() {
        return valorDeprecAcumulado;
    }

    public void setValorDeprecAcumulado(Double valorDeprecAcumulado) {
        this.valorDeprecAcumulado = valorDeprecAcumulado;
    }

    public Double getValorNeto() {
        return valorNeto;
    }

    public void setValorNeto(Double valorNeto) {
        this.valorNeto = valorNeto;
    }

    public String getTipUsoCuenta() {
        return tipUsoCuenta;
    }

    public void setTipUsoCuenta(String tipUsoCuenta) {
        this.tipUsoCuenta = tipUsoCuenta;
    }

    public Integer getCatalogoBienID() {
        return catalogoBienID;
    }

    public void setCatalogoBienID(Integer catalogoBienID) {
        this.catalogoBienID = catalogoBienID;
    }

    public String getOtrasCaract() {
        return otrasCaract;
    }

    public void setOtrasCaract(String otrasCaract) {
        this.otrasCaract = otrasCaract;
    }

    public String getDescripcionUbicacionBien() {
        return descripcionUbicacionBien;
    }

    public void setDescripcionUbicacionBien(String descripcionUbicacionBien) {
        this.descripcionUbicacionBien = descripcionUbicacionBien;
    }

    public String getCausalBaja() {
        return causalBaja;
    }

    public void setCausalBaja(String causalBaja) {
        this.causalBaja = causalBaja;
    }

    public String getActoDisposicionBien() {
        return actoDisposicionBien;
    }

    public void setActoDisposicionBien(String actoDisposicionBien) {
        this.actoDisposicionBien = actoDisposicionBien;
    }

    public String getEntidadBeneficiadaActoDisposicion() {
        return entidadBeneficiadaActoDisposicion;
    }

    public void setEntidadBeneficiadaActoDisposicion(String entidadBeneficiadaActoDisposicion) {
        this.entidadBeneficiadaActoDisposicion = entidadBeneficiadaActoDisposicion;
    }

    public String getActoAdministracionBien() {
        return actoAdministracionBien;
    }

    public void setActoAdministracionBien(String actoAdministracionBien) {
        this.actoAdministracionBien = actoAdministracionBien;
    }

    public String getNumeroResolucionAdministracion() {
        return numeroResolucionAdministracion;
    }

    public void setNumeroResolucionAdministracion(String numeroResolucionAdministracion) {
        this.numeroResolucionAdministracion = numeroResolucionAdministracion;
    }

    public Date getFechaAdministracion() {
        return fechaAdministracion;
    }

    public void setFechaAdministracion(Date fechaAdministracion) {
        this.fechaAdministracion = fechaAdministracion;
    }

    public Date getFechaVencActoAdmin() {
        return fechaVencActoAdmin;
    }

    public void setFechaVencActoAdmin(Date fechaVencActoAdmin) {
        this.fechaVencActoAdmin = fechaVencActoAdmin;
    }

    public String getEntidadBeneficiadaActoAdmin() {
        return entidadBeneficiadaActoAdmin;
    }

    public void setEntidadBeneficiadaActoAdmin(String entidadBeneficiadaActoAdmin) {
        this.entidadBeneficiadaActoAdmin = entidadBeneficiadaActoAdmin;
    }

    public String getDocAltaSbn() {
        return docAltaSbn;
    }

    public void setDocAltaSbn(String docAltaSbn) {
        this.docAltaSbn = docAltaSbn;
    }

    public String getDocBajaSbn() {
        return docBajaSbn;
    }

    public void setDocBajaSbn(String docBajaSbn) {
        this.docBajaSbn = docBajaSbn;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroResolucionDisp() {
        return numeroResolucionDisp;
    }

    public void setNumeroResolucionDisp(String numeroResolucionDisp) {
        this.numeroResolucionDisp = numeroResolucionDisp;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

}
