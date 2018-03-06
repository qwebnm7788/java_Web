package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	FilterConfig config;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.err.println("FILTER INIT()");
		this.config = filterConfig;
	}
	
	//response, request encoding 설정
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		response.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
