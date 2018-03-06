package spms.listeners;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import spms.context.ApplicationContext;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	static ApplicationContext applicationContext;			//프런트 컨트롤러에서 테이블을 사용할 수 있도록
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener init()...");
		try {
			applicationContext = new ApplicationContext();			//기본 생성자 이용
			
			String resource = "spms/dao/mybatis-config.xml";			//mybatis 설정파일 경로
			InputStream inputStream = Resources.getResourceAsStream(resource);			//설정파일의 입력스트림을 가져옴
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);		//설정파일 입력스트림을 이용하여 sqlSessionFactory 생성
			
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);				//sqlSessionFactory는 따로 생성하여 추가
			
			ServletContext sc = sce.getServletContext();
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));			//프로퍼티 파일 경로 획득
			
			applicationContext.prepareObjectsByProperties(propertiesPath);			//프로퍼티 파일 내의 객체 준비
			applicationContext.prepareObjectsByAnnotation("");						//애노테이션이 달린 객체 준비
		
			applicationContext.injectDependency();                               //생성된 객체들의 의존객체 주입
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ContextLoaderListener destroy()...");
	}
}
