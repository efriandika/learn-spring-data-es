package com.efriandika.learn;

import com.efriandika.learn.entity.Author;
import com.efriandika.learn.repository.elasticsearch.AuthorEsRepository;
import com.efriandika.learn.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.efriandika.learn.repository")
@EnableElasticsearchRepositories(basePackages = "com.efriandika.learn.repository.elasticsearch")
public class LearnSpringDataEsApplication implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorEsRepository authorEsRepository;

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringDataEsApplication.class, args);
        // SpringApplication.run(LearnSpringDataEsApplication.class, "--debug").close();
    }

    @Override
    public void run(String... strings) throws Exception {
        // authorService.deleteAllAuthor();
        createAuthor();
        indexAuthor();
        fetchAuthorFromEs();
        fetchAllAuthor();
    }

    private void createAuthor(){
        System.out.println("Creating author:");
        System.out.println("-------------------------------");

        authorService.createAuthor(new Author("efriandika", "Efriandika Pratama", "efriandika@gmail.com"));
        authorService.createAuthor(new Author("riska", "Riska Ferbiana", "riska@gmail.com"));
        authorService.createAuthor(new Author("coba.tiga", "Coba User", "coba@gmail.com"));

        System.out.println();
    }

    private void indexAuthor(){
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
}
