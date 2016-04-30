package negocio.datos;

import java.sql.*;
import java.util.ArrayList;

import entidad.Alquiler;
import entidad.Usuario;
import entidad.Vehiculo;
import util.DataConnectionManager;

public class CatalogoUsuarios {

//====================================================================================
	
	public Usuario getUsuario(String usu, String cont){
		
		String sql="select * from usuarios where (usuarioUsuario=? and contraseñaUsuario=?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		Usuario u = new Usuario();

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,usu);
				sentencia.setString(2,cont);
				rs = sentencia.executeQuery();

				while(rs.next()){
					u.setDni(rs.getInt("dniUsuario"));
					u.setUsuario(rs.getString("usuarioUsuario"));
					u.setContraseña(rs.getString("contraseñaUsuario"));
					u.setNombre(rs.getString("nombreUsuario"));
					u.setTelefono(rs.getInt("telefonoUsuario"));
					u.setRol(rs.getString("rolUsuario"));
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

		return u;
	
	}

//====================================================================================
	
	public String validarUsuario(String usu){
		
		String usuario= "";
		String sql="select * from usuarios where usuarioUsuario=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,usu);
				rs = sentencia.executeQuery();

				while(rs.next()){
					usuario = rs.getString("usuarioUsuario");
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

		return usuario;
		
	}
	
//====================================================================================
	
	public ArrayList<Alquiler> getAlqCliente(String usu){
		
		ArrayList<Alquiler> alquileres= new ArrayList<Alquiler>();
			
		String sql="select * from alquileres where usuarioCliente=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

			try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,usu);
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

	public void insertCliente(Usuario u){
			
		String sql="insert into usuarios (dniUsuario,usuarioUsuario,contraseñaUsuario,nombreUsuario,telefonoUsuario,rolUsuario) values (?,?,?,?,?,?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setInt(1,u.getDni());
				sentencia.setString(2,u.getUsuario());
				sentencia.setString(3,u.getContraseña());
				sentencia.setString(4,u.getNombre());
				sentencia.setInt(5,u.getTelefono());
				sentencia.setString(6,"cliente");
				rs = sentencia.executeQuery();

				int filasAfectadas=sentencia.executeUpdate();
				ResultSet cps= sentencia.getGeneratedKeys();
				if(cps.next()){
						int locId=cps.getInt(1);
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

	}
		
//====================================================================================
	
	public void cambiarContraseña(String usuario, String contNueva){
					
		String sql="update usuarios set contraseñaUsuario=? where usuarioUsuario=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
					
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, contNueva);
				sentencia.setString(2, usuario);
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
