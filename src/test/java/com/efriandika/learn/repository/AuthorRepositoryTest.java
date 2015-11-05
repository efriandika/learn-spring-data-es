package com.efriandika.learn.repository;

import com.efriandika.learn.LearnSpringDataEsApplication;
import com.efriandika.learn.entity.Author;
import com.efriandika.learn.entity.QAuthor;
import com.efriandika.learn.repository.elasticsearch.AuthorEsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LearnSpringDataEsApplication.class)
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorEsRepository authorEsRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Before
    @After
    public void setup(){
        authorRepository.deleteAll();
        authorEsRepository.deleteAll();
    }

    @Test
    public void testInsertData(){
        // Insert with JPA
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("efriandika", "Efriandika Pratama", "efriandika@gmail.com"));
        authors.add(new Author("efrian", "Efrian Dika", "efrian@gmail.com"));
        authorRepository.save(authors);

        Assert.isTrue(authorRepository.count() == 2 && authorEsRepository.count() == 0);

        // Insert to ES
        authorEsRepository.save(authorRepository.findAll());
        Assert.isTrue(authorEsRepository.count() == authorRepository.count());

        // Checking Data
        Author authorFromJpa = authorRepository.findOne(QAuthor.author.username.eq("efriandika"));
        Author authorFromEs = authorEsRepository.findByUsername("efriandika");

        Assert.isTrue(authorFromEs.getUsername().equals(authorFromJpa.getUsername()) && authorFromEs.getId().equals(authorFromJpa.getId()));
    }

    @Test
    public void search(){
        // Prepare Data
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("efriandi kacang", "Efria", "efr@gmail.com"));
        authors.add(new Author("efriandika", "Efriandika Pratama", "efriandika@gmail.com"));
        authors.add(new Author("efrian", "Efrian Dika", "efrian@gmail.com"));
        authors.add(new Author("dika", "Febrian febriano", "dika@gmail.com"));
        authors.add(new Author("febriandi", "Efriandika Pratama", "febriandi@gmail.com"));
        authors.add(new Author("febriana", "Riska Febriana", "riska@gmail.com"));
        authors.add(new Author("febriani", "Riska Febriani", "riskai@gmail.com"));
        authorRepository.save(authors);
        authorEsRepository.save(authorRepository.findAll());

        // Searching
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(matchAllQuery());

        Page<Author> results = authorEsRepository.search(queryBuilder.build());
    }
}
