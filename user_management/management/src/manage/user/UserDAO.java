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
		String url = "jdbc:mysql://localhost:8888/management";
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
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, user.getUserId());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		
		pstmt.executeUpdate();
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT * FROM users WHERE userId=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			User user = new User(
					rs.getString("userId"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("name"));
			return user;
		}
		return null;
	}

	public void removeUser(String userId) throws SQLException {
		String sql = "DELETE FROM users WHERE userId=?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		pstmt.setString(1, userId);
		
		pstmt.executeUpdate();
	}
}
