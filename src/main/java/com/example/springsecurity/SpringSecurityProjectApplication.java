package com.example.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springsecurity.domain.UserData;
import com.example.springsecurity.service.impl.IService;

@SpringBootApplication
public class SpringSecurityProjectApplication implements CommandLineRunner {
	@Autowired
	private IService<UserData> userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * UserData user1 = new UserData(); user1.setName("Mainak"); user1
		 * userService.saveOrUpdate(user1);
		 * 
		 * User user2 = new User(); user2.setEmail("test@admin.com");
		 * user2.setName("Test Admin"); user2.setMobile("9787456545");
		 * user2.setRole(roleService.findById(1L).get()); user2.setPassword(new
		 * BCryptPasswordEncoder().encode("testadmin"));
		 * userService.saveOrUpdate(user2);
		 * 
		 * Book book = new Book(); book.setTitle("Spring Microservices in Action");
		 * book.setAuthor("John Carnell"); book.setCoverPhotoURL(
		 * "https://images-na.ssl-images-amazon.com/images/I/417zLTa1uqL._SX397_BO1,204,203,200_.jpg"
		 * ); book.setIsbnNumber(1617293989L); book.setPrice(2776.00);
		 * book.setLanguage("English"); bookService.saveOrUpdate(book);
		 */
	}
}
