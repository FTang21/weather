package com.example.university.service;

import com.example.university.exception.NotValidCountryException;
import com.example.university.pojo.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UniversityServiceImpl implements UniversityService {

    private final RestTemplate restTemplate;
    private final String API_URL = "http://universities.hipolabs.com/search";

    @Autowired
    public UniversityServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> getAllUniversities() {
        University[] universities = restTemplate.getForObject(API_URL, University[].class);
        return Arrays.stream(universities)
                .map(University::getName)
                .collect(Collectors.toList());
    }

    public List<String> getUniversitiesByCountries(List<String> countries) {
        List<CompletableFuture<List<String>>> futures = countries.stream()
                .map(country -> CompletableFuture.supplyAsync(() -> getUniversitiesByCountry(country)))
                .collect(Collectors.toList());
        List<String> allUniversityNames = futures.stream()
                .flatMap(future -> future.join().stream())
                .collect(Collectors.toList());
        return allUniversityNames;
    }

    public List<String> getUniversitiesByCountry(String country) {
        String url = API_URL + "?country=" + country;
        try {
            University[] universities = restTemplate.getForObject(url, University[].class);
            return Arrays.stream(universities)
                    .map(University::getName)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new NotValidCountryException(country + " is not a valid country");
        }
    }
}
