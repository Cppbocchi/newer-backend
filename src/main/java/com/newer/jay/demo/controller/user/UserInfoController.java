package com.newer.jay.demo.controller.user;

import org.springframework.web.bind.annotation.RestController;
import com.newer.jay.demo.dto.LoginResponseDTO;
import com.newer.jay.demo.service.user.UserInfoService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/api/auth/me")
    public LoginResponseDTO UserInfo(HttpServletResponse response) {
        try {
            return userInfoService.UserInfo();
        } catch (Exception e) {
            response.setStatus(500);
            return new LoginResponseDTO(1, "服务器内部错误: " + e.getMessage(), null, null);
        }
    }
}
