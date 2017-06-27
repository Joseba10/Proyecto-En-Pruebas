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

		<p>Productos que quieres comprar y la cantidad</p>
			
			<input name="productos" type="hidden" value="manzanas">Manzanas	
			<input id="cantidad1" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="hidden"value="tomates">Tomates
			<input id="cantidad2" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="hidden"value="patatas">Patatas
			<input id="cantidad3" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="hidden"value="mandarinas">Mandarinas
			<input id="cantidad4" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="hidden"value="naranjas">Naranjas
			<input id="cantidad5" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="hidden"value="peras">Peras
			<input id="cantidad6" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="hidden"value="vinos">Botellas de Vino
			<input id="cantidad7" name="cantidad" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+">
			
	

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