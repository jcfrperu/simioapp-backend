/* ========================================================================
 * simioapp 1.0
 * ========================================================================
 * Copyright SiMiOS Coders
 * ======================================================================== */

function preventDefaultEvent(event) {
	if (estaDefinido(event)) {
		event.preventDefault();
	}
}

function consoleLog(consoleText) {
	if (window.console && window.console.log) {
		console.log(consoleText);
	}
}

function formToObject(formID, incluirDisabled) {

	// convierte todos los campos de un formulario a un objeto javascript.
	// se usa para poder deserializar directamente con los beans view object

	var formularioObject = {};
	var formularioArray = $('#' + formID).serializeArray();

	$.each(formularioArray, function (i, v) {
		formularioObject[v.name] = v.value;
	});

	if (estaDefinido(incluirDisabled) && incluirDisabled) {
		$('#' + formID).find('input:disabled, select:disabled').each(function (i, v) {
			formularioObject[v.name] = v.value;
		});
	}

	return formularioObject;
}

function copiarValores(fuenteFrom, destinoTo, usarTrim) {
	// fuenteFrom -> destinoTo
	// fuenteFrom es el objeto javascript que contiene los datos a copiarValores
	// destinoTo es el objeto javascript que va recibir los datos de fuenteFrom
	// usarTrim, si se desea convertir todo a string, y si son cadenas quitar los espacios

	var trimear = estaDefinido(usarTrim) ? usarTrim : false;

	// copia solo los campos de destinoTo(object) que existen en fuenteFrom (los demas que tenga destinoTo los ignora)
	$.each(destinoTo, function (key, value) {

		if (estaDefinido(fuenteFrom[key])) {

			destinoTo[key] = trimear ? $.trim(fuenteFrom[key]) : fuenteFrom[key];
		}
	});
}

function clonarObject(fuenteFrom, destinoTo) {

    if (!estaDefinido(fuenteFrom) || !estaDefinido(destinoTo)) {
        return;
    }

    $.each(fuenteFrom, function (key, value) {
        destinoTo[key] = fuenteFrom[key];
    });
}

function huboErrorJson(data) {

    // solo si viene definido data, data.estado
    if (estaDefinido(data) && estaDefinido(data.estado)) {

		// data.estado = { ok, error, errorValidacio }
        if (data.estado != 'ok') {
            return true;
        }
    }

    return false;
}

function huboErrorValidacionJson(data) {
	// TODO/FIXME: revisar este metodo, no tiene el mismo comportamiento de huboErrorJson()
    return data != null && data.estado == 'errorValidacion';
}

function handleErrorJson(data) {

    // si no viene data
	if (!estaDefinido(data)) {

        showMensaje('Mensaje', 'Respuesta JSON no seteada');
        return true;
    }

    // si no viene estado
	if (!estaDefinido(data.estado)) {

        showMensaje('Mensaje', 'Atributo estado de respuesta JSON no seteado');
        return true;
    }

    // si viene error de aplicacion (data.estado = { ok, error, errorValidacio } )
    if (data.estado != 'ok') {
        consoleLog('error app -> ' + data.msg);
        showMensaje('Mensaje', data.msg);
        return true;
    }

    return false;
}

function handleErrorBD(info) {

	if (estaDefinido(info)) {

		var msg = 'Ha ocurrido un error en BD';

		if (estaDefinido(info.code)) {
			msg = msg + ', code: ' + info.code;
		}

		if (estaDefinido(info.message)) {
			msg = msg + ', message: ' + info.message;
		}

		if (estaDefinido(info.name)) {
			msg = msg + ', name: ' + info.name;
		}

		if (estaDefinido(info.type)) {
			msg = msg + ', type: ' + info.type;
		}

		consoleLog(msg);
	}
}

function handleError(error) {

	var msg = 'No se obtuvo respuesta del servidor';

	if (estaDefinido(error)) {
		msg = 'error -> status: ' + error.status + ', readyState: ' + error.readyState + ', statusText: ' + error.statusText;
	}

	showMensajeError('Mensaje', msg);
	consoleLog(msg);
}

function estaDefinido(valor) {
	return !(valor == null || (typeof valor == 'undefined'));
}

function getTrimValue(inputQuery) {
	return $.trim($(inputQuery).val());
}

function toNumero(valor, defaultValue) {
	// valor por default en caso que no valor no sea número
	var defaultValueResult = !esNumero(defaultValue) ? 0.0 : Number(defaultValue);

	return !esNumero(valor) ? defaultValueResult : Number(valor);
}

function esNumero(valor) {
	// que no sea null, ni blanco, ni indefinido y que pase la validación de jquery isNumeric
	return estaDefinido(valor) && valor != '' && $.isNumeric(valor);
}

function roundComasMilesString(valor, digitos) {
	var val = roundString(valor, digitos);

	var parts = val.toString().split(".");

	// formato coma como separador de miles, punto como separador decimal
	return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",") + (parts[1] ? "." + parts[1] : "");
}

function roundString(valor, digitos) {
	// redondear
	var round = roundNumero(digitos, parseFloat(valor));

	// formatear completando el nro de digitos en los decimales
	return round.toFixed(digitos) + '';
}

function roundNumero(valor, digitos) {

	return Math.round(parseFloat(valor) * Math.pow(10, digitos)) / Math.pow(10, digitos);
}

function recortarDigitosEnteros(numeroString, maxDigEnteros) {

	if (!estaDefinido(numeroString)) return numeroString;

	var num = (numeroString + '').replace(/,/g, '');

	var posComa = num.indexOf('.');

	if (posComa >= 0) {

		var izq = num.substring(0, posComa);
		var der = num.substring(posComa + 1, num.length);

		if (izq.length > maxDigEnteros) {
			izq = izq.substring(0, maxDigEnteros);
		}

		num = izq + '.' + der;

	} else {

		if (num.length > maxDigEnteros) {
			num = num.substring(0, maxDigEnteros);
		}

	}

	return num;
}

function esHoraValida(hora) {
	return isValidoFormatHour(hora);
}

function esFechaValida(fecha) {
	var fechaTrim = $.trim(fecha);
	return fechaTrim != '' && checkdate(fechaTrim);
}

function esFechaMayor(fecha1, fecha2) {
	var result = compararFechas(fecha1, fecha2);
	return result == 1;
}

function esFechaMayorIgual(fecha1, fecha2) {
	var result = compararFechas(fecha1, fecha2);
	return result == 1 || result == 0;
}

function esFechaMenor(fecha1, fecha2) {
	var result = compararFechas(fecha1, fecha2);
	return result == 2;
}

function esFechaMenorIgual(fecha1, fecha2) {
	var result = compararFechas(fecha1, fecha2);
	return result == 2 || result == 0;
}

function esFechaIgual(fecha1, fecha2) {
	var result = compararFechas(fecha1, fecha2);
	return result == 0;
}

function compararFechas(fecha1, fecha2) {
	// se crea un wrapper por un bug en el compara fecha de sunat
	return checkcomparafecha($.trim(fecha1), $.trim(fecha2));
}

function checkcomparafecha(fecha1, fecha2) {

	/* -1: err, 1: f1>f2, 2: f1<f2, 0: f1=f2 */
	if (!checkdate(fecha1) || !checkdate(fecha2)) return -1;

	var dia = fecha1.substring(0, 2)
    var mes = fecha1.substring(3, 5)
    var anho = fecha1.substring(6, 10)

    var fecha1x = anho + mes + dia

    var dia = fecha2.substring(0, 2)
    var mes = fecha2.substring(3, 5)
    var anho = fecha2.substring(6, 10)
    var fecha2x = anho + mes + dia

	return (fecha1x > fecha2x ? 1 : (fecha1x < fecha2x ? 2 : 0));
}

function checkdate(fecha) {

	var err = 0

	if (fecha.length != 10) err = 1

    var dia = fecha.substring(0, 2)
    var slash1 = fecha.substring(2, 3)
    var mes = fecha.substring(3, 5)
    var slash2 = fecha.substring(5, 6)
    var anho = fecha.substring(6, 10)

	if (dia < 1 || dia > 31) err = 1
	if (slash1 != '/') err = 1
	if (mes < 1 || mes > 12) err = 1
	if (slash1 == '/' && slash2 != '/') err = 1
	if (anho < 0 || anho > 2200) err = 1
	if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
		if (dia == 31) err = 1
	}

	if (mes == 2) {
		var g = parseInt(anho / 4)
		if (isNaN(g)) {
			err = 1
		}
		if (dia > 29) err = 1
		if (dia == 29 && ((anho / 4) != parseInt(anho / 4))) err = 1
	}

	return (!(err == 1));
}

function compararHoras(hora1, hora2) {

	/* -1: err, 1: f1>f2, 2: f1<f2, 0: f1=f2 */

	// validar que venga data
	if (!estaDefinido(hora1) || !estaDefinido(hora2)) return -1;

	var h1Split = $.trim(hora1).split(':');
	var h2Split = $.trim(hora2).split(':');

	// validar formato: validar que tenga un solo ':'
	if (h1Split == null || h1Split.length != 2) return -1;
	if (h2Split == null || h2Split.length != 2) return -1;

	// pasarlo todo a minutos
	var h1 = toNumero(h1Split[0]) * 60 + toNumero(h1Split[1]);
	var h2 = toNumero(h2Split[0]) * 60 + toNumero(h2Split[1]);

	if (h1 > h2) return 1;	// 1
	if (h1 < h2) return 2;	// 2

	return 0;
}

function convertirEnMinutos(horaHHMM) {

	// validar que venga data
	if (!estaDefinido(horaHHMM)) return -1;

	var horaSplit = $.trim(horaHHMM).split(':');
	// validar formato: validar que tenga un solo ':'
	if (horaSplit == null || horaSplit.length != 2) return -1;

	// pasarlo todo a minutos
	return toNumero(horaSplit[0]) * 60 + toNumero(horaSplit[1]);
}

function roundDiferenciaHoras(horaMayorHHMM, horaMenorHHMM) {
	// PRE: horaMayorHHMM > horaMenorHHMM (en formato HH:MM )

	// convertir a minutos
	var mayorMins = convertirEnMinutos(horaMayorHHMM);
	var menorMins = convertirEnMinutos(horaMenorHHMM);

	// convertir a horas y redondear a 1 digito
	return roundString((mayorMins - menorMins) / 60.0, 1);
}

function isValidoFormatDate(valueDate) {
	var formatDate = /^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$/;
	var isValid = formatDate.test(valueDate);
	if (isValid && (valueDate.indexOf("-") > -1)) {
		isValid = false;
	}
    return isValid;
}

function isValidoFormatHour(valueHour) {
	var formatHour = /^([0-1]?[0-9]|2[0-4]):([0-5][0-9])(:[0-5][0-9])?$/;
	return formatHour.test(valueHour);
}

function showMensaje(titulo, mensaje, functionAceptar) {
	return showMensajeModal(titulo, mensaje, functionAceptar, 'primary');
}

function showMensajeExito(titulo, mensaje, functionAceptar) {
	return showMensajeModal(titulo, mensaje, functionAceptar, 'exito');
}

function showMensajeError(titulo, mensaje, functionAceptar) {
	return showMensajeModal(titulo, mensaje, functionAceptar, 'error');
}

function showMensajeWarning(titulo, mensaje, functionAceptar) {
	return showMensajeModal(titulo, mensaje, functionAceptar, 'warning');
}

function showMensajeInfo(titulo, mensaje, functionAceptar) {
	return showMensajeModal(titulo, mensaje, functionAceptar, 'info');
}

function showMensajeDefault(titulo, mensaje, functionAceptar) {
	return showMensajeModal(titulo, mensaje, functionAceptar, 'default');
}

function showMensajeModal(titulo, mensaje, functionAceptar, tipoMensaje) {

	// NOTA: En este sistema, no se usa el parámetro título.

	// por si se desea manejar los colores de bootstrap
	if (tipoMensaje == 'exito' || tipoMensaje == 'success') {
		$('#divPopupPanelClass').prop('class', 'panel panel-success');
        $('#btnPopupAceptar').prop('class', 'btn btn-success btn-md');
	} else if (tipoMensaje == 'error' || tipoMensaje == 'danger') {
		$('#divPopupPanelClass').prop('class', 'panel panel-danger');
        $('#btnPopupAceptar').prop('class', 'btn btn-danger btn-md');
	} else if (tipoMensaje == 'warning') {
		$('#divPopupPanelClass').prop('class', 'panel panel-warning');
        $('#btnPopupAceptar').prop('class', 'btn btn-warning btn-md');
	} else if (tipoMensaje == 'info') {
		$('#divPopupPanelClass').prop('class', 'panel panel-info');
        $('#btnPopupAceptar').prop('class', 'btn btn-info btn-md');
	} else if (tipoMensaje == 'default') {
		$('#divPopupPanelClass').prop('class', 'panel panel-default');
        $('#btnPopupAceptar').prop('class', 'btn btn-default btn-md');
	} else {
		$('#divPopupPanelClass').prop('class', 'panel panel-primary');
        $('#btnPopupAceptar').prop('class', 'btn btn-primary btn-md');
	}

	var mensajeTrim = $.trim(mensaje);

	if (mensajeTrim.length < 50) {
		$('#divPopupContainerClass').prop('class', 'container appMsgConfirmContainer verticalAlignmentHelper');
	} else {
		$('#divPopupContainerClass').prop('class', 'container appMsgConfirmContainerBigger verticalAlignmentHelper');
	}

	if ($('#divModalPopup').length) {

		// si se tiene el div de popup
		$('#divPopupMensaje').html(mensajeTrim);

		$('#divModalPopup').modal({
			keyboard: false
		});

		$('#btnPopupAceptar').off('click');
		if (estaDefinido(functionAceptar)) {
			$('#btnPopupAceptar').on('click', functionAceptar);
		}

		// pone el foco el boton aceptar, y de paso fix el bug que deja el foco
		// en algún control de la pantalla padre y puede efectuar operaciones con él.
		setTimeout(function () {
			// btnPopupAceptar
			$('#btnPopupAceptar').focus();
		}, 250);

	} else {

		// sino imprimir un simple alert
		alert(mensaje);
	}
}

function showMensajeConfirm(titulo, mensaje, functionAceptar, functionCancelar) {

	// NOTA: En este sistema, no se usa el parámetro título.
	var mensajeTrim = $.trim(mensaje);

	if (mensajeTrim.length < 50) {
		$('#divPopupContainerClassSINO').prop('class', 'container appMsgConfirmContainer verticalAlignmentHelper');
	} else {
		$('#divPopupContainerClassSINO').prop('class', 'container appMsgConfirmContainerBigger verticalAlignmentHelper');
	}

	$('#divPopupPanelClassSINO').prop('class', 'panel panel-primary');

	if ($('#divModalPopupSINO').length) {

		// si se tiene el div de popup
		$('#divPopupMensajeSINO').html(mensajeTrim);

		$('#divModalPopupSINO').modal({
			keyboard: false
		});

		$('#btnPopupAceptarSINO').off('click');
		if (estaDefinido(functionAceptar)) {
			$('#btnPopupAceptarSINO').on('click', functionAceptar);
		}

		$('#btnPopupCancelarSINO').off('click');
		if (estaDefinido(functionCancelar)) {
			$('#btnPopupCancelarSINO').on('click', functionCancelar);
		}

		// pone el foco el boton aceptar, y de paso fix el bug que deja el foco
		// en algún control de la pantalla padre y puede efectuar operaciones con él.
		setTimeout(function () {
			$('#btnPopupAceptarSINO').focus();
		}, 250);

	}
}

function showConfirmation(titulo, mensaje, functionAceptar, functionCancelar) {
	showMensajeConfirm( titulo, mensaje, functionAceptar, functionCancelar );
}

function makeInputMask( controlQuery, mask, maxlength, valorInicial, caracterMascara ) {
	
	var control = $( controlQuery );
	
	var caracter_mask = '';
	if ( caracterMascara != null )  caracter_mask = caracterMascara;
	
	control.inputmask( mask, {placeholder: caracter_mask});
	
	if ( maxlength != null ) {
		control.prop('maxlength', maxlength);	
	}
	
	if ( valorInicial != null ) {
		control.val( valorInicial );	
	}
	
}

// METODO PARA MANEJAR GRILLAS PROGRAMATICAMENTE
function cleanGrilla( tableID ) {

    if ( estaDefinido( tableID ) ) {
        $( tableID ).dataTable().fnDestroy();
    }
}

function initDG(tableID, showPaginar, showSearch, showOrdering, configColumns, configColumnDefs) {

    cleanGrilla( tableID );

    var grillaObject = $(tableID).dataTable({
        "lengthMenu": [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "responsive": true,
        "bAutoWidth": false,
        "paging": estaDefinido( showPaginar ) && showPaginar,
        "searching": estaDefinido( showSearch ) && showSearch,
        "ordering": estaDefinido( showOrdering ) && showOrdering,
        "language": {
            "url": "libs/datatables/idiomas/Spanish.json"
        },
        "columns": estaDefinido( configColumns ) ? configColumns : null,
        "columnDefs": estaDefinido( configColumnDefs ) ? configColumnDefs : null
    });

    /*
     $(window).on('resize', function () {
     grillaObject.fnAdjustColumnSizing();
     });
     */

    return grillaObject;
}

function initDataDG(tableID, dataObject, showPaginar, showSearch, showOrdering, configColumns, configColumnDefs, initComplete, lengthMenu) {

    cleanGrilla( tableID );

    var grillaObject = $(tableID).dataTable({
        "lengthMenu": estaDefinido( lengthMenu ) ? lengthMenu : [
            [10, 25, 50, -1],
            [10, 25, 50, "All"]
        ],
        "responsive": true,
        "bAutoWidth": false,
        "data": dataObject,
        "paging": estaDefinido( showPaginar ) && showPaginar,
        "searching": estaDefinido( showSearch ) && showSearch,
        "ordering": estaDefinido( showOrdering ) && showOrdering,
        "language": {
            "url": "libs/datatables/idiomas/Spanish.json"
        },
        "columns": estaDefinido( configColumns ) ? configColumns : null,
        "columnDefs": estaDefinido( configColumnDefs ) ? configColumnDefs : null,
        "initComplete": estaDefinido( initComplete ) ? initComplete : null
    });

    /*
     $(window).on('resize', function () {
     grillaObject.fnAdjustColumnSizing();
     });
     */

    return grillaObject;
}

function initGrillaIE8( tableID, dataGrilla, tamPag, orderingParam, searchingParam, pagingParam, columnsParams, columnDefsParams, lengthChangeParam ) {

    // destruye la grilla
    cleanGrilla( tableID );

    // construir la grilla
    var table = $( tableID ).dataTable({
        "data": dataGrilla,
        "ordering": !estaDefinido( orderingParam )? false : orderingParam,
        "searching": !estaDefinido( searchingParam )? false : searchingParam,
        "paging": !estaDefinido( pagingParam )? true : pagingParam,
        "bScrollAutoCss": true,
        "bStateSave": false,
        "bAutoWidth": false,
        "bScrollCollapse": false,
        "pagingType": "simple_numbers",
        "iDisplayLength": tamPag,
        "aLengthMenu": [[5, 10, 25, 50], [5, 10, 25, 50]],
        "responsive": true,
        "bLengthChange": !estaDefinido( lengthChangeParam )? false : lengthChangeParam,
        "fnDrawCallback": function(oSettings) {
            if (oSettings.fnRecordsTotal() == 0) {
                $( tableID + '_paginate').addClass('hiddenDiv');
            } else {
                $( tableID + '_paginate').removeClass('hiddenDiv');
            }
        },
        "language": {
            "url": "libs/datatables/idiomas/Spanish.json"
        },
        "columns": columnsParams,
        "columnDefs": columnDefsParams
    });

    return table;
}

// OTROS METODOS
function estadoInputInicial(divID) {

    ocultarMsgGeneralError();

    if ($(divID + ' :input').hasClass('date-picker') || $(divID + ' :input').hasClass('clas_email')) {
        $(divID + ' :input').parent().parent().find('span:last').html('');
        $(divID + ' :input').parent().parent().find('p:last').html('');
    } else {
        $(divID + ' :input').parent().find('span:last').html('');
    }

    $(divID + ' :input').closest('.form-group').removeClass('has-error');
    $(divID + ' select').closest('.form-group').removeClass('has-error');
    $(divID + ' textarea').closest('.form-group').removeClass('has-error');

    return null;
}

function mostrarMsgGeneralError(msgTitulo, msgError) {

    // copiar la plantilla a la instancia
    $('#divMsgGeneralErrorInstance').html($('#divMsgGeneralError').html());

    // setear el mensaje en la plantilla
    $('#spanMsgGeneralError').html(estaDefinido(msgError) ? msgError : '');
    $('#spanMsgGeneralErrorTitulo').html(estaDefinido(msgTitulo) ? msgTitulo : '');

    // mostrar
    $('#divMsgGeneralErrorInstance').show();
}

function ocultarMsgGeneralError() {

    // copiar la plantilla a la instancia
    $('#divMsgGeneralErrorInstance').html($('#divMsgGeneralError').html());

    // limpiar el mensaje en la plantilla
    $('#spanMsgGeneralError').html('');

    // ocultar
    $('#divMsgGeneralErrorInstance').hide();
}

function estadoInputError(divID, data, estadoRestore) {

    //$('#divMsgGeneralError').hide();

    var msgError = data.msg;
    var campoError = data.dataJson.campoError;

    // sino viene con campo poco se puede hacer
    if (campoError == null) return;

    mostrarMsgGeneralError( 'Revise el formulario:', msgError);

    // si el campo es un error general, mostrarlo como popup
    if (campoError == 'general') {
        showMensaje( 'Mensaje', msgError);
        return;
    }

    var selInput = $(divID + ' input[name=' + campoError + ']');
    var selSelec = $(divID + ' select[name=' + campoError + ']');
    var selTextA = $(divID + ' textarea[name=' + campoError + ']');

    if (selInput.length) {

        selInput.focus();

		if ($(divID + ' input[name=' + campoError + ']').hasClass('date-picker') || $(divID + ' input[name=' + campoError + ']').hasClass('clas_email')) {
			selInput.parent().parent().find('span:last').html(msgError);
			selInput.parent().parent().find('p:last').html(msgError);
		} else {
			var spanFecha = selInput.parent().parent().find('span:last');
			if ( spanFecha.length && spanFecha.hasClass('help-block') ) {
				spanFecha.html(msgError);
			} else {
				selInput.parent().find('span:last').html(msgError);
			}
		}

        selInput.closest('.form-group').addClass('has-error');
        selInput.focus();
    }

    if (selSelec.length) {

        selSelec.focus();
        selSelec.next().html(msgError);
        selSelec.closest('.form-group').addClass('has-error');
        selSelec.focus();
    }

    if (selTextA.length) {

        selTextA.focus();
        selTextA.next().html(msgError);
        selTextA.closest('.form-group').addClass('has-error');
        selTextA.focus();
    }

    // FIXME: si se llega a utilizar, con el estadoRestore reestablecer la estructura de datos
}

function handleUploadErrorJson(inputControl, nameControl, data) {
    if (inputControl != null) {
        cleanControlError(inputControl);
        uploadResetInputFile(inputControl);
    }
    $('#' + nameControl + '-barra').html($('#divErrorUpload').html());
}

function handleUploadError(inputControl, nameControl, data) {
    if (inputControl != null) {
        cleanControlError(inputControl);
        uploadResetInputFile(inputControl);
    }
    $('#' + nameControl + '-barra').html($('#divErrorUpload').html());
    handleError(data);
}

function handleUploadSuccess(inputControl, nameControl, data) {
    // NOTA: inputControl, data no se usan (por ahora)
    $('#' + nameControl + '-barra').html($('#divExitoUpload').html());
}

function uploadResetInputFile(inputControl) {
    inputControl.wrap('<form>').parent('form').trigger('reset');
    inputControl.unwrap();
}

function cleanControlError(inputControl) {
    // limpiar estilo de error
    if (inputControl != null) {
        inputControl.next().html('');
        inputControl.closest('.form-group').removeClass('has-error');
    }
}

function habilitarControles( divID, habilitar ) {
	$( divID ).find( 'input, textarea, button, select' ).prop( 'disabled', !habilitar);
}

function habilitarControl( controlID, habilitar ) {
	$( controlID ).prop( 'disabled', !habilitar);
}

function habilitarMostrarControl(controlID, habilitar ) {

    $( controlID ).prop( 'disabled', !habilitar);

    // NOTA: ya no se hace sino que se deja de visualizar
    // hacerGrisBoton( controlID, !habilitar );
    mostrarControl(controlID, habilitar);
}

function hacerGrisBoton(controlID, hacerGris) {

    var classValue = $(controlID).prop('class');

    if (hacerGris) {

        $(controlID).removeClass('btn-primary').removeClass('btn-info').removeClass('btn-warning').removeClass('btn-danger').removeClass('btn-default');
        $(controlID).addClass('btn-default');

    } else {

        var estilo = $(controlID).data('estilo');

        if (estaDefinido(estilo)) {
            $(controlID).prop('class', estilo);
        }

    }
}

function mostrarControles(divID, mostrar) {

    if (mostrar) {
        $(divID).find('input, textarea, button, select').show();
    } else {
        $(divID).find('input, textarea, button, select').hide();
    }
}

function mostrarControl(controlID, mostrar) {

    if (mostrar) {
        $(controlID).show();
    } else {
        $(controlID).hide();
    }
}

function eventSelectOnTD(tableID, tipo) {

    // seleccionar un input ( 'radio' | 'checkbox' segun tipo ) clickeando en todo el td
    $(tableID + ' tbody').on('click', 'td', function(e) {
        var input = $(this).find('input:' + tipo);
        input.prop('checked', !input.prop('checked'));
    });

    $(tableID + ' input:radio').on('click', function(e) {
        e.stopPropagation();
    });

}

function eventSelectOnTR(tableID, offEvent) {

	// datatable
	var table = $( tableID ).DataTable();

	if ( estaDefinido(offEvent) && offEvent ) {
        $(tableID + ' tbody').off();
    }
	
    // seleccionar un input clickeando en todo el tr
    $(tableID + ' tbody').on('click', 'tr', function(e) {
    	
    	var tr = $(this);
    	
    	// cambiar de estilo seleccionado/deseleccionado
		if ( tr.hasClass('info') ) {
			tr.removeClass('info');
		} else {
			table.$('tr.info').removeClass('info');
			tr.addClass('info');
		}
    	
    	// seleccionar/deseleccionar el radio button
        var inputArray = tr.find('td input:radio');
        if (inputArray.length && inputArray[0] != null) {
            var checked = $(inputArray[0]).prop('checked');
            $(inputArray[0]).prop('checked', !checked);
        }
        
    });

    if ( estaDefinido(offEvent) && offEvent ) {
        $(tableID + ' tbody tr td input:radio').off();
    }

    $(tableID + ' tbody tr td input:radio').on('click', function(e) {
    	
    	e.stopPropagation();
    	
    	var checked = $(this).prop('checked');
    	
    	consoleLog( 'checked: ' + checked );
    	
    	var tr = $(this).parent().parent();
    	
    	// cambiar de estilo seleccionado/deseleccionado
		if ( tr.hasClass('info') ) {
			tr.removeClass('info');
		} else {
			table.$('tr.info').removeClass('info');
			tr.addClass('info');
		}
 
    });
}

function extraerDataDG(tableID, arrayColumnsIgnorar) {

    var dataJson = $(tableID).DataTable().rows().data();

    // validar que haya data para exportar
    if (dataJson == null || dataJson == '' || dataJson.length == 0) {
        return null;
    }

    var data = [];
    var dataFila = {};
	var col = 0;
    var ignorarCol = false;

    for (var i = 0; i < dataJson.length; i++) {

        dataFila = {};
        col = 0;

        for (var j in dataJson[i]) {

            // bloque para ignorar columnas (como links, radio buttons, etc)
            ignorarCol = false;
            if (arrayColumnsIgnorar != null && arrayColumnsIgnorar.length > 0) {

                for (var indexIgnorar in arrayColumnsIgnorar) {

                    if (j == arrayColumnsIgnorar[indexIgnorar]) {
                        ignorarCol = true;
                        break;
                    }

                }

            }

            // si bandera ignorar es true continuar con la siguiente columna j
            if (ignorarCol) continue;

            //             	dataFila[ 'col' + col ] =  dataJson[i][j];
            dataFila['col' + j] = dataJson[i][j];

            col++;

        }

        data[i] = dataFila;

    }

    return {
        filas: (dataJson == null || dataJson.length == null ? 0 : dataJson.length),
        columnas: (col == null ? 0 : col),
        data: data
    };
}

function quote(text) {
    if (!estaDefinido(text)) return text;
    return '"' + text.replace('"', '""') + '"';
}

function cleanCaracter(text, caracterQuitar) {
    if (!estaDefinido(text)) return text;
    return text.replace(caracterQuitar, '');
}

function exportObjectJS(nombreArchivo, extension, objectJS) {

    if (!estaDefinido(nombreArchivo) || !estaDefinido(extension) || !estaDefinido(objectJS) ) {
        return;
    }

    var output = JSON.stringify(objectJS);

    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + output);
    element.setAttribute('download', nombreArchivo + "." + extension);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}


function exportArrayJSToCSV(nombreArchivo, extension, arrayColumnas, arrayData) {

    // exporta un arreglo de objetos (arrayData), considerando como cabecera solo un array de cadenas (arrayColumnas)
    // que representan las columnas pero estas columnas no necesariamente corresponden con las propiedades de los objetos.
    // la exportacion de los datos (arrayData), se hace con las propiedades en la lista arrayColumnas.

    if (!estaDefinido(arrayColumnas) || !estaDefinido(arrayData) || !estaDefinido(arrayData.length) || arrayData.length <= 0) {
        return;
    }

    var options = {
        filename: nombreArchivo + '.' + extension,
        appendTo: 'body',
        separator: '\t',
        newline: '\n',
        quoteFields: false,
        excludeColumns: '',
        excludeRows: ''
    };

    var output = '';

    // exportando la cabecera
    var nroColumn = 0;
    arrayColumnas.forEach(function (columna) {

        output += options.quoteFields ? quote(cleanCaracter(columna, options.separator) + '') : (cleanCaracter(columna, options.separator) + '');

        if (nroColumn != arrayColumnas.length - 1) {
            output += options.separator;
        } else {
            output += options.newline;
        }

        nroColumn++;
    });

    // Add the rows
    arrayData.forEach(function (fila) {
        nroColumn = 0;
        arrayColumnas.forEach(function (propiedad) {

            output += options.quoteFields ? quote(cleanCaracter(fila[propiedad], options.separator) + '') : (cleanCaracter(fila[propiedad], options.separator) + '');

            if (nroColumn != arrayColumnas.length - 1) {
                output += options.separator;
            } else {
                output += options.newline;
            }

            nroColumn++;
        });
    });

    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(output));
    element.setAttribute('download', options.filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}

function exportTableToCSV(nombreArchivo, extension, tableQuery) {

    var options = {
        filename: nombreArchivo + '.' + extension,
        appendTo: 'body',
        separator: '\t',
        newline: '\n',
        quoteFields: false,
        excludeColumns: '',
        excludeRows: ''
    };

    var output = '';
    var rows = $(tableQuery).find('tr').not(options.excludeRows);

    var numCols = rows.first().find("td,th").filter(":visible").not(options.excludeColumns).length;

    rows.each(function() {
        $(this).find("td,th").filter(":visible").not(options.excludeColumns)
            .each(function(i, col) {
                col = $(col);

                output += options.quoteFields ? quote(cleanCaracter(col.text(), options.separator)) : cleanCaracter(col.text(), options.separator);

                if(i != numCols-1) {
                    output += options.separator;
                } else {
                    output += options.newline;
                }
            });
    });

    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(output));
    element.setAttribute('download', options.filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}
