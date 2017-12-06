package org.aksw.dice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.aksw.dice.*")
public class SpringHareApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHareApplication.class, args);
	}
}