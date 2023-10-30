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

        ArrayList<Book> booksFromService = bookService.getAllBooks();

        ActivityLog activityLog = Utilities.createLog(request,"getAllBooks",
                "books", "success", "api/v1/book/getAllBooks", "GET");
        if ( activityLog != null) {
            activityLogService.addActivityLog(activityLog);

            HttpHeaders headers = Utilities.createHeader(activityLog);

            //HttpHeaders headers = new HttpHeaders();
            //Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            //System.out.println("Lapsed time: " + ( timestamp2.getNanos() - timestamp.getNanos()));
            return ResponseEntity.accepted().headers(headers).body(booksFromService);

        } else return ResponseEntity.accepted().body(booksFromService);

    }

    //CRUD: get by id
    @GetMapping("/getBookById/{id}")
    public Book getBookById(@PathVariable  String  id){

        //boolean result = bookService.checkBookById();
        // reference variable creation book
        Book book = null;
        // call to service for find by id
        book = bookService.findBookById(id);

      return book;
    }

    //CRUD: delete all
    @DeleteMapping("/deleteAllBooks")
    public void deleteAllBooks (){

        // call to service
        //
        bookService.deleteAllBooks();
    }

    //CRUD: delete by id
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
    @PostMapping(path = "/createBook", consumes = "application/JSON")
    public ResponseEntity<Book> addBook(HttpServletRequest request, @RequestBody Book book) {
        //
        ActivityLog activityLog = Utilities.createLog(request,"createBook",
                "books", "fail", "api/v1/book/createBook", "POST");


        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", activityLog.getOperationMethod());
        headers.add("version", activityLog.getVersion());
        headers.add("domain", activityLog.getDomain());
        headers.add("timestamp", activityLog.getTime().toString());
        headers.add("id", activityLog.getId());
        headers.add("user", activityLog.getUser());
        headers.add("endpoint", activityLog.getEndpoint());
        headers.add("ip", activityLog.getIp());
        headers.add("restMethod", activityLog.getRestMethod());

        Book bookCreated = bookService.createBook(book);

        if (bookCreated != null) {
            headers.add("status", "success");
            if (activityLog != null) {
                activityLog.setStatus("book created");
                activityLogService.addActivityLog(activityLog);
            }
            return ResponseEntity.accepted().headers(headers).body(bookCreated);
        } else {
            if (activityLog != null) {
                activityLog.setStatus("book not created");
                activityLogService.addActivityLog(activityLog);
            }
            headers.add("status", "not created");
            return ResponseEntity.accepted().body(null);
        }
    }

    //CRUD: update



}
