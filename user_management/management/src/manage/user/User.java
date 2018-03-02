package manage.user;

import manage.db.Database;

public class User {
	private String userId;
	private String password;
	private String email;
	private String name;
	
	public User(String userId, String password, String email, String name) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public boolean matchPassword(String password) {
		return password.equals(this.password);
	}
	
	public static boolean login(String userId2, String password2) throws UserNotFoundException, PasswordMismatchException {
		User user = Database.findByUserId(userId2);
		if(user == null) {
			throw new UserNotFoundException();
		}
		if(!user.matchPassword(password2)) {
			throw new PasswordMismatchException();
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return userId + " "  + password + " " + email + " " + name;
	}

}
