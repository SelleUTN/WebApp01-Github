package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocio.datos.CatalogoUsuarios;
import validaciones.Jarvis;
import validaciones.PropiasExceptions;

public class ValidarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ValidarUsuario() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
							
								try {
								
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
														request.setAttribute("contraseña", u.getContraseña());
												        getServletContext().getRequestDispatcher("/pagppalCliente.jsp").forward(request,response);
												        
													} else {  
														request.setAttribute("respuesta", "Error del sistema, volver a intentar"); 
														respuesta(request,response);
													}
											}
							
								} catch (PropiasExceptions pe) {
									request.setAttribute("respuesta", pe.getResp()); 
									respuesta(request,response);
								}	
						}
				
				}
	}	
	
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
