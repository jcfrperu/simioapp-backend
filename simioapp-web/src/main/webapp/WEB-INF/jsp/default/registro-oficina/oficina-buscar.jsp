<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> OFICINAS </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-buscar">
            <div class="col-md-12">

                <form id="form-buscar" method="POST" role="form">
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Abreviatura Oficina</label>
                                <input name="txt_abreviaturaOficina" type="text" title="Abreviatura Oficina" maxlength="15" class="form-control input-sm text-uppercase">
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
                                <label class="control-label">Local</label>
                                <select class="form-control input-sm" title="Local" id="cbo_local" name="cbo_local">
                                	<c:forEach items="${cboLocal.items}" var="item" >
                                        <option <c:if test="${item.id == cboLocal.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">&Aacute;rea</label>
                                <select class="form-control input-sm" title="&Aacute;rea" id="cbo_area" name="cbo_area">
                                    <c:forEach items="${cboArea.items}" var="item" >
                                        <option <c:if test="${item.id == cboArea.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Nombre Oficina</label>
                                <input name="txt_nombreOficina" type="text" title="Nombre Oficina" maxlength="150" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                         <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Piso Oficina</label>
                                <input name="txt_pisoOficina" type="text" title="Piso Oficina" maxlength="15" class="form-control input-sm text-uppercase">
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
                                <c:param name="_page" value="oficina-buscar-grilla" />
                                <c:param name="_module" value="registro-oficina" />
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
        
        <div> &nbsp; </div>
				
	</div>
</div>        

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
        cargarArea();
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

    }

    function getSelectedIDGrilla() {

        var idRegistro = $('#div-load-grilla').find('input[type=radio]:checked').val();

        return idRegistro;
    }

    function initGrilla() {

        initDG('#oficina-buscar-grilla', true, true, true, null, null);

        eventSelectOnTR('#oficina-buscar-grilla');
    }

    function cargarGrilla() {

        var paramsBuscar = $('#form-buscar').serialize();

        $('#div-load-grilla').load('registro-oficina.htm?action=buscarOficina', paramsBuscar, function(response, status, xhr) {

            if (status == "error") { // xhr.statusText
                showMensaje('Mensaje', 'Ocurri\u00F3 un error inesperado: ERROR ' + xhr.status);
                return;
            }

            initGrilla();

        });
    }

    function eventNuevo() {

        $('#btn_nuevo').on('click', function(e) {

            $(location).prop('href', 'registro-oficina.htm?action=mostrarNuevo');
        });
    }

    function eventEditar() {

        $('#btn_editar').on('click', function(e) {

            var selectedID = getSelectedIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
                return null;
            }

            $(location).prop('href', 'registro-oficina.htm?action=mostrarEditar&oficinaID=' + selectedID);
        });
    }

    function eventBuscar() {

        $('#btn_buscar').on('click', function(e) {

            e.preventDefault();

            var restore = estadoInputInicial('#div-panel-buscar');

            $.ajax({
                url: 'registro-oficina.htm?action=validarBuscar',
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
                    url: 'registro-oficina.htm?action=eliminarOficina',
                    cache: false,
                    data: {
                        'oficinaID': selectedID
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

	function cargarArea(){
    	
    	$("#cbo_local").change(function () {

    		var formulario = "BUSCAR";
    		
    		var formURL = 'registro-oficina.htm?action=cargarAreas&formulario='+formulario;
    		
    		$.ajax({
                url: formURL,
                cache: false,
                data: $('#form-buscar').serialize(),
                async: true,
                type: 'POST',
                dataType: 'json',
                success: function(data) {

                    if (huboErrorJson(data)) {

                        if (huboErrorValidacionJson(data)) {
                            estadoInputError('#div-panel-registro', data, null);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    var items = data.dataJson.items;
                    $("#cbo_area").empty();
            		$.each(items, function (i, item) {
        		    	$('#cbo_area').append($('<option>', { 
        		        value: item.id,
        		        text : item.label 
        		    	}));
        			});

                },
                error: function(data, textStatus, errorThrown) {
                    handleError(data);
                }
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

            $(location).prop('href', 'registro-oficina.htm?action=verOficina&oficinaID=' + selectedID);
        });
    }

</script>
