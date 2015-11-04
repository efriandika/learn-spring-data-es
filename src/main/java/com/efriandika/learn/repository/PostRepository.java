package com.efriandika.learn.repository;

import com.efriandika.learn.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>, QueryDslPredicateExecutor<Post> {

}
