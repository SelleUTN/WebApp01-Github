package util;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataConnectionManager {
	
	private static DataConnectionManager instancia;
	public static DataConnectionManager getInstancia(){
			if(instancia==null){
					instancia=new DataConnectionManager();
			}
			return instancia;
	}


	private static String dbUrl="jdbc:mysql://localhost:3306/Alquiler";
	private static String dbUser="java";
	private static String dbPassword="";
	
	/*private static String dbUrl="jdbc:mysql://mariadb14484-webapp01java.underjelastic.com.br/Alquiler";
	private static String dbUser="root";
	private static String dbPassword="";*/

	private DataConnectionManager(){}

	private Connection conn;

	public Connection getConn(){
		
			try {
					if(conn==null || !conn.isValid(3)){
						
							Class.forName("com.mysql.jdbc.Driver").newInstance();
						
							conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);	
					}
			} catch (InstantiationException e) {
					e.printStackTrace();
			} catch (IllegalAccessException e) {
					e.printStackTrace();
			} catch (ClassNotFoundException e) {
					e.printStackTrace();
			} catch (SQLException e) {
					e.printStackTrace();
			}

			return conn;
	}


	public void CloseConn(){
			try {
					if(conn!=null && !conn.isClosed()){
							conn.close();
					}
			} catch (SQLException e) {
					e.printStackTrace();
			}
	}

}
