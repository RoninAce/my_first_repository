package com.qfhp.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

/**
 * @author Hu
 * @date 2022-03-19 17:16
 */
public class HelloCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;

    public HelloCommand(RestTemplate restTemplate) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("aaa")));
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("http://storage/deduct",String.class);
    }

    @Override
    protected String getFallback() {
        return "error---fallback";
    }
}
