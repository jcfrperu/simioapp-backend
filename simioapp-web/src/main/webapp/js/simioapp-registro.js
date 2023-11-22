// Variable que determina si el boton otros esta habilitado o no [1 = Boton Habilitado ; 0 = Boton NO Habilitado / Valor por defecto]
var dtBtnOtro = 0;
// Variable que almacena el evento que se va ejecutar [N = Nuevo , E = Editar]
var evento = 'N';
//Variable que almacena el ID local del bien (localKey)
var localKey = '';

//Variable que contiene todos los datos existentes en BD cuando se selecciona un BIEN
var dataOld = "";

function initCombos() {
//    gSimioDAO.catalogoBien.eachItem(listarItemsCatalogoBien);
    // construir la grilla
	updateGrillaTipoBienesListado();

    // asignar el evento a las filas de la grilla para que cambie de color
	eventSelectOnTRGrillaTipoBien('#table-grilla-listado-tipo-bien', true);
	
}

function clickBtnCargarNuevoBien(e) {

    e.preventDefault();
	
	//Ordenando de manera ascendente la lista de elementos del combo
//    var options = $('#cbo_catalogo_bien option');
//    var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
//    arr.sort(function(o1, o2) {
//        // ordenacion especial para los espacios en blanco
//        if ( $.trim(o1.t) == '' && $.trim(o2.t) != '' ) return 1;
//        if ( $.trim(o1.t) != '' && $.trim(o2.t) == '' ) return -1;
//
//        // como estaba antes: ordenacion natural por cadena
//    	if ( o1.t > o2.t ) return  1;
//    	if ( o1.t < o2.t ) return -1;
//    	return 0;
//    });
//    options.each(function(i, o) {
//      o.value = arr[i].v;
//      $(o).text(arr[i].t);
//    });
    
	$('#div-buscar-codigo-patrimonial').show();
	$('#div-tipo-bien').show();
	
	evento = 'N';

    // ocultar y mostrar sus botones del costado
    habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
    habilitarMostrarControl('#btn-menu-izq-nuevo-bien', false);
    habilitarMostrarControl('#btn-menu-izq-editar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-quitar-bien', false);
    habilitarMostrarControl('#btn-menu-izq-ver-bien', false);
    habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

    mostrarPagina('#div-vista-registro');
    limpiarControles();

}

function limpiarControles(){
//	$('#txt_buscar_codigo_patrimonial').val("");
	$('#txt-mensaje-codigo-patrimonial').html("");
	$('#txt_denominacion').val("");
	$('#txt_denominacion').prop("readOnly",true);
	$('#txt_grupo_generico').val("");
	$('#txt_grupo_generico').prop("readOnly",true);
	$('#txt_clase').val("");
	$('#txt_clase').prop("readOnly",true);
	$('#txt_codigo_patrimonial').val("");
	$('#txt_codigo_patrimonial').prop("readOnly",true);
	$('#txt_codigo_interno').val("");
	$('#txt_codigo_interno').prop("readOnly",true);
	$('#txt_ult_correlativo').val("");
	$('#txt_ult_correlativo').prop("readOnly",true);
	$('#cbo_tipo_cuenta').val("P");
	$('#cbo_tipo_cuenta').attr( "readOnly" ,true);
	$('#txt_tipo_cuenta').val("");
	$('#txt_tipo_cuenta').prop("readOnly",true);
//	$('input:radio[name=rb_tipo_cuenta]:checked').val();
	$('#cbo_cuenta_contable').val("");
	$('#cbo_cuenta_contable').attr( "readOnly" ,true);
	$('#txt_des_cuenta_contable').val("");
	$('#txt_des_cuenta_contable').prop("readOnly",true);
	$('#cbo_forma_adquisicion').empty();
	$('#cbo_forma_adquisicion').attr( "readOnly" ,true);
	$('#txt_fecha_adquisicion').val("");
	$('#txt_fecha_adquisicion').prop("readOnly",true);
	$('#txt_resol_alta').val("");
	$('#txt_resol_alta').prop("readOnly",true);
	$('#txt_valor_adquisicion').val("");
	$('#txt_valor_adquisicion').prop("readOnly",true);
	$('#txt_valor_neto').val("");
	$('#txt_valor_neto').prop("readOnly",true);
	$('#chk_asegurado').prop('checked',false); 
	$('#cbo_estado_bien').val("#");
	$('#cbo_estado_bien').attr( "readOnly" ,true);
	$('#cbo_usuario').empty();
	$('#cbo_usuario').attr( "readOnly" ,true);
	habilitarControl('#btn_buscarEmpleado', false);
	$('#cbo_local').empty();
	$('#cbo_local').attr( "readOnly" ,true);
	$('#cbo_area').empty();
	$('#cbo_area').attr( "readOnly" ,true);
	$('#cbo_oficina').empty();
	$('#cbo_oficina').attr( "readOnly" ,true);
	$('#txt_marca').val("");
	$('#txt_marca').prop("readOnly",true);
	$('#txt_modelo').val("");
	$('#txt_modelo').prop("readOnly",true);
	$('#txt_tipo').val("");
	$('#txt_tipo').prop("readOnly",true);
	$('#txt_color').val("");
	$('#txt_color').prop("readOnly",true);
	$('#txt_serie').val("");
	$('#txt_serie').prop("readOnly",true);
	$('#txt_placa').val("");
	$('#txt_placa').prop("readOnly",true);
	$('#txt_numero_motor').val("");
	$('#txt_numero_motor').prop("readOnly",true);
	$('#txt_numero_chasis').val("");
	$('#txt_numero_chasis').prop("readOnly",true);
	$('#txt_otros').val("");
	$('#txt_otros').prop("readOnly",true);
	$('#txt_dimension').val("");
	$('#txt_dimension').prop("readOnly",true);
	$('#txt_anyo').val("");
	$('#txt_anyo').prop("readOnly",true);
	$('#txt_raza').val("");
	$('#txt_raza').prop("readOnly",true);
	$('#txt_nombre').val("");
	$('#txt_nombre').prop("readOnly",true);
	$('#txt_edad').val("");
	$('#txt_edad').prop("readOnly",true);
	$('#txt_DatosTecnicos_marcaFabricante').val("");
	$('#txt_DatosTecnicos_marcaFabricante').prop("readOnly",true);
	$('#txt_DatosTecnicos_modelo').val("");
	$('#txt_DatosTecnicos_modelo').prop("readOnly",true);
	$('#txt_DatosTecnicos_Tipo').val("");
	$('#txt_DatosTecnicos_Tipo').prop("readOnly",true);
	$('#txt_DatosTecnicos_NumeroMatricula').val("");
	$('#txt_DatosTecnicos_NumeroMatricula').prop("readOnly",true);
	$('#txt_DatosTecnicos_AnyoFabricacion').val("");
	$('#txt_DatosTecnicos_AnyoFabricacion').prop("readOnly",true);
	$('#txt_DatosTecnicos_NumeroSerie').val("");
	$('#txt_DatosTecnicos_NumeroSerie').prop("readOnly",true);
	$('#txt_DatosTecnicos_Dimensiones').val("");
	$('#txt_DatosTecnicos_Dimensiones').prop("readOnly",true);
	
}

function clickBtnCargarEditarBien(e) {

    e.preventDefault();

	$('#div-buscar-codigo-patrimonial').hide();
	$('#div-tipo-bien').hide();
	
	evento = 'E';
    dataOld = "";//Limpiamos variable

    // buscar la fila seleccionada
    var selectedID = getSelectedIDGrillaListado();

    // no selecciono nada
    if (!estaDefinido(selectedID)) {
        showMensaje('Mensaje', 'Por favor seleccione un elemento de la lista');
        return null;
    }

    // buscar la seleccion en la tabla RAM
    dataOld = buscarInventarioBienListado( selectedID );
    
    // si lo encontro
    if ( dataOld != null ) {
        consoleLog('inventarioID: ' +  dataOld.inventarioID);
        consoleLog('inventarioBienID: ' +  dataOld.inventarioBienID);
        consoleLog('codigoPatrimonial: ' +  dataOld.codigoPatrimonial);

        if ( $.trim(dataOld.estadoSubida) != 'N' ) {
            showMensaje('', 'S&oacute;lo puedes editar bienes SIN SUBIR');
            return;
        }

        // ocultar y mostrar sus botones del costado
        habilitarMostrarControl('#btn-menu-izq-listado-bienes', true);
        habilitarMostrarControl('#btn-menu-izq-nuevo-bien', false);
        habilitarMostrarControl('#btn-menu-izq-editar-bien', false);
        habilitarMostrarControl('#btn-menu-izq-quitar-bien', false);
        habilitarMostrarControl('#btn-menu-izq-ver-bien', false);
        habilitarMostrarControl('#btn-menu-izq-resumen-toma', true);

        mostrarPagina('#div-vista-registro');
        mostrarValoresEnFormularioEditar(dataOld);

    }
}

function initControlerBuscarBien(){
	
	$( '#btn_buscar_codigo_patrimonial' ).off();
    $( '#btn_buscar_codigo_patrimonial' ).on( 'click', function(e) {
    	e.preventDefault();
    	
    	/*Logica :Buscamos en inventarioBien y si encontramos el codigo patrimonial en esa tabla entonces lo que haremos
    	es una EDICION del bien.
    	Si no encontramos datos en la tabla inventarioBien (esta tabla solo contiene los bienes asignados y trabajados en el offline) 
    	entonces buscamos en la tabla inventarioApertura (esta tabla contiene todos los bienes TODOS). Si encontramos
    	datos en la tabla inventarioApertura entonces lo que haremos es AGREGAR un nuevo bien.
    	*/ 
    	    	
    	//Primero buscamos en inventarioBien
    	var inventarioBien = buscarInventarioBien( $('#txt_buscar_codigo_patrimonial').val() );
    	
    	// si lo encontro
    	if ( inventarioBien != null ) {
    		limpiarControles();
    		$('#div-tipo-bien').show();
    		$('#txt-mensaje-codigo-patrimonial').html('Tienes asignado este bien, por favor vaya a Listado bienes y editelo');
//    		evento = 'E'; //Si lo encontro en inventarioBien entonces se trata de una Edicion.
//    		consoleLog('El codigo patrimonial se encontro en inventarioBien. Actuara como un EDITAR');
//    		mostrarValoresEnFormularioEditar(inventarioBien);
    		
    	}else{//si no lo encuentra buscamos en inventarioApertura
    		
    		// buscar bien en la tabla RAM inventarioApertura
    	    var bienApertura = buscarBienApertura( $('#txt_buscar_codigo_patrimonial').val() );
        	  
        	// si lo encontro
        	if ( bienApertura != null ) {
        		$('#div-tipo-bien').hide();
        		$('#txt-mensaje-codigo-patrimonial').html('');
        		evento = 'N'; //Si lo encontro en inventarioApertura entonces se trata de un NUEVO REGISTRO que sera grabado finalmente en inventarioBien.
        		consoleLog('El codigo patrimonial se encontro en inventarioApertura. Actuara como un NUEVO');
        		mostrarValoresEnFormularioEditar(bienApertura);
        	}else{
        		limpiarControles();
        		$('#div-tipo-bien').show();
        		$('#txt-mensaje-codigo-patrimonial').html('C&oacute;digo Patrimonial no encontrado');
        	}
    	}
    	
    });
    
    $( '#txt_buscar_codigo_patrimonial' ).off();
    $( '#txt_buscar_codigo_patrimonial' ).on( 'input', function(e) {
    	e.preventDefault();
    	
    	/*Logica :Buscamos en inventarioBien y si encontramos el codigo patrimonial en esa tabla entonces lo que haremos
    	es una EDICION del bien.
    	Si no encontramos datos en la tabla inventarioBien (esta tabla solo contiene los bienes asignados y trabajados en el offline) 
    	entonces buscamos en la tabla inventarioApertura (esta tabla contiene todos los bienes TODOS). Si encontramos
    	datos en la tabla inventarioApertura entonces lo que haremos es AGREGAR un nuevo bien.
    	*/ 
    	
    	if($('#txt_buscar_codigo_patrimonial').val().length < 12){
    		$('#txt-mensaje-codigo-patrimonial').html('');
    		limpiarControles();
    		return;
    	} 
    	
    	//Primero buscamos en inventarioBien
    	var inventarioBien = buscarInventarioBien( $('#txt_buscar_codigo_patrimonial').val() );
    	
    	// si lo encontro
    	if ( inventarioBien != null ) {
    		limpiarControles();
    		$('#div-tipo-bien').show();
    		$('#txt-mensaje-codigo-patrimonial').html('Tienes asignado este bien, por favor vaya a Listado bienes y editelo');
//    		evento = 'E'; //Si lo encontro en inventarioBien entonces se trata de una Edicion.
//    		consoleLog('El codigo patrimonial se encontro en inventarioBien. Actuara como un EDITAR');
//    		mostrarValoresEnFormularioEditar(inventarioBien);
    		
    	}else{//si no lo encuentra buscamos en inventarioApertura
    		
    		// buscar bien en la tabla RAM inventarioApertura
    	    var bienApertura = buscarBienApertura( $('#txt_buscar_codigo_patrimonial').val() );
        	  
        	// si lo encontro
        	if ( bienApertura != null ) {
        		$('#div-tipo-bien').hide();
        		$('#txt-mensaje-codigo-patrimonial').html('');
        		evento = 'N'; //Si lo encontro en inventarioApertura entonces se trata de un NUEVO REGISTRO que sera grabado finalmente en inventarioBien.
        		consoleLog('El codigo patrimonial se encontro en inventarioApertura. Actuara como un NUEVO');
        		mostrarValoresEnFormularioEditar(bienApertura);
        	}else{
        		limpiarControles();
        		$('#div-tipo-bien').show();
        		$('#txt-mensaje-codigo-patrimonial').html('C&oacute;digo Patrimonial no encontrado');
        	}
    	}
    	
    });
}


//function initControlesBuscarNombreBien(){
//
//    $( '#txt_buscar_nombre_bien' ).off();
//    $( '#txt_buscar_nombre_bien' ).on( 'input', function(e) {
//        e.preventDefault();
//        consoleLog('coincide');
//
//        var ingresado = $.trim( $( '#txt_buscar_nombre_bien' ).val() ).toUpperCase();
//
//        // JOSE, esto parte la cadena
//        var ingresadoSplit = v.split(ingresado, ' ');
//        consoleLog(ingresadoSplit);
//
//        var ingresado_size = ingresado.length;
//
//        // deseleccionar todo
//        $('#cbo_catalogo_bien option').prop('selected', false);
//
//        // seleccionar el especifico
//        $.each($('#cbo_catalogo_bien option'), function(i, item) {
//
//            if ( $.trim(item.label).toUpperCase().substring(0, ingresado_size) == ingresado.substring(0, ingresado_size) ) {
//            	           	
//                $(item).prop('selected', true);
//                return false;
//            }
//        });
//
//    });
//}


function buscarBienApertura( codigoPatrimonial ) {
	
    // PRE: se entiende que en bd solo hay bienes de la entidad y del inventario en session
    var bienApertura = null;

    $.each(gSimioRAM.tabla.inventarioApertura, function(i, item) {

        if ( $.trim(item.codigoPatrimonial) == $.trim(codigoPatrimonial) ) {
        	bienApertura = item;
            return false;
        }
    });

    return bienApertura;
}

function buscarInventarioBien( codigoPatrimonial ) {
	
    // PRE: se entiende que en bd solo hay bienes de la entidad y del inventario en session
    var inventarioBien = null;

    $.each(gSimioRAM.tabla.inventarioBien, function(i, item) {

        if ( $.trim(item.codigoPatrimonial) == $.trim(codigoPatrimonial) ) {
        	inventarioBien = item;
            return false;
        }
    });

    return inventarioBien;
}

function mostrarValoresEnFormularioEditar(bien){
	
	var catalogoBien = getCatalogoBienById(bien.catalogoBienID);
//	$('#cbo_catalogo_bien').val(catalogoBien.codigoBien);
    $('#table-grilla-listado-tipo-bien_filter input').val(catalogoBien.codigoBien);
    $('#table-grilla-listado-tipo-bien_filter input').trigger('input');
    seleccionandoSiEsUnicoListadoTipoBien();
    
	llenarDatosEnFormulario(catalogoBien.codigoBien);
	habilitarControles();
	
	//Código Interno
	$('#txt_codigo_interno').val((bien.codigoPatrimonial).substring(8, 12));
	//Ultimo Correlativo
	$('#txt_ult_correlativo').val((bien.codigoPatrimonial).substring(8, 12));
	//Tipo de cuenta
	$('#txt_tipo_cuenta').val(bien.tipUsoCuenta);
	//Radio Button Tipo de cuenta
	$('input:radio[name=rb_tipo_cuenta]:checked').val(bien.tipoCuenta);
	//Cuenta contable
	$('#cbo_cuenta_contable').val(bien.numeroCuentaContable);
//VARIFICAR PORQUE NO SE MUESTRA LA DESCRIPCION DE LA CUENTA CONTABLE EN EL TEXTBOX txt_des_cuenta_contable
	//Forma de Adquisición
	$('#cbo_forma_adquisicion').val(bien.condicion);
	//Fecha
	$('#txt_fecha_adquisicion').val(bien.fechaAdquisicion);
	//Resol. Alta
	$('#txt_resol_alta').val(bien.numeroDocAdquisicion);
	//Valor de Adquisición
	$('#txt_valor_adquisicion').val(bien.valorAdquisicion);
	//Valor Neto
	$('#txt_valor_neto').val(bien.valorNeto);
	//Asegurado
	if(bien.cuentaConSeguro == "S"){$('#chk_asegurado').prop('checked',true);}else{$('#chk_asegurado').prop('checked',false);}
	//Estado del Bien
	$('#cbo_estado_bien').val(bien.estadoBien);
//Usuario - VERIFICAR PORQUE bien.empleadoID trae el correlativo y no el codigo de negocio del empleado
	var empleado = gSimioRAM.tabla.empleado;
	var cantidadRegistros = empleado.length;
	if(cantidadRegistros > 0){
		for(var i=0;i<cantidadRegistros;i++){
			var item = empleado[i];
			if(item.empleadoID == bien.empleadoID.toUpperCase()){
				$('#cbo_usuario').val(item.empleadoID);
				
				//Local
				localesAreaPorEmpleado = [];
				indexLocalesAreaPorEmpleado = 0;
				cargarComboLocales(item.empleadoID);
				$('#cbo_local').removeAttr( "readOnly" );
				$('#cbo_local').val(bien.localesID);
				
				//Área
				cargarComboAreas(bien.localesID);
				$('#cbo_area').removeAttr( "readOnly" );
				$('#cbo_area').val(bien.areaID);
				
				//Oficina
				cargarComboOficina(bien.areaID);
				$('#cbo_oficina').removeAttr( "readOnly" );
				$('#cbo_oficina').val(bien.oficinaID);
				
				break;
			}
		}
	}	
	//Marca
	if(dtBtnOtro == 0){$('#txt_marca').val(bien.marca);}else{$('#txt_DatosTecnicos_marcaFabricante').val(bien.marca);}
	//Modelo
	if(dtBtnOtro == 0){$('#txt_modelo').val(bien.modelo);}else{$('#txt_DatosTecnicos_modelo').val(bien.modelo);}
//Tipo - VERIFICAR QUE ESTE DATO SE GUARDA EN bien.tipoCausalAlta
	if(dtBtnOtro == 0){$('#txt_tipo').val(bien.tipoCausalAlta);}else{$('#txt_DatosTecnicos_Tipo').val(bien.tipoCausalAlta);}
	//Color
	$('#txt_color').val(bien.color);
	//Serie
	if(dtBtnOtro == 0){$('#txt_serie').val(bien.serie);}else{$('#txt_DatosTecnicos_NumeroSerie').val(bien.serie);}
	//Placa
	$('#txt_placa').val(bien.placa);
	//N° Motor
	$('#txt_numero_motor').val(bien.numeroMotor);
	//N° Chasis
	$('#txt_numero_chasis').val(bien.numeroChasis);
	//Dimension
	if(dtBtnOtro == 0){$('#txt_dimension').val(bien.dimension);}else{$('#txt_DatosTecnicos_Dimensiones').val(bien.dimension);}
	//Año
	$('#txt_anyo').val(bien.anho);
	//Raza
	$('#txt_raza').val(bien.raza);
//Nombre [X]
	$('#txt_nombre').val();
	//Edad
	 $('#txt_edad').val(bien.edad);
	//Otros
	 $('#txt_otros').val(bien.dscOtros);
	 
	 localKey = bien.inventarioBienID;
	
}

function clickBtnkAgregar(event) {

    preventDefaultEvent(event);
    
    var sessionData = gSimioRAM.tabla.session[0];
    
	if(validarDatosIngresados()){
		
		var accion = "I";
		var estadoSubida = "N";//No subido
		var sobranteFaltante = "S";//Sobrante
		var inventarioBienID = "";
		var situacionBienInventario = dataOld.sitBinv; // U = UBICADO ; N = NO UBICADO -> No lo estamos usanso este campo, siempre guardara como esta en BD
		var revisadoToma = "1"; // 1 = INVENTARIADO ; 0 = POR INVENTARIAR
		var catalogoBienID = "";//Solo se registra cuando es Nuevo. Cuando se edita no debe ser tocado este dato.
		var indiceCatalogoBien = getNombreCatalogoBienByCodBien(getSelectedIDGrillaListadoTipoBien());//jlunah
		var catalogoBien = gSimioRAM.tabla.catalogoBien[indiceCatalogoBien];
		catalogoBienID = catalogoBien.catalogoBienID;
		
		if(evento == 'N'){//Nuevo
			accion = 'I';//I: insertar
			localKey=asignarLocalKey();
			inventarioBienID = "";//localKey;
		}
		if(evento == 'E'){//Editar
			accion = 'A';//A: actualizar
			inventarioBienID = dataOld.inventarioBienID;//localKey;
			sobranteFaltante = "";
			/*-Si es un sobrante quiere decir que se agrego como nuevo. Para este caso, en el editar este campo no debe ser modificado.
			 * -Si es un faltante entonces se editó y se marco como faltante. Al editarse de nuevo no debe cambiarse esta marca.*/
			if(dataOld.sobranteFaltante == "S" || dataOld.sobranteFaltante == "F"){
				sobranteFaltante = dataOld.sobranteFaltante;
			}
		}
			
		var denominacion = $('#txt_denominacion').val();
		var grupoGenerico = $('#txt_grupo_generico').val(); //[X]
		var clase = $('#txt_clase').val();//[X]
		var codigoPatrimonial = $('#txt_codigo_patrimonial').val();
		var codigoInterno = $('#txt_codigo_interno').val();
		var ultCorrelativo = $('#txt_ult_correlativo').val();//[X]
		var tipoCuenta = evento == "N" ? "" : dataOld.tipUsoCuenta;//$('#txt_tipo_cuenta').val();
		var radioTipoCuenta = $('input:radio[name=rb_tipo_cuenta]:checked').val();//[X] //Devuelve valor de acuerdo a radio button seleccionado (RADIO BUTTON ACTIVO FIJO o RADIO BUTTON CUENTA DE ORDEN)
		var cuentaContable = $('#cbo_cuenta_contable').val();
		//La descripcion de la cuenta contable no se grabara
		var formaAdquisicion = $('#cbo_forma_adquisicion').val();
		var fechaAdquisicion = $('#txt_fecha_adquisicion').val();
		var resolAlta = $('#txt_resol_alta').val();
		var valorAdquisicion = $('#txt_valor_adquisicion').val();
		var valorNeto = $('#txt_valor_neto').val();
		var asegurado = $('#chk_asegurado').prop('checked'); //Devuelve true si esta checkeado y false caso contrario (CHECK ASEGURADO)
		
		var estadoBien = $('#cbo_estado_bien').val();
		if(evento == "E" && estadoBien == "*"){//El valor * significa que el bien no ha sido ubicado
			estadoBien = dataOld.estadoBien;// El estado se deja como esta en BD ya que NO UBICADO se ha colado en este combo XD
			sobranteFaltante = "F"; // F = FALTANTE
		}else{
			estadoBien = $('#cbo_estado_bien').val();	
		}
		
		var usuario = $('#cbo_usuario').val();
		var local = $('#cbo_local').val();
		var area = $('#cbo_area').val();
		var oficina = $('#cbo_oficina').val();
		var marca = $('#txt_marca').val();
		var modelo = $('#txt_modelo').val();
		var tipo = $('#txt_tipo').val();
		var color = $('#txt_color').val();
		var serie = $('#txt_serie').val();
		var placa = $('#txt_placa').val();
		var numeroMotor = $('#txt_numero_motor').val();
		var numeroChasis = $('#txt_numero_chasis').val();
		var otros = $('#txt_otros').val();
		
		/*Inicio 05/12/2016*/
		var dimension = $('#txt_dimension').val();
		var anyo = $('#txt_anyo').val();
		var raza = $('#txt_raza').val();
		var nombre = $('#txt_nombre').val();//[X]
		var edad = $('#txt_edad').val();
		/*Fin 05/12/2016*/
		
		/*Inicio 05/12/2016*/
		/*Datos Tecnicos*/
		var dtMarcaFabricante = $('#txt_DatosTecnicos_marcaFabricante').val();
		var dtModelo = $('#txt_DatosTecnicos_modelo').val();
		var dtTipo = $('#txt_DatosTecnicos_Tipo').val();
		var dtNumeroMatricula = $('#txt_DatosTecnicos_NumeroMatricula').val();
		var dtAnyoFabricacion = $('#txt_DatosTecnicos_AnyoFabricacion').val();
		var dtNumeroSerie = $('#txt_DatosTecnicos_NumeroSerie').val();
		var dtDimension = $('#txt_DatosTecnicos_Dimensiones').val();
		/*Fin 05/12/2016*/
		
		var tipoCausalAlta = "";
		if(dtBtnOtro == 0){tipoCausalAlta = tipo;}else{tipoCausalAlta = dtTipo;}
		
		var cuentaConSeguro = "";
		if(asegurado == true){cuentaConSeguro = "S";}else{cuentaConSeguro = "N";}
		
		var finalDimension = "";
		if(dtBtnOtro == 0){finalDimension = dimension;}else{finalDimension = dtDimension;}
		
		var finalModelo = "";
		if(dtBtnOtro == 0){finalModelo = modelo;}else{finalModelo = dtModelo;}
		
		var finalMarca = "";
		if(dtBtnOtro == 0){finalMarca = marca;}else{finalMarca = dtMarcaFabricante;}
		
		var finalSerie = "";
		if(dtBtnOtro == 0){finalSerie = serie;}else{finalSerie = dtNumeroSerie;}
		
		/*Otros datos*/
		var bienID = evento == "N" ? "" : dataOld.bienID;
		
		var inventarioID = evento == "N" ? "" : dataOld.inventarioID;
		var inventariador = evento == "N" ? "" : dataOld.inventariador;
		var entidadID = evento == "N" ? "" : dataOld.entidadID;
		var ancho = evento == "N" ? "" : dataOld.ancho;
		var entDisp = evento == "N" ? "" : dataOld.entDisp;
		var entArre = evento == "N" ? "" : dataOld.entArre;
		var fechaVafec = evento == "N" ? "" : dataOld.fechaVafec;
		var motivoEliminacionBien = evento == "N" ? "" : dataOld.motivoEliminacionBien;
		var resolAfec = evento == "N" ? "" : dataOld.resolAfec;
		var especie = evento == "N" ? "" : dataOld.especie;
		var fechaAfec = evento == "N" ? "" : dataOld.fechaAfec;
		var flgActo = evento == "N" ? "" : dataOld.flgActo;
		var flgCausal = evento == "N" ? "" : dataOld.flgCausal;
		var numeroResolucionBaja = evento == "N" ? "" : dataOld.numeroResolucionBaja;
		var resolArre = evento == "N" ? "" : dataOld.resolArre;
		var fechaArre = evento == "N" ? "" : dataOld.fechaArre;
		var fechaDisposicion = evento == "N" ? "" : dataOld.fechaDisposicion; 
		var resolDisp = evento == "N" ? "" : dataOld.resolDisp; 
		var altura = evento == "N" ? "" : dataOld.altura; 
		var valortasa = evento == "N" ? "" : dataOld.valortasa; 
		var estBien = evento == "N" ? "" : dataOld.estBien; 
		var entAfec = evento == "N" ? "" : dataOld.entAfec; 
		var estGestio = evento == "N" ? "" : dataOld.estGestio; 
		var docBaja = evento == "N" ? "" : dataOld.docBaja; 
		var longitud = evento == "N" ? "" : dataOld.longitud; 
		var pais = evento == "N" ? "" : dataOld.pais; 
		var fechaBaja = evento == "N" ? "" : dataOld.fechaBaja; 
		var fechaVarre = evento == "N" ? "" : dataOld.fechaVarre; 
		var fechaDepreciacion = evento == "N" ? "" : dataOld.fechaDepreciacion; 
		var valorDeprecEjercicio = evento == "N" ? "" : dataOld.valorDeprecEjercicio; 
		var valorDeprecAcumulado = evento == "N" ? "" : dataOld.valorDeprecAcumulado; 
		var otrasCaract = evento == "N" ? "" : dataOld.otrasCaract; 
		var descripcionUbicacionBien = evento == "N" ? "" : dataOld.descripcionUbicacionBien; 
		var causalBaja = evento == "N" ? "" : dataOld.causalBaja; 
		var actoDisposicionBien = evento == "N" ? "" : dataOld.actoDisposicionBien; 
		var entidadBeneficiadaActoDisposicion = evento == "N" ? "" : dataOld.entidadBeneficiadaActoDisposicion; 
		var actoAdministracionBien = evento == "N" ? "" : dataOld.actoAdministracionBien; 
		var numeroResolucionAdministracion = evento == "N" ? "" : dataOld.numeroResolucionAdministracion; 
		var fechaAdministracion = evento == "N" ? "" : dataOld.fechaAdministracion; 
		var fechaVencActoAdmin = evento == "N" ? "" : dataOld.fechaVencActoAdmin; 
		var entidadBeneficiadaActoAdmin = evento == "N" ? "" : dataOld.entidadBeneficiadaActoAdmin; 
		var docAltaSbn = evento == "N" ? "" : dataOld.docAltaSbn; 
		var docBajaSbn = evento == "N" ? "" : dataOld.docBajaSbn; 
		var tipo = evento == "N" ? "" : dataOld.tipo; 
		var numeroResolucionDisp = evento == "N" ? "" : dataOld.numeroResolucionDisp; 
		var codigoBarras = evento == "N" ? "" : dataOld.codigoBarras; 
		
		var valorLibro = evento == "N" ? "" : dataOld.valorLibro;
        var inventariadorAnterior = evento == "N" ? "" : dataOld.inventariadorAnterior;

        var itemAdd = {
	            'inventarioBienID': inventarioBienID,                       // integer
	            'bienID': bienID,                                 // integer
	            'inventarioID': inventarioID,                           // integer
	            'estadoSubida' : estadoSubida,                          // string
	            'sobranteFaltante' : sobranteFaltante,                      // string
	            'inventariador' : inventariador,                         // string
	            'codigoPatrimonial': codigoPatrimonial.concat(ultCorrelativo),                      // string
	            'entidadID': entidadID,                              // integer
	            'localesID': local,                              // integer
	            'areaID': area,                                 // integer
	            'empleadoID': usuario,//usuario,                             // integer
	            'oficinaID': oficina,                              // integer
	            'tipoCausalAlta': tipoCausalAlta,                         // string
	            'anho': anyo,                                   // integer
	            'matricula': dtNumeroMatricula,                              // string
	            'ancho': ancho,                                  // double
	            'valorLibro': valorLibro,                             // double
	            'cuentaConSeguro' : cuentaConSeguro,                        // string
	            'estadoBien': estadoBien,                             // string
	            'dimension': finalDimension,                              // string
	            'condicion': formaAdquisicion,                              // string
	            'numeroDocAdquisicion': resolAlta,                   // string
	            'color': color,                                  // string
	            'fechaAdquisicion': fechaAdquisicion,                       // date
	            'entDisp': entDisp,                                // string
	            'entArre': entArre,                                // string
	            'fechaVafec': fechaVafec,                             // date
	            'edad': edad,                                   // string
	            'motivoEliminacionBien': motivoEliminacionBien,                  // string
	            'valorAdquisicion': valorAdquisicion,                       // double
	            'anioFabricacion': dtAnyoFabricacion,                        // string
	            'resolAfec': resolAfec,                              // string
	            'especie': especie,                                // string
	            'raza': raza,                                   // string
	            'fechaAfec': fechaAfec,                              // date
	            'flgActo': flgActo,                                // string
	            'modelo': finalModelo,                                 // string
	            'flgCausal': flgCausal,                              // string
	            'marca': finalMarca,                                  // string
	            'numeroResolucionBaja': numeroResolucionBaja,                   // string
	            'resolArre': resolArre,                              // string
	            'dscOtros': otros,                               // string
	            'fechaArre': fechaArre,                              // date
	            'fechaDisposicion': fechaDisposicion,                       // date
	            'resolDisp': resolDisp,                              // string
	            'altura': altura,                                 // string
	            'numeroChasis': numeroChasis,                           // string
	            'valortasa': valortasa,                              // string
	            'placa': placa,                                  // string
	            'sitBinv': situacionBienInventario,                                // string
	            'numeroCuentaContable': cuentaContable,                   // string
	            'serie': finalSerie,                                  // string
	            'estBien': estBien,                                // string
	            'entAfec': entAfec,                                // string
	            'codanterio': codigoInterno,                             // string
	            'tipoCuenta': radioTipoCuenta,                             // string
	            'estGestio': estGestio,                              // string
	            'docBaja': docBaja,                                // string
	            'numeroMotor': numeroMotor,                            // string
	            'longitud': longitud,                               // string
	            'pais': pais,                                   // string
	            'fechaBaja': fechaBaja,                              // date
	            'fechaVarre': fechaVarre,                             // date
	            'denominacionBien': denominacion,                       // string
	            'fechaDepreciacion': fechaDepreciacion,                      // date
	            'valorDeprecEjercicio': valorDeprecEjercicio,                   // double
	            'valorDeprecAcumulado': valorDeprecAcumulado,                   // double
	            'valorNeto': valorNeto,                              // double
	            'tipUsoCuenta': tipoCuenta,                           // string
	            'catalogoBienID': catalogoBienID,                         // integer
	            'otrasCaract': otrasCaract,                            // string
	            'descripcionUbicacionBien': descripcionUbicacionBien,               // string
	            'causalBaja': causalBaja,                             // string
	            'actoDisposicionBien': actoDisposicionBien,                    // string
	            'entidadBeneficiadaActoDisposicion': entidadBeneficiadaActoDisposicion,      // string
	            'actoAdministracionBien': actoAdministracionBien,                 // string
	            'numeroResolucionAdministracion': numeroResolucionAdministracion,         // string
	            'fechaAdministracion': fechaAdministracion,                    // date
	            'fechaVencActoAdmin': fechaVencActoAdmin,                     // date
	            'entidadBeneficiadaActoAdmin': entidadBeneficiadaActoAdmin,            // string
	            'docAltaSbn': docAltaSbn,                             // string
	            'docBajaSbn': docBajaSbn,                             // string
	            'tipo': tipo,                                   // string
	            'numeroResolucionDisp': numeroResolucionDisp,                   // string
	            'codigoBarras': codigoBarras,                           // string
	            'inventariadoPor': sessionData.usuario,                        // string
	            'revisadoToma': revisadoToma,                           // string
                'inventariadorAnterior': inventariadorAnterior,           // string
	            'localInfo': {
	                'pk': 'inventarioBienID',
	                'accion': accion                              // I: insertar, A: actualizar, E: eliminar
	            },                                            // objeto con info adicional
	            'localKey': localKey                                // pk de base de datos local
	        };

		
		if(evento == 'N'){gSimioDAO.inventarioBien.addItem(itemAdd,functionOK,functionError);}
		if(evento == 'E'){gSimioDAO.inventarioBien.putItem(itemAdd,functionOK,functionError);}
		copiarTablaRAM( 'inventarioBien' );
	
	}

}

function asignarLocalKey(){ 
	
	var localKeyMax = "0" ;
	
	var inventarioBien = gSimioRAM.tabla.inventarioBien;
	
	var cantidadRegistros = inventarioBien.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item1 = inventarioBien[i];
			var localKeyMax = item1.localKey;
			
			for(var k=0;k<cantidadRegistros;k++){
				
				var item2 = inventarioBien[k];
				var localKey2 = item2.localKey;
				
				if(localKeyMax < localKey2){
					
					localKeyMax = localKey2;
					
				}
				
			}
							
			break;
	    }
		
		return String(parseInt(localKeyMax) + 1); 
		
	}else{
		
		return String(parseInt(localKeyMax) + 1);
		
	}
	
}

function functionOK(){

	// darle un tiempito para que cargue el listado
	setTimeout(function() {
        clickBtnCargarListado();
	}, 500);

}

//handleErrorBD
function functionError(){
	alert("Error :(");
} 

//function listarItemsCatalogoBien(item){
//	
//	/*Copiando valores de tabla catalogo bien a RAM*/
//	var i = data_tb_catalogo_bien.length;
//	data_tb_catalogo_bien[i] = item;
//	
//    	$('#cbo_catalogo_bien').append($('<option>', { 
//        value: item.value.codigoBien,
//        text : item.value.denominacion 
//    	}));
//}

//Este uso para busquedas. Lo he probado desde la consolta del browser
/*
 * paramDenominacion = valor a buscar
 * si encuentra que la palabra esta contenida en la denominacion de un catalogo_bien entonces
 * imprime en que indice lo encontro
 * */
function getCatalogoBienByDenominacion(paramDenominacion){
	
	var cantidadRegistros = data_tb_catalogo_bien.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = data_tb_catalogo_bien[i];
			
			if(item.value.denominacion.indexOf(paramDenominacion.toUpperCase()) != -1){
				
				console.log("Se encuentra en el indice " + i);
				
			}
			
	    }
		
	}else{
		console.log("No se encontraron datos");
	}
	
}

//function changeCatalogoBien(){
//	 
//	$("#cbo_catalogo_bien").change(function () {
//		var codBien = $('#cbo_catalogo_bien').val();
//		
//		llenarDatosEnFormulario(codBien[0]);
//		habilitarControles();
//		asignarUltimoCorrelativo(codBien[0]);
//
//	});
//}

function habilitarControles(){
	
	dtBtnOtro = 0;
	$('#txt_codigo_interno').removeAttr( "readOnly" );
	$('#cbo_tipo_cuenta').removeAttr( "readOnly" );
	$('#cbo_cuenta_contable').removeAttr( "readOnly" );
	$('#cbo_forma_adquisicion').removeAttr( "readOnly" );
	$('#txt_fecha_adquisicion').removeAttr( "readOnly" );
	$('#txt_resol_alta').removeAttr( "readOnly" );
	$('#txt_valor_adquisicion').removeAttr( "readOnly" );
	$('#txt_valor_neto').removeAttr( "readOnly" );
	$('#cbo_estado_bien').removeAttr( "readOnly" );
	$('#cbo_usuario').removeAttr( "readOnly" );	
    habilitarControl('#btn_buscarEmpleado', true);
	
	var codBien = getSelectedIDGrillaListadoTipoBien();
	
	var catalogoBien = gSimioRAM.tabla.catalogoBien;
	
	var cantidadRegistros = catalogoBien.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = catalogoBien[i];
			
			if(item.codigoBien == codBien){
							
				if(item.dtMarca == 1){
					$('#txt_marca').removeAttr( "readOnly" );	
				}else{
					$('#txt_marca').attr('readonly', true);
				}
				
				if(item.dtModelo == 1){
					$('#txt_modelo').removeAttr( "readOnly" );	
				}else{
					$('#txt_modelo').attr('readonly', true);
				}
				
				if(item.dtTipo == 1){
					$('#txt_tipo').removeAttr( "readOnly" );	
				}else{
					$('#txt_tipo').attr('readonly', true);
				}
				
				if(item.dtColor == 1){
					$('#txt_color').removeAttr( "readOnly" );	
				}else{
					$('#txt_color').attr('readonly', true);
				}
				
				if(item.dtSerie == 1){
					$('#txt_serie').removeAttr( "readOnly" );	
				}else{
					$('#txt_serie').attr('readonly', true);
				}
				
				if(item.dtPlaca == 1){
					$('#txt_placa').removeAttr( "readOnly" );	
				}else{
					$('#txt_placa').attr('readonly', true);
				}
				
				if(item.dtMotor == 1){
					$('#txt_numero_motor').removeAttr( "readOnly" );	
				}else{
					$('#txt_numero_motor').attr('readonly', true);
				}
				
				if(item.dtChasis == 1){
					$('#txt_numero_chasis').removeAttr( "readOnly" );	
				}else{
					$('#txt_numero_chasis').attr('readonly', true);
				}
				
				if(item.dtBtnOtro == 1){
					dtBtnOtro = 1;//Determina que el boton Otros esta Habilitado
					$( "#btn_otros" ).prop( "disabled", false );	
				}else{
					dtBtnOtro = 0;//Determina que el boton Otros NO esta Habilitado
					$( "#btn_otros" ).prop( "disabled", true );
				}
				
				/*Inicio 05/12/2016*/
				
				if(item.dtDimension == 1){
					$('#txt_dimension').removeAttr( "readOnly" );	
				}else{
					$('#txt_dimension').attr('readonly', true);
				}
				
				if(item.dtAno == 1){
					$('#txt_anyo').removeAttr( "readOnly" );	
				}else{
					$('#txt_anyo').attr('readonly', true);
				}
				
				if(item.dtRaza == 1){
					$('#txt_raza').removeAttr( "readOnly" );	
				}else{
					$('#txt_raza').attr('readonly', true);
				}
				
				if(item.dtNombre == 1){
					$('#txt_nombre').removeAttr( "readOnly" );	
				}else{
					$('#txt_nombre').attr('readonly', true);
				}
				
				if(item.dtEdad == 1){
					$('#txt_edad').removeAttr( "readOnly" );	
				}else{
					$('#txt_edad').attr('readonly', true);
				}
				
				/*Fin 05/12/2016*/

			}
			
	    }
		
	}
	
}

function changeTipoCuenta(){
	
	$("#cbo_tipo_cuenta").change(function () {
		var tipoCuenta = $('#cbo_tipo_cuenta').val();
		
		datosTipoCuenta = [];
		datosTipoCuenta[0] = "";
		datosTipoCuenta[1] = "";
		datosTipoCuenta[2] = tipoCuenta;
		
		cargarComboCuentaContable("",datosTipoCuenta);
		
		//console.log(tipoCuenta);

	});
	
}

function changeUsuario(){
	
	$("#cbo_usuario").change(function () {
		var codEmpleadoNegocio = $('#cbo_usuario').val();
		
		//Iniciamos variables de RAM
		areaIDPorEmpleado = [];
		indexAreaIDPorEmpleado = 0;
		
		localesAreaPorEmpleado = [];
		indexLocalesAreaPorEmpleado = 0;
		
		cargarComboLocales(codEmpleadoNegocio);
		
		$('#cbo_local').removeAttr( "readOnly" );	
		
		//console.log(codEmpleadoNegocio);

	});
}

function changeLocales(){
	
	$("#cbo_local").change(function () {
		var codLocal = $('#cbo_local').val();
			
		cargarComboAreas(codLocal);
		
		$('#cbo_area').removeAttr( "readOnly" );	
		
		//console.log(codLocal);

	});
	
}

function changeAreas(){
	
	$("#cbo_area").change(function () {
		var codArea = $('#cbo_area').val();
			
		cargarComboOficina(codArea);
		
		$('#cbo_oficina').removeAttr( "readOnly" );
		
		//console.log(codArea);

	});
	
}


function getCuentaContableByGrupoClase(grupoID,claseID){
	
	var grupoClase = gSimioRAM.tabla.grupoClase;
	
	var cantidadRegistros = grupoClase.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = grupoClase[i];
			
			if((item.grupoID == grupoID.toUpperCase()) && (item.claseID == claseID.toUpperCase())){
							
				return item.cuenta;

			}
			
	    }
		
	}else{
		console.log("No se encontraron datos en Grupo Clase");
	}
	
}

function getUsoCuentaByCuenta(cuentaContable){
	
	var cuenta = gSimioRAM.tabla.cuenta;
	
	var cantidadRegistros = cuenta.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = cuenta[i];
			
			if(item.cuenta == cuentaContable.toUpperCase()){
				
				var datoCuenta = [];
				datoCuenta[0] = item.cuentaID;
				datoCuenta[1] = item.cuenta;
				datoCuenta[2] = item.usoCta;
					
				return datoCuenta ;

			}
			
	    }
		
	}else{
		console.log("No se encontraron datos en Cuenta");
	}
	
}

function llenarDatosEnFormulario(codBien){
	
	$('#txt_denominacion').val("");
	$('#txt_grupo_generico').val("");
	$('#txt_clase').val("");
	$('#txt_codigo_patrimonial').val(codBien);
	
	//Iniciamos variables de RAM
	areaIDPorEmpleado = [];
	indexAreaIDPorEmpleado = 0;
	
	var indiceDondeSeEncontro = getNombreCatalogoBienByCodBien(codBien);
	var catalogoBien = gSimioRAM.tabla.catalogoBien;
	var item = catalogoBien[indiceDondeSeEncontro];
	
	var grupoID = item.grupoID;
	getNombreGrupoByIDGrupo(grupoID);
	
	var claseID = item.claseID;
	getNombreClaseByIDClase(claseID);
	
	var cuentaContable = getCuentaContableByGrupoClase(grupoID,claseID);
	var usoCuenta = getUsoCuentaByCuenta(cuentaContable);
	
	cargarComboCuentaContable(cuentaContable,usoCuenta);
	cargarComboUsuario();
	cargarComboFormaAdquisicion();
	
}

function changeCuentaContable(){
	$("#cbo_cuenta_contable").change(function () {
		
		var cuentaContable = $('#cbo_cuenta_contable').val();
		
		var cuenta = gSimioRAM.tabla.cuenta;
		var cantidadRegistros = cuenta.length;
		var descripcionCuenta = "";
		
		if(cantidadRegistros > 0){
			
			for(var i=0;i<cantidadRegistros;i++){
		        
				var item = cuenta[i];
				
				if(item.cuenta == cuentaContable.toUpperCase()){
					
					descripcionCuenta = item.denomina;
				}	
		    }
			
			$('#txt_des_cuenta_contable').val(descripcionCuenta);
			
		}else{
			console.log("No se encontraron datos en Cuenta Contable");
		}
		

	});
}

function cargarComboCuentaContable(cuentaContable,usoCuenta){
	
	/*
	 * usoCuenta[0] = id de la cuenta
	 * usoCuenta[1] = el numero de cuenta
	 * usoCuenta[2] = el codigo de uso de cuenta (E o P)
	 * */
	 $("#cbo_cuenta_contable").empty();
	 
	var cuenta = gSimioRAM.tabla.cuenta;
	var cantidadRegistros = cuenta.length;
	var descripcionCuenta = "";
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = cuenta[i];
			
			if(item.tipoCta == "A" && item.flagCta == "V" && item.usoCta == usoCuenta[2].toUpperCase()){
								
				$('#cbo_cuenta_contable').append($('<option>', { 
			        value: item.cuenta,
			        text : item.cuenta 
			    	}));
			}
			
			if(item.cuenta == usoCuenta[1].toUpperCase()){
				
				descripcionCuenta = item.denomina;
				
			}
			
	    }
		
		$('#cbo_tipo_cuenta').val(usoCuenta[2]);
		$('#cbo_cuenta_contable').val(usoCuenta[1]);
		$('#txt_des_cuenta_contable').val(descripcionCuenta);
		
	}else{
		console.log("No se encontraron datos en Cuenta");
	}
}

function getNombreCatalogoBienByCodBien(codBien){
	
	var catalogoBien = gSimioRAM.tabla.catalogoBien;
	
	var cantidadRegistros = catalogoBien.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = catalogoBien[i];
			
			if(item.codigoBien == codBien.toUpperCase()){
				
				$('#txt_denominacion').val(item.denominacion);
				
				return i;

			}
			
	    }
		
	}else{
		console.log("No se encontraron datos en Catalogo Bien");
	}
	
}

function getCatalogoBienById(id){
	
	var catalogoBien = gSimioRAM.tabla.catalogoBien;
	
	var cantidadRegistros = catalogoBien.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = catalogoBien[i];
			
			if(item.catalogoBienID == id.toUpperCase()){
				
				return catalogoBien[i];

			}
			
	    }
		
	}else{
		console.log("No se encontraron datos en Catalogo Bien");
	}
	
}

function getNombreGrupoByIDGrupo(grupoID){
	
	var grupo = gSimioRAM.tabla.grupo;
	
	var cantidadRegistros = grupo.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = grupo[i];
			
			if(item.grupoID == grupoID.toUpperCase()){
				
				$('#txt_grupo_generico').val(item.descripcion);
				
				break;

			}
			
	    }
		
	}else{
		console.log("No se encontraron datos en Grupo");
	}
	
}

function getNombreClaseByIDClase(claseID){
	
	var clase = gSimioRAM.tabla.clase;
	
	var cantidadRegistros = clase.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = clase[i];
			
			if(item.claseID == claseID.toUpperCase()){
				
				$('#txt_clase').val(item.descripcion);
				
				break;

			}
			
	    }
		
	}else{
		console.log("No se encontraron datos en Clase");
	}
	
}

function validarDatosIngresados(){
	
	//Aqui va la logica de validaciones antes de Grabar

	// validar que ingrese usuario valida
	if ( !estaDefinido($('#cbo_usuario').val()) || $('#cbo_usuario').val() == '#' ) {
		showMensaje('', 'Por favor escoja un Usuario', function() {
            $('#cbo_usuario').focus();
		});
		return false;
	}

    // validar que ingrese una forma de adquisicion valida
    if ( !estaDefinido($('#cbo_forma_adquisicion').val()) ||  $('#cbo_forma_adquisicion').val() == '#' ) {
        showMensaje('', 'Por favor escoja una Forma de Adquisición', function() {
            $('#cbo_forma_adquisicion').focus();
		});
        return false;
    }

	return true;
}

function cargarComboUsuario(){
	
	 $("#cbo_usuario").empty(); 
	
	 $('#cbo_usuario').append($('<option>', { 
	        value: "#",
	        text : "SELECCIONE UN USUARIO" 
	    	}));
	 
	var empleado = gSimioRAM.tabla.empleado;
	var cantidadRegistros = empleado.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = empleado[i];
								
				$('#cbo_usuario').append($('<option>', { 
			        value: item.empleadoID,
			        text : item.apellidos + " " + item.nombres 
			    	}));
			
	    }
		
	}else{
		
		$("#cbo_usuario").empty();
		
		console.log("No se encontraron datos en Empleado");
	}
	
}

function cargarComboFormaAdquisicion(){
	
	 $("#cbo_forma_adquisicion").empty(); 
	
	 $('#cbo_forma_adquisicion').append($('<option>', { 
	        value: "#",
	        text : "SELECCIONE UNA FORMA DE ADQUISICION" 
	    	}));
	 
	var formaAdquisicion = gSimioRAM.tabla.catalogo;
	var cantidadRegistros = formaAdquisicion.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = formaAdquisicion[i];
								
				if(item.catalogo == "FORMA_ADQUISICION" && item.tipo != "CAB"){
					
					$('#cbo_forma_adquisicion').append($('<option>', { 
//				        value: item.catalogoID + "|" + item.datacatalogo,
						value: item.datacatalogo,
				        text : item.descripcion 
				    	}));
				}
	    }
		
	}else{
		
		$("#cbo_forma_adquisicion").empty();
		
		console.log("No se encontraron datos en Forma Adquisicion");
	}
	
}

function cargarComboLocales(codEmpleadoNegocio){
	
	 $("#cbo_local").empty(); 
	 $("#cbo_area").empty(); 
	 $("#cbo_oficina").empty();
	 
	var empleado = gSimioRAM.tabla.empleado;
	var cantidadRegistros = empleado.length;
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = empleado[i];
			
			//Primero debemos ver que area tiene asignado el usuario y luego desde el area ubicar la oficina (Nos aseguramos)
			//Un empleado puede tener mas de un area asignada
			if(item.empleadoID == codEmpleadoNegocio.toUpperCase()){
				
				$('#cbo_local').append($('<option>', { 
			        value: "#",
			        text : "SELECCIONE UN LOCAL" 
			    	}));
				
				var empleadoUbicacion = gSimioRAM.tabla.empleadoUbicacion;
				var cantidadRegistrosEmpleadoUbicacion = empleadoUbicacion.length;
				
				if(cantidadRegistrosEmpleadoUbicacion > 0){
					
					for(var k=0;k<cantidadRegistrosEmpleadoUbicacion;k++){
						
						var itemEmpleadoUbicacion = empleadoUbicacion[k];
						
						if(itemEmpleadoUbicacion.empleadoID == item.empleadoID){	
							
							//Agregamos el local encontrada a la RAM, pero primero evaluamos si esta o no en RAM
							var posicionLocal = localSeEncuentraEnRAM(itemEmpleadoUbicacion.localesID);
							var areasArray = [];
							
							if(posicionLocal != null){
								areasArray = localesAreaPorEmpleado[posicionLocal].areas;
								areasArray[areasArray.length] = itemEmpleadoUbicacion.areaID;
								localesAreaPorEmpleado[posicionLocal].areas = areasArray; 
							}else{
								var localObjeto = new Object();
								areasArray = [];
								areasArray[0] = itemEmpleadoUbicacion.areaID;
								localObjeto.localID = itemEmpleadoUbicacion.localesID;
								localObjeto.areas = areasArray;

								localesAreaPorEmpleado[indexLocalesAreaPorEmpleado] = localObjeto;	
								indexLocalesAreaPorEmpleado++;
								
								/*Cuando en localesAreaPorEmpleado no se encuentre local, entonces se agrega option a combo*/
								$('#cbo_local').append($('<option>', { 
						        value: itemEmpleadoUbicacion.localesID,
						        text : getDescripcionLocal(itemEmpleadoUbicacion.localesID) 
						    	}));
							}
							
						}
					}
				}else{
					
					console.log("no hay registros en empleado ubicacion");
					
				}
				
					
			}
				
			
	    }
		
	}else{
		console.log("No se encontraron datos en Empleado");
	}
	
}

function getDescripcionLocal(localID){

	var locales = gSimioRAM.tabla.locales;
	var cantidadRegistrosLocales = locales.length;
	
	//Recorremos la tabla locales para encontrar la descripcion
	if(cantidadRegistrosLocales > 0){
		
		for(var z=0;z<cantidadRegistrosLocales;z++){
			
			var itemLocal = locales[z];
			
			if(itemLocal.localesID == localID.toUpperCase()){
				
				return itemLocal.nombreLocal;
			}
		}
	}else{
		
		return "";
	}	
}

function cargarComboAreas(codLocal){
	
	 $("#cbo_area").empty(); 
	 $("#cbo_oficina").empty(); 
	
	var area = gSimioRAM.tabla.area;
	var cantidadRegistros = area.length;
	
	var posicionLocal = localSeEncuentraEnRAM(codLocal);
	var areasLocal = localesAreaPorEmpleado[posicionLocal].areas;
	
	$('#cbo_area').append($('<option>', { 
        value: "#",
        text : "SELECCIONE UN AREA" 
    	}));
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = area[i];
			
			if(item.localesID == codLocal.toUpperCase() && valorEnArray(areasLocal,item.areaID)){
				
				$('#cbo_area').append($('<option>', { 
			        value: item.areaID,
			        text : item.nombreArea 
			    	}));
				
			}
			
	    }
		
	}else{
		
		$("#cbo_area").empty();
		
		console.log("No se encontraron datos en cargarComboAreas");
	}
		
}

function cargarComboOficina(codArea){

	 $("#cbo_oficina").empty(); 
	
	var oficina = gSimioRAM.tabla.oficina;
	var cantidadRegistros = oficina.length;
	
	$('#cbo_oficina').append($('<option>', { 
       value: "#",
       text : "SELECCIONE UNA OFICINA" 
   	}));
	
	if(cantidadRegistros > 0){
		
		for(var i=0;i<cantidadRegistros;i++){
	        
			var item = oficina[i];
			
			if(item.areaID == codArea.toUpperCase()){
				
				$('#cbo_oficina').append($('<option>', { 
			        value: item.oficinaID,
			        text : item.nombreOficina 
			    	}));
				
			}
			
	    }
		
	}else{
		
		$("#cbo_oficina").empty(); 
		
		console.log("No se encontraron datos en cargarComboOficina");
	}
		
}

function areaSeEncuentraEnRAM(areaID){
	
	//Agregamos el area encontrada a la RAM
	areasEnRAM = areaIDPorEmpleado.length;
	
	if(areasEnRAM > 0){
		
		return valorEnArray(areaIDPorEmpleado,areaID);
		
	}else{
		
		return false;
		
	}
	
}

function localSeEncuentraEnRAM(localID){
	
	localesEnRAM = localesAreaPorEmpleado.length;
	
	if(localesEnRAM > 0){
		
		for(var i=0;i<localesEnRAM;i++){
			
			if(localesAreaPorEmpleado[i].localID == localID.toUpperCase()){
				
				return i;
			}
		}
		
		return null;
		
	}else{
		
		return null;
	}
	
}

function getLocalesByArea(areaID){
		 
		var area = gSimioRAM.tabla.area;
		var cantidadRegistros = area.length;
		//var locales = [];
		var local = "";
		
		if(cantidadRegistros > 0){
			
			for(var i=0;i<cantidadRegistros;i++){
		        
				var item = area[i];
				
				if(item.areaID == areaID.toUpperCase()){
					
					return local = item.localesID;
					
					//if(!valorEnArray(locales,item.localesID)){
						
						//var cantidadLocales = locales.length;
						//locales[cantidadLocales] = item.localesID;
						
					//}
					
				}
				
		    }
			
		}else{
			console.log("No se encontraron datos en getLocalesByArea");
		}
	
}

function valorEnArray(array,valor){
	
	cantidadValores = array.length;
	
	for(var i=0;i<cantidadValores;i++){
		
		if(array[i] == valor.toUpperCase()){
			
			return true;
			
		}
		
	}
	
	return false;
}

function initMask() {
	
	var div = $( '#div-panel-registro' );
	
	control = div.find( 'input[name="txt_valor_neto"]' );
	control.inputmask({
        'alias': 'decimal',
        rightAlign: false,
        'groupSeparator': '.',
        'autoGroup': false
      });
	control.prop( 'maxlength', 10 );
    
    control = div.find( 'input[name="txt_valor_adquisicion"]' );   
    control.inputmask({
        'alias': 'decimal',
        rightAlign: false,
        'groupSeparator': '.',
        'autoGroup': false
      });
    control.prop( 'maxlength', 10 );
    
    control = div.find( 'input[name="txt_edad"]' );
    control.inputmask( "9{0,10}", {placeholder: ''});
    control.prop( 'maxlength', 3 );
    

}

function asignarUltimoCorrelativo(codBien){
		
	var maxInventarioApertura = 0;
	var maxInventarioBien = 0;
	
	var inventarioApertura = gSimioRAM.tabla.inventarioApertura;
	var cantidadRegistros = inventarioApertura.length;
	
	if(cantidadRegistros > 0){
		for(var i=0;i<cantidadRegistros;i++){
			var item = inventarioApertura[i];
			if(item.codigoPatrimonial.indexOf(codBien) != -1){
				var correlativo = parseInt(item.codigoPatrimonial.substring((item.codigoPatrimonial.length	-	4), item.codigoPatrimonial.length)); //para que coja los ultimos 4 digitos
				if(maxInventarioApertura < correlativo){
					maxInventarioApertura = correlativo;
				}
			}
	    }
	}else{
		console.log("No se encontraron datos en Inventario Apertura metodo asignarUltimoCorrelativo");
	}
	
	var inventarioBien = gSimioRAM.tabla.inventarioBien;
	cantidadRegistros = inventarioBien.length;
	
	if(cantidadRegistros > 0){
		for(var i=0;i<cantidadRegistros;i++){
			var item = inventarioBien[i];
			if(item.codigoPatrimonial.indexOf(codBien) != -1){
				var correlativo = parseInt(item.codigoPatrimonial.substring((item.codigoPatrimonial.length	-	4), item.codigoPatrimonial.length)); //para que coja los ultimos 4 digitos
				if(maxInventarioBien < correlativo){
					maxInventarioBien = correlativo;
				}
			}
	    }
	}else{
		console.log("No se encontraron datos en Inventario Bien metodo asignarUltimoCorrelativo");
	}
	
	if(maxInventarioApertura > maxInventarioBien){
		maxInventarioApertura = maxInventarioApertura	+	1;
		maxInventarioApertura = maxInventarioApertura + "";
		while(maxInventarioApertura.length <	4){
			maxInventarioApertura = '0' + maxInventarioApertura;
		}
		$('#txt_ult_correlativo').val(maxInventarioApertura);
	}else{
		maxInventarioBien = maxInventarioBien	+	1;
		maxInventarioBien = maxInventarioBien	+	"";
		while(maxInventarioBien.length <	4){
			maxInventarioBien = '0' + maxInventarioBien;
		}
		$('#txt_ult_correlativo').val(maxInventarioBien);
	}
	
}


/*jlunah */
function updateGrillaTipoBienesListado() {

    // NOTA IMPORTANTE: este metodo toma los valores de RAM
    var listaTipoBien = gSimioRAM.tabla.catalogoBien;

//    sortListaBienesParaGrillaPrincipal(listaTipoBien);

    var grilla = initDataDG('#table-grilla-listado-tipo-bien', listaTipoBien, true, true, true, [
        {"data": "codigoBien", "class": "text-center"},
        {"data": "codigoBien", "class": "text-center"},
        {"data": "denominacion", "class": "text-left"}
//        ],null, function(settings, json) {
    ], [{
        "targets": 0, // codigoBien
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {
                return '<input type="radio" name="codigoTipoBien" class="radioButtonTipoBien" title="Seleccionar" value="' + $.trim(row.codigoBien) + '">';
            }

            return '';
        }

    }], function(settings, json) {

        consoleLog('grilla tipo bien se ha cargado exitosamente');
    });
}

function eventSelectOnTRGrillaTipoBien(tableID, offEvent) {

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
        
        if(evento == 'N'){
        	llenarDatosEnFormulario(getSelectedIDGrillaListadoTipoBien());
    		habilitarControles();
    		asignarUltimoCorrelativo(getSelectedIDGrillaListadoTipoBien());
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
		
		if(evento == 'N'){
			llenarDatosEnFormulario(getSelectedIDGrillaListadoTipoBien());
			habilitarControles();
			asignarUltimoCorrelativo(getSelectedIDGrillaListadoTipoBien());
		}
 
    });
}

function getSelectedIDGrillaListadoTipoBien() {
	
    var idRegistro = $('#table-grilla-listado-tipo-bien').find('input[type=radio]:checked').val();

    return idRegistro;
}

function seleccionandoTipoBien(codigoBien) {
	
//    setTimeout(function () {
    	var radioButtons = $('#table-grilla-listado-tipo-bien .radioButtonTipoBien');
        if (radioButtons) {
        	$.each(radioButtons, function (i, item) {
                if ( item.value == codigoBien ) {
                	$(item).trigger('click');
                	return false;
                }
            });
        }
//    }, 250);
}


function clickBtnBuscarEmpleado(e) {

    e.preventDefault();

    updateGrillaEmpleados();

    // asignar el evento a las filas de la grilla para que cambie de color
    eventSelectOnTR('#table-grilla-empleados', true);

    $('#divModalBuscarEmpleado').modal('show');
}

function clickBtnAceptarModalBuscarEmpleado(e) {

    e.preventDefault();

    var selectedID = getSelectedIDGrillaEmpleado();

    if (!estaDefinido(selectedID)) {
        showMensaje('Mensaje', 'Por favor seleccione un empleado de la lista');
        return null;
    }

    $('#cbo_usuario').val(selectedID);
    $('#cbo_usuario').trigger('change');

    $('#divModalBuscarEmpleado').modal('hide');
}

function clickBtnCerrarModalBuscarEmpleado(e) {

    e.preventDefault();

    $('#divModalBuscarEmpleado').modal('hide');
}

function sortListaParaBuscarEmpleados(listaEmpleados) {

    if ( !estaDefinido(listaEmpleados) ) return;

    listaEmpleados.sort(function (a,b) {

        var compareA = v.padLeft($.trim(a.apellidoPaterno), 25) +
            v.padLeft($.trim(a.apellidoMaterno), 25) +
            v.padLeft($.trim(a.nombres), 50) +
            v.padLeft($.trim(a.empleadoID), 15);

        var compareB = v.padLeft($.trim(b.apellidoPaterno), 25) +
            v.padLeft($.trim(b.apellidoMaterno), 25) +
            v.padLeft($.trim(b.nombres), 50) +
            v.padLeft($.trim(b.empleadoID), 15);

        if (compareA > compareB) {
            return 1;
        }

        if (compareA < compareB) {
            return -1;
        }

        return 0;
    });
}


function updateGrillaEmpleados() {

    // NOTA IMPORTANTE: este metodo toma los valores de RAM

    var listaEmpleados = gSimioRAM.tabla.empleado;

    sortListaParaBuscarEmpleados(listaEmpleados);

    initDataDG('#table-grilla-empleados', listaEmpleados, true, true, true, [
        {"data": "empleadoID", "class": "text-center"},
        {"data": "apellidos", "class": "text-left"},
        {"data": "nombres", "class": "text-left"},
        {"data": "numeroDocIdentPersonal", "class": "text-left"}
    ], [{
        "targets": 0, // inventarioBienID
        "render": function (data, type, row) {

            if (row != null && typeof row != 'undefined') {
                return '<input type="radio" name="codigo" class="radioButtonEmpleado" title="Seleccionar" value="' + $.trim(row.empleadoID) + '">';
            }

            return '';
        }

    }], function(settings, json) {

        // seleccionandoSiEsUnicoListadoEmpleado();

    }, [
        [5, 10, -1],
        [5, 10, "All"]
    ]);
}

function seleccionandoSiEsUnicoListadoEmpleado() {

    setTimeout(function () {
        var radioButtons = $('#table-grilla-empleados .radioButtonEmpleado');
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

function getSelectedIDGrillaEmpleado() {

    var idRegistro = $('#table-grilla-empleados').find('input[type=radio]:checked').val();

    return idRegistro;
}

function seleccionandoSiEsUnicoListadoTipoBien() {

    setTimeout(function () {
        var radioButtons = $('#table-grilla-listado-tipo-bien .radioButtonTipoBien');
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
