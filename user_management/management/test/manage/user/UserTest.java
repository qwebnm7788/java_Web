package manage.user;

import static org.junit.Assert.*;

import org.junit.Test;

import manage.db.Database;

public class UserTest {
	public static User TEST_USER = new User("userId", "password", "email", "name");
	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}

	@Test
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("password2"));
	}
	
	@Test
	public void login() throws Exception {
		Database.addUser(TEST_USER);
		assertTrue(TEST_USER.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception {
		Database.addUser(TEST_USER);
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		Database.addUser(TEST_USER);
		User.login(TEST_USER.getUserId(), "password2");
	}
}
