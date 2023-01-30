package com.example.provider.service;

import com.example.provider.pojo.User;
import com.example.provider.pojo.dto.ProviderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@FeignClient(name = "provider-service", url = "http://localhost:8082")
public interface UserService {

    public ProviderDTO insertUser(User user);

    public List<ProviderDTO> getAllUser();

    public void deleteAllUser();
}

