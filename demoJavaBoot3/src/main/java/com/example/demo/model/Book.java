package com.example.demo.model;

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
    private String id;
    private String title;
    private int pages;
    private String language;
    //private String author;
    @ManyToOne
    @JoinColumn(name = "AUTHOR_FK")
    private Author author;

}
