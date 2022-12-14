package com.spring.journalingapp.module.user.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {

    private String id;

    private String name;

    private String email;

    private Boolean is_active;

    private LocalDateTime lastOnline;

}
