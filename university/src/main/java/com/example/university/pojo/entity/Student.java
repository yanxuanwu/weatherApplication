package com.example.university.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "student")
@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;

    @OneToMany(mappedBy = "stu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Teacher_Student> teacher_students = new ArrayList<>();

    public List<Teacher_Student> getTeacher_Students() {
        return teacher_students;
    }

    public void setTeacher_Students(List<Teacher_Student> ts) {
        this.teacher_students = ts;
    }

}
