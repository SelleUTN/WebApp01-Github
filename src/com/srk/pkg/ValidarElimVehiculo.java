package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoVehiculos;

/**
 * Servlet implementation class ValidarElimVehiculo
 */
public class ValidarElimVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarElimVehiculo() {
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
		CatalogoVehiculos catveh = new CatalogoVehiculos();
		
		if ( !catveh.validarEliminacion(nroPatente).isEmpty() ) {
			request.setAttribute("respuesta", "El vehiculo tiene al menos un alquiler o reparacion pendiente"); 
			respuesta(request,response);
		} else {
			catveh.eliminarVehiculo(nroPatente);
		
			getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
		}
		
		
	}
	
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
