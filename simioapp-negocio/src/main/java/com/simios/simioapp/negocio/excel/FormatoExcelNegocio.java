package com.simios.simioapp.negocio.excel;

import com.simios.simioapp.comunes.exceptions.SimioException;
import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.comunes.utiles.ExcelUtilXML;
import com.simios.simioapp.comunes.utiles.FileUtil;
import com.simios.simioapp.dominio.entidades.*;
import com.simios.simioapp.negocio.*;
import com.simios.simioapp.negocio.base.BaseNegocio;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.simios.simioapp.comunes.utiles.Constantes.S;

@Component("formatoExcelNegocio")
public class FormatoExcelNegocio extends BaseNegocio {

    public static final int DEFAULT_INITIAL_FILE_SIZE = 1024;
    public static final String TEMPLATE_XLSM_FILE_V6 = "Inventario2016_v6.xlsm";
    public static final String TEMPLATE_XLSM_FILE_V7 = "Inventario2017_v7.xlsm";

    public static final String[] MODALIDADES_CONTRATO = new String[]{"C", "F", "P", "S", "E", "T"};

    public static final String[] TIPO_CAUSAL_ALTA = new String[]{"C", "D", "F", "P", "R", "E", "S", "L", "J", "X", "O"};

    public static final String[] TIP_USO_CUENTA = new String[]{"P", "E"};

    public static final String[] TIPO_CUENTA = new String[]{"A", "O"};

    public static final String[] ESTADO_BIEN = new String[]{"N", "B", "R", "M", "X"};

    public static final String[] CONDICION = new String[]{"A", "B"};

    public static final String[] CAUSAL_BAJA = new String[]{"E", "O", "M", "R", "C", "P", "H", "B", "X", "Y", "Z", "D", "V", "F", "S", "W"};

    public static final String[] ACTO_ADMINISTRACION_BIEN = new String[]{"F", "R", "C"};

    public static final String[] ACTO_DISPOSICION_BIEN = new String[]{"V", "S", "P", "D", "I"};


    @Autowired
    @Qualifier("parametroNegocio")
    private ParametroNegocio parametroNegocio;

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


    public byte[] generarFormatoExcelV7(List<? extends BienEntity> bienes, Integer entidadID) throws Exception {

        if (CollectionUtils.isEmpty(bienes)) {
            throw new Exception("No hay bienes para generar reporte");
        }

        // creando una excel desde cero
        ExcelUtilXML excel = new ExcelUtilXML(new XSSFWorkbook(FileUtil.getResource(TEMPLATE_XLSM_FILE_V7)));

        // abrir primera hoja
        excel.openSheet(0);

        // fila de formatos
        int posicionDeInicio = 5;
        excel.checkRowPosition(posicionDeInicio);
        XSSFRow firstRow = excel.getSheet().getRow(posicionDeInicio);

        // guardar los estilos de las 8 primeras columnas para usarlas luego en las que se van a insertar
        Map<Integer, XSSFCellStyle> styles = new HashMap<>();
        for (int k = 0; k < 68; k++) {
            // se le suma 1 para no tener que restar 1 en cada excel.xlsCell

            styles.put(k + 1, excel.cloneStyleFrom(firstRow.getCell(k)));
        }

        // crear las demas filas (usando los estilos guardados)
        excel.setRowPosition(posicionDeInicio - 1);

        Map<String, UbigeoEntity> ubigeos = buscarUbigeo();
        Map<Integer, EmpleadoEntity> empleados = buscarEmpleados(entidadID);
        Map<Integer, EntidadEntity> entidades = buscarEntidades(entidadID);
        Map<Integer, LocalesEntity> locales = buscarLocales(entidadID);
        Map<Integer, AreaEntity> areas = buscarAreas(entidadID);
        Map<Integer, OficinaEntity> oficinas = buscarOficinas(entidadID);

        boolean usarNulls = usarNulls();

        for (int i = 0; i < bienes.size(); i++) {

            BienEntity bien = bienes.get(i);

            EmpleadoEntity empleado = empleados.get(bien.getEmpleadoID());
            EntidadEntity entidad = entidades.get(bien.getEntidadID());
            LocalesEntity local = locales.get(bien.getLocalesID());
            AreaEntity area = areas.get(bien.getAreaID());
            OficinaEntity oficina = oficinas.get(bien.getOficinaID());
            UbigeoEntity ubigeoDis = null;
            UbigeoEntity ubigeoPro = null;
            UbigeoEntity ubigeoDep = null;

            int k = 0;
            excel.xlsRow();

            // columna ITEM (Numérico)
            excel.xlsCell(k++, i + 1, styles.get(k));

            // columna RUC_ENTIDAD (caracter 11)
            if (entidad == null || !JS._numeroEntero(entidad.getRucEntidad())) {
                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
            } else {
                excel.xlsCell(k++, JS.toInt(entidad.getRucEntidad()), styles.get(k));
            }

            // columna NOMBRE_LOCAL (caracter 255)
            if (local == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 255), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, local.getNombreLocal(), 255), styles.get(k));

                if (StringUtils.isNotBlank(local.getCodigoUbigeo())) {

                    ubigeoDis = ubigeos.get(local.getCodigoUbigeo());
                    ubigeoPro = ubigeos.get(StringUtils.substring(local.getCodigoUbigeo(), 0, 4));
                    ubigeoDep = ubigeos.get(StringUtils.substring(local.getCodigoUbigeo(), 0, 2));
                }
            }

            // columna DEPARTAMENTO (caracter 50)
            if (ubigeoDep == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, ubigeoDep.getDescripcion(), 50), styles.get(k));
            }

            // columna PROVINCIA (caracter 50)
            if (ubigeoPro == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, ubigeoPro.getDescripcion(), 50), styles.get(k));
            }

            // columna DISTRITO (caracter 50)
            if (ubigeoDis == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, ubigeoDis.getDescripcion(), 50), styles.get(k));
            }

            // columna NOMBRE_AREA (caracter 150)
            if (area == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 150), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, area.getNombreArea(), 150), styles.get(k));
            }

            // columna ABREVIATURA_AREA (caracter 15)
            if (area == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, area.getAbreviaturaArea(), 15), styles.get(k));
            }

            // columna NOMBRE_OFICINA (caracter 150)
            if (oficina == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 150), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, oficina.getNombreOficina(), 150), styles.get(k));
            }

            // columna ABREVIATURA_OFICINA (caracter 15)
            if (oficina == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, oficina.getAbreviaturaOficina(), 15), styles.get(k));
            }

            // columna PISO_OFICINA (caracter 15)
            if (oficina == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, oficina.getPisoOficina(), 15), styles.get(k));
            }


            // TODO/FIXME: facil se deberá hacer una conversión/match
            // columna TIPO_DOC_IDENTIDAD (Numérico 1)
            if (empleado == null || !JS._numeroEntero(empleado.getTipoDocIdentidad())) {
                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
            } else {
                // 1. DNI, 2: carnet extranjeria
                excel.xlsCell(k++, JS.toInt(empleado.getTipoDocIdentidad()), styles.get(k));
            }

            // columna NRO_DOC_IDENT_PERSONAL (Numérico 15)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getNumeroDocIdentPersonal(), 15), styles.get(k));
            }

            // columna APELLIDO_PATERNO (caracter 50)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getApellidoPaterno(), 50), styles.get(k));
            }

            // columna APELLIDO_MATERNO (caracter 50)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getApellidoMaterno(), 50), styles.get(k));
            }

            // columna NOMBRES (caracter 100)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 100), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getNombres(), 100), styles.get(k));
            }

            // TODO/FIXME: conversion
            // columna MODALIDAD_CONTRATO (caracter 1)
            // C: CAS
            // F: FUNCIONARIO
            // P: PRACTICANTE
            // S: SECIGRISTA
            // E: SERVIDOR PUBLICO
            // T: TERCERO
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 1), styles.get(k));
            } else {
                String modalidadContrato = StringUtils.trimToEmpty(empleado.getModalidadContrato());
                if (!ArrayUtils.contains(FormatoExcelNegocio.MODALIDADES_CONTRATO, modalidadContrato)) {
                    throw new SimioException("Fila " + i + " del excel contiene un valor no válido para modalidad contrato -> " + modalidadContrato);
                }
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getModalidadContrato(), 1), styles.get(k));
            }

            // columna CODIGO_PATRIMONIAL (caracter 12)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getCodigoPatrimonial(), 12), styles.get(k));

            // columna DENOMINACION_BIEN (caracter 120)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDenominacionBien(), 12), styles.get(k));

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
            String tipoCausaAlta = StringUtils.trimToEmpty(bien.getTipoCausalAlta());
            if (!ArrayUtils.contains(FormatoExcelNegocio.TIPO_CAUSAL_ALTA, tipoCausaAlta)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para tipo causal alta -> " + tipoCausaAlta);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipoCausalAlta(), 1), styles.get(k));

            // columna NRO_DOC_ADQUISICION (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroDocAdquisicion(), 50), styles.get(k));

            // columna FECHA_ADQUISICION (Fecha  10)
            excel.xlsCell(k++, bien.getFechaAdquisicion(), styles.get(k));

            // columna VALOR_ADQUISICION (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorAdquisicion()), styles.get(k));

            // columna FECHA_DEPRECIACION (Fecha  10)
            excel.xlsCell(k++, bien.getFechaDepreciacion(), styles.get(k));

            // columna VALOR_DEPREC_EJERCICIO (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorDeprecEjercicio()), styles.get(k));

            // columna VALOR_DEPREC_ACUMULADO (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorDeprecAcumulado()), styles.get(k));

            // columna VALOR_NETO (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorNeto()), styles.get(k));

            // columna TIP_USO_CUENTA  (caracter 1)
            // P: DE USO PRIVADO
            // E: DE USO ESTATAL
            String tipoUsoCuenta = StringUtils.trimToEmpty(bien.getTipUsoCuenta());
            if (!ArrayUtils.contains(FormatoExcelNegocio.TIP_USO_CUENTA, tipoUsoCuenta)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para tipo uso cuenta -> " + tipoUsoCuenta);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipUsoCuenta(), 1), styles.get(k));

            // columna TIPO_CUENTA (caracter 1)
            // A: ACTIVO FIJO
            // O: CUENTA DE ORDEN
            String tipoCuenta = StringUtils.trimToEmpty(bien.getTipoCuenta());
            if (!ArrayUtils.contains(FormatoExcelNegocio.TIPO_CUENTA, tipoCuenta)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para tipo cuenta -> " + tipoCuenta);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipoCuenta(), 1), styles.get(k));

            // columna NRO_CUENTA_CONTABLE (caracter 20) -> formato: cuentaMayor + PUNTO + Subcuenta
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroCuentaContable(), 20), styles.get(k));

            // columna CTA_CON_SEGURO (SI: NO)
            if (JS.toBlank(bien.getCuentaConSeguro()).equalsIgnoreCase(S)) {
                excel.xlsCell(k++, Constantes.SI.toUpperCase(), styles.get(k));
            } else {
                excel.xlsCell(k++, Constantes.NO.toUpperCase(), styles.get(k));
            }

            // columna ESTADO_BIEN (caracter 1)
            // N: NUEVO, B: BUENO, R: REGULAR, M: MALO, X: MUYMALO
            String estadoBien = StringUtils.trimToEmpty(bien.getEstadoBien());
            if (!ArrayUtils.contains(FormatoExcelNegocio.ESTADO_BIEN, estadoBien)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para estado bien -> " + estadoBien);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEstadoBien(), 1), styles.get(k));

            // columna CONDICION (caracter 1)
            // A: ACTIVO, B: BAJA
            String condicion = StringUtils.trimToEmpty(bien.getCondicion());
            if (!ArrayUtils.contains(FormatoExcelNegocio.CONDICION, condicion)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para condicion -> " + condicion);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getCondicion(), 1), styles.get(k));

            // columna MARCA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getMarca(), 100), styles.get(k));

            // columna MODELO (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getModelo(), 100), styles.get(k));

            // columna TIPO (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipo(), 100), styles.get(k));

            // columna COLOR (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getColor(), 100), styles.get(k));

            // columna SERIE (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getSerie(), 100), styles.get(k));

            // columna DIMENSION (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDimension(), 100), styles.get(k));

            // columna PLACA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getPlaca(), 100), styles.get(k));

            // columna NRO_MOTOR (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroMotor(), 100), styles.get(k));

            // columna NRO_CHASIS (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroChasis(), 100), styles.get(k));

            // columna MATRICULA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getMatricula(), 100), styles.get(k));

            // columna ANIO_FABRICACION (Numérico 4)
            if (JS._numeroEntero(bien.getAnioFabricacion())) {
                excel.xlsCell(k++, toIntegerNull(usarNulls, JS.toInt(bien.getAnioFabricacion())), styles.get(k));
            } else {
                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
            }

            // columna LONGITUD (caracter 20)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getLongitud(), 20), styles.get(k));

            // columna ALTURA (caracter 20)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getAltura(), 20), styles.get(k));

            // columna ANCHO  (caracter 20)
            excel.xlsCell(k++, bien.getAncho() == null ? (usarNulls ? null : "0.0") : JS.roundString(bien.getAncho(), 2), styles.get(k));

            // columna RAZA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getRaza(), 100), styles.get(k));

            // columna ESPECIE (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEspecie(), 100), styles.get(k));

            // columna EDAD (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEdad(), 100), styles.get(k));

            // columna PAIS (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getPais(), 100), styles.get(k));

            // columna OTRAS_CARACT (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getOtrasCaract(), 100), styles.get(k));

            // columna DESCRIPCION_UBICACION_BIEN (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDescripcionUbicacionBien(), 200), styles.get(k));

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
            String causalBaja = StringUtils.trimToEmpty(bien.getCausalBaja());
            if (!ArrayUtils.contains(FormatoExcelNegocio.CAUSAL_BAJA, causalBaja)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para causal baja -> " + causalBaja);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getCausalBaja(), 1), styles.get(k));

            // columna NRO_RESOLUCION_BAJA (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroResolucionBaja(), 50), styles.get(k));

            // columna FECHA_BAJA (Fecha 10)
            excel.xlsCell(k++, bien.getFechaBaja(), styles.get(k));

            // columna ACTO_DISPOSICION_BIEN (caracter 1)
            // V: SUBASTRA RESTRINGIDA, S: SUBASTA PUBLICA, P: PERMUTA, D: DONACION, I: DESTRUCCION
            String actoDisposicionBien = StringUtils.trimToEmpty(bien.getActoDisposicionBien());
            if (!ArrayUtils.contains(FormatoExcelNegocio.ACTO_DISPOSICION_BIEN, actoDisposicionBien)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para acto disposición bien -> " + actoDisposicionBien);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getActoDisposicionBien(), 1), styles.get(k));

            // columna NRO_RESOLUCION_DISP (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroResolucionDisp(), 50), styles.get(k));

            // columna FECHA_DISPOSICION (Fecha 10)
            excel.xlsCell(k++, bien.getFechaDisposicion(), styles.get(k));

            // columna ENTIDAD_BENEFICIADA_ACTO_DISPOSICION  (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEntidadBeneficiadaActoDisposicion(), 200), styles.get(k));

            // columna MOTIVO_ELIMINACION_BIEN (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getMotivoEliminacionBien(), 200), styles.get(k));

            // columna ACTO_ADMINISTRACION_BIEN (caracter 1)
            // F: AFECTACION EN USO, R: ARRENDAMIENTO, C: CESION EN USO
            String actoAdministracionBien = StringUtils.trimToEmpty(bien.getActoAdministracionBien());
            if (!ArrayUtils.contains(FormatoExcelNegocio.ACTO_ADMINISTRACION_BIEN, actoAdministracionBien)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para acto administracion bien -> " + actoAdministracionBien);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getActoAdministracionBien(), 1), styles.get(k));

            // columna NUM_RESOLUCION_ADMINISTRACION (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroResolucionAdministracion(), 50), styles.get(k));

            // TODO/FIXME: INFORMAR QUE EN EL PDF, HAY UN CAMPO MAS NO CONTABILIZADO, FECHA_INICIO_ACTO_ADMIN
            // columna FECHA_ADMINISTRACION (Fecha 10)
            excel.xlsCell(k++, bien.getFechaAdministracion(), styles.get(k));

            // columna FECHA_VENC_ACTO_ADMIN (Fecha 10)
            excel.xlsCell(k++, bien.getFechaVencActoAdmin(), styles.get(k));

            // columna ENTIDAD_BENEFICIADA_ACTO_ADMIN (NOMBRE_BENEFICIARIO_ACTO_ADMIN) (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEntidadBeneficiadaActoAdmin(), 200), styles.get(k));

            // columna DOC_ALTA_SBN (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDocAltaSbn(), 50), styles.get(k));

            // columna DOC_BAJA_SBN (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDocBajaSbn(), 50), styles.get(k));

        }

        return excel.exportToByteArray(DEFAULT_INITIAL_FILE_SIZE);
    }

    public byte[] generarFormatoExcelV6(List<? extends BienEntity> bienes, Integer entidadID) throws Exception {

        if (CollectionUtils.isEmpty(bienes)) {
            throw new Exception("No hay bienes para generar reporte");
        }

        // creando una excel desde cero
        ExcelUtilXML excel = new ExcelUtilXML(new XSSFWorkbook(FileUtil.getResource(TEMPLATE_XLSM_FILE_V6)));

        // abrir primera hoja
        excel.openSheet(4);

        // fila de formatos
        int posicionDeInicio = 5;
        excel.checkRowPosition(posicionDeInicio);
        XSSFRow firstRow = excel.getSheet().getRow(posicionDeInicio);

        // guardar los estilos de las 8 primeras columnas para usarlas luego en las que se van a insertar
        Map<Integer, XSSFCellStyle> styles = new HashMap<>();
        for (int k = 0; k < 68; k++) {
            // se le suma 1 para no tener que restar 1 en cada excel.xlsCell

            styles.put(k + 1, excel.cloneStyleFrom(firstRow.getCell(k)));
        }

        // crear las demas filas (usando los estilos guardados)
        excel.setRowPosition(posicionDeInicio - 1);

        Map<String, UbigeoEntity> ubigeos = buscarUbigeo();
        Map<Integer, EmpleadoEntity> empleados = buscarEmpleados(entidadID);
        Map<Integer, EntidadEntity> entidades = buscarEntidades(entidadID);
        Map<Integer, LocalesEntity> locales = buscarLocales(entidadID);
        Map<Integer, AreaEntity> areas = buscarAreas(entidadID);
        Map<Integer, OficinaEntity> oficinas = buscarOficinas(entidadID);

        boolean usarNulls = usarNulls();

        for (int i = 0; i < bienes.size(); i++) {

            BienEntity bien = bienes.get(i);

            EmpleadoEntity empleado = empleados.get(bien.getEmpleadoID());
            EntidadEntity entidad = entidades.get(bien.getEntidadID());
            LocalesEntity local = locales.get(bien.getLocalesID());
            AreaEntity area = areas.get(bien.getAreaID());
            OficinaEntity oficina = oficinas.get(bien.getOficinaID());
            UbigeoEntity ubigeoDis = null;
            UbigeoEntity ubigeoPro = null;
            UbigeoEntity ubigeoDep = null;

            int k = 0;
            excel.xlsRow();

            // columna ITEM (Numérico)
            excel.xlsCell(k++, i + 1, styles.get(k));

            // columna RUC_ENTIDAD (caracter 11)
            if (entidad == null || !JS._numeroEntero(entidad.getRucEntidad())) {
                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
            } else {
                excel.xlsCell(k++, JS.toInt(entidad.getRucEntidad()), styles.get(k));
            }

            // columna NOMBRE_LOCAL (caracter 255)
            if (local == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 255), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, local.getNombreLocal(), 255), styles.get(k));

                if (StringUtils.isNotBlank(local.getCodigoUbigeo())) {

                    ubigeoDis = ubigeos.get(local.getCodigoUbigeo());
                    ubigeoPro = ubigeos.get(StringUtils.substring(local.getCodigoUbigeo(), 0, 4));
                    ubigeoDep = ubigeos.get(StringUtils.substring(local.getCodigoUbigeo(), 0, 2));
                }
            }

            // columna DEPARTAMENTO (caracter 50)
            if (ubigeoDep == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, ubigeoDep.getDescripcion(), 50), styles.get(k));
            }

            // columna PROVINCIA (caracter 50)
            if (ubigeoPro == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, ubigeoPro.getDescripcion(), 50), styles.get(k));
            }

            // columna DISTRITO (caracter 50)
            if (ubigeoDis == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, ubigeoDis.getDescripcion(), 50), styles.get(k));
            }

            // columna NOMBRE_AREA (caracter 150)
            if (area == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 150), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, area.getNombreArea(), 150), styles.get(k));
            }

            // columna ABREVIATURA_AREA (caracter 15)
            if (area == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, area.getAbreviaturaArea(), 15), styles.get(k));
            }

            // columna NOMBRE_OFICINA (caracter 150)
            if (oficina == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 150), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, oficina.getNombreOficina(), 150), styles.get(k));
            }

            // columna ABREVIATURA_OFICINA (caracter 15)
            if (oficina == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, oficina.getAbreviaturaOficina(), 15), styles.get(k));
            }

            // columna PISO_OFICINA (caracter 15)
            if (oficina == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, oficina.getPisoOficina(), 15), styles.get(k));
            }


            // TODO/FIXME: facil se deberá hacer una conversión/match
            // columna TIPO_DOC_IDENTIDAD (Numérico 1)
            if (empleado == null || !JS._numeroEntero(empleado.getTipoDocIdentidad())) {
                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
            } else {
                // 1. DNI, 2: carnet extranjeria
                excel.xlsCell(k++, JS.toInt(empleado.getTipoDocIdentidad()), styles.get(k));
            }

            // columna NRO_DOC_IDENT_PERSONAL (Numérico 15)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 15), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getNumeroDocIdentPersonal(), 15), styles.get(k));
            }

            // columna APELLIDO_PATERNO (caracter 50)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getApellidoPaterno(), 50), styles.get(k));
            }

            // columna APELLIDO_MATERNO (caracter 50)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 50), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getApellidoMaterno(), 50), styles.get(k));
            }

            // columna NOMBRES (caracter 100)
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 100), styles.get(k));
            } else {
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getNombres(), 100), styles.get(k));
            }

            // TODO/FIXME: conversion
            // columna MODALIDAD_CONTRATO
            if (empleado == null) {
                excel.xlsCell(k++, toStringNull(usarNulls, null, 1), styles.get(k));
            } else {
                // C: CAS
                // F: FUNCIONARIO
                // P: PRACTICANTE
                // S: SECIGRISTA
                // E: SERVIDOR PUBLICO
                // T: TERCERO
                String modalidadContrato = StringUtils.trimToEmpty(empleado.getModalidadContrato());
                if (!ArrayUtils.contains(FormatoExcelNegocio.MODALIDADES_CONTRATO, modalidadContrato)) {
                    throw new SimioException("Fila " + i + " del excel contiene un valor no válido para modalidad contrato -> " + modalidadContrato);
                }
                excel.xlsCell(k++, toStringNull(usarNulls, empleado.getModalidadContrato(), 1), styles.get(k));
            }

            // columna CODIGO_PATRIMONIAL (caracter 12)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getCodigoPatrimonial(), 12), styles.get(k));

            // columna DENOMINACION_BIEN (caracter 120)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDenominacionBien(), 12), styles.get(k));

            // columna TIPO_CAUSAL_ALTA
//            C: COMPRA
//            D: ACEPTACIÓN DE DONACIÓN
//            F: FABRICACION DE BIENES
//            P: PERMUTA DE BIENES
//            R: REPOSICIÓN DE BIENES
//            E: REPRODUCCIÓN DE SEMOVIENTES
//            S: SANEAMIENTOS DE BIENES SOBRANTES
//            L: DISPOSICIÓN LEGAL
//            J: MANDATO JUDICIAL O ARBITRAL
//            X: OPINION FAVORABLE DE ALTA OTORGADO POR LA OAJ DE LA ENTIDAD
//            O: OTRA CAUSAL
            String tipoCausaAlta = StringUtils.trimToEmpty(bien.getTipoCausalAlta());
            if (!ArrayUtils.contains(FormatoExcelNegocio.TIPO_CAUSAL_ALTA, tipoCausaAlta)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para tipo causal alta -> " + tipoCausaAlta);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipoCausalAlta(), 1), styles.get(k));

            // columna NRO_DOC_ADQUISICION (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroDocAdquisicion(), 50), styles.get(k));

            // columna FECHA_ADQUISICION (Fecha  10)
            excel.xlsCell(k++, bien.getFechaAdquisicion(), styles.get(k));

            // columna VALOR_ADQUISICION (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorAdquisicion()), styles.get(k));

            // columna FECHA_DEPRECIACION (Fecha  10)
            excel.xlsCell(k++, bien.getFechaDepreciacion(), styles.get(k));

            // columna VALOR_DEPREC_EJERCICIO (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorDeprecEjercicio()), styles.get(k));

            // columna VALOR_DEPREC_ACUMULADO (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorDeprecAcumulado()), styles.get(k));

            // columna VALOR_NETO (Decimal   20  2)
            excel.xlsCell(k++, toDoubleNull(usarNulls, bien.getValorNeto()), styles.get(k));

            // TODO/FIXME: REVISAR QUE SOLO SEA DE 1
            // columna TIP_USO_CUENTA  (caracter 1)
            // P: DE USO PRIVADO
            // E: DE USO ESTATAL
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipUsoCuenta(), 1), styles.get(k));

            // TODO/FIXME: REVISAR QUE SOLO SEA DE 1
            // columna TIPO_CUENTA (caracter 1)
            // A: ACTIVO FIJO
            // O: CUENTA DE ORDEN
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipoCuenta(), 1), styles.get(k));

            // columna NRO_CUENTA_CONTABLE (caracter 20) -> formato: cuentaMayor + PUNTO + Subcuenta
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroCuentaContable(), 20), styles.get(k));

            // columna CTA_CON_SEGURO (SI: NO)
            if (JS.toBlank(bien.getCuentaConSeguro()).equalsIgnoreCase(S)) {
                excel.xlsCell(k++, Constantes.SI.toUpperCase(), styles.get(k));
            } else {
                excel.xlsCell(k++, Constantes.NO.toUpperCase(), styles.get(k));
            }

            // TODO/FIXME: REVISAR QUE SOLO SEA DE 1
            // columna ESTADO_BIEN (caracter 1)
            // N: NUEVO, B: BUENO, R: REGULAR, M: MALO, X: MUYMALO
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEstadoBien(), 1), styles.get(k));

            // TODO/FIXME: REVISAR QUE SOLO SEA DE 1
            // columna CONDICION (caracter 1)
            // A: ACTIVO, B: BAJA
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getCondicion(), 1), styles.get(k));

            // columna MARCA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getMarca(), 100), styles.get(k));

            // columna MODELO (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getModelo(), 100), styles.get(k));

            // columna TIPO (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getTipo(), 100), styles.get(k));

            // columna COLOR (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getColor(), 100), styles.get(k));

            // columna SERIE (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getSerie(), 100), styles.get(k));

            // columna DIMENSION (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDimension(), 100), styles.get(k));

            // columna PLACA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getPlaca(), 100), styles.get(k));

            // columna NRO_MOTOR (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroMotor(), 100), styles.get(k));

            // columna NRO_CHASIS (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroChasis(), 100), styles.get(k));

            // columna MATRICULA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getMatricula(), 100), styles.get(k));

            // columna ANIO_FABRICACION (Numérico 4)
            if (JS._numeroEntero(bien.getAnioFabricacion())) {
                excel.xlsCell(k++, toIntegerNull(usarNulls, JS.toInt(bien.getAnioFabricacion())), styles.get(k));
            } else {
                excel.xlsCell(k++, toIntegerNull(usarNulls, null), styles.get(k));
            }

            // columna LONGITUD (caracter 20)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getLongitud(), 20), styles.get(k));

            // columna ALTURA (caracter 20)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getAltura(), 20), styles.get(k));

            // columna ANCHO  (caracter 20)
            excel.xlsCell(k++, bien.getAncho() == null ? (usarNulls ? null : "0.0") : JS.roundString(bien.getAncho(), 2), styles.get(k));

            // columna RAZA (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getRaza(), 100), styles.get(k));

            // columna ESPECIE (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEspecie(), 100), styles.get(k));

            // columna EDAD (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEdad(), 100), styles.get(k));

            // columna PAIS (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getPais(), 100), styles.get(k));

            // columna OTRAS_CARACT (caracter 100)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getOtrasCaract(), 100), styles.get(k));

            // columna DESCRIPCION_UBICACION_BIEN (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDescripcionUbicacionBien(), 200), styles.get(k));

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
            String causalBaja = StringUtils.trimToEmpty(bien.getCausalBaja());
            if (!ArrayUtils.contains(FormatoExcelNegocio.CAUSAL_BAJA, causalBaja)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para causal baja -> " + causalBaja);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getCausalBaja(), 1), styles.get(k));

            // columna NRO_RESOLUCION_BAJA (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroResolucionBaja(), 50), styles.get(k));

            // columna FECHA_BAJA (Fecha 10)
            excel.xlsCell(k++, bien.getFechaBaja(), styles.get(k));

            // columna ACTO_DISPOSICION_BIEN (caracter 1)
            // V: SUBASTRA RESTRINGIDA, S: SUBASTA PUBLICA, P: PERMUTA, D: DONACION, I: DESTRUCCION
            String actoDisposicionBien = StringUtils.trimToEmpty(bien.getActoDisposicionBien());
            if (!ArrayUtils.contains(FormatoExcelNegocio.ACTO_DISPOSICION_BIEN, actoDisposicionBien)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para acto disposición bien -> " + actoDisposicionBien);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getActoDisposicionBien(), 1), styles.get(k));

            // columna NRO_RESOLUCION_DISP (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroResolucionDisp(), 50), styles.get(k));

            // columna FECHA_DISPOSICION (Fecha 10)
            excel.xlsCell(k++, bien.getFechaDisposicion(), styles.get(k));

            // columna ENTIDAD_BENEFICIADA_ACTO_DISPOSICION  (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEntidadBeneficiadaActoDisposicion(), 200), styles.get(k));

            // columna MOTIVO_ELIMINACION_BIEN (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getMotivoEliminacionBien(), 200), styles.get(k));

            // columna ACTO_ADMINISTRACION_BIEN (caracter 1)
            // F: AFECTACION EN USO, R: ARRENDAMIENTO, C: CESION EN USO
            String actoAdministracionBien = StringUtils.trimToEmpty(bien.getActoAdministracionBien());
            if (!ArrayUtils.contains(FormatoExcelNegocio.ACTO_ADMINISTRACION_BIEN, actoAdministracionBien)) {
                throw new SimioException("Fila " + i + " del excel contiene un valor no válido para acto administracion bien -> " + actoAdministracionBien);
            }
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getActoAdministracionBien(), 1), styles.get(k));

            // columna NUM_RESOLUCION_ADMINISTRACION (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getNumeroResolucionAdministracion(), 50), styles.get(k));

            // TODO/FIXME: INFORMAR QUE EN EL PDF, HAY UN CAMPO MAS NO CONTABILIZADO, FECHA_INICIO_ACTO_ADMIN
            // columna FECHA_ADMINISTRACION (Fecha 10)
            excel.xlsCell(k++, bien.getFechaAdministracion(), styles.get(k));

            // columna FECHA_VENC_ACTO_ADMIN (Fecha 10)
            excel.xlsCell(k++, bien.getFechaVencActoAdmin(), styles.get(k));

            // columna ENTIDAD_BENEFICIADA_ACTO_ADMIN (NOMBRE_BENEFICIARIO_ACTO_ADMIN) (caracter 200)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getEntidadBeneficiadaActoAdmin(), 200), styles.get(k));

            // columna DOC_ALTA_SBN (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDocAltaSbn(), 50), styles.get(k));

            // columna DOC_BAJA_SBN (caracter 50)
            excel.xlsCell(k++, toStringNull(usarNulls, bien.getDocBajaSbn(), 50), styles.get(k));

        }

        return excel.exportToByteArray(DEFAULT_INITIAL_FILE_SIZE);
    }

    private boolean usarNulls() throws Exception {

        ParametroEntity filter = new ParametroEntity();

        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setNombre(Constantes.PARAMETROS.EXCEL_SBN_TRANSFORMAR_NULLS);

        List<ParametroEntity> parametro = parametroNegocio.select(filter);
        if (CollectionUtils.isNotEmpty(parametro)) {
            return Constantes.UNO.equals(parametro.get(0).getValor());
        }

        return false;
    }

    private Map<Integer, AreaEntity> buscarAreas(Integer entidadID) throws Exception {
        AreaEntity filter = new AreaEntity();
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setEntidadID(entidadID);
        return areaNegocio.selectKeyObject(filter);
    }

    private Map<Integer, LocalesEntity> buscarLocales(Integer entidadID) throws Exception {
        LocalesEntity filter = new LocalesEntity();
        filter.setEntidadID(entidadID);
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        return localesNegocio.selectKeyObject(filter);
    }


    private Map<Integer, OficinaEntity> buscarOficinas(Integer entidadID) throws Exception {
        OficinaEntity filter = new OficinaEntity();
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setEntidadID(entidadID);
        return oficinaNegocio.selectKeyObject(filter);
    }

    private Map<Integer, EntidadEntity> buscarEntidades(Integer entidadID) throws Exception {
        EntidadEntity filter = new EntidadEntity();
        filter.setEntidadID(entidadID);
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        return entidadNegocio.selectKeyObject(filter);
    }

    private Map<Integer, EmpleadoEntity> buscarEmpleados(Integer entidadID) throws Exception {
        EmpleadoEntity filter = new EmpleadoEntity();
        filter.setEntidadID(entidadID);
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        return empleadoNegocio.selectKeyObject(filter);
    }

    private Map<String, UbigeoEntity> buscarUbigeo() throws Exception {
        UbigeoEntity filter = new UbigeoEntity();
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        List<UbigeoEntity> entityList = ubigeoNegocio.select(filter);

        HashMap<String, UbigeoEntity> keyObjectMap = new HashMap<>(entityList == null ? 16 : entityList.size());

        if (CollectionUtils.isNotEmpty(entityList)) {
            for (UbigeoEntity entity : entityList) {
                keyObjectMap.put(JS.toBlank(entity.getCodigoUbigeo()), entity);
            }
        }

        return keyObjectMap;
    }

    private String toStringNull(boolean usarNulls, String value, int maxSize) {
        // si en el parametro dice usar null, pasarlo todo a null, caso contrario un trim y eventualmente ""
        return usarNulls ? JS.toNullIf(StringUtils.substring(value, 0, maxSize)) : JS.toBlank(StringUtils.substring(value, 0, maxSize));
    }

    private Double toDoubleNull(boolean usarNulls, Double value) {
        // si en el parametro dice usar null, pasarlo todo a null, caso contrario un trim y eventualmente ""
        if (value == null) return usarNulls ? null : 0.0;
        return value;
    }

    private Integer toIntegerNull(boolean usarNulls, Integer value) {
        // si en el parametro dice usar null, pasarlo todo a null, caso contrario un trim y eventualmente ""
        if (value == null) return usarNulls ? null : 0;
        return value;
    }
}
