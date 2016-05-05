package com.srk.pkg;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoVehiculos;
import negocio.datos.CatalogoCategorias;
import validaciones.Jarvis;

/**
 * Servlet implementation class ValidarVehiculo
 */
public class ValidarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarVehiculo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nroPatente = request.getParameter("nroPatente");
		String modeloVehiculo = request.getParameter("modeloVehiculo");
		int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
		Jarvis j = new Jarvis();
		
		if ( nroPatente.isEmpty() || modeloVehiculo.isEmpty() ) {
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response);
		
		} else if ( new CatalogoCategorias().getCategoria(idCategoria).getIdCategoria()!=idCategoria ) 
		// Campos completos	
				{
				request.setAttribute("respuesta", "ID de Categoria incorrecto"); 
				respuesta(request,response);
			
			} else {
			// Id correcto
					
					if ( !Pattern.matches("[a-zA-Z]{3}+[0-9]{3}",nroPatente) ){
						request.setAttribute("respuesta", "Formato de vehiculo no válido"); 
						respuesta(request,response);
					
					}// Id correcto 
					
					else {
						
						String resp6 = j.validarModelo(modeloVehiculo);
						
						if (resp6.compareTo("valido")!=0){
							request.setAttribute("respuesta", resp6); 
							respuesta(request,response);
						// Patente con formato correcto
						} else {
							
							if ( !new CatalogoVehiculos().getVehiculo(nroPatente).isEmpty() ) {
								request.setAttribute("respuesta", "El vehiculo ingresado existe en el sistema"); 
								respuesta(request,response); 
								// Modelo con formato correcto	
							} else {
								// El vehiculo no se encuentra en el sistema, asi que puede ser registrado
								new CatalogoVehiculos().insertVehiculo(nroPatente.toUpperCase(), modeloVehiculo, idCategoria);
								
								getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
								
							}// El vehiculo no se encuentra en el sistema, asi que puede ser registrado
							
						}// Patente con formato correcto
						
					}// Modelo con formato correcto	
					
		}// Campos completos	
		
	}

	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}
	
}
