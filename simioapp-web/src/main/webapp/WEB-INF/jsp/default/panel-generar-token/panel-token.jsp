<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong> GENERAR TOKEN OFFLINE </strong> </div>
    <div class="panel-body">
        
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>
		
		<div>&nbsp;</div>
		
		<div class="row" id="div-panel-registro">
		    <div class="col-md-12">
		
		        <form id="form-registro" method="POST" role="form">
		                        
		            <div class="row">
		                <div class="col-md-4">
		                    <div class="form-group">
		                        <label class="control-label">Usuario</label>
		                        <input readonly name="txt_usuario" type="text" title="Usuario" class="form-control input-sm text-uppercase" value="${registro.usuario}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>

		            <div class="row">
		                <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Entidad</label>
                                <select class="form-control input-sm" title="Entidad" name="txt_entidad">
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
								<select class="form-control input-sm" title="Lista de Inventarios Abiertos" name="txt_inventario">
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
								<label class="control-label">D&iacute;as validez</label>
								<input name="txt_diasValidez" type="number" title="D&iacute;s validez" min="0" max="29" class="form-control input-sm" value="2">
								<span class="help-block"></span>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Horas validez</label>
								<input name="txt_horasValidez" type="number" title="Horas validez" min="0" max="23" class="form-control input-sm" value="12">
								<span class="help-block"></span>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Minutos validez</label>
								<input name="txt_minutosValidez" type="number" title="Minutos validez" min="1" max="59" class="form-control input-sm" value="0"> 
								<span class="help-block"></span>
							</div>
						</div>
					</div>

				</form>
		
		    </div>
		</div>
		
		<div class="row" id="div-panel-botones">
		    <div class="col-md-4 col-md-offset-4">
		        <button id="btn_generarToken" title="Generar Token Offline" class="btn btn-md btn-primary btn-block">GENERAR TOKEN OFFLINE</button>
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
		
		<!-- se usa para redirigir despues de generar el token (debe ser post porque el token tiene muchos caracteres)  -->
		<div id="hiddenForms">
			<form id="form-siguiente" action="descarga.htm" method="POST" role="form">
				<input type="hidden" name="action" value="mostrarPanelDescarga">
				<input type="hidden" name="usuario" value="">
				<input type="hidden" name="entidad" value="">
				<input type="hidden" name="inventario" value="">
				<input type="hidden" name="token" value="">
			</form>
		</div>
		
        <div>&nbsp;</div>
				
	</div>
</div>

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

        initMask();
        initFechas();
        
        $('#hiddenForms').hide();
    }

	function initFechas() {
	
	}

	function initMask() {
    	
    	var div = $( '#div-panel-registro' );

        var control = div.find( 'input[name="txt_diasValidez"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 2 );
        
        control = div.find( 'input[name="txt_horasValidez"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 2 );
        
        control = div.find( 'input[name="txt_minutosValidez"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 2 );        

    }

    function pageEvents() {

    	eventGenerarToken();
    }

    function pagePostInit() {

    }
 
    function eventGenerarToken() {

        $('#btn_generarToken').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Generar Token', '\u00BFSeguro que desea generar el token?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                $.ajax({
                    url: 'generacion-token.htm?action=generarToken',
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

                    	habilitarControl('#btn_generarToken', false);
                        habilitarControles('#div-panel-registro', false);
                        
                        var rpta = data.dataJson;
                        
                    	var formSiguiente = $('#form-siguiente');
                    	
                    	formSiguiente.find( 'input[name="usuario"]' ).val( rpta.usuario );
                    	formSiguiente.find( 'input[name="entidad"]' ).val( rpta.entidad );
                    	formSiguiente.find( 'input[name="inventario"]' ).val( rpta.inventario );
                    	formSiguiente.find( 'input[name="token"]' ).val( rpta.token );

                        showMensaje('Mensaje', "Token generado con \u00E9xito", function() {
							
							// ir a panel de descargas                        	
                        	$('#form-siguiente').submit();
                        	
                        });

                    },
                    error: function(data, textStatus, errorThrown) {
                        handleError(data);
                    }
                });

            });

        });
    }

</script>
