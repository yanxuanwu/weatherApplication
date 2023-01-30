package com.example.university.pojo.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String name;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Teacher_Student> teacher_students = new ArrayList<>();

    public List<Teacher_Student> getTeacher_Students() {
        return teacher_students;
    }

    public void setTeacher_Students(List<Teacher_Student> teacher_students) {
        this.teacher_students = teacher_students;
    }

    public void addTeacher_Students(Teacher_Student ts){
        this.teacher_students.add(ts);
    }
}
