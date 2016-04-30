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

public class CatalogoVehiculos {
	
//====================================================================================	
	
	public String getVehiculo(String nroPat){
		
		String nroPatente = "";
		String sql="select nroPatente from vehiculos v where v.nroPatente=?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nroPat);
				rs = sentencia.executeQuery();
				
				while(rs.next()){
					nroPatente = rs.getString("nroPatente");
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
		
		return nroPatente;

	}
	
//====================================================================================	
	
	public ArrayList<Vehiculo> getVehiculosDisp(int idCat, Date fechaDesde, Date fechaHasta) {
		
		ArrayList<Vehiculo> vehiculos= new ArrayList<Vehiculo>();

		String sql= "select ve.nroPatente, ve.modeloVehiculo from vehiculos ve where ve.idCategoria=? and ve.nroPatente not in ("
						+ "select distinct v.nroPatente from vehiculos v "
						+ "inner join alquileres a on a.nroPatente=v.nroPatente "
						+ "where v.idCategoria=1 "
						+ "and ( a.estadoAlquiler='agendado' or a.estadoAlquiler='en curso' or a.estadoAlquiler='finalizado') "
						+ "and a.fechaDesdeAlquiler <= ? and a.fechaHastaAlquiler >= ? "
						+ "union "
						+ "select v.nroPatente from vehiculos v "
						+ "inner join reparaciones r on r.nroPatente=v.nroPatente "
						+ "where v.idCategoria=? "
						+ "and r.fechaDesdeReparacion <= ? and r.fechaHastaReparacion >= ?"
				+ ");";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setInt(1,idCat);
				sentencia.setDate(2,fechaHasta);
				sentencia.setDate(3,fechaDesde);
				sentencia.setInt(4, idCat);
				sentencia.setDate(5,fechaHasta);
				sentencia.setDate(6,fechaDesde);
				rs = sentencia.executeQuery();

				while(rs.next()){
					Vehiculo v = new Vehiculo();
					v.setNroPatente(rs.getString("nroPatente"));
					v.setModeloVehiculo(rs.getString("modeloVehiculo"));
					vehiculos.add(v);
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


		return vehiculos;
		
	}

//====================================================================================

	public Vehiculo getVehiculoDisp(String nroPat, Date fechaDesde, Date fechaHasta) {
		
		String sql= "select nroPatente from vehiculos where nroPatente=? and nroPatente=? "
				+ "not in (select a.nroPatente from alquileres a "
				+ "where (a.estadoAlquiler='agendado' or a.estadoAlquiler='en curso' or a.estadoAlquiler='finalizado') "
				+ "and a.nroPatente=? "
				+ "and a.fechaDesdeAlquiler <= ? and a.fechaHastaAlquiler >= ? "
				+ "union "
				+ "select r.nroPatente from reparaciones r "
				+ "where r.nroPatente=? "
				+ "and (r.fechaDesdeReparacion <= ? and r.fechaHastaReparacion >= ?));";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		Vehiculo v = new Vehiculo();
		
		try {	
			sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1,nroPat);
			sentencia.setString(2,nroPat);
			sentencia.setString(3,nroPat);
			sentencia.setDate(4,fechaHasta);
			sentencia.setDate(5,fechaDesde);
			sentencia.setString(6,nroPat);
			sentencia.setDate(7,fechaHasta);
			sentencia.setDate(8,fechaDesde);
			rs = sentencia.executeQuery();
			
			while(rs.next()){
				v.setNroPatente(rs.getString("nroPatente"));
				
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


		return v;
		
	}

//====================================================================================
	
	public void insertVehiculo(String nroPat, int idCat){
				
		String sql="insert into vehiculos values (?,?)";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nroPat.toUpperCase());
				sentencia.setInt(2,idCat);
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

	public boolean validarReparacion(String nroPat, Date fechaDesde, Date fechaHasta){
		
		Reparacion r = new Reparacion();
		
		String sql = "select v.nroPatente,a.fechaDesdeAlquiler,a.fechaHastaAlquiler from vehiculos v "
				+ "inner join alquileres a on v.nroPatente=a.nroPatente "
				+ "WHERE v.nroPatente=? AND (a.fechaDesdeAlquiler < ? AND a.fechaHastaAlquiler > ?) "
				+ "or v.nroPatente not in  (select a.nroPatente from alquileres a) group by v.nroPatente "
				+ "union "
				+ "select v.nroPatente,r.fechaDesdeReparacion,r.fechaHastaReparacion from vehiculos v "
				+ "inner join reparaciones r on v.nroPatente=r.nroPatente "
				+ "WHERE v.nroPatente=? AND (r.fechaDesdeReparacion < ? AND r.fechaHastaReparacion > ?) "
				+ "or v.nroPatente not in  (select r.nroPatente from reparaciones r) group by v.nroPatente;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
		boolean estado = false;

		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nroPat);
				sentencia.setDate(2,fechaHasta);
				sentencia.setDate(3,fechaDesde);
				sentencia.setString(4,nroPat);
				sentencia.setDate(5,fechaHasta);
				sentencia.setDate(6,fechaDesde);
				rs = sentencia.executeQuery();

				while(rs.next()){
					r.setNroPatente(rs.getString("nroPatente"));
					r.setFechaDesdeReparacion(rs.getDate("fechaDesdeAlquiler"));
					r.setFechaHastaReparacion(rs.getDate("fechaHastaAlquiler"));
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
		
		if (r.getNroPatente() == null) {estado = true;}

		return estado;

	}

//====================================================================================
	
	public ArrayList<Vehiculo> getVehiculos(){
					
		ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		
		String sql="select * from vehiculos";
		Statement sentencia=null;
		ResultSet rs=null;

			try {	
				sentencia= DataConnectionManager.getInstancia().getConn().createStatement();
				rs= sentencia.executeQuery(sql);

				while(rs.next()){
					Vehiculo v= new Vehiculo();
					v.setNroPatente(rs.getString("nroPatente"));
					v.setModeloVehiculo(rs.getString("modeloVehiculo"));
					v.setIdCategoria(rs.getInt("idCategoria"));
					vehiculos.add(v);
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

		return vehiculos;
					
	}

//====================================================================================
	
	public void cambiarModelo(String nroPatente, String modelo){
						
		String sql="update vehiculos set modeloVehiculo=? where nroPatente=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
						
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, modelo);
				sentencia.setString(2, nroPatente);
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
	
	public String validarEliminacion(String nroPat){
			
		String nroPatente = "";
		String sql="select v.nroPatente from vehiculos v "
				+ "left join alquileres a on a.nroPatente=v.nroPatente "
				+ "left join reparaciones r on a.nroPatente=v.nroPatente "
				+ "where (a.fechaDesdeAlquiler>now() or r.fechaDesdeReparacion>now()) "
				+ "and (a.estadoAlquiler='agendado' or a.estadoAlquiler='en curso' or a.estadoAlquiler='finalizado') "
				+ "and v.nroPatente=? "
				+ "union "
				+ "select v.nroPatente from vehiculos v "
				+ "left join alquileres a on a.nroPatente=v.nroPatente "
				+ "left join reparaciones r on a.nroPatente=v.nroPatente "
				+ "where a.fechaDesdeAlquiler is null && r.fechaDesdeReparacion is null "
				+ "and v.nroPatente=?;";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
		ResultSet rs=null;
			
		try {	
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1,nroPat);
				sentencia.setString(2,nroPat);
				rs = sentencia.executeQuery();
					
				while(rs.next()){
					nroPatente = rs.getString("nroPatente");
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
			
		return nroPatente;

	}
		
//====================================================================================
	
	public void eliminarVehiculo(String nroPatente){
							
		String sql="delete from vehiculos where nroPatente=?";
		PreparedStatement sentencia=null;
		Connection conn=DataConnectionManager.getInstancia().getConn();
							
		try {
				sentencia=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				sentencia.setString(1, nroPatente);
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
	
}
