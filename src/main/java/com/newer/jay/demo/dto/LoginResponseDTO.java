package com.newer.jay.demo.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private int status;
    private String message;
    private String token;
    private UserDTO user;

    public LoginResponseDTO() {}

    public LoginResponseDTO(int status, String message, String token, UserDTO user) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.user = user;
    }
}