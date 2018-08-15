package br.com.jgm.api.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection con;

	@SuppressWarnings("deprecation")
	public static Connection getConnection(String url, String server, String database, String usuario, String senha) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if(ConnectionFactory.con == null){
			Class.forName("sybase.jdbc.sqlanywhere.IDriver").newInstance();
			con =DriverManager.getConnection("jdbc:sqlanywhere:uid="+usuario+";pwd="+senha+";eng="+server+";database="+database+";links=tcpip(host="+url+")");
		}
		return ConnectionFactory.con; 
	}
}
