package com.efriandika.learn.repository.elasticsearch;

import com.efriandika.learn.entity.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostEsRepository extends ElasticsearchRepository<Post, Long> {

}
