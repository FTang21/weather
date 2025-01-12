package com.example.university.controller;

import com.example.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RefreshScope
@RestController
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    };

    @GetMapping("/university")
    public ResponseEntity<List<String>> getUniversitiesByCountries(@RequestParam(required = false) List<String> country) {
        if (country == null || country.isEmpty()) {
            return new ResponseEntity<>(
                    universityService.getAllUniversities(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                universityService.getUniversitiesByCountries(country),
                HttpStatus.OK
        );
    }
}
