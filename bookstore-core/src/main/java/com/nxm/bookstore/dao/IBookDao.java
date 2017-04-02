package com.nxm.bookstore.dao;

import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Order;

public interface IBookDao {
	
	Book getBookById(@Param("id") int id);
	Collection<Book> getBooks();
	Double getBookPriceById(@Param("id") int id);
	
	Collection<Order> getOrdersByCustomerId(@Param("id") int customerId);
	int generateOrderId(@Param("order") Order order);
	void placeNewOrder(@Param("o_id") int orderId, @Param("b_id") int bookId);
	
	void addBookToCart(@Param("c_id") int customerId, @Param("b_id") int bookId);
	void deleteBookFromCart(@Param("c_id") int customerId, @Param("b_id") int bookId);
	Collection<Book> getCartBooks(@Param("c_id") int customerId);
}
