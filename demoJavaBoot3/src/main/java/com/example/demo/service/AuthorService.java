package com.example.demo.service;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    public Author create(Author author) {

        Author authorSaved = authorRepository.save(author);
        // to-do: test this method and improve it
        return authorSaved;
    }
}
