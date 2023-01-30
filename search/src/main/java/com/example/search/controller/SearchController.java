package com.example.search.controller;

import com.example.provider.ProviderApplication;
import com.example.provider.pojo.User;
import com.example.provider.pojo.dto.ProviderDTO;
import com.example.provider.service.UserService;
import com.example.search.pojo.dto.SearchResponseDTO;
import com.example.university.pojo.dto.StudentResponseDTO;
import com.example.university.pojo.dto.StudentResponseDTO.*;
import com.example.university.pojo.dto.TeacherResponseDTO;
import com.example.university.pojo.dto.TeacherResponseDTO.*;
import com.example.university.service.StudentService;
import com.example.university.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@Configuration
@ComponentScan({"com.example.university", "com.example.provider"})
public class SearchController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final UserService userService;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public SearchController(StudentService studentService, TeacherService teacherService, UserService userService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.userService = userService;
    }

    @GetMapping("/weather/search")
    public ResponseEntity<SearchResponseDTO> getDetails(@PathVariable String studentId, @PathVariable String teacherId, @RequestBody User user) {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        TeacherDTO teacherDTO = teacherService.getTeacherById(teacherId);
        ProviderDTO providerDTO= userService.insertUser(user);
        SearchResponseDTO searchResponseDTO = new SearchResponseDTO(studentDTO, teacherDTO, providerDTO);
        return new ResponseEntity<>(searchResponseDTO, HttpStatus.OK);
    }

    @GetMapping("weather/multithread-search")
    public CompletableFuture<SearchResponseDTO> search(@RequestParam String query) {
        String studentServiceUrl = "http://localhost:8081/student-service/search?query=" + query;
        String teacherServiceUrl = "http://localhost:8081/teacher-service/search?query=" + query;
        String providerServiceUrl = "http://localhost:8082/provider-service/search?query" + query;
        CompletableFuture<StudentDTO> studentResponse = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(studentServiceUrl, StudentDTO.class, query));
        CompletableFuture<TeacherDTO> teacherResponse = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(teacherServiceUrl, TeacherDTO.class, query));
        CompletableFuture<ProviderDTO> providerResponse = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(providerServiceUrl, ProviderDTO.class, query));
        CompletableFuture<SearchResponseDTO> searchFuture = studentResponse.thenCombine(teacherResponse, (student, teacher) -> {
            SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
            searchResponseDTO.setStudentDTO(student);
            searchResponseDTO.setTeacherDTO(teacher);
            return searchResponseDTO;
        }).thenCombine(providerResponse, (search, provider) -> {
            search.setProviderDTO(provider);
            return search;
        });

        return searchFuture;
    }
}
