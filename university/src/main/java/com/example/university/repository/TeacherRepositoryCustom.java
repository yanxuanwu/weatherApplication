package com.example.university.repository;

import com.example.university.pojo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepositoryCustom extends JpaRepository<Teacher, String> {
    List<Teacher> findAllTeacher();
}
