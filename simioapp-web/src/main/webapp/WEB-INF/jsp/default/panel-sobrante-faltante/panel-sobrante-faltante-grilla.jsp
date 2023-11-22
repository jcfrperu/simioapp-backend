<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="panel-sobrante-faltante-grilla">

    <thead>
    <tr>
        <th title="Seleccionar Registros"><input class="chk_seleccionar_todo" type="checkbox"></th>
        <th>C&oacute;digo Patrimonial</th>
        <th>Estado Subida</th>
        <th>Sobrante / Faltante</th>
        <th>Inventariador</th>
        <th>Local</th>
        <th>&Aacute;rea</th>
        <th>Oficina</th>
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
            <td class="text-center"><input type="checkbox" name="codigo" title="Seleccionar" value="${item.inventarioBienID}"></td>
            <td title="Inventario Bien ID: ${item.inventarioBienID}">${item.codigoPatrimonial}</td>
            <td>${item.estadoSubidaDescrip}</td>
            <td>${item.sobranteFaltanteDescrip}</td>
            <td title="Usuario ID: ${item.inventariador}">${item.inventariadorDescrip}</td>
            <td title="Local ID: ${item.localesID}">${item.localesDescrip}</td>
            <td title="&Aacute;rea ID: ${item.areaID}">${item.areaDescrip}</td>
            <td title="Oficina ID: ${item.oficinaID}">${item.oficinaDescrip}</td>
        </tr>

    </c:forEach>

    </tbody>
</table>

<!-- info JSON extra -->
<input id="infoExtraHidden" type="hidden" value='${infoExtraHidden}' >