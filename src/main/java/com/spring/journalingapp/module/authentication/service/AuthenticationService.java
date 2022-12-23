package com.spring.journalingapp.module.authentication.service;

import com.spring.journalingapp.module.authentication.dto.AuthenticationRequest;
import com.spring.journalingapp.module.authentication.dto.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

}
