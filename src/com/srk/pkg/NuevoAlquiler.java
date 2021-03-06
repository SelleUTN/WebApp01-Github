package com.srk.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoCategorias;
import validaciones.PropiasExceptions;

/**
 * Servlet implementation class RegistrarAlquiler
 */
public class NuevoAlquiler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoAlquiler() {
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
		
		request.setAttribute("usuario", request.getParameter("usuario"));
		request.setAttribute("contraseña", request.getParameter("contraseña"));
		
		try {
		
			request.setAttribute("categorias", new CatalogoCategorias().getCategorias());
			getServletContext().getRequestDispatcher("/nuevoAlquiler.jsp").forward(request,response);
		
		} catch (PropiasExceptions pe) {
			request.setAttribute("respuesta", pe.getResp()); 
			respuesta(request,response);
		}
		
	}

	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
