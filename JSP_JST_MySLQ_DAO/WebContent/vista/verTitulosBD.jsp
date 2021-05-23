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
<title>Títulos en la Base de datos</title>
</head>
<body>
	
	<c:set value="${requestScope.titulosBD}" var="listaTitulosBD"/>
	
	<c:choose>
	
		<c:when test="${(listaTitulosBD == null) || (empty listaTitulosBD)}">
		
			<h2 style="color:red">No existen titulos en la base de datos</h2>
		
		</c:when>
		<c:otherwise>
	
			<ul>
		
				<c:forEach var="titulo" items="${listaTitulosBD}">
					
					<li><c:out value="${titulo.titulo}"/></li>
				
				</c:forEach>
			
			</ul><br>
		
		</c:otherwise>
	
	</c:choose>
	
	<a href="${pageContext.request.contextPath}/index.html">Volver al menú</a>

</body>
</html>