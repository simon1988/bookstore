package com.nxm.bookstore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.dao.ICustomerDao;
import com.nxm.bookstore.model.Customer;
import com.nxm.util.MD5Util;

@Service
public class CustomerService {

	private static Logger logger=LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Transactional(readOnly=true)
	public boolean verifyPassword(String username, String password){
		Customer customer = customerDao.getCustomerByName(username);
		if(customer!=null&&customer.getPassword().equals(MD5Util.MD5(password))){
			return true;
		}
		return false;
	}
	
	@Cacheable(value="customerCache", key="#username")
	@Transactional(readOnly=true)
	public Customer getCustomerByName(String username){
		Customer customer = customerDao.getCustomerByName(username);
		return customer;
	}
	
	@CacheEvict(value="customerCache", key="#username")
	public void updateCustomerBalance(String username, double balance){
		customerDao.updateCustomerBalance(username, balance);
	}
	
	@Transactional
	public boolean registerNewCustomer(String username,String password,String email){
		if(customerDao.getCustomerByName(username)!=null){
			logger.info("customer {} already exists", username);
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
