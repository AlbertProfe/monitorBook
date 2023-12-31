package com.example.demo.restController;
import com.example.demo.model.ActivityLog;
import com.example.demo.model.Book;
import com.example.demo.service.ActivityLogService;
import com.example.demo.service.BookService;
import com.example.demo.utilities.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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

    //CRUD: get all
    @GetMapping("/getAllBooks")
    public ResponseEntity<Iterable<Book>> getAllBooks(HttpServletRequest request){

        // query to service to get all books
        ArrayList<Book> booksFromService = bookService.getAllBooks();
        // call Utilities to create a log
        ActivityLog activityLog = Utilities.createLog(request,"getAllBooks",
                "books", "processing", "/api/v1/book/getAllBooks", "GET");
        HttpHeaders headers = Utilities.createHeader(activityLog);
        //Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        //System.out.println("Lapsed time: " + ( timestamp2.getNanos() - timestamp.getNanos()));

        if (booksFromService != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(booksFromService);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(booksFromService);
        }
    }

    //CRUD: get by id
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Book> getBookById(HttpServletRequest request, @PathVariable  String  id){

        // call to service for find by id
        Book book = bookService.findBookById(id);

        ActivityLog activityLog = Utilities.createLog(request,"getBookById",
                "books", "processing", "api/v1/book/getBookById", "GET");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (book != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(book);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(book);
        }
    }

    //CRUD: delete all
    @DeleteMapping("/deleteAllBooks")
    public ResponseEntity deleteAllBooks (HttpServletRequest request){

       // query to delete all books
        int qty = bookService.qtyBooks();
        boolean deleted = bookService.deleteAllBooks();


        ActivityLog activityLog = Utilities.createLog(request,"deleteAllBooks",
                "books", "processing", "api/v1/book/deleteAllBooks", "DELETE");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (deleted) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            headers.add("qtyObjectsDeleted", String.valueOf(qty));
            return ResponseEntity.accepted().headers(headers).body(null);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(null);
        }
    }

    //CRUD: delete by id
    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<Book> deleteBookById (HttpServletRequest request, @PathVariable String id){

        Book book = bookService.deleteById(id);
        ActivityLog activityLog = Utilities.createLog(request,"deleteBookById",
                "books", "processing", "api/v1/book/deleteBookById", "DELETE");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (book != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(book);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(book);
        }


    }

    //CRUD: create
    @PostMapping(path = "/createBook", consumes = "application/JSON")
    public ResponseEntity<Book> addBook(HttpServletRequest request, @RequestBody Book book) {
        //
        ActivityLog activityLog = Utilities.createLog(request,"createBook",
                "books", "fail", "api/v1/book/createBook", "POST");


        HttpHeaders headers = Utilities.createHeader(activityLog);

        Book bookCreated = bookService.createBook(book);


        if (bookCreated != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(book);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(book);
        }
    }

    //CRUD: update
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook (HttpServletRequest request, @PathVariable String id,
                                            @RequestBody Book book){

        Book bookToUpdate = bookService.updateBook(id, book);

        ActivityLog activityLog = Utilities.createLog(request,"updateBook",
                "books", "processing", "api/v1/book/updateBook", "PUT");

        HttpHeaders headers = Utilities.createHeader(activityLog);

        if (bookToUpdate != null) {
            activityLog.setStatus("success");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "success");
            return ResponseEntity.accepted().headers(headers).body(book);
        } else {
            activityLog.setStatus("fail");
            activityLogService.addActivityLog(activityLog);
            headers.add("status", "fail");
            return ResponseEntity.internalServerError().headers(headers).body(book);
        }
    }



}
