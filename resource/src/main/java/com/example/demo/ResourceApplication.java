package com.example.demo;

import java.security.Principal;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, /*securedEnabled = true, */proxyTargetClass = true)
@Import({MethodSecurityConfig.class })
public class ResourceApplication {

	@RequestMapping("/")
	public Message home() {
		return new Message("Oauth World!!!");
	}
	
	@RequestMapping(value="/userresource", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public String userResource(Principal user) {
		return "{\"id\":\"" + user.getName() + "\",\"content\":\"Hello User\"}";
	}
	
	@RequestMapping(value="/adminresource", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String adminResource(Principal user) {
		return "{\"id\":\"" + user.getName() + "\",\"content\":\"Hello Admin\"}";
	}

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}

}

class Message {
	private String id = UUID.randomUUID().toString();
	private String content;

	Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}