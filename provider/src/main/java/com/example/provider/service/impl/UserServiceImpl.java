package com.example.provider.service.impl;

import com.example.provider.pojo.User;
import com.example.provider.pojo.dto.ProviderDTO;
import com.example.provider.repository.UserRepository;
import com.example.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public ProviderDTO insertUser(User user) {
        return new ProviderDTO(repository.save(user));
    }

    @Override
    public List<ProviderDTO> getAllUser() {
        return repository.findAll().stream().map(s -> new ProviderDTO(s)).collect(Collectors.toList());
    }

    @Override
    public void deleteAllUser() {
        this.repository.deleteAll();
    }
}
