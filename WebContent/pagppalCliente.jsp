<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario"%>
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
			
			<form action="NuevoAlquiler" method="post"> 
			<input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
			<input type="hidden" name="contraseña" value=<%=request.getAttribute("contraseña")%>>
			<input type="submit" class="btn btn-success" value="Nuevo Alquiler" />
			</form>
			<br>
			<form action="ListarAlqCliente" method="post"> 
			<input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
			<input type="submit" class="btn btn-warning" value="Listar Alquileres" />
			</form>
			
			<hr/>
			<form action="ListadoCategorias" method="post"> 
			<input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
			<input type="submit" class="btn btn-warning" value="Listar Categorias" />
			</form>
			
			<hr/>
			<form action="CambiarCont" method="post"> 
			<input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
			<input type="submit" class="btn btn-info" value="Cambiar Contraseña" />
			</form>
			<br>
		
		<hr/>	
			
		<input type="button" class="btn btn-danger" value="Salir" onclick="location.href='ingresarUsuario.jsp'" />
		   	 
		</div>   	
	
	</body>

</html>