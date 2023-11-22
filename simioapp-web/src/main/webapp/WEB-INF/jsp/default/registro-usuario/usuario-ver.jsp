<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row">
    <div class="col-md-12">
        <h4 class="page-header">
            <strong> VER USUARIO </strong> <br />
            <div class="css3gradient"></div>
        </h4>
    </div>
</div>

<!-- instancia de la barra de error superior -->
<div id="divMsgGeneralErrorInstance"></div>

<div class="row" id="div-panel-registro">
    <div class="col-md-12">

        <form id="form-registro" method="POST" role="form">
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">UsuarioID</label>
                        <input readonly name="txt_usuarioID" type="text" title="UsuarioID" maxlength="50" class="form-control input-sm" value="${registro.usuarioID}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">EntidadID</label>
                        <input readonly name="int_entidadID" type="text" title="EntidadID" maxlength="10" class="form-control input-sm" value="${registro.entidadID}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">Nombres</label>
                        <input readonly name="txt_nombres" type="text" title="Nombres" maxlength="100" class="form-control input-sm" value="${registro.nombres}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">ApellidoPaterno</label>
                        <input readonly name="txt_apellidoPaterno" type="text" title="ApellidoPaterno" maxlength="50" class="form-control input-sm" value="${registro.apellidoPaterno}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">ApellidoMaterno</label>
                        <input readonly name="txt_apellidoMaterno" type="text" title="ApellidoMaterno" maxlength="50" class="form-control input-sm" value="${registro.apellidoMaterno}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">Clave</label>
                        <input readonly name="txt_clave" type="text" title="Clave" maxlength="250" class="form-control input-sm" value="${registro.clave}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">Usuariocol</label>
                        <input readonly name="txt_usuariocol" type="text" title="Usuariocol" maxlength="45" class="form-control input-sm" value="${registro.usuariocol}">
                        <span class="help-block"></span>
                    </div>
                </div>
            </div>
                        
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <label class="control-label">ClaveAes</label>
                        <input readonly name="byte_claveAes" type="text" title="ClaveAes" maxlength="50" class="form-control input-sm" value="${registro.claveAes}">
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

            $(location).prop('href', 'registro-usuario.htm?action=mostrarBuscar');
        });
    }


</script>
