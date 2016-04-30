<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoCategorias"  %>
<%@ page import="entidad.Categoria"  %>
<%@ page import="java.text.SimpleDateFormat"  %>
<%@ page import="java.util.Date"  %>

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


<title>Listado de Alquileres</title>

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
	
	<% ArrayList <Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias"); 
	   int size = categorias.size(); 
	   Date ahora = new Date();
       SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
       String fechaActual = formateador.format(ahora);%>
        
<h1>Listado de Categorias</h1>

<% if (size==0) { %>

<h2>No hay categorias registradas</h2>

<% } else {%>

<table class="table table-bordered" id=tabla>

	<thead>

            <tr>

				<th>Descripcion</th>
	
			    <th>Precio x dia</th>
			    
			 </tr>

	</thead>

	<tbody>
	
		<ul>
		
		<%for(int i=0 ; i<categorias.size() ; i++) {%>
			
			
			<tr>
			
				<td> <%=categorias.get(i).getDescripcionCategoria()%> </td>
				
				<td> <%=categorias.get(i).getPrecioCategoria()%> </td>
				
			</tr>
		
		<% } %>
		
		</ul>
		
	</tbody>

<% } %>

</table>

	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>
</html>