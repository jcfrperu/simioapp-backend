<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> VER CLAVE </strong> </div>
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
		                        <label class="control-label">ClaveID</label>
		                        <input readonly name="int_claveID" type="text" title="ClaveID" maxlength="10" class="form-control input-sm" value="${registro.claveID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ClaveMetodo</label>
		                        <input readonly name="txt_claveMetodo" type="text" title="ClaveMetodo" maxlength="50" class="form-control input-sm" value="${registro.claveMetodo}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ClaveParams</label>
		                        <input readonly name="txt_claveParams" type="text" title="ClaveParams" maxlength="5000" class="form-control input-sm" value="${registro.claveParams}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Clave</label>
		                        <input readonly name="txt_clave" type="text" title="Clave" maxlength="5000" class="form-control input-sm" value="${registro.clave}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ClaveBytes</label>
		                        <input readonly name="byte_claveBytes" type="text" title="ClaveBytes" maxlength="50" class="form-control input-sm" value="${registro.claveBytes}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ClaveBytesLength</label>
		                        <input readonly name="int_claveBytesLength" type="text" title="ClaveBytesLength" maxlength="10" class="form-control input-sm" value="${registro.claveBytesLength}">
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

            $(location).prop('href', 'registro-clave.htm?action=mostrarBuscar');
        });
    }


</script>