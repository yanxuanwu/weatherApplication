package com.example.university.repository;


import com.example.university.pojo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositoryCustom extends JpaRepository<Student, String> {

    List<Student> findAll();

}