package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Member;

@Component(value="memberDao")
public class MySqlMemberDao implements MemberDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Member> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT mno, mname, email, cre_date, mod_date FROM members ORDER BY mno ASC"
					);
			ArrayList<Member> members = new ArrayList<Member>();
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt("mno"))
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"))
						.setCreatedDate(rs.getDate("cre_date"))
						.setModifiedDate(rs.getDate("mod_date")));
			}
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {} 
		}
	}
	
	public int insert(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			
			stmt = connection.prepareStatement("INSERT INTO members(EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE) VALUES(?, ?, ?, NOW(), NOW())");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getName());
			stmt.executeUpdate();
			return 0;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}
	
	public Member selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT mno, email, mname, cre_date FROM members where mno=" + no);
			rs.next();
			
			Member member = new Member()
					.setNo(rs.getInt("mno"))
					.setEmail(rs.getString("email"))
					.setName(rs.getString("mname"))
					.setCreatedDate(rs.getDate("cre_date"));
			return member;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}
	
	public int update(Member member) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"UPDATE members SET email=?, mname=?, mod_date=NOW() WHERE mno=?" 
					);
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getName());
			stmt.setInt(3, member.getNo());
			stmt.executeUpdate();
			return 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}
	
	public int delete(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate("DELETE FROM members WHERE mno=" + no);
			return 0;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}
	
	public Member exist(String email, String password) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"SELECT mname, email FROM members WHERE email=? AND pwd=?"
					);
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			
			Member member = null;
			if(rs.next()) {
				member = new Member()
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"));
			}
			
			return member;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e) {}
			try { if(stmt != null) stmt.close(); } catch (Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}
}
