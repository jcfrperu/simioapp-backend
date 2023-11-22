<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> EDITAR BIEN </strong> </div>
    <div class="panel-body">
        
        <div>&nbsp;</div>
		
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>
		
		<div class="row" id="div-panel-registro">
		    <div class="col-md-12">
		
		        <form id="form-registro" method="POST" role="form">
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">BienID</label>
		                        <input name="int_bienID" type="text" title="BienID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.bienID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">CodigoPatrimonial</label>
		                        <input name="txt_codigoPatrimonial" type="text" title="CodigoPatrimonial" maxlength="12" class="form-control input-sm text-uppercase" value="${registro.codigoPatrimonial}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EntidadID</label>
		                        <input name="int_entidadID" type="text" title="EntidadID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.entidadID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">LocalesID</label>
		                        <input name="int_localesID" type="text" title="LocalesID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.localesID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">AreaID</label>
		                        <input name="int_areaID" type="text" title="AreaID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.areaID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EmpleadoID</label>
		                        <input name="int_empleadoID" type="text" title="EmpleadoID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.empleadoID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">OficinaID</label>
		                        <input name="int_oficinaID" type="text" title="OficinaID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.oficinaID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">TipoCausalAlta</label>
		                        <input name="txt_tipoCausalAlta" type="text" title="TipoCausalAlta" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.tipoCausalAlta}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Anho</label>
		                        <input name="int_anho" type="text" title="Anho" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.anho}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Matricula</label>
		                        <input name="txt_matricula" type="text" title="Matricula" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.matricula}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Ancho</label>
		                        <input name="dbl_ancho" type="text" title="Ancho" maxlength="23" class="form-control input-sm text-uppercase" value="${registro.ancho}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ValorLibro</label>
		                        <input name="dbl_valorLibro" type="text" title="ValorLibro" maxlength="23" class="form-control input-sm text-uppercase" value="${registro.valorLibro}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">CuentaConSeguro</label>
		                        <input name="txt_cuentaConSeguro" type="text" title="CuentaConSeguro" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.cuentaConSeguro}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EstadoBien</label>
		                        <input name="txt_estadoBien" type="text" title="EstadoBien" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.estadoBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Dimensi&oacute;n</label>
		                        <input name="txt_dimension" type="text" title="Dimensi&oacute;n" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.dimension}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Condici&oacute;n</label>
		                        <input name="txt_condicion" type="text" title="Condici&oacute;n" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.condicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroDocAdquisici&oacute;n</label>
		                        <input name="txt_numeroDocAdquisicion" type="text" title="NumeroDocAdquisici&oacute;n" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.numeroDocAdquisicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Color</label>
		                        <input name="txt_color" type="text" title="Color" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.color}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaAdquisici&oacute;n</label>
		                        <input name="fec_fechaAdquisicion" type="text" title="FechaAdquisici&oacute;n" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaAdquisicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EntDisp</label>
		                        <input name="txt_entDisp" type="text" title="EntDisp" maxlength="200" class="form-control input-sm text-uppercase" value="${registro.entDisp}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EntArre</label>
		                        <input name="txt_entArre" type="text" title="EntArre" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.entArre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaVafec</label>
		                        <input name="fec_fechaVafec" type="text" title="FechaVafec" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaVafec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Edad</label>
		                        <input name="txt_edad" type="text" title="Edad" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.edad}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">MotivoEliminacionBien</label>
		                        <input name="txt_motivoEliminacionBien" type="text" title="MotivoEliminacionBien" maxlength="200" class="form-control input-sm text-uppercase" value="${registro.motivoEliminacionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ValorAdquisici&oacute;n</label>
		                        <input name="dbl_valorAdquisicion" type="text" title="ValorAdquisici&oacute;n" maxlength="23" class="form-control input-sm text-uppercase" value="${registro.valorAdquisicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">AnioFabricaci&oacute;n</label>
		                        <input name="txt_anioFabricacion" type="text" title="AnioFabricaci&oacute;n" maxlength="4" class="form-control input-sm text-uppercase" value="${registro.anioFabricacion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ResolAfec</label>
		                        <input name="txt_resolAfec" type="text" title="ResolAfec" maxlength="25" class="form-control input-sm text-uppercase" value="${registro.resolAfec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Especie</label>
		                        <input name="txt_especie" type="text" title="Especie" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.especie}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Raza</label>
		                        <input name="txt_raza" type="text" title="Raza" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.raza}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaAfec</label>
		                        <input name="fec_fechaAfec" type="text" title="FechaAfec" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaAfec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FlgActo</label>
		                        <input name="txt_flgActo" type="text" title="FlgActo" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.flgActo}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Modelo</label>
		                        <input name="txt_modelo" type="text" title="Modelo" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.modelo}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FlgCausal</label>
		                        <input name="txt_flgCausal" type="text" title="FlgCausal" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.flgCausal}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Marca</label>
		                        <input name="txt_marca" type="text" title="Marca" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.marca}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroResolucionBaja</label>
		                        <input name="txt_numeroResolucionBaja" type="text" title="NumeroResolucionBaja" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.numeroResolucionBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ResolArre</label>
		                        <input name="txt_resolArre" type="text" title="ResolArre" maxlength="25" class="form-control input-sm text-uppercase" value="${registro.resolArre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DscOtros</label>
		                        <input name="txt_dscOtros" type="text" title="DscOtros" maxlength="500" class="form-control input-sm text-uppercase" value="${registro.dscOtros}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaArre</label>
		                        <input name="fec_fechaArre" type="text" title="FechaArre" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaArre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaDisposici&oacute;n</label>
		                        <input name="fec_fechaDisposicion" type="text" title="FechaDisposici&oacute;n" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaDisposicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ResolDisp</label>
		                        <input name="txt_resolDisp" type="text" title="ResolDisp" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.resolDisp}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Altura</label>
		                        <input name="txt_altura" type="text" title="Altura" maxlength="20" class="form-control input-sm text-uppercase" value="${registro.altura}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroChasis</label>
		                        <input name="txt_numeroChasis" type="text" title="NumeroChasis" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.numeroChasis}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Valortasa</label>
		                        <input name="txt_valortasa" type="text" title="Valortasa" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.valortasa}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Placa</label>
		                        <input name="txt_placa" type="text" title="Placa" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.placa}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">SitBinv</label>
		                        <input name="txt_sitBinv" type="text" title="SitBinv" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.sitBinv}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroCuentaContable</label>
		                        <input name="txt_numeroCuentaContable" type="text" title="NumeroCuentaContable" maxlength="20" class="form-control input-sm text-uppercase" value="${registro.numeroCuentaContable}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Serie</label>
		                        <input name="txt_serie" type="text" title="Serie" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.serie}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EstBien</label>
		                        <input name="txt_estBien" type="text" title="EstBien" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.estBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EntAfec</label>
		                        <input name="txt_entAfec" type="text" title="EntAfec" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.entAfec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Codanterio</label>
		                        <input name="txt_codanterio" type="text" title="Codanterio" maxlength="15" class="form-control input-sm text-uppercase" value="${registro.codanterio}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">TipoCuenta</label>
		                        <input name="txt_tipoCuenta" type="text" title="TipoCuenta" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.tipoCuenta}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EstGestio</label>
		                        <input name="txt_estGestio" type="text" title="EstGestio" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.estGestio}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DocBaja</label>
		                        <input name="txt_docBaja" type="text" title="DocBaja" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.docBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroMotor</label>
		                        <input name="txt_numeroMotor" type="text" title="NumeroMotor" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.numeroMotor}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Longitud</label>
		                        <input name="txt_longitud" type="text" title="Longitud" maxlength="20" class="form-control input-sm text-uppercase" value="${registro.longitud}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Pais</label>
		                        <input name="txt_pais" type="text" title="Pais" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.pais}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaBaja</label>
		                        <input name="fec_fechaBaja" type="text" title="FechaBaja" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaVarre</label>
		                        <input name="fec_fechaVarre" type="text" title="FechaVarre" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaVarre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DenominacionBien</label>
		                        <input name="txt_denominacionBien" type="text" title="DenominacionBien" maxlength="120" class="form-control input-sm text-uppercase" value="${registro.denominacionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaDepreciaci&oacute;n</label>
		                        <input name="fec_fechaDepreciacion" type="text" title="FechaDepreciaci&oacute;n" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaDepreciacion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ValorDeprecEjercicio</label>
		                        <input name="dbl_valorDeprecEjercicio" type="text" title="ValorDeprecEjercicio" maxlength="23" class="form-control input-sm text-uppercase" value="${registro.valorDeprecEjercicio}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ValorDeprecAcumulado</label>
		                        <input name="dbl_valorDeprecAcumulado" type="text" title="ValorDeprecAcumulado" maxlength="23" class="form-control input-sm text-uppercase" value="${registro.valorDeprecAcumulado}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ValorNeto</label>
		                        <input name="dbl_valorNeto" type="text" title="ValorNeto" maxlength="23" class="form-control input-sm text-uppercase" value="${registro.valorNeto}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">TipUsoCuenta</label>
		                        <input name="txt_tipUsoCuenta" type="text" title="TipUsoCuenta" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.tipUsoCuenta}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">CatalogoBienID</label>
		                        <input name="int_catalogoBienID" type="text" title="CatalogoBienID" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.catalogoBienID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">OtrasCaract</label>
		                        <input name="txt_otrasCaract" type="text" title="OtrasCaract" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.otrasCaract}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DescripcionUbicacionBien</label>
		                        <input name="txt_descripcionUbicacionBien" type="text" title="DescripcionUbicacionBien" maxlength="200" class="form-control input-sm text-uppercase" value="${registro.descripcionUbicacionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">CausalBaja</label>
		                        <input name="txt_causalBaja" type="text" title="CausalBaja" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.causalBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ActoDisposicionBien</label>
		                        <input name="txt_actoDisposicionBien" type="text" title="ActoDisposicionBien" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.actoDisposicionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EntidadBeneficiadaActoDisposici&oacute;n</label>
		                        <input name="txt_entidadBeneficiadaActoDisposicion" type="text" title="EntidadBeneficiadaActoDisposici&oacute;n" maxlength="200" class="form-control input-sm text-uppercase" value="${registro.entidadBeneficiadaActoDisposicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">ActoAdministracionBien</label>
		                        <input name="txt_actoAdministracionBien" type="text" title="ActoAdministracionBien" maxlength="1" class="form-control input-sm text-uppercase" value="${registro.actoAdministracionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">NumeroResolucionAdministraci&oacute;n</label>
		                        <input name="txt_numeroResolucionAdministracion" type="text" title="NumeroResolucionAdministraci&oacute;n" maxlength="50" class="form-control input-sm text-uppercase" value="${registro.numeroResolucionAdministracion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaAdministraci&oacute;n</label>
		                        <input name="fec_fechaAdministracion" type="text" title="FechaAdministraci&oacute;n" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaAdministracion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">FechaVencActoAdmin</label>
		                        <input name="fec_fechaVencActoAdmin" type="text" title="FechaVencActoAdmin" maxlength="10" class="form-control input-sm text-uppercase" value="${registro.fechaVencActoAdmin}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">EntidadBeneficiadaActoAdmin</label>
		                        <input name="txt_entidadBeneficiadaActoAdmin" type="text" title="EntidadBeneficiadaActoAdmin" maxlength="200" class="form-control input-sm text-uppercase" value="${registro.entidadBeneficiadaActoAdmin}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DocAltaSbn</label>
		                        <input name="txt_docAltaSbn" type="text" title="DocAltaSbn" maxlength="25" class="form-control input-sm text-uppercase" value="${registro.docAltaSbn}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">DocBajaSbn</label>
		                        <input name="txt_docBajaSbn" type="text" title="DocBajaSbn" maxlength="25" class="form-control input-sm text-uppercase" value="${registro.docBajaSbn}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		            
		
		            <div class="row">
		                <div class="col-md-6">
		                    <div class="form-group">
		                        <label class="control-label">Estado Registro</label>
		                         <select class="form-control input-sm" title="Estado Registro" name="cbo_indDel">
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
		
		    <div class="col-md-2 col-md-offset-4">
		        <button id="btn_editar" class="btn btn-md btn-primary btn-block" title="Guardar"><i class="fa fa-floppy-o"></i>&nbsp; GUARDAR</button>
		    </div>
		
		    <div class="col-md-2">
		        <button id="btn_cancelar" class="btn btn-md btn-primary btn-block .btnCancelar" title="Cancelar">&nbsp; CANCELAR</button>
		    </div>
		
		</div>
		
		<div>&nbsp;</div>
		
		<!-- popups -->
		<c:import url="/admin.htm">
		    <c:param name="action" value="cargarPage"/>
		    <c:param name="_page" value="popups"/>
		    <c:param name="_module" value=""/>
		    <c:param name="_template" value="default"/>
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

<div>&nbsp;</div>

<script>

    $(document).ready(function() {
        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

        $('textarea').css('resize','none');
        
        initMask();
        initFechas();

    }
    
	function initFechas() {
	
	}    

	function initMask() {
    	
    	var div = $( '#div-panel-registro' );
    	var control = null;
    	
        control = div.find( 'input[name="int_bienID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_codigoPatrimonial"]' );
        control.inputmask( "[*|| ]{0,12}", {placeholder: ''});
        control.prop( 'maxlength', 12 );

        control = div.find( 'input[name="int_entidadID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="int_localesID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="int_areaID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="int_empleadoID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="int_oficinaID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_tipoCausalAlta"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="int_anho"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_matricula"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="dbl_ancho"]' );
        control.inputmask( "decimal", {placeholder: ''});
        control.prop('maxlength', 23);

        control = div.find( 'input[name="dbl_valorLibro"]' );
        control.inputmask( "decimal", {placeholder: ''});
        control.prop('maxlength', 23);

        control = div.find( 'input[name="txt_cuentaConSeguro"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_estadoBien"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_dimension"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_condicion"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_numeroDocAdquisicion"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="txt_color"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="fec_fechaAdquisicion"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_entDisp"]' );
        control.inputmask( "[*|| ]{0,200}", {placeholder: ''});
        control.prop( 'maxlength', 200 );

        control = div.find( 'input[name="txt_entArre"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="fec_fechaVafec"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_edad"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_motivoEliminacionBien"]' );
        control.inputmask( "[*|| ]{0,200}", {placeholder: ''});
        control.prop( 'maxlength', 200 );

        control = div.find( 'input[name="dbl_valorAdquisicion"]' );
        control.inputmask( "decimal", {placeholder: ''});
        control.prop('maxlength', 23);

        control = div.find( 'input[name="txt_anioFabricacion"]' );
        control.inputmask( "[*|| ]{0,4}", {placeholder: ''});
        control.prop( 'maxlength', 4 );

        control = div.find( 'input[name="txt_resolAfec"]' );
        control.inputmask( "[*|| ]{0,25}", {placeholder: ''});
        control.prop( 'maxlength', 25 );

        control = div.find( 'input[name="txt_especie"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_raza"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="fec_fechaAfec"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_flgActo"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_modelo"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_flgCausal"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_marca"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_numeroResolucionBaja"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="txt_resolArre"]' );
        control.inputmask( "[*|| ]{0,25}", {placeholder: ''});
        control.prop( 'maxlength', 25 );

        control = div.find( 'input[name="txt_dscOtros"]' );
        control.inputmask( "[*|| ]{0,500}", {placeholder: ''});
        control.prop( 'maxlength', 500 );

        control = div.find( 'input[name="fec_fechaArre"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="fec_fechaDisposicion"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_resolDisp"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="txt_altura"]' );
        control.inputmask( "[*|| ]{0,20}", {placeholder: ''});
        control.prop( 'maxlength', 20 );

        control = div.find( 'input[name="txt_numeroChasis"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_valortasa"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_placa"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_sitBinv"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_numeroCuentaContable"]' );
        control.inputmask( "[*|| ]{0,20}", {placeholder: ''});
        control.prop( 'maxlength', 20 );

        control = div.find( 'input[name="txt_serie"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_estBien"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_entAfec"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_codanterio"]' );
        control.inputmask( "[*|| ]{0,15}", {placeholder: ''});
        control.prop( 'maxlength', 15 );

        control = div.find( 'input[name="txt_tipoCuenta"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_estGestio"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_docBaja"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="txt_numeroMotor"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_longitud"]' );
        control.inputmask( "[*|| ]{0,20}", {placeholder: ''});
        control.prop( 'maxlength', 20 );

        control = div.find( 'input[name="txt_pais"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="fec_fechaBaja"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="fec_fechaVarre"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_denominacionBien"]' );
        control.inputmask( "[*|| ]{0,120}", {placeholder: ''});
        control.prop( 'maxlength', 120 );

        control = div.find( 'input[name="fec_fechaDepreciacion"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="dbl_valorDeprecEjercicio"]' );
        control.inputmask( "decimal", {placeholder: ''});
        control.prop('maxlength', 23);

        control = div.find( 'input[name="dbl_valorDeprecAcumulado"]' );
        control.inputmask( "decimal", {placeholder: ''});
        control.prop('maxlength', 23);

        control = div.find( 'input[name="dbl_valorNeto"]' );
        control.inputmask( "decimal", {placeholder: ''});
        control.prop('maxlength', 23);

        control = div.find( 'input[name="txt_tipUsoCuenta"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="int_catalogoBienID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_otrasCaract"]' );
        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_descripcionUbicacionBien"]' );
        control.inputmask( "[*|| ]{0,200}", {placeholder: ''});
        control.prop( 'maxlength', 200 );

        control = div.find( 'input[name="txt_causalBaja"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_actoDisposicionBien"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_entidadBeneficiadaActoDisposicion"]' );
        control.inputmask( "[*|| ]{0,200}", {placeholder: ''});
        control.prop( 'maxlength', 200 );

        control = div.find( 'input[name="txt_actoAdministracionBien"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="txt_numeroResolucionAdministracion"]' );
        control.inputmask( "[*|| ]{0,50}", {placeholder: ''});
        control.prop( 'maxlength', 50 );

        control = div.find( 'input[name="fec_fechaAdministracion"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="fec_fechaVencActoAdmin"]' );
        control.inputmask( "9{2}/9{2}/{1}9{4}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_entidadBeneficiadaActoAdmin"]' );
        control.inputmask( "[*|| ]{0,200}", {placeholder: ''});
        control.prop( 'maxlength', 200 );

        control = div.find( 'input[name="txt_docAltaSbn"]' );
        control.inputmask( "[*|| ]{0,25}", {placeholder: ''});
        control.prop( 'maxlength', 25 );

        control = div.find( 'input[name="txt_docBajaSbn"]' );
        control.inputmask( "[*|| ]{0,25}", {placeholder: ''});
        control.prop( 'maxlength', 25 );


    }
    
    function pagePostInit() {
 
    }

    function pageEvents() {

        eventGuardar();
        eventCancelar();
    }

    function eventGuardar() {

        $('#btn_editar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Editar Registro', '\u00BFSeguro que desea editar el registro?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                $.ajax({
                    url: 'registro-bien.htm?action=guardarEditar',
                    cache: false,
                    data: $('#form-registro').serialize(),
                    async: true,
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {

                        if (huboErrorJson(data)) {

                            if (huboErrorValidacionJson(data)) {
                                estadoInputError('#div-panel-registro', data, restore);
                                return;
                            }

                            handleErrorJson(data);
                            return;
                        }

                        console.log('data: ' + JSON.stringify(data));

                        showMensaje('Mensaje', "La operaci\u00F3n se realiz\u00F3 con \u00E9xito");

                        habilitarControl('#btn_editar', false);
                        habilitarControles('#div-panel-registro', false);

                        $('#btn_cancelar').html( 'REGRESAR' );

                    },
                    error: function(data, textStatus, errorThrown) {
                        handleError(data);
                    }
                });

            });

        });
    }

    function eventCancelar() {

        $('#btn_cancelar').on('click', function(e) {

            $(location).prop('href', 'registro-bien.htm?action=mostrarBuscar');
        });
    }


</script>
