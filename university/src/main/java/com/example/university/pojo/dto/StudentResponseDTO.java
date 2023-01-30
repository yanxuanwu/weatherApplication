package com.example.university.pojo.dto;
import com.example.university.pojo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
    private List<StudentDTO> studentList;

    @Data
    @AllArgsConstructor
    public static class StudentDTO{
        private String id;
        private String name;

        public StudentDTO(Student stu) {
            this.id = stu.getId();
            this.name = stu.getName();
        }
    }
}


