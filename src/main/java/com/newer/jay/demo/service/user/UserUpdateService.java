package com.newer.jay.demo.service.user;

import com.newer.jay.demo.entity.User;
import com.newer.jay.demo.mapper.UserMapper;

import jakarta.annotation.Resource;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {
    @Resource
    private PasswordEncoder passwordEncoder;
    
    @Resource
    private UserMapper userMapper;

    public void updateUser(User user) {
        userMapper.updateById(user);
    }
}
