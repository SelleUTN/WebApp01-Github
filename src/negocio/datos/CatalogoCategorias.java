package negocio.datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidad.Alquiler;
import entidad.Categoria;
import entidad.Mecanico;
import entidad.Reparacion;
import entidad.Socio;
import entidad.Taller;
import entidad.Usuario;
import entidad.Vehiculo;
import util.DataConnectionManager;

public class CatalogoCategorias {

//====================================================================================
	
	public float getPrecio(int idCat){
					
		float precioCategoria = 0;
		String sql="select precioCategoria from categorias where idCategoria=?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setInt(1,idCat);
				rs = sentencia.executeQuery();

				while(rs.next()){
					precioCategoria = rs.getFloat("precioCategoria");
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

		return precioCategoria;
					
	}

//====================================================================================	
	
	public Categoria getCategoria(int idCat){
					
		String sql="select * from categorias where idCategoria=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		Categoria c = new Categoria();

			try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setInt(1,idCat);
				rs = sentencia.executeQuery();

			while(rs.next()){
				c.setIdCategoria(rs.getInt("idCategoria"));
				c.setDescripcionCategoria(rs.getString("descripcionCategoria"));
				c.setPrecioCategoria(rs.getFloat("precioCategoria"));
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

		return c;
					
	}

//====================================================================================
	
	public ArrayList<Categoria> getCategorias(){
				
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
				
		String sql="select * from categorias";
		Statement sentencia=null;
		ResultSet rs=null;

			try {	
				sentencia= DataConnectionManager.getInstancia().getConn().createStatement();
				rs= sentencia.executeQuery(sql);

				while(rs.next()){
					Categoria c= new Categoria();
					c.setIdCategoria(rs.getInt("idCategoria"));
					c.setDescripcionCategoria(rs.getString("descripcionCategoria"));
					c.setPrecioCategoria(rs.getFloat("precioCategoria"));
					categorias.add(c);
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

		return categorias;
				
	}

//====================================================================================
	
}
