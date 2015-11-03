package com.efriandika.learn.service;


import com.efriandika.learn.entity.Author;
import com.efriandika.learn.repository.AuthorRepository;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JpaRepositoryAuthorService implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getAuthor(Long authorId) {
        return authorRepository.findOne(authorId);
    }

    @Override
    public Author getAuthor(Predicate predicate) {
        return authorRepository.findOne(predicate);
    }

    @Override
    public Iterable<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Iterable<Author> getAuthors(Predicate predicate) {
        return authorRepository.findAll(predicate);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteAuthor(Long authorId) {
        authorRepository.delete(authorId);
    }

    @Override
    public void deleteAllAuthor() {
        authorRepository.deleteAll();
    }
}
