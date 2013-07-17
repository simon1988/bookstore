package com.nxm.bookstore.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.nxm.bookstore.model.Book;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setName(rs.getString("name"));
		book.setAuthor(rs.getString("author"));
		return book;
	}

}
