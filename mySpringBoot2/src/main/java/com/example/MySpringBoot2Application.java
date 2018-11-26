package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.HelloService;

@SpringBootApplication
@RestController
public class MySpringBoot2Application {

	@Autowired
	private HelloService helloService;

	@RequestMapping("/helloService")
	public void say() {
		System.out.println(helloService.sayMessage());
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBoot2Application.class, args);
	}
}
