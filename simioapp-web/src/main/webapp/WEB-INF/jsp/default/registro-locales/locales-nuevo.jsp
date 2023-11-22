<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> NUEVO LOCAL </strong> </div>
	<div class="panel-body">
        
        <div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">
                    
                    <fieldset>
                        <legend>DATOS GENERALES</legend>
                        
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label">Entidad</label>
                                    <select class="form-control input-sm" title="Entidad" name="txt_entidadID">
	                                    <c:forEach items="${cboEntidad.items}" var="item" >
	                                        <option <c:if test="${item.id == cboEntidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                                    
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label class="control-label">Nombre Local</label>
                                    <input name="txt_nombreLocal" type="text" title="Nombre Local" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Propiedad</label>
                                    <select class="form-control input-sm" title="Propiedad" name="txt_flgPropie" id="txt_flgPropie">
	                                    <c:forEach items="${cboTipoPropiedad.items}" var="item" >
	                                        <option <c:if test="${item.id == cboTipoPropiedad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            
                        	<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">&Aacute;rea</label>
                                    <input name="dbl_ctdArea" type="text" title="&Aacute;rea" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Unidad de Medida</label>
                                    <select class="form-control input-sm" title="Unidad de Medida" name="txt_flgUm">
	                                    <c:forEach items="${cboUnidadMedida.items}" var="item" >
	                                        <option <c:if test="${item.id == cboUnidadMedida.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                         	<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Tipo Direcci&oacute;n</label>
                                    <select class="form-control input-sm" title="Tipo de Direcci&oacute;n" name="txt_flgTipovi">
	                                    <c:forEach items="${cboTipoDireccion.items}" var="item" >
	                                        <option <c:if test="${item.id == cboTipoDireccion.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="control-label">Direcci&oacute;n</label>
                                    <input name="txt_direccion" type="text" title="Direcci&oacute;n" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
							<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">N&deg;</label>
                                    <input name="txt_numeroMun" type="text" title="N&deg;" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Mz</label>
                                    <input name="txt_dscMz" type="text" title="Mz" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                             <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Lote</label>
                                    <input name="txt_dscLote" type="text" title="Lote" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
						
						<div class="row">
							<div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Departamento</label>
	                                <select class="form-control input-sm" title="Local" id="cbo_departamento" name="cbo_departamento">
	                                	<c:forEach items="${cboDepartamento.items}" var="item" >
	                                        <option <c:if test="${item.id == cboDepartamento.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
	                                <span class="help-block"></span>
	                            </div>
	                        </div>
	                        <div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Provincia</label>
	                                <select class="form-control input-sm" title="Area" id="cbo_provincia" name="cbo_provincia">
	                                </select>
	                                <span class="help-block"></span>
                            	</div>
	                        </div>
	                        <div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Distrito</label>
	                                <select class="form-control input-sm" title="Area" id="cbo_distrito" name="cbo_distrito">
	                                </select>
	                                <span class="help-block"></span>
                            	</div>
	                        </div>
						</div>
						
                        <div class="row" style="display: none;">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Ubigeo</label>
                                    <input name="txt_codigoUbigeo" id="txt_codigoUbigeo" type="text" title="Ubigeo" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                    </fieldset>
                    
                    <div> &nbsp; </div>
                    
                    <fieldset id="infoContable" name="infoContable">
                        <legend>INFORMACI&Oacute;N CONTABLE</legend>
                        
                        <div class="row">
                            <div class="col-md-6">
                            	<label class="control-label">Tipo Cuenta</label>
								<select class="form-control input-sm" title="Tipo Cuenta" id="cbo_tipo_cuenta" name="cbo_tipo_cuenta">
                                    <c:forEach items="${cboTipoCuenta.items}" var="item" >
                                        <option <c:if test="${item.id == cboTipoCuenta.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
	                            </select>
								<span class="help-block"></span>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Tipo de Moneda</label>
                                    <select class="form-control input-sm" title="Tipo de Moneda" name="txt_flgTipomo">
	                                    <c:forEach items="${cboTipoMoneda.items}" var="item" >
	                                        <option <c:if test="${item.id == cboTipoMoneda.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                                    
                        <div class="row">
                        	<div class="col-md-9">
	                        	<label class="control-label">Cuenta</label>
								<select class="form-control input-sm" title="Cuenta" id="cbo_cuenta" name="cbo_cuenta">
                                    <c:forEach items="${cboCuentas.items}" var="item" >
                                        <option <c:if test="${item.id == cboCuentas.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
	                            </select>
								<span class="help-block"></span>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label">Valor Contable</label>
                                    <input name="dbl_ctdValor" type="text" title="Valor Contable" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                                    
				</fieldset>
				
				<div> &nbsp; </div>
				
                <fieldset id="infoRegistral" name="infoRegistral">
                        <legend>INFORMACI&Oacute;N REGISTRAL</legend>		
                        
                        <div class="row">
                      	  	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Oficina Registral</label> 
                                    <select class="form-control input-sm" title="Oficina Registral" name="txt_flgTipore">
	                                    <c:forEach items="${cboOficinaRegistral.items}" var="item" >
	                                        <option <c:if test="${item.id == cboOficinaRegistral.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                             <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Tomo</label>
                                    <input name="txt_dscTomo" type="text" title="Tomo" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Fojas</label>
                                    <input name="int_dscFojas" type="text" title="Fojas" maxlength="10" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Asiento</label>
                                    <input name="txt_dscAsient" type="text" title="Asiento" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">C&oacute;digo Predio</label>
                                    <input name="txt_codigoPredio" type="text" title="C&oacute;digo Predio" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Partida Electr&oacute;nica</label>
                                    <input name="txt_dscPelect" type="text" title="Partida Electr&oacute;nica" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Registro SINABIP</label>
                                    <input name="txt_dscAsinab" type="text" title="Registro SINABIP" maxlength="500" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Propiedad Registral</label>
                                    <input name="txt_dscPropie" type="text" title="Propiedad Registral" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                       	 	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="cont	rol-label">Beneficiario</label>
                                    <input name="txt_dscBenefi" type="text" title="Beneficiario" maxlength="50" class="form-control input-sm text-uppercase">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                    </fieldset>
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
    
        <div> &nbsp; </div>
				
	</div>
</div>    

<script>

    $(document).ready(function() {
        pageInit();
        pageEvents();
        pagePostInit();
        cargarCuenta();
        changeFieldSet();
        cargarProvincia();
        cargarDistrito();
        obtenerUbigeo();
        
        $('#infoContable').prop('disabled',true);
		$('#infoRegistral').prop('disabled',true);
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
                    url: 'registro-locales.htm?action=guardarNuevo',
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

    function eventCancelar() {

        $('#btn_cancelar').on('click', function(e) {

            $(location).prop('href', 'registro-locales.htm?action=mostrarBuscar');
        });
    }
    
	function cargarCuenta(){
    	
    	$("#cbo_tipo_cuenta").change(function () {

    		var formulario = "NUEVO";
    		
    		var formURL = 'registro-locales.htm?action=cargarCuentas&formulario='+formulario;
    		
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
                            estadoInputError('#div-panel-registro', data, restore);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    console.log('data: ' + JSON.stringify(data.dataJson.items));
                    
                    var items = data.dataJson.items;
                    $("#cbo_cuenta").empty();
            		$.each(items, function (i, item) {
        		    	$('#cbo_cuenta').append($('<option>', { 
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
	
	function changeFieldSet(){
    	
    	$("#txt_flgPropie").change(function () {
    		
    		var propiedadSeleccionada = $('#txt_flgPropie').val();
    		
    		if(propiedadSeleccionada == "ESTATAL"){
    			
    			$('#infoContable').prop('disabled',false);
    			$('#infoRegistral').prop('disabled',false);
    			
    		}else{
    			
    			$('#infoContable').prop('disabled',true);
    			$('#infoRegistral').prop('disabled',true);
    			
    		}
    		
    		});
    }
	
	function cargarProvincia(){
    	
    	$("#cbo_departamento").change(function () {

    		var formulario = "NUEVO";
    		
    		var formURL = 'registro-locales.htm?action=cargarProvincia&formulario='+formulario;
    		
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
                            estadoInputError('#div-panel-registro', data, restore);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    console.log('data: ' + JSON.stringify(data.dataJson.items));
                    
                    var items = data.dataJson.items;
                    $("#cbo_provincia").empty();
					$("#cbo_distrito").empty();
            		$.each(items, function (i, item) {
        		    	$('#cbo_provincia').append($('<option>', { 
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
	
	function cargarDistrito(){
    	
    	$("#cbo_provincia").change(function () {

    		var formulario = "NUEVO";
    		
    		var formURL = 'registro-locales.htm?action=cargarDistrito&formulario='+formulario;
    		
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
                            estadoInputError('#div-panel-registro', data, restore);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    console.log('data: ' + JSON.stringify(data.dataJson.items));
                    
                    var items = data.dataJson.items;
                    $("#cbo_distrito").empty();
            		$.each(items, function (i, item) {
        		    	$('#cbo_distrito').append($('<option>', { 
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
	
	function obtenerUbigeo(){
    	
    	$("#cbo_distrito").change(function () {
    		
            var distritoSeleccionado = $('#cbo_distrito').val();

            $('#txt_codigoUbigeo').val(distritoSeleccionado);

        });
    }


</script>
