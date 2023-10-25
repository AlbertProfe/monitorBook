package com.example.demo;

public class Book {

    private String id;
    private String title;
    private int pages;
    private String language;
    private String author;



    public Book(String id, String title, int pages, String language, String author) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.language = language;
        this.author = author;
    }

        public Book (){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public String getLanguage() {
        return language;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
