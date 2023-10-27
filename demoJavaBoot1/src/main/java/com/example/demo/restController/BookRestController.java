package com.example.demo.restController;
import com.example.demo.model.ActivityLog;
import com.example.demo.model.Book;
import com.example.demo.service.ActivityLogService;
import com.example.demo.service.BookService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/book/")
public class BookRestController {

    @Autowired
    BookService bookService;
    ActivityLogService activityLogService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<Iterable<Book>> getAllBooks(HttpServletRequest request){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        request.getRemoteAddr();
        ArrayList<Book> booksFromService = bookService.getAllBooks();
        ActivityLog activityLog = new ActivityLog();

        activityLog.setOperationMethod("getAllBooks");
        activityLog.setDomain("");
        activityLog.setId("");
        activityLog.setIp("");
        //activityLog.setTime("");
        activityLog.setUser("");
        //activityLog.setStatus("");
        activityLog.setEndpoint("");

        activityLogService.addActivityLog(activityLog);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", activityLog.getOperationMethod());
        headers.add("version", "api 1.0");
        headers.add("domain", "book");
        headers.add("status", "success");
        headers.add("timestamps", "n/a");
        //headers.add("qtyObjects", booksFromService.size() );


        return ResponseEntity.accepted().headers(headers).body(booksFromService);
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


    //CRUD: create
    @PostMapping(path = "createBook", consumes = "application/JSON")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "createBook");
        headers.add("version", "api 1.0");
        headers.add("statusOperation", "success");

        Book bookCreated = bookService.createBook(book);

        if (bookCreated != null) {
            headers.add("statusOperation", "success");
            return ResponseEntity.accepted().headers(headers).body(bookCreated);
        } else {
            headers.add("statusOperation", "not created");
            return ResponseEntity.accepted().body(null);
        }
    }



}
