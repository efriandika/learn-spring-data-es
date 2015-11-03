package com.efriandika.learn;

import com.efriandika.learn.entity.Author;
import com.efriandika.learn.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringDataEsApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(LearnSpringDataEsApplication.class);

    @Autowired
    private AuthorService authorService;

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringDataEsApplication.class, args);
        // SpringApplication.run(LearnSpringDataEsApplication.class, "--debug").close();
    }

    @Override
    public void run(String... strings) throws Exception {
        authorService.deleteAllAuthor();
        createAuthor();
        fetchAllAuthor();
    }

    private void createAuthor(){
        LOGGER.debug("Creating author...");

        authorService.createAuthor(new Author("efriandika", "Efriandika Pratama", "efriandika@gmail.com"));
        authorService.createAuthor(new Author("riska", "Riska Ferbiana", "riska@gmail.com"));
        authorService.createAuthor(new Author("coba.tiga", "Coba User", "coba@gmail.com"));
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
