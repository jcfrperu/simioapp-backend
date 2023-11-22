<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> PANEL CIERRE DE INVENTARIO </strong> </div>
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

                    <div>&nbsp;</div>

                    <div class="row">
                        <div class="col-md-4 col-lg-3">
                            <button id="btn_cerrar_inventario" title="Cerrar Inventario" class="btn btn-sm btn-primary btn-block">CERRAR INVENTARIO</button>
                        </div>
                        <div class="col-md-4 col-lg-3">
                            <button id="btn_ir_modulo_sobrante_faltante" title="Ir al m&oacute;dulo Sobrantes / Faltantes" class="btn btn-sm btn-info btn-block">IR A SOBRANTES / FALTANTES</button>
                        </div>
                        <div class="col-md-4 col-lg-3">
                            <button id="btn_ir_modulo_estado_subidas" title="Ir al m&oacute;dulo Estado de Subidas" class="btn btn-sm btn-info btn-block">IR A ESTADO DE SUBIDAS</button>
                        </div>
                    </div>

                </form>

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
//        pageHiddenMessages()
    });

//    function pageHiddenMessages() {
//        if ( estaDefinido('#msgHidden') && getTrimValue('#msgHidden') != '' ) {
//            showMensaje('Mensaje', getTrimValue('#msgHidden'), function() {
//                $('#int_inventarioID').focus();
//            });
//        }
//    }

    function pageInit() {

        initMask();
        initFechas();
    }

    function initFechas() {

    }

    function initMask() {


    }

    function pageEvents() {

        eventBuscar();
        eventModuloEstadoSubidas();
        eventModuloSobranteFaltante();
    }

    function pagePostInit() {

    }

    function eventBuscar() {

        $('#btn_cerrar_inventario').on('click', function(e) {

            e.preventDefault();

            var restore = estadoInputInicial('#div-panel-buscar');

            $.ajax({
                url: 'cierre-inventario.htm?action=validarCierre',
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

                    cerrarInventarioAjax();

                },
                error: function(data, textStatus, errorThrown) {
                    handleError(data);
                }
            });

        });
    }

    function cerrarInventarioAjax() {

        $.ajax({
            url: 'cierre-inventario.htm?action=cerrarInventario',
            cache: false,
            data: $('#form-buscar').serialize(),
            async: true,
            type: 'POST',
            dataType: 'json',
            success: function(data) {

                if (huboErrorJson(data)) {
                    handleErrorJson(data);
                    return;
                }

                console.log('data: ' + JSON.stringify(data));

                showMensaje('Mensaje', 'Operaci\u00F3n realizada con \u00E9xito', function() {

                    // recargar la pagina
                    $(location).prop('href', 'cierre-inventario.htm?action=mostrarPanelCierre');
                });

            },
            error: function(data, textStatus, errorThrown) {
                handleError(data);
            }
        });
    }

    function eventModuloEstadoSubidas() {

        $('#btn_ir_modulo_estado_subidas').on('click', function(e) {

            e.preventDefault();

            $(location).prop('href', 'estado-subida-inventario.htm?action=mostrarPanelEstadoSubida');
        });
    }

    function eventModuloSobranteFaltante() {

        $('#btn_ir_modulo_sobrante_faltante').on('click', function(e) {

            e.preventDefault();

            $(location).prop('href', 'sobrante-faltante-inventario.htm?action=mostrarPanelSobranteFaltante');
        });
    }


</script>