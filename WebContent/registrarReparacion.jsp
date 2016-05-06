<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.Reparacion"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
		
		<title>Registrar reparación</title>
		
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
		
		<script> 
			$(document).ready(function() { 
			$("#datepicker1").datepicker();
			}); 
		</script>
		
		<script> 
			$(document).ready(function() { 
			$("#datepicker2").datepicker(); 
			}); 
		</script>
		
	</head>
	
	<body>
		
		<div class="bs-example" >
			
			<h1>Registrar Reparacion</h1>
			
			<form action="ValidarReparacion" method="post">
				Patente:<br>
				<input type="text" name="nroPatente" pattern="[a-zA-Z]{3}[0-9]{3}" title="Formato inválido - ejemplo: FAQ349" placeholder="Ejemplo: FAQ349" maxlength="6" autocomplete="off" required> <br>
				<br>
				Fecha Desde:<br>
				<input type="text" size="22" id="datepicker1" name="fechaDesdeReparacion" onpaste="return false" oncut="return false" oncopy="return false" placeholder="mes/dia/año ej: 05/09/2016" maxlength="10" autocomplete="off" required/> <br>
				<br>
				Fecha Hasta:<br>
				<input type="text" size="22" id="datepicker2" name="fechaHastaReparacion" onpaste="return false" oncut="return false" oncopy="return false" placeholder="mes/dia/año ej: 05/16/2016" maxlength="10" autocomplete="off" required/> <br>
				<br>
				Tipo de Reparacion:<br>
				<select name="opcion">    
				     <option value="2" selected="selected">Mecanico</option>
				     <option value="1">Taller</option>
				</select>
				<input type="text" name="tipoReparacion" size="28" pattern="[a-zA-Z0-9\s]{1,25}" title="Solo letras y/o numeros" placeholder="Nombre Mecanico o direccion Taller" maxlength="25" autocomplete="off" required> <br>
				<br>
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