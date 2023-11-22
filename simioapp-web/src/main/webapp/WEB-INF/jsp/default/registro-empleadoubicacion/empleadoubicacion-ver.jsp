<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> VER UBICACI&Oacute;N DE EMPLEADO </strong> </div>
    <div class="panel-body">
        
        <div>&nbsp;</div>
		
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>
		
		<div class="row" id="div-panel-registro">
		    <div class="col-md-12">
		
		        <form id="form-registro" method="POST" role="form">
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">C&oacute;digo</label>
								<input readonly name="int_empleadoUbicacionID" type="text" title="C&oacute;digo" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.empleadoUbicacionID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EmpleadoID</label>
								<select disabled class="form-control input-sm" title="C&oacute;digo" id="cbo_empleado" name="cbo_empleado">
									<c:forEach items="${cboEmpleado.items}" var="item" >
										<option <c:if test="${item.id == cboEmpleado.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Entidad</label>
								<select disabled class="form-control input-sm" title="Entidad" id="cboEntidad" name="cboEntidad">
									<c:forEach items="${cboEntidad.items}" var="item" >
										<option <c:if test="${item.id == cboEntidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Local</label>
								<select disabled class="form-control input-sm" title="Local" id="cbo_local" name="cbo_local">
									<c:forEach items="${cboLocal.items}" var="item" >
										<option <c:if test="${item.id == cboLocal.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
								<label class="control-label">&Aacute;rea</label>
								<select disabled class="form-control input-sm" title="&Aacute;rea" id="cbo_area" name="cbo_area">
									<c:forEach items="${cboArea.items}" var="item" >
										<option <c:if test="${item.id == cboArea.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
								<label class="control-label">Oficina</label>
								<select disabled class="form-control input-sm" title="Oficina" id="cbo_oficina" name="cbo_oficina">
									<c:forEach items="${cboOficina.items}" var="item" >
										<option <c:if test="${item.id == cboOficina.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Tipo Asociaci&oacute;n</label>
								<select class="form-control input-sm" title="Tipo Asociaci&oacute;n" name="txt_tipoAsociacion">
									<c:forEach items="${cboTipoAsociacion.items}" var="item" >
										<option <c:if test="${item.id == cboTipoAsociacion.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
									</c:forEach>
								</select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		            
		
		            <div class="row">
		                <div class="col-md-4">
		                    <div class="form-group">
		                        <label class="control-label">Usuario registro</label>
		                        <input readonly name="txt_usuReg" type="text" title="Usuario Registro" maxlength="50" class="form-control input-sm" value="${registro.usuReg}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-4">
		                    <div class="form-group">
		                        <label class="control-label div-date-picker">Fecha registro</label>
		                        <input readonly name="fec_fechaReg" type="text" title="Fecha de Registro" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaReg}' />">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		
		            <div class="row">
		                <div class="col-md-4">
		                    <div class="form-group">
		                        <label class="control-label">Usuario de la &uacute;ltima modificaci&oacute;n</label>
		                        <input readonly name="txt_usuAct" type="text" title="Usuario de la &uacute;ltima modificaci&oacute;n" maxlength="50" class="form-control input-sm" value="${registro.usuAct}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		                <div class="col-md-4">
		                    <div class="form-group div-date-picker">
		                        <label class="control-label">&Uacute;ltima modificaci&oacute;n</label>
		                        <input readonly name="fec_fechaAct" type="text" title="&Uacute;ltima modificaci&oacute;n" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaAct}' />">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Estado Registro</label>
		                         <select disabled class="form-control input-sm" title="Estado Registro" name="cbo_indDel">
		                            <c:forEach items="${cboEstados.items}" var="item" >
		                                <option <c:if test="${item.id == cboEstados.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
		                            </c:forEach>
		                        </select>
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		
		        </form>
		
		    </div>
		</div>
		
		<div>&nbsp;</div>
		
		<div class="row" id="div-panel-botones">
		
		    <div class="col-md-2 col-md-offset-5">
		        <button id="btn_cancelar" title="Regresar" class="btn btn-md btn-primary btn-block">&nbsp; REGRESAR</button>
		    </div>
		
		</div>
		
        <div>&nbsp;</div>
				
	</div>
</div>

<div>&nbsp;</div>

<script>

    $(document).ready(function() {
        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

    }

    function pagePostInit() {


    }

    function pageEvents() {

        eventCancelar();
    }

    function eventCancelar() {

        $('#btn_cancelar').on('click', function(e) {

            $(location).prop('href', 'registro-ubicacion-empleado.htm?action=mostrarBuscar');
        });
    }


</script>