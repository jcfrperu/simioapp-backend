<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> EDITAR EMPLEADO UBICACI&Oacute;N </strong> </div>
    <div class="panel-body">

        <div>&nbsp;</div>

		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>

		<div class="row" id="div-panel-registro">
		    <div class="col-md-12">

		        <form id="form-registro" method="POST" role="form">

		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">C&oacute;digo</label>
		                        <input readonly name="int_empleadoUbicacionID" type="text" title="C&oacute;digo" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.empleadoUbicacionID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Empleado</label>
                                <select class="form-control input-sm" title="Empleado" id="cbo_empleado" name="cbo_empleado">
                                    <c:forEach items="${cboEmpleado.items}" var="item" >
                                        <option <c:if test="${item.id == cboEmpleado.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
		            </div>

		            <div class="row">
		                <div class="col-md-12">
		                    <div class="form-group">
		                        <label class="control-label">Entidad</label>
								<select class="form-control input-sm" title="Entidad" id="cboEntidad" name="cboEntidad">
									<c:forEach items="${cboEntidad.items}" var="item" >
										<option <c:if test="${item.id == cboEntidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>

		            <div class="row">
		                <div class="col-md-4">
		                    <div class="form-group">
		                        <label class="control-label">Local</label>
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
                                <label class="control-label">&Aacute;rea</label>
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
                                <label class="control-label">Oficina</label>
                                <select class="form-control input-sm" title="&Aacute;rea" id="cbo_oficina" name="cbo_oficina">
                                    <c:forEach items="${cboOficina.items}" var="item" >
                                        <option <c:if test="${item.id == cboOficina.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
		            </div>

		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Tipo Asociaci&oacute;n</label>
								<select class="form-control input-sm" title="Tipo Asociaci&oacute;n" name="txt_tipoAsociacion">
									<c:forEach items="${cboTipoAsociacion.items}" var="item" >
										<option <c:if test="${item.id == cboTipoAsociacion.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Estado Registro</label>
                                <select class="form-control input-sm" title="Estado Registro" name="cbo_indDel">
                                    <c:forEach items="${cboEstados.items}" var="item" >
                                        <option <c:if test="${item.id == cboEstados.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
		            </div>

		        </form>

		    </div>
		</div>

		<div>&nbsp;</div>

		<div class="row" id="div-panel-botones">

		    <div class="col-md-2 col-md-offset-4">
		        <button id="btn_editar" class="btn btn-md btn-primary btn-block" title="Guardar"><i class="fa fa-floppy-o"></i>&nbsp; GUARDAR</button>
		    </div>

		    <div class="col-md-2">
		        <button id="btn_cancelar" class="btn btn-md btn-primary btn-block .btnCancelar" title="Cancelar">&nbsp; CANCELAR</button>
		    </div>

		</div>

		<div>&nbsp;</div>

		<!-- popups -->
		<c:import url="/admin.htm">
		    <c:param name="action" value="cargarPage"/>
		    <c:param name="_page" value="popups"/>
		    <c:param name="_module" value=""/>
		    <c:param name="_template" value="default"/>
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
        cargarArea();
        cargarOficina();
    });

    function pageInit() {

        $('textarea').css('resize','none');

        initMask();
        initFechas();
    }

	function initFechas() {

	}

	function initMask() {

    	var div = $( '#div-panel-registro' );

        var control = div.find( 'input[name="int_empleadoUbicacionID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

    }

    function pagePostInit() {

    }

    function pageEvents() {

        eventGuardar();
        eventCancelar();
    }

    function eventGuardar() {

        $('#btn_editar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Editar Registro', '\u00BFSeguro que desea editar el registro?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                $.ajax({
                    url: 'registro-ubicacion-empleado.htm?action=guardarEditar',
                    cache: false,
                    data: $('#form-registro').serialize(),
                    async: true,
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {

                        if (huboErrorJson(data)) {

                            if (huboErrorValidacionJson(data)) {
                                estadoInputError('#div-panel-registro', data, restore);
                                return;
                            }

                            handleErrorJson(data);
                            return;
                        }

                        console.log('data: ' + JSON.stringify(data));

                        showMensaje('Mensaje', "La operaci\u00F3n se realiz\u00F3 con \u00E9xito");

                        habilitarControl('#btn_editar', false);
                        habilitarControles('#div-panel-registro', false);

                        $('#btn_cancelar').html( 'REGRESAR' );

                    },
                    error: function(data, textStatus, errorThrown) {
                        handleError(data);
                    }
                });

            });

        });
    }

    function cargarArea(){

        $("#cbo_local").change(function () {

            var formulario = "EDITAR";

            var formURL = 'registro-ubicacion-empleado.htm?action=cargarAreas&formulario='+formulario;

            $.ajax({
                url: formURL,
                cache: false,
                data: $('#form-registro').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#div-panel-registro', data, null);
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

    function cargarOficina(){

        $("#cbo_area").change(function () {

            var formulario = "BUSCAR";

            var formURL = 'registro-ubicacion-empleado.htm?action=cargarOficinas&formulario='+formulario;

            $.ajax({
                url: formURL,
                cache: false,
                data: $('#form-registro').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#div-panel-registro', data, null);
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
    function eventCancelar() {

        $('#btn_cancelar').on('click', function(e) {

            $(location).prop('href', 'registro-ubicacion-empleado.htm?action=mostrarBuscar');
        });
    }


</script>