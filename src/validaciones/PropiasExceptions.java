package validaciones;

import java.util.regex.Pattern;

public class PropiasExceptions extends RuntimeException{
	
	String resp;
	
	public PropiasExceptions (String respuesta) {
		this.resp=respuesta;
	}

	public String getResp() {
		return resp;
	}
	
}

		

	
	

