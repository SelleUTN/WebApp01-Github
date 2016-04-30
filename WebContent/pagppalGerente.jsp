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

<title>Pagina Principal</title>

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
		
		    <h1>Sistema</h1>
			
			<form action="RegistrarVehiculo" method="post"> 
		   	<input type="submit" class="btn btn-success" value="Registrar Vehiculo" />
		   	</form>
		   	<br>
			
			<form action="RegistrarReparacion" method="post"> 
		   	<input type="submit" class="btn btn-success" value="Registrar Reparacion" />
		   	</form>
		   	<br>
		   	
		   	<form action="ListadoVehiculos" method="post"> 
		   	<input type="submit" class="btn btn-warning" value="Listar Vehiculos" />
		   	</form>
		   	<br>
		   	
		   	<hr/>
		   	
		   	<form action="ListarAlqGerente" method="post"> 
		   	<input type="submit" class="btn btn-warning" value="Listar Alquileres" />
		   	</form>
		   	<br>
		   	
		   	<form action="ListadoReparaciones" method="post"> 
		   	<input type="submit" class="btn btn-warning" value="Listar Reparaciones" />
		   	</form>
		   	<br>
		
			<hr/>
		   	
		<input type="button" class="btn btn-danger" value="Salir" onclick="location.href='ingresarUsuario.jsp'" />
		   	
		</div>   	
	
	</body>

</html>