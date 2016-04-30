package validaciones;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import negocio.datos.CatalogoVehiculos;

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
	
	/*public java.sql.Date fechaHastaSQL (String date2) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    
		java.util.Date parsed = null;
	    try {
	        parsed = sdf.parse(date2);
	    } catch (ParseException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	    }
	    
	    java.sql.Date fechaHasta = new java.sql.Date(parsed.getTime());
	    
	    return fechaHasta;
	    
	}*/
	
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
		
			if ( !usu.matches("[a-zA-Z0-9]*") || usu.length()<6 || usu.length()>20) {
				respuesta="El campo Usario debe tener entre 6 y 20 caracteres (solo numeros y/o letras)";
			} else if ( !cont.matches("[a-zA-Z0-9]*") || cont.length()<6 || cont.length()>20) {
				respuesta="El campo Contraseña debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( !conf.matches("[a-zA-Z0-9]*") || conf.length()<6 || cont.length()>20) {
				respuesta="El campo Repetir contraseña debe tener entre 6 y 20 caracteres (numeros y/o letras)";
			} else if ( cont.compareTo(conf)!=0  ) {
				respuesta="Las contraseñas deben ser iguales";
			} else if ( !dni.matches("[0-9]*") || dni.length()!=8 ) {
				respuesta="El campo DNI debe contener solo 8 numeros";
			} else if ( !nom.matches("[a-zA-Z]*") || nom.length()>25 ) {
				respuesta="El campo Nombre debe contener solo letras";
			} else if ( !tel.matches("[0-9]*") || tel.length()<6 || tel.length()>11 ) {
				respuesta="El campo Telefono debe contener solo numeros (domicilio o celular)";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarDatosUsu(String usu, String cont){
		
		String respuesta="";
		
			if ( !usu.matches("[a-zA-Z0-9]*") || usu.length()<6 || usu.length()>20 ) {
				respuesta="El campo Usuario debe tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else if ( !cont.matches("[a-zA-Z0-9]*") || cont.length()<6 || usu.length()>20 ) {
				respuesta="El campo Contraseña debe tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarDatosCont(String contActual, String contNueva, String contConf){
		
		String respuesta="";
		
			if ( !contActual.matches("[a-zA-Z0-9]*") || contActual.length()<6 || contActual.length()>20||
				 !contNueva.matches("[a-zA-Z0-9]*") || contNueva.length()<6 || contActual.length()>20||	
				 !contConf.matches("[a-zA-Z0-9]*") || contConf.length()<6 || contActual.length()>20) {
				 respuesta="Las Contraseñas deben tener entre 6 y 20 caracteres en total (numeros y/o letras)";
			} else if ( contConf.compareTo(contNueva)!=0 ) {
				respuesta="Confirmar correctamente la nueva contraseña";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarFechas(Date fechaDesde, Date fechaHasta){
		
		String respuesta="";
		
			if ( fechaDesde.equals(fechaHasta) ) {
				 respuesta="Las fechas no pueden ser las mismas";
			} else if ( !fechaDesde.before(fechaHasta) ) {
				respuesta="La primer fecha debe ser anterior a la segunda";
			} else { respuesta="valido"; }
			
		return respuesta;
	
	}
	
	public String validarPatente(String pat) {
		
		String respuesta="";
			
			if ( !pat.matches("[a-zA-Z]{3}+[0-9]{3}") ) {
				respuesta="La patente debe tener 3 numeros y luego 3 letras ejemplo: FAQ232";
			} else { respuesta="valido"; }
			
			/*if ( pat.length()!=6 ) {
				respuesta="La patente debe contener solo 6 caracteres (numeros y letras)";
			} else if ( !pat.matches("[a-zA-Z0-9]*") ) {
				respuesta="La patente debe contener solo numeros y letras";
			} else */
		
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
		            
					case 1: if ( !tipo.matches("[a-zA-Z]{4,20}+\\s?+[0-9]{1,4}") ) {
								respuesta="Ingresar direccion valida";
							} else {
								respuesta="Taller";
							}
		                     
							break;
		            
					case 2: if ( !tipo.matches("[a-zA-Z]*") ) {
								respuesta="El nombre del Mecanico debe contener solo letras";
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
	
}
