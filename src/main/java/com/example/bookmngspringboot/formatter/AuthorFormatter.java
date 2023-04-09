package com.example.bookmngspringboot.formatter;

import com.example.bookmngspringboot.model.Author;
import com.example.bookmngspringboot.service.author.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class AuthorFormatter implements Formatter<Author> {
    private final IAuthorService authorService;

    @Autowired
    public AuthorFormatter(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Author parse(String text, Locale locale) throws ParseException {
        Optional<Author> authorOptional = authorService.findById(Long.parseLong(text));
        return authorOptional.orElse(null);
    }

    @Override
    public String print(Author object, Locale locale) {
        return "[" + object.getId() + ", " +object.getAuthorName() + "]";
    }
}
