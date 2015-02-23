package com.nxm.bookstore.dao.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nxm.bookstore.dao.IBookDao;
import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.model.Order;

@Repository
public class HibernateBookDao implements IBookDao {
	static Logger logger = Logger.getLogger(HibernateBookDao.class);
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public Book getBookById(int id) {
		logger.info(sessionFactory.getCurrentSession().get(Customer.class, 1));
		logger.info(sessionFactory.getCurrentSession().get(Book.class, 1));
		return null;
	}

	@Override
	public Collection<Book> getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Order> getOrdersByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBookToCart(int customerId, int bookid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBookFromCart(int customerId, int bookid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Book> getCartBooks(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeNewOrder(int orderId, int bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int generateOrderId(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
