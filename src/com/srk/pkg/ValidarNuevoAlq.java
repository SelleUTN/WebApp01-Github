package com.srk.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Alquiler;
import entidad.Categoria;
import entidad.Vehiculo;
import negocio.datos.CatalogoCategorias;
import negocio.datos.CatalogoDescuentos;
import negocio.datos.CatalogoVehiculos;
import validaciones.Jarvis;
import validaciones.PropiasExceptions;

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
		
		String usuario = request.getParameter("usuario");
		String contraseña = request.getParameter("contraseña");
		String date1 = request.getParameter("fechaDesdeAlquiler");
		String date2 = request.getParameter("fechaHastaAlquiler");
		String idCategoria = request.getParameter("idCategoria");
		Jarvis j = new Jarvis();
		
		if ( date1.isEmpty() || date2.isEmpty() ){
			request.setAttribute("respuesta", "Completar campos vacíos"); 
			respuesta(request,response); 
		
		}
		
		else {
			// Campos completos
				
			try {
			
				j.fechasHabilitadas(date1, date2);
				java.sql.Date fechaDesde = new Jarvis().primerFechaSQL(date1);
				java.sql.Date fechaHasta = new Jarvis().primerFechaSQL(date2);
				j.validarFechas(fechaDesde, fechaHasta);
					
				ArrayList<Vehiculo> vehiculosDisp = new ArrayList<Vehiculo>();
				vehiculosDisp = new CatalogoVehiculos().getVehiculosDisp(Integer.parseInt(idCategoria), fechaDesde, fechaHasta);
							
				if ( vehiculosDisp.isEmpty() ) {
					request.setAttribute("respuesta", "No hay vehiculos disponibles de categoria "+idCategoria+" para las fechas ingresadas"); 
					respuesta(request,response);
							
				} else {
					// Hay vehiculos disponibles
							
					request.setAttribute("vehiculosDisp", vehiculosDisp);
					request.setAttribute("usuario", usuario);
					request.setAttribute("contraseña", contraseña);
					request.setAttribute("fechaDesde", fechaDesde);
					request.setAttribute("fechaHasta", fechaHasta);
				    int descuento = new CatalogoDescuentos().obtenerPorcentaje(fechaDesde, fechaHasta);
					float precio = new CatalogoCategorias().getPrecio(Integer.parseInt(idCategoria));
					request.setAttribute("descuento", descuento);
					request.setAttribute("importe", j.generarImporte(fechaDesde, fechaHasta, precio, descuento));
				    		
				    getServletContext().getRequestDispatcher("/listadoVehDisp.jsp").forward(request,response);
						
				 }// Hay vehiculos disponibles
		
			} catch (PropiasExceptions pe) {
				request.setAttribute("respuesta", pe.getResp()); 
				respuesta(request,response);
			} 
				
		}// Campos completos	
	
	}//cierre doPost			
			
	private void respuesta (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		getServletContext().getRequestDispatcher("/respuesta.jsp").forward(request,response);
	
	}

}
