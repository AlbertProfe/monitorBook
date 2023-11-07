package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository<Author> extends CrudRepository<Author, String> {
}

