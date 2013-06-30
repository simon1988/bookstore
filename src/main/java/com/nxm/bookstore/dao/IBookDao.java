package com.nxm.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.model.Order;

public interface IBookDao {
	public Collection<Order> getAllOrders();
	public Order getOrderFromId(int orderId);
	public void placeNewOrder(Customer c, Set<Book> books);
}
