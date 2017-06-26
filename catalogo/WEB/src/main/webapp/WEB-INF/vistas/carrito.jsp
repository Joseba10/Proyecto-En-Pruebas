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



		

		<p>Productos que quieres comprar y la cantidad</p>
			
			<input name="productos" type="checkbox" value="manzanas">Manzanas	
			<input id="cantidad1" name="cantidad-manzanas" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="checkbox"value="tomates">Tomates
			<input id="cantidad2" name="cantidad-tomates" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="checkbox"value="patatas">Patatas
			<input id="cantidad3" name="cantidad-patatas" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="checkbox"value="mandarinas">Mandarinas
			<input id="cantidad4" name="cantidad-mandarinas" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="checkbox"value="naranjas">Naranjas
			<input id="cantidad5" name="cantidad-naranjas" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="checkbox"value="peras">Peras
			<input id="cantidad6" name="cantidad-peras" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+"><br>
			
			<input name="productos" type="checkbox"value="vinos">Botellas de Vino
			<input id="cantidad7" name="cantidad-vinos" type="text" value="${producto.cantidad}" title="Cantidad" pattern="[0-9]+">
			
	

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