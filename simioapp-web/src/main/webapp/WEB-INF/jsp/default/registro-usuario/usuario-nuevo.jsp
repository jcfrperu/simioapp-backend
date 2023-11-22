<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
    <div class="col-md-12">
        <h4 class="page-header">
            <strong> NUEVO USUARIO </strong> <br />
            <div class="css3gradient"></div>
        </h4>
    </div>
</div>

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
                        <label class="control-label">Nombres</label>
                        <input name="txt_nombres" type="text" title="Nombres" maxlength="100" class="form-control input-sm text-uppercase">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
			            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">ApellidoPaterno</label>
                        <input name="txt_apellidoPaterno" type="text" title="ApellidoPaterno" maxlength="50" class="form-control input-sm text-uppercase">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
			            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">ApellidoMaterno</label>
                        <input name="txt_apellidoMaterno" type="text" title="ApellidoMaterno" maxlength="50" class="form-control input-sm text-uppercase">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
			            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Clave</label>
                        <input name="txt_clave" type="text" title="Clave" maxlength="250" class="form-control input-sm text-uppercase">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
			            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">Usuariocol</label>
                        <input name="txt_usuariocol" type="text" title="Usuariocol" maxlength="45" class="form-control input-sm text-uppercase">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
			            
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">ClaveAes</label>
                        <input name="byte_claveAes" type="text" title="ClaveAes" maxlength="50" class="form-control input-sm text-uppercase">
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
	
	    // inicializar todos los campos fecha
	    /*
    	$('#divCampoPrueba').datetimepicker({
    		locale: "es",
		    format: "DD/MM/YYYY"
    	});
    	*/
    	
    	// ejemplo html del control
    	/*
    	<div class='input-group date' id='divCampoPrueba'>
        <input type='text' class="form-control" name="fec_campoPrueba" />
        	<span class="input-group-addon">
            	<span class="glyphicon glyphicon-calendar"></span>
        	</span>
    	</div>
    	*/    	
	
	}    

	function initMask() {
    	
    	// componente input mask
    	// más info: https://github.com/RobinHerbots/jquery.inputmask
    	
    	var div = $( '#div-panel-registro' );
    	var control = null;
    	
        control = div.find( 'input[name="txt_usuarioID"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 )

        control = div.find( 'input[name="int_entidadID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 )

        control = div.find( 'input[name="txt_nombres"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 )

        control = div.find( 'input[name="txt_apellidoPaterno"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 )

        control = div.find( 'input[name="txt_apellidoMaterno"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 )

        control = div.find( 'input[name="txt_clave"]' );
        control.inputmask( "[*|| ]{0,250}", {placeholder: ''});
        control.prop( 'maxlength', 250 )

        control = div.find( 'input[name="txt_usuariocol"]' );
        control.inputmask( "[*|| ]{0,45}", {placeholder: ''});
        control.prop( 'maxlength', 45 )

        control = div.find( 'input[name="byte_claveAes"]' );
        control.inputmask( "9{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 )


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
                    url: 'registro-usuario.htm?action=guardarNuevo',
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

            $(location).prop('href', 'registro-usuario.htm?action=mostrarBuscar');
        });
    }


</script>