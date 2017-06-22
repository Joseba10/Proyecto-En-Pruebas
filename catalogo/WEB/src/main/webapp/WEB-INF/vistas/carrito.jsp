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
			
			<input name="productos" type="checkbox" value="manzana">Manzanas	
			<input id="cantidad1" name="cantidad-manzana" type="text" value="${producto.cantidad}" title="Cantidad"><br>
			
			<input name="productos" type="checkbox"value="tomate">Tomates
			<input id="cantidad2" name="cantidad-tomate" type="text" value="${producto.cantidad}" title="Cantidad"><br>
			
			<input name="productos" type="checkbox"value="patata">Patatas
			<input id="cantidad3" name="cantidad-patata" type="text" value="${producto.cantidad}" title="Cantidad"><br>
			
			<input name="productos" type="checkbox"value="mandarina">Mandarinas
			<input id="cantidad4" name="cantidad-mandarina" type="text" value="${producto.cantidad}" title="Cantidad"><br>
			
			<input name="productos" type="checkbox"value="naranja">Naranjas
			<input id="cantidad5" name="cantidad-naranja" type="text" value="${producto.cantidad}" title="Cantidad"><br>
			
			<input name="productos" type="checkbox"value="pera">Peras
			<input id="cantidad6" name="cantidad-pera" type="text" value="${producto.cantidad}" title="Cantidad"><br>
			
			<input name="productos" type="checkbox"value="vino">Botellas de Vino
			<input id="cantidad7" name="cantidad-vino" type="text" value="${producto.cantidad}" title="Cantidad">
			
	

			<input type="submit" value="Enviar">
			
			<c:if test="${param.op==null or param.op=='' }">
		
			</c:if>
			
			
			<p class="errores">${producto.errores }</p>
			
			<input type="hidden" name="opform" value="${param.op }">

		
	</form>
	
	<c:if test="${param.op == 'borrar'}">
		<script>
			document.forms[0].onsubmit = confirmarBorrado;
		</script>
	</c:if>
	<%@ include file="includes/productopie.jsp" %>




</body>
</html>