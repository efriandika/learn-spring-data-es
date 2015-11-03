package com.efriandika.learn.repository.elasticsearch;

import com.efriandika.learn.entity.Author;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface AuthorEsRepository extends ElasticsearchCrudRepository<Author, Long> {

}
