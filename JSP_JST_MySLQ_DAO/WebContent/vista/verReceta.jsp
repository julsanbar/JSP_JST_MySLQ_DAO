<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receta</title>
</head>
<body>
	
	<!-- Verificación del encondig -->
	<fmt:requestEncoding value="UTF-8"/>
		
	<c:set var="receta" value="${sessionScope.receta}"/>
	
	<table border="1">
		
		<tr>
			<th>Título</th>
			<th>Tipo harina</th>
			<th>Cantidad harina (gr)</th>
			<th>Líquidos (ml)</th>
			<th>Cantidad levadura (gr)</th>
			<th>Cantidad azúcar (gr)</th>
			<th>Cantidad sal (gr)</th>
			<th>Preparación</th>
		</tr>
	
		<tr>
			
			<td><c:out value="${receta.titulo}"/></td>
			<td><c:out value="${receta.harina}"/></td>
			<td><c:out value="${receta.cantidadHarina}"/>gr</td>
			<td><c:out value="${receta.liquidos}"/></td>		
			<td><c:out value="${receta.cantidadLevadura}"/>gr</td>
			<td><c:out value="${receta.cantidadAzucar}"/>gr</td>
			<td><c:out value="${receta.cantidadSal}"/>gr</td>
			<td><c:out value="${receta.preparacion}"/></td>			
		
		</tr>
	

	</table><br>

	<a href="${pageContext.request.contextPath}/index.html">Volver al menú</a><br>
	
	<form action="${pageContext.request.contextPath}/Controlador_Recetas" method="post">
		<input type="hidden" value="inserta" name="opcion"/>
		
		<c:choose>
			
			<c:when test="${(receta == null) || (requestScope.controlador != receta.titulo)}">
			
				<input type="submit" value="GuardarBD" name="guardarBD" disabled="disabled"/>
			
			</c:when>
			<c:otherwise>
			
				<input type="submit" value="GuardarBD" name="guardarBD"/>	
			
			</c:otherwise>
		
		</c:choose>
		
	</form>
	
	<c:if test="${requestScope.tituloinsertado != null}">
	
		<h3 style="color:green">La receta se ha guardado en la Base de datos correctamente</h3>
	
	</c:if>
	
	<c:if test="${(errorInserta != null) || (not empty errorInserta)}">
	
		<h2 style="color:red"><c:out value="${errorInserta}"/></h2>
	
	</c:if>
	
</body>
</html>