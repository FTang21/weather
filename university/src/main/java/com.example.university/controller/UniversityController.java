package com.example.university.controller;

import com.example.university.service.UniversityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    }

    @GetMapping("/university")
    @ApiOperation(value = "Get Universities", notes = "Get Universities filter by Countries")
    public ResponseEntity<List<String>> getUniversitiesByCountries(
            @ApiParam(value = "Parameter to filter by given countries") @RequestParam(required = false) List<String> country) {
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
