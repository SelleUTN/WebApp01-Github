package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoAlquileres;
import negocio.datos.CatalogoCategorias;
import negocio.datos.CatalogoVehiculos;

/**
 * Servlet implementation class ListadoVehiculos
 */
public class ListadoVehiculos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoVehiculos() {
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
		
		request.setAttribute("categorias", new CatalogoCategorias().getCategorias());
		request.setAttribute("vehiculos", new CatalogoVehiculos().getVehiculos());
        getServletContext().getRequestDispatcher("/listadoVehiculos.jsp").forward(request,response);
		
	}

}
