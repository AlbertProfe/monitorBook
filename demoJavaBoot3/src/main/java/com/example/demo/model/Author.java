package com.example.demo.model;

//https://projectlombok.org/features/all

import jakarta.persistence.*;
//import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data // generates getters, setters, equals, hashCode, and toString methods@NoArgsConstructor @AllArgsConstructor
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
@Entity
@Table(name="AUTHOR_TABLE")
public class Author {

    @Id
    @Column(name = "AUTHOR_ID")
    private String id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE")
    private LocalDate dob;


    // mapped by author object in Book Class
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<Book>();

    //method to add book to books
    public void addBook(Book book) {
        this.getBooks().add(book);
        //if (book.getId() != null) book.getId().getBooks().remove(book);
        //book.setId(this);
    }

}