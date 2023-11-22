<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> NUEVO CLAVE </strong> </div>
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
		                        <label class="control-label">ClaveMetodo</label>
		                        <input name="txt_claveMetodo" type="text" title="ClaveMetodo" maxlength="50" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ClaveParams</label>
		                        <input name="txt_claveParams" type="text" title="ClaveParams" maxlength="5000" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Clave</label>
		                        <input name="txt_clave" type="text" title="Clave" maxlength="5000" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ClaveBytes</label>
		                        <input name="byte_claveBytes" type="text" title="ClaveBytes" maxlength="50" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ClaveBytesLength</label>
		                        <input name="int_claveBytesLength" type="text" title="ClaveBytesLength" maxlength="10" class="form-control input-sm text-uppercase">
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
    	
        control = div.find( 'input[name="int_claveID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_claveMetodo"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="txt_claveParams"]' );
        control.inputmask( "[*|| ]{0,5000}", {placeholder: ''});
        control.prop( 'maxlength', 5000 );

        control = div.find( 'input[name="txt_clave"]' );
        control.inputmask( "[*|| ]{0,5000}", {placeholder: ''});
        control.prop( 'maxlength', 5000 );

        control = div.find( 'input[name="byte_claveBytes"]' );
        control.inputmask( "9{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="int_claveBytesLength"]' );
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

        $('#btn_guardar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Guardar Registro', '\u00BFSeguro que desea guardar el registro?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                $.ajax({
                    url: 'registro-clave.htm?action=guardarNuevo',
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

            $(location).prop('href', 'registro-clave.htm?action=mostrarBuscar');
        });
    }


</script>