package validaciones;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import java.util.regex.Pattern;

public class Jarvis {

	public void validarFormatoFechas (String date1, String date2) throws RuntimeException{
	
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
	    		throw new PropiasExceptions("El formato de ambas fechas no es valido"); 
	    	} else if (resp1==true && resp2==false) {
	    		throw new PropiasExceptions("El formato de la segunda fecha no es valido");
	    	} else if (resp1==false && resp2==true) {
	    		throw new PropiasExceptions("El formato de la primer fecha no es valido");
	    	} 
    	
	}
	
	public java.sql.Date primerFechaSQL (String date) {
		
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
	
	public java.sql.Date segundaFechaSQL (String date1){
		
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
	
	public String validarDatosCl(String usu,String cont,String conf, String dni,String nom, String tel){
		
		String respuesta="";
		
			if ( !Pattern.matches("[\\w]{6,20}",usu) ) {
				respuesta="El campo Usario debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( !Pattern.matches("[a-zA-Z0-9]{6,20}",cont) ) {
				respuesta="El campo Contraseña debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( !Pattern.matches("[a-zA-Z0-9]{6,20}",conf) ) {
				respuesta="El campo Repetir contraseña debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( cont.compareTo(conf)!=0  ) {
				respuesta="Las contraseñas deben ser iguales";
			} else if ( !Pattern.matches("\\d{8}",dni) ) {
				respuesta="El campo DNI debe contener solo 8 numeros";
			} else if ( !Pattern.matches("[a-zA-Z\\s]{1,25}",nom) ) {
				respuesta="El campo Nombre debe contener solo letras";
			} else if ( !Pattern.matches("\\d{7,9}",tel) ) {
				respuesta="El campo Telefono debe contener solo numeros (fijo o celular)";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarDatosUsu(String usu, String cont){
		
		String respuesta="";
		
			if ( !Pattern.matches("[\\w]{6,20}",usu) ) {
				respuesta="El campo Usuario debe tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else if ( !Pattern.matches("[a-zA-Z0-9]{6,20}",cont) ) {
				respuesta="El campo Contraseña debe tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarDatosCont(String contActual, String contNueva, String contConf){
		
		String respuesta="";
		
			if ( !Pattern.matches("[a-zA-Z0-9]{6,20}",contActual) ||
				 !Pattern.matches("[a-zA-Z0-9]{6,20}",contNueva) ||	
				 !Pattern.matches("[a-zA-Z0-9]{6,20}",contConf) ) {
				 respuesta="Las Contraseñas deben tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else if ( contConf.compareTo(contNueva)!=0 ) {
				respuesta="Confirmar correctamente la nueva contraseña";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public void validarFechas(Date fechaDesde, Date fechaHasta) throws RuntimeException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar cal = Calendar.getInstance();
        String fechaHoyString = sdf.format(cal.getTime());
        java.util.Date parsed = null;
        
        try {
	        parsed = sdf.parse(fechaHoyString);
	    } catch (ParseException e1) {
	    	throw new PropiasExceptions("Error del sistema");
	    }
        
        java.sql.Date fechaHoySql = new java.sql.Date(parsed.getTime());
        
        if ( fechaDesde.before(fechaHoySql) ) {
        		throw new PropiasExceptions("El Alquiler no puede ser registrado en alguna fecha antes de hoy");
			} else if ( fechaDesde.equals(fechaHasta) ) {
				throw new PropiasExceptions("Las fechas no pueden ser las mismas");
			} else if ( !fechaDesde.before(fechaHasta) ) {
				throw new PropiasExceptions("La primer fecha debe ser anterior a la segunda");
			}
			
	}
	
	public void fechasHabilitadas(String fechaDesde, String fechaHasta) throws RuntimeException{
		
		if ( !Pattern.matches("([0]+[13578]|[1]+[02])+/+([012]+[1-9]|[123]+[0-1])+/+[2]+[0]+[1-9]+[0-9]",fechaDesde) && 
			 !Pattern.matches("([0]+[469]|[1]+[1])+/+([012]+[1-9]|[123]+[0])+/+[2]+[0]+[1-9]+[0-9]",fechaDesde) &&
			 !Pattern.matches("[0]+[2]+/+[012]+[1-9]+/+[2]+[0]+[1-9]+[0-9]",fechaDesde) )
		{ throw new PropiasExceptions("La fecha de inicio tiene un formato no válido, probar de nuevo"); } 
		
		else if ( !Pattern.matches("([0]+[13578]|[1]+[02])+/+([012]+[1-9]|[123]+[0-1])+/+[2]+[0]+[1-9]+[0-9]",fechaHasta) && 
				  !Pattern.matches("([0]+[469]|[1]+[1])+/+([012]+[1-9]|[123]+[0])+/+[2]+[0]+[1-9]+[0-9]",fechaHasta) &&
				  !Pattern.matches("[0]+[2]+/+[012]+[1-9]+/+[2]+[0]+[1-9]+[0-9]",fechaHasta) )
			{ throw new PropiasExceptions("La fecha de finalizacion tiene un formato no válido, probar de nuevo"); } 
		
	}
	
	public void validarPatente(String pat) throws RuntimeException {
		
		if ( !Pattern.matches("[a-zA-Z]{3}+[0-9]{3}",pat) ) {
				throw new PropiasExceptions("La patente debe tener 3 numeros y luego 3 letras ejemplo: FAQ232"); } 
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
	
	public void validarTipoRep(int op, String tipo) throws RuntimeException{
		
			if ( op!=1 && op!=2 ) {
				throw new PropiasExceptions("Opcion incorrecta");
			} else {
					switch (op) {
		            
					case 1: if ( !Pattern.matches("[a-zA-Z\\s]{4,20}+\\s+[0-9]{1,4}",tipo) ) {
						throw new PropiasExceptions("Ingresar direccion valida, ejemplo: Italia 1455");
					}
					case 2: if ( !Pattern.matches("[a-zA-Z\\s]{1,20}",tipo) ) {
						throw new PropiasExceptions("El nombre del Mecanico debe contener solo letras");
					}	
			
					}
			
			}
	}
	
	public String validarModelo(String mod) {
		
		String respuesta="";
			
			if ( !Pattern.matches("[\\w\\s]{1,25}",mod) ) {
				respuesta="El campo Modelo debe tener letras y/o numeros";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public float generarImporte(Date fd, Date fh, float precioCat, int desc) {
		
		float importe = 0;
		
		long diferencia = ( fh.getTime() - fd.getTime() );
	    float dias = (float)Math.floor(diferencia / (1000 * 60 * 60 * 24));
	    
	    importe = dias*precioCat - ((dias*precioCat)*desc/100);
	    
	    return importe;
	
	}

	
}
