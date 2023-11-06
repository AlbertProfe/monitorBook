package com.example.demo.model;

//https://projectlombok.org/features/all
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data // generates getters, setters, equals, hashCode, and toString methods@NoArgsConstructor @AllArgsConstructor
@NoArgsConstructor // generates a no-args constructor
@AllArgsConstructor // generates a constructor with all arguments
@Entity(name="Author")
@Table(name="AUTHOR_TABLE")
public class Author {

    @Id
    @Column(name = "AUTHOR_ID")
    private long authorId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "DATE_OF_BIRTH", columnDefinition = "DATE")
    private LocalDate dob;

    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<Book>();

    //constructor without ID
    public Author(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    //method to add books to books
    public void addBook(Book book) {
        this.getBooks().add(book);
        //if (book.getId() != null) book.getId().getBooks().remove(book);
        //book.setId(this);
    }

}