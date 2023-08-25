package com.lcwr.db.api;

import com.lcwr.db.api.mysql.entities.Book;
import com.lcwr.db.api.mysql.repository.BookRepository;
import com.lcwr.db.api.postgresql.entities.User;
import com.lcwr.db.api.postgresql.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.lcwr.db.api.*")
@PropertySource("application_postgresql.properties")
@PropertySource("application_mysql.properties")
public class SpringBootMultipleDsApplication {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultipleDsApplication.class, args);
    }

    /*
    Manually add some Users and Books with @PostConstruct
     */
    @PostConstruct
    public void addData2DB() {

        userRepository.saveAll(Stream.of(new User(333, "John"),
                new User(331, "Pitter")).collect(Collectors.toList()));
        bookRepository.saveAll(Stream.of(new Book(111, "Core Java"),
                new Book(222, "Spring Boot")).collect(Collectors.toList()));

    }

/*
get all books form db
 */

    /*
    get all uses form db
     */
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

}
