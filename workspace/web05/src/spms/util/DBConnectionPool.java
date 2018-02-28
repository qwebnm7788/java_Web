package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	String url;
	String username;
	String password;
	ArrayList<Connection> connList = new ArrayList<Connection>();
	public DBConnectionPool(String driver, String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		
		try {
			Class.forName(driver);
		} catch (Exception e) {
			
		}
	}
	
	public Connection getConnection() throws Exception {
		if(connList.size() > 0) {
			Connection conn = connList.get(0);
			if(conn.isValid(10)) {
				return conn;
			}
		}
		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws Exception {
		connList.add(conn);
	}
	
	public void closeAll() {
		for(Connection conn : connList) {
			try { if(conn != null) conn.close(); } catch(Exception e) {}
		}
	}
}
