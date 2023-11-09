package com.example.demo.model;

//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data // generates getters, setters, equals, hashCode, and toString methods
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
@Table(name = "BOOK_TABLE")
public class Book {

    @Id
    @Column(name = "BOOK_ID")
    private String id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "PAGES")
    private int pages;
    @Column(name = "LANGUAGE")
    private String language;
    //private String author;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AUTHOR_FK")
    private Author author;

}
