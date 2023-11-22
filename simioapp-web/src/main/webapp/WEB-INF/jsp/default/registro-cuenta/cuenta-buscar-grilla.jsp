<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="cuenta-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
            <th>Cuenta</th>
            <th>Denominaci&oacute;n</th>
            <th>Tipo de Cuenta</th>
            <th>Flag Cuenta</th>
            <th>Uso Cuenta</th>
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
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.cuentaID}"></td>
                <td>${item.cuenta}</td>
                <td><c:out value='${item.denomina}' escapeXml='true'></c:out></td>
                <td>${item.tipoCtaDescrip}</td>
                <td>${item.flagCtaDescrip}</td>
                <td>${item.usoCtaDescrip}</td>
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
