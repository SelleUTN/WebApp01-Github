package validaciones;

import java.util.Comparator;

import entidad.Reparacion;

public class DateComparator implements Comparator<Reparacion>{
	
	public int compare(Reparacion r1, Reparacion r2) {
        if ( r1.getFechaDesdeReparacion().before(r2.getFechaDesdeReparacion()) ) {
            return 1;
        } else if ( r1.getFechaDesdeReparacion().after(r2.getFechaDesdeReparacion()) ) {
            return -1;
        } else {
            return 0;
        }        
    }
	
}
