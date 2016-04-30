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
import entidad.Descuento;
import util.DataConnectionManager;

public class CatalogoDescuentos {

//====================================================================================
	
	public int obtenerPorcentaje(Date fechaDesde, Date fechaHasta){
				
		int porcentaje = 1;
		String sql = "select d.descuento as descuento from descuentos d where (d.diaDesde<=(select datediff(?,?))) and (d.diaHasta>=(select datediff(?,?)));";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setDate(1,fechaHasta);
				sentencia.setDate(2,fechaDesde);
				sentencia.setDate(3,fechaHasta);
				sentencia.setDate(4,fechaDesde);
				rs = sentencia.executeQuery();

				while(rs.next()){
						porcentaje = rs.getInt("descuento");
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

		return porcentaje;
				
	}

//====================================================================================
	
	public ArrayList<Descuento> getDescuentos(){
		
		ArrayList<Descuento> descuentos = new ArrayList<Descuento>();
		
		String sql="select * from descuentos";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

		try {	
			sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = sentencia.executeQuery();

				while(rs.next()){
					Descuento d= new Descuento();
					d.setDescuento(rs.getInt("descuento"));
					d.setDiaDesde(rs.getInt("diaDesde"));
					d.setDiaHasta(rs.getInt("diaHasta"));
					descuentos.add(d);
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

		return descuentos;
				
	}

//====================================================================================
	
}
