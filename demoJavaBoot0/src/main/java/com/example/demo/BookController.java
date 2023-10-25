package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import org.springframework.ui.Model;


// manage request and response
@Controller
public class BookController {

    // connect contreller and service
    @Autowired
    BookService bookService;

    // manages path books
    // gets the request, and then hte method starts
    // creates a Model container and uses @Autowired to connect with service
    // gets all books and then sends to html th
    @RequestMapping("/books")
    public String AllBoosk (Model booksContainer){

        //bookService.deleteAllBooks();
        // inject books -data- to template -html-th-
        booksContainer.addAttribute("booksToTH",  bookService.getAllBooks() );

        // return web render
        return "books.html";
    }
}
