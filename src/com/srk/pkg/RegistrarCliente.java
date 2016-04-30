package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.datos.CatalogoUsuarios;
import validaciones.Jarvis;
import entidad.Usuario;
/**
 * Servlet implementation class RegistrarCliente
 */
public class RegistrarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarCliente() {
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
		
		String usuarioCliente = request.getParameter("usuarioCliente");
		String contCliente = request.getParameter("contCliente");
		String confCont = request.getParameter("confCont");
		String dniCliente = request.getParameter("dniCliente");
		String nombreCliente = request.getParameter("nombreCliente");
		String telefonoCliente = request.getParameter("telefonoCliente");
		
		if ( usuarioCliente.isEmpty() || contCliente.isEmpty() || confCont.isEmpty() || nombreCliente.isEmpty() || telefonoCliente.isEmpty() ) { 
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response);
		} 
		
		else { 
			
				String respuesta = new Jarvis().validarDatosCl(usuarioCliente,contCliente,confCont,dniCliente,nombreCliente,telefonoCliente);
				
				if ( respuesta.compareTo("valido")!=0 ){
					request.setAttribute("respuesta", respuesta); 
					respuesta(request,response); 
				} else  {
					
					CatalogoUsuarios catusu = new CatalogoUsuarios();
					
					if ( catusu.validarUsuario(usuarioCliente).compareTo(usuarioCliente)==0 ) {
						request.setAttribute("respuesta", "El Usuario ingresado ya existe"); 
						respuesta(request,response); 
					} else {
							catusu.insertCliente(new Usuario(
									Integer.parseInt(dniCliente),
									usuarioCliente,
									nombreCliente,
									contCliente,
									Integer.parseInt(telefonoCliente) )
									);
							
							getServletContext().getRequestDispatcher("/ingresarUsuario.jsp").forward(request,response);
						}
				
				}
		
			 }
		
	}
	
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
