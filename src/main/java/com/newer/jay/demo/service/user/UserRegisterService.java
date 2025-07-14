package com.jaylm.web.service.user;

import com.jaylm.web.mapper.UserMapper;
import com.jaylm.web.pojo.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RegisterService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    UserMapper userMapper;

    public Map<String, Object> register(String username, String email, String password, String avatarFileName,String birthday, List<String> region) {
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(null, username, email, encodedPassword, avatarFileName, birthday, region);
        userMapper.insert(user);

        return Map.of("status", 0, "message", "OK", "userId", user.getUserId());
    }
}
