package com.efriandika.learn.repository;


import com.efriandika.learn.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface AuthorRepository extends JpaRepository<Author, Long>, QueryDslPredicateExecutor<Author> {

}
