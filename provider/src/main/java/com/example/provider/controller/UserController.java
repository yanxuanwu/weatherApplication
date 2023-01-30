package com.example.provider.controller;


import com.example.provider.pojo.User;
import com.example.provider.pojo.dto.ProviderDTO;
import com.example.provider.service.UserService;
import com.example.provider.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class UserController {
    private final UserService service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> insertUser(@RequestBody User user) {
        this.service.insertUser(user);
        return new ResponseEntity<>(new ProviderDTO(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> getAllUser() {
        return new ResponseEntity<>(this.service.getAllUser(), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUser() {
        this.service.deleteAllUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

