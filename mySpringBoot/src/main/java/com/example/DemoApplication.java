package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bean.ConfigBean;
import com.example.bean.User;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Value("${my.name}")
	private String name;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello " + name;
	}

	@Autowired
	private ConfigBean configBean;

	@RequestMapping("/configbean")
	public String configbean() {
		return configBean.toString();
	}

	@Autowired
	private User user;

	@RequestMapping("/user")
	public String user() {
		return user.toString();
	}

	@Bean
	public static CommandLineRunner testA() {
		CommandLineRunner runner = new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("the testA runner start to init");
			}
		};
		return runner;
	}

	public static void main(String[] args) {
		System.out.println("the service to start");
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("the service has started");
	}
}
