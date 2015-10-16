package com.nxm.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nxm.util.RegexUtil;

public class LoginFilter implements Filter {

	private static final String[] LOGIN_REQUIRED={"/orders/*","/cart/*"};
	private static final String[] LOGIN_HINT={"/","/index","/book/.*"};
	private static final String LOGIN_PAGE="/login";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	private boolean isLoggedin(HttpServletRequest httpRequest){
		if(httpRequest.getCookies()==null){
			return false;
		}
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
			return true;
		}
		return false;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		if(LOGIN_PAGE.equals(httpRequest.getRequestURI())){
			if(isLoggedin(httpRequest)){
				String fromURL = request.getParameter("fromurl");
				if(fromURL==null||fromURL==""){
					fromURL="index";
				}
				httpResponse.sendRedirect(fromURL);
				return;
			}
		}else if(RegexUtil.matchAny(httpRequest.getRequestURI(), LOGIN_REQUIRED)){
			if(!isLoggedin(httpRequest)){
				httpResponse.sendRedirect("/login?fromurl=index");
				return;
			}
		}else if(RegexUtil.matchAny(httpRequest.getRequestURI(), LOGIN_HINT)){
			isLoggedin(httpRequest);
		}
		httpRequest.setAttribute("currentURI", httpRequest.getRequestURI());
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
