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

<form action="/admin/productoform" method="post">

<h1>Zona de Compra</h1>



		

		<p>Productos que quieres comprar y la cantidad</p>
			
			<input name="productos" type="checkbox" value="manzana">Manzanas	
			<input id="cantidad" name="cantidad-manzana" type="text" value="${producto.cantidad}"><br>
			
			<input name="productos" type="checkbox"value="tomate">Tomates
			<input id="cantidad" name="cantidad-tomate" type="text" value="${producto.cantidad}"><br>
			
			<input name="productos" type="checkbox"value="patata">Patatas
			<input id="cantidad" name="cantidad-patata" type="text" value="${producto.cantidad}"><br>
			
			<input name="productos" type="checkbox"value="mandarina">Mandarinas
			<input id="cantidad" name="cantidad-mandarina" type="text" value="${producto.cantidad}"><br>
			
			<input name="productos" type="checkbox"value="naranja">Naranjas
			<input id="cantidad" name="cantidad-naranja" type="text" value="${producto.cantidad}"><br>
			
			<input name="productos" type="checkbox"value="pera">Peras
			<input id="cantidad" name="cantidad-pera" type="text" value="${producto.cantidad}"><br>
			
			<input name="productos" type="checkbox"value="vino">Botellas de Vino
			<input id="cantidad" name="cantidad-vino" type="text" value="${producto.cantidad}">
			
	

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