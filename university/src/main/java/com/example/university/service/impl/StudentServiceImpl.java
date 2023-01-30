package com.example.university.service.impl;

import com.example.university.pojo.dto.StudentResponseDTO;
import com.example.university.pojo.dto.StudentResponseDTO.*;
import com.example.university.pojo.entity.Student;
import com.example.university.repository.StudentRepositoryCustom;
import com.example.university.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryCustom studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepositoryCustom studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentResponseDTO getAllStu(){
        Collection<Student> studentCollection = studentRepository.findAll();
        List<StudentDTO> studentDTOS = studentCollection.stream().map(s -> new StudentDTO(s)).collect(Collectors.toList());
        return new StudentResponseDTO(studentDTOS);
    }

    @Override
    public StudentDTO getStudentById(String id) {
        Logger logger = (Logger) LogManager.getLogger(StudentServiceImpl.class.getName());
        Student student = studentRepository.getOne(id);
        if (student == null) {
            logger.log(Level.INFO, "throw an exception here");
            throw new ResourceNotFoundException("student object is null");
        }
        return new StudentDTO(student);
    }

    @Override
    public StudentDTO insertStudent(Student student) {
        return new StudentDTO(studentRepository.save(student));
    }

    @Override
    public StudentDTO updateStudent(Student student) {
        Student studentUpdated = studentRepository.getOne(student.getId());
        studentUpdated.setName(student.getName());
        studentUpdated.setId(student.getId());
        return new StudentDTO(studentRepository.save(studentUpdated));
    }


    @Override
    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }


}