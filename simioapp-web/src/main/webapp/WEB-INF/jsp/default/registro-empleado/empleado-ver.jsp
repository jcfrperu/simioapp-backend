<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> VER EMPLEADO </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo</label>
                                <input readonly name="int_empleadoID" type="text" title="EmpleadoID" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.empleadoID}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Empleado</label>
                                <input readonly name="txt_codigo" type="text" title="Codigo" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.codigo}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                    </div>
                                        
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Tipo de documento</label>
                                <select disabled class="form-control input-sm" title="tipodoc" id="cbo_tipodoc" name="cbo_tipodoc">
                                    <c:forEach items="${cboTipoDoc.items}" var="item3" >
                                        <option <c:if test="${item3.id == cboTipoDoc.idSeleccionado}">selected</c:if> value="${item3.id}">${item3.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">N&uacute;mero documento de identidad</label>
                                <input readonly name="txt_numeroDocIdentPersonal" type="text" title="N&uacute;mero Documento de Identidad" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.numeroDocIdentPersonal}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Nombres</label>
                                <input readonly name="txt_nombres" type="text" title="Nombres" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.nombres}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Apellido Paterno</label>
                                <input readonly name="txt_apellidoPaterno" type="text" title="Apellido Paterno" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.apellidoPaterno}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                    </div>
                                
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Apellido Materno</label>
                                <input readonly name="txt_apellidoMaterno" type="text" title="Apellido Materno" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.apellidoMaterno}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                                <div class="form-group">
                                    <label class="control-label">Modalidad de contrato</label>
                                    <select disabled class="form-control input-sm" title="Modalidad de Contrato" id="cbo_modalidad" name="cbo_modalidad">
                                        <c:forEach items="${cboModalidad.items}" var="item" >
                                            <option <c:if test="${item.id == cboModalidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                        </c:forEach>
                                    </select>
                                    <span class="help-block"></span>
                                </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label">Usuario registro</label>
                                <input readonly name="txt_usuReg" type="text" title="Usuario Registro" maxlength="50" class="form-control input-sm" value="${registro.usuReg}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="control-label div-date-picker">Fecha registro</label>
                                <input readonly name="fec_fechaReg" type="text" title="Fecha de Registro" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaReg}' />">
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
                                <input readonly name="fec_act" type="text" title="&Uacute;ltima modificaci&oacute;n" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaAct}' />">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6">
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

        <div> &nbsp; </div>

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

            $(location).prop('href', 'registro-empleado.htm?action=mostrarBuscar');
        });
    }


</script>
