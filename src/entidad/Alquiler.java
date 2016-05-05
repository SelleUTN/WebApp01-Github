package entidad;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Alquiler {

	int nroAlquiler;
	String usuarioCliente;
	String nroPatente;
	float importe;
	Date fechaDesdeAlquiler;
	Date fechaHastaAlquiler;
	String estadoAlquiler;
	
	public int getNroAlquiler() {
		return nroAlquiler;
	}
	public void setNroAlquiler(int nroAlquiler) {
		this.nroAlquiler = nroAlquiler;
	}
	public String getUsuarioCliente() {
		return usuarioCliente;
	}
	public void setUsuarioCliente(String usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}
	public String getNroPatente() {
		return nroPatente;
	}
	public void setNroPatente(String nroPatente) {
		this.nroPatente = nroPatente;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public Date getFechaDesdeAlquiler() {
		return fechaDesdeAlquiler;
	}
	public void setFechaDesdeAlquiler(Date fechaDesdeAlq) {
		this.fechaDesdeAlquiler = fechaDesdeAlq;
	}
	public Date getFechaHastaAlquiler() {
		return fechaHastaAlquiler;
	}
	public void setFechaHastaAlquiler(Date fechaHastaAlq) {
		this.fechaHastaAlquiler = fechaHastaAlq;
	}
	public String getEstadoAlquiler() {
		return estadoAlquiler;
	}
	public void setEstadoAlquiler(String estadoAlquiler) {
		this.estadoAlquiler = estadoAlquiler;
	}
	
	public Alquiler(){
		
	}
	
	public Alquiler(String u, String n,float i, Date fd, Date fh){
		usuarioCliente = u;
		nroPatente = n;
		importe = i;
		fechaDesdeAlquiler = fd;
		fechaHastaAlquiler = fh;
	}
	
}
