package entidad;

public class Descuento {

	int descuento;
	int diaDesde;
	int diaHasta;
	
	public int getDescuento() {
		return descuento;
	}
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}
	public int getDiaDesde() {
		return diaDesde;
	}
	public void setDiaDesde(int diaDesde) {
		this.diaDesde = diaDesde;
	}
	public int getDiaHasta() {
		return diaHasta;
	}
	public void setDiaHasta(int diaHasta) {
		this.diaHasta = diaHasta;
	}
	
	public Descuento() {
		
	}
	
	public Descuento (int d, int dd, int dh) {
		descuento = d;
		diaDesde = dd;
		diaHasta = dh;
	}
	
}
