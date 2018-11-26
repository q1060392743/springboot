# 一、建立第一个springboot项目

## 1.在sts中建立
File->New->Spring boot starter project

## 2.pom文件
通过parent标签管理整个springboot项目的version

```
<parent>
	<groupId>org.springframework.boot</groupId>`
	<artifactId>spring-boot-starter-parent</artifactId>`
	<version>1.5.17.RELEASE</version>`
</parent>

<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>
</dependencies>

<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

或者

```
<dependencyManagement>`
	<dependencies>`
		<dependency>`
			<groupId>org.springframework.boot</groupId>`
			<artifactId>spring-boot-dependencies</artifactId>`
			<version>1.5.17.RELEASE</version>`
			<type>pom</type>`
			<scope>import</scope>`
		</dependency>`
	</dependencies>`
</dependencyManagement>
```

# 二、运行第一个springboot项目
==注意入口类的位置！！==
```
@SpringBootApplication
public class MySpringBoot2Application {

	public static void main(String[] args) {
		SpringApplication.run(MySpringBoot2Application.class, args);
	}
}
```

# 三、@CommandLineRunner
```
@SpringBootApplication
public class DemoApplication {
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
```

先于主函数执行，order的值越低优先级越高
```
@Component
@Order(value = 1)
public class OrderRunner1 implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println("the orderRunner1 start");
	}

}
```

```
@Component
@Order(value = 2)
public class OrderRunner2 implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("the orderRunner2 start");
	}
}
```
# 四、@ConfigurationProperties

prefix：前缀
```
@ConfigurationProperties(prefix = "my")
@Configuration
public class ConfigBean {
	private String name;
	private int age;
	//省略getset等方法
}
```
application.yaml

```
my:
  name: tom
  age: 100
```
主函数，自动注入configBean，属性值从配置文件中添加
```
@SpringBootApplication
@RestController
public class DemoApplication {
	@Autowired
	private ConfigBean configBean;

	@RequestMapping("/configbean")
	public String configbean() {
		return configBean.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```
# 五、@PropertySource
指定配置文件寻找值
```
@PropertySource(value = "classpath:test.yaml")
@ConfigurationProperties(prefix = "my1")
@Configuration
public class User {
	private String name;
	private int age;
	//省略getset等方法
}
```
test.yaml

```
my1:
  name: jary
  age: 50
```

```
@SpringBootApplication
@RestController
public class DemoApplication {
    @Autowired
	private User user;

	@RequestMapping("/user")
	public String user() {
		return user.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```
# 六、@Profile
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



