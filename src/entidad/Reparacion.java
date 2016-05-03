package entidad;

import java.sql.Date;


public class Reparacion {
	
	String nroPatente;
	Date fechaDesdeReparacion;
	Date fechaHastaReparacion;
	
	public String getTipoReparacion() {
		return "Ni Mecanico ni Taller asignado";
	}
	
	public String getNroPatente() {
		return nroPatente;
	}
	public void setNroPatente(String nroPat) {
		this.nroPatente = nroPat;
	}
	public Date getFechaDesdeReparacion() {
		return fechaDesdeReparacion;
	}
	public void setFechaDesdeReparacion(Date fechaDesdeAlq) {
		this.fechaDesdeReparacion = fechaDesdeAlq;
	}
	public Date getFechaHastaReparacion() {
		return fechaHastaReparacion;
	}
	public void setFechaHastaReparacion(Date fechaHastaAlq) {
		this.fechaHastaReparacion = fechaHastaAlq;
	}
	
	public Reparacion(){
		
	}
	
	public Reparacion(String n, Date fd, Date fh){
		nroPatente = n;
		fechaDesdeReparacion = fd;
		fechaHastaReparacion = fh;
	}
	
	
}
