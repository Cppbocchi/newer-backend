package com.newer.jay.demo.service.user;

import com.newer.jay.demo.entity.User;
import com.newer.jay.demo.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Map;

@Slf4j
@Service
public class UserLoginService {
    @Resource
    private AuthenticationManager authenticationManager;

    public Map<String, Object> login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetailImpl loginUser = (UserDetailImpl) authenticate.getPrincipal();

        User user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getUserId().toString());

        return Map.of("status", 0, "message", "OK", "token", jwt, "userId", user.getUserId(), "name",user.getName(),"email", user.getEmail(),  "avatar", user.getAvatarFileName());
    }
}