package com.simios.simioapp.dominio.entidades;

import com.simios.simioapp.dominio.entidades.base.AuditoriaFields;

public class CuentaEntity extends AuditoriaFields {

    private Integer cuentaID; // cuenta_id
    private String cuenta; // cuenta
    private String denomina; // denomina
    private String tipoCta; // tipo_cta
    private String flagCta; // flag_cta
    private String usoCta; // uso_cta

    public CuentaEntity() {

    }

    public Integer getCuentaID() {
        return cuentaID;
    }

    public void setCuentaID(Integer cuentaID) {
        this.cuentaID = cuentaID;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDenomina() {
        return denomina;
    }

    public void setDenomina(String denomina) {
        this.denomina = denomina;
    }

    public String getTipoCta() {
        return tipoCta;
    }

    public void setTipoCta(String tipoCta) {
        this.tipoCta = tipoCta;
    }

    public String getFlagCta() {
        return flagCta;
    }

    public void setFlagCta(String flagCta) {
        this.flagCta = flagCta;
    }

    public String getUsoCta() {
        return usoCta;
    }

    public void setUsoCta(String usoCta) {
        this.usoCta = usoCta;
    }

}
