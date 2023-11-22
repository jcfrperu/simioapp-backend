<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!-- dependencias JS -->
<script src="js/jquery-simiosdb.js"></script>
<script src="js/simiosdb.js"></script>
<script src="js/simioapp-global.js"></script>
<script src="js/simioapp-config.js"></script>
<script src="js/simioapp-dao.js"></script>

<div>&nbsp;</div>

<div class="panel panel-primary">

    <div class="panel-heading"> <strong> SINCRONIZAR TOMA DE INVENTARIO </strong> </div>
    <div class="panel-body">
        
		<!-- instancia de la barra de error superior -->
		<div id="divMsgGeneralErrorInstance"></div>

        <!-- forms ocultos -->
        <div id="generic-hidden-forms">
            <form id="generic-sync-form" action="sincronizar.htm" method="POST">
                <input type="hidden" name="action" value="mostrarSincronizar">
                <input type="hidden" name="usuario" value="${usuarioSYNC}">
                <input type="hidden" name="entidad" value="${entidadSYNC}">
                <input type="hidden" name="inventario" value="${inventarioSYNC}">
                <input type="hidden" name="token" value="${tokenSYNC}">
                <input type="hidden" name="dataSyncJSON" value='${dataSyncJSONSYNC}'>
            </form>
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

		<strong>por favor espere...</strong>

		<div>&nbsp;</div>

    </div>
</div>

<script>

    // variables globales
    var TABLAS = new SimioTabla();
    var gSyncConfig = new SimioConfigApp();                       // configuracion de la BD
    var gSyncDAO = new SimioDAO(gSyncConfig.getNombreBD());       // agrupador de DAOs para manipular las tablas de la BD
    var gSyncRAM = {'tabla': {}};                                 // objeto que contiene las tablas en memoria

    $(document).ready(function() {

        $('#generic-hidden-forms').hide();

		redirigirSync();

    });

    function redirigirSync() {

        // todo lo hace local
        gSyncConfig.existeBD(function () {

            copiarTablaRAMSync(TABLAS.inventarioBien);
            copiarTablaRAMSync(TABLAS.oficina);
            copiarTablaRAMSync(TABLAS.area);
            copiarTablaRAMSync(TABLAS.locales);
            copiarTablaRAMSync(TABLAS.session);

            // se le da un delay porque initRAM parece que se demora
            setTimeout(function() {

                if ( estaDefinido(gSyncRAM.tabla.session) && estaDefinido(gSyncRAM.tabla.session[0]) ) {

                    var dataSyncJSON = {};

                    dataSyncJSON.tabla = {};
                    dataSyncJSON.tabla.inventarioBien = gSyncRAM.tabla.inventarioBien;
                    dataSyncJSON.tabla.oficina = gSyncRAM.tabla.oficina;
                    dataSyncJSON.tabla.area = gSyncRAM.tabla.area;
                    dataSyncJSON.tabla.locales = gSyncRAM.tabla.locales;

                    var sessionData = gSyncRAM.tabla.session[0];

                    var form = $('#generic-sync-form');

                    form.find('input[name="usuario"]').val(sessionData.usuario);
                    form.find('input[name="entidad"]').val(sessionData.entidad);
                    form.find('input[name="inventario"]').val(sessionData.inventario);
                    form.find('input[name="token"]').val(sessionData.token);
                    form.find('input[name="dataSyncJSON"]').val(JSON.stringify(dataSyncJSON));

                    form.submit();

                    return;
                }

                // redirigir, no tiene los atributos de sync por lo que se atendera como error
                $('#generic-sync-form').submit();

                return;

			}, 750);

        }, function () {

            // redirigir, no tiene los atributos de sync por lo que se atendera como error
            $('#generic-sync-form').submit();

			return;
        });

    }

    function copiarTablaRAMSync(nombreTabla) {

        gSyncRAM.tabla[nombreTabla] = [];

        gSyncDAO[nombreTabla].eachItem(function (item) {

            gSyncRAM.tabla[nombreTabla].push(item.value);
        });
    }


</script>
