package com.example.school.service;

import com.example.school.repository.StudentRepository;
import com.example.school.repository.StudentTeacherRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.school.entity.Student;
import com.example.school.entity.Teacher;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final StudentRepository studentRepository;
    private final StudentTeacherRepository studentTeacherRepository;

    @Autowired
    public SchoolServiceImpl(StudentRepository studentRepository, StudentTeacherRepository studentTeacherRepository) {
        this.studentRepository = studentRepository;
        this.studentTeacherRepository = studentTeacherRepository;
    }

    @Override
    public List<Student> getAllStudents(List<Integer> ages) {
        if (ages == null || ages.isEmpty()) {
            return studentRepository.findAll();
        }
        return studentRepository.findByAge(ages);
    }

    @Override
    public List<Teacher> getTeacherInfoByStudentId(int studentId) {
        return studentTeacherRepository.findTeachersByStudentId(studentId);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(int id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}