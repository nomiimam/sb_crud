package com.nomi.student_course.service;

import com.nomi.student_course.model.Course;

import java.util.List;

public interface CourseService {
    public Course createCourse(Course course);
    public Course getCourseByName(String courseName);
    public List<Course> getAllCourse();
    public Course updateCourse(Course course);
    public void deleteCourse(Long courseId);
    public void enrollStudentInCourse(Long studentId, Long courseId);
    public void unenrollStudentFromCourse(Long studentId, Long courseId);
}
