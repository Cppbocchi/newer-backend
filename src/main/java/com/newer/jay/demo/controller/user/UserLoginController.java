package com.newer.jay.demo.controller.user;

import com.newer.jay.demo.dto.LoginResponseDTO;
import com.newer.jay.demo.service.user.UserLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class UserLoginController {
    @Resource
    private UserLoginService loginService;

    @PostMapping("api/auth/login")
    public LoginResponseDTO login(@RequestBody Map<String, Object> map, HttpServletResponse response) {
        String email = (String) map.get("email");
        String password = (String) map.get("password");

        if (email == null || password == null) {
            response.setStatus(400);
            return new LoginResponseDTO(1, "email and password are required", null, null);
        }

        try {
            return loginService.login(email, password);
        } catch (Exception e) {
            response.setStatus(500);
            return new LoginResponseDTO(1, e.getMessage(), null, null);
        }
    }
}
