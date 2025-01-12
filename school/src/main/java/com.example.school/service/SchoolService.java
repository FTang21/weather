package com.example.school.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.school.entity.Student;
import com.example.school.entity.Teacher;

@Service
public interface SchoolService {
    public List<Student> getAllStudents(List<Integer> age);
    public List<Teacher> getTeacherInfoByStudentId(int studentId);
    public Student createStudent(Student student);
    public Student updateStudent(int id, Student student);
    public void deleteStudent(int id);
}
