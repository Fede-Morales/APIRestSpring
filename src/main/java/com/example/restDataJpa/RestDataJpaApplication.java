package com.example.restDataJpa;

import com.example.restDataJpa.entities.Book;
import com.example.restDataJpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class RestDataJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RestDataJpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);


		//Crear y almacenar libros
		repository.save(new Book(null, "Cementerio de animales", "Stephen King", 350, 500.00,
				LocalDate.of(2018, 12 ,1), true));
		repository.save(new Book(null, "IT", "Stephen King", 500, 750.00,
				LocalDate.of(2016, 5 ,3), true));

		//Mostrar libros
		System.out.println(repository.findAll());

		//Eliminar un libro
		//repository.deleteById(1L);



	}

}
