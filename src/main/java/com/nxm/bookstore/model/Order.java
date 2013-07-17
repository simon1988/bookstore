package com.nxm.bookstore.model;

import java.sql.Timestamp;
import java.util.Set;

public class Order {
	private int id;
	private Timestamp orderDate;
	private Customer customer;
	private Set<Book> books;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public String toString(){
		return id+"\t"+customer.getName()+"\t"+orderDate+"\t"+books;
	}
}
