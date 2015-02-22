package com.nxm.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.model.Order;

public interface IBookDao {
	
	public Book getBookById(@Param("id") int id);
	public Collection<Book> getBooks();
	public Collection<Order> getAllOrders();
	public Order getOrderFromId(int orderId);
	public void placeNewOrder(Customer c, Set<Book> books);
}
