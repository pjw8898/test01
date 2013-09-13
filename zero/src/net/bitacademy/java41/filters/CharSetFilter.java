package net.bitacademy.java41.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharSetFilter implements Filter {
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	
	@Override
	public void doFilter(
			ServletRequest request, ServletResponse response,
			FilterChain next) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		
		next.doFilter(request, response);
		
		
		
	}
	
	@Override
	public void destroy() {}

	

	

}
