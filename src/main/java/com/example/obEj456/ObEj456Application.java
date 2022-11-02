package com.example.obEj456;

import com.example.obEj456.entities.Laptop;
import com.example.obEj456.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObEj456Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObEj456Application.class, args);
                LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);
                
                Laptop laptop1 = new Laptop("Lenovo Legion Slim",null, 556900.00);
                Laptop laptop2 = new Laptop("HP Envy p300",null, 179999.00);
                
                laptopRepository.save(laptop1);
                laptopRepository.save(laptop2);
                
	}

}
