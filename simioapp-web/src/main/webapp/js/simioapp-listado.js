function clickBtnCargarListado() {

    // limpiar la busqueda anterior
    $('#txt_buscar_listado').val('');

    // abrir el div
    mostrarPagina('#div-vista-listado');

    // construir la grilla
    updateGrillaMisBienesListado();

    // asignar el evento a las filas de la grilla para que cambie de color
    eventSelectOnTR('#table-grilla-listado', true);

    // inicializar los demas eventos
    eventBuscarListado();
    eventFiltrarListado();
    eventExportExcel();

    // ocultar y mostrar sus botones
    habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
    habilitarMostrarControl('#btn-menu-izq-nuevo-bien', true);
    habilitarMostrarControl('#btn-menu-izq-editar-bien', true);
    habilitarMostrarControl('#btn-menu-izq-quitar-bien', true);
    habilitarMostrarControl('#btn-menu-izq-ver-bien', true);
    habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

    // ocultar el boton dentro de la grilla de buscar
    setTimeout(function () {
        $('#table-grilla-listado_filter').hide();
    }, 250);
}

function guardarEstiloBotonListado(controlID) {

    var classValue = $(controlID).prop('class');

    $(controlID).data('estilo', classValue);

    consoleLog('guardando el estilo en : ' + controlID);
}

function getSelectedIDGrillaListado() {

    var idRegistro = $('#table-grilla-listado').find('input[type=radio]:checked').val();

    return idRegistro;
}

function eventBuscarListado() {

    $('#btn_buscar_listado').off();
    $('#btn_buscar_listado').on('click', function (e) {

        e.preventDefault();

        $('#table-grilla-listado_filter input').val($('#txt_buscar_listado').val());
        $('#table-grilla-listado_filter input').trigger('input');

        seleccionandoSiEsUnicoListado();
    });
}

function seleccionandoSiEsUnicoListado() {

    setTimeout(function () {
        var radioButtons = $('#table-grilla-listado .radioButtonBien');
        if (radioButtons) {

            // seleccionar siempre el primer elemento
            radioButtons.first().trigger('click');

            // seleccionar si hay un elemnto
            // if (radioButtons.length == 1 && !radioButtons.first().prop('checked')) {
            //     radioButtons.first().trigger('click');
            // }
        }
    }, 250);
}

function eventExportExcel() {

    $('#btn_export_excel_all').off();
    $('#btn_export_excel_all').on('click', function (e) {

        e.preventDefault();

        exportTableToCSV( 'bienes', 'csv', '#table-grilla-listado');
    });
}

function eventFiltrarListado() {

    var tableGrillaID = '#table-grilla-listado';

    $('#txt_buscar_listado').off();
    $('#txt_buscar_listado').on('input', function (e) {

        e.preventDefault();

        $(tableGrillaID + '_filter input').val($(this).val());
        $(tableGrillaID + '_filter input').trigger('input');

        seleccionandoSiEsUnicoListado();
    });
}

function sortListaBienesParaGrillaPrincipal(listaMisBienes) {

    if ( !estaDefinido(listaMisBienes) ) return;

    listaMisBienes.sort(function (a,b) {

        var compareA = v.padLeft($.trim(a.estadoSubida), 5) +
            v.padLeft($.trim(a.revisadoToma), 5) +
            v.padLeft($.trim(a.codigoPatrimonial), 15) +
            v.padLeft($.trim(a.inventarioBienID), 15);

        var compareB = v.padLeft($.trim(b.estadoSubida), 5) +
            v.padLeft($.trim(b.revisadoToma), 5) +
            v.padLeft($.trim(b.codigoPatrimonial), 15) +
            v.padLeft($.trim(b.inventarioBienID), 15);

        if (compareA > compareB) {
            return 1;
        }

        if (compareA < compareB) {
            return -1;
        }

        return 0;
    });
}

function updateGrillaMisBienesListado() {

    // NOTA IMPORTANTE: este metodo toma los valores de RAM

    var listaMisBienes = gSimioRAM.tabla.inventarioBien;

    sortListaBienesParaGrillaPrincipal(listaMisBienes);

    initDataDG('#table-grilla-listado', listaMisBienes, false, true, true, [
        {"data": "inventarioBienID", "class": "text-center"},
        {"data": "codigoPatrimonial", "class": "text-center"},
        {"data": "denominacionBien", "class": "text-left"},
        {"data": "estadoBien", "class": "text-left"},
        {"data": "revisadoToma", "class": "text-center"},
        {"data": "estadoSubida", "class": "text-center"},
        {"data": "sobranteFaltante", "class": "text-center"},
        {"data": "localesID", "class": "text-left"},
        {"data": "areaID", "class": "text-left"},
        {"data": "oficinaID", "class": "text-left"},
        {"data": "codanterio", "class": "text-center"},
        {"data": "inventarioBienID", "class": "text-left"}
    ], [{
        "targets": 0, // inventarioBienID
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {
                return '<input type="radio" name="codigo" class="radioButtonBien" title="Seleccionar" value="' + $.trim(row.inventarioBienID) + '">';
            }

            return '';
        }

    }, {
        "targets": 1, // codigo patrimonial
        "render": function(data, type, row) {

            if (row != null && typeof row != 'undefined') {
                return '<span title="Inventario Bien ID: ' + $.trim(row.inventarioBienID) + ', Código Interno: ' + $.trim(row.codanterio) + ' ">' + $.trim(row.codigoPatrimonial) + '</span>';
            }

            return data;
        }
    }, {
        "targets": 3, // estadoBien
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarCatalogoListado('ESTADO_BIEN', row.estadoBien);

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
        "targets": 4, // revisadoToma
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarCatalogoListado('REVISADO_TOMA', row.revisadoToma);

                if (found != null) {

                    // primero buscar la descripcion larga
                    if (estaDefinido(found.descripcion)) {
                        return wrapIntoSpanAndTitle(row.revisadoToma, found.descripcion);
                    }

                    // sino muestra la corta
                    if (estaDefinido(found.descripcionCorta)) {
                        return wrapIntoSpanAndTitle(row.revisadoToma, found.descripcionCorta);
                    }
                }

            }

            return data;
        }

    }, {
        "targets": 5, // estadoSubida
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarCatalogoListado('ESTADO_SUBIDA', row.estadoSubida);

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
        "targets": 6, // sobranteFaltante
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarCatalogoListado('SOBRANTE_FALTANTE', row.sobranteFaltante);

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
        "targets": 7, // localesID
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarLocalesListado(row.localesID);

                if (found != null) {
                    if (estaDefinido(found.nombreLocal)) {
                        return wrapIntoSpanAndTitle(row.localesID, found.nombreLocal);
                    }
                }
            }

            return data;
        }

    }, {
        "targets": 8, // areaID
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarAreaListado(row.areaID);

                if (found != null) {
                    if (estaDefinido(found.nombreArea)) {
                        return wrapIntoSpanAndTitle(row.areaID, found.nombreArea);
                    }
                }
            }

            return data;
        }

    }, {
        "targets": 9, // oficinaID
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {

                var found = buscarOficinaListado(row.oficinaID);

                if (found != null) {

                    // si tiene descripcion completa de la oficina lo muestra
                    if (estaDefinido(found.nombreOficina)) {
                        return wrapIntoSpanAndTitle(row.oficinaID, found.nombreOficina);
                    }

                    // prueba con la abreviatura
                    if (estaDefinido(found.abreviaturaOficina)) {
                        return wrapIntoSpanAndTitle(row.oficinaID, found.abreviaturaOficina);
                    }
                }
            }

            return data;
        }

    }], function(settings, json) {

        consoleLog('grilla se ha cargado exitosamente')
        // seleccionandoSiEsUnicoListado();
    });
}

function wrapIntoSpanAndTitle(valor, description) {

    return '<span title="' + $.trim(valor) + ' - ' + $.trim(description) + '">' + $.trim(description) + '</span>';
}

function clickBtnQuitarBien(e) {

    e.preventDefault();

    var selectedID = getSelectedIDGrillaListado();

    if (!estaDefinido(selectedID)) {
        showMensaje('Mensaje', 'Por favor seleccione un registro de la lista');
        return null;
    }

    showConfirmation('Eliminar Bien', '\u00BFSeguro que desea eliminar el Bien?', function () {

        var found = buscarInventarioBienListado(selectedID);

        if (found != null) {

            if (!($.trim(found.estadoSubida) == 'N' && $.trim(found.sobranteFaltante) == 'S')) {
                showMensaje('', 'Sólo puedes eliminar bienes SIN SUBIR y marcados como SOBRANTE');
                return;
            }

            consoleLog('inventarioID: ' + found.inventarioID);
            consoleLog('inventarioBienID: ' + found.inventarioBienID);
            consoleLog('codigoPatrimonial: ' + found.codigoPatrimonial);

            consoleLog('clickBtnQuitarBien: ' + selectedID);

            var result = eliminarBienFromLista(gSimioRAM.tabla.inventarioBien, 'inventarioBienID', selectedID, function() {

                updateGrillaMisBienesListado();

                habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
                habilitarMostrarControl('#btn-menu-izq-nuevo-bien', true);
                habilitarMostrarControl('#btn-menu-izq-editar-bien', true);
                habilitarMostrarControl('#btn-menu-izq-quitar-bien', true);
                habilitarMostrarControl('#btn-menu-izq-ver-bien', true);
                habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

                setTimeout(function () {
                    $('#table-grilla-listado_filter').hide();
                }, 250);

            }, function() {

                showMensajeError('', 'No se pudo borrar el bien: ' + result.mensajeError);
            });
        }

    });
}

function eliminarBienFromLista(inventarioBien, filtroCampoIgual, filtroValorIgual, functionOK, functionError) {

    var result = {
        encontrado: 'no',
        borrado: 'no',
        mensajeError: ''
    };

    if ( !estaDefinido(inventarioBien) || !estaDefinido(filtroCampoIgual) || !estaDefinido(filtroValorIgual) ) {
        result.mensajeError = 'Parámetros incorrectos: inventarioBien, filtroCampoIgual, filtroValorIgual';
        functionError(result);
        return;
    }

    // var inventarioBienDelete = $.grep( inventarioBien, function(item, i) {
    //    return item[filtroCampoIgual] != filtroValorIgual;
    // });

    var posDelete = -1;
    $.each(inventarioBien, function (i, item) {
        if ( item[filtroCampoIgual] == filtroValorIgual ) {
            posDelete = i;
            return false;
        }
    });

    if (posDelete >= 0) {

        gSimioDAO.inventarioBien.deleteItem(inventarioBien[posDelete][filtroCampoIgual], function() {

            inventarioBien.splice(posDelete, 1);

            result.encontrado = 'si';
            result.borrado = 'si';
            result.mensajeError = '';

            functionOK(result);

        }, function() {

            result.encontrado = 'si';
            result.borrado = 'no';
            result.mensajeError = 'No se pudo borrar el bien en la BD local';

            functionError(result);
        });

        return;
    }

    result.encontrado = 'no';
    result.borrado = 'no';
    result.mensajeError = 'No se encontró el bien en la lista de bienes';

    functionError(result);
}

function sacarResumenInventarioBien(inventarioBien, crearListas) {

    var result = {
        totalBienesAsignados: 0,
        totalBienesInventariados: 0,
        totalBienesPorInventariar: 0,
        totalBienesFaltantes: 0,
        totalBienesSobrantes: 0,
        totalBienesTrasladados: 0,
        totalBienesSubidos: 0,
        totalBienesSinSubir: 0,
        listas: {
            bienesAsignados: [],
            bienesInventariados: [],
            bienesPorInventariar: [],
            bienesFaltantes: [],
            bienesSobrantes: [],
            bienesTrasladados: [],
            bienesSubidos: [],
            bienesSinSubir: []
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

        // cuentas los S y los N, mala suerte si vienen null o valores diferentes
        if ($.trim(item.estadoSubida) == 'S') {

            if (crearListas) {
                var clon = {};
                clonarObject(item, clon);
                result.listas.bienesSubidos.push(clon);
            }

            result.totalBienesSubidos++

        } else if ($.trim(item.estadoSubida) == 'N') {

            if (crearListas) {
                var clon = {};
                clonarObject(item, clon);
                result.listas.bienesSinSubir.push(clon);
            }

            result.totalBienesSinSubir++;
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

function changeBuildExportarFormato( buttonClicked ) {

    var valueButton = $(buttonClicked).html();

    if ( valueButton == 'CSV' ) {

        $('#pFormatoExportar :button').first().prop('class', 'btn btn-primary btn-sm');
        $('#pFormatoExportar :button').last().prop('class', 'btn btn-default btn-sm');

    } else {

        $('#pFormatoExportar :button').first().prop('class', 'btn btn-default btn-sm');
        $('#pFormatoExportar :button').last().prop('class', 'btn btn-primary btn-sm');
    }

}


function buildResumenInformativoResumenToma(result) {

    var htmlBotones = '';

    htmlBotones += '<p id="pFormatoExportar">Formato: ';
    htmlBotones += '<button type="button" onclick="changeBuildExportarFormato(this);" class="btn btn-primary btn-sm">CSV</button>';
    htmlBotones += '<button type="button" onclick="changeBuildExportarFormato(this);" class="btn btn-default btn-sm">JSON</button>';
    htmlBotones += '</p>';
    htmlBotones += '<br/>';

    var html = '';

    // contenedor global
    html += '<div class="row">';
    html += '<div class="col-sm-12">';

    html += '<div class="panel panel-info">'; // inicio del panel-info
    html += '<div class="panel-heading"> <strong> RESUMEN DE MI TOMA DE INVENTARIO </strong> </div>';
    html += '<div class="panel-body">'; // iniciop del panel body
    html += '<div> &nbsp; </div>';

    // items resumen a mostrar
    html += '<button class="btn btn-primary" onclick="exportarBienesAsignadosListado();" title="Todos mis bienes asignados" type="button">MIS BIENES ASIGNADOS <span class="badge">' + result.totalBienesAsignados + '</span></button>';
    html += '<br /><br />';
    html += '<button class="btn btn-info" onclick="exportarBienesInventariadosListado();" title="Bienes que ya he inventariado" type="button">MIS BIENES INVENTARIADOS <span class="badge">' + result.totalBienesInventariados + '</span></button>&nbsp;&nbsp;';
    html += '<button class="btn btn-info"  onclick="exportarBienesFaltanInventariarListado();" title="Bienes que a&uacute;n no he inventariado" type="button">MIS BIENES QUE FALTAN INVENTARIAR <span class="badge">' + result.totalBienesPorInventariar + '</span></button>';
    html += '<br /><br />';
    html += '<button class="btn btn-success" onclick="exportarBienesSubidosListado();" title="Bienes que ya he subido (sincronizado)" type="button">MIS BIENES QUE YA FUERON SUBIDOS (SINCRONIZADOS) <span class="badge">' + result.totalBienesSubidos + '</span></button>&nbsp;&nbsp;';
    html += '<button class="btn btn-success"  onclick="exportarBienesSinSubirListado();" title="Bienes que a&uacute;n no he subido (sincronizado)" type="button">MIS BIENES QUE FALTAN SUBIR (FALTAN SINCRONIZAR) <span class="badge">' + result.totalBienesSinSubir + '</span></button>';
    html += '<br /><br />';
    html += '<button class="btn btn-warning" onclick="exportarBienesSobrantesListado();" title="Bienes marcados como sobrantes" type="button">MIS BIENES SOBRANTES <span class="badge">' + result.totalBienesSobrantes + '</span></button>&nbsp;&nbsp;';
    html += '<button class="btn btn-warning" onclick="exportarBienesFaltantesListado();" title="Bienes marcados como faltantes" type="button">MIS BIENES FALTANTES <span class="badge">' + result.totalBienesFaltantes + '</span></button>&nbsp;&nbsp;';
    html += '<button class="btn btn-warning" onclick="exportarBienesTrasladadosListado();" title="Bienes marcados como trasladados" type="button">MIS BIENES TRASLADADOS <span class="badge">' + result.totalBienesTrasladados + '</span></button>';

    html += '<div> &nbsp; </div>';
    html += '</div>'; // end del panel-body
    html += '</div>'; // end del panel-info

    html += '</div></div>'; // fin del contenedor global

    return htmlBotones + html;
}



function clickBtnCargarResumenToma() {

    // ocultar y mostrar sus botones
    habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
    habilitarMostrarControl('#btn-menu-izq-nuevo-bien', true);
    habilitarMostrarControl('#btn-menu-izq-editar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-quitar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-ver-bien', false);
    habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, false);

    var html = '';

    html += buildResumenInformativoResumenToma(result);
    html += '<br />';

    $('#div-vista-resumen').html(html);

    mostrarPagina('#div-vista-resumen');
}

function extraerPropiedadesInventarioBien(ignorarCamposInternos, ordenar) {

    var inventarioBienBlanco = gSimioMaker.makeInventarioBienObject(null, null);

    var listaPropiedades = [];
    $.each(inventarioBienBlanco, function (propiedad, valor) {
        if (ignorarCamposInternos) {
            if (propiedad != 'localInfo' && propiedad != 'localKey') {
                listaPropiedades.push(propiedad);
            }
        } else {
            listaPropiedades.push(propiedad);
        }
    });

    if (ordenar && listaPropiedades.length > 0) {
        listaPropiedades.sort();
    }

    return listaPropiedades;
}

function exportarBienesAsignadosListado() {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesAsignados.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-asignados', 'csv', columnas, result.listas.bienesAsignados);

    } else  {

        exportObjectJS('bienes-asignados', 'json', result.listas.bienesAsignados);
    }

}

function exportarBienesInventariadosListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesInventariados.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-inventariados', 'csv', columnas, result.listas.bienesInventariados);

    } else {

        exportObjectJS('bienes-inventariados', 'json', result.listas.bienesInventariados);
    }

}

function exportarBienesFaltanInventariarListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesPorInventariar.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-faltan-inventariar', 'csv', columnas, result.listas.bienesPorInventariar);

    } else {

        exportObjectJS('bienes-faltan-inventariar', 'json', result.listas.bienesPorInventariar);
    }
}

function exportarBienesSubidosListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesSubidos.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-subidos', 'csv', columnas, result.listas.bienesSubidos);

    } else {

        exportObjectJS('bienes-subidos', 'json', result.listas.bienesSubidos);
    }
}

function exportarBienesSinSubirListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesSinSubir.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-sin-subir', 'csv', columnas, result.listas.bienesSinSubir);

    } else {

        exportObjectJS('bienes-sin-subir', 'json', result.listas.bienesSinSubir);
    }

}

function exportarBienesFaltantesListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesFaltantes.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-faltantes', 'csv', columnas, result.listas.bienesFaltantes);

    } else {

        exportObjectJS('bienes-faltantes', 'json', result.listas.bienesFaltantes);
    }

}

function exportarBienesSobrantesListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesSobrantes.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-sobrantes', 'csv', columnas, result.listas.bienesSobrantes);

    } else {

        exportObjectJS('bienes-sobrantes', 'json', result.listas.bienesSobrantes);
    }
}

function exportarBienesTrasladadosListado(tipoExport) {

    if ( !estaDefinido(gSimioRAM) || !estaDefinido(gSimioRAM.tabla) || !estaDefinido(gSimioRAM.tabla.inventarioBien) ) {
        return;
    }

    var result = sacarResumenInventarioBien(gSimioRAM.tabla.inventarioBien, true);

    if ( result.listas.bienesTrasladados.length <= 0 ) {
        showMensajeError('', 'No hay datos para exportar');
        return;
    }

    // primary es el indicador del formato activo a exportar
    var esCSV = $('#pFormatoExportar :button').first().hasClass('btn btn-primary btn-sm');

    if ( esCSV ) {

        var columnas = extraerPropiedadesInventarioBien(true, true);

        exportArrayJSToCSV('bienes-trasladados', 'csv', columnas, result.listas.bienesTrasladados);

    } else {

        exportObjectJS('bienes-trasladados', 'json', result.listas.bienesTrasladados);
    }
}

function clickBtnCargarVerBien(e) {

    e.preventDefault();

    var selectedID = getSelectedIDGrillaListado();

    if (!estaDefinido(selectedID)) {

        showMensaje('Mensaje', 'Por favor seleccione un registro de la lista');
        return null;
    }

    var found = buscarInventarioBienListado(selectedID);

    if (found != null) {

        consoleLog('inventarioID: ' + found.inventarioID);
        consoleLog('inventarioBienID: ' + found.inventarioBienID);
        consoleLog('codigoPatrimonial: ' + found.codigoPatrimonial);

        doVerBienListado(found);
    }
}

function obtenerDescripcionDeCatalogo(found, valorCatalogo, wrap) {

    var descrip = '';

    if (found != null) {

        // primero buscar la descripcion larga
        if (descrip == '' && estaDefinido(found.descripcion)) {
            if (estaDefinido(wrap) && wrap) {
                descrip = wrapIntoSpanAndTitle(valorCatalogo, found.descripcion);
            } else {
                descrip = $.trim(found.descripcion);
            }
        }

        // sino muestra la corta
        if (descrip == '' && estaDefinido(found.descripcionCorta)) {
            if (estaDefinido(wrap) && wrap) {
                descrip = wrapIntoSpanAndTitle(valorCatalogo, found.descripcionCorta);
            } else {
                descrip = $.trim(found.descripcionCorta);
            }
        }
    }

    return descrip;
}

function doVerBienListado(inventarioBien) {

    // ocultar y mostrar sus botones del costado
    habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
    habilitarMostrarControl('#btn-menu-izq-nuevo-bien', true);
    habilitarMostrarControl('#btn-menu-izq-editar-bien', true); // ahora si puede editar cuando esta en ver, tomar el valor de la grilla oculta
    habilitarMostrarControl('#btn-menu-izq-quitar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-ver-bien', false);
    habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

    var codigoPatrimonial = $.trim(inventarioBien.codigoPatrimonial);
    var inventarioBienID = $.trim(inventarioBien.inventarioBienID);

    // var revisadoTomaDescrip = '';
    var revisadoTomaFound = buscarCatalogoListado('REVISADO_TOMA', inventarioBien.revisadoToma);
    var revisadoTomaDescrip = obtenerDescripcionDeCatalogo(revisadoTomaFound, inventarioBien.revisadoToma, true);

    // var estadoSubidaDescrip = '';
    var estadoSubidaFound = buscarCatalogoListado('ESTADO_SUBIDA', inventarioBien.estadoSubida);
    var estadoSubidaDescrip = obtenerDescripcionDeCatalogo(estadoSubidaFound, inventarioBien.estadoSubida, true);

    var inventarioApertura = buscarInventarioAperturaByPatrimonialListado(codigoPatrimonial);

    var html = '';

    // mostrar los campos de codigo patrimonial e inventario bien ID
    html += '<div class="row">';
    html += '   <div class="col-sm-5 col-sm-offset-1">';
    html += '       <div class="alert alert-success" role="alert">C&oacute;digo Pätrimonial: <strong>' + codigoPatrimonial + '</strong></div>';
    html += '   </div>';
    html += '   <div class="col-sm-5">';
    html += '       <div class="alert alert-info" role="alert">Inventario Bien ID: <strong>' + inventarioBienID + '</strong></div>';
    html += '   </div>';
    html += '</div>';

    // mostrar las descripciones de revisado toma y estado subida
    html += '<div class="row">';
    html += '   <div class="col-sm-5 col-sm-offset-1">';
    html += '       <div class="alert alert-warning" role="alert">Revisado Toma: <strong>' + revisadoTomaDescrip + '</strong></div>';
    html += '   </div>';
    html += '   <div class="col-sm-5">';
    html += '       <div class="alert alert-warning" role="alert">Estado Subida: <strong>' + estadoSubidaDescrip + '</strong></div>';
    html += '   </div>';
    html += '</div>';

    html += '<div class="row">';
    html += '<div class="col-sm-10 col-sm-offset-1">';

    // cabecera de la tabla
    html += '<table class="table table-bordered">';

    html += '<thead><tr class="active">';
    html += '<th class="text-center">Caracter&iacute;stica</th>';
    html += '<th class="text-center">Valor Inicial</th>';
    html += '<th class="text-center">Valor Actualizado en la Toma</th>';
    html += '</tr></thead>';

    html += '<tbody>';

    // crear una copia ordenada de inventarioBien
    var listaPropiedades = extraerPropiedadesInventarioBien(true, true);

    // limpiar y cargar de nuevo el html
    $.each(listaPropiedades, function (indice, key) {

        var valorOriginal = estaDefinido(inventarioApertura) ? inventarioApertura[key] : undefined; // validacion porque cuando se agrega, el bien no va existir en inventarioApertura
        var valorActualizado = inventarioBien[key]; // es lo mismo que value

        var valorColorListado = parseLabelColorListado(valorOriginal, valorActualizado);
        var valorPropiedadListado = parseToPropiedadListado(key);

        if (valorPropiedadListado != '') {

            // imprimir fila
            html += '<tr class="' + valorColorListado + '">';

            html += '<td title="' + key + '">';
            html += valorPropiedadListado;
            html += '</td>';

            html += '<td>';
            html += valorOriginal;
            html += '</td>';

            html += '<td>';
            html += valorActualizado;
            html += '</td>';

            html += '</tr>';
        }

    });

    html += '</tbody></table>';

    html += '</div></div>';

    $('#div-vista-ver').html(html);

    // mostrar la vista
    mostrarPagina('#div-vista-ver');
}

function parseLabelColorListado(valorOriginal, valorActualizadoEnToma) {

    // casos especiales con undefined
    if ( (typeof valorOriginal == 'undefined') && !(typeof valorActualizadoEnToma == 'undefined') ) {
        return 'success';
    }

    if ( !(typeof valorOriginal == 'undefined') && (typeof valorActualizadoEnToma == 'undefined') ) {
        return 'danger';
    }

    // casos especiales con null
    if ( valorOriginal == null && valorActualizadoEnToma != null) {
        return 'success';
    }

    if ( valorOriginal != null && valorActualizadoEnToma == null) {
        return 'danger';
    }

    // despues se igualan los undefined, los nulls y las cadenas vacias
    var original = $.trim(valorOriginal);
    var nuevo = $.trim(valorActualizadoEnToma);

    // si son iguales
    if (original == nuevo) {
        return 'default';
    }

    // todos los de abajo son diferentes

    // diferentes porque se agrego algo (verde)
    if (original == '' && nuevo != '') {
        return 'success';
    }

    // diferentes porque se quito algo (rojo)
    if (original != '' && nuevo == '') {
        return 'danger';
    }

    // diferentes porque sus valores son iguales
    return 'info';
}

function endsWithListado(fuente, pattern) {

    if (!estaDefinido(fuente) || !estaDefinido(pattern)) return false;

    var d = fuente.length - pattern.length;
    return d >= 0 && fuente.lastIndexOf(pattern) === d;
}

function parseToPropiedadListado(propiedad) {

    var prop = propiedad;

    if (!estaDefinido(prop)) return '';

    // si es la propiedad 'localKey' que es un objeto interno
    if ('localInfo' == prop || 'localKey' == prop) return '';

    // si termina con ID en mayuscula quitarlo
    if (endsWithListado(prop, 'ID')) prop = prop.substring(0, prop.length - 2) + "Id";

    return prop.replace(/([A-Z])/g, ' $1').replace(/^./, function (str) {
        return str.toUpperCase();
    });
}

// metodos de la seccion "ir modo online"
function clickBtnIrModoOnline() {

    // check si hay conexion
    checkConexionListado(function () {

        // data a enviar
        var dataSyncJSON = {};

        dataSyncJSON.tabla = {};
        dataSyncJSON.tabla.inventarioBien = gSimioRAM.tabla.inventarioBien;
        dataSyncJSON.tabla.oficina = gSimioRAM.tabla.oficina;
        dataSyncJSON.tabla.area = gSimioRAM.tabla.area;
        dataSyncJSON.tabla.locales = gSimioRAM.tabla.locales;
        dataSyncJSON.tabla.catalogo = gSimioRAM.tabla.catalogo;

        // el metodo de arriba asegura que este objeto sessionData y todos sus objetos internos existan
        var sessionData = gSimioRAM.tabla.session[0];

        var form = $('#form-ir-modo-online');

        form.find('input[name="usuario"]').val(sessionData.usuario);
        form.find('input[name="entidad"]').val(sessionData.entidad);
        form.find('input[name="inventario"]').val(sessionData.inventario);
        form.find('input[name="token"]').val(sessionData.token);
        form.find('input[name="dataSyncJSON"]').val(JSON.stringify(dataSyncJSON));

        form.submit();

    }, function () {

        showMensajeError('Mensaje', 'Comprueba que est&aacute;s conectado a Internet, y por favor vuelve a intentarlo');
    });
}

// metodos para seccion "conexion a internet" (se usa el prefijo cnx para evitar colision de metodos)
function clickBtnCargarComprobarConexion() {

    mostrarPagina('#div-vista-conexion');

    // ocultar y mostrar sus botones
    habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
    habilitarMostrarControl('#btn-menu-izq-nuevo-bien', false);
    habilitarMostrarControl('#btn-menu-izq-editar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-quitar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-ver-bien', false);
    habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

    cnxEstadoInicialConexion();
}

function clickBtnComprobarConexion() {

    // estado inicial
    habilitarControl('#btn_comprobar_conexion', false);
    $('#conexionMsgConectado').hide();
    $('#conexionMsgNoConectado').hide();

    // check si hay conexion
    checkConexionListado(function () {

        // mostrar respuesta de conectado
        $('#conexionMsgConectado').show();

        // volver a habilitar el boton
        habilitarControl('#btn_comprobar_conexion', true);

    }, function () {

        // mostrar respuesto de no conectado
        $('#conexionMsgNoConectado').show();

        // volver a habilitar el boton
        habilitarControl('#btn_comprobar_conexion', true);
    });
}

function checkConexionListado(estaConectadoFunction, noConectadoFunction) {

    // revisar si tiene los atributos para realizar el check de conexion
    if (!estaDefinido(gSimioRAM) ||
        !estaDefinido(gSimioRAM.tabla) ||
        !estaDefinido(gSimioRAM.tabla.session) ||
        !estaDefinido(gSimioRAM.tabla.session[0])) {

        // lanzar funcion no conectado
        noConectadoFunction();
        return;
    }

    var sessionData = gSimioRAM.tabla.session[0];

    $.ajax({
        url: 'comprobar-conexion.generic',
        cache: false,
        data: {
            'usuario': sessionData.usuario,
            'entidad': sessionData.entidad,
            'inventario': sessionData.inventario,
            'token': sessionData.token
        },
        async: true,
        type: 'POST',
        dataType: 'json',
        success: function (data) {

            if (huboErrorJson(data)) {

                // la conexion estuvo bien, pero por algun motivo la app retorno error,
                // en ese caso tambien considerarlo como no conectado
                noConectadoFunction();
                return;
            }

            if (data.dataJson.status == 'ok') {

                // lanzar funcion OK
                estaConectadoFunction();
                return;
            }

            // lanzar funcion no conectado
            noConectadoFunction();
            return;

        },
        error: function (data, textStatus, errorThrown) {
            // lanzar funcion no conectado
            noConectadoFunction();
            return;
        }
    });
}

function cnxEstadoInicialConexion() {

    habilitarControl('#btn_comprobar_conexion', true);

    // inicializacion de controles para la seccion "conexion a internet"
    $('#conexionMsgConectado').hide();
    $('#conexionMsgNoConectado').hide();

    // lanzar el evento de comprobar conexion
    clickBtnComprobarConexion();
}

// metodos para buscar por ids
function existeTablaListado(nombreTabla) {

    if (!estaDefinido(nombreTabla)) return false;

    return estaDefinido(gSimioRAM) && estaDefinido(gSimioRAM.tabla) && estaDefinido(gSimioRAM.tabla[nombreTabla]);
}

function buscarInventarioBienListado(inventarioBienID) {

    if (!existeTablaListado('inventarioBien')) return null;

    // PRE: se entiende que en bd solo hay bienes de la entidad y del inventario en session
    var found = null;
    var idFind = $.trim(inventarioBienID);

    $.each(gSimioRAM.tabla.inventarioBien, function (i, item) {

        if ($.trim(item.inventarioBienID) == idFind) {
            found = item;
            return false;
        }
    });

    return found;
}

function buscarInventarioBienByPatrimonialListado(codigoPatrimonial) {

    if (!existeTablaListado('inventarioBien')) return null;

    // PRE: se entiende que en bd solo hay bienes de la entidad y del inventario en session
    var found = null;
    var idFind = $.trim(codigoPatrimonial);

    $.each(gSimioRAM.tabla.inventarioBien, function (i, item) {

        if ($.trim(item.codigoPatrimonial) == idFind) {
            found = item;
            return false;
        }
    });

    return found;
}

function buscarInventarioAperturaByPatrimonialListado(codigoPatrimonial) {

    if (!existeTablaListado('inventarioApertura')) return null;

    var found = null;
    var idFind = $.trim(codigoPatrimonial);

    $.each(gSimioRAM.tabla.inventarioApertura, function (i, item) {

        if ($.trim(item.codigoPatrimonial) == idFind) {
            found = item;
            return false;
        }
    });

    return found;
}

// NOTA: estos metodos de buscar por catalolgo, local, area y oficina, pueden optimizarse usando un mapa colocando al  primaryKey como clave del mapa
function buscarLocalesListado(localesID) {

    if (!existeTablaListado('locales')) return;

    var found = null;
    var idFind = $.trim(localesID);

    $.each(gSimioRAM.tabla.locales, function (i, item) {

        if ($.trim(item.localesID) == idFind) {
            found = item;
            return false;
        }
    });

    return found;
}

function buscarAreaListado(areaID) {

    if (!existeTablaListado('area')) return;

    var found = null;
    var idFind = $.trim(areaID);

    $.each(gSimioRAM.tabla.area, function (i, item) {

        if ($.trim(item.areaID) == idFind) {
            found = item;
            return false;
        }
    });

    return found;
}

function buscarOficinaListado(oficinaID) {

    if (!existeTablaListado('oficina')) return;

    var found = null;
    var idFind = $.trim(oficinaID);

    $.each(gSimioRAM.tabla.oficina, function (i, item) {

        if ($.trim(item.oficinaID) == idFind) {
            found = item;
            return false;
        }
    });

    return found;
}

function buscarCatalogoListado(catalogo, datacatalogo) {

    if (!existeTablaListado('catalogo')) return;

    var found = null;

    var idCatalogo = $.trim(catalogo);
    var idDatacatalogo = $.trim(datacatalogo);

    $.each(gSimioRAM.tabla.catalogo, function (i, item) {

        if (item.tipo == 'DET' && $.trim(item.catalogo) == idCatalogo && $.trim(item.datacatalogo) == idDatacatalogo) {
            found = item;
            return false;
        }
    });

    return found;
}