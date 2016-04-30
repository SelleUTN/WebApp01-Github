<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoVehiculos"%>
<%@ page import="entidad.Alquiler"%>
<%@ page import="entidad.Vehiculo"  %>

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

<title>Listado de Vehiculos</title>

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
	
	<% ArrayList <Vehiculo> vehiculos = (ArrayList<Vehiculo>) request.getAttribute("vehiculosDisp"); %>
	<% Alquiler a = (Alquiler) request.getAttribute("Alquiler"); %> 
	<% int cantDisp = vehiculos.size(); %>

<% if (cantDisp == 0) { %>
	
	<h2>No hay vehiculos disponibles de categoria <%=request.getAttribute("idCategoria")%> entre
	para las fechas ingresadas</h2>

<%} else { %>

<h1>Detalle de Alquiler</h1>

<table class="table table-bordered">

	<thead>

            <tr>

				<th>Usuario</th>
			    
			    <th>Desde</th>
				
				<th>Hasta</th>
				
				<th bgcolor= "#FE2E2E">Importe</th>

            </tr>

	</thead>
	
	<tbody>
	
		<ul>
			
			<tr>
			
			<td> <%=a.getUsuarioCliente()%> </td>
			<td> <%=a.getFechaDesdeAlquiler()%> </td>
			<td> <%=a.getFechaHastaAlquiler()%> </td>
			<td bgcolor= "#FE2E2E"> <%=a.getImporte()%> </td>
			
			</tr>
		
		</ul>
		
	</tbody>

</table>

<h1>Listado de Vehiculos disponibles de Categoria <%=request.getAttribute("idCategoria")%></h1>

<table class="table table-bordered">

	<thead>

            <tr>

				<th>Número de Patente</th>
				
				<th>Modelo</th>
			    
			    <th>Opción</th>

            </tr>

	</thead>
	
	<tbody>
	
		<ul>
		
		<%for(int i=0 ; i<cantDisp ; i++) {%>
			
			<tr>
			
			<td> <%=vehiculos.get(i).getNroPatente()%> </td>
			
			<td> <%=vehiculos.get(i).getModeloVehiculo()%> </td>
			
			<td> 
			
			<form action="GenerarAlquiler" method="post">
		  	<% a.setNroPatente(vehiculos.get(i).getNroPatente()); %>
		  	<input type="hidden" name="usuario" value=<%=a.getUsuarioCliente()%>>
		  	<input type="hidden" name="nroPatente" value=<%=a.getNroPatente()%>>
		  	<input type="hidden" name="importe" value=<%=a.getImporte()%>>
		  	<input type="hidden" name="fechaDesde" value=<%=a.getFechaDesdeAlquiler()%>>
		  	<input type="hidden" name="fechaHasta" value=<%=a.getFechaHastaAlquiler()%>>
		  	<input type="submit" class="btn btn-success" value="Alquilar" >
		    </form>
			
			</td>
			
			</tr>
		
		
		<% } %>
		
		</ul>
		
	</tbody>

</table>

<%} %>

	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>
	<br>

</div>

</body>
</html>