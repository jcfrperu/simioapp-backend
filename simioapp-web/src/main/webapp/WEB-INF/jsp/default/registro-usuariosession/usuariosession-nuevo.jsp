<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> NUEVO USUARIOSESSION </strong> </div>
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
		                        <label class="control-label">EntidadID</label>
		                        <input name="int_entidadID" type="text" title="EntidadID" maxlength="10" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">UltimoLogin</label>
		                        <input name="fec_ultimoLogin" type="text" title="UltimoLogin" maxlength="10" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">UltimoCierre</label>
		                        <input name="fec_ultimoCierre" type="text" title="UltimoCierre" maxlength="10" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">UltimaAcci&oacute;n</label>
		                        <input name="fec_ultimaAccion" type="text" title="UltimaAcci&oacute;n" maxlength="10" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">MinutosValidez</label>
		                        <input name="int_minutosValidez" type="text" title="MinutosValidez" maxlength="10" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Estado</label>
		                        <input name="txt_estado" type="text" title="Estado" maxlength="3" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">IndPoliticaInactividad</label>
		                        <input name="txt_indPoliticaInactividad" type="text" title="IndPoliticaInactividad" maxlength="1" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DatosIpLogin</label>
		                        <input name="txt_datosIpLogin" type="text" title="DatosIpLogin" maxlength="100" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DatosIpAcci&oacute;n</label>
		                        <input name="txt_datosIpAccion" type="text" title="DatosIpAcci&oacute;n" maxlength="100" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">IndPoliticaIntentos</label>
		                        <input name="txt_indPoliticaIntentos" type="text" title="IndPoliticaIntentos" maxlength="1" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroIntentos</label>
		                        <input name="int_numeroIntentos" type="text" title="NumeroIntentos" maxlength="10" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NombresUsuario</label>
		                        <input name="txt_nombresUsuario" type="text" title="NombresUsuario" maxlength="100" class="form-control input-sm text-uppercase">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
					            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ApellidosUsuario</label>
		                        <input name="txt_apellidosUsuario" type="text" title="ApellidosUsuario" maxlength="100" class="form-control input-sm text-uppercase">
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
    	
        control = div.find( 'input[name="txt_usuarioID"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="int_entidadID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="fec_ultimoLogin"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="fec_ultimoCierre"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="fec_ultimaAccion"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="int_minutosValidez"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_estado"]' );
        control.inputmask( "[*|| ]{0,3}", {placeholder: ''});
        control.prop( 'maxlength', 3 );

        control = div.find( 'input[name="txt_indPoliticaInactividad"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_datosIpLogin"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_datosIpAccion"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_indPoliticaIntentos"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="int_numeroIntentos"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_nombresUsuario"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_apellidosUsuario"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
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
                    url: 'registro-usuariosession.htm?action=guardarNuevo',
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

            $(location).prop('href', 'registro-usuariosession.htm?action=mostrarBuscar');
        });
    }


</script>