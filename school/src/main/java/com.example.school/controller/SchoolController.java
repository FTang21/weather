package com.example.school.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.example.school.entity.Student;
import com.example.school.entity.Teacher;
import com.example.school.service.SchoolService;

@RefreshScope
@RestController
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping(value = "/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(
                schoolService.getAllStudents(new ArrayList<>()),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/students", params = {"age"})
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam(value = "age", required = false) List<Integer> age) {
        return new ResponseEntity<>(
                schoolService.getAllStudents(age),
                HttpStatus.OK
        );
    }

    @GetMapping("/teachers/{student_id}")
    public ResponseEntity<List<Teacher>> getAllTeachersByStudentId(@PathVariable("student_id") int id) {
        return new ResponseEntity<>(
                schoolService.getTeacherInfoByStudentId(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(
                schoolService.createStudent(student),
                HttpStatus.OK
        );
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        return new ResponseEntity<>(
                schoolService.updateStudent(id, student),
                HttpStatus.OK
        );
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
