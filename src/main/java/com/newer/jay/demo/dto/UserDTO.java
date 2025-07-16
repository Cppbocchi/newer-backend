package com.newer.jay.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String avatarFileName;
    private String memberLevel;
    private Date joinDate;
    private Integer totalTrips;
    private Integer points;
}