package com.nxm.bookstore.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nxm.bookstore.model.Book;
import com.nxm.bookstore.service.BookService;
import com.nxm.bookstore.service.CustomerService;
import com.nxm.bookstore.service.QRService;

@Controller
public class HomeController {
	static Logger logger=Logger.getLogger(HomeController.class);
	
	@Autowired
	CustomerService customerService;
	@Autowired
	BookService bookService;
	@Autowired
	QRService qrService;

	@RequestMapping(value={"/","/index"})
	public String index(Model model){
		model.addAttribute("books", bookService.getAllBooks());
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public void loginDo(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fromURL = request.getParameter("fromurl");
		if(customerService.verifyPassword(username, password)){
			//write to cookie
			Cookie cookie = new Cookie("username", username);
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			cookie = new Cookie("auth", "encrypt"+password);
			cookie.setPath("/");
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			
			logger.info("fromurl:"+fromURL);
			if(StringUtils.isBlank(fromURL)){
				fromURL="index";
			}
		}else{
			fromURL="login";
		}
		try {
			response.sendRedirect(fromURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	@RequestMapping("/user/{username}")
	public String user(@PathVariable("username") String username, Model model){
		model.addAttribute("customer", customerService.getCustomerByName(username));
		return "user";
	}
	
	@RequestMapping("/doRegister")
	public void registerDo(HttpServletRequest request, HttpServletResponse response){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		customerService.registerNewCustomer(username, password, email);
		try {
			response.sendRedirect("index");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/book/{bookId}",method = RequestMethod.GET)  
    public String getLogin(@PathVariable("bookId") int bookId, Model model){  
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book",book);
        return "book";  
    }

	@RequestMapping("/orders/{username}")
	public String orders(@PathVariable("username") String username, Model model){
		model.addAttribute("orders", bookService.getOrdersByCustomerName(username));
		return "orders";
	}
	
	@RequestMapping("/placeNewOrder/{username}")
	@ResponseBody
	public String placeNewOrder(@PathVariable("username") String username){
		return bookService.placeNewOrder(username);
	}

	@RequestMapping("/addToCart/{username}/{bookId}")
	@ResponseBody
	public String addToCart(@PathVariable("username") String username, @PathVariable("bookId") int bookId){
		if(bookService.addBookToCart(username, bookId)){
			return "Success";
		}else{
			return "Item already exists in cart!";
		}
	}

	@RequestMapping("/deleteFromCart/{username}/{bookId}")
	@ResponseBody
	public String deleteFromCart(@PathVariable("username") String username, @PathVariable("bookId") int bookId){
		bookService.deleteBookFromCart(username, bookId);
		return "Success";
	}

	@RequestMapping("/cart/{username}")
	public String cart(@PathVariable("username") String username, Model model){
		model.addAttribute("cart", bookService.getCartBooks(username));
		return "cart";
	}
	
	@RequestMapping("/error")
	public String error(){
		return "error";
	}
	
	@RequestMapping("/error/404")
	public String error404(){
		return "404";
	}
	
	@ResponseBody
	@RequestMapping(value = "/qrcode", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] qrcode() throws IOException {
		byte[] qrpic = qrService.generateQRImage("http://www.baidu.com");
	    return qrpic;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		logger.info("upload "+name+"!");
		if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
	}
}
