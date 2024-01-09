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
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class BookShopApplication {
    private final AuthorDao authorDao;
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final PublisherDao publisherDao;


    @Bean
    @Transactional @Profile("data")
    public ApplicationRunner runner() {
        return r -> {
            Author author = new Author("Charles Dickens", "charles@gmail.com");
            Author author1 = new Author("Thomas Hardy", "thomas@gmail.com");

            Book book = new Book(
                    1, IsbnGenerator.generate(),
                    "Oliver Twist",
                    "Excellent",
                    100.0,
                    20,
                    "https://source.unplash.com/400x300/?flower"

            );
            Book book1 = new Book(
                    2, IsbnGenerator.generate(),
                    "Great Expectation",
                    "GoodChoice",
                    30.5,
                    20,
                    "https://source.unplash.com/400x300/?nature"

            );
            Book book2 = new Book(
                    3, IsbnGenerator.generate(),
                    "Bleak House",
                    "Nice",
                    40.0,
                    20,
                    "https://source.unplash.com/400x300/?ocean"

            );
            Book book3 = new Book(
                    4, IsbnGenerator.generate(),
                    "Under The Greenwood Tree",
                    "Excellent",
                    80.0,
                    20,
                    "https://source.unplash.com/400x300/?flower"

            );
            Book book4 = new Book(
                    1, IsbnGenerator.generate(),
                    "Return Of  The Native",
                    "Nice",
                    70.0,
                    20,
                    "https://source.unplash.com/400x300/?flower"

            );
            Book book5 = new Book(
                    1, IsbnGenerator.generate(),
                    "Far From the Maddening Crowd",
                    "Wow,Your Choice was good",
                    100.0,
                    20,
                    "https://source.unplash.com/400x300/?flower"

            );

            Genre genre = new Genre();
            genre.setGenreName("Tragedy");

            Genre genre1 = new Genre();
            genre1.setGenreName("Adventure");

            Publisher publisher = new Publisher("New Era", "new@gmail.com");
            Publisher publisher1 = new Publisher("", "");



            //mapping
            author.addBook(book);
            author.addBook(book1);
            author.addBook(book2);

            author1.addBook(book3);
            author1.addBook(book4);
            author1.addBook(book5);

            Publisher pub1 = publisherDao.save(publisher);
            pub1.addBook(book);
            pub1.addBook(book1);
            pub1.addBook(book2);

            Publisher pub2  = publisherDao.save(publisher1);
            pub2.addBook(book3);
            pub2.addBook(book4);
            pub2.addBook(book5);

            Genre gen1 = genreDao.save(genre);
            book.addGenres(gen1);
            book1.addGenres(gen1);
            book2.addGenres(gen1);

            Genre gen2 = genreDao.save(genre1);
            book3.addGenres(gen2);
            book4.addGenres(gen2);
            book5.addGenres(gen2);


//            bookDao.save(book);
//            bookDao.save(book1);
//            bookDao.save(book2);
//            bookDao.save(book3);
//            bookDao.save(book4);
//            bookDao.save(book5);

            authorDao.save(author);
            authorDao.save(author1);



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BookShopApplication.class, args);
    }

}
