package com.example.bookmngspringboot.service.book;

import com.example.bookmngspringboot.model.Author;
import com.example.bookmngspringboot.model.Book;
import com.example.bookmngspringboot.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;
    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAllByBookNameOrAuthorOrPriceBetween(Optional<String> bookName, Optional<Author> author, Optional<Long> minPrice, Optional<Long> maxPrice) {
        return bookRepository.findAllByBookNameOrAuthorOrPriceBetween(bookName, author, minPrice, maxPrice);
    }

    @Override
    public Long getTotalPrice() {
        Iterable<Book> books = bookRepository.findAll();
        Long totalPrice = 0L;
        for (Book b : books) {
            totalPrice += b.getPrice();
        }
        return totalPrice;
    }
}
