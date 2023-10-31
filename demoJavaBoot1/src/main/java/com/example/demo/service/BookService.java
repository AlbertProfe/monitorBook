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

    public int qtyBooks (){
        return books.size();
    }

    public boolean deleteAllBooks(){
        //delete all books with clear

        books.clear();
        int qty = qtyBooks();
        boolean deletedBooks = true;

        if ( qty > 0) deletedBooks = false ;
        //else null;
        return deletedBooks;
    }

    public Book deleteById(String id) {

        Book book = findBookById(id);
        boolean boodRemoved = false;

        if (book != null) {

            boodRemoved = books.remove(book);
            return book;

        } else return null;
    }

    public Book createBook(Book book) {

        boolean bookAdded = books.add(book);

        if (bookAdded) return book; else return null;
    }

    public Book updateBook (String id, Book book){

        Book bookFound = findBookById(id);
        boolean boodUpdated = false;

        if (bookFound != null) {
            int index = books.indexOf(bookFound);
            Book bookUpdated = books.set(index, book);
            return bookUpdated;

        } else return null;

    }
}
