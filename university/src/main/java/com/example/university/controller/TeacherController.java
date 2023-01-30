package com.example.university.controller;

import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import com.example.university.pojo.entity.Teacher;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping("/{Id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable String id) {
        return new ResponseEntity<>(service.getTeacherById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<TeacherResponseDTO> getAllTeachers() {
        return new ResponseEntity<>(service.getAllTeacher(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(service.updateTeacher(teacher), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> insertTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(service.insertTeacher(teacher), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDTO> deleteTeacherById(@PathVariable String id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<TeacherDTO> deleteTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


