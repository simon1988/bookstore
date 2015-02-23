package com.nxm.bookstore.service;

import java.util.Collection;

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
	private CustomerService customerService;
	@Autowired
	private IBookDao bookDao;
	
	@Cacheable(value="bookCache", key="#root.methodName+'-'+#bookId")
	@Transactional(readOnly=true)
	public Book getBookById(int bookId){
		return bookDao.getBookById(bookId);
	}
	@Cacheable(value="bookCache", key="'AllBooks'")
	@Transactional(readOnly=true)
	public Collection<Book> getAllBooks(){
		return bookDao.getBooks();
	}
	@Cacheable(value="bookCache", key="'orders-'+#username")
	@Transactional(readOnly=true)
	public Collection<Order> getOrdersByCustomerName(String username){
		return bookDao.getOrdersByCustomerId(customerService.getCustomerByName(username).getId());
	}
	@CacheEvict(value="bookCache",key="'orders-'+#username")
	@Transactional
	public String placeNewOrder(String username){
		Customer customer = customerService.getCustomerByName(username);
		Collection<Book> books = bookDao.getCartBooks(customer.getId());
		if(books.isEmpty()){
			return "Your cart is Empty!";
		}
		double price = 0;
		for(Book book : books){
			price = price + book.getPrice();
		}
		double balance = customer.getBalance()-price;
		if(balance<0){
			return "You don't have enough money!";
		}
		Order order = new Order();
		order.setCustomer(customer);
		bookDao.generateOrderId(order);
		for(Book book : books){
			bookDao.placeNewOrder(order.getId(), book.getId());
			bookDao.deleteBookFromCart(customer.getId(), book.getId());
		}
		customerService.updateCustomerBalance(username, balance);
		return "Success";
	}
	@Cacheable(value="bookCache", key="'cart-'+#username")
	@Transactional(readOnly=true)
	public Collection<Book> getCartBooks(String username){
		return bookDao.getCartBooks(customerService.getCustomerByName(username).getId());
	}
	@CacheEvict(value="bookCache", key="'cart-'+#username")
	@Transactional
	public boolean addBookToCart(String username, int bookId){
		for(Book book : bookDao.getCartBooks(customerService.getCustomerByName(username).getId())){
			if(book.getId()==bookId){
				return false;
			}
		}
		bookDao.addBookToCart(customerService.getCustomerByName(username).getId(), bookId);
		return true;
	}
	@CacheEvict(value="bookCache", key="'cart-'+#username")
	@Transactional
	public void deleteBookFromCart(String username, int bookId){
		bookDao.deleteBookFromCart(customerService.getCustomerByName(username).getId(), bookId);
	}
}
