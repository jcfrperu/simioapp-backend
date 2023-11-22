<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div> &nbsp; </div>

<div class="panel panel-info">
	<div class="panel-heading"> <strong> VER OFICINA </strong> </div>
	<div class="panel-body">
	
		<div> &nbsp; </div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">
                                
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo</label>
                                <input readonly name="int_oficinaID" type="text" title="C&oacute;digo" maxlength="50" class="form-control input-sm" value="${registro.oficinaID}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                         <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Abreviatura Oficina</label>
                                <input readonly name="txt_abreviaturaOficina" type="text" title="Abreviatura Oficina" maxlength="50" class="form-control input-sm" value="${registro.abreviaturaOficina}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                   
                                
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Entidad</label>
                                <input readonly name="txt_codigoInst" type="text" title="C&oacute;digo Entidad" maxlength="50" class="form-control input-sm" value="${registro.entidadID}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">C&oacute;digo Local</label>
                                <input readonly name="txt_codigoLocal" type="text" title="C&oacute;digo Local" maxlength="50" class="form-control input-sm" value="${registro.localesID}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                   
                                
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Abreviatura &Aacute;rea</label>
                                <input readonly name="txt_abreviaturaArea" type="text" title="Abreviatura &Aacute;rea" maxlength="50" class="form-control input-sm" value="${registro.abreviaturaOficina}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Nombre Oficina</label>
                                <input readonly name="txt_nombreOficina" type="text" title="Nombre Oficina" maxlength="50" class="form-control input-sm" value="${registro.nombreOficina}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                                
                  
                                
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Piso Oficina</label>
                                <input readonly name="txt_pisoOficina" type="text" title="Piso Oficina" maxlength="50" class="form-control input-sm" value="${registro.pisoOficina}">
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
                                <input readonly name="fec_fecReg" type="text" title="Fecha de Registro" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaReg}' />">
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
                                <input readonly name="fec_fecAct" type="text" title="&Uacute;ltima modificaci&oacute;n" maxlength="50" class="form-control input-sm" value="<fmt:formatDate pattern='dd/MM/yyyy HH:mm:ss' value='${registro.fechaAct}' />">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
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
                <button id="btn_cancelar" title="Regresar" class="btn btn-sm btn-primary btn-block">&nbsp; REGRESAR</button>
            </div>

        </div>

        <div> &nbsp; </div>
				
	</div>
</div>        


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

            $(location).prop('href', 'registro-oficina.htm?action=mostrarBuscar');
        });
    }


</script>
