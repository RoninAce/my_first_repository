package com.qfhp.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hu
 * @date 2022-03-19 15:32
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String hello(){
        int i = 1 / 0;
        String s = restTemplate.getForObject("http://storage/deduct", String.class);
        System.out.println("s = " + s);
        return s;
    }

    public String error(){
        return "error";
    }
}
