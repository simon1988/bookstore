package com.nxm.bookstore;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.service.BookService;

@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	static Logger logger = Logger.getLogger(BookServiceTest.class);
	@Autowired
	private BookService bookService;
	@Test
	@Transactional
	public void TestPlaceNewOrder(){
		Customer c = new Customer();
		c.setId(3);
		Book book1 = new Book();
		book1.setId(1);
		Book book2 = new Book();
		book2.setId(2);
		Set<Book> set = new HashSet<Book>();
		set.add(book1);
		set.add(book2);
		bookService.placeNewOrder(c, set);
		assertNotNull(bookService.getOrderFromId(1004));
		logger.info(bookService.getOrderFromId(1004));
	}
}
