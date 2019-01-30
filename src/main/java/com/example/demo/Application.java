package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //禁用springboot默认加载的application.properties单数据源配置
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
