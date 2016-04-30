package negocio.datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Alquiler;
import util.DataConnectionManager;

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
	
	public void insertAlquiler(Alquiler alq){
		
		String sql="insert into alquileres (usuarioCliente,nroPatente,importe,fechaDesdeAlquiler,fechaHastaAlquiler,estadoAlquiler) values (?,?,?,?,?,?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, alq.getUsuarioCliente());
				sentencia.setString(2, alq.getNroPatente());
				sentencia.setFloat(3, alq.getImporte());
				sentencia.setDate(4, alq.getFechaDesdeAlquiler());
				sentencia.setDate(5, alq.getFechaHastaAlquiler());
				sentencia.setString(6, "agendado");
				
				int filasAfectadas=sentencia.executeUpdate();
				ResultSet cps= sentencia.getGeneratedKeys();
				if(cps.next()){
						int locId=cps.getInt(1);
						alq.setNroAlquiler(locId);
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
