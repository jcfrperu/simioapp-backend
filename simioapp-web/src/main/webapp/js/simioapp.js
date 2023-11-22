// VARIABLES GLOBALES
var TABLAS = new SimioTabla();
var CONSTANTES = new SimioConstantes();

var gSimioConfig = new SimioConfigApp();                       // configuracion de la BD
var gSimioDAO = new SimioDAO(gSimioConfig.getNombreBD());      // agrupador de DAOs para manipular las tablas de la BD
var gSimioMaker = new SimioObjectMaker();                      // objeto para crear beans con la estructura correcta para insertar en una tabla
var gSimioRAM = {'tabla': {}};                                 // objeto que contiene las tablas en memoria

var data_tb_catalogo_bien = [];

/*Variables de RAM para cuando se seleccione un usuario guarde en memoria las areas en las que se encuentra*/
var areaIDPorEmpleado = [];
var indexAreaIDPorEmpleado = 0;

/*Variable de RAM que contiene los locales y areas de tabla empleado_ubicacion para empleado seleccionado*/
var localesAreaPorEmpleado = [];
var indexLocalesAreaPorEmpleado = 0;

function initApp() {

    // validar que llegue desde el backend y que no haya escrito la URL en el navegador
    gSimioConfig.existeBD(function () {

        initHidden();
        initRAM(); // NOTA: este metodo es importante, todo lo de la BD local se guarda en RAM
        initFechas();
        initControlerBuscarBien();
//        initControlesBuscarNombreBien();jlunah

        // se le da un delay porque initRAM parece que se demora
        setTimeout(function() {

            initEventos();
            initCombos();
            initMask();
//            changeCatalogoBien();jlunah
            changeTipoCuenta();
            changeUsuario();
            changeLocales();
            changeAreas();
            changeCuentaContable();
            initDatosUsuario();
            cerrarSessionOfflineQuiet(); // este metodo elimina la session online por seguridad

            // pagina inicial
            clickBtnCargarListado();

            // guardar sus estilos
            guardarEstiloBotonListado('#btn-menu-izq-listado-bienes');
            guardarEstiloBotonListado('#btn-menu-izq-nuevo-bien');
            guardarEstiloBotonListado('#btn-menu-izq-editar-bien');
            guardarEstiloBotonListado('#btn-menu-izq-quitar-bien');
            guardarEstiloBotonListado('#btn-menu-izq-ver-bien');
            guardarEstiloBotonListado('#btn-menu-izq-resumen-toma');

        }, 750);

    }, function () {

        showMensaje('Mensaje', 'No tiene permiso para esta acci&oacute;n', function () {
            bloquearApp();
        });
    });
}

function initFechas() {

    $( '#div_txt_fecha_adquisicion' ).datetimepicker({
        locale: "es",
        format: "DD/MM/YYYY"
    });
}    

function cerrarSessionOfflineQuiet() {

    // envia una peticion desde el offline para cerrar la session en el servidor web (por seguridad se hace esto)
    if (estaDefinido(gSimioRAM) && estaDefinido(gSimioRAM.tabla) && estaDefinido(gSimioRAM.tabla.session)) {

        var sessionData = gSimioRAM.tabla.session[0];

        if (estaDefinido(sessionData)) {

            $.ajax({
                url: 'admin.htm?action=cerrarSesionDesdeOffline',
                cache: false,
                data: {
                    'usuario': sessionData.usuario,
                    'entidad': sessionData.entidad,
                    'inventario': sessionData.inventario,
                    'token': sessionData.token
                },
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {
                        // algun error en app -> se comento, es quiet, no se quiere reaccionar a esto
                        // handleErrorJson(data);
                        return;
                    }

                    // si es exito no pasa nada, solo muestra un mensaje informativo
                    if ( estaDefinido(data.dataJson) && estaDefinido(data.dataJson.closed) ) {
                        if ( data.dataJson.closed == 'si' ) {
                            consoleLog('session online cerrada correctamente');
                        }
                    }

                },
                error: function(data, textStatus, errorThrown) {

                    // hubo algun tipo de error en la conexion -> se comento, es quiet, no se quiere reaccionar a esto
                    // handleError(data);
                }
            });
        }
    }
}

function estadosIniciales() {

    // cada seccion de esta "vista compartida" debe inicializarse de cierta manera para mostrar primero el listado.
    // colocar aqui la inicializacion de cada seccion

    cnxEstadoInicialConexion();
}

function mostrarPagina(divID) {

    // oculta todas las vistas
    ocultarVistas();

    // muestra solo la del parametro
    $(divID).show();
}

function ocultarVistas() {

    // ocultar todas los divs asociados a las vistas
    $('#div-vista-listado').hide();
    $('#div-vista-registro').hide();
    $('#div-vista-ver').hide();
    $('#div-vista-resumen').hide();
    $('#div-vista-conexion').hide();
}

function cleanApp() {

    TABLAS = null;
    CONSTANTES = null;

    gSimioConfig = null;
    gSimioDAO = null;
    gSimioMaker = null;
    gSimioRAM = null;
}

function bloquearApp() {

    ocultarVistas();
    cleanApp();
    eliminarBD();

    // mostrar mensaje indicativo que app esta inactiva
    var spanJQ = $('<span/>');
    spanJQ.html("Aplicaci&oacute;n deshabilitada");
    $('#div-main-app').html(spanJQ);
}

function eliminarBD() {

    if ( estaDefinido( gSimioConfig ) ) {

        gSimioConfig.eliminarBD(function () {
            consoleLog('se borro la bd');
        }, function () {
            consoleLog('no se pudo borrar la bd');
        });
    }
}

function initHidden() {

    $('#hiddenForms').hide();
}

function initDatosUsuario() {

    if (estaDefinido(gSimioRAM) && estaDefinido(gSimioRAM.tabla) && estaDefinido(gSimioRAM.tabla.session)) {

        var sessionData = gSimioRAM.tabla.session[0];

        if (estaDefinido(sessionData)) {

            $('.div-header-inventariador').html(sessionData.usuarioNombre);
            $('.div-header-empresa').html( 'Entidad ' + sessionData.entidad);
            $('.div-header-inventario').html(sessionData.inventarioNombre);

            $('.div-header-inventariador').prop( 'title', 'Inventariador: ' + sessionData.usuarioNombre);
            $('.div-header-empresa').prop( 'title', 'Entidad: ' + sessionData.entidad);
            $('.div-header-inventario').prop( 'title', 'Inventario: ' + sessionData.inventarioNombre);
        }
    }
}

function initRAM() {

    // copia todas las tablas a variables javascript clasicas (en memoria)
    copiarTablaRAM(TABLAS.area);
    copiarTablaRAM(TABLAS.bien);
    copiarTablaRAM(TABLAS.catalogo);
    copiarTablaRAM(TABLAS.catalogoBien);
    copiarTablaRAM(TABLAS.clase);
    copiarTablaRAM(TABLAS.cuenta);
    copiarTablaRAM(TABLAS.dependencia);
    copiarTablaRAM(TABLAS.empleado);
    copiarTablaRAM(TABLAS.empleadoUbicacion);
    copiarTablaRAM(TABLAS.entidad);
    copiarTablaRAM(TABLAS.grupo);
    copiarTablaRAM(TABLAS.grupoClase);
    copiarTablaRAM(TABLAS.locales);
    copiarTablaRAM(TABLAS.oficina);
    copiarTablaRAM(TABLAS.parametro);
    copiarTablaRAM(TABLAS.ubigeo);
    copiarTablaRAM(TABLAS.inventario);
    copiarTablaRAM(TABLAS.inventarioBien);
    copiarTablaRAM(TABLAS.inventarioApertura);
    copiarTablaRAM(TABLAS.session);
}

function copiarTablaRAM(nombreTabla) {

    // crear dentro del objeto "tabla", un arreglo llamado "nombreTabla"
    gSimioRAM.tabla[nombreTabla] = [];

    // accediendo al DAO con el nombre "nombreTabla"
    gSimioDAO[nombreTabla].eachItem(function (item) {

        // agregando el item de la BD a la tabla en memoria
        gSimioRAM.tabla[nombreTabla].push(item.value);
    });
}
 
function initEventos() {

    // eventos generales del menu derecho
    $('#btn-menu-der-ir-modo-online').off();
    $('#btn-menu-der-ir-modo-online').on('click', clickBtnIrModoOnline);

    $('#btn-menu-der-comprobar-conexion').off();
    $('#btn-menu-der-comprobar-conexion').on('click', clickBtnCargarComprobarConexion);

    // eventos generales del menu izquierdo
    $('#btn-menu-izq-listado-bienes').off();
    $('#btn-menu-izq-listado-bienes').on('click', clickBtnCargarListado);                   

    $('#btn-menu-izq-nuevo-bien').off();
    $('#btn-menu-izq-nuevo-bien').on('click', clickBtnCargarNuevoBien);

    $('#btn-menu-izq-editar-bien').off();
    $('#btn-menu-izq-editar-bien').on('click', clickBtnCargarEditarBien);

    $('#btn-menu-izq-quitar-bien').off();
    $('#btn-menu-izq-quitar-bien').on('click', clickBtnQuitarBien);

    $('#btn-menu-izq-ver-bien').off();
    $('#btn-menu-izq-ver-bien').on('click', clickBtnCargarVerBien);

    $('#btn-menu-izq-resumen-toma').off();
    $('#btn-menu-izq-resumen-toma').on('click', clickBtnCargarResumenToma);

    // eventos generales de la seccion registro (nuevo y editar bien)
    $('#bnt_grabar').off();
    $('#bnt_grabar').on('click', clickBtnkAgregar);

    // eventos de la seccion "comprobar conexion"
    $('#btn_comprobar_conexion').off();
    $('#btn_comprobar_conexion').on('click', clickBtnComprobarConexion);

    // eventos para mostrar la pantalla modal relacionado a empleado
    $('#btn_buscarEmpleado').off();
    $('#btn_buscarEmpleado').on('click', clickBtnBuscarEmpleado);

    $('#btnAceptarModalBuscarEmpleado').off();
    $('#btnAceptarModalBuscarEmpleado').on('click', clickBtnAceptarModalBuscarEmpleado);

    $('#btnCerrarModalBuscarEmpleado').off();
    $('#btnCerrarModalBuscarEmpleado').on('click', clickBtnCerrarModalBuscarEmpleado);
}