<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoVehiculos"  %>
<%@ page import="entidad.Taller"  %>
<%@ page import="entidad.Mecanico"  %>
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


<title>Reparaciones</title>

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
	   ArrayList <Mecanico> nombresMecanicos = (ArrayList<Mecanico>) request.getAttribute("nombresMecanicos");
	   int sizeT = talleres.size(), sizeM = mecanicos.size(), t = 0, m = 0; 
	   Date ahora = new Date();
       SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
       String fechaActual = formateador.format(ahora);%>
 
<h1>Mecánicos</h1>
 
<table class="table table-bordered" id=tabla>

	<thead>

             <tr>

				<th>Mecánicos de la Empresa</th>
	
			 </tr>

	</thead>

	<tbody>
	
		<ul>
		
		<%for(int i=0 ; i<nombresMecanicos.size() ; i++) {%>
			
			<tr>
			
				<td> <%=nombresMecanicos.get(i).getNombreMecanico()%> </td>
			 	
			</tr>
		
		<% } %>
		
		</ul>
		
	</tbody>

</table>
        
<h1>Listado de Reparaciones</h1>

<% if (sizeT==0 && sizeM==0) { %>

<h2>No hay reparaciones registradas</h2>

<% } else {%>

<table class="table table-bordered">

	<thead>

            <tr>

				<th>Patente</th>
	
			    <th>Fecha Desde</th>
			    
			    <th>Fecha Hasta</th>
			    
			    <th>Direccion de Taller Mecanico</th>
			    
			    <th>Mecanico a cargo</th>
			   
			</tr>

	</thead>

	<tbody>
	
		<ul>
		
	<% while (t<sizeT && m<sizeM) {%>
				
		<% if (talleres.get(t).getFechaDesdeReparacion().after(mecanicos.get(m).getFechaDesdeReparacion())) {%>
			<tr>
				<td> <%= talleres.get(t).getNroPatente() %></td>
				<td> <%= talleres.get(t).getFechaDesdeReparacion() %></td>
				<td> <%= talleres.get(t).getFechaHastaReparacion() %></td>
				<td> <%= talleres.get(t).getDireccion() %></td>
				<td> ------- </td>
			</tr>
		<% t++;} else { %>
			<tr>	
				<td> <%= mecanicos.get(m).getNroPatente() %></td>
				<td> <%= mecanicos.get(m).getFechaDesdeReparacion() %></td>
				<td> <%= mecanicos.get(m).getFechaHastaReparacion() %></td>
				<td> ------- </td>
				<td> <%= mecanicos.get(m).getNombreMecanico() %></td>
			</tr>	
		<% m++;} %>
	
	
	<% } %>
	
	
			<% if (m==sizeM) {%>
			
				<% for (int i=t; i<sizeT; i++) {%>
					
					<tr>
						<td> <%= talleres.get(t).getNroPatente() %></td>
						<td> <%= talleres.get(t).getFechaDesdeReparacion() %></td>
						<td> <%= talleres.get(t).getFechaHastaReparacion() %></td>
						<td> <%= talleres.get(t).getDireccion() %></td>
						<td> ------- </td>
					</tr>
				
				<% } %>
			
			<% } else {%>
			
				<% for (int i=m; i<sizeM; i++) {%>
					
					<tr>	
						<td> <%= mecanicos.get(m).getNroPatente() %></td>
						<td> <%= mecanicos.get(m).getFechaDesdeReparacion() %></td>
						<td> <%= mecanicos.get(m).getFechaHastaReparacion() %></td>
						<td> ------- </td>
						<td> <%= mecanicos.get(m).getNombreMecanico() %></td>
					</tr>
				
				<% } %>
			
			
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