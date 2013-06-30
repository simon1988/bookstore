package com.nxm.bookstore.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nxm.bookstore.model.Order;
import com.nxm.bookstore.service.BookService;
public class Test {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		BookService bookService= context.getBean("bookService",BookService.class);
		if(bookService.getAllOrders()!=null)
		for(Order order : bookService.getAllOrders()){
			System.out.println(order);
		}
	}

}
