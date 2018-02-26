package lesson03.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorld implements Servlet {
	ServletConfig config;
	
	@Override
	public void destroy() {
		System.out.println("destroy() is called");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig() is called");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo() is called");
		return "version=1.0;author=jaewonchoi;copyright=jaewonchoi 2018";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() is called");
		this.config = config;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service() is called");
	}

}