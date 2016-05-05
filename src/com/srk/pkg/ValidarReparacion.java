package com.srk.pkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidad.Mecanico;
import entidad.Taller;
import negocio.datos.CatalogoReparaciones;
import negocio.datos.CatalogoVehiculos;
import validaciones.Jarvis;
import validaciones.PropiasExceptions;

/**
 * Servlet implementation class EstadoReparacion
 */
public class ValidarReparacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarReparacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nroPatente = request.getParameter("nroPatente");
		String date1 = request.getParameter("fechaDesdeReparacion");
		String date2 = request.getParameter("fechaHastaReparacion");
		String opcion = request.getParameter("opcion");
		String tipoReparacion = request.getParameter("tipoReparacion");
		Jarvis j = new Jarvis();
		CatalogoVehiculos catveh = new CatalogoVehiculos();
		CatalogoReparaciones catrep = new CatalogoReparaciones();
		
		if ( date1.isEmpty() || date2.isEmpty() || nroPatente.isEmpty() || tipoReparacion.isEmpty() ) { 
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response);
		} else {
		
			try {
			
			j.validarPatente(nroPatente);
			j.validarFormatoFechas(date1, date2);
			j.fechasHabilitadas(date1, date2);
			java.sql.Date fechaDesde = j.primerFechaSQL(date1);
		    java.sql.Date fechaHasta = j.primerFechaSQL(date2);
		    j.validarFechas(fechaDesde, fechaHasta);
		    j.validarTipoRep(Integer.parseInt(opcion), tipoReparacion);
			
		    
			if ( catveh.getVehiculo(nroPatente).isEmpty() ) {
				request.setAttribute("respuesta", "El vehiculo no se encuentra en el sistema"); 
				respuesta(request,response);
		    
		    } else {
		    
		    		switch (Integer.parseInt(opcion)) {
											            
						case 1:  new CatalogoReparaciones().insertReparacionTaller(new Taller(nroPatente,fechaDesde,fechaHasta,tipoReparacion));
							// Formato de Direccion correcta correcta
							getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
							break;
						
						case 2: 
							// Input solo de letras validada
							if ( catrep.validarMecanico(tipoReparacion).isEmpty() ) {
							request.setAttribute("respuesta", "El mecanico ingresado no existe"); 
							respuesta(request,response);
							break;
							
							} else {
									
									if ( !catrep.getMecanicoDisp(tipoReparacion,fechaDesde,fechaHasta).isEmpty() ) {
									request.setAttribute("respuesta", "El mecanico "+tipoReparacion+" no se encuentra disponible en esas fechas"); 
									respuesta(request,response);
									break;
									
										} else {
										
											new CatalogoReparaciones().insertReparacionMecanico(new Mecanico(nroPatente.toUpperCase(),fechaDesde,fechaHasta,tipoReparacion));
											getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
											break;
									
										}
									}
						
						default: break;
		    		
		    		}//cierre SWITCH 
		    	
		    }// "El vehiculo no se encuentra en el sistema"
		
			} catch (PropiasExceptions pe) {
				request.setAttribute("respuesta", pe.getResp()); 
				respuesta(request,response);
			}
			
		}// "Campos completos"
			
}// "Cierre doPost"				
					 		
	
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	

	}

}
