<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<title>Error</title>

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
		
			<h1> Error </h1>
		   	<h2> <%= request.getAttribute("respuesta") %> </h2>
		   	<br>
		   	<form>
			<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
			</form>
		
		</div>   	
	
	</body>

</html>