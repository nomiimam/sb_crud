package com.nomi.student_course.controller;

import com.nomi.student_course.model.Course;
import com.nomi.student_course.service.CourseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    private CourseServiceImp courseServiceImp;

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course){
        return courseServiceImp.createCourse(course);
    }

    @GetMapping("/courses/{courseNmae}")
    public Course getCourseByName(@PathVariable("courseNmae") String courseNmae){
        return courseServiceImp.getCourseByName(courseNmae);
    }
    @GetMapping("/courses")
    public List<Course> getAllCourse(){
        return courseServiceImp.getAllCourse();
    }

    @PutMapping("/courses/update/")
    public Course updateCourse(@RequestBody Course course){
        return courseServiceImp.updateCourse(course);
    }

    @DeleteMapping("/courses/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
        courseServiceImp.deleteCourse(id);
    }
    // Enroll a student in a course
    @PostMapping("/courses/{courseId}/enroll/{studentId}")
    public void enrollStudentInCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        courseServiceImp.enrollStudentInCourse(studentId, courseId);
    }

    // Unenroll a student from a course
    @PostMapping("/courses/{courseId}/unenroll/{studentId}")
    public void unenrollStudentFromCourse(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        courseServiceImp.unenrollStudentFromCourse(studentId, courseId);
    }


}
