package com.nxm.bookstore.web;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nxm.bookstore.model.Order;
import com.nxm.bookstore.service.BookService;

@Controller
public class HomeController {

	@Autowired
	BookService bookservice;
	
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
