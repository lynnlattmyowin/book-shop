package com.example.bookshop.service;

import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.Book;
import lombok.RequiredArgsConstructor;
import org.hibernate.metamodel.mapping.internal.EntityCollectionPart;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartBean cartBean;

    public void addToCart(Book book) {
        cartBean.addCardItem(toCartItem(book));
    }

    public Integer cartSize() {
        return cartBean.cartSize();
    }


    public Set<CartItem> getCartItems(){
        return cartBean.getCartItems();
    }
    private CartItem toCartItem(Book book) {
        return new CartItem(
                book.getId(),
                book.getIsbn(),
                book.getTitle(),
                book.getPrice(),
                1
        );


    }

    public void deleteCartItem(int id, String isbn) {
        cartBean.deleteCartItem(id,isbn);
    }

}
