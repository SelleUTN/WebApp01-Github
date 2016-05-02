<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.Reparacion"  %>

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


<title>Modificar vehiculo</title>

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

<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

</head>

<body>

<div class="bs-example" >

<% String nroPatente = (String)request.getAttribute("nroPatente"); %>

<h1>Modificar el vehiculo <%= nroPatente %></h1>

	<form action="ValidarModVehiculo" method="post">
		<input type="hidden" name="nroPatente" value=<%=nroPatente%>> <br>
		Cambiar Modelo:<br>
		<input type="text" name="modelo" pattern="[A-Za-zñÑ0-9\s]*" title="Ingresar caracteres válidos" maxlength="25" autocomplete="off" required> <br>
		<br>
		<input type="submit" class="btn btn-success" value="Modificar" >
	</form>

	<br><br>

	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>
</html>