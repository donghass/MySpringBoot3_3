package com.basic.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MySpringBoot33Application {

	public static void main(String[] args) {

		//SpringApplication.run(MySpringBoot33Application.class, args);
		SpringApplication application = new SpringApplication(MySpringBoot33Application.class);

		// application 타입 변경
		application.setWebApplicationType(WebApplicationType.SERVLET);

		application.run(args);//웹어플리케이션 타입지정
	}



	@Bean
	public String hello(){
		return "Welcome SpringBoot";
	}
}
