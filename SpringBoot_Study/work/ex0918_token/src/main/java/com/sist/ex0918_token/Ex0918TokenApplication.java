package com.sist.ex0918_token;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 날짜 자동넣기
public class Ex0918TokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ex0918TokenApplication.class, args);
	}

}
