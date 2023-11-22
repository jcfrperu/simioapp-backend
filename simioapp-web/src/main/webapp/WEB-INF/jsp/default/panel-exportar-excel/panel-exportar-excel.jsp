<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong> FORMATO EXCEL PARA SBN </strong> </div>
    <div class="panel-body">
        
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>
		
		<div>&nbsp;</div>
		
		<div class="row" id="div-panel-registro">
		    <div class="col-md-12">

		         <form id="form-registro" action="exportar-excel.htm?action=exportarFormatoExcel" method="post" role="form">

                     <div class="row">
                         <div class="col-md-4">
                             <div class="form-group">
                                 <label class="control-label">Inventario</label>
                                 <select class="form-control input-sm" title="Lista de Inventarios Abiertos" name="int_inventarioID" id="int_inventarioID">
                                     <c:forEach items="${cboInventario.items}" var="item" >
                                         <option <c:if test="${item.id == cboInventario.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                     </c:forEach>
                                 </select>
                                 <span class="help-block"></span>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group">
                                 <label class="control-label">FUENTE DEL REPORTE</label>
                                 <select class="form-control input-sm" title="Fuente para generar el reporte (InventarioBien, InventarioApertura, Bien)" name="fuente_reporte">
                                     <option value="IB" selected>INVENTARIO BIEN</option>
                                     <option value="IA">INVENTARIO APERTURA</option>
                                 </select>
                                 <span class="help-block"></span>
                             </div>
                         </div>
                         <div class="col-md-4">
                             <div class="form-group">
                                 <label class="control-label">VERSION DEL REPORTE</label>
                                 <select class="form-control input-sm" title="Versi&oacute;n del Reporte" name="version">
                                     <option value="V6">V6 (2016)</option>
                                     <option value="V7" selected>V7 (2017)</option>
                                 </select>
                                 <span class="help-block"></span>
                             </div>
                         </div>
                     </div>

                     <div class="row">
                         <div class="col-md-4 col-md-offset-4">
                             <input id="btn_generarExcel" type="submit" class="btn btn-md btn-primary btn-block" title="Generar Excel" value="GENERAR EXCEL" />
                         </div>
                     </div>

                </form>

		    </div>
		</div>

		<!-- popups - popup de mensaje y de confirmacion -->
		<c:import url="/admin.htm">
		    <c:param name="action" value="cargarPage" />
		    <c:param name="_page" value="popups" />
		    <c:param name="_module" value="" />
		    <c:param name="_template" value="default" />
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

<script>

    $(document).ready(function() {

        pageInit();
        pageEvents();
        pagePostInit();
    });

    function pageInit() {

        initMask();
        initFechas();
    }

	function initFechas() {
	
	}

	function initMask() {
    	
    }

    function pageEvents() {

    }

    function pagePostInit() {

    }

</script>
