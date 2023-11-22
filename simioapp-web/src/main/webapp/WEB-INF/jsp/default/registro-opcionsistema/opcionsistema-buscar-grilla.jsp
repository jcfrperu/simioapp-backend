<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="opcionsistema-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
                        <th>OpcionID</th>
            <th>NombreOpci&oacute;n</th>
            <th>NivelOpci&oacute;n</th>
            <th>NumeroOrdenOpci&oacute;n</th>
            <th>UrlOpci&oacute;n</th>
            <th>ParentOpci&oacute;n</th>

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
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.opcionID}"></td>
                                <td>${item.opcionID}</td>
                <td>${item.nombreOpcion}</td>
                <td>${item.nivelOpcion}</td>
                <td>${item.numeroOrdenOpcion}</td>
                <td>${item.urlOpcion}</td>
                <td>${item.parentOpcion}</td>

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
