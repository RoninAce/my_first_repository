package com.qfhp.hystrix.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.qfhp.hystrix.service.BookService;
import com.qfhp.hystrix.service.HelloCommand;
import com.qfhp.hystrix.service.HelloService;
import com.qfhp.service.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hu
 * @date 2022-03-19 15:45
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @Autowired
    BookService bookService;

    @GetMapping("/01")
    public void getBook() throws InterruptedException {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();
        Book b1 = bookService.getBook(10);
        Book b2 = bookService.getBook(10);
        Book b3 = bookService.getBook(10);
        hrc.close();
        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println("b3 = " + b3);
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.hello();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello1")
    public String hello1(){
        HelloCommand helloCommand = new HelloCommand(restTemplate);
        String execute = helloCommand.execute();
        return execute;
    }
}
