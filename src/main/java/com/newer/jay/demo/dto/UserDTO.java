package com.newer.jay.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String avatarFileName;

    public UserDTO() {}

    public UserDTO(Long userId, String name, String email, String avatarFileName) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.avatarFileName = avatarFileName;
    }
}