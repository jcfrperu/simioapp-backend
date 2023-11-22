<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> NUEVO PAR&Aacute;METRO </strong> </div>
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
                                <label class="control-label">Nombre</label>
                                <input name="txt_nombre" type="text" title="Nombre" maxlength="50" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Tipo Valor</label>
                                <select class="form-control input-sm" title="Tipo Valor" name="cbo_tipoValor">
                                    <c:forEach items="${cboTipoValor.items}" var="item" >
                                        <option <c:if test="${item.id == cboTipoValor.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>     
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Valor</label>
                                <input name="txt_valor" type="text" title="Valor" maxlength="50" class="form-control input-sm">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Descripci&oacute;n</label>
                                <textarea name="txt_descripcion" maxlength="200" title="Descripci&oacute;n" class="form-control input-sm text-uppercase" rows="4"></textarea>
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

		<div> &nbsp; </div>
				
	</div>
</div>        

<script>

    $(document).ready(function() {
        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

        $('textarea').css('resize','none');
        
        initMask();

    }
    
	function initMask() {
    	
    	// componente input mask
    	// mas info: https://github.com/RobinHerbots/jquery.inputmask
    	
    	var div = $( '#div-panel-registro' );

        var control = div.find( 'input[name="int_parametroID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 )

//        control = div.find( 'input[name="txt_nombre"]' );
//        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
//        control.prop( 'maxlength', 50 )

        control = div.find( 'input[name="txt_valor"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 )

        control = div.find( 'input[name="txt_descripcion"]' );
        control.inputmask( "[*|| ]{0,200}", {placeholder: ''});
        control.prop( 'maxlength', 200 )

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
                    url: 'registro-parametro.htm?action=guardarNuevo',
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

            $(location).prop('href', 'registro-parametro.htm?action=mostrarBuscar');
        });
    }


</script>