<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="modal-content">
   <div class="modal-header">
      <strong> DEPENDENCIAS </strong> <br />
   </div>
   <div class="modal-body">
      <!-- instancia de la barra de error superior -->
      <div id="divMsgGeneralErrorInstancePopup"></div>
      <div class="row" id="div-panel-buscarPopup">
         <div class="col-md-12">
            <form id="form-buscarPopup" method="POST" role="form">
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label class="control-label">Descripci&oacute;n</label>
                        <input name="txt_descripcion" type="text" title="Descripci&oacute;n" maxlength="50" class="form-control input-sm text-uppercase">
                        <input name="hdn_popup" type="hidden" value="on" >
                        <input name="id1" id="id1" type="hidden" value="${id1}">
                        <input name="id2" id="id2" type="hidden" value="${id2}">
                        <input name="id3"  id="id3"  type="hidden" value="${id3}" >
                        <span class="help-block"></span>
                     </div>
                  </div>
               </div>
               <div>&nbsp;</div>
			   <div class="row">
						<div class="col-md-2 col-md-offset-5">
							<button id="btn_buscarPopup" title="Buscar" class="btn btn-sm btn-primary btn-block"><span class="fa fa-search"></span>&nbsp; BUSCAR</button>
						</div>
			  </div>
            </form>
            <div>&nbsp;</div>
            <div>&nbsp;</div>
            <div class="row" id="div-panel-grilla">
               <div class="col-md-12">
                  <div class="panel panel-default">
                     <div class="panel-body">
                        <div class="table-responsive" id="div-load-grilla-popup">
                           <c:import url="/admin.htm">
                              <c:param name="action" value="cargarPage" />
                              <c:param name="_page" value="dependencia-buscar-grilla" />
                              <c:param name="_module" value="registro-dependencia" />
                              <c:param name="_template" value="default" />
                           </c:import>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <div class="modal-footer">
      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
   </div>
</div>

<script>
	
	// codigo html inicial de la grilla
	var htmlInicialPopupGrilla = '';

	function pageInitDependenciaPopup() {
		//inicializaTabla();
		// en la priemra cargada, guarda el html simple sin data de la tabla
		if ( htmlInicialPopupGrilla == '' ) {
			htmlInicialPopupGrilla = $('#div-load-grilla-popup').html(); 
		}
		
		// y en cada cargada del popup, setear el estado inicial de la grilla
		$('#div-load-grilla-popup').html(htmlInicialPopupGrilla);
		eventBuscarDependenciaPopup();
		
		
	}

	function initGrillaDependenciaPopup() {

		initDG('#dependencia-buscar-grilla-popup', true, true, true, null, null);
		$('#dependencia-buscar-grilla-popup').off();
		eventSelectOnTR('#dependencia-buscar-grilla-popup');
	}

	function eventBuscarDependenciaPopup() {

		$('#btn_buscarPopup').off();
		$('#btn_buscarPopup').on(
				'click',
				function(e) {

					e.preventDefault();

					var restore = estadoInputInicial('#div-panel-buscarPopup');

					$.ajax({
						url : 'registro-dependencia.htm?action=validarBuscar',
						cache : false,
						data : $('#form-buscarPopup').serialize(),
						async : true,
						type : 'POST',
						dataType : 'json',
						success : function(data) {

							if (huboErrorJson(data)) {

								if (huboErrorValidacionJson(data)) {
									estadoInputError('#div-panel-buscarPopup',
											data, restore);
									return;
								}

								handleErrorJson(data);
								return;
							}

							console.log('data: ' + JSON.stringify(data));

							cargarGrillaPopup();

						},
						error : function(data, textStatus, errorThrown) {
							handleError(data);
						}
					});

				});
	}

	function cargarGrillaPopup() {

		var paramsBuscar = $('#form-buscarPopup').serialize();

		$('#div-load-grilla-popup').load(
				'registro-dependencia.htm?action=buscarDependencia',
				paramsBuscar,
				function(response, status, xhr) {

					if (status == "error") { // xhr.statusText
						showMensaje('Mensaje',
								'Ocurri\u00F3 un error inesperado: ERROR '
										+ xhr.status);
						return;
					}

					initGrillaDependenciaPopup();

				});
	}
	function seleccinarId(id,cod_dependencia,descripcion){
		
		$("#"+$('#id1').val()).val(id);
		$("#"+$('#id2').val()).val(cod_dependencia);
		$("#"+$('#id3').val()).val(descripcion);
		$('#myModal').modal('hide');
		
	}
</script>