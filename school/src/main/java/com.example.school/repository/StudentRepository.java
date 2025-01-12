package com.example.school.repository;

import com.example.school.entity.Student;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.age in :ages")
    List<Student> findByAge(List<Integer> ages);
}
