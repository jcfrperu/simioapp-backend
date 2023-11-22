<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table table-striped table-bordered table-hover" id="inventario-buscar-grilla">

    <thead>
        <tr>
            <th title="Seleccionar Registro">SEL</th>
            <th>C&oacute;digo</th>
            <th>Nombre</th>
            <th>Apertura</th>
            <th>Cierre</th>
            <th>Estado Inventario</th>
            <th>Entidad</th>
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
                <td class="text-center"><input type="radio" name="codigo" title="Seleccionar" value="${item.inventarioID}"></td>
                <td>${item.inventarioID}</td>
                <td>${item.nombre}</td>
                <td class="text-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${item.fechaApertura}" /></td>
                <td class="text-center"><fmt:formatDate pattern="dd/MM/yyyy" value="${item.fechaCierre}" /></td>
                <td>${item.estadoDescrip}</td>
                <td>${item.entidadDescrip}</td>
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
