package com.nomi.student_course.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @NaturalId(mutable = true)
    private String email;
    @ManyToMany(mappedBy = "students")
    private List<Course> courses=new ArrayList<>();

}
