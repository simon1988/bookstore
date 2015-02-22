package com.nxm.bookstore.dao.impl;

import java.util.Collection;
import java.util.Set;

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
	public Collection<Order> getAllOrders() {
		logger.info(sessionFactory.getCurrentSession().get(Customer.class, 1));
		logger.info(sessionFactory.getCurrentSession().get(Book.class, 1));
		return null;
	}

	@Override
	public Order getOrderFromId(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeNewOrder(Customer c, Set<Book> books) {
		// TODO Auto-generated method stub

	}

	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Book> getBooks() {
		// TODO Auto-generated method stub
		return null;
	}

}
