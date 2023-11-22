package com.simios.simioapp.dominio.utiles;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simios.simioapp.comunes.utiles.NumberUtil;
import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.dominio.entidades.InventarioBienEntity;
import com.simios.simioapp.dominio.entidades.sync.DataSync;
import org.apache.commons.lang.StringUtils;

public class EntityUtil {

    public static DataSync parseJSONToDataSync(String dataSyncJSON) throws java.io.IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper.readValue(dataSyncJSON, DataSync.class);
    }

    public static boolean tienenMismoBienID(BienEntity bien01, BienEntity bien02) {

        return NumberUtil.equalsInteger(bien01.getBienID(), bien02.getBienID());
    }

    public static boolean tienenMismoCodigoPatrimonialUnique(BienEntity bien01, BienEntity bien02) {
        // valida tanto el codigo patrimonial como la entidadID
        return NumberUtil.equalsInteger(bien01.getEntidadID(), bien02.getEntidadID()) && StringUtils.equals(bien01.getCodigoPatrimonial(), bien02.getCodigoPatrimonial());
    }

    public static void copiarBien(BienEntity origen, BienEntity destino) {

        if (origen == null) throw new IllegalArgumentException("origen cannot be null");
        if (destino == null) throw new IllegalArgumentException("destino cannot be null");

        destino.setActoAdministracionBien(origen.getActoAdministracionBien());
        destino.setActoDisposicionBien(origen.getActoDisposicionBien());
        destino.setAltura(origen.getAltura());
        destino.setAncho(origen.getAncho());
        destino.setAnho(origen.getAnho());
        destino.setAnioFabricacion(origen.getAnioFabricacion());
        destino.setAreaID(origen.getAreaID());
        destino.setBienID(origen.getBienID());
        destino.setCatalogoBienID(origen.getCatalogoBienID());
        destino.setCausalBaja(origen.getCausalBaja());
        destino.setCodanterio(origen.getCodanterio());
        destino.setCodigoBarras(origen.getCodigoBarras());
        destino.setCodigoPatrimonial(origen.getCodigoPatrimonial());
        destino.setColor(origen.getColor());
        destino.setCondicion(origen.getCondicion());
        destino.setCuentaConSeguro(origen.getCuentaConSeguro());
        destino.setDenominacionBien(origen.getDenominacionBien());
        destino.setDescripcionUbicacionBien(origen.getDescripcionUbicacionBien());
        destino.setDimension(origen.getDimension());
        destino.setDocAltaSbn(origen.getDocAltaSbn());
        destino.setDocBaja(origen.getDocBaja());
        destino.setDocBajaSbn(origen.getDocBajaSbn());
        destino.setDscOtros(origen.getDscOtros());
        destino.setEdad(origen.getEdad());
        destino.setEmpleadoID(origen.getEmpleadoID());
        destino.setEntAfec(origen.getEntAfec());
        destino.setEntArre(origen.getEntArre());
        destino.setEntDisp(origen.getEntDisp());
        destino.setEntidadBeneficiadaActoAdmin(origen.getEntidadBeneficiadaActoAdmin());
        destino.setEntidadBeneficiadaActoDisposicion(origen.getEntidadBeneficiadaActoDisposicion());
        destino.setEntidadID(origen.getEntidadID());
        destino.setEspecie(origen.getEspecie());
        destino.setEstBien(origen.getEstBien());
        destino.setEstGestio(origen.getEstGestio());
        destino.setEstadoBien(origen.getEstadoBien());
//        destino.setEstadoSubida(null); // solo esta en InventarioBienEntity
        destino.setFechaAdministracion(origen.getFechaAdministracion());
        destino.setFechaAdquisicion(origen.getFechaAdquisicion());
        destino.setFechaAfec(origen.getFechaAfec());
        destino.setFechaArre(origen.getFechaArre());
        destino.setFechaBaja(origen.getFechaBaja());
        destino.setFechaDepreciacion(origen.getFechaDepreciacion());
        destino.setFechaDisposicion(origen.getFechaDisposicion());
        destino.setFechaVafec(origen.getFechaVafec());
        destino.setFechaVarre(origen.getFechaVarre());
        destino.setFechaVencActoAdmin(origen.getFechaVencActoAdmin());
        destino.setFlgActo(origen.getFlgActo());
        destino.setFlgCausal(origen.getFlgCausal());
//        destino.setInventariador(null);  // solo esta en InventarioBienEntity
//        destino.setInventarioBienID(null); // solo esta en InventarioBienEntity
//         inventariado_por
//         inventariador_anterior
//        destino.setInventarioID(null); // solo esta en InventarioBienEntity
        destino.setLocalesID(origen.getLocalesID());
        destino.setLongitud(origen.getLongitud());
        destino.setMarca(origen.getMarca());
        destino.setMatricula(origen.getMatricula());
        destino.setModelo(origen.getModelo());
        destino.setMotivoEliminacionBien(origen.getMotivoEliminacionBien());
        destino.setNumeroChasis(origen.getNumeroChasis());
        destino.setNumeroCuentaContable(origen.getNumeroCuentaContable());
        destino.setNumeroDocAdquisicion(origen.getNumeroDocAdquisicion());
        destino.setNumeroMotor(origen.getNumeroMotor());
        destino.setNumeroResolucionAdministracion(origen.getNumeroResolucionAdministracion());
        destino.setNumeroResolucionBaja(origen.getNumeroResolucionBaja());
        destino.setNumeroResolucionDisp(origen.getNumeroResolucionDisp());
        destino.setOficinaID(origen.getOficinaID());
        destino.setOtrasCaract(origen.getOtrasCaract());
        destino.setPais(origen.getPais());
        destino.setPlaca(origen.getPlaca());
        destino.setRaza(origen.getRaza());
        destino.setResolAfec(origen.getResolAfec());
        destino.setResolArre(origen.getResolArre());
        destino.setResolDisp(origen.getResolDisp());
//        revisado_toma (1,0)
        destino.setSerie(origen.getSerie());
        destino.setSitBinv(origen.getSitBinv());
//        destino.setSobranteFaltante(null);  // solo esta en InventarioBienEntity
        destino.setTipUsoCuenta(origen.getTipUsoCuenta());
        destino.setTipo(origen.getTipo());
        destino.setTipoCausalAlta(origen.getTipoCausalAlta());
        destino.setTipoCuenta(origen.getTipoCuenta());
        destino.setValorAdquisicion(origen.getValorAdquisicion());
        destino.setValorDeprecAcumulado(origen.getValorDeprecAcumulado());
        destino.setValorDeprecEjercicio(origen.getValorDeprecEjercicio());
        destino.setValorLibro(origen.getValorLibro());
        destino.setValorNeto(origen.getValorNeto());
        destino.setValortasa(origen.getValortasa());

        // campos de auditoria
        destino.setFechaReg(origen.getFechaReg());
        destino.setFechaAct(origen.getFechaAct());
        destino.setUsuReg(origen.getUsuReg());
        destino.setUsuAct(origen.getUsuAct());
        destino.setIndDel(origen.getIndDel());
    }

    public static void copiarInventarioBien(InventarioBienEntity origen, InventarioBienEntity destino) {

        copiarBien(origen, destino);

        destino.setEstadoSubida(origen.getEstadoSubida());
        destino.setInventariador(origen.getInventariador());
        destino.setInventarioBienID(origen.getInventarioBienID());
        destino.setInventarioID(origen.getInventarioID());
        destino.setSobranteFaltante(origen.getSobranteFaltante());

        destino.setInventariadoPor(origen.getInventariadoPor());
        destino.setRevisadoToma(origen.getRevisadoToma());
        destino.setInventariadorAnterior(origen.getInventariadorAnterior());

    }

    public static void trimToNullBien(BienEntity origen) {

        if (origen == null) return;

        origen.setActoAdministracionBien(StringUtils.trimToNull(origen.getActoAdministracionBien()));
        origen.setActoDisposicionBien(StringUtils.trimToNull(origen.getActoDisposicionBien()));
        origen.setAltura(StringUtils.trimToNull(origen.getAltura()));
//        origen.setAncho(StringUtils.trimToNull(origen.getAncho()));
//        origen.setAnho(StringUtils.trimToNull(origen.getAnho()));
        origen.setAnioFabricacion(StringUtils.trimToNull(origen.getAnioFabricacion()));
//        origen.setAreaID(StringUtils.trimToNull(origen.getAreaID()));
//        origen.setBienID(StringUtils.trimToNull(origen.getBienID()));
//        origen.setCatalogoBienID(StringUtils.trimToNull(origen.getCatalogoBienID()));
        origen.setCausalBaja(StringUtils.trimToNull(origen.getCausalBaja()));
        origen.setCodanterio(StringUtils.trimToNull(origen.getCodanterio()));
        origen.setCodigoBarras(StringUtils.trimToNull(origen.getCodigoBarras()));
        origen.setCodigoPatrimonial(StringUtils.trimToNull(origen.getCodigoPatrimonial()));
        origen.setColor(StringUtils.trimToNull(origen.getColor()));
        origen.setCondicion(StringUtils.trimToNull(origen.getCondicion()));
        origen.setCuentaConSeguro(StringUtils.trimToNull(origen.getCuentaConSeguro()));
        origen.setDenominacionBien(StringUtils.trimToNull(origen.getDenominacionBien()));
        origen.setDescripcionUbicacionBien(StringUtils.trimToNull(origen.getDescripcionUbicacionBien()));
        origen.setDimension(StringUtils.trimToNull(origen.getDimension()));
        origen.setDocAltaSbn(StringUtils.trimToNull(origen.getDocAltaSbn()));
        origen.setDocBaja(StringUtils.trimToNull(origen.getDocBaja()));
        origen.setDocBajaSbn(StringUtils.trimToNull(origen.getDocBajaSbn()));
        origen.setDscOtros(StringUtils.trimToNull(origen.getDscOtros()));
        origen.setEdad(StringUtils.trimToNull(origen.getEdad()));
//        origen.setEmpleadoID(StringUtils.trimToNull(origen.getEmpleadoID()));
        origen.setEntAfec(StringUtils.trimToNull(origen.getEntAfec()));
        origen.setEntArre(StringUtils.trimToNull(origen.getEntArre()));
        origen.setEntDisp(StringUtils.trimToNull(origen.getEntDisp()));
        origen.setEntidadBeneficiadaActoAdmin(StringUtils.trimToNull(origen.getEntidadBeneficiadaActoAdmin()));
        origen.setEntidadBeneficiadaActoDisposicion(StringUtils.trimToNull(origen.getEntidadBeneficiadaActoDisposicion()));
//        origen.setEntidadID(StringUtils.trimToNull(origen.getEntidadID()));
        origen.setEspecie(StringUtils.trimToNull(origen.getEspecie()));
        origen.setEstBien(StringUtils.trimToNull(origen.getEstBien()));
        origen.setEstGestio(StringUtils.trimToNull(origen.getEstGestio()));
        origen.setEstadoBien(StringUtils.trimToNull(origen.getEstadoBien()));
//        destino.setEstadoSubida(null)); // solo esta en InventarioBienEntity
//        origen.setFechaAdministracion(StringUtils.trimToNull(origen.getFechaAdministracion()));
//        origen.setFechaAdquisicion(StringUtils.trimToNull(origen.getFechaAdquisicion()));
//        origen.setFechaAfec(StringUtils.trimToNull(origen.getFechaAfec()));
//        origen.setFechaArre(StringUtils.trimToNull(origen.getFechaArre()));
//        origen.setFechaBaja(StringUtils.trimToNull(origen.getFechaBaja()));
//        origen.setFechaDepreciacion(StringUtils.trimToNull(origen.getFechaDepreciacion()));
//        origen.setFechaDisposicion(StringUtils.trimToNull(origen.getFechaDisposicion()));
//        origen.setFechaVafec(StringUtils.trimToNull(origen.getFechaVafec()));
//        origen.setFechaVarre(StringUtils.trimToNull(origen.getFechaVarre()));
//        origen.setFechaVencActoAdmin(StringUtils.trimToNull(origen.getFechaVencActoAdmin()));
        origen.setFlgActo(StringUtils.trimToNull(origen.getFlgActo()));
        origen.setFlgCausal(StringUtils.trimToNull(origen.getFlgCausal()));
//        destino.setInventariador(null));  // solo esta en InventarioBienEntity
//        destino.setInventarioBienID(null)); // solo esta en InventarioBienEntity
//         inventariado_por
//         inventariador_anterior
//        destino.setInventarioID(null)); // solo esta en InventarioBienEntity
//        origen.setLocalesID(StringUtils.trimToNull(origen.getLocalesID()));
        origen.setLongitud(StringUtils.trimToNull(origen.getLongitud()));
        origen.setMarca(StringUtils.trimToNull(origen.getMarca()));
        origen.setMatricula(StringUtils.trimToNull(origen.getMatricula()));
        origen.setModelo(StringUtils.trimToNull(origen.getModelo()));
        origen.setMotivoEliminacionBien(StringUtils.trimToNull(origen.getMotivoEliminacionBien()));
        origen.setNumeroChasis(StringUtils.trimToNull(origen.getNumeroChasis()));
        origen.setNumeroCuentaContable(StringUtils.trimToNull(origen.getNumeroCuentaContable()));
        origen.setNumeroDocAdquisicion(StringUtils.trimToNull(origen.getNumeroDocAdquisicion()));
        origen.setNumeroMotor(StringUtils.trimToNull(origen.getNumeroMotor()));
        origen.setNumeroResolucionAdministracion(StringUtils.trimToNull(origen.getNumeroResolucionAdministracion()));
        origen.setNumeroResolucionBaja(StringUtils.trimToNull(origen.getNumeroResolucionBaja()));
        origen.setNumeroResolucionDisp(StringUtils.trimToNull(origen.getNumeroResolucionDisp()));
//        origen.setOficinaID(StringUtils.trimToNull(origen.getOficinaID()));
        origen.setOtrasCaract(StringUtils.trimToNull(origen.getOtrasCaract()));
        origen.setPais(StringUtils.trimToNull(origen.getPais()));
        origen.setPlaca(StringUtils.trimToNull(origen.getPlaca()));
        origen.setRaza(StringUtils.trimToNull(origen.getRaza()));
        origen.setResolAfec(StringUtils.trimToNull(origen.getResolAfec()));
        origen.setResolArre(StringUtils.trimToNull(origen.getResolArre()));
        origen.setResolDisp(StringUtils.trimToNull(origen.getResolDisp()));
//        revisado_toma (1,0)
        origen.setSerie(StringUtils.trimToNull(origen.getSerie()));
        origen.setSitBinv(StringUtils.trimToNull(origen.getSitBinv()));
//        destino.setSobranteFaltante(null));  // solo esta en InventarioBienEntity
        origen.setTipUsoCuenta(StringUtils.trimToNull(origen.getTipUsoCuenta()));
        origen.setTipo(StringUtils.trimToNull(origen.getTipo()));
        origen.setTipoCausalAlta(StringUtils.trimToNull(origen.getTipoCausalAlta()));
        origen.setTipoCuenta(StringUtils.trimToNull(origen.getTipoCuenta()));
//        origen.setValorAdquisicion(StringUtils.trimToNull(origen.getValorAdquisicion()));
//        origen.setValorDeprecAcumulado(StringUtils.trimToNull(origen.getValorDeprecAcumulado()));
//        origen.setValorDeprecEjercicio(StringUtils.trimToNull(origen.getValorDeprecEjercicio()));
//        origen.setValorLibro(StringUtils.trimToNull(origen.getValorLibro()));
//        origen.setValorNeto(StringUtils.trimToNull(origen.getValorNeto()));
        origen.setValortasa(StringUtils.trimToNull(origen.getValortasa()));

        // campos de auditoria
//        origen.setFechaReg(StringUtils.trimToNull(origen.getFechaReg()));
//        origen.setFechaAct(StringUtils.trimToNull(origen.getFechaAct()));
        origen.setUsuReg(StringUtils.trimToNull(origen.getUsuReg()));
        origen.setUsuAct(StringUtils.trimToNull(origen.getUsuAct()));
        origen.setIndDel(StringUtils.trimToNull(origen.getIndDel()));
    }

    public static void trimToNullInventarioBien(InventarioBienEntity origen) {

        if (origen == null) return;

        trimToNullBien(origen);

        origen.setEstadoSubida(StringUtils.trimToNull(origen.getEstadoSubida()));
        origen.setInventariador(StringUtils.trimToNull(origen.getInventariador()));
//        origen.setInventarioBienID(StringUtils.trimToNull(origen.getInventarioBienID()));
//        origen.setInventarioID(StringUtils.trimToNull(origen.getInventarioID()));
        origen.setSobranteFaltante(StringUtils.trimToNull(origen.getSobranteFaltante()));

        origen.setInventariadoPor(StringUtils.trimToNull(origen.getInventariadoPor()));
        origen.setRevisadoToma(StringUtils.trimToNull(origen.getRevisadoToma()));
        origen.setInventariadorAnterior(StringUtils.trimToNull(origen.getInventariadorAnterior()));
    }
}
