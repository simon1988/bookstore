package com.nxm.bookstore.dao;

import org.apache.ibatis.annotations.Param;

import com.nxm.bookstore.model.Customer;

public interface ICustomerDao {
	Customer getCustomerByName(@Param("name") String name);
	void registerNewCustomer(@Param("customer") Customer customer);
	void updateCustomerBalance(@Param("name") String name, @Param("balance") double balance);
}
