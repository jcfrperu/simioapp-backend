<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> PANEL ASIGNACI&Oacute;N DE INVENTARIO </strong> </div>
    <div class="panel-body">
        
        <div>&nbsp;</div>
	
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>

		<div class="row" id="div-panel-buscar">
			<div class="col-md-12">

				<form id="form-buscar" method="POST" role="form">

                    <input id="msgHidden" type="hidden" value="${msgHidden}">

                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Entidad</label>
                                <select class="form-control input-sm" title="Entidad" name="cboEntidad">
                                    <c:forEach items="${cboEntidad.items}" var="item" >
                                        <option <c:if test="${item.id == cboEntidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Inventario</label>
                                <select class="form-control input-sm" title="Lista de Inventarios Abiertos" name="int_inventarioID" id="int_inventarioID">
                                    <c:forEach items="${cboInventario.items}" var="item" >
                                        <option <c:if test="${item.id == cboInventario.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Patrimonial</label>
                                <input name="txt_codigoPatrimonial" type="text" title="C&oacute;digo Patrimonial" maxlength="12" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Estado Subida</label>
                                <select class="form-control input-sm" title="Estado Subida" name="txt_estadoSubida">
                                    <c:forEach items="${cboEstadoSubida.items}" var="item" >
                                        <option <c:if test="${item.id == cboEstadoSubida.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Sobrante Faltante</label>
                                <select class="form-control input-sm" title="Sobrante Faltante" name="txt_sobranteFaltante">
                                    <c:forEach items="${cboSobranteFaltante.items}" var="item" >
                                        <option <c:if test="${item.id == cboSobranteFaltante.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Inventariador</label>
                                <select class="form-control input-sm" title="Inventariador" name="txt_inventariador">
                                    <c:forEach items="${cboInventariador.items}" var="item" >
                                        <option <c:if test="${item.id == cboInventariador.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
								<span class="help-block"></span>
							</div>
						</div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Inventariador Anterior</label>
                                <select class="form-control input-sm" title="Inventariador Anterior" name="txt_inventariador_anterior">
                                    <c:forEach items="${cboInventariadorAnterior.items}" var="item" >
                                        <option <c:if test="${item.id == cboInventariadorAnterior.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
					</div>

					<div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Local</label>
                                <select class="form-control input-sm" title="Local" id="cbo_local" name="cbo_local">
                                    <c:forEach items="${cboLocal.items}" var="item" >
                                        <option <c:if test="${item.id == cboLocal.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo &Aacute;rea</label>
                                <select class="form-control input-sm" title="&Aacute;rea" id="cbo_area" name="cbo_area">
                                    <c:forEach items="${cboArea.items}" var="item" >
                                        <option <c:if test="${item.id == cboArea.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Oficina</label>
                                <select class="form-control input-sm" title="&Aacute;rea" id="cbo_oficina" name="cbo_oficina">
                                    <c:forEach items="${cboOficina.items}" var="item" >
                                        <option <c:if test="${item.id == cboOficina.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
					</div>

					<div>&nbsp;</div>

					<div class="row">
                        <div class="col-md-3 col-md-offset-4 col-lg-2 col-lg-offset-5">
							<button id="btn_buscar" title="Buscar" class="btn btn-md btn-primary btn-block"><span class="fa fa-search"></span>&nbsp; BUSCAR</button>
						</div>
					</div>

				</form>

			</div>
		</div>

        <div>&nbsp;</div>
		
		<div class="row" id="div-panel-grilla">
		    <div class="col-md-12">
		        <div class="panel panel-default">
		
		            <div class="panel-body">
		                <div class="table-responsive" id="div-load-grilla">
		                    <c:import url="/admin.htm">
		                        <c:param name="action" value="cargarPage" />
		                        <c:param name="_page" value="panel-asignacion-grilla" />
		                        <c:param name="_module" value="panel-asignacion" />
		                        <c:param name="_template" value="default" />
		                    </c:import>
		                </div>
		            </div>
		
		        </div>
		    </div>
		</div>

		<div>&nbsp;</div>

        <div class="row" id="div-panel-inventariadores">
            <div class="col-md-12">

                <form id="form-inventariadores" action="asignacion-inventario.htm" method="POST" role="form">

                    <input type="hidden" name="action" value="">
                    <input type="hidden" name="bienes_id" value="">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Inventariador a asignar</label>
                                <select class="form-control input-sm" title="Inventariador a asignar" id="cbo_inventariador_asignar" name="cbo_inventariador_asignar">
                                    <c:forEach items="${cboInventariadorAsignar.items}" var="item" >
                                        <option <c:if test="${item.id == cboInventariadorAsignar.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6" id="infoResumen"></div>
                    </div>

                </form>

            </div>
        </div>

		<div class="row" id="div-panel-botones">
		
		    <div class="col-md-3">
		        <button id="btn_asignar" class="btn btn-md btn-primary btn-block" title="Asignar">ASIGNAR</button>
		    </div>
		
		    <div class="col-md-3">
		        <button id="btn_desasignar" class="btn btn-md btn-danger btn-block" title="Desasignar">DESASIGNAR</button>
		    </div>

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
		
        <div>&nbsp;</div>

	</div>
</div> 		

<div>&nbsp;</div>

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
        pageHiddenMessages();
    });

    function pageHiddenMessages() {
        if ( estaDefinido('#msgHidden') && getTrimValue('#msgHidden') != '' ) {
            showMensaje('Mensaje', getTrimValue('#msgHidden'), function() {
                $('#int_inventarioID').focus();
            });
        }
    }

    function pageInit() {

        initGrilla();
        initMask();
        initFechas();
    }

	function initFechas() {
	
	}

	function initMask() {
    	
    	var div = $( '#div-panel-buscar' );

    	var control = div.find( 'input[name="txt_codigoPatrimonial"]' );
        control.inputmask( "[*|| ]{0,12}", {placeholder: ''});
        control.prop( 'maxlength', 12 );
    }

    function pageEvents() {

        eventBuscar();
        eventAsignar();
        eventDesasignar();
        eventVer();
        eventChangeLocal();
        eventChangeArea();
        eventSeleccionarTodos();
    }

    function pagePostInit() {

    }

    function getRowsGrilla() {

        var table = $('#panel-asignacion-grilla').DataTable();

        // recoger todos los tr
        return table.rows().nodes();
    }

    function getSelectedChecksIDGrilla() {

        var rows = getRowsGrilla();
        var selectedCheckIDs = $('input[type=checkbox]:checked', rows);

        var ids = [];
        $.each(selectedCheckIDs, function(i, item) {
            ids.push( $(item).val() );
        });

        return ids;
    }

    function initGrilla() {

        initDG('#panel-asignacion-grilla', true, true, true, null, null);
        customEventSelectOnTR('#panel-asignacion-grilla');
        eventSeleccionarTodos();
    }

    function customEventSelectOnTR(tableID) {

        // seleccionar un input checkbox clickeando en todo el tr
        $(tableID + ' tbody').on('click', 'tr', function(e) {
            var input = $(this).find('input:checkbox');
            input.prop('checked', !input.prop('checked'));
        });

        $(tableID + ' input:checkbox').on('click', function(e) {
            e.stopPropagation();
        });

    }

    function cargarGrilla() {

        var paramsBuscar = $('#form-buscar').serialize();
		
        $('#div-load-grilla').load('asignacion-inventario.htm?action=buscarInventarioBien', paramsBuscar, function(response, status, xhr) {

            if (status == "error") { // xhr.statusText
                showMensaje('Mensaje', 'Ocurri\u00F3 un error inesperado: ERROR ' + xhr.status);
                return;
            }

            initGrilla();
            cargarInfoResumen();

        });
    }

    function cargarInfoResumen() {

        var infoExtraHidden = getTrimValue('#infoExtraHidden');

        if ( infoExtraHidden != '' ) {

            var infoResumen = $.parseJSON( infoExtraHidden );

            if ( estaDefinido( infoResumen ) ) {

                var html = '';

                html = html + '<span class="label label-primary">TOTAL DE BIENES</span> <span class="badge">' + $.trim(infoResumen.nroBienes) + '</span>';
                html = html + '<br />';
                html = html + '<span class="label label-danger">BIENES SIN ASIGNAR</span> <span class="badge">' + $.trim(infoResumen.nroSinAsignar) + '</span>';

                // si los bienes sin asignar son pocos los muestra
                if ( toNumero(infoResumen.nroBienes) > 0 && toNumero(infoResumen.nroSinAsignar) > 0 && toNumero(infoResumen.nroSinAsignar) < 5 ) {
                    html = html + '<br />';
                    html = html + '<span class="label label-warning">' + JSON.stringify(infoResumen.listaSinAsignar) + '</span>';
                }

                $( '#infoResumen' ).html( html );
            }

        }
    }

    function eventSeleccionarTodos() {

        $('.chk_seleccionar_todo').on('change', function(e) {

            e.preventDefault();

            var checked = $( this ).prop('checked');

            var rows = getRowsGrilla();

            $('input[type="checkbox"]', rows).prop('checked', checked);

        });
    }

    function eventAsignar() {

        $('#btn_asignar').on('click', function(e) {

            e.preventDefault();

            var selectedID = getSelectedChecksIDGrilla();
            var inventariador = $('#cbo_inventariador_asignar').val();

            if ( inventariador == '' || inventariador == '-1' ) {

                showMensaje('', 'Por favor seleccione un inventariador para asignar', function() {

                    $('#cbo_inventariador_asignar').focus();

                });

                return;
            }

            if (selectedID == null || selectedID.length == 0) {
                showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
                return null;
            }

            showConfirmation('', '\u00BFSeguro que desea asignar los bienes al inventariador?', function() {

                asignarAjax( selectedID, inventariador);

            });

        });
    }

    function asignarAjax(selectedID, inventariador) {

        $.ajax({
            url: 'asignacion-inventario.htm?action=asignar',
            cache: false,
            data: {
                'bienesID': JSON.stringify( selectedID ),
                'usuarioID': inventariador
            },
            async: true,
            type: 'POST',
            dataType: 'json',
            success: function(data) {

                if (handleErrorJson(data)) {
                    return;
                }


                $('#btn_buscar').trigger( 'click' );

            },
            error: function(data, textStatus, errorThrown) {
                handleError(data);
            }
        });

    }


    function eventDesasignar() {

        $('#btn_desasignar').on('click', function(e) {

            e.preventDefault();

            var selectedID = getSelectedChecksIDGrilla();

            if (selectedID == null || selectedID.length == 0) {
                showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
                return null;
            }

            showConfirmation('', '\u00BFSeguro que desea desasignar los bienes ?', function() {

                desasignarAjax( selectedID );

            });

        });
    }

    function desasignarAjax(selectedID) {

        $.ajax({
            url: 'asignacion-inventario.htm?action=desasignar',
            cache: false,
            data: {
                'bienesID': JSON.stringify( selectedID )
            },
            async: true,
            type: 'POST',
            dataType: 'json',
            success: function(data) {

                if (handleErrorJson(data)) {
                    return;
                }


                $('#btn_buscar').trigger( 'click' );

            },
            error: function(data, textStatus, errorThrown) {
                handleError(data);
            }
        });

    }
    
    
    function eventBuscar() {

        $('#btn_buscar').on('click', function(e) {

            e.preventDefault();

            var restore = estadoInputInicial('#div-panel-buscar');

            $.ajax({
                url: 'asignacion-inventario.htm?action=validarBuscar',
                cache: false,
                data: $('#form-buscar').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#div-panel-buscar', data, restore);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    console.log('data: ' + JSON.stringify(data));

                    cargarGrilla();

                },
                error: function(data, textStatus, errorThrown) {
                    handleError(data);
                }
            });

        });
    }

    function eventVer() {

        $('#btn_ver').on('click', function(e) {

            // TODO/FIXME: deberia ser un solo boton

            var selectedID = getSelectedChecksIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un registro de la lista');
                return null;
            }
			
            $(location).prop('href', 'asignacion-inventario.htm?action=verInventarioBien&inventarioBienID=' + selectedID);
        });
    }

    function eventChangeLocal() {

        // al cambiar el combo area se cargan sus locales asociados
        $("#cbo_local").change(function () {

            var formulario = "BUSCAR";

            var formURL = 'asignacion-inventario.htm?action=cargarAreas&formulario='+formulario;

            $.ajax({
                url: formURL,
                cache: false,
                data: $('#form-buscar').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#form-buscar', data, null);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    var items = data.dataJson.items;
                    $("#cbo_area").empty();
                    $.each(items, function (i, item) {
                        $('#cbo_area').append($('<option>', {
                            value: item.id,
                            text : item.label
                        }));
                    });

                },
                error: function(data, textStatus, errorThrown) {
                    handleError(data);
                }
            });


        });
    }

    function eventChangeArea() {

        // al cambiar el combo oficina se cargan sus locales asociados
        $("#cbo_area").change(function () {

            var formulario = "BUSCAR";

            var formURL = 'asignacion-inventario.htm?action=cargarOficinas&formulario='+formulario;

            $.ajax({
                url: formURL,
                cache: false,
                data: $('#form-buscar').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#form-buscar', data, null);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    var items = data.dataJson.items;
                    $("#cbo_oficina").empty();
                    $.each(items, function (i, item) {
                        $('#cbo_oficina').append($('<option>', {
                            value: item.id,
                            text : item.label
                        }));
                    });

                },
                error: function(data, textStatus, errorThrown) {
                    handleError(data);
                }
            });


        });
    }

</script>