## # Spring boot Admin监控

Spring Boot Admin 是一个针对spring-boot的actuator接口进行UI美化封装的监控工具。实现对多个spring boot应用进行监控，每个客户端应用可以通过HTTP或者使用 Eureka注册到admin server中进行展示。

## admin-server：8000

1. pom.xml

   ```xml
   		<!--引入admin server依赖-->
   		<dependency>
   			<groupId>de.codecentric</groupId>
   			<artifactId>spring-boot-admin-server</artifactId>
   			<version>1.5.6</version>
   		</dependency>
   		<!--admin server的展示-->
   		<dependency>
   			<groupId>de.codecentric</groupId>
   			<artifactId>spring-boot-admin-server-ui</artifactId>
   			<version>1.5.6</version>
   		</dependency>
   ```

2. 启动类上加注解

```java
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
```

1. http://localhost:8000 访问

## admin-client：8001

1. pom.xml

```xml
<dependency>
   <groupId>de.codecentric</groupId>
   <artifactId>spring-boot-admin-starter-client</artifactId>
   <version>1.5.6</version>
</dependency>
```

1. application.properties

```properties
spring.boot.admin.url=http://localhost:8000  
management.security.enabled=false
```

1. http://localhost:8001/health/ 客户端的心跳url

##  为监控系统添加权限保护

### 为server端设置登陆页面和权限

#### 1. 为server端设置登陆页面和权限

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-server-ui</artifactId>
</dependency>
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-server-ui-login</artifactId>
</dependency>
```

#### 2. 修改配置文件

```properties
security.user.name=aa
security.user.password=aa //登陆用户名密码
security.basic.enabled=false //关掉security框架自带的登陆弹出框
```

#### 3.配置config类

### 为client端设置端点保护

#### 1.加入如下依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

#### 2.修改配置文件

```properties
#可在线查看日志
endpoints.logfile.enabled=true
logging.file=fileDir
#客户端开启停止服务端点
endpoints.shutdown.enabled=true
#保护客户端端点数据
security.user.name=aa
security.user.password=aa
security.basic.path=/aa
eureka.instance.metadata-map.user.name=${security.user.name}
eureka.instance.metadata-map.user.password=${security.user.password}
```



 

 

 

 

 

 

 

 