package com.example.bookmngspringboot.controller;

import com.example.bookmngspringboot.model.Author;
import com.example.bookmngspringboot.service.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("authors")
@CrossOrigin("*")
public class AuthorController {
    @Autowired
    private IAuthorService authorService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Author>> getAuthors() {
        return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Author> createAuthors(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.save(author), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> editAuthors(@PathVariable Long id, @RequestBody Author author) {
        Optional<Author> authorOptional = authorService.findById(id);
        if (authorOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        author.setId(authorOptional.get().getId());
        return new ResponseEntity<>(authorService.save(author), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);
        if (!authorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.remove(id);
        return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> viewAuthor(@PathVariable Long id) {
        Optional<Author> authorOptional = authorService.findById(id);
        return authorOptional.
                map(author -> new ResponseEntity<>(author, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
