package com.nxm.bookstore.dao;

import org.apache.ibatis.annotations.Param;

import com.nxm.bookstore.model.Customer;

public interface ICustomerDao {
	public Customer getCustomerByName(@Param("name") String name);
	public void registerNewCustomer(@Param("customer") Customer customer);
	public void updateCustomerBalance(@Param("name") String name, @Param("balance") double balance);
}
