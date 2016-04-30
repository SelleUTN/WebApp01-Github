package entidad;

public class Vehiculo {

	String nroPatente;
	String modeloVehiculo;
	int idCategoria;
	
	public String getNroPatente() {
		return nroPatente;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public void setNroPatente(String nroPatente) {
		this.nroPatente = nroPatente;
	}
	public String getModeloVehiculo() {
		return modeloVehiculo;
	}
	public void setModeloVehiculo(String modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}
	
	public Vehiculo(){
		
	}
	
	public Vehiculo(String n, String m){
		nroPatente = n;
		modeloVehiculo = m;
	}
	
	public Vehiculo(String n, String m, int i){
		nroPatente = n;
		modeloVehiculo = m;
		idCategoria = i;
	}
	
}
