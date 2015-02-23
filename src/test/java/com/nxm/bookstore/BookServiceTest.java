package com.nxm.bookstore;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Order;
import com.nxm.bookstore.service.BookService;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	static Logger logger = Logger.getLogger(BookServiceTest.class);
	@Autowired
	private BookService bookService;
	@Test
	@Transactional
	public void TestBooksOrders(){
		Collection<Book> books = bookService.getAllBooks();
		logger.info(books);
		Collection<Order> orders = bookService.getOrdersByCustomerName("simon");
		logger.info(orders);
	}
	@Test
	@Transactional
	public void TestCart(){
		bookService.addBookToCart("simon", 1);
		bookService.addBookToCart("simon", 2);
		bookService.deleteBookFromCart("simon", 1);
		Collection<Book> books = bookService.getCartBooks("simon");
		logger.info(books);
		bookService.placeNewOrder("simon");
	}
}
