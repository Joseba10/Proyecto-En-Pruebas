<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zona de Compra</title>
<style>

h1{
margin-top:-60px;
}

footer{
margin-left:600px;
font-weight: bold;

}
form{
margin-top:100px;
margin-left: 550px;
}
</style>



</head>
<body>

<form action="/Zonadecompra" method="post">

<h1>Zona de Compra</h1>



		<!---manzanas --><!---tomates --><!-- -patatas --><!---mandarinas --><!---naranjas -->
		<!---peras --><!---vinos -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<p>Productos que quieres comprar y la cantidad</p>
			<c:forEach items="${arrayproductos}" var="producto">
				${producto.nombre}
				<input name="${producto.id }" type="number" value="" placeholder="Cantidad" pattern="[0-9]+"><br>
			</c:forEach>	
			

			<input type="submit" value="Enviar">
			
			<c:if test="${param.op==null or param.op=='' }">
		
			</c:if>
			
			
			<p class="errores">${producto.errores }</p>
			
			<input type="hidden" name="op" value="completado">

		
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
	<%@ include file="includes/productopie.jsp" %>




</body>
</html>