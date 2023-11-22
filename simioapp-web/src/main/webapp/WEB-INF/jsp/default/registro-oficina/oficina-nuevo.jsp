<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> NUEVA OFICINA </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>
                
        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Abreviatura Oficina</label>
                                <input name="txt_abreviaturaOficina" type="text" title="Abreviatura Oficina" maxlength="15" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
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
                        <div class="col-md-6">
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
                         <div class="col-md-6">
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
                    </div>
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Nombre Oficina</label>
                                <input name="txt_nombreOficina" type="text" title="Nombre Oficina" maxlength="150" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Piso Oficina</label>
                                <input name="txt_pisoOficina" type="text" title="Piso Oficina" maxlength="15" class="form-control input-sm text-uppercase">
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
                <button id="btn_guardar" class="btn btn-sm btn-primary btn-block" title="Guardar"><i class="fa fa-floppy-o"></i>&nbsp; GUARDAR</button>
            </div>
			<div class="col-md-2">
                <button id="btn_cancelar" class="btn btn-sm btn-primary btn-block .btnCancelar">&nbsp; CANCELAR</button>
            </div>
        </div>

        <div> &nbsp; </div>
				
	</div>
</div>

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

<script>

    $(document).ready(function() {
        pageInit();
        pageEvents();
        pagePostInit();
        cargarArea();
    });

    function pageInit() {

        $('textarea').css('resize','none');

    }

    function pagePostInit() {

    }

    function pageEvents() {

        eventGuardar();
        eventCancelar();
    }

    function eventGuardar() {

        $('#btn_guardar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Guardar Registro', '\u00BFSeguro que desea guardar el registro?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                $.ajax({
                    url: 'registro-oficina.htm?action=guardarNuevo',
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

                        habilitarControl('#btn_guardar', false);
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

    		var formulario = "NUEVO";
    		
    		var formURL = 'registro-oficina.htm?action=cargarAreas&formulario='+formulario;
    		
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

    function eventCancelar() {

        $('#btn_cancelar').on('click', function(e) {

            $(location).prop('href', 'registro-oficina.htm?action=mostrarBuscar');
        });
    }


</script>
