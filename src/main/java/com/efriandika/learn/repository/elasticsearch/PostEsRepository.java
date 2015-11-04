package com.efriandika.learn.repository.elasticsearch;

import com.efriandika.learn.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface PostEsRepository extends ElasticsearchCrudRepository<Post, Long> {

}
