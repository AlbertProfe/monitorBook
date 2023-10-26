package com.example.demo.restController;
import com.example.demo.service.BookService;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/book/")
public class BookRestController {

    @Autowired
    BookService bookService;

    @GetMapping("/getAllBooks")
    public ArrayList<Book> helloWorld(Model container){


        ArrayList<Book> booksFromService = bookService.getAllBooks();


        return bookService.getAllBooks();
    }

    @GetMapping("/getBookById/{id}")
    public Book getBookById(@PathVariable  String  id){

        //boolean result = bookService.checkBookById();
        // reference variable creation book
        Book book = null;
        // call to service for find by id
        book = bookService.findBookById(id);

      return book;
    }

    @DeleteMapping("/deleteAllBooks")
    public void deleteAllBooks (){

        // call to service
        //
        bookService.deleteAllBoooks();
    }

    @DeleteMapping("/deleteBookById/{id}")
    public String deleteBookById (String id){

        // check if book is in db

        // if does not exist ERROR

        // call to service bookService.deleteById(id)

        bookService.deleteById(id);

        // RESULT ....

        return "timestamp or deletion confirmation";
    }






}
