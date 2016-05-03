package validaciones;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import java.util.regex.Pattern;

public class Jarvis {

	public String validarFormatoFechas (String date1, String date2) {
		
		String respuesta = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    java.util.Date parsed = null;
	    boolean resp1=true, resp2=true;
			
	    	try {
		        parsed = sdf.parse(date1);
		    } catch (ParseException e1) {
		        resp1=false;
		    }
	    	
	    	try {
		        parsed = sdf.parse(date2);
		    } catch (ParseException e1) {
		        resp2=false;
		    }
		
	    	if ( resp1==false && resp2==false) {
	    		respuesta = "El formato de ambas fechas no es valido";
	    	} else if (resp1==true && resp2==false) {
	    		respuesta = "El formato de la segunda fecha no es valido";
	    	} else if (resp1==false && resp2==true) {
	    		respuesta = "El formato de la primer fecha no es valido";
	    	} else if (resp1==true && resp2==true) {
	    		respuesta = "valido";
	    	}
	    	
	    return respuesta;
	
	}
	
	public java.sql.Date fechaSQL (String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    
		java.util.Date parsed = null;
	    try {
	        parsed = sdf.parse(date);
	    } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	    
	    java.sql.Date fecha = new java.sql.Date(parsed.getTime());
	    
	    return fecha;
	    
	}
	
	public java.sql.Date fechaDesdeaSQL (String date1){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    
		java.util.Date parsed = null;
	    try {
	        parsed = sdf.parse(date1);
	    } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	    
	    java.sql.Date fechaDesde = new java.sql.Date(parsed.getTime());
	    
	    return fechaDesde;
	    
	}
	
	public java.sql.Date fechaHastaaSQL (String date1){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    
		java.util.Date parsed = null;
	    try {
	        parsed = sdf.parse(date1);
	    } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	    
	    java.sql.Date fechaHasta = new java.sql.Date(parsed.getTime());
	    
	    return fechaHasta;
	    
	}
	
	public String validarDatosCl(String usu,String cont,String conf, String dni,String nom, String tel){
		
		String respuesta="";
		
			if ( !Pattern.matches("[\\wñÑáéíóú]{6,20}",usu) ) {
				respuesta="El campo Usario debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( !Pattern.matches("[a-zA-Z0-9ñÑáéíóú]{6,20}",cont) ) {
				respuesta="El campo Contraseña debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( !Pattern.matches("[a-zA-Z0-9ñÑáéíóú]{6,20}",conf) ) {
				respuesta="El campo Repetir contraseña debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( cont.compareTo(conf)!=0  ) {
				respuesta="Las contraseñas deben ser iguales";
			} else if ( !Pattern.matches("\\d{8}",dni) ) {
				respuesta="El campo DNI debe contener solo 8 numeros";
			} else if ( !Pattern.matches("[a-zA-ZñÑáéíóú\\s]{1,25}",nom) ) {
				respuesta="El campo Nombre debe contener solo letras";
			} else if ( !Pattern.matches("\\d{7,9}",tel) ) {
				respuesta="El campo Telefono debe contener solo numeros (fijo o celular)";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarDatosUsu(String usu, String cont){
		
		String respuesta="";
		
			if ( !Pattern.matches("[\\wñÑáéíóú]{6,20}",usu) ) {
				respuesta="El campo Usuario debe tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else if ( !Pattern.matches("[a-zA-Z0-9ñÑáéíóú]{6,20}",cont) ) {
				respuesta="El campo Contraseña debe tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarDatosCont(String contActual, String contNueva, String contConf){
		
		String respuesta="";
		
			if ( !Pattern.matches("[a-zA-Z0-9ñÑáéíóú]{6,20}",contActual) ||
				 !Pattern.matches("[a-zA-Z0-9ñÑáéíóú]{6,20}",contNueva) ||	
				 !Pattern.matches("[a-zA-Z0-9ñÑáéíóú]{6,20}",contConf) ) {
				 respuesta="Las Contraseñas deben tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else if ( contConf.compareTo(contNueva)!=0 ) {
				respuesta="Confirmar correctamente la nueva contraseña";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarFechas(Date fechaDesde, Date fechaHasta){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
        String fechaHoyString = sdf.format(cal.getTime());
        java.util.Date parsed = null;
        
        try {
	        parsed = sdf.parse(fechaHoyString);
	    } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
        java.sql.Date fechaHoySql = new java.sql.Date(parsed.getTime());
        
        String respuesta="";
			
			if ( fechaDesde.before(fechaHoySql) ) {
				respuesta="El Alquiler no puede ser registrado en alguna fecha antes de hoy";
			} else if ( fechaDesde.equals(fechaHasta) ) {
				 respuesta="Las fechas no pueden ser las mismas";
			} else if ( !fechaDesde.before(fechaHasta) ) {
				respuesta="La primer fecha debe ser anterior a la segunda";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarPatente(String pat) {
		
		String respuesta="";
			
			if ( !Pattern.matches("[a-zA-Z]{3}+[0-9]{3}",pat) ) {
				respuesta="La patente debe tener 3 numeros y luego 3 letras ejemplo: FAQ232";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public boolean compararPatentes(String pat1, String pat2) {
		
		boolean bandera = true;
			
			try {
				pat1.equals(pat2);
			} catch (NullPointerException e) { 
				bandera = false;; 
			}
		
		return bandera;
	
	}
	
	public boolean compararCadenas(String cad1, String cad2) {
		
		boolean bandera = true;
			
			try {
				cad1.equals(cad2);
			} catch (NullPointerException e) { 
				bandera = false;; 
			}
		
		return bandera;
	
	}
	
	public String validarTipoRep(int op, String tipo){
		
		String respuesta="";
			
			if ( op!=1 && op!=2 ) {
				respuesta="Opcion incorrecta";
			} else {
				
					switch (op) {
		            
					case 1: if ( !Pattern.matches("[a-zA-Z\\s]{4,20}+\\s?+[0-9]{1,4}",tipo) ) {
								respuesta="Ingresar direccion valida, ejemplo: Italia 1455";
							} else {
								respuesta="Taller";
							}
		                     
							break;
		            
					case 2: if ( !Pattern.matches("[a-zA-Z]{1,20}",tipo) ) {
								respuesta="El nombre del Mecanico debe contener solo letras, sin espacios";
							} else {
								respuesta="Mecanico"; 
							}
		            
							break;
							
					 default: respuesta="Error de opcion";
					 		break;
					
					}
			
			}
			
		return respuesta;
	
	}

	public String validarModelo(String mod) {
		
		String respuesta="";
			
			if ( !Pattern.matches("[\\wñÑáéíóú\\s]{1,25}",mod) ) {
				respuesta="El campo Modelo debe tener letras y/o numeros";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}

	
}
