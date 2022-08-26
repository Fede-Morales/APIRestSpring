package com.example.restDataJpa.service;

import com.example.restDataJpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calcularPrecio() {
        //configuracion de la prueba
        BookPriceCalculator calculador = new BookPriceCalculator();
        //se ejecuta el comportamiento a testear
        double price = calculador.calcularPrecio(new Book(1L,
                "El señor de los anillos",
                "Author",
                1000,
                5000.50,
                LocalDate.now(),
                true));
        System.out.println(price);
        //Comprobacion de aserciones
        assertTrue(price > 0);
        assertEquals(5400.5,price);

    }
}

