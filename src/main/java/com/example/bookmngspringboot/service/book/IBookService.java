package com.example.bookmngspringboot.service.book;

import com.example.bookmngspringboot.model.Author;
import com.example.bookmngspringboot.model.Book;
import com.example.bookmngspringboot.service.IGeneral;

import java.util.List;
import java.util.Optional;

public interface IBookService extends IGeneral<Book> {
    List<Book> findAllByBookNameOrAuthorOrPriceBetween(Optional<String> bookName, Optional<Author> author, Optional<Long> minPrice, Optional<Long> maxPrice);
    Long getTotalPrice();
}
