package com.efriandika.learn;

import com.efriandika.learn.entity.Author;
import com.efriandika.learn.repository.elasticsearch.AuthorEsRepository;
import com.efriandika.learn.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@SpringBootApplication
@EnableJpaRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JpaRepository.class))
@EnableElasticsearchRepositories(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ElasticsearchCrudRepository.class))
public class LearnSpringDataEsApplication implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorEsRepository authorEsRepository;

    @Autowired
    private ElasticsearchTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringDataEsApplication.class, args);
        // SpringApplication.run(LearnSpringDataEsApplication.class, "--debug").close();
    }

    @Override
    public void run(String... strings) throws Exception {
        template.deleteIndex(Author.class); // Delete Index: Author Entity
        template.createIndex(Author.class);
        template.refresh(Author.class, true);
        template.putMapping(Author.class);

        authorService.deleteAllAuthor();
        createAuthor();
        // indexing();
        fetchAuthorFromEs();
        fetchAllAuthor();
        searchFromEs("efriandika");
    }

    private void createAuthor(){
        System.out.println("Creating author:");
        System.out.println("-------------------------------");

        List<Author> authors = new ArrayList<>();
        authors.add(new Author("efriandi kacang", "Efria", "efr@gmail.com"));
        authors.add(new Author("efriandika", "Efriandika Pratama", "efriandika@gmail.com"));
        authors.add(new Author("efrian", "Efrian Dika", "efrian@gmail.com"));
        authors.add(new Author("dika", "Febrian febriano", "dika@gmail.com"));
        authors.add(new Author("febriandi", "Efriandika Pratama", "febriandi@gmail.com"));
        authors.add(new Author("febriana", "Riska Febriana", "riska@gmail.com"));
        authors.add(new Author("febriani", "Riska Febriani", "riskai@gmail.com"));
        authorService.createAuthor(authors);

        System.out.println();
    }

    private void indexing(){
        System.out.println("Indexing author:");
        System.out.println("-------------------------------");

        authorEsRepository.save(authorService.getAuthors());

        System.out.println();
    }

    private void fetchAuthorFromEs(){
        System.out.println("Fetch author from elastic:");
        System.out.println("-------------------------------");

        for (Author author : authorEsRepository.findAll()) {
            System.out.println(author.toString());
        }

        System.out.println();
    }

    private void fetchAllAuthor() {
        System.out.println("Author found with findAll():");
        System.out.println("-------------------------------");
        for (Author author : authorService.getAuthors()) {
            System.out.println(author.toString());
        }
        System.out.println();
    }

    public void searchFromEs(String keyword){
        System.out.println("Searching from ES:");
        System.out.println("-------------------------------");

        // Searching
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(multiMatchQuery(keyword, "fullname", "username"));

        Page<Author> results = authorEsRepository.search(queryBuilder.build());

        for(Author author:results){
            System.out.println(author.toString());
        }
    }
}
