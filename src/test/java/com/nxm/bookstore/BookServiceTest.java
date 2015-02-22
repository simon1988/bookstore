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
import com.nxm.bookstore.service.BookService;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {
	static Logger logger = Logger.getLogger(BookServiceTest.class);
	@Autowired
	private BookService bookService;
	@Test
	@Transactional
	public void TestPlaceNewOrder(){
		Collection<Book> books = bookService.getAllBooks();
		logger.info(books);
	}
}
