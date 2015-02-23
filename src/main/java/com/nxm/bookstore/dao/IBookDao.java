package com.nxm.bookstore.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Order;

public interface IBookDao {
	
	public Book getBookById(@Param("id") int id);
	public Collection<Book> getBooks();
	
	public Collection<Order> getOrdersByCustomerId(@Param("id") int customerId);
	public int generateOrderId(@Param("order") Order order);
	public void placeNewOrder(@Param("o_id") int orderId, @Param("b_id") int bookId);
	
	public void addBookToCart(@Param("c_id") int customerId, @Param("b_id") int bookId);
	public void deleteBookFromCart(@Param("c_id") int customerId, @Param("b_id") int bookId);
	public Collection<Book> getCartBooks(@Param("c_id") int customerId);
}
