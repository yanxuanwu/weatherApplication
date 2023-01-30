package com.example.university.service;

import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import com.example.university.pojo.entity.Teacher;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient(name = "teacher-service", url = "http://localhost:8081/teacher-service")
public interface TeacherService {
    TeacherResponseDTO getAllTeacher();
    TeacherDTO getTeacherById(String id);
    TeacherDTO insertTeacher(Teacher teacher);
    TeacherDTO updateTeacher(Teacher teacher);
    void deleteTeacherById(String id);
    void deleteTeacher(Teacher teacher);
}
