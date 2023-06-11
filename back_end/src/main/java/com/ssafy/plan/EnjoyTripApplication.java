package com.ssafy.plan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = {"com.ssafy.*"})
@MapperScan({})
@SpringBootApplication
public class EnjoyTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnjoyTripApplication.class, args);
	}

}
