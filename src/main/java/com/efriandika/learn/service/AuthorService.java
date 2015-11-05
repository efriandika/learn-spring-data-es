package com.efriandika.learn.service;

import com.efriandika.learn.entity.Author;
import com.mysema.query.types.Predicate;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);
    List<Author> createAuthor(Iterable<Author> authors);
    Author updateAuthor(Author author);
    Author getAuthor(Long authorId);
    Author getAuthor(Predicate predicate);
    Iterable<Author> getAuthors();
    Iterable<Author> getAuthors(Predicate predicate);
    void deleteAuthor(Long authorId);
    void deleteAllAuthor();
}
