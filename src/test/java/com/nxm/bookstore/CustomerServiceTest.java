package com.nxm.bookstore;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.service.CustomerService;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest {
	static Logger logger = Logger.getLogger(CustomerServiceTest.class);
	@Autowired
	private CustomerService customerService;
	@Test
	@Transactional
	public void TestRegister(){
		customerService.registerNewCustomer("test", "test", "test");
	}
}
