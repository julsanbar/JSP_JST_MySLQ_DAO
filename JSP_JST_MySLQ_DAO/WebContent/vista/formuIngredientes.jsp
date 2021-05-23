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
<title>Ingredientes receta</title>
</head>
<body>

	<c:if test="${(errores != null) || (not empty errores)}">
		<h2 style="color:red"><c:out value="${errores}"/></h2>
	</c:if>

	<form action="${pageContext.request.contextPath}/Controlador_Recetas" method="post">
	
		<input type="hidden" value="mostrar" name="opcion"/>
	
		<label for="titulo">Título de la receta:</label>
		<input type="text" name="titulo" id="titulo"/><br>

		<label for="harina">Tipos harina:</label>
		<select name="harina" id="harina">
		
			<option value="null" selected>---</option>
			<option value="trigo">trigo</option>
			<option value="trigo de fuerza">trigo de fuerza</option>
			<option value="espelta">espelta</option>
			<option value="espelta integral">espelta integral</option>
			<option value="centeno">centeno</option>
		
		</select>
		
		<input type="text" name="cantidadHarina" id="cantidadHarina" placeholder="Cantidad en gramos"/><br>
	
		<label for="agua">Agua</label>
		<input type="checkbox" name="liquidos" id="agua" value="agua"/>
		<input type="text" name="aguaMl" placeholder="ml"/><br>
		
		<label for="leche">Leche</label>
		<input type="checkbox" name="liquidos" id="leche" value="leche"/>
		<input type="text" name="lecheMl" placeholder="ml"/><br>
		
		<label for="aceite">Aceite</label>
		<input type="checkbox" name="liquidos" id="aceite" value="aceite"/>
		<input type="text" name="aceiteMl" placeholder="ml"/><br>
	
		<label for="levadura">Cantidad levadura</label>
		<input type="text" name="levadura" id="levadura" placeholder="gramos"/><br>

		<label for="azucar">Cantidad azúcar</label>
		<input type="text" name="azucar" id="azucar" placeholder="gramos"/><br>
		
		<label for="sal">Cantidad sal</label>
		<input type="text" name="sal" id="sal" placeholder="gramos"/><br>		
	
		<textarea rows="20" cols="40" name="preparacion" id="preparacion" placeholder="Indique la preparación"></textarea><br>
	
		<input type="submit" name="crear" value="Crear"/>
		
	</form><br>

	<a href="${pageContext.request.contextPath}/index.html">Volver al menú</a>

</body>
</html>