package com.qfhp.openfeign.controller;

import com.qfhp.openfeign.feign.BookService;
import com.qfhp.service.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hu
 * @date 2022-03-18 14:43
 */
@RestController
public class HelloController {
    @Autowired
    BookService bookService;

    @GetMapping("/test01")
    public void test01() {
        Book b1 = bookService.getBook(10);
        System.out.println("b1 = " + b1);
        b1.setName("西游记");
        b1.setAuthor("吴承恩");
        Book b2 = bookService.addBook(b1);
        System.out.println("b2 = " + b2);
        bookService.putBook(b2);
        bookService.deleteBook(b2.getId());
    }
}
