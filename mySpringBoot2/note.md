# 一、@Profile
```
public interface MessageService {
	String sayMessage();
}
```
指定profile为hello
```
@Component
@Profile({ "hello" })
public class HelloService implements MessageService {
	//如果name为空，默认值为：World
	@Value("${name:World}")
	private String name;

	@Override
	public String sayMessage() {
		return "hello " + this.name;
	}
}
```
application.yaml

```
#激活hello
spring:
  profiles:
    active: hello
---
spring:
  profiles: hello
name: spring
```

```
@SpringBootApplication
@RestController
public class DemoApplication {
    @Autowired
	private HelloService helloService;

	@RequestMapping("/helloService")
	public void say() {
		System.out.println(helloService.sayMessage());
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```

# 二、日志
application.yaml
```
logging:
#自动生成的日志文件
  file: my.log
  #log等级映射
  level:
    root: warn
    org:
      springframework:
        web: debug
```

# 三、监控
添加依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
application1.yaml

```
management:
  port: 8888
  context-path: /abc
  security: 
    enabled: false
```
xx参考开发文档获取所需信息

==localhost:8888/abc/xx==
