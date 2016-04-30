package entidad;

public class Socio {
	
	int id;
	String contraseña;
	int telefono;
	int dni;
	String nombre;
	
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Socio(){
		
	}
	
	public Socio(int i, int d, String n){
		id = i;
		dni = d;
		nombre = n;
	}
	
	public Socio(int d, String n){
		dni = d;
		nombre = n;
	}
	
	public Socio(int i, int d, String n, String con, int t){
		id = i;
		dni = d;
		nombre = n;
		contraseña = con;
		telefono = t;
	}
	
}
