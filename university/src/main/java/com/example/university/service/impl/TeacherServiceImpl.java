package com.example.university.service.impl;

import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import com.example.university.pojo.entity.Teacher;
import com.example.university.repository.TeacherRepositoryCustom;
import com.example.university.service.TeacherService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepositoryCustom teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepositoryCustom teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherResponseDTO getAllTeacher() {
        List<Teacher> teacherList = teacherRepository.findAllTeacher();
        List<TeacherDTO> teacherDTOS = teacherList.stream().map(s -> new TeacherDTO(s)).collect(Collectors.toList());
        return new TeacherResponseDTO(teacherDTOS);
    }

    @Override
    public TeacherDTO getTeacherById(String id) {
        Logger logger = (Logger) LogManager.getLogger(StudentServiceImpl.class.getName());
        Teacher teacher = teacherRepository.getOne(id);
        if (teacher == null) {
            logger.log(Level.INFO, "throw an exception here");
            throw new ResourceNotFoundException("teacher object is null");
        }
        return new TeacherDTO(teacher);
    }

    @Override
    public TeacherDTO insertTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return new TeacherDTO(teacher);
    }

    @Override
    public TeacherDTO updateTeacher(Teacher teacher) {
        Teacher teacherUpdated = teacherRepository.getOne(teacher.getId());
        teacherUpdated.setId(teacher.getId());
        teacherUpdated.setName(teacher.getName());
        return new TeacherDTO(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacherById(String id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }
}


