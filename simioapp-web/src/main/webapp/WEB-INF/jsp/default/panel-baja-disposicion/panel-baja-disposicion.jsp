<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> PANEL BAJA / DISPOSICION </strong> </div>
    <div class="panel-body">

        <div>&nbsp;</div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-buscar">
            <div class="col-md-12">

                <form id="form-buscar" method="POST" role="form">

                    <input id="msgHidden" type="hidden" value="${msgHidden}">

                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Entidad</label>
                                <select class="form-control input-sm" title="Entidad" name="cboEntidad">
                                    <c:forEach items="${cboEntidad.items}" var="item" >
                                        <option <c:if test="${item.id == cboEntidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Inventario</label>
                                <select class="form-control input-sm" title="Lista de Inventarios Abiertos" name="int_inventarioID" id="int_inventarioID">
                                    <c:forEach items="${cboInventario.items}" var="item" >
                                        <option <c:if test="${item.id == cboInventario.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Patrimonial</label>
                                <input name="txt_codigoPatrimonial" type="text" title="C&oacute;digo Patrimonial" maxlength="12" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Estado Subida</label>
                                <select class="form-control input-sm" title="Estado Subida" name="txt_estadoSubida">
                                    <c:forEach items="${cboEstadoSubida.items}" var="item" >
                                        <option <c:if test="${item.id == cboEstadoSubida.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Inventariador</label>
                                <select class="form-control input-sm" title="Inventariador" name="txt_inventariador">
                                    <c:forEach items="${cboInventariador.items}" var="item" >
                                        <option <c:if test="${item.id == cboInventariador.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Local</label>
                                <select class="form-control input-sm" title="Local" id="cbo_local" name="cbo_local">
                                    <c:forEach items="${cboLocal.items}" var="item" >
                                        <option <c:if test="${item.id == cboLocal.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo &Aacute;rea</label>
                                <select class="form-control input-sm" title="&Aacute;rea" id="cbo_area" name="cbo_area">
                                    <c:forEach items="${cboArea.items}" var="item" >
                                        <option <c:if test="${item.id == cboArea.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Oficina</label>
                                <select class="form-control input-sm" title="&Aacute;rea" id="cbo_oficina" name="cbo_oficina">
                                    <c:forEach items="${cboOficina.items}" var="item" >
                                        <option <c:if test="${item.id == cboOficina.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div>&nbsp;</div>

                    <div class="row">
                        <div class="col-md-3 col-md-offset-4 col-lg-2 col-lg-offset-5">
                            <button id="btn_buscar" title="Buscar" class="btn btn-sm btn-primary btn-block"><span class="fa fa-search"></span>&nbsp; BUSCAR</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>

        <div>&nbsp;</div>

        <div class="row" id="div-panel-grilla">
            <div class="col-md-12">
                <div class="panel panel-default">

                    <div class="panel-body">
                        <div class="table-responsive" id="div-load-grilla">
                            <c:import url="/admin.htm">
                                <c:param name="action" value="cargarPage" />
                                <c:param name="_page" value="panel-sobrante-faltante-grilla" />
                                <c:param name="_module" value="panel-sobrante-faltante" />
                                <c:param name="_template" value="default" />
                            </c:import>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div>&nbsp;</div>

        <div class="row">
            <div class="col-md-12">
            <label class="control-label">Baja/Disposicion a guardar</label>
			<form id="form-inventariadores" action="baja-disposicion.htm" method="POST" role="form">
                    <input type="hidden" name="action" value="">
                    <input type="hidden" name="bienes_id" value="">
				<fieldset>
				<legend>ACTO DE BAJA</legend>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Resoluci&oacute;n de Baja</label>
                                <input id="txt_resolucionBaja" name="txt_resolucionBaja" type="text" title="Resoluci&oacute;n de Baja" maxlength="150" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Fecha de Resoluci&oacute;n</label>
                                <input id="txt_fechaResolucionBaja" name="txt_fechaResolucionBaja" type="text" title="Fecha de Resoluci&oacute;n" maxlength="15" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Causal de Baja</label>
                                <select class="form-control input-sm" title="Causal de Baja" id="cbo_causalBaja" name="cbo_causalBaja">
                                    <c:forEach items="${cboCausalBaja.items}" var="item" >
                                        <option <c:if test="${item.id == cboCausalBaja.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">N° Documento SBN</label>
                                <input id="txt_numeroDocSBNBaja" name="txt_numeroDocSBNBaja" type="text" title="N° Documento SBN" maxlength="15" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
				</fieldset>
				
				<fieldset>
				<legend>DISPOSICION DE BIENES</legend>
                    <div class="row">
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Resoluci&oacute;n de Disposici&oacute;n</label>
                                <input id="txt_resolucionDisposicion" name="txt_resolucionDisposicion" type="text" title="Resoluci&oacute;n de Disposici&oacute;n" maxlength="150" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Fecha de Resoluci&oacute;n</label>
                                <input id="txt_fechaResolucionDisposicion" name="txt_fechaResolucionDisposicion" type="text" title="Fecha de Resoluci&oacute;n" maxlength="15" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6" id="infoResumen"></div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Acto de Disposici&oacute;n</label>
                                <select class="form-control input-sm" title="Acto de Disposici&oacute;n" id="cbo_actoDisposicion" name="cbo_actoDisposicion">
                                    <c:forEach items="${cboActoDisposicion.items}" var="item" >
                                        <option <c:if test="${item.id == cboActoDisposicion.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Donatario /  Permutante /  Adjudicatario</label>
                                <input id="txt_donatario" name="txt_donatario" type="text" title=">Donatario /  Permutante /  Adjudicatario" maxlength="15" class="form-control input-sm text-uppercase">
                                <span class="help-block"></span>
                            </div>
                        </div>
                
                    </div>
				</fieldset>
				<div class="col-md-6" id="infoResumen"></div>
				</form>
            </div>
        </div>

        <div class="row" id="div-panel-botones">

            <div class="col-md-3">
                <button id="btn_guardar" class="btn btn-md btn-primary btn-block" title="Guardar">GUARDAR</button>
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

        <div>&nbsp;</div>

    </div>
</div>

<div>&nbsp;</div>

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
        pageHiddenMessages();
    });

    function pageHiddenMessages() {
        if ( estaDefinido('#msgHidden') && getTrimValue('#msgHidden') != '' ) {
            showMensaje('Mensaje', getTrimValue('#msgHidden'), function() {
                $('#int_inventarioID').focus();
            });
        }
    }

    function pageInit() {

        initGrilla();
        initMask();
        initFechas();
    }

    function initFechas() {

    }

    function initMask() {

        var div = $( '#div-panel-buscar' );

        var control = div.find( 'input[name="txt_codigoPatrimonial"]' );
        control.inputmask( "[*|| ]{0,12}", {placeholder: ''});
        control.prop( 'maxlength', 12 );

    }

    function pageEvents() {

        eventBuscar();
        eventGuardar();
        eventVer();
        eventChangeLocal();
        eventChangeArea();
        eventSeleccionarTodos();
    }

    function pagePostInit() {

    }

    function getRowsGrilla() {

        var table = $('#panel-sobrante-faltante-grilla').DataTable();

        // recoger todos los tr
        return table.rows().nodes();
    }

    function getSelectedChecksIDGrilla() {

        var rows = getRowsGrilla();
        var selectedCheckIDs = $('input[type=checkbox]:checked', rows);

        var ids = [];
        $.each(selectedCheckIDs, function(i, item) {
            ids.push( $(item).val() );
        });

        return ids;
    }

    function initGrilla() {

        initDG('#panel-sobrante-faltante-grilla', true, true, true, null, null);
        customEventSelectOnTR('#panel-sobrante-faltante-grilla');
        eventSeleccionarTodos();
    }

    function customEventSelectOnTR(tableID) {

        // seleccionar un input checkbox clickeando en todo el tr
        $(tableID + ' tbody').on('click', 'tr', function(e) {
            var input = $(this).find('input:checkbox');
            input.prop('checked', !input.prop('checked'));
        });

        $(tableID + ' input:checkbox').on('click', function(e) {
            e.stopPropagation();
        });

    }

    function cargarGrilla() {

        var paramsBuscar = $('#form-buscar').serialize();

        $('#div-load-grilla').load('sobrante-faltante-inventario.htm?action=buscarInventarioBien', paramsBuscar, function(response, status, xhr) {

            if (status == "error") { // xhr.statusText
                showMensaje('Mensaje', 'Ocurri\u00F3 un error inesperado: ERROR ' + xhr.status);
                return;
            }

            initGrilla();
            cargarInfoResumen();

        });
    }

    function cargarInfoResumen() {

        var infoExtraHidden = getTrimValue('#infoExtraHidden');

        if ( infoExtraHidden != '' ) {

            var infoResumen = $.parseJSON( infoExtraHidden );

            if ( estaDefinido( infoResumen ) ) {

                var html = '';

                html = html + '<span class="label label-primary">TOTAL DE BIENES</span> <span class="badge">' + $.trim(infoResumen.nroBienes) + '</span>';
                html = html + '<br />';
                html = html + '<span class="label label-success">SOBRANTES </span> <span class="badge">' + $.trim(infoResumen.nroSobrantes) + '</span>';
                html = html + '<br />';
                html = html + '<span class="label label-warning">FALTANTES </span> <span class="badge">' + $.trim(infoResumen.nroFaltantes) + '</span>';
                html = html + '<br />';
                html = html + '<span class="label label-danger">EN BLANCO </span> <span class="badge">' + $.trim(infoResumen.nroBlancos) + '</span>';

                // si los bienes sin asignar son pocos los muestra
                if ( toNumero(infoResumen.nroBienes) > 0 && toNumero(infoResumen.nroBlancos) > 0 && toNumero(infoResumen.nroBlancos) < 5 ) {
                    html = html + '<br />';
                    html = html + '<span class="label label-danger">' + JSON.stringify(infoResumen.listaBlancos) + '</span>';
                }

                $( '#infoResumenAsignacion' ).html( html );
            }

        }
    }

    function eventSeleccionarTodos() {

        $('.chk_seleccionar_todo').on('change', function(e) {

            e.preventDefault();

            var checked = $( this ).prop('checked');

            // table
            var table = $('#panel-sobrante-faltante-grilla').DataTable();

            // recoger todos los tr
            var rows = table.rows().nodes();

            $('input[type="checkbox"]', rows).prop('checked', checked);

        });
    }

    function eventGuardar() {

        $('#btn_guardar').on('click', function(e) {

            e.preventDefault();

            var selectedID = getSelectedChecksIDGrilla();

//             var cbo_sobrante_faltante_guardar = $('#cbo_sobrante_faltante_guardar').val();

//             if ( cbo_sobrante_faltante_guardar == '' || cbo_sobrante_faltante_guardar == '-1' ) {

//                 showMensaje('', 'Por favor seleccione un Sobrante/Faltante a guardar', function() {

//                     $('#cbo_sobrante_faltante_guardar').focus();

//                 });

//                 return;
//             }

            if (selectedID == null || selectedID.length == 0) {
                showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
                return null;
            }

            showConfirmation('', '\u00BFSeguro que desea guardar los cambios?', function() {

                guardarAjax( selectedID );

            });

        });
    }

    function guardarAjax(selectedID) {

    	var resolucionBaja = $('#txt_resolucionBaja').val();
        var fechaResolucionBaja = $('#txt_fechaResolucionBaja').val();
        var causalBaja = $('#cbo_causalBaja').val();
        var numDocSBN = $('#txt_numeroDocSBNBaja').val();
        var resolucionDisposicion = $('#txt_resolucionDisposicion').val();
        var fechaResolucionDisposicion = $('#txt_fechaResolucionDisposicion').val();
        var actoDisposicion = $('#cbo_actoDisposicion').val();
        var donatorio = $('#txt_donatario').val();
    	
        $.ajax({
            url: 'baja-disposicion.htm?action=guardar',
            cache: false,
            data: {
                'bienesID': JSON.stringify( selectedID ),
                'resolucionBaja': resolucionBaja,
                'fechaResolucionBaja': fechaResolucionBaja,
                'causalBaja': causalBaja,
                'numDocSBN': numDocSBN,
                'resolucionDisposicion': resolucionDisposicion,
                'fechaResolucionDisposicion': fechaResolucionDisposicion,
                'actoDisposicion': actoDisposicion,
                'donatorio': donatorio
            },
            async: true,
            type: 'POST',
            dataType: 'json',
            success: function(data) {

                if (handleErrorJson(data)) {
                    return;
                }


                $('#btn_buscar').trigger( 'click' );

            },
            error: function(data, textStatus, errorThrown) {
                handleError(data);
            }
        });

    }

    function eventBuscar() {

        $('#btn_buscar').on('click', function(e) {

            e.preventDefault();

            var restore = estadoInputInicial('#div-panel-buscar');

            $.ajax({
                url: 'sobrante-faltante-inventario.htm?action=validarBuscar',
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

    function eventVer() {

        $('#btn_ver').on('click', function(e) {

            // TODO/FIXME: deberia ser un solo boton

            var selectedID = getSelectedChecksIDGrilla();

            if (selectedID == null) {
                showMensaje('Mensaje', 'Por favor seleccione un registro de la lista');
                return null;
            }

            $(location).prop('href', 'sobrante-faltante-inventario.htm?action=verInventarioBien&inventarioBienID=' + selectedID);
        });
    }

    function eventChangeLocal() {

        // al cambiar el combo area se cargan sus locales asociados
        $("#cbo_local").change(function () {

            var formulario = "BUSCAR";

            var formURL = 'sobrante-faltante-inventario.htm?action=cargarAreas&formulario='+formulario;

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
                            estadoInputError('#form-buscar', data, null);
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

    function eventChangeArea() {

        // al cambiar el combo oficina se cargan sus locales asociados
        $("#cbo_area").change(function () {

            var formulario = "BUSCAR";

            var formURL = 'sobrante-faltante-inventario.htm?action=cargarOficinas&formulario='+formulario;

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
                            estadoInputError('#form-buscar', data, null);
                            return;
                        }

                        handleErrorJson(data);
                        return;
                    }

                    var items = data.dataJson.items;
                    $("#cbo_oficina").empty();
                    $.each(items, function (i, item) {
                        $('#cbo_oficina').append($('<option>', {
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

</script>

