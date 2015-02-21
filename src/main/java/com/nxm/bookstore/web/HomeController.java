package com.nxm.bookstore.web;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nxm.bookstore.model.Order;
import com.nxm.bookstore.service.BookService;
import com.nxm.bookstore.util.MD5Util;

@Controller
public class HomeController {
	static Logger logger=Logger.getLogger(HomeController.class);
	
	@Autowired
	BookService bookservice;

	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/tmp")
	public String tmp(){
		return "tmp";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/login.do")
	public void loginDo(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fromURL = request.getParameter("fromurl");
		if(username.equals(password)){
			//write to cookie
			Cookie cookie = new Cookie("username", username);
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			cookie = new Cookie("auth", "encrypt"+password);
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			
			logger.info("fromurl:"+fromURL);
			if(fromURL==null||fromURL==""){
				fromURL="index";
			}
		}else{
			fromURL="login";
		}
		try {
			response.sendRedirect(fromURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/booklist")
	public String showHomePage(Map<String,Object> model){
		model.put("owner", "Mr Niu");
		model.put("all_orders", bookservice.getAllOrders());
		return "welcome";
	}
	
	@RequestMapping("/ajax/getAllOrders.do")
	public @ResponseBody Collection<Order> getAllOrders(){
		return bookservice.getAllOrders();
	}
	
	@RequestMapping(value="/user/{userId}/roles/{roleId}",method = RequestMethod.GET)  
    public String getLogin(@PathVariable("userId") String userId,  
        @PathVariable("roleId") String roleId){  
        System.out.println("User Id : " + userId);  
        System.out.println("Role Id : " + roleId);  
        return "hello";  
    } 
}
