package com.example.restDataJpa.controller;

import com.example.restDataJpa.entities.Book;
import com.example.restDataJpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    //CRUD sobre la entidad book

    private final Logger log = LoggerFactory.getLogger(BookController.class);
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
    public ResponseEntity<Book> create(@RequestBody Book book){
        if(book.getId() != null){
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    //Actualizar un libro
    @PutMapping("/api/update")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null){
            log.warn("trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }else if(!bookRepository.existsById(book.getId())){
            log.warn("trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);

        return ResponseEntity.ok(result);
    }

    //Borrar un libro
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)) {
            log.warn("trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/api/deleteAll")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST Request for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.notFound().build();
    }
}
