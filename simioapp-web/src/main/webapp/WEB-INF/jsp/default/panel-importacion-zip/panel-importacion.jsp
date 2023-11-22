<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong> IMPORTAR DATA: BACKUP ZIP </strong> </div>
    <div class="panel-body">
        
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>
		
		<div>&nbsp;</div>
		
		<div class="row" id="div-panel-registro">
		    <div class="col-md-12">
		
		        <form id="form-registro" action="importacion-zip.htm?action=importarArchivoZIP" method="post" enctype="multipart/form-data" role="form">

					<input id="txt_entidad" type="hidden" name="txt_entidad">

		            <div class="row">
		                <div class="col-md-12">
		                    <div class="form-group">
		                        <label class="control-label">Entidad</label>
								<input id="entidad_input" type="text" title="Entidad" class="form-control input-sm" list="entidad_list">
								<datalist id="entidad_list">
									<c:forEach items="${cboEntidad.items}" var="item" >
										<option value="${item.label}" data-entidad="${item.id}">${item.id}</option>
									</c:forEach>
								</datalist>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		            
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Archivo ZIP</label>
		                        <input id="file_archivo_zip" name="file_archivo_zip" type="file" > <br />
		                        <span class="help-block"></span>
		                    </div>
		                </div>
						<div class="col-md-6">
							<div class="form-group">
                                <label class="control-label">Forzar Uso de la Entidad ${entidad_id}</label>
                                <select class="form-control input-sm" title="Ignora la entidad del ZIP, y usa entidad del usuario en sesi&oacute;n (${entidad_id})" name="cboForzarUsoEntidadSession">
                                    <c:forEach items="${cboForzarUsoEntidadSession.items}" var="item" >
                                        <option <c:if test="${item.id == cboForzarUsoEntidadSession.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
							</div>
						</div>
		            </div>

					<div class="row">
						<div class="col-md-3 col-md-offset-4">
							<input id="btn_Migrar" type="submit" class="btn btn-md btn-primary btn-block" value="IMPORTAR" />
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

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

        initMask();
        initFechas();
        
    }

	function initFechas() {
	
	}

	function initMask() {
    	
    }

    function pageEvents() {

        $('#entidad_input').on('change', function(e) {

            e.preventDefault();

			// recuperar el value de la caja de texto (que es el value del datalist)
            var seleccionado = $('#entidad_input').val();

            // buscamos en el datalist el option que coincida con ese value (no es muy limpio, pero sirve),
			// seria mejor tener un metodo que retorne el index del item seleccionado dentro del datalist
            var optionSelected = $('#entidad_list option[value="' + seleccionado + '"]');

            // recogemos el atributo data-entidad del <option para pasarlo a la peticion
            $('#txt_entidad').val( optionSelected.data( 'entidad' ) );

            consoleLog( $(this).data( 'entidad' )  );

		});

    }

    function pagePostInit() {

    }

</script>
