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
        Iterable<Book> booksFromService = bookService.getAllBooks();
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


}
