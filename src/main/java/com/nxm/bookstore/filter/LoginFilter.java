package com.nxm.bookstore.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	private static final String[] LOGIN_URLS={"/login"};
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if(Arrays.asList(LOGIN_URLS).contains(httpRequest.getRequestURI())&&httpRequest.getCookies()!=null){
			String username = "";
			String auth = "";
			for(Cookie cookie:httpRequest.getCookies()){
				if(cookie.getName().equals("username")){
					username = cookie.getValue();
				}else if(cookie.getName().equals("auth")){
					auth = cookie.getValue().replace("encrypt", "");
				}
			}
			if(username.length()>0&&username.equals(auth)){
				httpRequest.setAttribute("username", username);
				if(httpRequest.getRequestURI().equals("/login")){
					String fromURL = request.getParameter("fromurl");
					if(fromURL==null||fromURL==""){
						fromURL="index";
					}
					httpResponse.sendRedirect(fromURL);
					return;
				}
			}else{
				if(!httpRequest.getRequestURI().equals("/login")){
					httpResponse.sendRedirect("login?fromurl="+httpRequest.getRequestURI());
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
