package com.example.bookmngspringboot.controller;

import com.example.bookmngspringboot.model.Author;
import com.example.bookmngspringboot.model.Book;
import com.example.bookmngspringboot.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("books")
public class BookController {
    
    @Autowired
    private IBookService bookService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Book>> getBooks() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> createBooks(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> editBooks(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (bookOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        book.setId(bookOptional.get().getId());
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        if (!bookOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.remove(id);
        return new ResponseEntity<>(bookOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> viewbook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findById(id);
        return bookOptional.
                map(book -> new ResponseEntity<>(book, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/total-price")
    public ResponseEntity<Long> getTotalPrice() {
        Long totalPrice = bookService.getTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Book>> search(
            @RequestParam(value = "bookName") Optional<String> bookName,
            @RequestParam(value = "author") Optional<Author> author,
            @RequestParam(value = "minPrice", required = false) Optional<Long> minPrice,
            @RequestParam(value = "maxPrice", required = false) Optional<Long> maxPrice) {
        List<Book> books = bookService.findAllByBookNameOrAuthorOrPriceBetween(bookName,author , minPrice, maxPrice);
        if (books.isEmpty() ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
