package com.newer.jay.demo.controller.user;

import com.newer.jay.demo.service.user.UserAvatarUploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class UserUploadAvatarController {
    @Resource
    UserAvatarUploadService uploadAvatarService;

    @PostMapping("/api/upload/avatar")
    public Map<String, Object> uploadAvatar(@RequestParam("avatar") MultipartFile avatar, HttpServletResponse response) {
        if (avatar == null || avatar.isEmpty()) {
            response.setStatus(400);
            return Map.of("status", 1, "message", "请选择要上传的头像文件");
        }

        try {
            return uploadAvatarService.uploadAvatar(avatar);
        } catch (Exception e) {
            response.setStatus(500);
            return Map.of("status", 1, "message", "上传失败: " + e.getMessage());
        }
    }
}
