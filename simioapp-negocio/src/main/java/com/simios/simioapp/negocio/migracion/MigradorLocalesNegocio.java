package com.simios.simioapp.negocio.migracion;

import com.jcfr.utiles.DateTime;
import com.linuxense.javadbf.DBFReader;
import com.simios.simioapp.comunes.beans.ErrorLineaMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.JSONUtil;
import com.simios.simioapp.dominio.entidades.LocalesEntity;
import com.simios.simioapp.negocio.base.BaseMigradorNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("migradorLocalesNegocio")
public class MigradorLocalesNegocio extends BaseMigradorNegocio {

    // campos principales en el DBF (que relacionan con los unique key en la BD)
    private static final int DBF_CODINST = 0;
    private static final int DBF_CODIGO = 1;

    @Transactional(rollbackFor=Throwable.class)
    public ResultadoMigracionBean migrarDBFLocales(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) throws IOException {

        Date now = DateTime.getNow().toDate();

        // lista de errores
        ArrayList<ErrorLineaMigracionBean> listaErrores = new ArrayList<>();

        // contadores de filas
        int nroFilasMigradas = 0;
        int nroFilasNoMigradas = 0;
        int nroFila = 0;

        // variables para la lectura de cada registro
        DBFReader reader = new DBFReader(inputStreamDBF);

        // campos de cada registro DBF
        Object[] rowObjects;
        while ((rowObjects = reader.nextRecord()) != null) {

            try {

                nroFila++;

                LocalesEntity localesInsert = new LocalesEntity();

                for (int i = 0; i < rowObjects.length; i++) {

                    final String valueObject = getStringValue(rowObjects[i]);

                    // Aplicar validaciones para migrar
                    switch (i) {
                        case DBF_CODINST:
                            // CODINST -> entidad_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODINST del DBF esta empty");
                            }

                            Integer entityID = buscarEntityIDByUnique(valueObject);

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            if (entityID == null) {
                                throw new Exception("Campo CODINST del DBF no existe en la tabla entidad de la BD -> " + valueObject);
                            }

                            if (entityID.intValue() != entidadIDMigracion.intValue()) {
                                throw new Exception("Código de entidades no coinciden. EntidadID DBF: " + entityID + ", EntidadID Migracion: " + entidadIDMigracion.intValue());
                            }

                            localesInsert.setEntidadID(entityID);
                            break;
                        case DBF_CODIGO:
                            // CODIGO -> codigo
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODIGO del DBF esta empty");
                            }
                            localesInsert.setCodigo(valueObject);
                            break;
                        case 2:
                            // locales -> nombre_local
                            localesInsert.setNombreLocal(valueObject);
                            break;

                        case 3:
                            // FLG_TIPOVI -> FLG_TIPOVI
                            localesInsert.setFlgTipovi(valueObject);
                            break;
                        case 4:
                            // direccion -> direccion
                            localesInsert.setDireccion(valueObject);
                            break;
                        case 5:
                            // NUM_MUN -> num_mun
                            localesInsert.setNumeroMun(valueObject);
                            break;
                        case 6:
                            // DSC_MZ -> dsc_mz
                            localesInsert.setDscMz(valueObject);
                            break;
                        case 7:
                            // DSC_LOTE -> dsc_lote
                            localesInsert.setDscLote(valueObject);
                            break;
                        case 8:
                            // UBIGEO -> cod_ubigeo
                            if (!JS._numeroEntero(valueObject)) {
                                throw new Exception("Campo UBIGEO no tiene formato de ubigeo correcto -> " + valueObject);
                            }

                            localesInsert.setCodigoUbigeo(valueObject);
                            break;
                        case 9:
                            // CTD_AREA -> ctd_area
                            if (!JS._numeroDecimal(valueObject)) {
                                throw new Exception("Campo CTD_AREA no es un número decimal -> " + valueObject);
                            }

                            localesInsert.setCtdArea(Double.valueOf(valueObject));
                            break;
                        case 10:
                            // FLG_UM -> flg_um
                            localesInsert.setFlgUm(valueObject);
                            break;
                        case 11:
                            // FLG_TIPORE -> flg_tipore
                            localesInsert.setFlgTipore(valueObject);
                            break;
                        case 12:
                            // DSC_ASIENT -> dsc_asient
                            localesInsert.setDscAsient(valueObject);
                            break;
                        case 13:
                            // DSC_FOJAS	-> dsc_fojas
                            localesInsert.setDscFojas(valueObject);
                            break;
                        case 14:
                            // DSC_TOMO -> dsc_tomo
                            localesInsert.setDscTomo(valueObject);
                            break;
                        case 15:
                            // DSC_PELECT -> dsc_pelect
                            localesInsert.setDscPelect(valueObject);
                            break;
                        case 16:
                            // COD_PREDIO - > cod_predio
                            localesInsert.setCodigoPredio(valueObject);
                            break;
                        case 17:
                            // DSC_BENEFI -> dsc_benefi
                            localesInsert.setDscBenefi(valueObject);
                            break;
                        case 18:
                            // DSC_PROPIE -> dsc_propie
                            localesInsert.setDscPropie(valueObject);
                            break;
                        case 19:
                            // DSC_CTACTB -> dsc_ctactb
                            localesInsert.setDscCtactb(valueObject);
                            break;
                        case 20:
                            // FLG_TIPOMO -> flg_tipomo
                            localesInsert.setFlgTipomo(valueObject);
                            break;
                        case 21:
                            // CTD_VALOR -> ctd_valor
                            if (!JS._numero(valueObject)) {
                                throw new Exception("Campo CTD_VALOR no es un número -> " + valueObject);
                            }

                            localesInsert.setCtdValor(Double.valueOf(valueObject));
                            break;
                        case 22:
                            // FLG_PROPIE -> flg_propie
                            localesInsert.setFlgPropie(valueObject);
                            break;
                        case 23:
                            // DSC_ASINAB -> dsc_asinab
                            localesInsert.setDscAsinab(valueObject);
                            break;
                    }

                }

                // validar que no se haya insertado previamente en la BD MySQL
                if (existeLocalesByUnique(localesInsert)) {
                    throw new Exception("Local ya fue insertado en BD -> " + JSONUtil.toJSON(localesInsert, false));
                }

                // seteando campos de adicionales de auditoria
                localesInsert.setIndDel(Constantes.REGISTRO_ACTIVO);
                localesInsert.setUsuAct(usuarioSession.getUsuarioSessionID());
                localesInsert.setUsuReg(usuarioSession.getUsuarioSessionID());
                localesInsert.setFechaAct(now);
                localesInsert.setFechaReg(now);

                // insertar en la BD MySQL
                localesDAO.insert(localesInsert);

                nroFilasMigradas++;

            } catch (Exception sos) {

                nroFilasNoMigradas++;
                listaErrores.add(new ErrorLineaMigracionBean(String.valueOf(nroFila), sos.getMessage()));
            }

        }

        // By now, we have itereated through all of the rows
        inputStreamDBF.close();

        // resultado de la migracion
        ResultadoMigracionBean result = new ResultadoMigracionBean();

        result.setListaErrores(listaErrores);
        result.setNombreTabla("locales");
        result.setNroFilasEncontradas(nroFila);
        result.setNroFilasMigradas(nroFilasMigradas);
        result.setNroFilasNoMigradas(nroFilasNoMigradas);
        result.setOcurrioError(nroFilasNoMigradas > 0);
        result.setMsgError(nroFilasNoMigradas > 0 ? "No se migraron varias filas. Por favor revisar" : StringUtils.EMPTY);

        return result;
    }

    private boolean existeLocalesByUnique(LocalesEntity entity) throws Exception {

        if (entity == null) new IllegalAccessException("entity must be not null");

        // buscamos por sus campos unique
        LocalesEntity filter = new LocalesEntity();

        filter.setCodigo(entity.getCodigo());
        filter.setEntidadID(entity.getEntidadID());

        List<LocalesEntity> results = localesDAO.select(filter);

        return CollectionUtils.isNotEmpty(results);
    }

}
