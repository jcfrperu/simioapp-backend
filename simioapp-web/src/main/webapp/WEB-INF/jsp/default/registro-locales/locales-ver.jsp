<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> VER LOCAL </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">
                    
					<fieldset>
                        <legend>DATOS GENERALES</legend>
						
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo</label>
                                <input readonly name="int_localesID" type="text" title="LocalesID" maxlength="50" class="form-control input-sm" value="${registro.localesID}">
                                <span class="help-block"></span>
                            </div>
                        </div>
						
						<div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Entidad</label>
                                <input readonly name="txt_entidadID" type="text" title="Entidad" maxlength="50" class="form-control input-sm" value="${registro.entidadID}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Local</label>
                                <input readonly name="txt_codigo" type="text" title="Codigo" maxlength="50" class="form-control input-sm" value="${registro.codigo}">
                                <span class="help-block"></span>
                            </div>
                        </div>
						
						<div class="col-md-8">
                            <div class="form-group">
                                <label class="control-label">Nombre Local</label>
                                <input readonly name="txt_nombreLocal" type="text" title="Nombre Local" maxlength="50" class="form-control input-sm" value="${registro.nombreLocal}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                    <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Propiedad</label>
                                    <select disabled class="form-control input-sm" title="Propiedad" name="txt_flgPropie">
	                                    <c:forEach items="${cboTipoPropiedad.items}" var="item" >
	                                        <option <c:if test="${item.id == cboTipoPropiedad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">&Aacute;rea</label>
                                    <input readonly name="dbl_ctdArea" type="text" title="&Aacute;rea" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.ctdArea}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Unidad de Medida</label>
                                    <select disabled class="form-control input-sm" title="Unidad de Medida" name="txt_flgUm">
	                                    <c:forEach items="${cboUnidadMedida.items}" var="item" >
	                                        <option <c:if test="${item.id == cboUnidadMedida.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                         	<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Tipo Direcci&oacute;n</label>
                                    <select disabled class="form-control input-sm" title="Tipo de Direcci&oacute;n" name="txt_flgTipovi">
	                                    <c:forEach items="${cboTipoDireccion.items}" var="item" >
	                                        <option <c:if test="${item.id == cboTipoDireccion.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <div class="form-group">
                                    <label class="control-label">Direcci&oacute;n</label>
                                    <input readonly name="txt_direccion" type="text" title="Direcci&oacute;n" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.direccion}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
							<div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">N&deg;</label>
                                    <input readonly name="txt_numeroMun" type="text" title="N&deg;" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.numeroMun}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Mz</label>
                                    <input readonly name="txt_dscMz" type="text" title="Mz" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscMz}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                             <div class="col-md-4">
                                <div class="form-group">
                                    <label class="control-label">Lote</label>
                                    <input readonly name="txt_dscLote" type="text" title="Lote" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscLote}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>

						<div class="row">
							<div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Departamento</label>
	                                <select disabled class="form-control input-sm" title="Local" id="cbo_departamento" name="cbo_departamento">
	                                	<c:forEach items="${cboDepartamento.items}" var="item" >
	                                        <option <c:if test="${item.id == cboDepartamento.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
	                                <span class="help-block"></span>
	                            </div>
	                        </div>
	                        <div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Provincia</label>
	                                <select disabled class="form-control input-sm" title="Area" id="cbo_provincia" name="cbo_provincia">
										<c:forEach items="${cboProvincia.items}" var="item" >
											<option <c:if test="${item.id == cboProvincia.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
										</c:forEach>
	                                </select>
	                                <span class="help-block"></span>
                            	</div>
	                        </div>
	                        <div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Distrito</label>
	                                <select disabled class="form-control input-sm" title="Area" id="cbo_distrito" name="cbo_distrito">
										<c:forEach items="${cboDistrito.items}" var="item" >
											<option <c:if test="${item.id == cboDistrito.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
										</c:forEach>
	                                </select>
	                                <span class="help-block"></span>
                            	</div>
	                        </div>
						</div>
						
                        <div class="row" style="display: none;">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Ubigeo</label>
                                    <input readonly name="txt_codigoUbigeo" type="text" title="Ubigeo" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.codigoUbigeo}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                    </fieldset>
                    
                    <div> &nbsp; </div>
                    
                    <fieldset id="infoContable" name="infoContable">
                        <legend>INFORMACI&Oacute;N CONTABLE</legend>
                        
                        <div class="row">
                            <div class="col-md-6">
                            	<label class="control-label">Tipo Cuenta</label>
								<select disabled class="form-control input-sm" title="Tipo Cuenta" id="cbo_tipo_cuenta" name="cbo_tipo_cuenta">
                                    <c:forEach items="${cboTipoCuenta.items}" var="item" >
                                        <option <c:if test="${item.id == cboTipoCuenta.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
	                            </select>
								<span class="help-block"></span>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Tipo de Moneda</label>
                                    <select disabled class="form-control input-sm" title="Tipo de Moneda" name="txt_flgTipomo">
	                                    <c:forEach items="${cboTipoMoneda.items}" var="item" >
	                                        <option <c:if test="${item.id == cboTipoMoneda.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                                    
                        <div class="row">
                        	<div class="col-md-9">
	                        	<label class="control-label">Cuenta</label>
								<select disabled class="form-control input-sm" title="Cuenta" id="cbo_cuenta" name="cbo_cuenta">
                                    <c:forEach items="${cboCuentas.items}" var="item" >
                                        <option <c:if test="${item.id == cboCuentas.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
	                            </select>
								<span class="help-block"></span>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="control-label">Valor Contable</label>
                                    <input readonly name="dbl_ctdValor" type="text" title="Valor Contable" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.ctdValor}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                                    
				</fieldset>
				
				<div> &nbsp; </div>
				
                <fieldset id="infoRegistral" name="infoRegistral">
                        <legend>INFORMACI&Oacute;N REGISTRAL</legend>		
                        
                        <div class="row">
                      	  	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Oficina Registral DUDA</label> 
                                    <select disabled class="form-control input-sm" title="Oficina Registral" name="txt_flgTipore">
	                                    <c:forEach items="${cboOficinaRegistral.items}" var="item" >
	                                        <option <c:if test="${item.id == cboOficinaRegistral.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
	                                    </c:forEach>
	                                </select>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                             <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Tomo</label>
                                    <input readonly name="txt_dscTomo" type="text" title="Tomo" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscTomo}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Fojas</label>
                                    <input readonly name="int_dscFojas" type="text" title="Fojas" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscFojas}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Asiento</label>
                                    <input readonly name="txt_dscAsient" type="text" title="Asiento" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscAsient}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">C&oacute;digo Predio</label>
                                    <input readonly name="txt_codigoPredio" type="text" title="C&oacute;digo Predio" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.codigoPredio}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Partida Electr&oacute;nica</label>
                                    <input readonly name="txt_dscPelect" type="text" title="Partida Electr&oacute;nica" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscPelect}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                        	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Registro SINABIP DUDA</label>
                                    <input readonly name="txt_dscAsinab" type="text" title="Registro SINABIP" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscAsinab}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Propiedad Registral</label>
                                    <input readonly name="txt_dscPropie" type="text" title="Propiedad Registral" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscPropie}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                       	 	<div class="col-md-6">
                                <div class="form-group">
                                    <label class="cont	rol-label">Beneficiario</label>
                                    <input readonly name="txt_dscBenefi" type="text" title="Beneficiario" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.dscBenefi}">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                    </fieldset>
                    

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Usuario de registro</label>
                                <input readonly name="txt_usuReg" type="text" title="Usuario Registro" maxlength="50" class="form-control input-sm" value="${registro.usuReg}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label div-date-picker">Fecha de registro</label>
                                <input readonly name="fec_fecReg" type="text" title="Fecha de Registro" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaReg}' />">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Usuario de la &uacute;ltima modificaci&oacute;n</label>
                                <input readonly name="txt_usuAct" type="text" title="Usuario de la &uacute;ltima modificaci&oacute;n" maxlength="50" class="form-control input-sm" value="${registro.usuAct}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group div-date-picker">
                                <label class="control-label">&Uacute;ltima modificaci&oacute;n</label>
                                <input readonly name="fec_fechaAct" type="text" title="&Uacute;ltima modificaci&oacute;n" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaAct}' />">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Estado de registro</label>
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
                <button id="btn_cancelar" title="Regresar" class="btn btn-sm btn-primary btn-block">&nbsp; REGRESAR</button>
            </div>

        </div>

        <div> &nbsp; </div>
				
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

            $(location).prop('href', 'registro-locales.htm?action=mostrarBuscar');
        });
    }


</script>
