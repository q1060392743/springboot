package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({ "hello" })
public class HelloService implements MessageService {
	@Value("${name:World}")
	private String name;

	@Override
	public String sayMessage() {
		return "hello " + this.name;
	}

}
