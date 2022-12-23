package com.spring.journalingapp.module.authentication.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String tokenId;
    private String accessToken;
    private String refreshToken;

}
