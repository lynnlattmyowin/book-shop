package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String publisherName;
    private String publisherUrl;
    @OneToMany (mappedBy = "publisher",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Book> books =
            new ArrayList<>();

    public Publisher(String publisherName, String publisherUrl) {
        this.publisherName = publisherName;
        this.publisherUrl = publisherUrl;
    }

    public  void addBook(Book book){
        book.setPublisher(this);
        books.add(book);
    }

}
