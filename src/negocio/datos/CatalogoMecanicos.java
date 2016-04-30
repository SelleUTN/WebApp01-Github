package negocio.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidad.Mecanico;
import util.DataConnectionManager;

public class CatalogoMecanicos {

//====================================================================================	
	
	public String validarMecanico(String nombre){
			
		String nombreMecanico = "";
		String sql="select nombreMecanico from mecanicos where nombreMecanico=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nombre);
				rs = sentencia.executeQuery();
					
				while(rs.next()){
					nombreMecanico=rs.getString("nombreMecanico");
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
					if(rs!=null){rs.close();}
					if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
					DataConnectionManager.getInstancia().CloseConn();;
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}
			
		return nombreMecanico;

	}

//====================================================================================	
	
	public String getMecanicoDisp(String nombre, Date fechaDesde, Date fechaHasta){
				
		String nombreMecanico= "";
		String sql="select nombreMecanico from reparaciones where nombreMecanico = ? "
				+ "and fechaDesdeReparacion <= ? and fechaHastaReparacion >= ?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
				
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nombre);
				sentencia.setDate(2,fechaHasta);
				sentencia.setDate(3,fechaDesde);
				rs = sentencia.executeQuery();
						
				while(rs.next()){
					nombreMecanico=(rs.getString("nombreMecanico"));
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
					if(rs!=null){rs.close();}
					if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
					DataConnectionManager.getInstancia().CloseConn();;
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}
				
		return nombreMecanico;

	}
		
//====================================================================================	

}
