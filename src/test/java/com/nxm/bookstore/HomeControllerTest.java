package com.nxm.bookstore;

import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nxm.bookstore.service.CustomerService;
import com.nxm.bookstore.web.HomeController;

@ContextConfiguration(locations = {"classpath:spring/applicationContext-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {
	static Logger logger = Logger.getLogger(HomeControllerTest.class);
	@Autowired
	private HomeController controller;
	@Autowired
	private CustomerService customerService;

	@Test
	@Transactional
	public void testRegisterDo(){
		MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setParameter("username", "xiaoming");
        request.setParameter("password", "xiaoming");
        request.setParameter("email", "xiaoming@163.com");
        controller.registerDo(request, response);
        assertNotNull(customerService.getCustomerByName("xiaoming"));
	}
}
