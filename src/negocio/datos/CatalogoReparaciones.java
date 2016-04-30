package negocio.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Mecanico;
import entidad.Taller;
import util.DataConnectionManager;

public class CatalogoReparaciones {

//====================================================================================		
	
	public void insertReparacionTaller(Taller t){
			
		String sql="insert into reparaciones (nroPatente,fechaDesdeReparacion,fechaHastaReparacion,direccionTaller) values(?,?,?,?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
				
				
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, t.getNroPatente());
				sentencia.setDate(2, t.getFechaDesdeReparacion());
				sentencia.setDate(3, t.getFechaHastaReparacion());
				sentencia.setString(4, t.getDireccion());
						
				int filasAfectadas=sentencia.executeUpdate();
				ResultSet cps= sentencia.getGeneratedKeys();
				if(cps.next()){
							
				}

				
		} catch (SQLException e) {
					e.printStackTrace();
		}
		finally{
				try{
						if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
						DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
							sqle.printStackTrace();
				}
		}
	
	}

//====================================================================================
		
	public void insertReparacionMecanico(Mecanico m){
			
		String sql="insert into reparaciones (nroPatente,fechaDesdeReparacion,fechaHastaReparacion,nombreMecanico) values(?,?,?,?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
				
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, m.getNroPatente());
				sentencia.setDate(2, m.getFechaDesdeReparacion());
				sentencia.setDate(3, m.getFechaHastaReparacion());
				sentencia.setString(4, m.getNombreMecanico());
						
				int filasAfectadas=sentencia.executeUpdate();
				ResultSet cps= sentencia.getGeneratedKeys();
				if(cps.next()){
							
				}

		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
						if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
						DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}
	
	}

//====================================================================================

	public ArrayList<Taller> getReparacionesTaller() {
			
		ArrayList<Taller> talleres = new ArrayList<Taller>();

		String sql= "select * from reparaciones "
				+ "where nombreMecanico is null "
				+ "order by fechaDesdeReparacion desc;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
				
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				rs = sentencia.executeQuery();

				while(rs.next()){
					Taller t = new Taller();
					t.setNroPatente(rs.getString("nroPatente"));
					t.setFechaDesdeReparacion(rs.getDate("fechaDesdeReparacion"));
					t.setFechaHastaReparacion(rs.getDate("fechaHastaReparacion"));
					t.setDireccion(rs.getString("direccionTaller"));
					talleres.add(t);
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
					if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
					DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}

		return talleres;
				
	}

//====================================================================================

	public ArrayList<Mecanico> getReparacionesMecanicos() {
					
		ArrayList<Mecanico> mecanicos = new ArrayList<Mecanico>();

		String sql= "select * from reparaciones "
				+ "where direccionTaller is null "
				+ "order by fechaDesdeReparacion desc;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
					
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				rs = sentencia.executeQuery();

				while(rs.next()){
					Mecanico m = new Mecanico();
					m.setNroPatente(rs.getString("nroPatente"));
					m.setFechaDesdeReparacion(rs.getDate("fechaDesdeReparacion"));
					m.setFechaHastaReparacion(rs.getDate("fechaHastaReparacion"));
					m.setNombreMecanico(rs.getString("nombreMecanico"));
					mecanicos.add(m);
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
					if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
					DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}

		return mecanicos;
					
	}

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

	public ArrayList<Mecanico> getMecanicos(){
						
		ArrayList<Mecanico> mecanicos = new ArrayList<Mecanico>();
						
		String sql="select * from mecanicos";
		Statement sentencia=null;
		ResultSet rs=null;

			try {	
				sentencia= DataConnectionManager.getInstancia().getConn().createStatement();
				rs= sentencia.executeQuery(sql);

				while(rs.next()){
					Mecanico m= new Mecanico();
					m.setNombreMecanico(rs.getString("nombreMecanico"));
					mecanicos.add(m);
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
						if(rs!=null){rs.close();}
						if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
						DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}

		return mecanicos;
						
	}

//====================================================================================

	public ArrayList<Mecanico> getVehiculoMec(String nroPat){
							
			ArrayList<Mecanico> mecanicos = new ArrayList<Mecanico>();
							
			String sql="select * from reparaciones "
					+ "where direccionTaller is null and nroPatente=? "
					+ "order by fechaDesdeReparacion desc;";
			PreparedStatement sentencia=null;
			Connection conn=DataConnectionManager.getInstancia().getConn();
			ResultSet rs=null;

				try {	
					sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					sentencia.setString(1,nroPat);
					rs = sentencia.executeQuery();

					while(rs.next()){
						Mecanico m = new Mecanico();
						m.setNroPatente(rs.getString("nroPatente"));
						m.setFechaDesdeReparacion(rs.getDate("fechaDesdeReparacion"));
						m.setFechaHastaReparacion(rs.getDate("fechaHastaReparacion"));
						m.setNombreMecanico(rs.getString("nombreMecanico"));
						mecanicos.add(m);
					}	
			} catch (SQLException e) {
					e.printStackTrace();
			}
			finally{
					try{
							if(rs!=null){rs.close();}
							if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
							DataConnectionManager.getInstancia().CloseConn();
					}
					catch (SQLException sqle){
							sqle.printStackTrace();
					}
			}

			return mecanicos;
							
		}

//====================================================================================

	public ArrayList<Taller> getVehiculoTall(String nroPat) {
		
		ArrayList<Taller> talleres = new ArrayList<Taller>();

		String sql= "select * from reparaciones "
				+ "where nombreMecanico is null and nroPatente=? "
				+ "order by fechaDesdeReparacion desc;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
				
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nroPat);
				rs = sentencia.executeQuery();

				while(rs.next()){
					Taller t = new Taller();
					t.setNroPatente(rs.getString("nroPatente"));
					t.setFechaDesdeReparacion(rs.getDate("fechaDesdeReparacion"));
					t.setFechaHastaReparacion(rs.getDate("fechaHastaReparacion"));
					t.setDireccion(rs.getString("direccionTaller"));
					talleres.add(t);
				}	
		} catch (SQLException e) {
				e.printStackTrace();
		}
		finally{
				try{
					if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
					DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
						sqle.printStackTrace();
				}
		}

		return talleres;
				
	}

//====================================================================================
	
}
