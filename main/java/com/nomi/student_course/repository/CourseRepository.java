package com.nomi.student_course.repository;

import com.nomi.student_course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
   public Course findCourseByName(String courseName);
}
