package com.nomi.student_course.service;

import com.nomi.student_course.model.Student;

import java.util.List;

public interface StudentService {
    public Student createStudent(Student student);
    public Student getStudentById(Long id);
    public List<Student> getAllStudents();
    public Student updateStudent(Student student);
    public void deleteStudent(Long id);
    public void enrollStudentInCourse(Long studentId,Long courseId);
    public void unenrollStudentFromCourse(Long studentId, Long courseId);
}
