package negocio.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Alquiler;
import entidad.Taller;
import util.DataConnectionManager;
import validaciones.PropiasExceptions;

public class CatalogoAlquileres {

//====================================================================================	
	
	public ArrayList<Alquiler> getAlquileres(){
			
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
			
		String sql="select * from alquileres a order by a.fechaHastaAlquiler desc";
		Statement sentencia=null;
		ResultSet rs=null;

			try {	
				sentencia= DataConnectionManager.getInstancia().getConn().createStatement();
				rs= sentencia.executeQuery(sql);

				while(rs.next()){
					Alquiler a= new Alquiler();
					a.setNroAlquiler(rs.getInt("nroAlquiler"));
					a.setUsuarioCliente(rs.getString("usuarioCliente"));
					a.setNroPatente(rs.getString("nroPatente"));
					a.setImporte(rs.getFloat("importe"));
					a.setFechaDesdeAlquiler(rs.getDate("fechaDesdeAlquiler"));
					a.setFechaHastaAlquiler(rs.getDate("fechaHastaAlquiler"));
					a.setEstadoAlquiler(rs.getString("estadoAlquiler"));
					alquileres.add(a);
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

		return alquileres;
			
	}

//====================================================================================	
	
	public ArrayList<Alquiler> getVehAlquileres(String nroPat) {
		
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();

		String sql= "select * from alquileres where nroPatente=?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
				
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nroPat);
				rs = sentencia.executeQuery();

				while(rs.next()){
					Alquiler a= new Alquiler();
					a.setNroAlquiler(rs.getInt("nroAlquiler"));
					a.setUsuarioCliente(rs.getString("usuarioCliente"));
					a.setNroPatente(rs.getString("nroPatente"));
					a.setImporte(rs.getFloat("importe"));
					a.setFechaDesdeAlquiler(rs.getDate("fechaDesdeAlquiler"));
					a.setFechaHastaAlquiler(rs.getDate("fechaHastaAlquiler"));
					a.setEstadoAlquiler(rs.getString("estadoAlquiler"));
					alquileres.add(a);
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

		return alquileres;
				
	}

//====================================================================================	
	
		public void insertAlquiler(String usuario, String nroPatente, Float importe, Date fechaDesde, Date fechaHasta){
		
		String sql="insert into alquileres (usuarioCliente,nroPatente,importe,fechaDesdeAlquiler,fechaHastaAlquiler,estadoAlquiler) values (?,?,?,?,?,?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, usuario);
				sentencia.setString(2, nroPatente);
				sentencia.setFloat(3, importe);
				sentencia.setDate(4, fechaDesde);
				sentencia.setDate(5, fechaHasta);
				sentencia.setString(6, "agendado");
				
				int filasAfectadas=sentencia.executeUpdate();
				ResultSet cps= sentencia.getGeneratedKeys();
				if(cps.next()){
						int locId=cps.getInt(1);
						sentencia.setInt(1, locId);
				}

		} catch (SQLException e) {
			throw new PropiasExceptions("Error del Sistema: No se pudo conectar con la base de datos");
		}
		finally{
				try{
						if(sentencia!=null && !sentencia.isClosed()){sentencia.close();}
						DataConnectionManager.getInstancia().CloseConn();
				}
				catch (SQLException sqle){
					throw new PropiasExceptions("Error del Sistema: No se pudo conectar con la base de datos");
				}
		}
	
	}
	
//====================================================================================
	
	public void cambiarEstadoAlq(int nroAlq, String estado){
			
		String sql="update alquileres a set a.estadoAlquiler=? where a.nroAlquiler=?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
			
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, estado);
				sentencia.setInt(2, nroAlq);
				int filasAfectadas=sentencia.executeUpdate();
					
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
	
	public void cancelarAlquiler(int nroAlq){
				
		String sql="update alquileres a set a.estadoAlquiler='cancelado' where a.nroAlquiler=?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
				
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setInt(1, nroAlq);
				int filasAfectadas=sentencia.executeUpdate();
						
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
		
}
