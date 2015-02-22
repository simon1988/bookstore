package com.nxm.bookstore.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.dao.ICustomerDao;
import com.nxm.bookstore.model.Customer;
import com.nxm.bookstore.util.MD5Util;

@Service
public class CustomerService {

	static Logger logger=Logger.getLogger(CustomerService.class);
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Cacheable(value="customerCache")
	@Transactional(readOnly=true)
	public boolean verifyPassword(String username, String password){
		Customer customer = customerDao.getCustomerByName(username);
		if(customer!=null&&customer.getPassword().equals(MD5Util.MD5(password))){
			return true;
		}
		return false;
	}
	
	@CacheEvict(value="customerCache",allEntries=true)
	@Transactional
	public boolean registerNewCustomer(String username,String password,String email){
		if(customerDao.getCustomerByName(username)!=null){
			return false;
		}
		Customer customer = new Customer();
		customer.setName(username);
		customer.setPassword(MD5Util.MD5(password));
		customer.setEmail(email);
		customerDao.registerNewCustomer(customer);
		return true;
	}
}
