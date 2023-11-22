package com.simios.simioapp.negocio.migracion;

import com.jcfr.utiles.DateTime;
import com.simios.simioapp.comunes.beans.ErrorLineaMigracionBean;
import com.simios.simioapp.comunes.beans.MensajeLineaMigracionBean;
import com.simios.simioapp.comunes.beans.ResultadoMigracionSBNExcelBean;
import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.seguridad.UsuarioSession;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.ExcelUtilXML;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.negocio.*;
import com.simios.simioapp.negocio.base.BaseMigradorNegocio;
import com.simios.simioapp.negocio.excel.FormatoExcelNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("migradorExcelSBNNegocio")
public class MigradorExcelSBNNegocio extends BaseMigradorNegocio {

    @Autowired
    @Qualifier("entidadNegocio")
    private EntidadNegocio entidadNegocio;

    @Autowired
    @Qualifier("areaNegocio")
    private AreaNegocio areaNegocio;

    @Autowired
    @Qualifier("localesNegocio")
    private LocalesNegocio localesNegocio;

    @Autowired
    @Qualifier("empleadoNegocio")
    private EmpleadoNegocio empleadoNegocio;

    @Autowired
    @Qualifier("oficinaNegocio")
    private OficinaNegocio oficinaNegocio;

    @Autowired
    @Qualifier("ubigeoNegocio")
    private UbigeoNegocio ubigeoNegocio;


    @Transactional(rollbackFor = Throwable.class)
    public ResultadoMigracionSBNExcelBean migrarSBNExcelV7(InputStream inputStreamDBF, UsuarioSession usuarioSession, Integer entidadIDMigracion, boolean forzarUsarEntidadDeMigracion) throws Exception {

        Date now = DateTime.getNow().toDate();

        ResultadoMigracionSBNExcelBean result = new ResultadoMigracionSBNExcelBean();

        StringBuilder erroresGenerales = new StringBuilder(500);
        result.setOcurrioError(false);

        // creando una excel desde cero
        ExcelUtilXML excel = new ExcelUtilXML(new XSSFWorkbook(inputStreamDBF));

        // abrir primera hoja
        excel.openSheet(0);

        int pos = 5;
        if (!hayFila(excel, pos)) {
            throw new SimioException("MES-MSE-003", "No existe la fila " + pos + " en la pestaña " + excel.getSheet().getSheetName());
        }

        Object value;
        for (excel.setRowPosition(pos); hayFila(excel, pos) && hayData(excel); pos++) {

            try {
                BienEntity bien = new BienEntity();

                // setear posicion de la fila
                excel.setRowPosition(pos);
                XSSFRow row = excel.getRow();

                // columna
                int k = -1;

                // columna ITEM (Numérico)
                Integer fila = getSafeInteger(row, k++);

                // columna RUC_ENTIDAD (caracter 11)
                String rucEntidad = getSafeString(row, k++);

                // buscar el entidadID a partir del RUC
                Integer entityID = buscarEntityIDByRUC(rucEntidad);

                // si se fuerza, considera que el entityID que viene del DB es el valor forzado y sigue su flujo normal
                if (forzarUsarEntidadDeMigracion) {
                    entityID = entidadIDMigracion;
                }

                if (entityID == null) {
                    result.getBienes().getListaErrores().add(new ErrorLineaMigracionBean(String.valueOf(fila), "No se insertará bien. No se pudo encontrar ninguna entidadID que coincida con el número de RUC " + rucEntidad));
                    continue;
                }

                if (entityID.intValue() != entidadIDMigracion.intValue()) {
                    result.getBienes().getListaErrores().add(new ErrorLineaMigracionBean(String.valueOf(fila), "No se insertará bien. Código de entidades no coinciden. EntidadID DBF: " + entityID + ", EntidadID Migracion: " + entidadIDMigracion.intValue()));
                    continue;
                }

                // NOTA IMPORTANTE: entidadID (a partir de su RUC) es lo unico que no se tratara de insertar,
                //                  eso quiere decir que se debe revisar en la BD manualente para ver que exista, sino insertarlo
                bien.setEntidadID(entityID);

                // columna NOMBRE_LOCAL (caracter 255)
                Integer localesID = null;

                String nombreLocal = getSafeString(row, k++);

                // columna DEPARTAMENTO (caracter 50)
                String descripDepartamento = getSafeString(row, k++);
                String codigoDepartamento = buscarCodigoUbigeoByDescrip(descripDepartamento, 2);

                // columna PROVINCIA (caracter 50)
                String descripProvincia = getSafeString(row, k++);
                String codigoProvincia = buscarCodigoUbigeoByDescrip(descripProvincia, 4);

                // columna DISTRITO (caracter 50)
                String descripDistrito = getSafeString(row, k++);
                String codigoDistrito = buscarCodigoUbigeoByDescrip(descripDistrito, 6);

                localesID = buscarLocalesID(entityID, nombreLocal, codigoDepartamento + codigoProvincia + codigoDistrito);

                if (localesID == null) {
                    // TODO/FIXME: aqui se podria agregar un logica para que inserte el local que no pudo encontrar
                    result.getLocales().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila),
                            "No se le pudo encontrar el local -> " + nombreLocal));

                    // solo se agrega un mensaje, no bloquea el flujo
                    result.getBienes().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila),
                            "No se le pudo encontrar el local para el bien, datos del local -> " + nombreLocal));
                }

                bien.setLocalesID(localesID);

                // columna NOMBRE_AREA (caracter 150)
                Integer areaID = null;

                String nombreArea = getSafeString(row, k++);

                // columna ABREVIATURA_AREA (caracter 15)
                String abreviaturaArea = getSafeString(row, k++);

                areaID = buscarAreaID(entityID, nombreArea, localesID, abreviaturaArea);

                if (areaID == null) {

                    // TODO/FIXME: aqui se podria agregar un logica para que inserte el area que no pudo encontrar
                    result.getAreas().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila),
                            "No se le pudo encontrar el area -> " + nombreArea + " - " + abreviaturaArea));

                    // solo se agrega un mensaje, no bloquea el flujo2,
                    result.getBienes().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila),
                            "No se le pudo encontrar el area para el bien, datos del area -> " + nombreArea + " - " + abreviaturaArea));
                }

                bien.setAreaID(localesID);

                // columna NOMBRE_OFICINA (caracter 150)
                Integer oficinaID = null;

                String nombreOficina = getSafeString(row, k++);

                // columna ABREVIATURA_OFICINA (caracter 15)
                String abreviaturaOficina = getSafeString(row, k++);

                // columna PISO_OFICINA (caracter 15)
                String pisoOficina = getSafeString(row, k++);

                oficinaID = buscarOficinaID(entityID, nombreOficina, localesID, areaID, abreviaturaOficina, pisoOficina);
                if (oficinaID == null) {

                    // TODO/FIXME: aqui se podria agregar un logica para que inserte la oficina que no pudo encontrar
                    result.getOficinas().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila),
                            "No se le pudo encontrar la oficina -> " + nombreOficina + " - " + abreviaturaOficina));

                    // solo se agrega un mensaje, no bloquea el flujo
                    result.getBienes().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila),
                            "No se le pudo encontrar la oficina para el bien, datos de la oficina -> " + nombreOficina + ", " + abreviaturaOficina));
                }

                bien.setOficinaID(oficinaID);

                // TODO/FIXME: facil se deberá hacer una conversión/match
                // columna TIPO_DOC_IDENTIDAD (Numérico 1)
                value = getSafeInteger(row, k++);
//            if (empleado == null || !JS._numeroEntero(empleado.getTipoDocIdentidad())) {
//                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
//            } else {
//                // 1. DNI, 2: carnet extranjeria
//                excel.xlsCell(k++, JS.toInt(empleado.getTipoDocIdentidad()), styles.get(k));
//            }

                // columna NRO_DOC_IDENT_PERSONAL (Numérico 15)
                value = getSafeLong(row, k++);

//            if (empleado == null) {
//                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
//            } else {
//                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getNumeroDocIdentPersonal(), 15), styles.get(k));
//            }

                // columna APELLIDO_PATERNO (caracter 50)
                value = getSafeString(row, k++);

//            if (empleado == null) {
//                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
//            } else {
//                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getApellidoPaterno(), 50), styles.get(k));
//            }

                // columna APELLIDO_MATERNO (caracter 50)
                value = getSafeString(row, k++);

//            if (empleado == null) {
//                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
//            } else {
//                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getApellidoMaterno(), 50), styles.get(k));
//            }

                // columna NOMBRES (caracter 100)
                value = getSafeString(row, k++);

//            if (empleado == null) {
//                excel.xlsCell(k++, toStringNull(usarNulls, null, 100), styles.get(k));
//            } else {
//                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getNombres(), 100), styles.get(k));
//            }

                // TODO/FIXME: conversion
                // columna MODALIDAD_CONTRATO (caracter 1)
                // C: CAS
                // F: FUNCIONARIO
                // P: PRACTICANTE
                // S: SECIGRISTA
                // E: SERVIDOR PUBLICO
                // T: TERCERO
                String modalidadContrato = getSafeString(row, k++);
                if (StringUtils.isNotBlank(modalidadContrato) && !ArrayUtils.contains(FormatoExcelNegocio.MODALIDADES_CONTRATO, modalidadContrato)) {
                    result.getEmpleados().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila), "contiene un valor modalidadContrato no válido -> " + modalidadContrato));
                }

//            if (empleado == null) {
//                excel.xlsCell(k++, toStringNull(usarNulls, null, 1), styles.get(k));
//            } else {
//                String modalidadContrato = StringUtils.trimToEmpty(empleado.getModalidadContrato());
//                if (!ArrayUtils.contains(FormatoExcelNegocio.MODALIDADES_CONTRATO, modalidadContrato)) {
//                    throw new SimioException("Fila " + i + " del excel contiene un valor no válido para modalidad contrato -> " + modalidadContrato);
//                }
//                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getModalidadContrato(), 1), styles.get(k));
//            }

                // columna CODIGO_PATRIMONIAL (caracter 12)
                bien.setCodigoPatrimonial(getSafeString(row, k++));

                // columna DENOMINACION_BIEN (caracter 120)
                bien.setDenominacionBien(getSafeString(row, k++));

                // columna TIPO_CAUSAL_ALTA (caracter 1)
                // C: COMPRA
                // D: ACEPTACIÓN DE DONACIÓN
                // F: FABRICACION DE BIENES
                // P: PERMUTA DE BIENES
                // R: REPOSICIÓN DE BIENES
                // E: REPRODUCCIÓN DE SEMOVIENTES
                // S: SANEAMIENTOS DE BIENES SOBRANTES
                // L: DISPOSICIÓN LEGAL
                // J: MANDATO JUDICIAL O ARBITRAL
                // X: OPINION FAVORABLE DE ALTA OTORGADO POR LA OAJ DE LA ENTIDAD
                // O: OTRA CAUSAL
                String tipoCausaAlta = getSafeString(row, k++);
                if (StringUtils.isNotBlank(tipoCausaAlta) && !ArrayUtils.contains(FormatoExcelNegocio.TIPO_CAUSAL_ALTA, tipoCausaAlta)) {
                    result.getBienes().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila), "contiene un valor tipoCausaAlta no válido -> " + tipoCausaAlta));
                }
                bien.setTipoCausalAlta(tipoCausaAlta);


                // columna NRO_DOC_ADQUISICION (caracter 50)
                bien.setNumeroDocAdquisicion(getSafeString(row, k++));

                // columna FECHA_ADQUISICION (Fecha  10)
                bien.setFechaAdquisicion(getSafeDate(row, k++));

                // columna VALOR_ADQUISICION (Decimal   20  2)
                bien.setValorAdquisicion(getSafeDouble(row, k++, 2));

                // columna FECHA_DEPRECIACION (Fecha  10)
                bien.setFechaDepreciacion(getSafeDate(row, k++));

                // columna VALOR_DEPREC_EJERCICIO (Decimal   20  2)
                bien.setValorDeprecEjercicio(getSafeDouble(row, k++, 2));

                // columna VALOR_DEPREC_ACUMULADO (Decimal   20  2)
                bien.setValorDeprecAcumulado(getSafeDouble(row, k++, 2));

                // columna VALOR_NETO (Decimal   20  2)
                bien.setValorNeto(getSafeDouble(row, k++, 2));

                // columna TIP_USO_CUENTA  (caracter 1)
                // P: DE USO PRIVADO
                // E: DE USO ESTATAL
                String tipoUsoCuenta = getSafeString(row, k++);
                if (StringUtils.isNotBlank(tipoUsoCuenta) && !ArrayUtils.contains(FormatoExcelNegocio.TIP_USO_CUENTA, tipoUsoCuenta)) {
                    result.getBienes().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila), "contiene un valor tipoUsoCuenta no válido -> " + tipoUsoCuenta));
                }
                bien.setTipUsoCuenta(tipoUsoCuenta);

                // columna TIPO_CUENTA (caracter 1)
                // A: ACTIVO FIJO
                // O: CUENTA DE ORDEN
                bien.setTipoCuenta(getSafeString(row, k++));

                // columna NRO_CUENTA_CONTABLE (caracter 20) -> formato: cuentaMayor + PUNTO + Subcuenta
                bien.setNumeroCuentaContable(getSafeString(row, k++));
//
//            // columna CTA_CON_SEGURO (caracter 2) (SI: NO)
                String cuentaConSeguro = getSafeString(row, k++);
                bien.setCuentaConSeguro(StringUtils.equalsIgnoreCase(cuentaConSeguro, "SI") ? "S" : "N");

                // columna ESTADO_BIEN (caracter 1)
                // N: NUEVO, B: BUENO, R: REGULAR, M: MALO, X: MUYMALO
                bien.setEstadoBien(getSafeString(row, k++));

                // columna CONDICION (caracter 1)
                // A: ACTIVO, B: BAJA
                bien.setCondicion(getSafeString(row, k++));

                // columna MARCA (caracter 100)
                bien.setMarca(getSafeString(row, k++));

                // columna MODELO (caracter 100)
                bien.setModelo(getSafeString(row, k++));

                // columna TIPO (caracter 100)
                bien.setTipo(getSafeString(row, k++));

                // columna COLOR (caracter 100)
                bien.setColor(getSafeString(row, k++));

                // columna SERIE (caracter 100)
                bien.setSerie(getSafeString(row, k++));

                // columna DIMENSION (caracter 100)
                bien.setDimension(getSafeString(row, k++));

                // columna PLACA (caracter 100)
                bien.setPlaca(getSafeString(row, k++));

                // columna NRO_MOTOR (caracter 100)
                bien.setNumeroMotor(getSafeString(row, k++));

                // columna NRO_CHASIS (caracter 100)
                bien.setNumeroChasis(getSafeString(row, k++));

                // columna MATRICULA (caracter 100)
                bien.setMatricula(getSafeString(row, k++));

                // columna ANIO_FABRICACION (Numérico 4)
                Integer anio = getSafeInteger(row, k++);
                bien.setAnioFabricacion(anio == null ? null : anio.toString());

                // columna LONGITUD (caracter 20)
                bien.setLongitud(getSafeString(row, k++));

                // columna ALTURA (caracter 20)
                bien.setAltura(getSafeString(row, k++));

                // columna ANCHO  (caracter 20)
                String ancho = getSafeString(row, k++);
                bien.setAncho(JS._numero(ancho) ? JS.toDouble(ancho) : 0.0);

                // columna RAZA (caracter 100)
                bien.setRaza(getSafeString(row, k++));

                // columna ESPECIE (caracter 100)
                bien.setEspecie(getSafeString(row, k++));

                // columna EDAD (caracter 100)
                bien.setEdad(getSafeString(row, k++));

                // columna PAIS (caracter 100)
                bien.setPais(getSafeString(row, k++));

                // columna OTRAS_CARACT (caracter 100)
                bien.setOtrasCaract(getSafeString(row, k++));

                // columna DESCRIPCION_UBICACION_BIEN (caracter 200)
                bien.setDescripcionUbicacionBien(getSafeString(row, k++));

                // columna CAUSAL_BAJA  (caracter 1)
//            E: ESTADO DE EXCEDENCIA
//            O: OBSOLESCENCIA TÉCNICA
//            M: MANTENIMIENTO O REPARACIÓN ONEROSA
//            R: REEMBOLSO
//            C: REPOSICIÓN
//            P: PERDIDA
//            H: HURTO
//            B: ROBO
//            X: RESIDUOS DE APARATOS ELECTRICOS Y ELECTRONICOS - RAEE
//            Y: ESTADO DE CHATARRA
//            Z: SINIESTRO
//            D: DESTRUCCIÓN ACCIDENTAL
//            V: CASO DE SEMOVIENTE
//            F: SANEAMIENTO DE BIEN FALTANTE
//            S: OTRA CAUSAL
//            W: OPINION FAVORABLE DE BAJA OTORGADO POR LA SBN
                String causalBaja = getSafeString(row, k++);
                if (StringUtils.isNotBlank(causalBaja) && !ArrayUtils.contains(FormatoExcelNegocio.CAUSAL_BAJA, causalBaja)) {
                    result.getBienes().getListaMensajes().add(new MensajeLineaMigracionBean(String.valueOf(fila), "contiene un valor causalBaja no válido -> " + causalBaja));

                }
                bien.setCausalBaja(causalBaja);

                // columna NRO_RESOLUCION_BAJA (caracter 50)
                bien.setNumeroResolucionBaja(getSafeString(row, k++));

                // columna FECHA_BAJA (Fecha 10)
                bien.setFechaBaja(getSafeDate(row, k++));

                // columna ACTO_DISPOSICION_BIEN (caracter 1)
                // V: SUBASTRA RESTRINGIDA, S: SUBASTA PUBLICA, P: PERMUTA, D: DONACION, I: DESTRUCCION
                bien.setActoDisposicionBien(getSafeString(row, k++));

                // columna NRO_RESOLUCION_DISP (caracter 50)
                bien.setNumeroResolucionDisp(getSafeString(row, k++));

                // columna FECHA_DISPOSICION (Fecha 10)
                bien.setFechaDisposicion(getSafeDate(row, k++));

                // columna ENTIDAD_BENEFICIADA_ACTO_DISPOSICION  (caracter 200)
                bien.setEntidadBeneficiadaActoDisposicion(getSafeString(row, k++));

                // columna MOTIVO_ELIMINACION_BIEN (caracter 200)
                bien.setMotivoEliminacionBien(getSafeString(row, k++));

                // columna ACTO_ADMINISTRACION_BIEN (caracter 1)
                // F: AFECTACION EN USO, R: ARRENDAMIENTO, C: CESION EN USO
                bien.setActoAdministracionBien(getSafeString(row, k++));

                // columna NUM_RESOLUCION_ADMINISTRACION (caracter 50)
                bien.setNumeroResolucionAdministracion(getSafeString(row, k++));

                // TODO/FIXME: INFORMAR QUE EN EL PDF, HAY UN CAMPO MAS NO CONTABILIZADO, FECHA_INICIO_ACTO_ADMIN
                // columna FECHA_ADMINISTRACION (Fecha 10)
                bien.setFechaAdministracion(getSafeDate(row, k++));

                // columna FECHA_VENC_ACTO_ADMIN (Fecha 10)
                bien.setFechaVencActoAdmin(getSafeDate(row, k++));

                // columna ENTIDAD_BENEFICIADA_ACTO_ADMIN (NOMBRE_BENEFICIARIO_ACTO_ADMIN) (caracter 200)
                bien.setEntidadBeneficiadaActoAdmin(getSafeString(row, k++));

                // columna DOC_ALTA_SBN (caracter 50)
                bien.setDocAltaSbn(getSafeString(row, k++));

                // columna DOC_BAJA_SBN (caracter 50)
                bien.setDocBajaSbn(getSafeString(row, k++));

                if (bien.getEntidadID() == null) {
                    result.getBienes().getListaErrores().add(new ErrorLineaMigracionBean(String.valueOf(fila), "No tiene seteado el entidadID, no se insertará en la tabla de bien"));
                    continue;
                }

                if (StringUtils.isBlank(bien.getCodigoPatrimonial())) {
                    result.getBienes().getListaErrores().add(new ErrorLineaMigracionBean(String.valueOf(fila), "No tiene seteado codigo patrimonial, no se insertará en la tabla de bien"));
                    continue;
                }

                boolean existeBien = existeBienUnique(bien);

                if (existeBien) {

                    bien.setIndDel(Constantes.REGISTRO_ACTIVO);
                    bien.setUsuAct(usuarioSession.getUsuarioSessionID());
                    bien.setFechaAct(now);

                    // insertar en la BD MySQL
                    bienDAO.updateNullByCodigoPatrimonialEntidadID(bien);

                } else {

                    bien.setIndDel(Constantes.REGISTRO_ACTIVO);
                    bien.setUsuAct(usuarioSession.getUsuarioSessionID());
                    bien.setUsuReg(usuarioSession.getUsuarioSessionID());
                    bien.setFechaAct(now);
                    bien.setFechaReg(now);

                    // insertar en la BD MySQL
                    bienDAO.insert(bien);

                }

                // seteando campos de adicionales de auditoria

            } catch (Exception sos) {

                erroresGenerales.append(sos.getMessage()).append(com.jcfr.utiles.Constantes.ENTER_LINUX);

                result.setOcurrioError(true);
                result.setMsgError(erroresGenerales.toString()); // siempre pone lo ultimo que acumula el string builder
            }
        }

        return result;
    }

    private String buscarCodigoUbigeoByDescrip(String descripFiltro, int sizeDepProDis) throws Exception {

        if (StringUtils.isNotBlank(descripFiltro)) {

            UbigeoEntity filter = new UbigeoEntity();

            filter.setIndDel(Constantes.REGISTRO_ACTIVO);
            filter.setDescripcion(StringUtils.trimToEmpty(descripFiltro).toUpperCase());

            List<UbigeoEntity> results = ubigeoNegocio.select(filter);

            if (CollectionUtils.isNotEmpty(results)) {

                // filtrar a mano por el codigo de ubigeo que por ser departamento solo debe tener dos digitos
                for (UbigeoEntity result : results) {
                    if (result == null) continue;
                    // sizeDepProDis = 2 -> departamento
                    // sizeDepProDis = 4 -> provincia
                    // sizeDepProDis = 6 -> distrito
                    if (StringUtils.trimToEmpty(result.getCodigoUbigeo()).length() == sizeDepProDis) {
                        return StringUtils.trimToEmpty(result.getCodigoUbigeo());
                    }
                }

                return StringUtils.trimToEmpty(results.get(0).getCodigoUbigeo());
            }
        }

        return StringUtils.EMPTY;
    }

    private boolean existeBienUnique(BienEntity entity) throws Exception {

        if (entity == null) new IllegalAccessException("entity must be not null");

        // buscamos por sus campos unique
        BienEntity filter = new BienEntity();

        filter.setCodigoPatrimonial(entity.getCodigoPatrimonial());
        filter.setEntidadID(entity.getEntidadID());

        List<BienEntity> results = bienDAO.select(filter);

        return CollectionUtils.isNotEmpty(results);
    }

    private Integer buscarLocalesID(Integer entidadID, String nombreLocal, String codigoUbigeo) throws Exception {

        Map<String, Object> filter = new HashMap<>();

        // busca los que coinciden con nombre de local exactamente
        filter.clear();
        filter.put("entidadID", entidadID);
        filter.put("indDel", Constantes.REGISTRO_ACTIVO);
        filter.put("nombreLocal", StringUtils.trimToNull(nombreLocal));
        filter.put("nombreLocal_type", "igual");

        List<LocalesEntity> locales = localesNegocio.selectByMap(filter);

        if (CollectionUtils.isEmpty(locales)) {

            // si no hay coincidencias, ampliar la busqueda por like por un lado
            filter.clear();
            filter.put("entidadID", entidadID);
            filter.put("indDel", Constantes.REGISTRO_ACTIVO);
            filter.put("nombreLocal", StringUtils.trimToNull(nombreLocal) + "%");

            locales = localesNegocio.selectByMap(filter);

            if (CollectionUtils.isEmpty(locales)) {

                // si no hay coincidencias, ampliar la busqueda por like en ambos lados
                filter.clear();
                filter.put("entidadID", entidadID);
                filter.put("indDel", Constantes.REGISTRO_ACTIVO);
                filter.put("nombreLocal", "%" + StringUtils.trimToNull(nombreLocal) + "%");

                locales = localesNegocio.selectByMap(filter);

            }
        }

        boolean hayLocales = CollectionUtils.isNotEmpty(locales);

        // refinar la busqueda usando el codigoUbigeo
        if (hayLocales && StringUtils.length(codigoUbigeo) == 8) {
            String fltroBuscar = StringUtils.trimToEmpty(codigoUbigeo);
            for (LocalesEntity local : locales) {
                if (StringUtils.equalsIgnoreCase(fltroBuscar, StringUtils.trimToEmpty(local.getCodigoUbigeo()))) {
                    return local.getLocalesID();
                }
            }
        }

        // retorna el primer local
        return hayLocales ? locales.get(0).getLocalesID() : null;
    }


    private Integer buscarAreaID(Integer entidadID, String nombreArea, Integer localesID, String abreviaturaArea) throws Exception {

        Map<String, Object> filter = new HashMap<>();

        // busca los que coinciden con nombre de local exactamente
        filter.clear();
        filter.put("entidadID", entidadID);
        filter.put("localesID", localesID); // esto funciona como opcional, porque sino pudo encontrar el localesID antes aqui sera null y como si nada
        filter.put("indDel", Constantes.REGISTRO_ACTIVO);
        filter.put("nombreArea", StringUtils.trimToNull(nombreArea));
        filter.put("nombreArea_type", "igual");

        List<AreaEntity> areas = areaNegocio.selectByMap(filter);

        if (CollectionUtils.isEmpty(areas)) {

            // si no hay coincidencias, ampliar la busqueda por like por un lado
            filter.clear();
            filter.put("entidadID", entidadID);
            filter.put("localesID", localesID); // esto funciona como opcional, porque sino pudo encontrar el localesID antes aqui sera null y como si nada
            filter.put("indDel", Constantes.REGISTRO_ACTIVO);
            filter.put("nombreArea", StringUtils.trimToNull(nombreArea) + "%");

            areas = areaNegocio.selectByMap(filter);

            if (CollectionUtils.isEmpty(areas)) {

                // si no hay coincidencias, ampliar la busqueda por like en ambos lados
                filter.clear();
                filter.put("entidadID", entidadID);
                filter.put("localesID", localesID); // esto funciona como opcional, porque sino pudo encontrar el localesID antes aqui sera null y como si nada
                filter.put("indDel", Constantes.REGISTRO_ACTIVO);
                filter.put("nombreArea", "%" + StringUtils.trimToNull(nombreArea) + "%");

                areas = areaNegocio.selectByMap(filter);

            }
        }

        boolean hayAreas = CollectionUtils.isNotEmpty(areas);

        // refinar la busqueda usando su abreviatura
        if (hayAreas && StringUtils.isNotBlank(abreviaturaArea)) {
            String fltroBuscar = StringUtils.trimToEmpty(abreviaturaArea);
            for (AreaEntity area : areas) {
                if (StringUtils.equalsIgnoreCase(fltroBuscar, StringUtils.trimToEmpty(area.getAbreviaturaArea()))) {
                    return area.getAreaID();
                }
            }
        }

        // retorna el primer elemento
        return hayAreas ? areas.get(0).getAreaID() : null;
    }


    private Integer buscarOficinaID(Integer entidadID, String nombreOficina, Integer localesID, Integer areaID, String abreviaturaOficina, String pisoOficina) throws Exception {

        Map<String, Object> filter = new HashMap<>();

        // busca los que coinciden con nombre de local exactamente
        filter.clear();
        filter.put("entidadID", entidadID);
        filter.put("localesID", localesID); // esto funciona como opcional, porque sino pudo encontrar el localesID antes aqui sera null y como si nada
        filter.put("areaID", areaID); // esto funciona como opcional, porque sino pudo encontrar el areaID antes aqui sera null y como si nada
        filter.put("indDel", Constantes.REGISTRO_ACTIVO);
        filter.put("nombreOficina", StringUtils.trimToNull(nombreOficina));
        filter.put("nombreOficina_type", "igual");

        List<OficinaEntity> oficinas = oficinaNegocio.selectByMap(filter);

        if (CollectionUtils.isEmpty(oficinas)) {

            // si no hay coincidencias, ampliar la busqueda por like por un lado
            filter.clear();
            filter.put("entidadID", entidadID);
            filter.put("localesID", localesID); // esto funciona como opcional, porque sino pudo encontrar el localesID antes aqui sera null y como si nada
            filter.put("areaID", areaID); // esto funciona como opcional, porque sino pudo encontrar el areaID antes aqui sera null y como si nada
            filter.put("indDel", Constantes.REGISTRO_ACTIVO);
            filter.put("nombreOficina", StringUtils.trimToNull(nombreOficina) + "%");

            oficinas = oficinaNegocio.selectByMap(filter);

            if (CollectionUtils.isEmpty(oficinas)) {

                // si no hay coincidencias, ampliar la busqueda por like en ambos lados
                filter.clear();
                filter.put("entidadID", entidadID);
                filter.put("localesID", localesID); // esto funciona como opcional, porque sino pudo encontrar el localesID antes aqui sera null y como si nada
                filter.put("areaID", areaID); // esto funciona como opcional, porque sino pudo encontrar el areaID antes aqui sera null y como si nada
                filter.put("indDel", Constantes.REGISTRO_ACTIVO);
                filter.put("nombreOficina", "%" + StringUtils.trimToNull(nombreOficina) + "%");

                oficinas = oficinaNegocio.selectByMap(filter);

            }
        }

        boolean hayOficinas = CollectionUtils.isNotEmpty(oficinas);

        // refinar la busqueda usando su abreviatura
        if (hayOficinas && StringUtils.isNotBlank(abreviaturaOficina)) {
            String fltroBuscar = StringUtils.trimToEmpty(abreviaturaOficina);
            for (OficinaEntity oficina : oficinas) {
                if (StringUtils.equalsIgnoreCase(fltroBuscar, StringUtils.trimToEmpty(oficina.getAbreviaturaOficina()))) {
                    return oficina.getAreaID();
                }
            }
        }

        // refinar la busqueda usando piso de oficina
        if (hayOficinas && StringUtils.isNotBlank(pisoOficina)) {
            String fltroBuscar = StringUtils.trimToEmpty(pisoOficina);
            for (OficinaEntity oficina : oficinas) {
                if (StringUtils.equalsIgnoreCase(fltroBuscar, StringUtils.trimToEmpty(oficina.getPisoOficina()))) {
                    return oficina.getAreaID();
                }
            }
        }


        // retorna el primer elemento
        return hayOficinas ? oficinas.get(0).getAreaID() : null;
    }

//    private Map<Integer, EntidadEntity> buscarEntidades(Integer entidadID) throws Exception {
//        EntidadEntity filter = new EntidadEntity();
//        filter.setEntidadID(entidadID);
//        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
//        return entidadNegocio.selectKeyObject(filter);
//    }

//    private Map<Integer, EmpleadoEntity> buscarEmpleados(Integer entidadID) throws Exception {
//        EmpleadoEntity filter = new EmpleadoEntity();
//        filter.setEntidadID(entidadID);
//        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
//        return empleadoNegocio.selectKeyObject(filter);
//    }

//    private Map<String, UbigeoEntity> buscarUbigeo() throws Exception {
//        UbigeoEntity filter = new UbigeoEntity();
//        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
//        List<UbigeoEntity> entityList = ubigeoNegocio.select(filter);
//
//        HashMap<String, UbigeoEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());
//
//        if (CollectionUtils.isNotEmpty(entityList)) {
//            for (UbigeoEntity entity : entityList) {
//                keyObjectMap.put(JS.toBlank(entity.getCodigoUbigeo()), entity);
//            }
//        }
//
//        return keyObjectMap;
//    }


    private boolean hayFila(ExcelUtilXML excel, int pos) {

        boolean existe = true;
        try {
            // se toma la columna 0 porque ahi esta la secuencia de item
            excel.checkRowPosition(pos);
        } catch (Exception sos) {
            existe = false;
        }

        return existe;
    }

    private boolean hayData(ExcelUtilXML excel) {

        boolean existe = true;
        try {
            // se toma la columna 0 porque ahi esta la secuencia de item
            excel.checkRowPosition(0);
        } catch (Exception sos) {
            existe = false;
        }

        // se toma la columna 0 porque ahi esta la secuencia de item
        return existe && excel.getRow() != null && excel.getRow().getCell(0) != null && StringUtils.isNotBlank(excel.getRow().getCell(0).getRawValue());
    }


    private boolean isCeldaOK(XSSFRow row, int col) {
        if (row.getCell(col) == null) return false;
        int tipo = row.getCell(col).getCellType();
        return tipo != Cell.CELL_TYPE_BLANK && tipo != Cell.CELL_TYPE_ERROR && tipo != Cell.CELL_TYPE_FORMULA;
    }

    private String getSafeString(XSSFRow row, int col) {

        // recoge un string, lo trimea y si es en blanco lo retorna como null
        // si la celda no es del tipo numerico recoge su raw value y hace lo mismo
        if (isCeldaOK(row, col)) {

            if (row.getCell(col).getCellType() == Cell.CELL_TYPE_STRING) {
                return StringUtils.trimToNull(row.getCell(col).getStringCellValue());
            }

            try {
                return StringUtils.trimToNull(row.getCell(col).getRawValue());
            } catch (Exception ignored) {
                // no hay nada que hacer
            }
        }

        return null;
    }

    private Integer getSafeInteger(XSSFRow row, int col) {

        // recoge un string, lo trimea y si es en blanco lo retorna como null
        // si la celda no es del tipo numerico recoge su raw value y hace lo mismo
        if (isCeldaOK(row, col)) {

            if (row.getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return (int) row.getCell(col).getNumericCellValue();
            }

            try {
                return (int) Double.parseDouble(StringUtils.trimToNull(row.getCell(col).getRawValue()));
            } catch (Exception ignored) {
                // no hay nada que hacer
            }
        }

        return null;
    }

    private Long getSafeLong(XSSFRow row, int col) {

        // recoge un string, lo trimea y si es en blanco lo retorna como null
        // si la celda no es del tipo numerico recoge su raw value y hace lo mismo
        if (isCeldaOK(row, col)) {

            if (row.getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return (long) row.getCell(col).getNumericCellValue();
            }

            try {
                return (long) Double.parseDouble(StringUtils.trimToNull(row.getCell(col).getRawValue()));
            } catch (Exception ignored) {
                // no hay nada que hacer
            }
        }

        return null;
    }

    private Double getSafeDouble(XSSFRow row, int col, int nroDecimales) {

        // recoge un string, lo trimea y si es en blanco lo retorna como null
        // si la celda no es del tipo numerico recoge su raw value y hace lo mismo
        if (isCeldaOK(row, col)) {

            if (row.getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return JS.round(row.getCell(col).getNumericCellValue(), nroDecimales);
            }

            try {
                return JS.round(Double.parseDouble(StringUtils.trimToNull(row.getCell(col).getRawValue())), nroDecimales);
            } catch (Exception ignored) {
                // no hay nada que hacer
            }
        }

        return null;
    }

    private Date getSafeDate(XSSFRow row, int col) {

        // recoge un string, lo trimea y si es en blanco lo retorna como null
        // si la celda no es del tipo numerico recoge su raw value y hace lo mismo
        if (isCeldaOK(row, col)) {

            if (row.getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return row.getCell(col).getDateCellValue();
            }

            if (row.getCell(col).getCellType() == Cell.CELL_TYPE_STRING) {
                String fechaString = StringUtils.trimToEmpty(row.getCell(col).getStringCellValue());
                return DateTime.tieneFormatoFecha(fechaString) ? DateTime.getInstancia(fechaString).toDate() : null;
            }

            try {
                String fechaString = StringUtils.trimToEmpty(row.getCell(col).getRawValue());
                return DateTime.tieneFormatoFecha(fechaString) ? DateTime.getInstancia(fechaString).toDate() : null;
            } catch (Exception ignored) {
                // no hay nada que hacer
            }

        }

        return null;
    }

}
