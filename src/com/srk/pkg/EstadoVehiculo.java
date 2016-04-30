package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoAlquileres;
import negocio.datos.CatalogoReparaciones;

/**
 * Servlet implementation class EstadoVehiculo
 */
public class EstadoVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EstadoVehiculo() {
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
		
		String nroPat=request.getParameter("nroPatente");
		
		request.setAttribute("talleres", new CatalogoReparaciones().getVehiculoTall(nroPat));
		request.setAttribute("mecanicos", new CatalogoReparaciones().getVehiculoMec(nroPat));
		request.setAttribute("alquileres", new CatalogoAlquileres().getVehAlquileres(nroPat));
		
        getServletContext().getRequestDispatcher("/estadoVehiculo.jsp").forward(request,response);
		
	}

}
