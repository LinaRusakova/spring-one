package ru.geekbrains.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.geekbrains.spring.repositories.ProductDao;

@SpringBootApplication
public class SpringOneApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringOneApplication.class, args);
		//ProductDao.init();
	}

}
