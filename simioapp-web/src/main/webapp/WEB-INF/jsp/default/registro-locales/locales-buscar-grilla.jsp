<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="locales-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
            <th>C&oacute;digo Local</th>
            <th>Nombre Local</th>
            <th>Direcci&oacute;n</th>
            <th>C&oacute;digo Predio</th>
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
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.localesID}"></td>
                <td title="Local ID: ${item.localesID}">${item.codigo}</td>
                <td>${item.nombreLocal}</td>
                <td>${item.direccion}</td>
                <td>${item.codigoPredio}</td>
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
