<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es"manifest="app.manifest">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- frontend-meta.jsp - inicio -->
    <meta name="description" content="Toma de Inventario">
    <meta name="author" content="SimiOS">
    <!-- frontend-meta.jsp - fin -->

    <title>Inventario SIMIOAPP - Toma de Inventario</title>

    <!-- Bootstrap Core CSS -->
    <link href="libs/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- MetisMenu CSS -->
    <link href="libs/metisMenu/css/metisMenu.min.css" rel="stylesheet" type="text/css">

    <!-- Boostrap 3 DateTimepicker CSS -->
    <link href="libs/bootstrap3-datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

    <!-- Custom Fonts CSS -->
    <link href="libs/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- DataTables CSS -->
    <link href="libs/datatables/DataTables-1.10.12/css/dataTables.bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="libs/datatables/Responsive-2.1.0/css/responsive.bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- SideBar CSS -->
    <link href="css/sidebar.css" rel="stylesheet" type="text/css">
    <link href="css/sidebar-style.css" rel="stylesheet" type="text/css">

    <!-- Custom CSS -->
    <link href="css/comunes.css" rel="stylesheet" type="text/css">

</head>

<body>
    <!-- barra de navegacion superior fija -->
    <div class="navbar navbar-static navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">

                <!-- boton que aparece en la izquierda -->
                <button type="button" class="navbar-toggle toggle-left hidden-md hidden-lg" data-toggle="sidebar" data-target=".sidebar-left">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>

                <!-- front-header-main.jsp - inicio -->
                <a class="navbar-brand" href="#" title="Sistema de Inventario"><strong>Inventario <em>SIMIOAPP</em></strong></a>
                <!-- front-header-main.jsp - fin -->

            </div>

            <div class="visible-sm-inline-block visible-md-inline-block visible-lg-inline-block">
                &nbsp;&nbsp;
                <span class="label label-primary div-header-inventariador" title="Inventariador">usuario</span>
                <span class="label label-danger div-header-empresa" title="Entidad">entidad</span>
                <span class="label label-warning div-header-inventario" title="Inventario">Modo Offline</span>
                &nbsp;&nbsp;
            </div>

            <button type="button" class="navbar-toggle toggle-right" data-toggle="sidebar" data-target=".sidebar-right">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

        </div>
    </div>

    <!-- inicio del contenido -->
    <div class="container-fluid">
        <div class="row">

            <!-- menu de la izquierda - inicio -->
            <div class="col-xs-6 col-sm-3 col-md-2 sidebar sidebar-left sidebar-animate sidebar-md-show">
                <ul class="nav navbar-stacked">
                    <li class="active">
                        <button id="btn-menu-izq-listado-bienes" class="btn btn-md btn-info btn-block botonMenu" title="Listado Bienes"><i class="fa fa-table fa-3x"></i><br />Listado Bienes</button>
                    </li>
                    <li>
                        <button id="btn-menu-izq-nuevo-bien" class="btn btn-md btn-primary btn-block botonMenu" title="Nuevo Bien"><i class="fa fa-plus-circle fa-3x"></i><br />Nuevo Bien</button>
                    </li>
                    <li>
                        <button id="btn-menu-izq-editar-bien" class="btn btn-md btn-warning btn-block botonMenu" title="Editar Bien"><i class="fa fa-pencil fa-3x"></i><br />Editar Bien</button>
                    </li>
                    <li>
                        <button id="btn-menu-izq-quitar-bien" class="btn btn-md btn-danger btn-block botonMenu" title="Eliminar Bien"><i class="fa fa-remove fa-3x"></i><br />Eliminar Bien</button>
                    </li>
                    <li>
                        <button id="btn-menu-izq-ver-bien" class="btn btn-md btn-info btn-block botonMenu" title="Ver bien"><i class="fa fa-columns fa-3x"></i><br />Ver Bien</button>
                    </li>
                    <li>
                        <button id="btn-menu-izq-resumen-toma" class="btn btn-md btn-success btn-block botonMenu" title="Resumen Toma de Inventario"><i class="fa fa-list-alt fa-3x"></i><br />Resumen</button>
                    </li>

                </ul>
            </div>
            <!-- menu de la izquierda - fin -->

            <!-- contenido del centro: header-second + contenido de paginas - inicio -->
            <div class="col-md-10 col-md-offset-2" id="div-panel-registro">

                <!-- frontend-header-second.jsp - inicio -->
                <div class="visible-xs-inline">
                    <div> &nbsp; </div>
                    <span class="label label-primary div-header-inventariador" title="Inventariador">usuario</span>
                    <span class="label label-danger div-header-empresa" title="Entidad">entidad</span>
                    <span class="label label-warning div-header-inventario" title="Inventario">Modo Offline</span>
                </div>

                <div> &nbsp; </div>
                <!-- frontend-header-second.jsp - fin -->

                <div class="row">
                    <div class="col-md-12">

                        <!-- contenido de paginas -->
                        <div id="page-wrapper" class="contenedor-app">

                            <div id="div-main-app">

                                <!-- div-vista-listado INICIO -->
                                <div id="div-vista-listado">
                                    <div class="panel panel-info">
                                        <div class="panel-heading"> <strong> MIS BIENES ASIGNADOS </strong> </div>
                                        <div class="panel-body">

                                            <div> &nbsp; </div>

                                            <div class="row" id="div-panel-buscar-listado">
                                                <div class="col-md-12">
                                                    <div class="input-group">
                                                        <input id="txt_buscar_listado" type="text" title="Buscar Bien" maxlength="50" class="form-control input-md" placeholder="Escriba algo y empieza a buscar">
                                                        <span class="input-group-btn">
                                                            <button id="btn_buscar_listado" title="Buscar" class="btn btn-md btn-primary btn-block"><span class="fa fa-search"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div> &nbsp; </div>

                                            <div class="row" id="div-panel-grilla-listado">
                                                <div class="col-md-12">
                                                    <table id="table-grilla-listado" class="table table-striped table-bordered table-hover">
                                                        <thead>
                                                            <tr>
                                                                <th>SEL</th>
                                                                <th>C&oacute;digo Patrimonial</th>
                                                                <th>Descripci&oacute;n</th>
                                                                <th>Estado del Bien</th>
                                                                <th>Revisado Toma</th>
                                                                <th>Estado Subida</th>
                                                                <th>Sobrante/Faltante</th>
                                                                <th>Local</th>
                                                                <th>&Aacute;rea</th>
                                                                <th>Oficina</th>
                                                                <th>C&oacute;digo Interno</th>
                                                                <th>Inventario Bien ID</th>
                                                            </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>

                                            <div> &nbsp; </div>

                                            <div class="row">
                                                <div class="col-xs-4 col-sm-2 col-md-1 col-lg-1 pull-left">
                                                    <button id="btn_export_excel_all" title="Exportar datos de la tabla" class="btn btn-md btn-success"><span class="fa fa-file-excel-o fa-2x"></span></button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <!-- div-vista-listado FIN -->


                                <!-- div-vista-registro INICIO -->
                                <div id="div-vista-registro">
									
									
									<!-- seleccion buscar bien - inicio -->
                                    <div class="row" id="div-buscar-codigo-patrimonial">
                                    	<div class="col-sm-12 col-md-12">
                                            <fieldset>
                                                <legend>BUSCAR C&Oacute;DIGO PATRIMONIAL (BIEN NO ASIGNADO)</legend>
		                                         <div class="row" id="div-panel-buscar-codigo-patrimonial">
		                                                <div class="col-md-12">
		                                                    <div class="input-group">
		                                                        <input id="txt_buscar_codigo_patrimonial" name="txt_buscar_codigo_patrimonial" type="text" title="Buscar Bien" maxlength="50" class="form-control input-md" placeholder="Ingrese el C&oacute;digo Patrimonial de un bien para buscarlo">
		                                                        <span class="input-group-btn">
		                                                            <button id="btn_buscar_codigo_patrimonial" name="btn_buscar_codigo_patrimonial" title="Buscar" class="btn btn-md btn-primary btn-block"><span class="fa fa-search"></span></button>
		                                                        </span>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-12">
		                                                    <div class="form-group">
		                                                        <label id="txt-mensaje-codigo-patrimonial" class="control-label">&nbsp;</label>
		
		                                                        <span class="help-block"></span>
		                                                    </div>
		                                                </div>
		                                            </div>
                                           </fieldset>
                                        </div>
                                    </div>
                                    <!-- seleccion buscar bien - fin -->
                                      
                                    <!-- seleccion del tipo de bien - inicio -->
                                    <div class="row" id="div-tipo-bien">
                                        <div class="col-sm-12 col-md-12">
											<!-- div-vista-listado-tipo-bien INICIO -->
											<div id="div-vista-listado-tipo-bien">
												<div class="panel panel-info">
													<div class="panel-heading">
														<strong> SELECCI&Oacute;N DEL TIPO DE BIEN </strong>
													</div>
													<div class="panel-body">

														<div>&nbsp;</div>
														<div class="row" id="div-panel-grilla-listado-tipo-bien">
															<div class="col-md-12">
																<table id="table-grilla-listado-tipo-bien"
																	class="table table-striped table-bordered table-hover">
																	<thead>
																		<tr>
																			<th>SEL</th>
																			<th>Codigo Tipo Bien</th>
																			<th>Tipo Bien</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
											<!-- div-vista-listado-tipo-bien FIN -->
                                        </div>
                                    </div>
                                    <!-- seleccion del tipo de bien - fin -->

                                    <!-- datos del bien - inicio -->
                                    <div class="row">
                                        <div class="col-sm-12 col-md-12">
                                            <fieldset>
                                                <legend>DATOS DEL BIEN</legend>
                                                <div class="row">
                                                    <div class="col-sm-12 col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label">Denominaci&oacute;n</label>
                                                            <input readOnly id="txt_denominacion" name="txt_denominacion" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label">Grupo Gen&eacute;rico</label>
                                                            <input readOnly id="txt_grupo_generico" name="txt_grupo_generico" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Clase</label>
                                                            <input readOnly id="txt_clase" name="txt_clase" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">C&oacute;digo Patrimonial</label>
                                                            <input readOnly id="txt_codigo_patrimonial" name="txt_codigo_patrimonial" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">C&oacute;digo Interno</label>
                                                            <input readOnly id="txt_codigo_interno" name="txt_codigo_interno" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">&Uacute;ltimo correlativo</label>
                                                            <input id="txt_ult_correlativo" name="txt_ult_correlativo" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Tipo de cuenta</label>
                                                            <select readOnly id="cbo_tipo_cuenta" name="cbo_tipo_cuenta" class="form-control input-sm">
                                                                <option value="P">DE USO PRIVADO</option>
                                                                <option value="E">DE USO ESTATAL</option>
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp;</label>
                                                            <div class="radio">
                                                                <label>
                                                                    <input id="rb_activo_fijo" type="radio" name="rb_tipo_cuenta" value="A" checked>
                                                                    <strong>Activo Fijo</strong>
                                                                </label>
                                                            </div>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp;</label>
                                                            <div class="radio">
                                                                <label>
                                                                    <input id="rb_cuenta_orden" type="radio" name="rb_tipo_cuenta" value="O" checked>
                                                                    <strong>Cuenta de Orden</strong>
                                                                </label>
                                                            </div>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Cuenta contable</label>
                                                            <select readOnly id="cbo_cuenta_contable" name="cbo_cuenta_contable" class="form-control input-sm">
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp;</label>
                                                            <input readOnly id="txt_des_cuenta_contable" name="txt_des_cuenta_contable" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Forma de Adquisici&oacute;n</label>
                                                            <select readOnly id="cbo_forma_adquisicion" name="cbo_forma_adquisicion" class="form-control input-sm">
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Fecha</label>
                                                            <div class='input-group date' id='div_txt_fecha_adquisicion'>
                                                                <input id="txt_fecha_adquisicion" name="txt_fecha_adquisicion" type="text" maxlength="10" class="form-control input-sm text-uppercase">
                                                                <span class="input-group-addon">
                                                                    <span class="glyphicon glyphicon-calendar"></span>
                                                                </span>
                                                            </div>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Resol. Alta</label>
                                                            <input readOnly id="txt_resol_alta" name="txt_resol_alta" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Valor de Adquisici&oacute;n</label>
                                                            <input readOnly id="txt_valor_adquisicion" name="txt_valor_adquisicion" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Valor Neto</label>
                                                            <input readOnly id="txt_valor_neto" name="txt_valor_neto" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">&nbsp;</label>
                                                            <div class="checkbox">
                                                                <label>
                                                                    <input id="chk_asegurado" name="chk_asegurado" type="checkbox" value="S">
                                                                    <strong>Asegurado</strong>
                                                                </label>
                                                            </div>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Estado del Bien</label>
                                                            <select readOnly id="cbo_estado_bien" name="cbo_estado_bien" class="form-control input-sm">
                                                                <option value="#">SELECCIONE ESTADO DEL BIEN</option>
                                                                <option value="N">MUY BUENO</option>
                                                                <option value="B">BUENO</option>
                                                                <option value="R">REGULAR</option>
                                                                <option value="M">MALO</option>
                                                                <option value="*">FALTANTE</option>
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-sm-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label">Empleado</label>
                                                            <div class="input-group">
                                                                <select readOnly id="cbo_usuario" name="cbo_usuario" class="form-control input-sm"></select>
                                                                <span class="input-group-btn">
                                                                    <button id="btn_buscarEmpleado" title="Buscar Empleado" class="btn btn-sm btn-primary btn-block"><span class="fa fa-search"></span></button>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label">Local</label>
                                                            <select readOnly id="cbo_local" name="cbo_local" class="form-control input-sm">
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label">&Aacute;rea</label>
                                                            <select readOnly id="cbo_area" name="cbo_area" class="form-control input-sm">
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-8 col-md-8">
                                                        <div class="form-group">
                                                            <label class="control-label">Oficina</label>
                                                            <select readOnly id="cbo_oficina" name="cbo_oficina" class="form-control input-sm">
                                                            </select>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                            </fieldset>
                                        </div>
                                    </div>
                                    <!-- datos del bien - fin -->

                                    <!-- detalle tecnico y otros - inicio -->
                                    <div class="row">
                                        <div class="col-sm-10 col-md-10">
                                            <fieldset>
                                                <legend>DETALLE T&Eacute;CNICO</legend>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Marca</label>
                                                            <input readOnly id="txt_marca" name="txt_marca" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Modelo</label>
                                                            <input readOnly id="txt_modelo" name="txt_modelo" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Tipo</label>
                                                            <input readOnly id="txt_tipo" name="txt_tipo" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Color</label>
                                                            <input readOnly id="txt_color" name="txt_color" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Serie</label>
                                                            <input readOnly id="txt_serie" name="txt_serie" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Placa</label>
                                                            <input readOnly id="txt_placa" name="txt_placa" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">N&deg; Motor</label>
                                                            <input readOnly id="txt_numero_motor" name="txt_numero_motor" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">N&deg; Chasis</label>
                                                            <input readOnly id="txt_numero_chasis" name="txt_numero_chasis" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Dimension</label>
                                                            <input readOnly id="txt_dimension" name="txt_dimension" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Año</label>
                                                            <input readOnly id="txt_anyo" name="txt_anyo" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Raza</label>
                                                            <input readOnly id="txt_raza" name="txt_raza" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Nombre</label>
                                                            <input readOnly id="txt_nombre" name="txt_nombre" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-4 col-md-4">
                                                        <div class="form-group">
                                                            <label class="control-label">Edad</label>
                                                            <input readOnly id="txt_edad" name="txt_edad" type="text" maxlength="50" class="form-control input-sm">
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-sm-12 col-md-12">
                                                        <div class="form-group">
                                                            <label class="control-label">Otros</label>
                                                            <textarea readOnly id="txt_otros" name="txt_otros" maxlength="200" class="form-control input-sm" rows="4"></textarea>
                                                            <span class="help-block"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                            </fieldset>
                                        </div>

                                        <div class="col-sm-2 col-md-2">
                                            <fieldset>
                                                <legend>OTROS</legend>
                                                <button disabled="true" id="btn_otros" class="btn btn-sm btn-primary btn-block" title="Otros" data-toggle="modal" data-target="#divModalOtros"><i class="fa fa-file-o fa-4x"></i></button>
                                            </fieldset>
                                        </div>
                                    </div>
                                    <!--detalle tecnico y otros - fin -->

                                    <!-- botones - inicio -->
                                    <div class="row">
                                        <div class="col-sm-12 col-md-12">
                                            <div class="panel panel-default">

                                                <div class="panel-body">

                                                    <div class="row">

                                                        <div class="col-sm-3 col-md-2 col-sm-offset-1 col-md-offset-3">
                                                            <button id="bnt_grabar" class="btn btn-md btn-primary btn-block" title="Grabar">GRABAR</button>
                                                        </div>

                                                        <div class="col-sm-3 col-md-2" style="display: none">
                                                            <button id="btn_cancelar" class="btn btn-md btn-primary btn-block" title="Cancelar">CANCELAR</button>
                                                        </div>

                                                        <div class="col-sm-3 col-md-2" style="display: none">
                                                            <button id="btn_salir" class="btn btn-md btn-primary btn-block" title="Salir">SALIR</button>
                                                        </div>

                                                    </div>

                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <!-- botones - fin -->

                                    <div class="row"> &nbsp; </div>

                                    <!-- popups - inicio -->
                                    <div id="divModalOtros" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
                                        <div class="modal-dialog modal-lg" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <strong>Datos T&eacute;cnicos - Otros</strong>
                                                </div>
                                                <div class="modal-body">
                                                    <!-- contenido del modal - inicio -->
                                                    <fieldset>
                                                        <legend>DATOS T&Eacute;CNICOS - DATOS GENERALES</legend>

                                                        <div class="row">
                                                            <div class="col-sm-12 col-md-12">
                                                                <div class="form-group">
                                                                    <label class="control-label">Marca/Fabricante</label>
                                                                    <input id="txt_DatosTecnicos_marcaFabricante" name="txt_marcaFabricante" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-sm-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label">Modelo</label>
                                                                    <input id="txt_DatosTecnicos_modelo" name="txt_DatosTecnicos_modelo" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label">Tipo</label>
                                                                    <input id="txt_DatosTecnicos_Tipo" name="txt_DatosTecnicos_Tipo" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-sm-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label">N&uacute;mero de Matr&iacute;cula</label>
                                                                    <input id="txt_DatosTecnicos_NumeroMatricula" name="txt_DatosTecnicos_NumeroMatricula" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label">A&ntilde;o de Fabricaci&oacute;n</label>
                                                                    <input id="txt_DatosTecnicos_AnyoFabricacion" name="txt_DatosTecnicos_AnyoFabricacion" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="row">
                                                            <div class="col-sm-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label">N&uacute;mero de Serie</label>
                                                                    <input id="txt_DatosTecnicos_NumeroSerie" name="txt_DatosTecnicos_NumeroSerie" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-6 col-md-6">
                                                                <div class="form-group">
                                                                    <label class="control-label">Dimensiones</label>
                                                                    <input id="txt_DatosTecnicos_Dimensiones" name="txt_DatosTecnicos_Dimensiones" type="text" maxlength="50" class="form-control input-sm">
                                                                    <span class="help-block"></span>
                                                                </div>
                                                            </div>
                                                        </div>


                                                    </fieldset>
                                                    <!-- contenido del modal - final -->
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">Aceptar</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="divModalBuscarEmpleado" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
                                        <div class="modal-dialog modal-lg">

                                            <!-- Modal content-->
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" title="Cerrar">&times;</button>
                                                    <h4 class="modal-title">Seleccionar Empleado</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="row" id="div-panel-grilla-empleados">
                                                        <div class="col-md-12">
                                                            <table id="table-grilla-empleados" class="table table-striped table-bordered table-hover">
                                                                <thead>
                                                                <tr>
                                                                    <th>SEL</th>
                                                                    <th>Apellidos</th>
                                                                    <th>Nombres</th>
                                                                    <th>N&uacute;mero Documento</th>
                                                                </tr>
                                                                </thead>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button id="btnAceptarModalBuscarEmpleado" type="button" class="btn btn-primary" title="Seleccionar Empleado">Seleccionar</button>
                                                    <button id="btnCerrarModalBuscarEmpleado" type="button" class="btn btn-primary" title="Cancelar B&uacute;squeda">Cancelar</button>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- popups - fin -->

                                </div>
                                <!-- div-vista-registro FIN -->


                                <!-- div-vista-ver INICIO -->
                                <div id="div-vista-ver">
                                </div>
                                <!-- div-vista-ver FIN -->


                                <!-- div-vista-resumen INICIO -->
                                <div id="div-vista-resumen">
                                </div>
                                <!-- div-vista-resumen FIN -->

                                <!-- div-vista-conexion INICIO -->
                                <div id="div-vista-conexion">
                                    <div class="row">
                                        <div class="col-sm-12 col-md-12">
                                            <div class="panel panel-info">

                                                <div class="panel-heading">
                                                    Comprobar Conexi&oacute;n a Internet
                                                </div>

                                                <div class="panel-body">
                                                    <div class="row">
                                                        <div class="col-sm-12">
                                                            <div id="conexionMsgConectado" class="alert alert-success" role="alert">
                                                                <strong>Te encuentras conectado a Internet</strong>&nbsp;&nbsp;<i class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i>
                                                            </div>
                                                        </div>
                                                        <div class="col-sm-12">
                                                            <div id="conexionMsgNoConectado" class="alert alert-danger" role="alert">
                                                                <strong>No estas conectado a Internet</strong>&nbsp;&nbsp;<i class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-sm-4 col-md-3">
                                                            <button id="btn_comprobar_conexion" class="btn btn-md btn-primary btn-block" title="Comprobar Conexi&oacute;n a Internet">Comprobar Conexi&oacute;n</button>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- div-vista-conexion FIN -->

                            </div>

                        </div>

                    </div>
                </div>

            </div>
            <!-- contenido del centro: header-second + contenido de paginas - fin -->

            <!-- menu de la derecha - inicio -->
            <div class="col-xs-6 col-sm-3 col-md-2 sidebar sidebar-right sidebar-animate">
                <ul class="nav navbar-stacked">
                    <li>
                        <button id="btn-menu-der-comprobar-conexion" class="btn btn-md btn-warning btn-block botonMenu" title="Comprobar Conexi&oacute;n a Internet"><i class="fa fa-wifi fa-3x"></i><br />Comprobar Conexi&oacute;n</button>
                    </li>
                    <li class="active">
                        <button id="btn-menu-der-ir-modo-online" class="btn btn-md btn-primary btn-block botonMenu" title="Ir a Modo Online"><i class="fa fa-toggle-on fa-3x"></i><br />Ir a Modo Online</button>
                    </li>
                </ul>
            </div>
            <!-- menu de la derecha - fin -->

            <!-- forms ocultos -->
            <div id="hiddenForms">
                <form id="form-ir-modo-online" action="sincronizar.htm" method="POST" role="form">
                    <input type="hidden" name="action" value="mostrarSincronizar">
                    <input type="hidden" name="usuario" value="">
                    <input type="hidden" name="entidad" value="">
                    <input type="hidden" name="inventario" value="">
                    <input type="hidden" name="token" value="">
                    <input type="hidden" name="dataSyncJSON" value="">
                </form>
            </div>

            <!-- popups generales: mensajes y confirmacion -->
            <div id="divModalPopup" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
                <div id="divPopupContainerClass" class="container appMsgConfirmContainer verticalAlignmentHelper">
                    <div class="row verticalAlignCenter">
                        <div class="col-xs-12">
                            <div id="divPopupPanelClass" class="panel panel-info">
                                <div class="panel-heading" id="divPopupMensaje">Mensaje</div>
                                <div class="panel-body">
                                    <div class="text-center">
                                        <button type="button" id="btnPopupAceptar" class="btn btn-primary btn-sm" data-dismiss="modal">Aceptar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="divModalPopupSINO" class="modal fade" role="dialog" data-backdrop="static" data-keyboard="false">
                <div id="divPopupContainerClassSINO" class="container appMsgConfirmContainer verticalAlignmentHelper">
                    <div class="row verticalAlignCenter">
                        <div class="col-xs-12">
                            <div id="divPopupPanelClassSINO" class="panel panel-info">
                                <div class="panel-heading" id="divPopupMensajeSINO">Mensaje</div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-xs-5 col-xs-offset-1">
                                            <button type="button" id="btnPopupAceptarSINO" class="btn btn-primary btn-sm btn-block" data-dismiss="modal" title="Si">SI</button>
                                        </div>
                                        <div class="col-xs-5">
                                            <button type="button" id="btnPopupCancelarSINO" class="btn btn-primary btn-sm btn-block" data-dismiss="modal" title="No">NO</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <!-- jQuery -->
    <script src="libs/jquery/1.12.1/jquery.min.js"></script>
    <script src="libs/jquery-inputmask/jquery.inputmask.bundle.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="libs/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <!-- Side Bar JavaScript -->
    <script src="js/sidebar.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="libs/metisMenu/js/metisMenu.min.js"></script>

    <!-- Boostrap 3 DateTimepicker JavaScript -->
    <script src="libs/momentjs/moment-with-locales.min.js"></script>
    <script src="libs/bootstrap3-datepicker/js/bootstrap-datetimepicker.min.js"></script>

    <!-- Voca - String Library -->
    <script src="libs/voca/1.3.0/voca.min.js" type="text/javascript"></script>

    <!-- App Custom JavaScript -->
	<script src="js/simioapp-default.js"></script>
    <script src="js/jquery-simiosdb.js"></script>
    <script src="js/simiosdb.js"></script>
    <script src="js/simioapp-global.js"></script>
    <script src="js/simioapp-config.js"></script>
    <script src="js/simioapp-dao.js"></script>
    <script src="js/simioapp-view.js"></script>
	<script src="js/simioapp.js"></script>
    <script src="js/simioapp-listado.js"></script>
    <script src="js/simioapp-registro.js"></script>

    <!-- DataTables JavaScript -->
    <script type="text/javascript" src="libs/datatables/DataTables-1.10.12/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="libs/datatables/DataTables-1.10.12/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="libs/datatables/Responsive-2.1.0/js/dataTables.responsive.min.js"></script>
    <script type="text/javascript" src="libs/datatables/Responsive-2.1.0/js/responsive.bootstrap.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="libs/adaptative/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="libs/adaptative/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
    window.addEventListener('load', function(e) {
		
    	window.applicationCache.addEventListener('updateready', function(e) {
    		//Si hubo cambios en el manifiesto
        	if (window.applicationCache.status == window.applicationCache.UPDATEREADY) {
    			//Sustituira la antigua cache por la nueva
                window.applicationCache.swapCache();
                //if (confirm('A new version of this site is available. Load it?')) {
                    window.location.reload();
                //}
        	} else {
            // Si no hubo cambios en el manifiesto
        	}
    	}, false);
    }, false);
    
	$(document).ready(function() {
		initApp();
	});

</script>

    <!-- MUY IMPORTANTE: esta linea es muy importante, porque al ir al offline debe borrar la session, y no se puede -->
    <!--                 iendo al controlador (por el tema del cache html5) -->
    <!-- a la simple visualizacion de esta pagina elimina los datos de la session -->
    <c:remove var="usuario_session" scope="session" />

</body>

</html>