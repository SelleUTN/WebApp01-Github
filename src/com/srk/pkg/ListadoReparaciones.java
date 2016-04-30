package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.datos.CatalogoReparaciones;


/**
 * Servlet implementation class ListadoReparaciones
 */
public class ListadoReparaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoReparaciones() {
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
		
		request.setAttribute("talleres", new CatalogoReparaciones().getReparacionesTaller());
		request.setAttribute("mecanicos", new CatalogoReparaciones().getReparacionesMecanicos());
		request.setAttribute("nombresMecanicos", new CatalogoReparaciones().getMecanicos());
		
        getServletContext().getRequestDispatcher("/listadoReparaciones.jsp").forward(request,response);
		
	}

}
