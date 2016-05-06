package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Alquiler;
import negocio.datos.CatalogoAlquileres;
import validaciones.Jarvis;
import validaciones.PropiasExceptions;

/**
 * Servlet implementation class Alquiler2
 */
public class GenerarAlquiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerarAlquiler() {
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
		
		String usuario = request.getParameter("usuario");
		Jarvis j = new Jarvis();
		
		try {
		
		new CatalogoAlquileres().insertAlquiler( 
				usuario,
				request.getParameter("nroPatente"),
				Float.parseFloat(request.getParameter("importe")),
				(j.segundaFechaSQL(request.getParameter("fechaDesde"))), 
				(j.segundaFechaSQL(request.getParameter("fechaHasta")))
		);
		
		request.setAttribute("usuario", usuario);
		request.setAttribute("contraseña", request.getParameter("contraseña"));
		
		getServletContext().getRequestDispatcher("/pagppalCliente.jsp").forward(request,response);
		
		} catch (PropiasExceptions pe) {
			request.setAttribute("respuesta", pe.getResp()); 
			respuesta(request,response);
		}
	}
	
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}
}
