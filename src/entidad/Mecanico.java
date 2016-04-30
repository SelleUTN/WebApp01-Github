package entidad;

import java.sql.Date;

public class Mecanico extends Reparacion {

	String nombreMecanico;
	
	public String getNombreMecanico() {
		return nombreMecanico;
	}

	public void setNombreMecanico(String nombreMecanico) {
		this.nombreMecanico = nombreMecanico;
	}
	
	public Mecanico () {
		
	}
	
	public Mecanico (String np, Date fd, Date fh, String n){
		super(np,fd,fh);
		this.nombreMecanico = n;
	}
	
}
