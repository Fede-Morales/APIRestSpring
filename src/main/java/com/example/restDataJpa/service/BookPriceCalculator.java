package com.example.restDataJpa.service;

import com.example.restDataJpa.entities.Book;

public class BookPriceCalculator {

    public double calcularPrecio(Book book){
        double price = book.getPrice();

        if(book.getPages() > 300){
            price +=100;
        }
        //precio + envio
        price += 300;
        return price;
    }

}
