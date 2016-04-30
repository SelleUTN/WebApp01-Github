<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoVehiculos"  %>
<%@ page import="entidad.Taller"  %>
<%@ page import="entidad.Mecanico"  %>
<%@ page import="entidad.Alquiler"  %>
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
	
	<% ArrayList <Taller> talleres = (ArrayList<Taller>) request.getAttribute("talleres");
	   ArrayList <Mecanico> mecanicos = (ArrayList<Mecanico>) request.getAttribute("mecanicos");
	   ArrayList <Alquiler> alquileres = (ArrayList<Alquiler>) request.getAttribute("alquileres");
	   int sizeT = talleres.size(), sizeM = mecanicos.size(), sizeA = alquileres.size(); 
	   Date ahora = new Date();
       SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
       String fechaActual = formateador.format(ahora);%>
 
<% if (sizeT==0 && sizeM==0 && sizeA==0) { %>
	
	<h2>El vehiculo no tiene alquileres ni reparaciones registradas en el sistema</h2>

<%} else { %> 
 
<h1>Estados</h1>
 
<table class="table table-bordered" id=tabla>

	<thead>

             <tr>

				<th>Fecha Desde</th>
				
				<th>Fecha Hasta</th>
				
				<th>Estado del Vehiculo</th>
				
				<th>Especificación de Reparacion</th>
	
			 </tr>

	</thead>

	<tbody>
	
		<ul>
		
			<%for(int i=0 ; i<sizeM ; i++) {%>
				
				<tr>
				
					<td> <%=mecanicos.get(i).getFechaDesdeReparacion()%> </td>
					
					<td> <%=mecanicos.get(i).getFechaHastaReparacion()%> </td>
					
					<td> Reparación a cargo de Mecánico </td>
					
					<td> <%=mecanicos.get(i).getNombreMecanico()%> </td>
				
				</tr>
				 	
			<% } %>
			
			<%for(int i=0 ; i<sizeT ; i++) {%>
				
				<tr>
				
					<td> <%=talleres.get(i).getFechaDesdeReparacion()%> </td>
					
					<td> <%=talleres.get(i).getFechaHastaReparacion()%> </td>
					
					<td> Reparación en Taller </td>
					
					<td> <%=talleres.get(i).getDireccion()%> </td>
				
				</tr>
				 	
			<% } %>
			
			<%for(int i=0 ; i<sizeA ; i++) {%>
				
				<tr>
				
					<td> <%=alquileres.get(i).getFechaDesdeAlquiler()%> </td>
					
					<td> <%=alquileres.get(i).getFechaHastaAlquiler()%> </td>
					
					<td> Alquiler <%=alquileres.get(i).getEstadoAlquiler()%> </td>
					
					<td> ------- </td>
				
				</tr>
				 	
			<% } %>
		
		</ul>
		
	</tbody>

</table>

<% } %>
        
<br>
	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>
</html>