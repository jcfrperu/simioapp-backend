<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> EDITAR EMPLEADO </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">
                    <input name="int_empleadoID" type="hidden" value="${registro.empleadoID}">
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Empleado</label>
                                <input name="txt_codigo" type="text" title="C&oacute;digo Empleado" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.codigo}">
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
                                <label class="control-label">Tipo documento</label>
                                <select class="form-control input-sm" title="tipodoc" id="cbo_tipodoc" name="cbo_tipodoc">
                                    <c:forEach items="${cboTipoDoc.items}" var="item" >
                                        <option <c:if test="${item.id == cboTipoDoc.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">N&uacute;mero documento</label>
                                <input name="txt_numeroDocIdentPersonal" type="text" title="N&uacute;mero documento" maxlength="15" class="form-control input-sm text-uppercase" value="${registro.numeroDocIdentPersonal}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="control-label">Nombres</label>
                                <input name="txt_nombres" type="text" title="Nombres" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.nombres}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Apellido Paterno</label>
                                <input name="txt_apellidoPaterno" type="text" title="Apellido Paterno" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.apellidoPaterno}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Apellido Materno</label>
                                <input name="txt_apellidoMaterno" type="text" title="Apellido Materno" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.apellidoMaterno}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Modalidad de contrato</label>
                                <select class="form-control input-sm" title="Modalidad de Contrato" id="cbo_modalidad" name="cbo_modalidad">
                                    <c:forEach items="${cboModalidad.items}" var="item" >
                                        <option <c:if test="${item.id == cboModalidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
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
                <button id="btn_editar" class="btn btn-sm btn-primary btn-block" title="Guardar"><i class="fa fa-floppy-o"></i>&nbsp; GUARDAR</button>
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
    }

    function pagePostInit() {

    	initMask();
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
                    url: 'registro-empleado.htm?action=guardarEditar',
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

            $(location).prop('href', 'registro-empleado.htm?action=mostrarBuscar');
        });
    }

	function initMask() {
    	
    	var div = $( '#div-panel-registro' );

        var control = div.find( 'input[name="txt_codigo"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});//acepta alfanumericos
        control.prop( 'maxlength', 5 );

        control = div.find( 'input[name="txt_numeroDocIdentPersonal"]' );
        control.inputmask( "9{0,15}", {placeholder: ''});
        control.prop( 'maxlength', 15 ); // el tamanio del documento de identidad real se valida lado servidor

        control = div.find( 'input[name="txt_nombres"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});//acepta solo texto
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_apellidoPaterno"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});//acepta solo texto
        control.prop( 'maxlength', 50 );
        
        control = div.find( 'input[name="txt_apellidoMaterno"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});//acepta solo texto
        control.prop( 'maxlength', 50 );
        
    }


</script>
