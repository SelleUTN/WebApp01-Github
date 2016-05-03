<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>Cambiar Contraseña</title>

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

<h1> Cambiar Contraseña </h1>

	 <form action="AplicarNuevaCont" method="post">
		  <input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
		  Contraseña Actual:<br>
		  <input type="password" name="contActual" size="22" pattern="[\wñÑáéíóú]{6,20}" title="Mínimo 6 numeros o letras" placeholder="Mínimo 6 numeros o letras" maxlength="20" autocomplete="off" required>
		  <br><br>
		  Contraseña Nueva:<br>
		  <input type="password" name="contNueva" size="22" pattern="[\wñÑáéíóú]{6,20}" title="Mínimo 6 numeros o letras" placeholder="Mínimo 6 numeros o letras" maxlength="20" autocomplete="off"  required>
		  <br><br>
		  Confirmar Nueva Contraseña:<br>
		  <input type="password" name="contConfirmar" size="22" pattern="[\wñÑáéíóú]{6,20}" title="Mínimo 6 numeros o letras" placeholder="Mínimo 6 numeros o letras" maxlength="20" autocomplete="off"  required>
		  <br><br>
		  <input type="submit" class="btn btn-success" value="Cambiar" >
		  <input type="reset" class="btn btn-default" value="Reset" >
	</form>
	<br>
	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>

</html>