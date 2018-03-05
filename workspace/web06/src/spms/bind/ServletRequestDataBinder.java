package spms.bind;

import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Set;

import javax.servlet.ServletRequest;

public class ServletRequestDataBinder {
	//dataName에 일치하는 
	public static Object bind(ServletRequest request, Class<?> dataType, String dataName) throws Exception {
		if(isPrimitiveType(dataType)) {
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		//dataType에 맞는 객체를 찾은 후
		//인스턴스를 생성하여 해당 객체의 세터메서드의 파라미터와 일치하는 값을 request에서 찾아 그 값으로 초기화 시켜준다.
		Set<String> paramNames = request.getParameterMap().keySet();		//모든 key값을 얻어온다.
		Object dataObject = dataType.getConstructor().newInstance();			//해당 타입의 인스턴스 생성 (기본생성자 이용)
		Method m = null;			//reflect API 이용
		
		for(String paramName : paramNames) {
			m = findSetter(dataType, paramName);			//dataType클래스의 paramName타입의 setter을 얻음
			if(m != null) {
				//dataObject 객체에 대해서 해당 메서드를 호출하는데 넘겨주는 값은 request내에 들어있는 값으로 초기화된 parameter를 넣어준다
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0], request.getParameter(paramName)));
			}
		}
		//dataObject에 가능한 모든 값이 차게 된다. (request에 들어있는 모든 값을 이용하여)
		return dataObject;
	}
	
	//type 타입의 이름은 name인 값의 setter를 모두 찾는다.
	private static Method findSetter(Class<?> type, String name) {
		Method[] methods = type.getMethods();				//type 형태의 클래스에 선언된 모든 메서드를 얻어온다.
		String propName = null;
		for(Method m : methods) {
			if(!m.getName().startsWith("set")) continue;		//setter 아닌것은 제외
			propName = m.getName().substring(3);	//메서드명의 set 이후의 글자를 얻어옴
			if(propName.toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		return null;
	}
	
	//type이 기본형인지 확인
	private static boolean isPrimitiveType(Class<?> type) {
		if(type.getName().equals("int") || type == Integer.class ||
		   type.getName().equals("long") || type == Long.class ||
		   type.getName().equals("float") || type == Float.class ||
		   type.getName().equals("double") || type == Double.class ||
		   type.getName().equals("boolean") || type == Boolean.class ||
		   type == Date.class || type == String.class ) {
			return true;
		}
		return false;
	}
	
	//기본형 type에 대해 value값을 갖는 객체 생성 후 반환
	private static Object createValueObject(Class<?> type, String value) {
		if(type.getName().equals("int") || type == Integer.class) {
			return Integer.valueOf(value);
		}else if(type.getName().equals("long") || type == Long.class) {
			return Long.valueOf(value);
		}else if(type.getName().equals("float") || type == Float.class) {
			return Float.valueOf(value);
		}else if(type.getName().equals("double") || type == Double.class) {
			return Double.valueOf(value);
		}else if(type.getName().equals("boolean") || type == Boolean.class) {
			return Boolean.valueOf(value);
		}else if(type == java.util.Date.class) {
			return java.sql.Date.valueOf(value);
		}else {
			return value;
		}
	}
}
