<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> UBIGEOS </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-buscar">
            <div class="col-md-12">

                <form id="form-buscar" method="POST" role="form">
                                
                <div class="row">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label class="control-label">Codigo de Ubigeo</label>
                                <input name="txt_codigoUbigeo" type="text" title="CodigoUbigeo" maxlength="6" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Descripci&oacute;n</label>
                                <input name="txt_descripcion" type="text" title="Descripci&oacute;n" maxlength="50" class="form-control input-sm text-uppercase">
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

                    <div>&nbsp;</div>

                    <div class="row">
                        <div class="col-md-2 col-md-offset-5">
                            <button id="btn_buscar" title="Buscar" class="btn btn-sm btn-primary btn-block"><span class="fa fa-search"></span>&nbsp; BUSCAR</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>

        <div>&nbsp;</div>
        <div>&nbsp;</div>

        <div class="row" id="div-panel-grilla">
            <div class="col-md-12">
                <div class="panel panel-default">

                    <div class="panel-body">
                        <div id="div-load-grilla">
                            <c:import url="/admin.htm">
                                <c:param name="action" value="cargarPage" />
                                <c:param name="_page" value="ubigeo-buscar-grilla" />
                                <c:param name="_module" value="registro-ubigeo" />
                                <c:param name="_template" value="default" />
                            </c:import>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div>&nbsp;</div>

        <div class="row" id="div-panel-botones">

            <div class="col-md-2 col-md-offset-2">
                <button id="btn_nuevo" class="btn btn-sm btn-primary btn-block" title="Nuevo"><i class="fa fa-file-o"></i>&nbsp; NUEVO</button>
            </div>

            <div class="col-md-2">
                <button id="btn_editar" class="btn btn-sm btn-primary btn-block" title="Editar"><i class="fa fa-pencil-square-o"></i>&nbsp; EDITAR</button>
            </div>

            <div class="col-md-2">
                <button id="btn_eliminar" class="btn btn-sm btn-primary btn-block" title="Eliminar"><i class="fa fa-times"></i>&nbsp; ELIMINAR</button>
            </div>

            <div class="col-md-2">
                <button id="btn_ver" class="btn btn-sm btn-primary btn-block" title="Ver"><i class="fa fa-eye"></i>&nbsp; VER</button>
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

        initGrilla();
    }

    function pageEvents() {

        eventBuscar();
        eventNuevo();
        eventEditar();
        eventEliminar();
        eventVer();
    }

    function pagePostInit() {
		initMask();
    }

    function getSelectedIDGrilla() {

        var idRegistro = $('#div-load-grilla').find('input[type=radio]:checked').val();

        return idRegistro;
    }

    function initGrilla() {

        initDG('#ubigeo-buscar-grilla', true, true, true, null, null);

        eventSelectOnTR('#ubigeo-buscar-grilla');
    }

    function cargarGrilla() {

        var paramsBuscar = $('#form-buscar').serialize();

        $('#div-load-grilla').load('registro-ubigeo.htm?action=buscarUbigeo', paramsBuscar, function(response, status, xhr) {

            if (status == "error") { // xhr.statusText
                showMensaje('Mensaje', 'Ocurri\u00F3 un error inesperado: ERROR ' + xhr.status);
                return;
            }

            initGrilla();

        });
    }

    function eventNuevo() {

        $('#btn_nuevo').on('click', function(e) {

            $(location).prop('href', 'registro-ubigeo.htm?action=mostrarNuevo');
        });
    }

    function eventEditar() {

        $('#btn_editar').on('click', function(e) {

            var selectedID = getSelectedIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
                return null;
            }

            $(location).prop('href', 'registro-ubigeo.htm?action=mostrarEditar&ubigeoID=' + selectedID);
        });
    }

    function eventBuscar() {

        $('#btn_buscar').on('click', function(e) {

            e.preventDefault();

            var restore = estadoInputInicial('#div-panel-buscar');

            $.ajax({
                url: 'registro-ubigeo.htm?action=validarBuscar',
                cache: false,
                data: $('#form-buscar').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#div-panel-buscar', data, restore);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    console.log('data: ' + JSON.stringify(data));

                    cargarGrilla();

                },
                error: function(data, textStatus, errorThrown) {
                    handleError(data);
                }
            });

        });
    }

    function eventEliminar() {

        $('#btn_eliminar').on('click', function(e) {

            var selectedID = getSelectedIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un registro de la lista');
                return null;
            }

            showConfirmation('Eliminar registro', '\u00BFSeguro que desea eliminar el registro?', function() {

                $.ajax({
                    url: 'registro-ubigeo.htm?action=eliminarUbigeo',
                    cache: false,
                    data: {
                        'ubigeoID': selectedID
                    },
                    async: true,
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {

                        $('#msgPopupConfirmDiv').modal('hide');

                        if (handleErrorJson(data)) {
                            return;
                        }

                        console.log('data: ' + JSON.stringify(data));

                        showMensaje('Mensaje', 'Operaci\u00F3n realizada con \u00E9xito');

                        cargarGrilla();

                    },
                    error: function(data, textStatus, errorThrown) {
                        handleError(data);
                    }
                });

            });

        });
    }

    function eventVer() {

        $('#btn_ver').on('click', function(e) {

            var selectedID = getSelectedIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un registro de la lista');
                return null;
            }

            $(location).prop('href', 'registro-ubigeo.htm?action=verUbigeo&ubigeoID=' + selectedID);
        });
    }
	
	function initMask() {
    	
    	var div = $( '#div-panel-buscar' );
    	var control = null;
    	
        control = div.find( 'input[name="txt_codigoUbigeo"]' );
        control.inputmask( "9{0,6}", {placeholder: ''});//solo numeros
        control.prop( 'maxlength', 6 )

        control = div.find( 'input[name="txt_descripcion"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});//acepta solo texto
        control.prop( 'maxlength', 50 )
        
    }

</script>