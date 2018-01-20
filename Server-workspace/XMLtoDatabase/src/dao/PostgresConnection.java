package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

	private final String url = "jdbc:postgresql://localhost:5432/ark";
	private final String user = "postgres";
	private final String password = "postgres";
	
	public Connection connect(){
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return conn;
	}
	
}
