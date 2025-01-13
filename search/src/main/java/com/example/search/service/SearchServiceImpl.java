package com.example.search.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.org.apache.xpath.internal.operations.String;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchServiceImpl implements SearchService{

    private final RestTemplate restTemplate;

    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "fallbackSearch")
    public Object[] search() {
        CompletableFuture<String[]> universityFuture = CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject("http://localhost:9000/university", String[].class)
        );
        CompletableFuture<String> detailsFuture = CompletableFuture.supplyAsync(
                () -> restTemplate.getForObject("http://localhost:9400/details/port", String.class)
        );
        CompletableFuture<Object[]> resultFuture = universityFuture.thenCombine(detailsFuture,
                (university, details) -> {
                    return Stream.concat(Stream.of(details), Arrays.stream(university)).toArray();
                });
        return resultFuture.join();
    }

    public Object[] fallbackSearch() {
        return new Object[]{"Something went wrong"};
    }
}
