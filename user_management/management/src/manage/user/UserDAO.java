package manage.user;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class UserDAO {

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:8888/management?useSSL=false";
		String id = "study";
		String password = "study";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, password);	
		} catch (Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public void addUser(User user) throws SQLException {
		String sql = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			
			pstmt.executeUpdate();			
		} finally {
			if(pstmt != null) {
				pstmt.close();	
			}
			if(conn != null) {
				conn.close();
			}
		}
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT * FROM users WHERE userId=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("email"),
						rs.getString("name"));
				return user;
			}else {
				return null;
			}
		}
		finally {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					rs.close();
				}
		}
	}

	public void removeUser(String userId) throws SQLException {
		String sql = "DELETE FROM users WHERE userId=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.executeUpdate();	
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}

	public void updateUser(User user) throws SQLException {
		String sql = "UPDATE users SET password=?, name=?, email=? where userId=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getEmail());
		pstmt.setString(4, user.getUserId());
		
		pstmt.executeUpdate();
	}
}
