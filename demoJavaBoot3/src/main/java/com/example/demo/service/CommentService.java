package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {


    @Autowired
    CommentRepository commentRepository;
    public Comment create(Comment comment) {

        Comment commentSaved = commentRepository.save(comment);
        // to-do: test this method and improve it
        return commentSaved;
    }
}

