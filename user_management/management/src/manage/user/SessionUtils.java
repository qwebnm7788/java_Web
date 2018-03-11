package manage.user;

import javax.servlet.http.HttpSession;

public class SessionUtils {
	//session에 key라는 이름의 아이디가 존재하지 않으면 true를 반환
	public static boolean isEmpty(HttpSession session, String key) {
		Object object = session.getAttribute(key);
		if(object == null) {
			return true;
		}
		return false;
	}
	
	public static String getStringValue(HttpSession session, String key) {
		if(isEmpty(session, key)) {
			return null;
		}
		return (String)session.getAttribute(key);
	}
}
