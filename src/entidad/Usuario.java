package entidad;

public class Usuario {
	
	int dniUsuario;
	String usuarioUsuario;
	String contraseñaUsuario;
	String nombreUsuario;
	int telefonoUsuario;
	String rolUsuario;
	
	public int getDni() {
		return dniUsuario;
	}
	public void setDni(int dni) {
		this.dniUsuario = dni;
	}
	public String getUsuario() {
		return usuarioUsuario;
	}
	public void setUsuario(String usuario) {
		this.usuarioUsuario = usuario;
	}
	public String getContraseña() {
		return contraseñaUsuario;
	}
	public void setContraseña(String contraseña) {
		this.contraseñaUsuario = contraseña;
	}
	public String getNombre() {
		return nombreUsuario;
	}
	public void setNombre(String nombre) {
		this.nombreUsuario = nombre;
	}
	public int getTelefono() {
		return telefonoUsuario;
	}
	public void setTelefono(int telefono) {
		this.telefonoUsuario = telefono;
	}
	public String getRol() {
		return rolUsuario;
	}
	public void setRol(String rol) {
		this.rolUsuario = rol;
	}
	
	public Usuario(){
		
	}
	
	public Usuario(int d, String u,String c, String n, int t){
		dniUsuario = d;
		usuarioUsuario = u;
		contraseñaUsuario = c;
		nombreUsuario = n;
		telefonoUsuario = t;
	}
	
	public Usuario(int d, String u,String c, String n, int t, String r){
		dniUsuario = d;
		usuarioUsuario = u;
		contraseñaUsuario = c;
		nombreUsuario = n;
		telefonoUsuario = t;
		rolUsuario = r;
	}
	
}
