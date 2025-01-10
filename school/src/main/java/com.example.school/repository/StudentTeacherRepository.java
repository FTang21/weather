package com.example.school.repository;

import com.example.school.entity.StudentTeacher;
import com.example.school.entity.Teacher;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTeacherRepository extends JpaRepository<StudentTeacher, Integer> {
    @Query("SELECT st.teacher FROM StudentTeacher st WHERE st.student.id = :studentId")
    List<Teacher> findTeachersByStudentId(@Param("studentId") int studentId);
}
