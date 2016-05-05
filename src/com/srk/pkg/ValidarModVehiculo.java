package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoVehiculos;

/**
 * Servlet implementation class ValidarModVehiculo
 */
public class ValidarModVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarModVehiculo() {
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
		String modelo = request.getParameter("modelo");
		
		if ( !modelo.matches("[\\w\\s]{1,25}") ){
			request.setAttribute("respuesta", "El campo Modelo debe contener solo letras y/o numeros"); 
			respuesta(request,response);
		} else {
			new CatalogoVehiculos().cambiarModelo(nroPatente, modelo);
		
			getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
		}
		
		
	}

	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}
	
}
