package com.nomi.student_course.controller;

import com.nomi.student_course.model.Student;
import com.nomi.student_course.service.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentServiceImp studentServiceImp;

    @PostMapping("/students")
    private Student createStudent(@RequestBody Student student){
        return studentServiceImp.createStudent(student);
    }
    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Long id){
        System.out.println("------------>>"+id);
        return studentServiceImp.getStudentById(id);
    }
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentServiceImp.getAllStudents();
    }

    @PutMapping("/students/update")
    public Student updateStudent(@RequestBody Student student){
        return studentServiceImp.updateStudent(student);
    }

    @DeleteMapping("/students/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        studentServiceImp.deleteStudent(id);
    }


    @PostMapping("/students/{studentId}/enroll/{courseId}")
    public void enrollStudentInCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        studentServiceImp.enrollStudentInCourse(studentId, courseId);

    }
    @PostMapping("/students/{studentId}/unenroll/{courseId}")
    public void unenrollStudentFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        studentServiceImp.unenrollStudentFromCourse(studentId, courseId);

    }
}
