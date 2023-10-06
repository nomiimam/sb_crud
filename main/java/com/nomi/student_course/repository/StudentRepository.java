package com.nomi.student_course.repository;

import com.nomi.student_course.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    public Student findByEmail(String email);

}
