package manage.user;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;


public class UserDAOTest {
	private UserDAO userDao;

	@Before
	public void setup() {
		userDao = new UserDAO();
	}
	
	@Test
	public void connection() throws SQLException {
		Connection conn = userDao.getConnection();
		assertNotNull(conn);
	}

	@Test
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		User dbUser = userDao.findByUserId(user.getUserId());
		assertEquals(UserTest.TEST_USER, dbUser);
		
	} 
	@Test
	public void ��������_�ʴ�_�����_��ȸ() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}
}
