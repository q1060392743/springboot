package com.example.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


//批量赋值，@Value是单个
@ConfigurationProperties(prefix = "my")
@Configuration
public class ConfigBean {
	private String name;
	private int age;

	public ConfigBean() {
		super();
	}

	public ConfigBean(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ConfigBean [name=" + name + ", age=" + age + "]";
	}

}
