package com.example.bookshop.controller;

import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.BookId;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final BookService bookService;

    //cart/view-cart
    @GetMapping("/view-cart")
    public String viewCart(Model model){
        model.addAttribute("cartItems",cartService.getCartItems());
        model.addAttribute("cartItem",new CartItem());

        return "viewCart";
    }

    @GetMapping("/add-cart")
    public String addToCart(@RequestParam("id")int id,
                            @RequestParam("isbn")String isbn,
                            @RequestParam("page")String page){
        BookId bookId = new BookId();
        bookId.setIsbn(isbn);
        bookId.setId(id);
        Book book = bookService.findBookById(bookId);
        cartService.addToCart(book);
        if (page.equals("booklist")){
            return "redirect:/book/list-books";
        }
        else {
            return "redirect:/book/book-details?id="+id+"&isbn="+isbn;
        }
    }

    @GetMapping("/delete")
    public String deleteCartItem(@RequestParam("id")int id,
                                 @RequestParam("isbn")String isbn){
        cartService.deleteCartItem(id,isbn);
        return "redirect:/cart/view-cart";
    }
    @GetMapping("/clear-cart")
    public String clearCart(){
        cartService.clearCart();
        return "redirect:/cart/view-cart";
    }
    @PostMapping("/checkout")
    public String checkOut(CartItem cartItem){
//        cartItem.getCardItemQuantity().forEach(System.out::println);
//        if (cartItem.getCardItemQuantity().size()==0){
//            for (CartItem item:cartService.getCartItems()){
//                cartItem.getCardItemQuantity().add(1);
//            }
//        }
//        cartService.=cartService.getCartItems()
//                .stream()
//                .map(c -> c.getCardItemQuantity().get(1));
        int i  =0 ;
        for (CartItem item:cartService.getCartItems()){
            if (cartItem.getCardItemQuantity().get(i)==null){
                item.setQuantity(1);
            }else {
                item.setQuantity(cartItem.getCardItemQuantity().get(i));
            }
            i++;
        }

        cartService.getCartItems().forEach(System.out::println);
        return "redirect:/cart/view-cart";
    }
}
