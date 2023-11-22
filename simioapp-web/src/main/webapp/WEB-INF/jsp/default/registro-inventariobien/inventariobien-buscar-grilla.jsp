<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="inventariobien-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
                        <th>InventarioBienID</th>
            <th>BienID</th>
            <th>InventarioID</th>
            <th>EstadoSubida</th>
            <th>SobranteFaltante</th>
            <th>Inventariador</th>
            <th>CodigoPatrimonial</th>
            <th>EntidadID</th>
            <th>LocalesID</th>
            <th>AreaID</th>
            <th>EmpleadoID</th>
            <th>OficinaID</th>
            <th>TipoCausalAlta</th>
            <th>Anho</th>
            <th>Matricula</th>
            <th>Ancho</th>
            <th>ValorLibro</th>
            <th>CuentaConSeguro</th>
            <th>EstadoBien</th>
            <th>Dimensi&oacute;n</th>
            <th>Condici&oacute;n</th>
            <th>NumeroDocAdquisici&oacute;n</th>
            <th>Color</th>
            <th>FechaAdquisici&oacute;n</th>
            <th>EntDisp</th>
            <th>EntArre</th>
            <th>FechaVafec</th>
            <th>Edad</th>
            <th>MotivoEliminacionBien</th>
            <th>ValorAdquisici&oacute;n</th>
            <th>AnioFabricaci&oacute;n</th>
            <th>ResolAfec</th>
            <th>Especie</th>
            <th>Raza</th>
            <th>FechaAfec</th>
            <th>FlgActo</th>
            <th>Modelo</th>
            <th>FlgCausal</th>
            <th>Marca</th>
            <th>NumeroResolucionBaja</th>
            <th>ResolArre</th>
            <th>DscOtros</th>
            <th>FechaArre</th>
            <th>FechaDisposici&oacute;n</th>
            <th>ResolDisp</th>
            <th>Altura</th>
            <th>NumeroChasis</th>
            <th>Valortasa</th>
            <th>Placa</th>
            <th>SitBinv</th>
            <th>NumeroCuentaContable</th>
            <th>Serie</th>
            <th>EstBien</th>
            <th>EntAfec</th>
            <th>Codanterio</th>
            <th>TipoCuenta</th>
            <th>EstGestio</th>
            <th>DocBaja</th>
            <th>NumeroMotor</th>
            <th>Longitud</th>
            <th>Pais</th>
            <th>FechaBaja</th>
            <th>FechaVarre</th>
            <th>DenominacionBien</th>
            <th>FechaDepreciaci&oacute;n</th>
            <th>ValorDeprecEjercicio</th>
            <th>ValorDeprecAcumulado</th>
            <th>ValorNeto</th>
            <th>TipUsoCuenta</th>
            <th>CatalogoBienID</th>
            <th>OtrasCaract</th>
            <th>DescripcionUbicacionBien</th>
            <th>CausalBaja</th>
            <th>ActoDisposicionBien</th>
            <th>EntidadBeneficiadaActoDisposici&oacute;n</th>
            <th>ActoAdministracionBien</th>
            <th>NumeroResolucionAdministraci&oacute;n</th>
            <th>FechaAdministraci&oacute;n</th>
            <th>FechaVencActoAdmin</th>
            <th>EntidadBeneficiadaActoAdmin</th>
            <th>DocAltaSbn</th>
            <th>DocBajaSbn</th>
            <th>Tipo</th>
            <th>NumeroResolucionDisp</th>
            <th>CodigoBarras</th>
            <th>InventariadoPor</th>
            <th>RevisadoToma</th>
            <th>InventariadorAnterior</th>

            <th title="Usuario de la &uacute;ltima modificaci&oacute;n">Usuario</th>
            <th title="Modificaci&oacute;n">Modificaci&oacute;n</th>
            <th title="Estado Registro BD">Estado BD</th>
        </tr>
    </thead>

    <tbody>

        <c:forEach items="${lista}" var="item" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 0}">
                    <c:set var="claseFila" value="odd" />
                </c:when>
                <c:otherwise>
                    <c:set var="claseFila" value="even" />
                </c:otherwise>
            </c:choose>

            <tr class="${claseFila}">
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.inventarioBienID}"></td>
                                <td>${item.inventarioBienID}</td>
                <td>${item.bienID}</td>
                <td>${item.inventarioID}</td>
                <td>${item.estadoSubida}</td>
                <td>${item.sobranteFaltante}</td>
                <td>${item.inventariador}</td>
                <td>${item.codigoPatrimonial}</td>
                <td>${item.entidadID}</td>
                <td>${item.localesID}</td>
                <td>${item.areaID}</td>
                <td>${item.empleadoID}</td>
                <td>${item.oficinaID}</td>
                <td>${item.tipoCausalAlta}</td>
                <td>${item.anho}</td>
                <td>${item.matricula}</td>
                <td>${item.ancho}</td>
                <td>${item.valorLibro}</td>
                <td>${item.cuentaConSeguro}</td>
                <td>${item.estadoBien}</td>
                <td>${item.dimension}</td>
                <td>${item.condicion}</td>
                <td>${item.numeroDocAdquisicion}</td>
                <td>${item.color}</td>
                <td>${item.fechaAdquisicion}</td>
                <td>${item.entDisp}</td>
                <td>${item.entArre}</td>
                <td>${item.fechaVafec}</td>
                <td>${item.edad}</td>
                <td>${item.motivoEliminacionBien}</td>
                <td>${item.valorAdquisicion}</td>
                <td>${item.anioFabricacion}</td>
                <td>${item.resolAfec}</td>
                <td>${item.especie}</td>
                <td>${item.raza}</td>
                <td>${item.fechaAfec}</td>
                <td>${item.flgActo}</td>
                <td>${item.modelo}</td>
                <td>${item.flgCausal}</td>
                <td>${item.marca}</td>
                <td>${item.numeroResolucionBaja}</td>
                <td>${item.resolArre}</td>
                <td>${item.dscOtros}</td>
                <td>${item.fechaArre}</td>
                <td>${item.fechaDisposicion}</td>
                <td>${item.resolDisp}</td>
                <td>${item.altura}</td>
                <td>${item.numeroChasis}</td>
                <td>${item.valortasa}</td>
                <td>${item.placa}</td>
                <td>${item.sitBinv}</td>
                <td>${item.numeroCuentaContable}</td>
                <td>${item.serie}</td>
                <td>${item.estBien}</td>
                <td>${item.entAfec}</td>
                <td>${item.codanterio}</td>
                <td>${item.tipoCuenta}</td>
                <td>${item.estGestio}</td>
                <td>${item.docBaja}</td>
                <td>${item.numeroMotor}</td>
                <td>${item.longitud}</td>
                <td>${item.pais}</td>
                <td>${item.fechaBaja}</td>
                <td>${item.fechaVarre}</td>
                <td>${item.denominacionBien}</td>
                <td>${item.fechaDepreciacion}</td>
                <td>${item.valorDeprecEjercicio}</td>
                <td>${item.valorDeprecAcumulado}</td>
                <td>${item.valorNeto}</td>
                <td>${item.tipUsoCuenta}</td>
                <td>${item.catalogoBienID}</td>
                <td>${item.otrasCaract}</td>
                <td>${item.descripcionUbicacionBien}</td>
                <td>${item.causalBaja}</td>
                <td>${item.actoDisposicionBien}</td>
                <td>${item.entidadBeneficiadaActoDisposicion}</td>
                <td>${item.actoAdministracionBien}</td>
                <td>${item.numeroResolucionAdministracion}</td>
                <td>${item.fechaAdministracion}</td>
                <td>${item.fechaVencActoAdmin}</td>
                <td>${item.entidadBeneficiadaActoAdmin}</td>
                <td>${item.docAltaSbn}</td>
                <td>${item.docBajaSbn}</td>
                <td>${item.tipo}</td>
                <td>${item.numeroResolucionDisp}</td>
                <td>${item.codigoBarras}</td>
                <td>${item.inventariadoPor}</td>
                <td>${item.revisadoToma}</td>
                <td>${item.inventariadorAnterior}</td>

                <td class="text-center">${item.usuAct}</td>
                <td class="text-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${item.fechaAct}" /></td>
                <td class="text-center">
                    <c:choose>
                        <c:when test="${item.indDel == '0'}">ACTIVO</c:when>
                        <c:when test="${item.indDel == '1'}">INACTIVO</c:when>
                        <c:otherwise>NO DEFINIDO</c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </c:forEach>

    </tbody>
</table>
