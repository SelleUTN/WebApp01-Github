package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Mecanico;
import entidad.Taller;
import negocio.datos.CatalogoReparaciones;
import negocio.datos.CatalogoVehiculos;
import validaciones.Jarvis;

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
		
		if ( date1.isEmpty() || date2.isEmpty() || nroPatente.isEmpty() || tipoReparacion.isEmpty() ) { 
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response);
		} 
		
		else {
			// Campos completos
			String resp1 = j.validarPatente(nroPatente);
			
			if ( resp1.compareTo("valido")!=0 ) { 
				request.setAttribute("respuesta", resp1); 
				respuesta(request,response);
			
			} else {
			// Patente ingresada en el formato correcto
					if ( catveh.getVehiculo(nroPatente).isEmpty() ) {
						request.setAttribute("respuesta", "El vehiculo no se encuentra en el sistema"); 
						respuesta(request,response);
					
					} else {
					// El vehiculo ingresado se encuentra en el sistema	
							String resp2 = j.validarFormatoFechas(date1, date2);	
							
							if ( resp2.compareTo("valido")!=0 ) {
								request.setAttribute("respuesta", resp2); 
								respuesta(request,response);
										
							} else {
							// Las fechas fueron ingresadas en el formato correcto	
								
								java.sql.Date fechaDesde = new Jarvis().fechaSQL(date1);
							    
							    java.sql.Date fechaHasta = new Jarvis().fechaSQL(date2);	
							    
							    String resp3 = j.validarFechas(fechaDesde, fechaHasta);
								
									if ( resp3.compareTo("valido")!=0 ){
										request.setAttribute("respuesta", resp3); 
										respuesta(request,response); 
									
									} else {
									// Las fechas fueron ingresadas correctamente
											if ( j.compararPatentes(new CatalogoVehiculos().getVehiculoDisp(nroPatente, fechaDesde, fechaHasta).getNroPatente(), nroPatente) ) {
												request.setAttribute("respuesta", "El vehiculo no esta disponible en esas fechas"); 
												respuesta(request,response); 
												
											} else {
											// El vehiculo se encuentra disponible en esas fechas
												String resp4 = j.validarTipoRep(Integer.parseInt(opcion), tipoReparacion);
													
														switch (resp4) {
											            
													    case "Taller":  new CatalogoReparaciones().insertReparacionTaller(new Taller(nroPatente,fechaDesde,fechaHasta,tipoReparacion));
													    	// Formato de Direccion correcta correcta
													    				getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
													    				break;
											            case "Mecanico": 
											            	// Input solo de letras validada
											            				if ( new CatalogoReparaciones().validarMecanico(tipoReparacion).isEmpty() ) {
											            					request.setAttribute("respuesta", "El mecanico ingresado no existe"); 
												            				respuesta(request,response);
												            				break;
											            				} else {
												            					if ( !new CatalogoReparaciones().getMecanicoDisp(tipoReparacion.toUpperCase(),fechaDesde,fechaHasta).isEmpty() ) {
												            						request.setAttribute("respuesta", "El mecanico "+tipoReparacion+" no se encuentra disponible en esas fechas"); 
														            				respuesta(request,response);
														            				break;
												            					} else {
												            						new CatalogoReparaciones().insertReparacionMecanico(new Mecanico(nroPatente.toUpperCase(),fechaDesde,fechaHasta,tipoReparacion));
												            						getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
												            						break;
												            					}
											            				}
											            	
											            default: request.setAttribute("respuesta", resp4); 
																 respuesta(request,response);
																 break;
													    }//cierre SWITCH
														
											}// El vehiculo se encuentra disponible en esas fechas
											
									}// Las fechas fueron ingresadas correctamente	
								
							}// Las fechas fueron ingresadas en el formato correcto
									
					}// El vehiculo ingresado se encuentra en el sistema
					
			}// Patente ingresada en el formato correcto
			
		}// Campos completos		
	
	}//cierre doPost
			
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
