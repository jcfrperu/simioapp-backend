<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- dependencias JS -->
<script src="js/jquery-simiosdb.js"></script>
<script src="js/simiosdb.js"></script>
<script src="js/simioapp-global.js"></script>
<script src="js/simioapp-config.js"></script>
<script src="js/simioapp-dao.js"></script>
<script src="js/simioapp-view.js"></script>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong>PANEL DE DESCARGAS</strong> </div>
    
    <div id="divPanelBody" class="panel-body">

		<div class="row">
			<div class="col-md-6">
				<div id="divDescargaAreaID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla area
					</div>
				</div>
			</div>
            <div class="col-md-6">
                <div id="divDescargaInventarioID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla inventario
                    </div>
                </div>
            </div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div id="divDescargaCatalogoID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla catalogo
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="divDescargaCatalogoBienID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla catalogo bien
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div id="divDescargaClaseID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla clase
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="divDescargaCuentaID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla cuenta
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div id="divDescargaDependenciaID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla dependencia
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="divDescargaEmpleadoID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla empleado
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
				<div id="divDescargaEmpleadoUbicacionID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla empleado
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div id="divDescargaEntidadID" class="progress">
					<div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%;">
						descargando tabla entidad
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
                <div id="divDescargaGrupoID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla grupo
                    </div>
                </div>
			</div>
			<div class="col-md-6">
                <div id="divDescargaGrupoClaseID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla grupo clase
                    </div>
                </div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
                <div id="divDescargaLocalesID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla locales
                    </div>
                </div>
			</div>
			<div class="col-md-6">
                <div id="divDescargaOficinaID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla oficina
                    </div>
                </div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6">
                <div id="divDescargaParametroID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla parametro
                    </div>
                </div>
			</div>
			<div class="col-md-6">
                <div id="divDescargaUbigeoID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla ubigeo
                    </div>
                </div>
			</div>
		</div>

        <div class="row">
            <div class="col-md-12">
                <div id="divDescargaBienID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla bien
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <div id="divDescargaInventarioBienID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla inventario bien
                    </div>
                </div>
            </div>
        </div>

		<div class="row">
			<div class="col-md-12">
                <div id="divDescargaInventarioAperturaID" class="progress">
                    <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;">
                        descargando tabla inventario apertura
                    </div>
                </div>
			</div>
		</div>

    </div>
    <div class="panel-footer" id="div-footer">
        <div class="row">
            <div class="col-sm-4 col-md-3 col-lg-2">
                <button id="btnDescargar" type="button" class="btn btn-block btn-primary" title="Descargar">DESCARGAR</button>
            </div>
            <div class="col-sm-4 col-md-4 col-lg-3" id="divIrModoOffline">

				<form  id="form-siguiente-offline" action="simioapp.jsp" method="GET">
					<button id="btnIrModoOffline" type="button" class="btn btn-block btn-primary" title="Ir al Modo offline">IR MODO OFFLINE</button>
				</form>

            </div>
        </div>
    </div>
</div>

<!-- se usa para redirigir despues de generar el token (debe ser post porque el token tiene muchos caracteres)  -->
<div id="hiddenForms">
	<form id="form-siguiente" action="admin.htm" method="POST" role="form">
        <!-- este form solo se usa para guardar los input hidden, no se debe hacer un submit -->
		<input type="hidden" name="usuario" value="${usuario}">
		<input type="hidden" name="entidad" value="${entidad}">
		<input type="hidden" name="inventario" value="${inventario}">
		<input type="hidden" name="token" value="${token}">

        <input type="hidden" name="usuarioNombre" value="${usuarioNombre}">
        <input type="hidden" name="entidadNombre" value="${entidadNombre}">
        <input type="hidden" name="inventarioNombre" value="${inventarioNombre}">
	</form>
</div>

<!-- popups - popup de mensaje y de confirmacion -->
<c:import url="/admin.htm">
    <c:param name="action" value="cargarPage" />
    <c:param name="_page" value="popups" />
    <c:param name="_module" value="" />
    <c:param name="_template" value="default" />
</c:import>

<!-- divMsgGeneralError -->
<c:import url="/admin.htm">
    <c:param name="action" value="cargarPage"/>
    <c:param name="_page" value="barra-error"/>
    <c:param name="_module" value=""/>
    <c:param name="_template" value="default"/>
</c:import>

<script type="text/javascript">

    // variables globales
    var TABLAS = new SimioTabla();
    var gPanelDescargaConfig = new SimioConfigApp();                                    // configuracion de la BD
    var gPanelDescargaDAO = new SimioDAO(gPanelDescargaConfig.getNombreBD());           // agrupador de DAOs para manipular las tablas de la BD
    var gPanelDescargaMaker = new SimioObjectMaker();                                   // objeto para crear beans con la estructura correcta para insertar en una tabla
    var gPanelDescargaErrores = [];
    var gPanelDescargaCorrectas = [];

	$(document).ready(function() {

        $('#hiddenForms').hide();
        $('#btnIrModoOffline').hide();
        
		initPanelDescarga();
	});

    function initPanelDescarga() {

        // intenta borrar y crear la bd local (este o no este previamente), sino muestra un mensaje de error y no continua
        gPanelDescargaConfig.eliminarYCrearBD(function() {

            initEventosPanelDescarga();

        }, function() {

            habilitarControles('#div-footer', false);
            showMensajeError("Mensaje", "No se pudo borrar la BD local por favor avisar a sistemas");
        });

    }

    function initEventosPanelDescarga() {

        $('#btnDescargar').on('click', clickBtnDescargarPanelDescarga);
        $('#btnIrModoOffline').on('click', clickBtnIrModoOfflinePanelDescarga);
    }

    function clickBtnDescargarPanelDescarga(event) {

        preventDefaultEvent(event);

        gPanelDescargaErrores = [];
        gPanelDescargaCorrectas = [];

        // desaparecer el boton de descarga (solo se descargara una vez)
        habilitarControl('#btnDescargar', false);
        $('#btnDescargar').off();

        // descargar tablas (19 tablas)
        callAjaxDescargarTablaPanelDescarga(TABLAS.area, '#divDescargaAreaID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.bien, '#divDescargaBienID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.catalogo, '#divDescargaCatalogoID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.catalogoBien, '#divDescargaCatalogoBienID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.clase, '#divDescargaClaseID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.cuenta, '#divDescargaCuentaID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.dependencia, '#divDescargaDependenciaID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.empleado, '#divDescargaEmpleadoID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.empleadoUbicacion, '#divDescargaEmpleadoUbicacionID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.entidad, '#divDescargaEntidadID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.grupo, '#divDescargaGrupoID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.grupoClase, '#divDescargaGrupoClaseID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.locales, '#divDescargaLocalesID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.oficina, '#divDescargaOficinaID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.parametro, '#divDescargaParametroID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.ubigeo, '#divDescargaUbigeoID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.inventario, '#divDescargaInventarioID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.inventarioBien, '#divDescargaInventarioBienID');
        callAjaxDescargarTablaPanelDescarga(TABLAS.inventarioApertura, '#divDescargaInventarioAperturaID');

        guardarDatosSessionPanelDescarga();
    }

    function updateBotonIrModoOffline() {

        var gNroTablasDescargar = 19; // CONSTANTE
        if ( gPanelDescargaCorrectas.length == gNroTablasDescargar ) {
            setTimeout(function() {
                $('#btnIrModoOffline').show();
                habilitarControl('#btnIrModoOffline', true);
            }, 500);
        }
    }

    function getDatosSessionPanelDescarga() {

        var formSiguiente = $('#form-siguiente');

        var result = {};

        result.usuario = formSiguiente.find( 'input[name="usuario"]' ).val();
        result.usuarioNombre = formSiguiente.find( 'input[name="usuarioNombre"]' ).val();
        result.entidad = formSiguiente.find( 'input[name="entidad"]' ).val();
        result.entidadNombre = formSiguiente.find( 'input[name="entidadNombre"]' ).val();
        result.inventario = formSiguiente.find( 'input[name="inventario"]' ).val();
        result.inventarioNombre = formSiguiente.find( 'input[name="inventarioNombre"]' ).val();
        result.token = formSiguiente.find( 'input[name="token"]' ).val();

        return result;
    }

    function guardarDatosSessionPanelDescarga() {

        var itemBD = gPanelDescargaMaker.makeSessionObject(null, null);

        var result = getDatosSessionPanelDescarga();

        itemBD.usuario = result.usuario;
        itemBD.usuarioNombre = result.usuarioNombre;
        itemBD.entidad = result.entidad;
        itemBD.entidadNombre = result.entidadNombre;
        itemBD.inventario = result.inventario;
        itemBD.inventarioNombre = result.inventarioNombre;
        itemBD.token = result.token;

        buildLocalKey(itemBD);

        // guardar la data de la session/credenciales
        gPanelDescargaDAO.session.putItem(itemBD, function(info, event) {

            consoleLog('se guardaron las credenciales correctamente');
            updateBotonIrModoOffline();

        }, function(error, event) {

            gPanelDescargaErrores.push('credenciales');
            showMensaje( 'Mensaje', 'No se pudo guardar los datos de la session/credenciales.' );
            handleErrorBD(error);
            updateBotonIrModoOffline();
        });

        // guardar el registro fijo en la tabla test para probar luego si hay conexion a la BD o no
        var itemTestBD = gPanelDescargaMaker.makeTestObject(null, null);

        // setear el registro en la tabla test por default, que se usara como test para ver si existe o no la bd
        itemTestBD.testID = 'testID';

        buildLocalKey(itemTestBD);

        gPanelDescargaDAO.test.putItem(itemTestBD, function(info, event) {

            consoleLog('se guardo registro test correctamente');
            updateBotonIrModoOffline();

        }, function(error, event) {

            gPanelDescargaErrores.push('test');
            showMensaje( 'Mensaje', 'No se pudo guardar los datos de la tabla test' );
            handleErrorBD(error);
            updateBotonIrModoOffline();
        });

        updateBotonIrModoOffline();
    }

    function clickBtnIrModoOfflinePanelDescarga(event) {

        preventDefaultEvent(event);

        // solo si no hubo errores sigue al siguiente paso
        if ( estaDefinido( gPanelDescargaErrores ) && gPanelDescargaErrores.length == 0 ) {

			// NOTA: se hace el submit con POST, se carga la pagina de offline y ahi mismo con un ajax elimina la session
			// del lado servidor para tener congruencia de que ha salido de session
            $('#form-siguiente-offline').submit();

        } else {

            showMensaje('Mensaje', 'Ocurrieron errores en la descarga. Favor revisar' );
        }
    }

    function callAjaxDescargarTablaPanelDescarga(nombreTabla, divID) {

        var result = getDatosSessionPanelDescarga();

        // Metodo que recibe el nombre de la tabla a descargar, asi como el ID del div donde
        // se generan las cajitas con los resultados de la migracion

        callAjax(true, 'descarga.htm?action=descargarTabla', {
            'nombreTabla': nombreTabla,
            'usuario' : result.usuario,
            'entidad' : result.entidad,
            'inventario' : result.inventario,
            'token' : result.token
        }, function (response) {

            var txDAO = gPanelDescargaDAO.crearTXDAO([nombreTabla]);

            txDAO.execute(function (transaction) {

                txDAO.clearItems(transaction, nombreTabla);

                // recorrer todos los items de la peticion
                var posicion = 0;
                var listaSize = response.dataJson.lista.length;

                if (listaSize > 0) {

                    $.each(response.dataJson.lista, function (i, item) {

                        var itemBD = gPanelDescargaMaker.makeTablaObject(nombreTabla, item, true);

                        txDAO.addItem(transaction, itemBD, nombreTabla);

                        posicion++;
                        actualizarBarraEnProcesoPanelDescarga(divID, parseInt(100 * posicion / listaSize));
                    });

                }

            }, function (info) {

                actualizarBarraExitoPanelDescarga(divID, 'Descarga de la tabla ' + nombreTabla + ' se realiz&oacute; con &eacute;xito');
                consoleLog('se termino de descargar la tabla: ' + nombreTabla);

                gPanelDescargaCorrectas.push(nombreTabla);

                updateBotonIrModoOffline();

            }, function (error) {

                // indicador que ocurrio error en la descarga
                gPanelDescargaErrores.push(nombreTabla);

                actualizarBarraErrorPanelDescarga(divID, 'Ocurrió un error al descargar tabla ' + nombreTabla);
                handleErrorBD(error);

                updateBotonIrModoOffline();
            });

        }, function (esErrorAjax, response, error) {

            // indicador que ocurrio error en la descarga
            gPanelDescargaErrores.push(nombreTabla);

            updateBotonIrModoOffline();
        });
    }

    function actualizarBarraExitoPanelDescarga(divID, mensaje) {

        $(divID).children().css('width', '100%');
        $(divID).children().removeClass('progress-bar-primary');
        $(divID).children().addClass('progress-bar-success');
        $(divID).children().html(mensaje);
    }

    function actualizarBarraErrorPanelDescarga(divID, mensaje) {

        $(divID).children().css('width', '100%');
        $(divID).children().removeClass('progress-bar-primary');
        $(divID).children().addClass('progress-bar-danger');
        $(divID).children().html(mensaje);
    }

    function actualizarBarraEnProcesoPanelDescarga(divID, porcentajeAvance) {

        $(divID).children().css('width', porcentajeAvance + '%');
    }

</script>
