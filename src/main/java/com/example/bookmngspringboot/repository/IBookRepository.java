package com.example.bookmngspringboot.repository;

import com.example.bookmngspringboot.model.Author;
import com.example.bookmngspringboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByBookNameOrAuthorOrPriceBetween(Optional<String> bookName,
                                                       Optional<Author> author,
                                                       Optional<Long> minPrice,
                                                       Optional<Long> maxPrice);

}
