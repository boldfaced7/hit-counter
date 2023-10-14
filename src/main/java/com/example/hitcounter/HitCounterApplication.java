package com.example.hitcounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HitCounterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HitCounterApplication.class, args);
	}

}
