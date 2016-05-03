package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Alquiler;
import negocio.datos.CatalogoCategorias;
import negocio.datos.CatalogoDescuentos;
import negocio.datos.CatalogoVehiculos;
import validaciones.Jarvis;

/**
 * Servlet implementation class Alquiler1
 */
public class ValidarNuevoAlq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarNuevoAlq() {
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
		
		String date1 = request.getParameter("fechaDesdeAlquiler");
		String date2 = request.getParameter("fechaHastaAlquiler");
		String idCategoria = request.getParameter("idCategoria");
		Jarvis j = new Jarvis();
		
		if ( date1.isEmpty() || date2.isEmpty() || idCategoria.isEmpty() ){
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response); 
		
		}
		
		else {
			// Campos completos
			String resp1 = j.validarFormatoFechas(date1, date2);	
			
			if ( resp1.compareTo("valido")!=0 ) {
				request.setAttribute("respuesta", resp1); 
				respuesta(request,response);
						
			} else {
			// Las fechas fueron ingresadas en el formato correcto
						
					java.sql.Date fechaDesde = new Jarvis().fechaSQL(date1);
				    
				    java.sql.Date fechaHasta = new Jarvis().fechaSQL(date2);
				    
					String resp2 = j.validarFechas(fechaDesde, fechaHasta);
					
					if ( resp2.compareTo("valido")!=0 ){
						request.setAttribute("respuesta", resp2); 
						respuesta(request,response); 
					
					} else {
					// Las fechas fueron ingresadas correctamente
						request.setAttribute("vehiculosDisp", new CatalogoVehiculos().getVehiculosDisp(Integer.parseInt(idCategoria), fechaDesde, fechaHasta));
			    		
						request.setAttribute("descuento", new CatalogoDescuentos().obtenerPorcentaje(fechaDesde, fechaHasta) );
						
			    		request.setAttribute("Alquiler", new Alquiler( 
			    					request.getParameter("usuario"), fechaDesde, fechaHasta, 
			    					new CatalogoCategorias().getPrecio(Integer.parseInt(idCategoria)), 
			    					new CatalogoDescuentos().obtenerPorcentaje(fechaDesde, fechaHasta) ) 
			    		);
			    		
			    		request.setAttribute("usuario", request.getParameter("usuario"));
			    		
			    		request.setAttribute("idCategoria", idCategoria);
			    		
						getServletContext().getRequestDispatcher("/listadoVehDisp.jsp").forward(request,response);
					
					}// Las fechas fueron ingresadas correctamente
			
			}// Las fechas fueron ingresadas en el formato correcto
		
		}// Campos completos	
	
	}//cierre doPost			
			
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
