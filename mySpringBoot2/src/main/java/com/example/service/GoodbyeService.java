package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({ "bye" })
@Component
public class GoodbyeService implements MessageService {
	@Value("${name:Tom}")
	private String name;

	@Override
	public String sayMessage() {

		return "goodbye " + this.name;
	}

}
