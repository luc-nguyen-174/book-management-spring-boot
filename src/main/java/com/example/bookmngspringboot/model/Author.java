package com.example.bookmngspringboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String authorName;

    @OneToMany(targetEntity = Book.class,fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {
    }

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
