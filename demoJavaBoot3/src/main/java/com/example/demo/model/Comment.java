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
@Table(name = "COMMENT_TABLE")
public class Comment {

    @Id
    private String id;
    private String commenterName;
    private String commentText;
    private int rating;
    private String timestamp;




}
