package com.example.university.pojo.dto;

import com.example.university.pojo.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherResponseDTO {
    private List<TeacherDTO> teacherDTOList;

    @Data
    @AllArgsConstructor
    public static class TeacherDTO{
        String id;
        String name;
        public TeacherDTO(Teacher teacher) {
            this.id = teacher.getId();
            this.name = teacher.getName();
        }
    }

}
