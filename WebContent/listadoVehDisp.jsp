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

<title>Vehiculos disponibles</title>

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
	<% String usuario = (String)request.getAttribute("usuario"); %>
	<% java.sql.Date fechaDesde = (java.sql.Date)request.getAttribute("fechaDesde"); %>
	<% java.sql.Date fechaHasta = (java.sql.Date)request.getAttribute("fechaHasta"); %>
	<% String descuento = String.valueOf(request.getAttribute("descuento")); %>
	<% String importe = String.valueOf(request.getAttribute("importe")); %>
	
<h1>Detalle de Alquiler</h1>

<table class="table table-bordered">

	<thead>

            <tr>

				<th>Usuario</th>
			    
			    <th>Desde</th>
				
				<th>Hasta</th>
				
				<th bgcolor= "#FE2E2E" width="250">Importe</th>

            </tr>

	</thead>
	
	<tbody>
	
		<ul>
			
			<tr>
			
			<td> <%=usuario%> </td>
			<td> <%=fechaDesde%> </td>
			<td> <%=fechaHasta%> </td>
			
			<% if ( descuento.equals(0) ) { %>
			
			<td bgcolor= "#FE2E2E"> <%=importe%> sin descuento</td>
			
			<% } else {%>
			
			<td bgcolor= "#FE2E2E"> <%=importe%> el <%= descuento %>% aplicado</td>
			
			<% } %>
			
			</tr>
		
		</ul>
		
	</tbody>

</table>

<h1>Listado de Vehiculos disponibles</h1>

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
		
		<%for(int i=0 ; i<vehiculos.size() ; i++) {%>
			
			<% String nroPatente =  vehiculos.get(i).getNroPatente();%>
			
			<tr>
			
			<td> <%=nroPatente%> </td>
			
			<td> <%=vehiculos.get(i).getModeloVehiculo()%> </td>
			
			<td> 
			
			<form action="GenerarAlquiler" method="post">
		  	<input type="hidden" name="usuario" value=<%=usuario%>>
		  	<input type="hidden" name="nroPatente" value=<%=nroPatente%>>
		  	<input type="hidden" name="importe" value=<%=importe%>>
		  	<input type="hidden" name="fechaDesde" value=<%=fechaDesde%>>
		  	<input type="hidden" name="fechaHasta" value=<%=fechaHasta%>>
		  	<input type="submit" class="btn btn-success" value="Alquilar" >
		    </form>
			
			</td>
			
			</tr>
		
		
		<% } %>
		
		</ul>
		
	</tbody>

</table>

	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>
	<br>

</div>

</body>
</html>