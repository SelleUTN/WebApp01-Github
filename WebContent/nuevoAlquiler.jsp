<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.Categoria"%>
<%@ page import="negocio.datos.CatalogoCategorias"  %>
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

<title>Alquiler</title>

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

	<% ArrayList <Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias"); %>

<h1> Alquiler </h1>

	<form action="ValidarNuevoAlq" method="post">
	  <input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
	  Fecha Desde:<br>
	  <input type="text" id="datepicker1" name="fechaDesdeAlquiler" onpaste="return false" oncut="return false" oncopy="return false" autocomplete="off" /> <br>
	  <br>
	  Fecha Hasta:<br>
	  <input type="text" id="datepicker2" name="fechaHastaAlquiler" onpaste="return false" oncut="return false" oncopy="return false" autocomplete="off" /> <br>
	  <br>
	  Categoria:<br>
  		  <select name="idCategoria" required>    
		       	   <%for(int i=0 ; i<categorias.size() ; i++) {%>
			       <option value=<%=categorias.get(i).getIdCategoria()%> ><%=categorias.get(i).getDescripcionCategoria()%></option>
			       <% } %>
			</select><br><br>
	  <input type="submit" class="btn btn-success" value="Verificar" >
	  <input type="reset" class="btn btn-default" value="Reset" >
	</form>
	<br>
	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>
</div>

</body>

</html>