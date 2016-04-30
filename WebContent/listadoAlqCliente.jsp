<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoVehiculos"  %>
<%@ page import="entidad.Alquiler"  %>

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
	
	<% ArrayList <Alquiler> alquileres = (ArrayList<Alquiler>) request.getAttribute("alquileres"); 
	   int size = alquileres.size(); %>
	    
<h1>Listado de Alquileres</h1>

<% if (size==0) { %>

<h2>No hay alquileres registrados</h2>

<% } else {%>

<table class="table table-bordered">

	<thead>

            <tr>

				<th>Numero Alquiler</th>
	
			    <th>Patente del Vehiculo</th>
			    
			    <th>Importe</th>
			    
			    <th>Desde</th>
			    
			    <th>Hasta</th>
			    
			    <th>Opcion</th>

            </tr>

	</thead>
	
	<tbody>
	
		<ul>
		
		<%for(int i=0 ; i<alquileres.size() ; i++) {%>
			
			<tr>
			
			<td> <%=alquileres.get(i).getNroAlquiler()%> </td>
			
			<td> <%=alquileres.get(i).getNroPatente()%> </td>
			
			<td> <%=alquileres.get(i).getImporte()%> </td>
			
			<td> <%=alquileres.get(i).getFechaDesdeAlquiler()%> </td>
			
			<td> <%=alquileres.get(i).getFechaHastaAlquiler()%> </td>
			
			<td> 
				
				<%String respuesta = alquileres.get(i).getEstadoAlquiler(); %>
				
				<%if ( respuesta.equals("agendado") ) { %>
					<form action="CancelarAlquiler" method="post">
					<input type="hidden" name="nroAlquiler" value=<%=alquileres.get(i).getNroAlquiler()%>>
					<input type="hidden" name="usuario" value=<%=request.getAttribute("usuario")%>>
					<input type="submit" class="btn btn-danger" value="Cancelar">
					</form>
				<%} else {%>
					 <%=alquileres.get(i).getEstadoAlquiler()%>
				<%}%>
			
			</td>
			
			</tr>
		
		
		<% } %>
		
		</ul>
		
	</tbody>

</table>

<% } %>

	<form>
	<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>
</html>