package com.qfhp.storage.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hu
 * @date 2022-03-17 15:27
 */
@RestController
public class StorageController {

    @Value("${server.port}")
    Integer port;

    @GetMapping("/deduct")
    public String deduct(){
        System.out.println("deduct:"+port);
        return "deduct:"+port;
    }
}
