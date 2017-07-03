<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Factura</title>


<style>



h1{
text-align: center;
margin-top: 30px;
}
ul{

margin-left: 380px;
margin-top: 0px;

}

li{
background-color: #EFEFEF;
width:100px;
border:solid #EFEFEF;
-webkit-border-radius: 19px;
-moz-border-radius: 19px;
border-radius: 19px;
color:blue;
text-align:center;
display: inline-block;
margin-left: 200px;

}

li a:hover {
	color:red;
}

li a{
text-decoration: none;
}

table{

margin-left: 370px;

border: solid;
background-color: green;
font-family: sans-serif;


border-color: green;
}
table tr{
background-color: white;
}
table tr td a{

color:blue;
font-weight: bold;


}
table tr td a:hover{

color:red;
background-color: white;

}


table tr th{

height:25px;
background-color: red;

}

table tr td{

text-align: center;}

a{



text-decoration: none;}

#carrito{
margin-top:-210px;
margin-left:100px;
border: solid 2px;
width: 200px;
text-align: center;
font-family: sans-serif;
text-transform: uppercase;
border: solid 2px;
-webkit-border-radius: 70px;
-moz-border-radius: 70px;
border-radius: 70px;
padding-top: 10px;
height: 40px;

}

#confirmar{

margin-left: 80px;
margin-top: 80px;
border: solid 2px;
width: 230px;
text-align:center;
-webkit-border-radius: 70px;
-moz-border-radius: 70px;
border-radius: 70px;
padding-top: 10px;
height: 30px;
font-family: sans-serif;
text-transform: uppercase;
}


#confirmar a{

font-size: 16px;



}

h3{

margin-left: 650px;
text-transform: uppercase;

}

#precio{

margin-top: 50px;
margin-left: 150px;
}

</style>
</head>
<body>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>Factura-Resumen de la compra</h1>

<h3>${usuario.username}</h3>
<table border=1 width="700" height=150px;>

<thead>

<tr>

	

	<th>Producto</th>
	<th>Precio</th>
	<th>Descripcion</th>
	<th>Cantidad</th>
	<th>Imagen</th>
	
	</tr>
	
	</thead>
	
	<tbody>
	
	
	
	<c:forEach items="${sessionScope.productosCarrito}" var="producto">
	<tr>
	
		
		<td>${producto.value.nombre}</td>
		<td>${producto.value.precio} €</td>
		<td>${producto.value.descripcion}</td>
		<td>${producto.value.cantidad}</td>
		<td><img src="${producto.value.imagen}.jpg" width="80px"></td>

	</tr>
	</c:forEach>
			

	</tbody>



</table>

	<div id="carrito">
	<a href="Zonadecompra?op=primeravez">Volver hacer la Compra</a>
	
	</div>
	
	
	
	<div id="precio">
	
	<p>Precio Total:</p>
	
	
	</div>
	
	<div id="confirmar">
	<a href="Zonadecompra?op=confirmado">Confirmar Compra</a>
	
	</div>


<%@  include file="includes/productopie.jsp" %>



</body>
</html>