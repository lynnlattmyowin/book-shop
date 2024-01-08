package com.example.bookshop;

import com.example.bookshop.dao.AuthorDao;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.dao.GenreDao;
import com.example.bookshop.dao.PublisherDao;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Genre;
import com.example.bookshop.entity.Publisher;
import com.example.bookshop.util.IsbnGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;

    @Bean
    public ApplicationRunner runner(){
        return r -> {
            Author author = new Author("Charles Dickens","charles@gmail.com");
            Author author1 = new Author("Thomas Hardy","thomas@gmail.com");

            Book book = new Book(
                    1, IsbnGenerator.generate(),
                    "Oliver Twist",
                    "Excellent",
                    100.0,
                    20,
                    "https://source.unplash.com/400x300/?flower"

            );
            Book book1 = new Book();
            Book book2 = new Book();
            Book book3 = new Book();

            Genre genre = new Genre();
            genre.setGenreName("Tragedy");
            Genre genre1 = new Genre();
            genre1.setGenreName("Adventure");

            Publisher publisher = new Publisher("New Era","new@gmail.com");
            Publisher publisher1 = new Publisher("","");

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
