package entidad;

import java.sql.Date;

public class Taller extends Reparacion {
	
	String direccion;
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Taller () {
		
	}
	
	public Taller (String np, Date fd, Date fh, String d) {
		super(np,fd,fh);
		this.direccion = d;
	}
	
}
