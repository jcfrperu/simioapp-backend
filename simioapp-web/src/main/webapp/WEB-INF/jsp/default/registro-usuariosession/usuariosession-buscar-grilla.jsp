<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="usuariosession-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
                        <th>UsuarioID</th>
            <th>EntidadID</th>
            <th>UltimoLogin</th>
            <th>UltimoCierre</th>
            <th>UltimaAcci&oacute;n</th>
            <th>MinutosValidez</th>
            <th>Estado</th>
            <th>IndPoliticaInactividad</th>
            <th>DatosIpLogin</th>
            <th>DatosIpAcci&oacute;n</th>
            <th>IndPoliticaIntentos</th>
            <th>NumeroIntentos</th>
            <th>NombresUsuario</th>
            <th>ApellidosUsuario</th>

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
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.usuarioID}"></td>
                                <td>${item.usuarioID}</td>
                <td>${item.entidadID}</td>
                <td>${item.ultimoLogin}</td>
                <td>${item.ultimoCierre}</td>
                <td>${item.ultimaAccion}</td>
                <td>${item.minutosValidez}</td>
                <td>${item.estado}</td>
                <td>${item.indPoliticaInactividad}</td>
                <td>${item.datosIpLogin}</td>
                <td>${item.datosIpAccion}</td>
                <td>${item.indPoliticaIntentos}</td>
                <td>${item.numeroIntentos}</td>
                <td>${item.nombresUsuario}</td>
                <td>${item.apellidosUsuario}</td>

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
