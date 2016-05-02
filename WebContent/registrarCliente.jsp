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

<title>Registrarse</title>

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

<h1> Registrarse </h1>

	<form action="RegistrarCliente" method="post">
		  Usuario:<br>
		  <input type="text" name="usuarioCliente" pattern="[A-Za-zñÑ0-9]{6,20}" title="Mínimo 6 numeros/letras (sin acentos)" placeholder="Solo letras y/o numeros" maxlength="20" autocomplete="off" required>
		  <br>
		  Contraseña:<br>
		  <input type="password" name="contCliente" pattern="[A-Za-zñÑ0-9]{6,20}" title="Mínimo 6 numeros/letras (sin acentos)" placeholder="Solo letras y/o numeros" maxlength="20" autocomplete="off" required>
		  <br>
		  Repetir contraseña:<br>
		  <input type="password" name="confCont" pattern="[A-Za-zñÑ0-9]{6,20}" title="Mínimo 6 numeros/letras (sin acentos)" placeholder="Solo letras y/o numeros" maxlength="20" autocomplete="off" required>
		  <br>
		  DNI:<br>
		  <input type="text" name="dniCliente" pattern="[0-9]{8}" title="Solo numeros, 8 en total" maxlength="8" autocomplete="off" required>
		  <br>
		  Nombre:<br>
		  <input type="text" name="nombreCliente" pattern="[A-Za-z]*" title="Solo letras (sin acentos)" placeholder="Solo letras" maxlength="25" autocomplete="off" required>
		  <br>
		  Teléfono:<br>
		  <input type="text" name="telefonoCliente" pattern="[0-9]{7,12}" title="Solo numeros" placeholder="Fijo o Celular" maxlength="12" autocomplete="off" required>
		  <br><br>
		  <input type="submit" class="btn btn-success" value="Registrar" >
		  <input type="reset" class="btn btn-default" value="Reset" >
	</form>
    <br>   
	<form>
	<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>

</html>