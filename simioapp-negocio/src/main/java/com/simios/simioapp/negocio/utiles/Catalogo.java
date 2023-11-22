package com.simios.simioapp.negocio.utiles;

import com.simios.simioapp.comunes.utiles.Constantes;
import com.simios.simioapp.dao.CatalogoDAO;
import com.simios.simioapp.dominio.entidades.CatalogoEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component("catalogo")
public class Catalogo {

    @Autowired
    private CatalogoDAO catalogoDAO;

    // CATALOGO INDEL: Indicador de borrado en BD
    public String INDEL;
    public String INDEL_ACTIVO;
    public String INDEL_INACTIVO;

    // CATALOGO TIPO_VALOR_PARAMETRO: Tipo de valor de parámetro
    public String TIPO_VALOR_PARAMETRO;
    public String TIPO_VALOR_PARAMETRO_ENTERO;
    public String TIPO_VALOR_PARAMETRO_DECIMAL;
    public String TIPO_VALOR_PARAMETRO_CADENA;
    public String TIPO_VALOR_PARAMETRO_BOOLEANO;

    // CATALOGO TIPO_CATALOGO: Tipo de catálogo
    public String TIPO_CATALOGO;
    public String TIPO_CATALOGO_CABECERA;
    public String TIPO_CATALOGO_DETALLE;

    // CATALOGO TIPO_DOCUMENTO: Tipo de documento
    public String TIPO_DOCUMENTO;
    public String TIPO_DOCUMENTO_DNI;
    public String TIPO_DOCUMENTO_CARNET_EXTRANJERIA;

    // CATALOGO TIPO_MODALIDAD: Tipo de modalidad de contratacion
    public String TIPO_MODALIDAD;
    public String TIPO_MODALIDAD_CAS;
    public String TIPO_MODALIDAD_SERVIDOR_PUBLICO;
    public String TIPO_MODALIDAD_FUNCIONARIO;
    public String TIPO_MODALIDAD_PRACTICANTE;
    public String TIPO_MODALIDAD_SECIGRISTA;
    public String TIPO_MODALIDAD_TERCERO;

    // CATALOGO OFICINA_REGISTRAL: Oficina registral
    public String OFICINA_REGISTRAL;
    public String OFICINA_REGISTRAL_REGISTRO_PREDIAL;
    public String OFICINA_REGISTRAL_REGISTROS_PUBLICOS;

    // CATALOGO TIPO_CUENTA:
    public String TIPO_CUENTA;
    public String TIPO_CUENTA_DE_USO_ESTATAL;
    public String TIPO_CUENTA_DE_USO_PRIVADO;

    // CATALOGO TIPO_MONEDA: Tipo de Moneda
    public String TIPO_MONEDA;
    public String TIPO_MONEDA_DOLARES;
    public String TIPO_MONEDA_SOLES;

    // CATALOGO TIPO_MONEDA: Unidad de Medida
    public String UNIDAD_MEDIDA;
    public String UNIDAD_MEDIDA_HECTARIAS;
    public String UNIDAD_MEDIDA_METROS;

    // CATALOGO TIPO_PROPIEDAD: Tipo de propiedad
    public String TIPO_PROPIEDAD;
    public String TIPO_PROPIEDAD_ESTATAL;
    public String TIPO_PROPIEDAD_PRIVADA;

    // CATALOGO TIPO_DIRECCION: Tipo Direccion
    public String TIPO_DIRECCION;
    public String TIPO_DIRECCION_ALAMEDA;
    public String TIPO_DIRECCION_AVENIDA;
    public String TIPO_DIRECCION_BLOCK;
    public String TIPO_DIRECCION_CALLE;
    public String TIPO_DIRECCION_CARRETERA;
    public String TIPO_DIRECCION_JIRON;
    public String TIPO_DIRECCION_MALECON;
    public String TIPO_DIRECCION_OVALO;
    public String TIPO_DIRECCION_PARQUE;
    public String TIPO_DIRECCION_PASAJE;
    public String TIPO_DIRECCION_PLAZA;
    public String TIPO_DIRECCION_PROLONGACION;

    // CATALOGO FORMA_ADQUISICION: Forma de Adquisicion
    public String FORMA_ADQUISICION;
    public String FORMA_ADQUISICION_COMPRA;
    public String FORMA_ADQUISICION_DONACION_TRANSFERENCIA;
    public String FORMA_ADQUISICION_REPRODUCCION;
    public String FORMA_ADQUISICION_FABRICACION;
    public String FORMA_ADQUISICION_OTRA_CAUSAL;
    public String FORMA_ADQUISICION_PERMUTA;
    public String FORMA_ADQUISICION_REPOSICION;
    public String FORMA_ADQUISICION_SANEAMIENTO;

    // CATALOGO CUENTA_USOCUENTA: USO DE LA CUENTA PARA MODULO DE CUENTAS
    public String CUENTA_USOCUENTA;
    public String CUENTA_USOCUENTA_PRIVADA;
    public String CUENTA_USOCUENTA_ESTATAL;

    // CATALOGO CUENTA_FLAGCUENTA: FLAG DE CUENTA PARA MODULO DE CUENTAS
    public String CUENTA_FLAGCUENTA;
    public String CUENTA_FLAGCUENTA_FALSO;
    public String CUENTA_FLAGCUENTA_VERDADERO;

    // CATALOGO CUENTA_TIPOCUENTA: TIPO DE CUENTA EN MODULO DE CUENTAS
    public String CUENTA_TIPOCUENTA;
    public String CUENTA_TIPOCUENTA_ACTIVO_FIJO;
    public String CUENTA_TIPOCUENTA_CUENTA_ORDEN;
    public String CUENTA_TIPOCUENTA_INMUEBLE;

    // CATALOGO ESTADO_INVENTARIO: ESTADOS DEL INVENTARIO
    public String ESTADO_INVENTARIO;
    public String ESTADO_INVENTARIO_ABIERTO;
    public String ESTADO_INVENTARIO_CERRADO;

    // CATALOGO UBICACION_EMPLEADO_ASOCIACION: TIPO DE ASOCIACIÓN DEL EMPLEADO CON SU UBICACIÓN
    public String UBICACION_EMPLEADO_ASOCIACION;
    public String UBICACION_EMPLEADO_ASOCIACION_NINGUNA;
    public String UBICACION_EMPLEADO_ASOCIACION_UBICACION_FISICA;
    public String UBICACION_EMPLEADO_ASOCIACION_RESPONSABLE_UBICACION;

    // CATALOGO SESION_USUARIO_ESTADO: ESTADOS DE LA SESSION DE USUARIO
    public String SESION_USUARIO_ESTADO;
    public String SESION_USUARIO_ESTADO_ACTIVO;
    public String SESION_USUARIO_ESTADO_INACTIVO;
    public String SESION_USUARIO_ESTADO_BLOQUEDO_MANUAL;
    public String SESION_USUARIO_ESTADO_BLOQUEADO_SUPERAR_INTENTOS;

    // CATALOGO ESTADO_BIEN: ESTADO FISICO DEL BIEN
    public String ESTADO_BIEN;
    public String ESTADO_BIEN_MUY_BUENO;
    public String ESTADO_BIEN_BUENO;
    public String ESTADO_BIEN_REGULAR;
    public String ESTADO_BIEN_MALO;

    // CATALOGO ESTADO_SUBIDA: ESTADOS DE SUBIDA DEL BIEN
    public String ESTADO_SUBIDA;
    public String ESTADO_SUBIDA_SUBIDO;
    public String ESTADO_SUBIDA_FALTA_SUBIR;

    // CATALOGO SOBRANTE_FALTANTE: SOBRANTE O FALTANTE DEL BIEN
    public String SOBRANTE_FALTANTE;
    public String SOBRANTE_FALTANTE_SOBRANTE;
    public String SOBRANTE_FALTANTE_FALTANTE;
    public String SOBRANTE_FALTANTE_TRASLADO;

    // CATALOGO REVISADO_TOMA: ESTADO REVISADO TOMA DE INVENTARIO
    public String REVISADO_TOMA;
    public String REVISADO_TOMA_INVENTARIADO;
    public String REVISADO_TOMA_FALTA_INVENTARIAR;

    // INICIALIZACION
    @PostConstruct
    public void fill() {

        try {

            List<CatalogoEntity> lista;

            lista = buscarCatalogoDetalle("INDEL");
            INDEL = "INDEL";
            INDEL_ACTIVO = buscarValorCatalogo(lista, "ACTIVO");
            INDEL_INACTIVO = buscarValorCatalogo(lista, "INACTIVO");

            lista = buscarCatalogoDetalle("TIPO_CATALOGO");
            TIPO_CATALOGO = "TIPO_CATALOGO";
            TIPO_CATALOGO_CABECERA = buscarValorCatalogo(lista, "CABECERA");
            TIPO_CATALOGO_DETALLE = buscarValorCatalogo(lista, "DETALLE");

            lista = buscarCatalogoDetalle("REVISADO_TOMA");
            REVISADO_TOMA = "REVISADO_TOMA";
            REVISADO_TOMA_INVENTARIADO = buscarValorCatalogo(lista, "INVENTARIADO");
            REVISADO_TOMA_FALTA_INVENTARIAR = buscarValorCatalogo(lista, "FALTA_INVENTARIAR");

            lista = buscarCatalogoDetalle("SOBRANTE_FALTANTE");
            SOBRANTE_FALTANTE = "SOBRANTE_FALTANTE";
            SOBRANTE_FALTANTE_SOBRANTE = buscarValorCatalogo(lista, "SOBRANTE");
            SOBRANTE_FALTANTE_FALTANTE = buscarValorCatalogo(lista, "FALTANTE");
            SOBRANTE_FALTANTE_TRASLADO = buscarValorCatalogo(lista, "TRASLADO");

            lista = buscarCatalogoDetalle("ESTADO_SUBIDA");
            ESTADO_SUBIDA = "ESTADO_SUBIDA";
            ESTADO_SUBIDA_SUBIDO = buscarValorCatalogo(lista, "SUBIDO");
            ESTADO_SUBIDA_FALTA_SUBIR = buscarValorCatalogo(lista, "FALTA_SUBIR");

            lista = buscarCatalogoDetalle("TIPO_VALOR_PARAMETRO");
            TIPO_VALOR_PARAMETRO = "TIPO_VALOR_PARAMETRO";
            TIPO_VALOR_PARAMETRO_ENTERO = buscarValorCatalogo(lista, "ENTERO");
            TIPO_VALOR_PARAMETRO_DECIMAL = buscarValorCatalogo(lista, "DECIMAL");
            TIPO_VALOR_PARAMETRO_CADENA = buscarValorCatalogo(lista, "CADENA");
            TIPO_VALOR_PARAMETRO_BOOLEANO = buscarValorCatalogo(lista, "BOOLEANO");

            lista = buscarCatalogoDetalle("OFICINA_REGISTRAL");
            OFICINA_REGISTRAL = "OFICINA_REGISTRAL";
            OFICINA_REGISTRAL_REGISTRO_PREDIAL = buscarValorCatalogo(lista, "REGISTRO_PREDIAL");
            OFICINA_REGISTRAL_REGISTROS_PUBLICOS = buscarValorCatalogo(lista, "REGISTROS_PUBLICOS");

            lista = buscarCatalogoDetalle("TIPO_DOCUMENTO"); // NO TODOS ESTAN EN BD
            TIPO_DOCUMENTO = "TIPO_DOCUMENTO";
            TIPO_DOCUMENTO_DNI = buscarValorCatalogo(lista, "DNI");
            TIPO_DOCUMENTO_CARNET_EXTRANJERIA = buscarValorCatalogo(lista, "CARNET_EXTRANJERIA");

            lista = buscarCatalogoDetalle("TIPO_MODALIDAD");
            TIPO_MODALIDAD = "TIPO_MODALIDAD";
            TIPO_MODALIDAD_CAS = buscarValorCatalogo(lista, "CAS");
            TIPO_MODALIDAD_SERVIDOR_PUBLICO = buscarValorCatalogo(lista, "SERVIDOR_PUBLICO");
            TIPO_MODALIDAD_FUNCIONARIO = buscarValorCatalogo(lista, "FUNCIONARIO");
            TIPO_MODALIDAD_PRACTICANTE = buscarValorCatalogo(lista, "PRACTICANTE");
            TIPO_MODALIDAD_SECIGRISTA = buscarValorCatalogo(lista, "SECIGRISTA");
            TIPO_MODALIDAD_TERCERO = buscarValorCatalogo(lista, "TERCERO");

            lista = buscarCatalogoDetalle("UNIDAD_MEDIDA");
            UNIDAD_MEDIDA = "UNIDAD_MEDIDA";
            UNIDAD_MEDIDA_HECTARIAS = buscarValorCatalogo(lista, "HECTARIAS");
            UNIDAD_MEDIDA_METROS = buscarValorCatalogo(lista, "METROS");

            lista = buscarCatalogoDetalle("TIPO_MONEDA");
            TIPO_MONEDA = "TIPO_MONEDA";
            TIPO_MONEDA_DOLARES = buscarValorCatalogo(lista, "DOLARES");
            TIPO_MONEDA_SOLES = buscarValorCatalogo(lista, "SOLES");

            lista = buscarCatalogoDetalle("TIPO_CUENTA");
            TIPO_CUENTA = "TIPO_CUENTA";
            TIPO_CUENTA_DE_USO_ESTATAL = buscarValorCatalogo(lista, "DE_USO_ESTATAL");
            TIPO_CUENTA_DE_USO_PRIVADO = buscarValorCatalogo(lista, "DE_USO_PRIVADO");

            lista = buscarCatalogoDetalle("TIPO_DIRECCION");
            TIPO_DIRECCION = "TIPO_DIRECCION";
            TIPO_DIRECCION_ALAMEDA = buscarValorCatalogo(lista, "ALAMEDA");
            TIPO_DIRECCION_AVENIDA = buscarValorCatalogo(lista, "AVENIDA");
            TIPO_DIRECCION_BLOCK = buscarValorCatalogo(lista, "BLOCK");
            TIPO_DIRECCION_CALLE = buscarValorCatalogo(lista, "CALLE");
            TIPO_DIRECCION_CARRETERA = buscarValorCatalogo(lista, "CARRETERA");
            TIPO_DIRECCION_JIRON = buscarValorCatalogo(lista, "JIRON");
            TIPO_DIRECCION_MALECON = buscarValorCatalogo(lista, "MALECON");
            TIPO_DIRECCION_OVALO = buscarValorCatalogo(lista, "OVALO");
            TIPO_DIRECCION_PARQUE = buscarValorCatalogo(lista, "PARQUE");
            TIPO_DIRECCION_PASAJE = buscarValorCatalogo(lista, "PASAJE");
            TIPO_DIRECCION_PLAZA = buscarValorCatalogo(lista, "PLAZA");
            TIPO_DIRECCION_PROLONGACION = buscarValorCatalogo(lista, "PROLONGACION");

            lista = buscarCatalogoDetalle("TIPO_PROPIEDAD");
            TIPO_PROPIEDAD = "TIPO_PROPIEDAD";
            TIPO_PROPIEDAD_ESTATAL = buscarValorCatalogo(lista, "ESTATAL");
            TIPO_PROPIEDAD_PRIVADA = buscarValorCatalogo(lista, "PRIVADA");

            lista = buscarCatalogoDetalle("FORMA_ADQUISICION");
            FORMA_ADQUISICION = "FORMA_ADQUISICION";
            FORMA_ADQUISICION_COMPRA = buscarValorCatalogo(lista, "COMPRA");
            FORMA_ADQUISICION_DONACION_TRANSFERENCIA = buscarValorCatalogo(lista, "DONACION_TRANSFERENCIA");
            FORMA_ADQUISICION_FABRICACION= buscarValorCatalogo(lista, "FABRICACION");
            FORMA_ADQUISICION_REPRODUCCION = buscarValorCatalogo(lista, "REPRODUCCION");
            FORMA_ADQUISICION_OTRA_CAUSAL = buscarValorCatalogo(lista, "OTRA_CAUSAL");
            FORMA_ADQUISICION_PERMUTA = buscarValorCatalogo(lista, "PERMUTA");
            FORMA_ADQUISICION_REPOSICION = buscarValorCatalogo(lista, "REPOSICION");
            FORMA_ADQUISICION_SANEAMIENTO = buscarValorCatalogo(lista, "SANEAMIENTO");

            lista = buscarCatalogoDetalle("CUENTA_FLAGCUENTA");
            CUENTA_FLAGCUENTA = "CUENTA_FLAGCUENTA";
            CUENTA_FLAGCUENTA_FALSO = buscarValorCatalogo(lista, "FALSO");
            CUENTA_FLAGCUENTA_VERDADERO = buscarValorCatalogo(lista, "VERDADERO");

            lista = buscarCatalogoDetalle("CUENTA_USOCUENTA");
            CUENTA_USOCUENTA = "CUENTA_USOCUENTA";
            CUENTA_USOCUENTA_PRIVADA = buscarValorCatalogo(lista, "PRIVADA");
            CUENTA_USOCUENTA_ESTATAL = buscarValorCatalogo(lista, "ESTATAL");

            lista = buscarCatalogoDetalle("UBICACION_EMPLEADO_ASOCIACION");
            UBICACION_EMPLEADO_ASOCIACION = "UBICACION_EMPLEADO_ASOCIACION";
            UBICACION_EMPLEADO_ASOCIACION_NINGUNA = buscarValorCatalogo(lista, "NINGUNA");
            UBICACION_EMPLEADO_ASOCIACION_UBICACION_FISICA = buscarValorCatalogo(lista, "RESPONSABLE_DE_UBICACION");
            UBICACION_EMPLEADO_ASOCIACION_RESPONSABLE_UBICACION = buscarValorCatalogo(lista, "UBICACION_FISICA");

            lista = buscarCatalogoDetalle("ESTADO_INVENTARIO");
            ESTADO_INVENTARIO = "ESTADO_INVENTARIO";
            ESTADO_INVENTARIO_ABIERTO = buscarValorCatalogo(lista, "ABIERTO");
            ESTADO_INVENTARIO_CERRADO = buscarValorCatalogo(lista, "CERRADO");

            lista = buscarCatalogoDetalle("CUENTA_TIPOCUENTA");
            CUENTA_TIPOCUENTA = "CUENTA_TIPOCUENTA";
            CUENTA_TIPOCUENTA_ACTIVO_FIJO = buscarValorCatalogo(lista, "ACTIVO_FIJO");
            CUENTA_TIPOCUENTA_CUENTA_ORDEN = buscarValorCatalogo(lista, "CUENTA_ORDEN");
            CUENTA_TIPOCUENTA_INMUEBLE = buscarValorCatalogo(lista, "INMUEBLE");

            lista = buscarCatalogoDetalle("SESION_USUARIO_ESTADO");
            SESION_USUARIO_ESTADO = "SESION_USUARIO_ESTADO";
            SESION_USUARIO_ESTADO_ACTIVO = buscarValorCatalogo(lista, "ACTIVO");
            SESION_USUARIO_ESTADO_INACTIVO = buscarValorCatalogo(lista, "INACTIVO");
            SESION_USUARIO_ESTADO_BLOQUEDO_MANUAL = buscarValorCatalogo(lista, "BLOQUEADO_MANUAL");
            SESION_USUARIO_ESTADO_BLOQUEADO_SUPERAR_INTENTOS = buscarValorCatalogo(lista, "BLOQUEADO_SUPERAR_INTENTOS");

            lista = buscarCatalogoDetalle("ESTADO_BIEN");
            ESTADO_BIEN = "ESTADO_BIEN";
            ESTADO_BIEN_MUY_BUENO = buscarValorCatalogo(lista, "MUY_BUENO");
            ESTADO_BIEN_BUENO = buscarValorCatalogo(lista, "BUENO");
            ESTADO_BIEN_REGULAR = buscarValorCatalogo(lista, "REGULAR");
            ESTADO_BIEN_MALO = buscarValorCatalogo(lista, "MALO");

        } catch (Exception ignored) {
            // no se puede hacer mucho aqui
        }

    }

    private List<CatalogoEntity> buscarCatalogoDetalle(String catalogo) throws Exception {

        CatalogoEntity filter = new CatalogoEntity();

        filter.setCatalogo(catalogo);
        filter.setIndDel(Constantes.REGISTRO_ACTIVO);
        filter.setTipo(Constantes.CATALOGO_TIPO_DETALLE);

        List<CatalogoEntity> lista = catalogoDAO.select(filter);

        return CollectionUtils.isNotEmpty(lista) ? lista : new ArrayList<CatalogoEntity>();
    }

    private String buscarValorCatalogo(List<CatalogoEntity> catalogos, String filtro) {

        if (CollectionUtils.isNotEmpty(catalogos)) {
            for (CatalogoEntity catalogo : catalogos) {
                if (StringUtils.equals(catalogo.getDescripcionCorta(), filtro)) {
                    return catalogo.getDatacatalogo();
                }
            }
        }

        return StringUtils.EMPTY;
    }

}