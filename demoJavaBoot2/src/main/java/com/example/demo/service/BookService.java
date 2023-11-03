package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
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

    public Optional<Book> findBookById(String id) {

        Optional<Book> bookFound = bookRepository.findById(id);


        return bookFound;
    }

    public long qtyBooks (){

        return bookRepository.count();
    }

    public boolean deleteAllBooks(){
        //delete all books with clear

        bookRepository.deleteAll();
        long qty = qtyBooks();
        boolean deletedBooks = true;

        if ( qty > 0) deletedBooks = false ;
        //else null;
        return deletedBooks;
    }

    public Book deleteById(String id) {

        Optional<Book> bookFound = findBookById(id);
        boolean boodRemoved = false;

        if (bookFound.isPresent()) {
            bookRepository.deleteById(id);
            boodRemoved = true;
            return bookFound.get() ;

        } else return null;
    }

    public Book createBook(Book book) {

        Book bookSaved = bookRepository.save(book);
        // to-do: test this method and improve it
        return bookSaved;
    }

    public Book updateBook (String id, Book book){

        Optional<Book> bookFound = findBookById(id);

        if (bookFound.isPresent()) {

            Book bookSaved = bookRepository.save(book);
            return bookSaved;

        } else return null;

    }


}
