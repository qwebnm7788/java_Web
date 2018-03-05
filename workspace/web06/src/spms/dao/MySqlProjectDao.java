package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import spms.annotation.Component;
import spms.vo.Project;

@Component(value="projectDao")
public class MySqlProjectDao implements ProjectDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public List<Project> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"SELECT pno, pname, sta_date, end_date, state FROM projects ORDER BY cre_date DESC"
					);
			List<Project> projects = new ArrayList<Project>();
			while(rs.next()) {
				projects.add(new Project()
						.setNo(rs.getInt("pno"))
						.setTitle(rs.getString("pname"))
						.setStartDate(rs.getDate("sta_date"))
						.setEndDate(rs.getDate("end_date"))
						.setState(rs.getInt("state")));
			}
			return projects;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}

	@Override
	public int insert(Project project) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			
			stmt = connection.prepareStatement("INSERT INTO projects(PNAME, CONTENT, STA_DATE, END_DATE, STATE, CRE_DATE, TAGS)"
					+ "VALUES(?, ?, ?, ?, 0, NOW(), ?)");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setString(5, project.getTags());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e; 
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}

	@Override
	public Project selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM projects WHERE PNO=" + no);
			rs.next();
			
			Project project = new Project()
					.setNo(rs.getInt("pno"))
					.setTitle(rs.getString("pname"))
					.setContent(rs.getString("content"))
					.setStartDate(rs.getDate("sta_date"))
					.setEndDate(rs.getDate("end_date"))
					.setState(rs.getInt("state"))
					.setTags(rs.getString("tags"));
					
			return project;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch(Exception e) {}
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}

	@Override
	public int update(Project project) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("UPDATE projects SET pname=?, content=?, sta_date=?, end_date=?, state=?, tags=? WHERE pno=?");
			stmt.setString(1, project.getTitle());
			stmt.setString(2, project.getContent());
			stmt.setDate(3, new java.sql.Date(project.getStartDate().getTime()));
			stmt.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
			stmt.setInt(5, project.getState());
			stmt.setString(6, project.getTags());
			stmt.setInt(7, project.getNo());
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}

	@Override
	public int delete(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate("DELETE FROM projects WHERE pno=" + no);
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch(Exception e) {}
			try { if(connection != null) connection.close(); } catch(Exception e) {}
		}
	}
}