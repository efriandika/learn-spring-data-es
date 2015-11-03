package com.efriandika.learn.service;

import com.efriandika.learn.entity.Author;
import com.mysema.query.types.Predicate;

public interface AuthorService {
    Author createAuthor(Author author);
    Author updateAuthor(Author author);
    Author getAuthor(Long authorId);
    Author getAuthor(Predicate predicate);
    Iterable<Author> getAuthors();
    Iterable<Author> getAuthors(Predicate predicate);
    void deleteAuthor(Long authorId);
    void deleteAllAuthor();
}
