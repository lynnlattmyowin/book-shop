package com.example.bookshop;

import com.example.bookshop.dao.*;
import com.example.bookshop.entity.*;
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
    private final RoleDao roleDao;


    @Bean
    @Transactional
    @Profile("security")
    public ApplicationRunner runner1() {
        return r -> {
            Role admin = new Role();
            admin.setRoleName("ROLE_ADMIN");
            Role user = new Role();
            user.setRoleName("ROLE_USER");

            roleDao.save(admin);
            roleDao.save(user);
        };
    }

    @Bean
    @Transactional
    @Profile("data")

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
                    "https://m.media-amazon.com/images/W/MEDIAX_792452-T2/images/I/51zYFogJw3L.jpg"
            );
            Book book1 = new Book(
                    2, IsbnGenerator.generate(),
                    "Great Expectation",
                    "GoodChoice",
                    30.5,
                    20,
                    "https://cdn.kobo.com/book-images/b300a5df-adb1-4d43-b48f-53e35f2804d4/353/569/90/False/great-expectations-30.jpg"
            );
            Book book2 = new Book(
                    3, IsbnGenerator.generate(),
                    "Bleak House",
                    "Nice",
                    40.0,
                    20,
                    "https://m.media-amazon.com/images/I/51OSlFe0I+L.jpg"
            );
            Book book3 = new Book(
                    4, IsbnGenerator.generate(),
                    "Under The Greenwood Tree",
                    "Excellent",
                    80.0,
                    20,
                    " https://m.media-amazon.com/images/I/51pG4gRLgkL.jpg"
            );
            Book book4 = new Book(
                    5, IsbnGenerator.generate(),
                    "Return Of  The Native",
                    "Nice",
                    70.0,
                    20,
                    "https://m.media-amazon.com/images/I/51JQgYHiEmL.jpg"
            );
            Book book5 = new Book(
                    6, IsbnGenerator.generate(),
                    "Far From the Maddening Crowd",
                    "Wow",
                    100.0,
                    20,
                    "https://m.media-amazon.com/images/I/51NgwIhjOoL.jpg"
            );

            Genre genre = new Genre();
            genre.setGenreName("Tragedy");

            Genre genre1 = new Genre();
            genre1.setGenreName("Adventure");

            Publisher publisher = new Publisher("New Era", "new@gmail.com");
            Publisher publisher1 = new Publisher("Old Era", "old@gmail.com");


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

            Publisher pub2 = publisherDao.save(publisher1);
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
