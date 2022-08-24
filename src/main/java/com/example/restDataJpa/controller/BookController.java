package com.example.restDataJpa.controller;

import com.example.restDataJpa.entities.Book;
import com.example.restDataJpa.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    //CRUD sobre la entidad book

    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * https://localhost:8080/api/books
     * @return
     */
    //Buscar todos los libros(Lista libros)
    @GetMapping("/api/books")
    public List<Book> findAll(){
        //Recuperar y devolver los libros de bd
        return bookRepository.findAll();
    }

    //Buscar un solo libro segun su ID
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> bookOpt = bookRepository.findById(id);
        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        }else
            return ResponseEntity.notFound().build();
        // return bookOpt.orElse(null); Expresion funcional
    }
    
    //Crear un nuevo libro
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);

    }

    //Actualizar un libro

    //Borrar un libro
}
