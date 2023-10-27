package com.example.demo.service;

import com.example.demo.model.Book;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

@Service
public class BookService {
    static ArrayList<Book> books = new ArrayList<>();

    static {

        // locale in english
        Faker faker = new Faker(new Locale("en-GB"));
        // ref variable creation UUID
        String uniqueID;

        for (int i = 0; i <100 ; i++ ){

            uniqueID = UUID.randomUUID().toString();
            books.add(new Book ( uniqueID, faker.book().title() , faker.number().numberBetween(100, 1250), "Russian",  faker.book().author() ));
            }

    }

    // return books to controller
    // get books form list static from class and return as signature
    public ArrayList<Book> getAllBooks (){
        return books;
    }

    public Book findBookById(String id) {

        Book bookFound = null;

        for (Book book : books) {
            boolean checkBook = book.getId().equals(id);
            if (checkBook)

            {bookFound = book;
              break;}

        }


        return bookFound;
    }

    public void deleteAllBooks(){
        books.clear();
        //to-do delete all books with sql
    }

    public void deleteById(String id) {
    }

    public void deleteAllBoooks() {
    }

    public boolean  checkBookById (){

        // check if book is in db

        // if does not exist FALSE

        // if exist TRUE

        return true;
    }


    public Book createBook(Book book) {

        return book;
    }
}
