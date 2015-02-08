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
	
	@Cacheable(value="all_orders")
	@Transactional(readOnly=true)
	public Collection<Order> getAllOrders(){
		return bookDao.getAllOrders();
	}
	@Cacheable(value="getOrderFromId", key="#orderId + 'test'")
	@Transactional(readOnly=true)
	public Order getOrderFromId(int orderId){
		return bookDao.getOrderFromId(orderId);
	}
	@CacheEvict(value="andCache",allEntries=true)
	@Transactional
	public void placeNewOrder(Customer c, Set<Book> books){
		bookDao.placeNewOrder(c, books);
	}
}
