package com.qfhp.service;


import org.springframework.web.bind.annotation.*;

//@RestController
public interface IBookController {
    @GetMapping("/book/")
    Book getBook(@RequestParam("id") Integer id);

    @PostMapping("/book/")
    Book addBook(@RequestBody Book book);

    @PutMapping("/book/")
    void putBook(@RequestBody Book book);

    @DeleteMapping("/book/{id}")
    void deleteBook(@PathVariable("id") Integer id);
}
