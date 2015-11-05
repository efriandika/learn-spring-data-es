package com.efriandika.learn.repository.elasticsearch;

import com.efriandika.learn.entity.Author;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AuthorEsRepository extends ElasticsearchRepository<Author, Long> {
    Author findByUsername(String username);
}
