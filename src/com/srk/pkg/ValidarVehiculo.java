package com.srk.pkg;

import java.io.IOException;
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
					String resp1 = j.validarPatente(nroPatente);
					
					if (resp1.compareTo("valido")!=0){
						request.setAttribute("respuesta", resp1); 
						respuesta(request,response);
					
					}// Id correcto 
					
					else if (!modeloVehiculo.matches("[a-zA-Z0-9]*") || modeloVehiculo.length()>45) {
						request.setAttribute("respuesta", "El campo Modelo debe tener letras y/o numeros"); 
						respuesta(request,response);
					
					} else {
						// Patente y modelo con formato correcto
						if ( !new CatalogoVehiculos().getVehiculo(nroPatente).isEmpty() ) {
							request.setAttribute("respuesta", "El vehiculo ingresado existe en el sistema"); 
							respuesta(request,response); 
							
						} else {
							// El vehiculo no se encuentra en el sistema, asi que puede ser registrado
							new CatalogoVehiculos().insertVehiculo(nroPatente, idCategoria);
							
							getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
							
						}// El vehiculo no se encuentra en el sistema, asi que puede ser registrado
						
					}// Patente con formato correcto
					
			}// Campos completos	
		
	}

	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}
	
}
