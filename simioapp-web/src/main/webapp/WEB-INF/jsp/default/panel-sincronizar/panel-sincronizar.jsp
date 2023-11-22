<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<style type="text/css">
    .nonePointerEvents {
        pointer-events: none;
        margin-bottom: 10px;
    }

    .nonePointerEvents:hover {
        pointer-events: none;
        margin-bottom: 10px;
    }
</style>

<div class="panel panel-primary">

    <div class="panel-heading"> <strong> SINCRONIZAR TOMA DE INVENTARIO - SUBIDA </strong> </div>
    <div class="panel-body">
        
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>

		<div>&nbsp;</div>
		
		<div class="row" id="div-panel-sincronizar">
		    <div class="col-md-12">

                <form id="form-sincronizar" method="POST" role="form">

                    <input type="hidden" name="usuario" value="${usuarioSYNC}">
                    <input type="hidden" name="entidad" value="${entidadSYNC}">
                    <input type="hidden" name="inventario" value="${inventarioSYNC}">
                    <input type="hidden" name="token" value="${tokenSYNC}">
                    <input type="hidden" name="dataSyncJSON" value='${dataSyncJSONSYNC}'>

                    <div class="row">
                        <div class="col-md-12">
                            <button class="btn btn-success nonePointerEvents" type="button">MIS BIENES INVENTARIADOS (LISTOS PARA SUBIR) <span id="span-bienes-inventariados" class="badge">0</span></button>
                            <div class="row" id="div-panel-table-grilla-bienes-inventariados">
                                <div class="col-md-12">
                                    <table id="table-grilla-bienes-listos-para-subir" class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>C&oacute;digo Patrimonial</th>
                                            <th>Descripci&oacute;n</th>
                                            <th>Estado del Bien</th>
                                            <th>Estado Subida</th>
                                            <th>Sobrante/Faltante</th>
                                            <th>Local</th>
                                            <th>&Aacute;rea</th>
                                            <th>Oficina</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div>&nbsp;</div><div>&nbsp;</div>

                    <div class="row">
                        <div class="col-md-12">
                            <button class="btn btn-danger nonePointerEvents" type="button">MIS BIENES QUE FALTAN INVENTARIAR (NO SE SUBIR&Aacute;N) <span id="span-bienes-faltan-inventariar" class="badge">0</span></button>
                            <div class="row" id="div-panel-table-grilla-bienes-faltan-inventariar">
                                <div class="col-md-12">
                                    <table id="table-grilla-bienes-faltan-inventariar" class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>C&oacute;digo Patrimonial</th>
                                            <th>Descripci&oacute;n</th>
                                            <th>Estado del Bien</th>
                                            <th>Estado Subida</th>
                                            <th>Sobrante/Faltante</th>
                                            <th>Local</th>
                                            <th>&Aacute;rea</th>
                                            <th>Oficina</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:if test="${puedeSincronizar == 'si'}">

                        <div>&nbsp;</div><div>&nbsp;</div>

                        <div class="row">
                            <div class="col-md-3 col-md-offset-4">
                                <button title="Sincronizar" class="btn btn-md btn-primary btn-block btn_sincronizar">SINCRONIZAR</button>
                            </div>
                        </div>

                    </c:if>

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

        <div id="hiddenForms">
            <form id="form-descarga-sync" action="sincronizar.htm" method="POST" role="form">
                <input type="hidden" name="action" value="mostrarSincronizacionDescarga">
                <input type="hidden" name="usuario" value="${usuarioSYNC}">
                <input type="hidden" name="entidad" value="${entidadSYNC}">
                <input type="hidden" name="inventario" value="${inventarioSYNC}">
                <input type="hidden" name="token" value="${tokenSYNC}">
            </form>
        </div>

    </div>
</div>

<script>

    $(document).ready(function() {

        <c:if test="${puedeSincronizar == 'si'}">
            pageInit();
            pageEvents();
            pagePostInit();
        </c:if>

        <c:if test="${puedeSincronizar == 'no'}">

            showMensajeError('Advertancia', '${puedeSincronizarMsgError}', function() {
                mostrarMsgGeneralError( 'ADVERTENCIA:', '${puedeSincronizarMsgError}');
            });

        </c:if>
    });

    function pageInit() {

        $('#hiddenForms').hide();

        initMask();
        initFechas();

        var dataSyncHidden = $.trim( $('#form-sincronizar').find('input[name="dataSyncJSON"]').val() );
        var dataSync = $.parseJSON( dataSyncHidden );
        var result = sacarResumenInventarioBienSincronizar(dataSync.tabla.inventarioBien, true);

        initGrillaBienesListosParaSubir(dataSync, result.listas.bienesListosParaSubir);
        initGrillaBienesQueNoSeSubiran(dataSync, result.listas.bienesListosQueNoSeSubiran);

        initTotalesBadgets(result);
    }

    function initTotalesBadgets(result) {

        if (estaDefinido(result)) {

            $('#span-bienes-listos-para-subir').html(result.totalBienesListosParaSubir);
            $('#span-bienes-que-no-se-subiran').html(result.totalBienesListosQueNoSeSubiran);
        }
    }

    function initGrillaBienesListosParaSubir(dataSync, bienesListosParaSubir) {

        initDataDG('#table-grilla-bienes-listos-para-subir', bienesListosParaSubir, false, false, true, [
            {"data": "codigoPatrimonial", "class": "text-center"},
            {"data": "denominacionBien", "class": "text-left"},
            {"data": "estadoBien", "class": "text-left"},
            {"data": "estadoSubida", "class": "text-center"},
            {"data": "sobranteFaltante", "class": "text-center"},
            {"data": "localesID", "class": "text-left"},
            {"data": "areaID", "class": "text-left"},
            {"data": "oficinaID", "class": "text-left"}
        ],[{
            "targets": 0, // codigo patrimonial
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {
                    return '<span title="Inventario Bien ID: ' + $.trim(row.inventarioBienID) + ', Código Interno: ' + $.trim(row.codanterio) + ' ">' + $.trim(row.codigoPatrimonial) + '</span>';
                }

                return data;
            }
        }, {
            "targets": 1, // description
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {
                    return $.trim(row.denominacionBien);
                }

                return '';
            }
        }, {
            "targets": 2, // estadoBien
            "render": function (data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarCatalogoListado('ESTADO_BIEN', row.estadoBien, dataSync.tabla.catalogo);

                    if (found != null) {

                        // primero buscar la descripcion larga
                        if (estaDefinido(found.descripcion)) {
                            return wrapIntoSpanAndTitle(row.estadoBien, found.descripcion);
                        }

                        // sino muestra la corta
                        if (estaDefinido(found.descripcionCorta)) {
                            return wrapIntoSpanAndTitle(row.estadoBien, found.descripcionCorta);
                        }
                    }

                }

                return data;
            }

        }, {
            "targets": 3, // estadoSubida
            "render": function (data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarCatalogoListado('ESTADO_SUBIDA', row.estadoSubida, dataSync.tabla.catalogo);

                    if (found != null) {

                        // primero buscar la descripcion larga
                        if (estaDefinido(found.descripcion)) {
                            return wrapIntoSpanAndTitle(row.estadoSubida, found.descripcion);
                        }

                        // sino muestra la corta
                        if (estaDefinido(found.descripcionCorta)) {
                            return wrapIntoSpanAndTitle(row.estadoSubida, found.descripcionCorta);
                        }
                    }
                }

                return data;
            }

        }, {
            "targets": 4, // sobranteFaltante
            "render": function (data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarCatalogoListado('SOBRANTE_FALTANTE', row.sobranteFaltante, dataSync.tabla.catalogo);

                    if (found != null) {

                        // primero buscar la descripcion larga
                        if (estaDefinido(found.descripcion)) {
                            return wrapIntoSpanAndTitle(row.sobranteFaltante, found.descripcion);
                        }

                        // sino muestra la corta
                        if (estaDefinido(found.descripcionCorta)) {
                            return wrapIntoSpanAndTitle(row.sobranteFaltante, found.descripcionCorta);
                        }
                    }
                }

                return data;
            }

        }, {
            "targets": 5, // local
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarLocalesListado(row.localesID, dataSync.tabla.locales);

                    if ( found != null ) {
                        if ( estaDefinido(found.nombreLocal) ) {
                            return $.trim( found.nombreLocal );
                        }
                    }
                }

                return '';
            }
        }, {
            "targets": 6, // area
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarAreaListado(row.areaID, dataSync.tabla.area);

                    if (found != null) {
                        if (estaDefinido(found.nombreArea)) {
                            return $.trim(found.nombreArea);
                        }
                    }
                }

                return '';
            }
        }, {
            "targets": 7, // oficina
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarOficinaListado(row.oficinaID, dataSync.tabla.oficina);

                    if (found != null) {

                        // si tiene descripcion completa de la oficina lo muestra
                        if (estaDefinido(found.nombreOficina)) {
                            return $.trim(found.nombreOficina);
                        }

                        // prueba con la abreviatura
                        if (estaDefinido(found.abreviaturaOficina)) {
                            return $.trim(found.abreviaturaOficina);
                        }
                    }
                }

                return '';
            }
        }]);
    }

    function initGrillaBienesQueNoSeSubiran(dataSync, bienesQueNoSeSubiran) {

        initDataDG('#table-grilla-bienes-faltan-inventariar', bienesQueNoSeSubiran, false, false, true, [
            {"data": "codigoPatrimonial", "class": "text-center"},
            {"data": "denominacionBien", "class": "text-left"},
            {"data": "estadoBien", "class": "text-left"},
            {"data": "estadoSubida", "class": "text-center"},
            {"data": "sobranteFaltante", "class": "text-center"},
            {"data": "localesID", "class": "text-left"},
            {"data": "areaID", "class": "text-left"},
            {"data": "oficinaID", "class": "text-left"}
        ],[{
            "targets": 0, // codigo patrimonial
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {
                    return '<span title="Inventario Bien ID: ' + $.trim(row.inventarioBienID) + ', Código Interno: ' + $.trim(row.codanterio) + ' ">' + $.trim(row.codigoPatrimonial) + '</span>';
                }

                return data;
            }
        }, {
            "targets": 1, // denominacion
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    return $.trim(row.denominacionBien);
                }

                return '';
            }
        }, {
            "targets": 2, // estadoBien
            "render": function (data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarCatalogoListado('ESTADO_BIEN', row.estadoBien, dataSync.tabla.catalogo);

                    if (found != null) {

                        // primero buscar la descripcion larga
                        if (estaDefinido(found.descripcion)) {
                            return wrapIntoSpanAndTitle(row.estadoBien, found.descripcion);
                        }

                        // sino muestra la corta
                        if (estaDefinido(found.descripcionCorta)) {
                            return wrapIntoSpanAndTitle(row.estadoBien, found.descripcionCorta);
                        }
                    }

                }

                return data;
            }

        }, {
            "targets": 3, // estadoSubida
            "render": function (data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarCatalogoListado('ESTADO_SUBIDA', row.estadoSubida, dataSync.tabla.catalogo);

                    if (found != null) {

                        // primero buscar la descripcion larga
                        if (estaDefinido(found.descripcion)) {
                            return wrapIntoSpanAndTitle(row.estadoSubida, found.descripcion);
                        }

                        // sino muestra la corta
                        if (estaDefinido(found.descripcionCorta)) {
                            return wrapIntoSpanAndTitle(row.estadoSubida, found.descripcionCorta);
                        }
                    }
                }

                return data;
            }

        }, {
            "targets": 4, // sobranteFaltante
            "render": function (data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarCatalogoListado('SOBRANTE_FALTANTE', row.sobranteFaltante, dataSync.tabla.catalogo);

                    if (found != null) {

                        // primero buscar la descripcion larga
                        if (estaDefinido(found.descripcion)) {
                            return wrapIntoSpanAndTitle(row.sobranteFaltante, found.descripcion);
                        }

                        // sino muestra la corta
                        if (estaDefinido(found.descripcionCorta)) {
                            return wrapIntoSpanAndTitle(row.sobranteFaltante, found.descripcionCorta);
                        }
                    }
                }

                return data;
            }

        }, {
            "targets": 5, // local
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarLocalesListado(row.localesID, dataSync.tabla.locales);

                    if ( found != null ) {
                        if ( estaDefinido(found.nombreLocal) ) {
                            return $.trim( found.nombreLocal );
                        }
                    }
                }

                return '';
            }
        }, {
            "targets": 6, // area
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarAreaListado(row.areaID, dataSync.tabla.area);

                    if (found != null) {
                        if (estaDefinido(found.nombreArea)) {
                            return $.trim(found.nombreArea);
                        }
                    }
                }

                return '';
            }
        }, {
            "targets": 7, // oficina
            "render": function(data, type, row) {

                if (row != null && typeof row != 'undefined') {

                    var found = buscarOficinaListado(row.oficinaID, dataSync.tabla.oficina);

                    if (found != null) {

                        // si tiene descripcion completa de la oficina lo muestra
                        if (estaDefinido(found.nombreOficina)) {
                            return $.trim(found.nombreOficina);
                        }

                        // prueba con la abreviatura
                        if (estaDefinido(found.abreviaturaOficina)) {
                            return $.trim(found.abreviaturaOficina);
                        }
                    }
                }

                return '';
            }
        }]);
    }

    function wrapIntoSpanAndTitle(valor, description) {

        return '<span title="' + $.trim(valor) + ' - ' + $.trim(description) + '">' + $.trim(description) + '</span>';
    }

    // NOTA: estos metodos de buscar por catalogo, local, area y oficina, pueden optimizarse usando un mapa colocando al primaryKey como clave del mapa
    function buscarLocalesListado( localesID, dataLocales ) {

        if ( !estaDefinido( dataLocales ) ) return;

        var found = null;
        var idFind = $.trim(localesID);

        $.each(dataLocales, function(i, item) {

            if ( $.trim(item.localesID) == idFind ) {
                found = item;
                return false;
            }
        });

        return found;
    }

    function buscarAreaListado( areaID, dataAreas ) {

        if ( !estaDefinido( dataAreas ) ) return;

        var found = null;
        var idFind = $.trim(areaID);

        $.each(dataAreas, function(i, item) {

            if ( $.trim(item.areaID) == idFind ) {
                found = item;
                return false;
            }
        });

        return found;
    }

    function buscarOficinaListado( oficinaID, dataOficinas ) {

        if ( !estaDefinido( dataOficinas ) ) return;

        var found = null;
        var idFind = $.trim(oficinaID);

        $.each(dataOficinas, function(i, item) {

            if ( $.trim(item.oficinaID) == idFind ) {
                found = item;
                return false;
            }
        });

        return found;
    }

    function buscarCatalogoListado(catalogo, datacatalogo, dataCatalogo) {

        if ( !estaDefinido( dataCatalogo ) ) return;

        var found = null;

        var idCatalogo = $.trim(catalogo);
        var idDatacatalogo = $.trim(datacatalogo);

        $.each(dataCatalogo, function (i, item) {

            if (item.tipo == 'DET' && $.trim(item.catalogo) == idCatalogo && $.trim(item.datacatalogo) == idDatacatalogo) {
                found = item;
                return false;
            }
        });

        return found;
    }

	function initFechas() {

	}

	function initMask() {

    }

    function pageEvents() {
        eventSincronizar();
    }

    function pagePostInit() {

    }

    function eventSincronizar() {

        $('.btn_sincronizar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Sincronizar', '\u00BFSeguro que desea sincronizar la toma de inventario?', function() {

                $.ajax({
                    url: 'sincronizar.htm?action=sincronizarInventario',
                    cache: false,
                    data: $('#form-sincronizar').serialize(),
                    async: true,
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {

                        if (huboErrorJson(data)) {

                            handleErrorJson(data);
                            return;
                        }

                        console.log('data: ' + JSON.stringify(data));

                        // si hubieron errores controlados
                        if ( data.dataJson.syncIsOK == 'no' ) {

                            showMensajeError('Mensaje', "NO se pudo realizar la sincronizaci\u00F3n: " + $.trim(data.dataJson.syncMsgError));
                            return;
                        }

                        // si no hubieron erores controlados
                        if ( data.dataJson.syncIsOK == 'si' ) {

                            showMensaje('Mensaje', "La sincronizaci\u00F3n se realiz\u00F3 con \u00E9xito", function() {

                                // ir a resultado
                                $('#form-descarga-sync').submit();

                            });
                        }

                    },
                    error: function(data, textStatus, errorThrown) {
                        handleError(data);
                    }
                });

            });

        });
    }

    function sacarResumenInventarioBienSincronizar(inventarioBien, crearListas) {
        // NOTA: este metodo es una coppia de simio-lista.js (sacarResumenInventarioBien)

        var result = {
            totalBienesAsignados: 0,
            totalBienesInventariados: 0,
            totalBienesPorInventariar: 0,
            totalBienesFaltantes: 0,
            totalBienesSobrantes: 0,
            totalBienesTrasladados: 0,
            totalBienesListosParaSubir: 0,
            totalBienesListosQueNoSeSubiran: 0,
            listas: {
                bienesAsignados: [],
                bienesInventariados: [],
                bienesPorInventariar: [],
                bienesFaltantes: [],
                bienesSobrantes: [],
                bienesTrasladados: [],
                bienesListosParaSubir: [],
                bienesListosQueNoSeSubiran: []
            }
        };

        if ( !estaDefinido(inventarioBien) ) {
            return result;
        }

        $.each(inventarioBien, function (i, item) {

            // cuentas los 1 y los 0, mala suerte si vienen null o valores diferentes
            if ($.trim(item.revisadoToma) == '1') {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesInventariados.push(clon);
                }

                result.totalBienesInventariados++

            } else if ($.trim(item.revisadoToma) == '0') {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesPorInventariar.push(clon);
                }

                result.totalBienesPorInventariar++;
            }

            // cuenta las S y las F, mala suerte si vienen null o valores diferentes
            if ($.trim(item.sobranteFaltante) == 'S') {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesSobrantes.push(clon);
                }

                result.totalBienesSobrantes++;

            } else if ($.trim(item.sobranteFaltante) == 'F') {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesFaltantes.push(clon);
                }

                result.totalBienesFaltantes++;

            } else if ($.trim(item.sobranteFaltante) == 'T') {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesTrasladados.push(clon);
                }

                result.totalBienesTrasladados++;
            }

            consoleLog('patrimonio: ' + item.codigoPatrimonial + ', revisadoToma: ' + item.revisadoToma + ", estadoSubida: " + item.estadoSubida );

            // lista adicionales, listas para subir y las que no se subiran
            if ($.trim(item.revisadoToma) == '1' && $.trim(item.estadoSubida) == 'N') {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesListosParaSubir.push(clon);
                }

                result.totalBienesListosParaSubir++;

            } else {

                if (crearListas) {
                    var clon = {};
                    clonarObject(item, clon);
                    result.listas.bienesListosQueNoSeSubiran.push(clon);
                }

                result.totalBienesListosQueNoSeSubiran++;
            }

            if (crearListas) {
                var clon = {};
                clonarObject(item, clon);
                result.listas.bienesAsignados.push(clon);
            }

            result.totalBienesAsignados++;
        });

        return result;
    }

</script>
