<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoCategorias"  %>
<%@ page import="entidad.Categoria"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="bootstrap/css/bootstrap-theme.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-theme.css.map" rel="stylesheet" />
<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="bootstrap/css/bootstrap.css.map" rel="stylesheet" />
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<link href="bootstrap/js/bootstrap.js" rel="stylesheet" />
<link href="bootstrap/js/bootstrap.min.js" rel="stylesheet" />
<link href="bootstrap/js/bootstrap.npm.js" rel="stylesheet" />

<title>Registrar vehiculo</title>

<style type="text/css">
    h1{
        margin: 30px 0;
        padding: 0 200px 15px 0;
        border-bottom: 1px solid #E5E5E5;
    }
	.bs-example{
    	margin: 20px;
    }
</style>

</head>

<body>

<div class="bs-example" >

	<% ArrayList <Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias"); %>

<% if (categorias.size()!=0) {%>

<h1> Registrar Vehiculo </h1>

	<form action="ValidarVehiculo" method="post">
		  Numero de Patente:<br>
		  <input type="text" name="nroPatente" pattern="[a-zA-Z]{3}[0-9]{3}" title="Formato incorrecto" placeholder="Ejemplo: FAQ349" maxlength="6" autocomplete="off" required>
		  <br>
		  Modelo:<br>
		  <input type="text" name="modeloVehiculo" pattern="[\wñÑáéíóú\s]{1,25}" title="Ingresar caracteres válidos" maxlength="25" autocomplete="off" required>
		  <br>
		  Categoria:<br>
  		  <select name="idCategoria" required>    
		       	   <%for(int i=0 ; i<categorias.size() ; i++) {%>
			       <option value=<%=categorias.get(i).getIdCategoria()%> ><%=categorias.get(i).getDescripcionCategoria()%></option>
			       <% } %>
			</select><br><br>
		  <input type="submit" class="btn btn-success" value="Registrar" >
		  <input type="reset" class="btn btn-default" value="Reset" >
	</form>

<% } else {%> <h2>No hay categorias cargadas en el sistema</h2> <% }%>    
    
    <br>
    <form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>

</html>