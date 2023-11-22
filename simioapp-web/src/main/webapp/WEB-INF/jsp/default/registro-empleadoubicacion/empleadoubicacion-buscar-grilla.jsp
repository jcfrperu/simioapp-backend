<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="empleadoubicacion-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
            <th>C&oacute;digo</th>
            <th>Empleado</th>
            <th>Local</th>
            <th>&Aacute;rea</th>
            <th>Oficina</th>
            <th>Tipo Asociaci&oacute;n</th>
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
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.empleadoUbicacionID}"></td>
                <td>${item.empleadoUbicacionID}</td>
                <td title="Empleado ID: ${item.empleadoID}">${item.empleadoDescrip}</td>
                <td title="Local ID: ${item.localesID}">${item.localesDescrip}</td>
                <td title="&Aacute;rea ID: ${item.areaID}">${item.areaDescrip}</td>
                <td title="Oficina ID: ${item.oficinaID}">${item.oficinaDescrip}</td>
                <td>${item.tipoAsociacion}</td>
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
