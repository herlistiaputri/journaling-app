package com.spring.journalingapp.module.user.model;

import lombok.Data;

@Data
public class UserRequest {

    private String name;

    private String email;

    private String password;

    private String pin;

    private Boolean isActive;

}
