package com.nomi.student_course.service;

import com.nomi.student_course.model.Course;
import com.nomi.student_course.model.Student;
import com.nomi.student_course.repository.CourseRepository;
import com.nomi.student_course.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course createCourse(Course course) {
        Course existCourse=courseRepository.findCourseByName(course.getName());

        if (existCourse!=null){
            throw new EntityNotFoundException("Course already register with name: "+course.getName());
        }
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.findCourseByName(courseName);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course) {
        Optional<Course> existCourse=courseRepository.findById(course.getId());
        if(!existCourse.isPresent()){
            throw  new EntityNotFoundException("Course not found with name: "+course.getName());
        }
        existCourse.get().setName(course.getName());
        Course saved=courseRepository.save(existCourse.get());
        courseRepository.save(saved);
        return existCourse.get();
    }

    @Override
    public void deleteCourse(Long courseId) {
        Optional<Course> check=courseRepository.findById(courseId);
        if(!check.isPresent()){
            throw new EntityNotFoundException("Course doesn't exist with id: " + courseId);
        }
        courseRepository.delete(check.get());
    }

    @Override
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

        // Add the student to the course and update both entities
        course.getStudents().add(student);
        student.getCourses().add(course);
        studentRepository.save(student);
        courseRepository.save(course);
    }

    @Override
    public void unenrollStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

        // Remove the student from the course and update both entities
        course.getStudents().remove(student);
        student.getCourses().remove(course);
        studentRepository.save(student);
        courseRepository.save(course);
    }
}
