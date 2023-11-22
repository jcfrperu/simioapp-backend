<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
    <div class="col-md-12">
        <h4 class="page-header">
            <strong> USUARIO </strong> <br />
            <div class="css3gradient"></div>
        </h4>
    </div>
</div>

<!-- instancia de la barra de error superior -->
<div id="divMsgGeneralErrorInstance"></div>

<div class="row" id="div-panel-buscar">
    <div class="col-md-12">

        <form id="form-buscar" method="POST" role="form">
                        
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="control-label">UsuarioID</label>
                        <input name="txt_usuarioID" type="text" title="UsuarioID" maxlength="50" class="form-control input-sm text-uppercase">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
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
                        <c:param name="_page" value="usuario-buscar-grilla" />
                        <c:param name="_module" value="registro-usuario" />
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
        <button id="btn_nuevo" class="btn btn-md btn-primary btn-block" title="Nuevo"><i class="fa fa-file-o"></i>&nbsp; NUEVO</button>
    </div>

    <div class="col-md-2">
        <button id="btn_editar" class="btn btn-md btn-primary btn-block" title="Editar"><i class="fa fa-pencil-square-o"></i>&nbsp; EDITAR</button>
    </div>

    <div class="col-md-2">
        <button id="btn_eliminar" class="btn btn-md btn-primary btn-block" title="Eliminar"><i class="fa fa-times"></i>&nbsp; ELIMINAR</button>
    </div>

    <div class="col-md-2">
        <button id="btn_ver" class="btn btn-md btn-primary btn-block" title="Ver"><i class="fa fa-eye"></i>&nbsp; VER</button>
    </div>

</div>

<div>&nbsp;</div>
<div>&nbsp;</div>

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

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

        initGrilla();
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
    	
    	// div panel buscar de donde buscar los controles a aplicarle la mascara
    	// var div = $( '#div-panel-buscar' );
    	
    	// componente input mask
    	// más info: https://github.com/RobinHerbots/jquery.inputmask
    	// 9: numerico
    	// a: alfabetico
    	// *: alfanumerico
    	// EXPRESION{15}: exactamente 15 caracteres
    	// EXPRESION{0, 15}: de 0 a 15 caracteres
    	// 
    	// EJEMPLOS:
    	// ---------
    	//
    	// números enteros 
    	// control.inputmask( "integer", {placeholder: ''});
    	// control.inputmask( "-{0,1}9{10}", {placeholder: ''});
    	//
    	// números enteros no negativos
    	// control.inputmask( "9{10}", {placeholder: ''});
    	//
    	// números decimales
    	// control.inputmask( "decimal", {placeholder: ''});
    	
    	// cualquier caracter
    	// control.inputmask( "*{5}", {placeholder: ''});
    	
    	var div = $( '#div-panel-buscar' );
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

    function pageEvents() {

        eventBuscar();
        eventNuevo();
        eventEditar();
        eventEliminar();
        eventVer();
    }

    function pagePostInit() {

    }

    function getSelectedIDGrilla() {

        var idRegistro = $('#div-load-grilla').find('input[type=radio]:checked').val();

        return idRegistro;
    }

    function initGrilla() {

        initDG('#usuario-buscar-grilla', true, true, true, null, null);

        eventSelectOnTR('#usuario-buscar-grilla');
    }

    function cargarGrilla() {

        var paramsBuscar = $('#form-buscar').serialize();
		
        $('#div-load-grilla').load('registro-usuario.htm?action=buscarUsuario', paramsBuscar, function(response, status, xhr) {

            if (status == "error") { // xhr.statusText
                showMensaje('Mensaje', 'Ocurri\u00F3 un error inesperado: ERROR ' + xhr.status);
                return;
            }

            initGrilla();

        });
    }

    function eventNuevo() {

        $('#btn_nuevo').on('click', function(e) {

            $(location).prop('href', 'registro-usuario.htm?action=mostrarNuevo');
        });
    }

    function eventEditar() {

        $('#btn_editar').on('click', function(e) {

            var selectedID = getSelectedIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
                return null;
            }

            $(location).prop('href', 'registro-usuario.htm?action=mostrarEditar&usuarioID=' + selectedID);
        });
    }

    function eventBuscar() {

        $('#btn_buscar').on('click', function(e) {

            e.preventDefault();

            var restore = estadoInputInicial('#div-panel-buscar');

            $.ajax({
                url: 'registro-usuario.htm?action=validarBuscar',
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
                    url: 'registro-usuario.htm?action=eliminarUsuario',
                    cache: false,
                    data: {
                        'usuarioID': selectedID
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
			
            $(location).prop('href', 'registro-usuario.htm?action=verUsuario&usuarioID=' + selectedID);
        });
    }

</script>