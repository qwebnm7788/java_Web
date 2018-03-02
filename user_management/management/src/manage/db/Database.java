package manage.db;

import java.util.HashMap;
import java.util.Map;

import manage.user.User;

public class Database {
	private static Map<String, User> users = new HashMap<String, User>();
	
	public static void addUser(User user) {
		users.put(user.getUserId(), user);
		System.out.println(user);
	}

	public static User findByUserId(String userId) {
		return users.get(userId);
	}
}
