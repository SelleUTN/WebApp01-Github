package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoUsuarios;
import validaciones.Jarvis;

/**
 * Servlet implementation class AplicarNuevaCont
 */
public class AplicarNuevaCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AplicarNuevaCont() {
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
		
		String usuario = request.getParameter("usuario");
		String contActual = request.getParameter("contActual");
		String contNueva = request.getParameter("contNueva");
		String contConfirmar = request.getParameter("contConfirmar");
		
		if ( contActual.isEmpty() || contNueva.isEmpty() || contConfirmar.isEmpty() ) {
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response);  
		}
		
		else {
		// Campos completos		
				String respuesta = new Jarvis().validarDatosCont(contActual, contNueva, contConfirmar);
				
				if ( respuesta.compareTo("valido")!=0 ){
					request.setAttribute("respuesta", respuesta); 
					respuesta(request,response); 
				
				} else {
				// Las contraseñas se encuentran ingresadas con el formato correcto	
						if (new CatalogoUsuarios().getUsuario(usuario, contActual).getDni() == 0) {
							request.setAttribute("respuesta", "Contraseña Actual incorrecta"); 
							respuesta(request,response);	
						
						} else {
						// Contraseña actual correcta, contraseña nueva confirmada y formatos correctos
							new CatalogoUsuarios().cambiarContraseña(usuario, contNueva);
							
							request.setAttribute("usuario", usuario);
							
							getServletContext().getRequestDispatcher("/pagppalCliente.jsp").forward(request,response);
						
						}// Contraseña actual correcta, contraseña nueva confirmada y formatos correctos
				
				}// Las contraseñas se encuentran ingresadas con el formato correcto
	
		}// Campos completos
	
	}//metodo doPost
	

	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}
	
}
