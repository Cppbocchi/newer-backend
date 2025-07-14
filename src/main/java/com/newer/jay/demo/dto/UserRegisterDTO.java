package com.newer.jay.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    
    /**
     * 用户名（2-20字符）
     */
    private String name;
    
    /**
     * 邮箱地址
     */
    private String email;
    
    /**
     * 密码（6-20字符）
     */
    private String password;
    
    /**
     * 手机号码（可选）
     */
    private String phone;
    
    /**
     * 头像文件名（可选）
     */
    private String avatarFileName;
}
