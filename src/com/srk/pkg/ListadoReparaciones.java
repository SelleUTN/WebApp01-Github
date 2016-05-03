package com.srk.pkg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import entidad.Mecanico;
import entidad.Reparacion;
import entidad.Taller;
import negocio.datos.CatalogoReparaciones;
import validaciones.DateComparator;
import validaciones.Jarvis;


/**
 * Servlet implementation class ListadoReparaciones
 */
public class ListadoReparaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoReparaciones() {
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
		
		ArrayList<Reparacion> reparaciones = new ArrayList<Reparacion>();
		ArrayList<Taller> talleres = new CatalogoReparaciones().getReparacionesTaller();
		ArrayList<Mecanico> mecanicos = new CatalogoReparaciones().getReparacionesMecanicos();
		
		for (int i=0; i<talleres.size(); i++) {
			reparaciones.add(talleres.get(i));
		}
		for (int i=0; i<mecanicos.size(); i++) {
			reparaciones.add(mecanicos.get(i));
		}
		
		Collections.sort(reparaciones, new DateComparator());
		
		request.setAttribute("reparaciones", reparaciones);
		request.setAttribute("nombresMecanicos", new CatalogoReparaciones().getMecanicos());
		
		getServletContext().getRequestDispatcher("/listadoReparaciones.jsp").forward(request,response);
		
	}

}
