package com.nxm.bookstore.service;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Order;
import com.nxm.bookstore.service.BookService;
import com.nxm.bookstore.service.QRService;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	private static Logger logger=LoggerFactory.getLogger(BookServiceTest.class);
	@Autowired
	private BookService bookService;
	@Autowired
	private QRService qrService;
	@Test
	@Transactional
	public void TestBooksOrders(){
		Collection<Book> books = bookService.getAllBooks();
		logger.info("books:{}", books);
		Collection<Order> orders = bookService.getOrdersByCustomerName("simon");
		logger.info("orders:{}", orders);
	}
	@Test
	@Transactional
	public void TestCart(){
		bookService.addBookToCart("simon", 1);
		bookService.addBookToCart("simon", 2);
		bookService.deleteBookFromCart("simon", 1);
		Collection<Book> books = bookService.getCartBooks("simon");
		logger.info("books:{}", books);
		bookService.placeNewOrder("simon");
	}
	@Test
	@Ignore
	public void TestQRGenerate(){
		System.out.println(qrService.generateQRImage("http://www.baidu.com"));
	}
}
