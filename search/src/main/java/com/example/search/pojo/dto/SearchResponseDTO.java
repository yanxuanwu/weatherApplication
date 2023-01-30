package com.example.search.pojo.dto;

import com.example.provider.pojo.User;
import com.example.provider.pojo.dto.ProviderDTO;
import com.example.university.pojo.dto.StudentResponseDTO.*;
import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDTO {
    private StudentDTO studentDTO;
    private TeacherDTO teacherDTO;
    private ProviderDTO providerDTO;
}
