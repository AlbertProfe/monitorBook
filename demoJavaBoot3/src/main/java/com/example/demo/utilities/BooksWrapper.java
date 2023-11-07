package com.example.demo.utilities;

import com.example.demo.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
public class BooksWrapper {

    private String id;
    private int qty;
    private Iterable<Book> books;
}
