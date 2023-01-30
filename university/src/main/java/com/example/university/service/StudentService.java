package com.example.university.service;

import com.example.university.pojo.dto.StudentResponseDTO;
import com.example.university.pojo.entity.Student;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "student-service", url = "http://localhost:8081/student-service")
public interface StudentService {
    StudentResponseDTO getAllStu();
    StudentResponseDTO.StudentDTO getStudentById(String id);
    StudentResponseDTO.StudentDTO insertStudent(Student student);
    StudentResponseDTO.StudentDTO updateStudent(Student student);
    void deleteStudentById(String id);
    void deleteStudent(Student student);
}
