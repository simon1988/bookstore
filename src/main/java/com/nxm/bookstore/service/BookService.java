package com.nxm.bookstore.service;

import java.util.Collection;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.dao.IBookDao;
import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.model.Order;

@Service
public class BookService {
	static Logger logger=Logger.getLogger(BookService.class);
	@Autowired
	private IBookDao bookDao;
	
	

	@Cacheable(value="bookCache", key="#bookId + 'test'")
	@Transactional(readOnly=true)
	public Book getBookById(int bookId){
		return bookDao.getBookById(bookId);
	}
	@Cacheable(value="bookCache")
	@Transactional(readOnly=true)
	public Collection<Book> getAllBooks(){
		return bookDao.getBooks();
	}
	@CacheEvict(value="bookCache",allEntries=true)
	@Transactional
	public void placeNewOrder(Customer c, Set<Book> books){
		bookDao.placeNewOrder(c, books);
	}
}
