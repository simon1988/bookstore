package com.nxm.bookstore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.model.Order;

public class OrderRowMapper implements RowMapper<Order> {
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getInt("orders.id"));
		order.setOrderDate(rs.getTimestamp("order_date"));
		Customer customer = new Customer();
		customer.setId(rs.getInt("customer.id"));
		customer.setName(rs.getString("customer.name"));
		customer.setAddress(rs.getString("customer.address"));
		order.setCustomer(customer);
		return order;
	}
}
