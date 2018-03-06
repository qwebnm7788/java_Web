package spms.context;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.reflections.Reflections;

import spms.annotation.Component;

public class ApplicationContext {
	Map<String, Object> objTable = new Hashtable<String, Object>();
	
	public Object getBean(String key) {
		return objTable.get(key);
	}
	
	public void addBean(String name, Object obj) {
		objTable.put(name, obj);
	}
	
	//매개변수로 주어진 프로퍼티 파일의 경로를 이용하여 해당 파일 내의 객체를 모두 생성한다.
	public void prepareObjectsByProperties(String propertiesPath) throws Exception {
		Properties props = new Properties();
		props.load(new FileReader(propertiesPath));
		
		Context ctx = new InitialContext();			//jndi객체를 위한 initialContext
		String key = null;
		String value = null;
		
		//프로퍼티에 있는 모든 key를 살펴본다
		for(Object item : props.keySet()) {
			key = (String) item;
			value = props.getProperty(key);
			if(key.startsWith("jndi.")) {
				//jndi객체라면 컨텍스트에서 값을 찾아서 넣어준다 (생성하는것이 아니라)
				objTable.put(key, ctx.lookup(value));
			}else {
				//일반 객체라면 해당 타입의 인스턴스를 생성하여 넣어준다.
				objTable.put(key, Class.forName(value).getConstructor().newInstance());
			}
		}
	}
	
	public void injectDependency() throws Exception {
		for(String key: objTable.keySet()) {
			if(!key.startsWith("jndi.")) {			//jndi이름으로 들어가 있는 친구들은 서버에서 준 객체이므로 의존객체를 주입하면 안된다. (이미 다 설정되어있는 것임)
				callSetter(objTable.get(key));
			}
		}
	}
	
	
	//obj의 모든 setter를 불러서 초기화 시켜준다. (값이 있다면)
	private void callSetter(Object obj) throws Exception {
		Object dependency = null;
		for(Method m : obj.getClass().getMethods()) {
			if(m.getName().startsWith("set")) {
				dependency = findObjectByType(m.getParameterTypes()[0]);
				if(dependency != null) {
					m.invoke(obj, dependency);
				}
			}
		}
	}
	
	//type 타입의 값을 objTable에서 찾아서 반환한다.
	private Object findObjectByType(Class<?> type) {
		for(Object obj : objTable.values()) {
			if(type.isInstance(obj)) {			//type과 같은 타입의 값?
				return obj;
			}
		}
		return null;
	}
	
	//basePackage부터 Component 애노테이션이 달린 클래스를 검색한다.
	public void prepareObjectsByAnnotation(String basePackage) throws Exception {
		Reflections reflector = new Reflections(basePackage);
		
		Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);			//Component 컴포넌트가 붇은 클래스 타입을 리스트로 반환한다.
		String key = null;
		
		for(Class<?> clazz : list) {
			key = clazz.getAnnotation(Component.class).value();                        //value속성의 값을 받아옴
			objTable.put(key, clazz.getConstructor().newInstance());									   //해당 클래스의 객체를 생성하여 테이블에 넣어준다.
		}
	}
}
