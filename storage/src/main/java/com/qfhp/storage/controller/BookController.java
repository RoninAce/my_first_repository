package com.qfhp.storage.controller;

import com.qfhp.service.Book;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hu
 * @date 2022-03-17 19:34
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping("/")
    public Book getBook(Integer id){
        Book book = new Book();
        book.setId(id);
        System.out.println("book = " + book);
        return book;
    }

    /*@PostMapping("/")
    public Book addBook(Book book){
        return book;
    }*/
    @PostMapping("/")
    public Book addBook(@RequestBody Book book){
        return book;
    }

    @PutMapping("/")
    public void putBook(@RequestBody Book book){
        System.out.println("book = " + book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id){
        System.out.println("id = " + id);
    }
}
