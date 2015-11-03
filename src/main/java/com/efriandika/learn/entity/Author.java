package com.efriandika.learn.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="learn_author")
@Document(indexName = "author", type = "author", shards = 1, replicas = 0)
public class Author implements Serializable {

    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="fullname")
    private String fullname;

    @Column(name="email")
    private String email;

    public Author() {

    }

    public Author(String username, String fullname, String email) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
