package com.efriandika.learn.listener;

import com.efriandika.learn.entity.Author;
import com.efriandika.learn.repository.elasticsearch.AuthorEsRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;

/**
 * @author efriandika
 */
@Component
public class IndexingAuthorListener implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(final ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    @PostUpdate
    @PostPersist
    public void putToEs(Author author){
        System.out.println("************ Indexing Author to ES ************");
        AuthorEsRepository authorEsRepository = applicationContext.getBean(AuthorEsRepository.class);

        if(author != null) {
            authorEsRepository.save(author);
            System.out.println(author.toString());
        }

    }

    @PreRemove
    public void removeFromEs(Author author){
        System.out.println("************ Remove deleted Author from ES ************");
        AuthorEsRepository authorEsRepository = applicationContext.getBean(AuthorEsRepository.class);

        if(author != null && authorEsRepository.findOne(author.getId()) != null) {
            authorEsRepository.delete(author);
            System.out.println(author.toString());
        }
    }
}
