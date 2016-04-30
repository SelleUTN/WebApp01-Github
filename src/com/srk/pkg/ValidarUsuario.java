package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Socio;
import entidad.Usuario;
import negocio.datos.CatalogoUsuarios;
import validaciones.Jarvis;

/**
 * Servlet implementation class ValidarUsuario
 */
public class ValidarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarUsuario() {
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
		String cont = request.getParameter("cont");
		
			if ( usuario.isEmpty() || cont.isEmpty() ){
				request.setAttribute("respuesta", "Completar campos vacíos"); 
				respuesta(request,response);
			
			} else {
				
				String respuesta = new Jarvis().validarDatosUsu(usuario, cont);
				
						if ( respuesta.compareTo("valido")!=0 ){
							request.setAttribute("respuesta", respuesta); 
							respuesta(request,response); 
						
						} else {
							
							CatalogoUsuarios catusu = new CatalogoUsuarios();
							Usuario u = catusu.getUsuario(usuario, cont);
							
									if ( u.getDni()==0 ) {
										request.setAttribute("respuesta", "Usuario y/o Contraseña incorrecta"); 
										respuesta(request,response); 
									
									} else {
										
												if (u.getRol().equals("gerente")){
													//sistema-vista de gerente
													getServletContext().getRequestDispatcher("/pagppalGerente.jsp").forward(request,response);
												
												} else if (u.getRol().equals("encargado")){
													//sistema-vista de encargado
													getServletContext().getRequestDispatcher("/pagppalEncargado.jsp").forward(request,response);
													
												} else if (u.getRol().equals("cliente")){
													//sistema-vista de cliente
													request.setAttribute("usuario", u.getUsuario());
											        getServletContext().getRequestDispatcher("/pagppalCliente.jsp").forward(request,response);
											        
												} else {  
													request.setAttribute("respuesta", "Error del sistema, volver a intentar"); 
													respuesta(request,response);
												}
										}
						
						}
				
				}
	}	
	
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
