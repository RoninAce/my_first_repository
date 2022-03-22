package com.qfhp.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.qfhp.service.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hu
 * @date 2022-03-19 20:27
 */
@Service
public class BookService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "nextBook")
    @CacheResult
    public Book getBook(Integer id){
        return restTemplate.getForObject("http://storage/book/?id={1}",Book.class,id);
    }

    public Book nextBook(Integer id){
        Book book = new Book();
        book.setId(id);
        System.out.println("服务降级了");
        return book;
    }
}
