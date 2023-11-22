<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong> PANEL IMPORTACI&Oacute;N EXCEL SBN - RESULTADO </strong> </div>
    <div class="panel-body">
        
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>
		
		<div>&nbsp;</div>
		
		<div class="row">
		    <div class="col-md-12">
		        <div class="alert alert-success" role="alert">${panel_importacion_resultado_locales}</div>
		    </div>
		</div>

		<div class="row">
            <div class="col-md-12">
                <div class="alert alert-success" role="alert">${panel_importacion_resultado_areas}</div>
            </div>
        </div>

		<div class="row">
            <div class="col-md-12">
                <div class="alert alert-success" role="alert">${panel_importacion_resultado_oficinas}</div>
            </div>
        </div>

		<div class="row">
            <div class="col-md-12">
                <div class="alert alert-success" role="alert">${panel_importacion_resultado_empleados}</div>
            </div>
        </div>

		<div class="row">
            <div class="col-md-12">
                <div class="alert alert-success" role="alert">${panel_importacion_resultado_bienes}</div>
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
