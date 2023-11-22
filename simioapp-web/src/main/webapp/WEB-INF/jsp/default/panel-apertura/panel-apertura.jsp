<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div>&nbsp;</div>

<div class="panel panel-info">

    <div class="panel-heading"> <strong> PANEL APERTURA DE INVENTARIO </strong> </div>
    <div class="panel-body">

        <div>&nbsp;</div>

        <!-- instancia de la barra de error superior -->
        <div id="divMsgGeneralErrorInstance"></div>

        <div class="row" id="div-panel-registro">
            <div class="col-md-12">

                <form id="form-registro" method="POST" role="form">

                    <div class="row">
                        <div class="col-md-9">
                            <div class="form-group">
                                <label class="control-label">Entidad</label>
                                <select class="form-control input-sm" title="Entidad" name="int_entidadID">
                                    <c:forEach items="${cboEntidad.items}" var="item" >
                                        <option <c:if test="${item.id == cboEntidad.idSeleccionado}">selected</c:if> value="${item.id}">${item.label}</option>
                                    </c:forEach>
                                </select>
                                <span class="help-block"></span>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="control-label">Fecha Apertura</label>
                                <input readonly name="fec_fechaApertura" type="text" title="Fecha Apertura" maxlength="10" class="form-control input-sm text-uppercase" value='${today}'>
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="control-label">Nombre</label>
                                <input name="txt_nombre" type="text" title="Nombre" maxlength="100" class="form-control input-sm text-uppercase" value="${registro.nombre}">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>

                </form>

            </div>
        </div>

        <div class="row" id="div-panel-botones">
            <div class="col-md-4 col-lg-3 col-md-offset-2 col-lg-offset-4">
                <button id="btn_aperturar" class="btn btn-md btn-primary btn-block" title="Aperturar Inventario">APERTURAR</button>
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

        $( '#div_fechaApertura' ).datetimepicker({
            locale: "es",
            format: "DD/MM/YYYY"
        });

        $( '#div_fechaCierre' ).datetimepicker({
            locale: "es",
            format: "DD/MM/YYYY"
        });

    }

    function initMask() {

        var div = $( '#div-panel-registro' );

        var control = div.find( 'input[name="int_inventarioID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );

        control = div.find( 'input[name="txt_nombre"]' );
//        control.inputmask( "[*|| ]{0,100}", {placeholder: ''});
        control.prop( 'maxlength', 100 );

        control = div.find( 'input[name="txt_estado"]' );
        control.inputmask( "[*|| ]{0,1}", {placeholder: ''});
        control.prop( 'maxlength', 1 );

        control = div.find( 'input[name="int_entidadID"]' );
        control.inputmask( "9{0,10}", {placeholder: ''});
        control.prop( 'maxlength', 10 );


    }

    function pagePostInit() {

    }

    function pageEvents() {

        eventAperturar();
    }

    function eventAperturar() {

        $('#btn_aperturar').on('click', function(e) {

            e.preventDefault();

            showConfirmation('Aperturar Inventario', '\u00BFSeguro que desea aperturar el inventario?', function() {

                var restore = estadoInputInicial('#div-panel-registro');

                habilitarControl('#btn_aperturar', false);

                $.ajax({
                    url: 'apertura-inventario.htm?action=aperturarInventario',
                    cache: false,
                    data: $('#form-registro').serialize(),
                    async: true,
                    type: 'POST',
                    dataType: 'json',
                    success: function(data) {

                        habilitarControl('#btn_aperturar', true);

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

                        habilitarControl('#btn_aperturar', false);
                        habilitarControles('#div-panel-registro', false);

                    },
                    error: function(data, textStatus, errorThrown) {
                        habilitarControl('#btn_aperturar', true);
                        handleError(data);
                    }
                });

            });

        });
    }



</script>