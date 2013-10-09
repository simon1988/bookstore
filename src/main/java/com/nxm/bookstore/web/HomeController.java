package com.nxm.bookstore.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nxm.bookstore.service.BookService;

@Controller
public class HomeController {

	@Autowired
	BookService bookservice;
	
	@RequestMapping("/")
	public String showHomePage(Map<String,Object> model){
		model.put("owner", "Mr Niu");
		model.put("all_orders", bookservice.getAllOrders());
		return "welcome";
	}
}
