<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> VER OPCIONSISTEMA </strong> </div>
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
		                        <label class="control-label">OpcionID</label>
		                        <input readonly name="int_opcionID" type="text" title="OpcionID" maxlength="10" class="form-control input-sm" value="${registro.opcionID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NombreOpci&oacute;n</label>
		                        <input readonly name="txt_nombreOpcion" type="text" title="NombreOpci&oacute;n" maxlength="100" class="form-control input-sm" value="${registro.nombreOpcion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NivelOpci&oacute;n</label>
		                        <input readonly name="int_nivelOpcion" type="text" title="NivelOpci&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.nivelOpcion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroOrdenOpci&oacute;n</label>
		                        <input readonly name="int_numeroOrdenOpcion" type="text" title="NumeroOrdenOpci&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.numeroOrdenOpcion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">UrlOpci&oacute;n</label>
		                        <input readonly name="txt_urlOpcion" type="text" title="UrlOpci&oacute;n" maxlength="500" class="form-control input-sm" value="${registro.urlOpcion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ParentOpci&oacute;n</label>
		                        <input readonly name="int_parentOpcion" type="text" title="ParentOpci&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.parentOpcion}">
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

            $(location).prop('href', 'registro-opcionsistema.htm?action=mostrarBuscar');
        });
    }


</script>