package entidad;

public class Categoria {

	int idCategoria;
	String descripcionCategoria;
	float precioCategoria;
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCat) {
		this.idCategoria = idCat;
	}
	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}
	public void setDescripcionCategoria(String descCat) {
		this.descripcionCategoria = descCat;
	}
	public float getPrecioCategoria() {
		return precioCategoria;
	}
	public void setPrecioCategoria(float precioCat) {
		this.precioCategoria = precioCat;
	}
	
	public Categoria(){
		
	}
	
	public Categoria(String d, float p){
		descripcionCategoria = d;
		precioCategoria = p;
	}
	
}
