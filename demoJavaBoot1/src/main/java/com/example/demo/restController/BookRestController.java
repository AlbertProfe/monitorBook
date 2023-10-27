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
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/book/")
public class BookRestController {

    @Autowired
    BookService bookService;

    @Autowired
    ActivityLogService activityLogService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<Iterable<Book>> getAllBooks(HttpServletRequest request){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String ip = request.getRemoteAddr();
        ArrayList<Book> booksFromService = bookService.getAllBooks();
        ActivityLog activityLog = new ActivityLog();

        String uniqueID = UUID.randomUUID().toString();

        activityLog.setOperationMethod("getAllBooks");
        activityLog.setDomain("books");
        activityLog.setId(uniqueID);
        activityLog.setIp(ip);
        activityLog.setTime(timestamp);
        activityLog.setUser("anonymous");
        activityLog.setStatus("success");
        activityLog.setEndpoint("api/v1/book/getAllBooks");
        activityLog.setVersion("1.0");

        activityLogService.addActivityLog(activityLog);

        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", activityLog.getOperationMethod());
        headers.add("version", activityLog.getVersion());
        headers.add("domain", activityLog.getDomain());
        headers.add("status", activityLog.getStatus());
        headers.add("timestamp", activityLog.getTime().toString());
        headers.add("id", activityLog.getId());
        headers.add("user", activityLog.getUser());
        headers.add("status", activityLog.getStatus());
        headers.add("endpoint", activityLog.getEndpoint());
        headers.add("ip", activityLog.getIp());


        //headers.add("qtyObjects", booksFromService.size() );
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        //System.out.println((long)timestamp2 - (long)timestamp);
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
