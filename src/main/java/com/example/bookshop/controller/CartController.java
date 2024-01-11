package com.example.bookshop.controller;

import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.BookId;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
