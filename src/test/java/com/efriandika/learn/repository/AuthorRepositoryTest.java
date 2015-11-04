package com.efriandika.learn.repository;

import com.efriandika.learn.LearnSpringDataEsApplication;
import com.efriandika.learn.repository.elasticsearch.AuthorEsRepository;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LearnSpringDataEsApplication.class)
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorEsRepository authorEsRepository;

    @Before
    public void setup(){
        authorRepository.deleteAll();
        authorEsRepository.deleteAll();
    }

    @Test
    public void testCurrentData(){
        Assert.isTrue(Lists.newArrayList(authorEsRepository.findAll()).size() == 0);
        Assert.isTrue(authorRepository.findAll().size() == 0);
    }


}
