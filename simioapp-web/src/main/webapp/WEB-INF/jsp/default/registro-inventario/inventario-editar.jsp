<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong> EDITAR CABECERA INVENTARIO </strong> </div>
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
		                        <input readonly name="int_inventarioID" type="text" title="C&oacute;digo" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.inventarioID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Entidad</label>
                                <input name="int_entidadID" type="hidden" value="${registro.entidadID}">
                                <select disabled class="form-control input-sm" title="Entidad" name="int_entidadID_no_usado">
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
		                        <label class="control-label">Nombre</label>
		                        <input name="txt_nombre" type="text" title="Nombre" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.nombre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Estado</label>
								<select class="form-control input-sm" title="Entidad" name="cbo_estadoInventario">
									<c:forEach items="${cboEstadoInventario.items}" var="item" >
										<option <c:if test="${item.id == cboEstadoInventario.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
                                <span class="help-block"></span>
                            </div>
                        </div>
		            </div>

		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Fecha Apertura</label>
                                <div class='input-group date' id='div_fechaApertura'>
                                    <input name="fec_fechaApertura" type="text" title="Fecha Apertura" maxlength="10" class="form-control input-sm text-uppercase" value='<fmt:formatDate pattern="dd/MM/yyyy" value="${registro.fechaApertura}" />'>
                                    <span class="input-group-addon">
            	                        <span class="glyphicon glyphicon-calendar"></span>
        	                        </span>
                                </div>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Fecha Cierre</label>
                                <div class='input-group date' id='div_fechaCierre'>
                                    <input name="fec_fechaCierre" type="text" title="Fecha Cierre" maxlength="10" class="form-control input-sm text-uppercase" value='<fmt:formatDate pattern="dd/MM/yyyy" value="${registro.fechaCierre}" />'>
                                    <span class="input-group-addon">
            	                        <span class="glyphicon glyphicon-calendar"></span>
        	                        </span>
                                </div>
								<span class="help-block"></span>
                            </div>
                        </div>
		            </div>

					<!--
					NOTA: el campo indDel se esta ignorando explicitamente, porque si se borra deberian borrarse tambien los bienes de la tabla inventario_bien e apertura_bien
					-->

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
    });

    function pageInit() {

        $('textarea').css('resize','none');
        
        initMask();
        initFechas();

    }
    
	function initFechas() {

        $( '#div_fechaApertura' ).datetimepicker({
            locale: "es",
            format: "DD/MM/YYYY"
        });

        $( '#div_fechaCierre' ).datetimepicker({
            locale: "es",
            format: "DD/MM/YYYY"
        });

	}    

	function initMask() {
    	
    	var div = $( '#div-panel-registro' );

        var control = div.find( 'input[name="int_inventarioID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_nombre"]' );
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

        $('#btn_editar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Editar Registro', '\u00BFSeguro que desea editar el registro?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                $.ajax({
                    url: 'registro-inventario.htm?action=guardarEditar',
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

    function eventCancelar() {

        $('#btn_cancelar').on('click', function(e) {

            $(location).prop('href', 'registro-inventario.htm?action=mostrarBuscar');
        });
    }


</script>