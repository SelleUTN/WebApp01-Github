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
		  <input type="text" name="usuarioCliente" size="22" pattern="[\w]{6,20}" title="Mínimo 6 numeros o letras" placeholder="Mínimo 6 numeros o letras" maxlength="20" autocomplete="off" required>
		  <br>
		  Contraseña:<br>
		  <input type="password" name="contCliente" size="22" pattern="[a-zA-Z0-9]{6,20}" title="Mínimo 6 numeros o letras" placeholder="Mínimo 6 numeros o letras" maxlength="20" autocomplete="off" required>
		  <br>
		  Repetir contraseña:<br>
		  <input type="password" name="confCont" size="22" pattern="[a-zA-Z0-9]{6,20}" title="Mínimo 6 numeros o letras" placeholder="Mínimo 6 numeros o letras" maxlength="20" autocomplete="off" required>
		  <br>
		  DNI:<br>
		  <input type="text" name="dniCliente" size="22" pattern="\d{8}" title="Solo numeros, 8 en total" placeholder="Solo numeros, 8 en total" maxlength="8" autocomplete="off" required>
		  <br>
		  Nombre:<br>
		  <input type="text" name="nombreCliente" size="22" pattern="[a-zA-Z\s]{1,25}" title="Solo letras" placeholder="Solo letras" maxlength="25" autocomplete="off" required>
		  <br>
		  Teléfono:<br>
		  <input type="text" name="telefonoCliente" size="22" pattern="\d{7,9}" title="Solo numeros, entre 7 y 9 numeros" placeholder="Solo numeros, Fijo o Celular" maxlength="9" autocomplete="off" required>
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