package com.newer.jay.demo.controller.user;

import com.newer.jay.demo.service.user.UserLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {
    @Resource
    private UserLoginService loginService;

    @PostMapping("/user/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> map, HttpServletResponse response) {
        String username = map.get("username").toString();
        String email = (String) map.get("email");
        String password = (String) map.get("password");

        if (email == null || password == null) {
            response.setStatus(400);
            return Map.of("status", 1, "message", "email and password are required");
        }

        try {
            return loginService.login(email, password);
        } catch (Exception e) {
            response.setStatus(500);
            return Map.of("status", 1, "message", e.getMessage());
        }
    }
}
