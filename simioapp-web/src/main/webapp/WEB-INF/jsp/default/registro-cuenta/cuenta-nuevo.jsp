<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> NUEVO CUENTA </strong> </div>
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
		                        <label class="control-label">Cuenta</label>
		                        <input name="txt_cuenta" type="text" title="Cuenta" maxlength="11" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Denominaci&oacute;n</label>
		                        <textarea name="txt_denomina" title="Denominaci&oacute;n" maxlength="100" class="form-control input-sm text-uppercase" rows="4"></textarea>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Tipo Cuenta</label>
		                        <select class="form-control input-sm text-uppercase" title="Tipo Cuenta" name="txt_tipoCta">
	                                <c:forEach items="${cboTipoCuenta.items}" var="item" >
	                                    <option <c:if test="${item.id == cboTipoCuenta.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                </c:forEach>
	                            </select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Flag Cuenta</label>
		                        <select class="form-control input-sm text-uppercase" title="Flag Cuenta" name="txt_flagCta">
	                                <c:forEach items="${cboFlagCuenta.items}" var="item" >
	                                    <option <c:if test="${item.id == cboFlagCuenta.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                </c:forEach>
	                            </select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Uso Cuenta</label>
		                        <select class="form-control input-sm text-uppercase" title="Uso Cuenta" name="txt_usoCta">
	                                <c:forEach items="${cboUsoCuenta.items}" var="item" >
	                                    <option <c:if test="${item.id == cboUsoCuenta.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
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
		        <button id="btn_guardar" class="btn btn-md btn-primary btn-block" title="Guardar"><i class="fa fa-floppy-o"></i>&nbsp; GUARDAR</button>
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
    	var control = null;

        control = div.find( 'input[name="txt_cuenta"]' );
        control.inputmask( "[*|| ]{0,11}", {placeholder: ''});
        control.prop( 'maxlength', 11 );

        control = div.find( 'input[name="txt_denomina"]' );
        control.inputmask( "[*|| ||\"]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );
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
                    url: 'registro-cuenta.htm?action=guardarNuevo',
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

            $(location).prop('href', 'registro-cuenta.htm?action=mostrarBuscar');
        });
    }


</script>