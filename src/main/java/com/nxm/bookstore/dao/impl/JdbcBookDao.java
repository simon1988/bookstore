package com.nxm.bookstore.dao.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.nxm.bookstore.dao.IBookDao;
import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.model.Order;

public class JdbcBookDao extends JdbcDaoSupport implements IBookDao {
	private final static String GET_ALL_ORDERS = "select a.id, a.order_date, b.id, b.name, b.address from orders a inner join customer b on a.customer_id=b.id";

	private final static String GET_ORDER_FROM_ID = GET_ALL_ORDERS + " where a.id=:id";
	
	private final static String GET_BOOKS_FROM_ORDER_ID = "select id, name, author from orders__books a inner join books b on a.book_id = b.id where a.order_id=:id";

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Collection<Order> getAllOrders() {
		List<Order> orders = namedParameterJdbcTemplate.query(GET_ALL_ORDERS, new OrderRowMapper());
		Map<String, Object> params = new HashMap<String, Object>();
		for(Order order : orders){
			params.put("id", order.getId());
			List<Book> books = namedParameterJdbcTemplate.query(GET_BOOKS_FROM_ORDER_ID, params, new BookRowMapper());
			order.setBooks(new HashSet<Book>(books));
		}
		return orders;
	}

	@Override
	public void placeNewOrder(Customer c, Set<Book> books) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		int order_id = namedParameterJdbcTemplate.queryForInt("select max(id) from orders", params);
		params.put("id", order_id+1);
		params.put("customer_id", c.getId());
		namedParameterJdbcTemplate.update("insert into orders(id,customer_id) values(:id,:customer_id)", params);
		Map<String, Object> paramsMaps[] = new Map[books.size()];
		int index = 0;
		for(Book book : books){
			params=new HashMap<String, Object>();
			params.put("order_id", order_id+1);
			params.put("book_id", book.getId());
			paramsMaps[index++]=params;
		}
		
		namedParameterJdbcTemplate.batchUpdate("insert into orders__books(order_id,book_id) values(:order_id,:book_id)", paramsMaps);
	}

	@Override
	public Order getOrderFromId(int orderId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", orderId);
		Order order = namedParameterJdbcTemplate.queryForObject(GET_ORDER_FROM_ID, params, new OrderRowMapper());
		if(order==null){
			return null;
		}
		List<Book> books = namedParameterJdbcTemplate.query(GET_BOOKS_FROM_ORDER_ID, params, new BookRowMapper());
		order.setBooks(new HashSet<Book>(books));
		return order;
	}

}
