package com.example.university.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UniversityService {
    List<String> getAllUniversities();
    List<String> getUniversitiesByCountry(String universityName);
    List<String> getUniversitiesByCountries(List<String> countries);
}
