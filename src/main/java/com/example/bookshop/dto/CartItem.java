package com.example.bookshop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private int id;
    private String isbn;
    private String title;
    private double price;
    private int quantity = 1;
    private List<Integer> cardItemQuantity = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return id == cartItem.id && Objects.equals(isbn, cartItem.isbn);
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}
