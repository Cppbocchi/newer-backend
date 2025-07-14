package com.newer.jay.demo.controller.user;

import com.newer.jay.demo.dto.UserRegisterDTO;
import com.newer.jay.demo.service.user.UserRegisterService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class RegisterController {
    
    @Resource
    private UserRegisterService userRegisterService;

    /**
     * 用户注册接口
     * @param registerDTO 注册信息
     * @param response HTTP响应
     * @return 注册结果
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody UserRegisterDTO registerDTO, HttpServletResponse response) {
        try {
            Map<String, Object> result = userRegisterService.register(
                registerDTO.getName(), 
                registerDTO.getEmail(), 
                registerDTO.getPassword(), 
                registerDTO.getPhone(), 
                registerDTO.getAvatarFileName()
            );
            
            int status = (Integer) result.get("status");
            if (status != 0) {
                response.setStatus(400);
            }
            
            return result;
        } catch (Exception e) {
            response.setStatus(500);
            return Map.of("status", 1, "message", "服务器内部错误: " + e.getMessage());
        }
    }

    /**
     * 用户注册接口（Map方式，兼容性）
     * @param requestBody 注册信息
     * @param response HTTP响应
     * @return 注册结果
     */
    @PostMapping("/register-map")
    public Map<String, Object> registerWithMap(@RequestBody Map<String, Object> requestBody, HttpServletResponse response) {
        try {
            String name = (String) requestBody.get("name");
            String email = (String) requestBody.get("email");
            String password = (String) requestBody.get("password");
            String phone = (String) requestBody.get("phone");
            String avatarFileName = (String) requestBody.get("avatarFileName");

            Map<String, Object> result = userRegisterService.register(name, email, password, phone, avatarFileName);
            
            int status = (Integer) result.get("status");
            if (status != 0) {
                response.setStatus(400);
            }
            
            return result;
        } catch (Exception e) {
            response.setStatus(500);
            return Map.of("status", 1, "message", "服务器内部错误: " + e.getMessage());
        }
    }

    /**
     * 检查用户名是否可用
     * @param name 用户名
     * @return 检查结果
     */
    @GetMapping("/check-username")
    public Map<String, Object> checkUsername(@RequestParam String name) {
        try {
            boolean available = userRegisterService.isUsernameAvailable(name);
            return Map.of(
                "status", 0,
                "available", available,
                "message", available ? "用户名可用" : "用户名已被占用"
            );
        } catch (Exception e) {
            return Map.of("status", 1, "message", "检查失败: " + e.getMessage());
        }
    }

    /**
     * 检查邮箱是否可用
     * @param email 邮箱
     * @return 检查结果
     */
    @GetMapping("/check-email")
    public Map<String, Object> checkEmail(@RequestParam String email) {
        try {
            boolean available = userRegisterService.isEmailAvailable(email);
            return Map.of(
                "status", 0,
                "available", available,
                "message", available ? "邮箱可用" : "邮箱已被注册"
            );
        } catch (Exception e) {
            return Map.of("status", 1, "message", "检查失败: " + e.getMessage());
        }
    }
}
