package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    // return books to controller
    // get books form list static from class and return as signature
    public Iterable<Book> getAllBooks (){


        return bookRepository.findAll();
    }


}
