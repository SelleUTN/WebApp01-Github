<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"  %>
<%@ page import="negocio.datos.CatalogoCategorias"  %>
<%@ page import="entidad.Categoria"  %>
<%@ page import="entidad.Vehiculo"  %>
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


<title>Vehiculos</title>

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
	   ArrayList <Vehiculo> vehiculos = (ArrayList<Vehiculo>) request.getAttribute("vehiculos");
	   int size = categorias.size(); 
	   Date ahora = new Date();
       SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
       String fechaActual = formateador.format(ahora);%>
        
<h1>Listado de Vehiculos</h1>

<% if (categorias.size()==0) { %>

<h2>No hay categorias registradas</h2>

<% } else  { %>

<%for(int i=0 ; i<categorias.size() ; i++) {%>
	
	<% int id = categorias.get(i).getIdCategoria();%>
		
	<h2> Categoria <%=id%>: <%=categorias.get(i).getDescripcionCategoria()%> </h2>
		
		<table class="table table-bordered" id=tabla>
		
			<thead style="table-layout:fixed">
		
		            <tr>
		
						<th WIDTH="250">Patente</th>
			
					    <th WIDTH="250">Modelo</th>
					    
					    <th>Modificar</th>
			
					    <th>Eliminar</th>
					    
					    <th>Estado</th>
					    
					 </tr>
		
			</thead>
		
			<tbody>
			
				<ul>
				
					<%for(int j=0 ; j<vehiculos.size() ; j++) {%>
						
							<% if (vehiculos.get(j).getIdCategoria()==id ) {%>
									
									<% String nroPatente = vehiculos.get(j).getNroPatente();  %>
									
									<tr>
									
										<td> <%=nroPatente%> </td>
										
										<td> <%=vehiculos.get(j).getModeloVehiculo()%> </td>
										
										<td>
											<form action="ModificarVehiculo" method="post"> 
			   								<input type="hidden" name="nroPatente" value=<%=nroPatente%>>
			   								<input type="submit" class="btn btn-warning" value="Modificar" />
			   								</form>
										</td>
										
										<td>
											<form action="EliminarVehiculo" method="post"> 
			   								<input type="hidden" name="nroPatente" value=<%=nroPatente%>>
			   								<input type="submit" class="btn btn-danger" value="Eliminar" />
			   								</form>
										</td>
										
										<td>
											<form action="EstadoVehiculo" method="post"> 
			   								<input type="hidden" name="nroPatente" value=<%=nroPatente%>>
			   								<input type="submit" class="btn btn-info" value="Estado" />
			   								</form>
										</td>
										
									</tr>
						
							<% } %>
						
					<% } %>
				
				</ul>
				
			</tbody>
		
		</table>

	<% } %>
	
<% } %>	
	
	<form>
		<input type="button" class="btn btn-info" value="Volver Atrás" onclick="history.back()" />
	</form>

</div>

</body>
</html>