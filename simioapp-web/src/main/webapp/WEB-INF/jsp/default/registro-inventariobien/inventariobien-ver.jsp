<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">
    <div class="panel-heading"> <strong> VER INVENTARIOBIEN </strong> </div>
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
		                        <label class="control-label">InventarioBienID</label>
		                        <input readonly name="int_inventarioBienID" type="text" title="InventarioBienID" maxlength="10" class="form-control input-sm" value="${registro.inventarioBienID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">BienID</label>
		                        <input readonly name="int_bienID" type="text" title="BienID" maxlength="10" class="form-control input-sm" value="${registro.bienID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">InventarioID</label>
		                        <input readonly name="int_inventarioID" type="text" title="InventarioID" maxlength="10" class="form-control input-sm" value="${registro.inventarioID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EstadoSubida</label>
		                        <input readonly name="txt_estadoSubida" type="text" title="EstadoSubida" maxlength="1" class="form-control input-sm" value="${registro.estadoSubida}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">SobranteFaltante</label>
		                        <input readonly name="txt_sobranteFaltante" type="text" title="SobranteFaltante" maxlength="1" class="form-control input-sm" value="${registro.sobranteFaltante}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Inventariador</label>
		                        <input readonly name="txt_inventariador" type="text" title="Inventariador" maxlength="50" class="form-control input-sm" value="${registro.inventariador}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">CodigoPatrimonial</label>
		                        <input readonly name="txt_codigoPatrimonial" type="text" title="CodigoPatrimonial" maxlength="12" class="form-control input-sm" value="${registro.codigoPatrimonial}">
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
		                        <label class="control-label">LocalesID</label>
		                        <input readonly name="int_localesID" type="text" title="LocalesID" maxlength="10" class="form-control input-sm" value="${registro.localesID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">AreaID</label>
		                        <input readonly name="int_areaID" type="text" title="AreaID" maxlength="10" class="form-control input-sm" value="${registro.areaID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EmpleadoID</label>
		                        <input readonly name="int_empleadoID" type="text" title="EmpleadoID" maxlength="10" class="form-control input-sm" value="${registro.empleadoID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">OficinaID</label>
		                        <input readonly name="int_oficinaID" type="text" title="OficinaID" maxlength="10" class="form-control input-sm" value="${registro.oficinaID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">TipoCausalAlta</label>
		                        <input readonly name="txt_tipoCausalAlta" type="text" title="TipoCausalAlta" maxlength="50" class="form-control input-sm" value="${registro.tipoCausalAlta}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Anho</label>
		                        <input readonly name="int_anho" type="text" title="Anho" maxlength="10" class="form-control input-sm" value="${registro.anho}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Matricula</label>
		                        <input readonly name="txt_matricula" type="text" title="Matricula" maxlength="100" class="form-control input-sm" value="${registro.matricula}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Ancho</label>
		                        <input readonly name="dbl_ancho" type="text" title="Ancho" maxlength="23" class="form-control input-sm" value="${registro.ancho}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ValorLibro</label>
		                        <input readonly name="dbl_valorLibro" type="text" title="ValorLibro" maxlength="23" class="form-control input-sm" value="${registro.valorLibro}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">CuentaConSeguro</label>
		                        <input readonly name="txt_cuentaConSeguro" type="text" title="CuentaConSeguro" maxlength="1" class="form-control input-sm" value="${registro.cuentaConSeguro}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EstadoBien</label>
		                        <input readonly name="txt_estadoBien" type="text" title="EstadoBien" maxlength="1" class="form-control input-sm" value="${registro.estadoBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Dimensi&oacute;n</label>
		                        <input readonly name="txt_dimension" type="text" title="Dimensi&oacute;n" maxlength="100" class="form-control input-sm" value="${registro.dimension}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Condici&oacute;n</label>
		                        <input readonly name="txt_condicion" type="text" title="Condici&oacute;n" maxlength="1" class="form-control input-sm" value="${registro.condicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroDocAdquisici&oacute;n</label>
		                        <input readonly name="txt_numeroDocAdquisicion" type="text" title="NumeroDocAdquisici&oacute;n" maxlength="50" class="form-control input-sm" value="${registro.numeroDocAdquisicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Color</label>
		                        <input readonly name="txt_color" type="text" title="Color" maxlength="100" class="form-control input-sm" value="${registro.color}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaAdquisici&oacute;n</label>
		                        <input readonly name="fec_fechaAdquisicion" type="text" title="FechaAdquisici&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.fechaAdquisicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EntDisp</label>
		                        <input readonly name="txt_entDisp" type="text" title="EntDisp" maxlength="200" class="form-control input-sm" value="${registro.entDisp}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EntArre</label>
		                        <input readonly name="txt_entArre" type="text" title="EntArre" maxlength="100" class="form-control input-sm" value="${registro.entArre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaVafec</label>
		                        <input readonly name="fec_fechaVafec" type="text" title="FechaVafec" maxlength="10" class="form-control input-sm" value="${registro.fechaVafec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Edad</label>
		                        <input readonly name="txt_edad" type="text" title="Edad" maxlength="100" class="form-control input-sm" value="${registro.edad}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">MotivoEliminacionBien</label>
		                        <input readonly name="txt_motivoEliminacionBien" type="text" title="MotivoEliminacionBien" maxlength="200" class="form-control input-sm" value="${registro.motivoEliminacionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ValorAdquisici&oacute;n</label>
		                        <input readonly name="dbl_valorAdquisicion" type="text" title="ValorAdquisici&oacute;n" maxlength="23" class="form-control input-sm" value="${registro.valorAdquisicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">AnioFabricaci&oacute;n</label>
		                        <input readonly name="txt_anioFabricacion" type="text" title="AnioFabricaci&oacute;n" maxlength="4" class="form-control input-sm" value="${registro.anioFabricacion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ResolAfec</label>
		                        <input readonly name="txt_resolAfec" type="text" title="ResolAfec" maxlength="25" class="form-control input-sm" value="${registro.resolAfec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Especie</label>
		                        <input readonly name="txt_especie" type="text" title="Especie" maxlength="100" class="form-control input-sm" value="${registro.especie}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Raza</label>
		                        <input readonly name="txt_raza" type="text" title="Raza" maxlength="100" class="form-control input-sm" value="${registro.raza}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaAfec</label>
		                        <input readonly name="fec_fechaAfec" type="text" title="FechaAfec" maxlength="10" class="form-control input-sm" value="${registro.fechaAfec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FlgActo</label>
		                        <input readonly name="txt_flgActo" type="text" title="FlgActo" maxlength="100" class="form-control input-sm" value="${registro.flgActo}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Modelo</label>
		                        <input readonly name="txt_modelo" type="text" title="Modelo" maxlength="100" class="form-control input-sm" value="${registro.modelo}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FlgCausal</label>
		                        <input readonly name="txt_flgCausal" type="text" title="FlgCausal" maxlength="1" class="form-control input-sm" value="${registro.flgCausal}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Marca</label>
		                        <input readonly name="txt_marca" type="text" title="Marca" maxlength="100" class="form-control input-sm" value="${registro.marca}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroResolucionBaja</label>
		                        <input readonly name="txt_numeroResolucionBaja" type="text" title="NumeroResolucionBaja" maxlength="50" class="form-control input-sm" value="${registro.numeroResolucionBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ResolArre</label>
		                        <input readonly name="txt_resolArre" type="text" title="ResolArre" maxlength="25" class="form-control input-sm" value="${registro.resolArre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">DscOtros</label>
		                        <input readonly name="txt_dscOtros" type="text" title="DscOtros" maxlength="500" class="form-control input-sm" value="${registro.dscOtros}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaArre</label>
		                        <input readonly name="fec_fechaArre" type="text" title="FechaArre" maxlength="10" class="form-control input-sm" value="${registro.fechaArre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaDisposici&oacute;n</label>
		                        <input readonly name="fec_fechaDisposicion" type="text" title="FechaDisposici&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.fechaDisposicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ResolDisp</label>
		                        <input readonly name="txt_resolDisp" type="text" title="ResolDisp" maxlength="50" class="form-control input-sm" value="${registro.resolDisp}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Altura</label>
		                        <input readonly name="txt_altura" type="text" title="Altura" maxlength="20" class="form-control input-sm" value="${registro.altura}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroChasis</label>
		                        <input readonly name="txt_numeroChasis" type="text" title="NumeroChasis" maxlength="100" class="form-control input-sm" value="${registro.numeroChasis}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Valortasa</label>
		                        <input readonly name="txt_valortasa" type="text" title="Valortasa" maxlength="100" class="form-control input-sm" value="${registro.valortasa}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Placa</label>
		                        <input readonly name="txt_placa" type="text" title="Placa" maxlength="100" class="form-control input-sm" value="${registro.placa}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">SitBinv</label>
		                        <input readonly name="txt_sitBinv" type="text" title="SitBinv" maxlength="1" class="form-control input-sm" value="${registro.sitBinv}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroCuentaContable</label>
		                        <input readonly name="txt_numeroCuentaContable" type="text" title="NumeroCuentaContable" maxlength="20" class="form-control input-sm" value="${registro.numeroCuentaContable}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Serie</label>
		                        <input readonly name="txt_serie" type="text" title="Serie" maxlength="100" class="form-control input-sm" value="${registro.serie}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EstBien</label>
		                        <input readonly name="txt_estBien" type="text" title="EstBien" maxlength="1" class="form-control input-sm" value="${registro.estBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EntAfec</label>
		                        <input readonly name="txt_entAfec" type="text" title="EntAfec" maxlength="100" class="form-control input-sm" value="${registro.entAfec}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Codanterio</label>
		                        <input readonly name="txt_codanterio" type="text" title="Codanterio" maxlength="15" class="form-control input-sm" value="${registro.codanterio}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">TipoCuenta</label>
		                        <input readonly name="txt_tipoCuenta" type="text" title="TipoCuenta" maxlength="1" class="form-control input-sm" value="${registro.tipoCuenta}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EstGestio</label>
		                        <input readonly name="txt_estGestio" type="text" title="EstGestio" maxlength="1" class="form-control input-sm" value="${registro.estGestio}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">DocBaja</label>
		                        <input readonly name="txt_docBaja" type="text" title="DocBaja" maxlength="50" class="form-control input-sm" value="${registro.docBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroMotor</label>
		                        <input readonly name="txt_numeroMotor" type="text" title="NumeroMotor" maxlength="100" class="form-control input-sm" value="${registro.numeroMotor}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Longitud</label>
		                        <input readonly name="txt_longitud" type="text" title="Longitud" maxlength="20" class="form-control input-sm" value="${registro.longitud}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Pais</label>
		                        <input readonly name="txt_pais" type="text" title="Pais" maxlength="100" class="form-control input-sm" value="${registro.pais}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaBaja</label>
		                        <input readonly name="fec_fechaBaja" type="text" title="FechaBaja" maxlength="10" class="form-control input-sm" value="${registro.fechaBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaVarre</label>
		                        <input readonly name="fec_fechaVarre" type="text" title="FechaVarre" maxlength="10" class="form-control input-sm" value="${registro.fechaVarre}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">DenominacionBien</label>
		                        <input readonly name="txt_denominacionBien" type="text" title="DenominacionBien" maxlength="120" class="form-control input-sm" value="${registro.denominacionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaDepreciaci&oacute;n</label>
		                        <input readonly name="fec_fechaDepreciacion" type="text" title="FechaDepreciaci&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.fechaDepreciacion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ValorDeprecEjercicio</label>
		                        <input readonly name="dbl_valorDeprecEjercicio" type="text" title="ValorDeprecEjercicio" maxlength="23" class="form-control input-sm" value="${registro.valorDeprecEjercicio}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ValorDeprecAcumulado</label>
		                        <input readonly name="dbl_valorDeprecAcumulado" type="text" title="ValorDeprecAcumulado" maxlength="23" class="form-control input-sm" value="${registro.valorDeprecAcumulado}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ValorNeto</label>
		                        <input readonly name="dbl_valorNeto" type="text" title="ValorNeto" maxlength="23" class="form-control input-sm" value="${registro.valorNeto}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">TipUsoCuenta</label>
		                        <input readonly name="txt_tipUsoCuenta" type="text" title="TipUsoCuenta" maxlength="1" class="form-control input-sm" value="${registro.tipUsoCuenta}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">CatalogoBienID</label>
		                        <input readonly name="int_catalogoBienID" type="text" title="CatalogoBienID" maxlength="10" class="form-control input-sm" value="${registro.catalogoBienID}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">OtrasCaract</label>
		                        <input readonly name="txt_otrasCaract" type="text" title="OtrasCaract" maxlength="100" class="form-control input-sm" value="${registro.otrasCaract}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">DescripcionUbicacionBien</label>
		                        <input readonly name="txt_descripcionUbicacionBien" type="text" title="DescripcionUbicacionBien" maxlength="200" class="form-control input-sm" value="${registro.descripcionUbicacionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">CausalBaja</label>
		                        <input readonly name="txt_causalBaja" type="text" title="CausalBaja" maxlength="1" class="form-control input-sm" value="${registro.causalBaja}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ActoDisposicionBien</label>
		                        <input readonly name="txt_actoDisposicionBien" type="text" title="ActoDisposicionBien" maxlength="1" class="form-control input-sm" value="${registro.actoDisposicionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EntidadBeneficiadaActoDisposici&oacute;n</label>
		                        <input readonly name="txt_entidadBeneficiadaActoDisposicion" type="text" title="EntidadBeneficiadaActoDisposici&oacute;n" maxlength="200" class="form-control input-sm" value="${registro.entidadBeneficiadaActoDisposicion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">ActoAdministracionBien</label>
		                        <input readonly name="txt_actoAdministracionBien" type="text" title="ActoAdministracionBien" maxlength="1" class="form-control input-sm" value="${registro.actoAdministracionBien}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroResolucionAdministraci&oacute;n</label>
		                        <input readonly name="txt_numeroResolucionAdministracion" type="text" title="NumeroResolucionAdministraci&oacute;n" maxlength="50" class="form-control input-sm" value="${registro.numeroResolucionAdministracion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaAdministraci&oacute;n</label>
		                        <input readonly name="fec_fechaAdministracion" type="text" title="FechaAdministraci&oacute;n" maxlength="10" class="form-control input-sm" value="${registro.fechaAdministracion}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">FechaVencActoAdmin</label>
		                        <input readonly name="fec_fechaVencActoAdmin" type="text" title="FechaVencActoAdmin" maxlength="10" class="form-control input-sm" value="${registro.fechaVencActoAdmin}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">EntidadBeneficiadaActoAdmin</label>
		                        <input readonly name="txt_entidadBeneficiadaActoAdmin" type="text" title="EntidadBeneficiadaActoAdmin" maxlength="200" class="form-control input-sm" value="${registro.entidadBeneficiadaActoAdmin}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">DocAltaSbn</label>
		                        <input readonly name="txt_docAltaSbn" type="text" title="DocAltaSbn" maxlength="25" class="form-control input-sm" value="${registro.docAltaSbn}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">DocBajaSbn</label>
		                        <input readonly name="txt_docBajaSbn" type="text" title="DocBajaSbn" maxlength="25" class="form-control input-sm" value="${registro.docBajaSbn}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">Tipo</label>
		                        <input readonly name="txt_tipo" type="text" title="Tipo" maxlength="100" class="form-control input-sm" value="${registro.tipo}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">NumeroResolucionDisp</label>
		                        <input readonly name="txt_numeroResolucionDisp" type="text" title="NumeroResolucionDisp" maxlength="50" class="form-control input-sm" value="${registro.numeroResolucionDisp}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">CodigoBarras</label>
		                        <input readonly name="txt_codigoBarras" type="text" title="CodigoBarras" maxlength="200" class="form-control input-sm" value="${registro.codigoBarras}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">InventariadoPor</label>
		                        <input readonly name="txt_inventariadoPor" type="text" title="InventariadoPor" maxlength="50" class="form-control input-sm" value="${registro.inventariadoPor}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">RevisadoToma</label>
		                        <input readonly name="txt_revisadoToma" type="text" title="RevisadoToma" maxlength="1" class="form-control input-sm" value="${registro.revisadoToma}">
		                        <span class="help-block"></span>
		                    </div>
		                </div>
		            </div>
		                        
		            <div class="row">
		                <div class="col-md-8">
		                    <div class="form-group">
		                        <label class="control-label">InventariadorAnterior</label>
		                        <input readonly name="txt_inventariadorAnterior" type="text" title="InventariadorAnterior" maxlength="50" class="form-control input-sm" value="${registro.inventariadorAnterior}">
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

            $(location).prop('href', 'registro-inventariobien.htm?action=mostrarBuscar');
        });
    }

</script>