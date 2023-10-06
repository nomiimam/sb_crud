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
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Student createStudent(Student student) {

      Student exist=studentRepository.findByEmail(student.getEmail());
      if(exist!=null){
          throw  new EntityNotFoundException("Student already register with email: "+student.getEmail());
      }
      return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> existStudent=studentRepository.findById(id);
        if(!existStudent.isPresent()){
            throw  new EntityNotFoundException("Student not found with id: "+id);
        }
        return existStudent.get();

    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student) {
        Student newStd=studentRepository.findByEmail(student.getEmail());
        if(newStd==null){
            throw  new EntityNotFoundException("Student not found with email: "+student.getEmail());
        }
        newStd.setFullName(student.getFullName());
        newStd.setCourses(student.getCourses());
        Student saved=studentRepository.save(newStd);
        studentRepository.save(saved);
        return newStd;
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> exist=studentRepository.findById(id);
        if(!exist.isPresent()) {
            throw new EntityNotFoundException("Student doesn't exist with id: " + id);
        }
        studentRepository.delete(exist.get());

    }

    @Override
    public void enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseId));

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
