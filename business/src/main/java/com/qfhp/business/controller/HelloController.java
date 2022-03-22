package com.qfhp.business.controller;

import com.qfhp.service.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hu
 * @date 2022-03-17 15:26
 */
@RestController
public class HelloController {

    @Autowired
    DiscoveryClient discoveryClient;

    private AtomicInteger con = new AtomicInteger(1);

    @GetMapping("/hello")
    public void hello(){
        List<ServiceInstance> list = discoveryClient.getInstances("storage");
        ServiceInstance instance = list.get(con.getAndAdd(1) % list.size());
        String host = instance.getHost();
        int port = instance.getPort();
        try {
            URL url = new URL("http://" + host + ":" + port + "/deduct");
            HttpURLConnection content = (HttpURLConnection) url.openConnection();
            if (content.getResponseCode()==200){
                BufferedReader br = new BufferedReader(new InputStreamReader(content.getInputStream()));
                String s = br.readLine();
                System.out.println("s = " + s);
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hello01")
    public void hello01() {
        System.out.println("<================GET=================>");
        ResponseEntity<Book> entity = restTemplate.getForEntity("http://storage/book/?id={1}", Book.class, 10);
        Book b1 = entity.getBody();
        b1.setName("三国演义");
        b1.setAuthor("罗贯中");
        System.out.println("b1 = " + b1);
        System.out.println("entity.getStatusCode() = " + entity.getStatusCode());
        System.out.println("entity.getStatusCodeValue() = " + entity.getStatusCodeValue());
        HttpHeaders headers = entity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String key : keySet) {
            List<String> list = headers.get(key);
            System.out.println("list = " + list);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("id",11);
        ResponseEntity<Book> entity1 = restTemplate.getForEntity("http://storage/book/?id={id}", Book.class, params);
        Book b2 = entity1.getBody();
        System.out.println("b2 = " + b2);
        System.out.println("<================POST================>");
        MultiValueMap<String,Object> p = new LinkedMultiValueMap<>();
        p.add("id",12);
        p.add("name","水浒传");
        p.add("author","施耐庵");
        Book b3 = restTemplate.postForObject("http://storage/book/", p, Book.class);
        System.out.println("b3 = " + b3);
        System.out.println("<================PUT================>");
        restTemplate.put("http://storage/book/",b1);
        System.out.println("<================DELETE================>");
        restTemplate.delete("http://storage/book/{1}",100);
    }
}
