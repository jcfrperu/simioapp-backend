package com.simios.simioapp.negocio.migracion;

import com.jcfr.utiles.DateTime;
import com.linuxense.javadbf.DBFReader;
import com.simios.simioapp.comunes.beans.ErrorLineaMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionBean;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dominio.entidades.BienEntity;
import com.simios.simioapp.dominio.entidades.CatalogoBienEntity;
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

@Component("migradorBienNegocio")
public class MigradorBienNegocio extends BaseMigradorNegocio {

    // campos principales en el DBF (que relacionan con los unique key en la BD)
    private static final int DBF_CODBIEN = 0;
    private static final int DBF_CODINST = 1;
    private static final int DBF_CODLOCAL = 2;
    private static final int DBF_CODAREA = 3;
    private static final int DBF_UBICACION = 4;
    private static final int DBF_CODUSUARIO = 6;

    @Transactional(rollbackFor=Throwable.class)
    public ResultadoMigracionBean migrarDBFBienes(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) throws IOException {

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

                BienEntity bienEntity = new BienEntity();

                for (int i = 0; i < rowObjects.length; i++) {

                    final String valueObject = getStringValue(rowObjects[i]);

                    Integer entityID = null;
                    Integer localesID = null;
                    Integer areaID = null;
                    Integer oficinaID = null;

                    switch (i) {
                        case DBF_CODBIEN:
                            // CODBIEN -> codigo_patrimonial
                            bienEntity.setCodigoPatrimonial(valueObject);
                            break;
                        case DBF_CODINST:
                            // CODINST -> entidad_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODINST del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(valueObject);

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

                            bienEntity.setEntidadID(entityID);
                            break;
                        case DBF_CODLOCAL:
                            // CODLOCAL -> locales_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODLOCAL del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            localesID = buscarLocalesIDByUnique(entityID, valueObject);
                            if (localesID == null) {
                                throw new Exception("Campos CODINST Y CODLOCAL del DBF no hacen match en la tabla locales de la BD -> " + entityID + ", " + valueObject);
                            }

                            bienEntity.setLocalesID(localesID);
                            break;
                        case DBF_CODAREA:
                            // CODAREA -> area_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODAREA del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            localesID = buscarLocalesIDByUnique(entityID, getStringValue(rowObjects[DBF_CODLOCAL]));

                            areaID = buscarAreaIDByUnique(valueObject, entityID, localesID);
                            if (areaID == null) {
                                throw new Exception("Campos CODINST, CODLOCAL Y CODAREA del DBF no hacen match en la tabla area de la BD -> " + entityID + ", " + localesID + ", " + valueObject);
                            }

                            bienEntity.setAreaID(areaID);
                            break;
                        case DBF_UBICACION:
                            // UBICACION -> oficina_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo UBICACION del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            localesID = buscarLocalesIDByUnique(entityID, getStringValue(rowObjects[DBF_CODLOCAL]));
                            areaID = buscarAreaIDByUnique(getStringValue(rowObjects[DBF_CODAREA]), entityID, localesID);

                            oficinaID = buscarOficinaIDByUnique(valueObject, entityID, localesID, areaID);
                            if (oficinaID == null) {
                                throw new Exception("Campos CODINST, CODLOCAL, CODAREA y UBICACION del DBF no hacen match en la tabla area de la BD -> " + entityID + ", " + localesID + ", " + areaID + ", " + valueObject);
                            }

                            bienEntity.setOficinaID(oficinaID);
                            break;
                        case 5:
                            bienEntity.setCodanterio(valueObject);
                            break;
                        case DBF_CODUSUARIO:
                            // CODUSUARIO -> empleado_id
                            if (StringUtils.isBlank(valueObject)) {
                                throw new Exception("Campo CODIGO del DBF esta empty");
                            }

                            entityID = buscarEntityIDByUnique(getStringValue(rowObjects[DBF_CODINST]));

                            // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                            if (forzarUsarEntidadDeMigracion) {
                                entityID = entidadIDMigracion;
                            }

                            Integer empleadoID = buscarEmpleadoIDByUnique(valueObject, entityID);

                            if (empleadoID == null) {
                                throw new Exception("Campos CODINST y CODUSUARIO del DBF no hacen match en la tabla area de la BD -> " + entityID + ", " + valueObject);
                            }

                            bienEntity.setEmpleadoID(empleadoID);
                            break;
                        case 7:
                            // TIPO_CTA -> tipo_cuenta
                            bienEntity.setTipoCuenta(valueObject);
                            break;
                        case 8:
                            // CUENTA -> nro_cuenta_contable
                            bienEntity.setNumeroCuentaContable(valueObject);
                            break;
                        case 9:
                            // VALORLIBRO -> valor_libro
                            if (JS._numero(valueObject)) {
                                bienEntity.setValorLibro(JS.toDouble(valueObject));
                            }
                            break;
                        case 10:
                            // VALORTASA -> valortasa
                            if (JS._numero(valueObject)) {
                                bienEntity.setValortasa(JS.roundString(JS.toDouble(valueObject), 10));
                            }
                            break;
                        case 11:
                            // ESTADO -> estado_bien
                            bienEntity.setEstadoBien(valueObject);
                            break;
                        case 12:
                            // MARCA -> marca
                            bienEntity.setMarca(valueObject);
                            break;
                        case 13:
                            // MODELO -> modelo
                            bienEntity.setModelo(valueObject);
                            break;
                        case 14:
                            // TIPO -> tipo_causal_alta
                            // NOTA: antes de crear el campo tipo en la tabla bien, este campo se mapeaba contra el campo
                            //       tipo_causal_alta, pero hora el campo tipo_causal_alta se quedo en el aire
                            bienEntity.setTipo(valueObject);
                            break;
                        case 15:
                            // COLOR -> color
                            bienEntity.setColor(valueObject);
                            break;
                        case 16:
                            // SERIE -> serie
                            bienEntity.setSerie(valueObject);
                            break;
                        case 17:
                            // NUMMOTOR -> nro_motor
                            bienEntity.setNumeroMotor(valueObject);
                            break;
                        case 18:
                            // NUMCHASIS -> nro_chasis
                            bienEntity.setNumeroChasis(valueObject);
                            break;
                        case 19:
                            // AÑO -> anho
                            if (JS._numeroEntero(valueObject)) {
                                bienEntity.setAnho(JS.toInt(valueObject));
                            }
                            break;
                        case 20:
                            // DIMENSION -> dimension
                            bienEntity.setDimension(valueObject);
                            break;
                        case 21:
                            // RAZA -> raza
                            bienEntity.setRaza(valueObject);
                            break;
                        case 22:
                            // ESPECIE -> especie
                            bienEntity.setEspecie(valueObject);
                            break;
                        case 23:
                            // fecha de registro, se seta mas abajo con el date
                            break;
                        case 24:
                            // PLACA -> placa
                            bienEntity.setPlaca(valueObject);
                            break;
                        case 25:
                            // EDAD -> edad
                            bienEntity.setEdad(valueObject);
                            break;
                        case 26:
                            // PAIS	pais
                            bienEntity.setPais(valueObject);
                            break;
                        case 27:
                            // CONDICION -> condicion
                            bienEntity.setCondicion(valueObject);
                            break;
                        case 28:
                            // FEC_ACT -> fecha_adquisicion
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaAdquisicion(DateTime.getInstancia(valueObject).toDate());
                            }
                            break;
                        case 29:
                            // ASEGURADO -> cuenta_con_seguro
                            bienEntity.setCuentaConSeguro(valueObject);
                            break;
                        case 30:
                            // EST_BIEN -> est_bien
                            bienEntity.setEstBien(valueObject);
                            break;
                        case 31:
                            // FLG_CAUSAL -> flg_causal
                            bienEntity.setFlgCausal(valueObject);
                            break;
                        case 32:
                            // RESOL_BAJA -> xxxxxxx
                            bienEntity.setNumeroResolucionBaja(valueObject);
                            break;
                        case 33:
                            // FEC_BAJA	fecha_baja
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaBaja(DateTime.getInstancia(valueObject).toDate());
                            }
                            break;
                        case 34:
                            // FLG_ACTO	flg_acto
                            bienEntity.setFlgActo(valueObject);
                            break;
                        case 35:
                            // RESOL_DISP -> resol_disp
                            bienEntity.setResolDisp(valueObject);
                            break;
                        case 36:
                            // FEC_DISP -> fecha_disposicion
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaDisposicion(DateTime.getInstancia(valueObject).toDate());
                            }
                            break;
                        case 37:
                            // CAUSAL_ELI -> motivo_eliminacion_bien
                            bienEntity.setMotivoEliminacionBien(valueObject);
                            break;
                        case 38:
                            // EST_GESTIO -> est_gestio
                            bienEntity.setEstGestio(valueObject);
                            break;
                        case 39:
                            // RESOL_AFEC -> resol_afec
                            bienEntity.setResolAfec(valueObject);
                            break;
                        case 40:
                            // FEC_AFEC -> fec_afec
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaAfec(DateTime.getInstancia(valueObject).toDate());
                            }
                            break;
                        case 41:
                            // FEC_VAFEC -> fec_vafec
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaVafec(DateTime.getInstancia(valueObject).toDate());
                            }
                        case 42:
                            // ENT_AFEC -> ent_afec
                            bienEntity.setEntAfec(valueObject);
                            break;
                        case 43:
                            // RESOL_ARRE -> resol_arre
                            bienEntity.setResolArre(valueObject);
                            break;
                        case 44:
                            // FEC_ARRE -> fec_arre
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaArre(DateTime.getInstancia(valueObject).toDate());
                            }
                            break;
                        case 45:
                            // FEC_VARRE -> fec_varre
                            if (JS._fecha(valueObject, DateTime.FECHA_DDMMAAAA)) {
                                bienEntity.setFechaVarre(DateTime.getInstancia(valueObject).toDate());
                            }
                            break;
                        case 46:
                            // ENT_ARRE -> ent_arre
                            bienEntity.setEntArre(valueObject);
                            break;
                        case 47:
                            // DOC_ALTA -> nro_doc_adquisicion
                            bienEntity.setNumeroDocAdquisicion(valueObject);
                            break;
                        case 48:
                            // DOC_BAJA -> doc_baja
                            bienEntity.setDocBaja(valueObject);
                            break;
                        case 49:
                            // SIT_BINV -> sit_binv
                            bienEntity.setSitBinv(valueObject);
                            break;
                        case 50:
                            // VALORADQ -> valor_adquisicion
                            if (JS._numero(valueObject)) {
                                bienEntity.setValorAdquisicion(JS.toDouble(valueObject));
                            }
                            break;
                        case 51:
                            // ENT_DISP -> ent_disp
                            bienEntity.setEntDisp(valueObject);
                            break;
                        case 52:
                            // MATRICULA -> matricula
                            bienEntity.setMatricula(valueObject);
                            break;
                        case 53:
                            // ANHO_FAB -> anio_fabricacion
                            bienEntity.setAnioFabricacion(valueObject);
                            break;
                        case 54:
                            // LONGITUD -> longitud
                            bienEntity.setLongitud(valueObject);
                            break;
                        case 55:
                            // ALTURA -> altura
                            bienEntity.setAltura(valueObject);
                            break;
                        case 56:
                            // ANCHO -> ancho
                            if (JS._numero(valueObject)) {
                                bienEntity.setAncho(JS.toDouble(valueObject));
                            }
                            break;
                        case 57:
                            // DSC_OTROS -> dsc_otros
                            bienEntity.setDscOtros(valueObject);
                            break;

                    }

                }

                // setear otros campos
                String catalogoBienString = StringUtils.substring(bienEntity.getCodigoPatrimonial(), 0, 8);
                Integer catalogoBienID = buscarCatalogoBienIDByUnique(catalogoBienString);
                if (catalogoBienID == null) {
                    throw new Exception("Catálogo bien " + catalogoBienString + " no fue encontrado en la tabla catalogo_bien");
                }
                bienEntity.setCatalogoBienID(catalogoBienID);

                // con el catalogo bien ID se ingresa su descripcion en el campo denominacion
                CatalogoBienEntity catalogoBienEntity = buscarCatalogoBienByID(catalogoBienID);
                if (catalogoBienEntity != null ) {
                    bienEntity.setDenominacionBien(StringUtils.trimToEmpty(catalogoBienEntity.getDenominacion()));
                }

                // validar que no se haya insertado previamente en la BD MySQL
                if (existeBienUnique(bienEntity)) {
                    throw new Exception("El Bien ya fue insertado en BD (codigo patrimonial, entidad ) -> " + bienEntity.getCodigoPatrimonial() + ", " + bienEntity.getEntidadID());
                }

                // seteando campos de adicionales de auditoria
                bienEntity.setIndDel(Constantes.REGISTRO_ACTIVO);
                bienEntity.setUsuAct(usuarioSession.getUsuarioSessionID());
                bienEntity.setUsuReg(usuarioSession.getUsuarioSessionID());
                bienEntity.setFechaAct(now);
                bienEntity.setFechaReg(now);

                // insertar en la BD MySQL
                bienDAO.insert(bienEntity);

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
        result.setNombreTabla("bien");
        result.setNroFilasEncontradas(nroFila);
        result.setNroFilasMigradas(nroFilasMigradas);
        result.setNroFilasNoMigradas(nroFilasNoMigradas);
        result.setOcurrioError(nroFilasNoMigradas > 0);
        result.setMsgError(nroFilasNoMigradas > 0 ? "No se migraron varias filas. Por favor revisar" : StringUtils.EMPTY);

        return result;
    }

    // FIXME: actualmente bien tiene codigo_patrimonial y entidadID, pero segun yorch son mas
    protected boolean existeBienUnique(BienEntity entity) throws Exception {

        if (entity == null) new IllegalAccessException("entity must be not null");

        // buscamos por sus campos unique
        BienEntity filter = new BienEntity();

        filter.setCodigoPatrimonial(entity.getCodigoPatrimonial());
        filter.setEntidadID(entity.getEntidadID());
//        filter.setLocalesID(entity.getLocalesID());
//        filter.setAreaID(entity.getAreaID());

        List<BienEntity> results = bienDAO.select(filter);

        return CollectionUtils.isNotEmpty(results);
    }

}
