<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<title>Ingreso al Sistema</title>

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

<h1> Ingresar al Sistema </h1>

	<form action="ValidarUsuario" method="post">
		  Usuario:<br>
		  <input type="text" name="usuario" pattern="[\w]{6,20}" title="Mínimo 6 numeros/letras (sin acentos)" maxlength="20" autocomplete="off" required>
		  <br>
		  Contraseña:<br>
		  <input type="password" name="cont" pattern="[a-zA-Z0-9]{6,20}" title="Mínimo 6 numeros/letras (sin acentos)" maxlength="20" required>
		  <br><br>
		  <input type="submit" class="btn btn-success" value="Ingresar" >
		  <input type="reset" class="btn btn-default" value="Reset" >
	</form>

<br>
<a href="registrarCliente.jsp">Registrarse</a>

</div>

</body>

</html>