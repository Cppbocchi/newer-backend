package com.newer.jay.demo.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.newer.jay.demo.entity.User;
import com.newer.jay.demo.mapper.UserMapper;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/api/user/update1")
public class UserUpdateController {
    @Resource
    private UserMapper userMapper;
    @PostMapping("/api/user/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        userMapper.updateById(user);
        return ResponseEntity.ok("User updated successfully");
    }
}
