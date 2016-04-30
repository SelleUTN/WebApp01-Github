package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoAlquileres;


/**
 * Servlet implementation class CambiarEstadoAlquiler
 */
public class CambiarEstadoAlquiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarEstadoAlquiler() {
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
		
		String nroAlquiler = request.getParameter("nroAlquiler");
		String estadoAlquiler = request.getParameter("estadoAlquiler");
		
		new CatalogoAlquileres().cambiarEstadoAlq(Integer.parseInt(nroAlquiler), estadoAlquiler);
		
		getServletContext().getRequestDispatcher("/pagppalEncargado.jsp").forward(request,response);
		
	}

}
