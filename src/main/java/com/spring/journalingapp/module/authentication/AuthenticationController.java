package com.spring.journalingapp.module.authentication;

import com.spring.journalingapp.core.BaseResponse;
import com.spring.journalingapp.module.authentication.dto.AuthenticationRequest;
import com.spring.journalingapp.module.authentication.dto.AuthenticationResponse;
import com.spring.journalingapp.module.authentication.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createUser(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse responseDto = authenticationService.login(request);
        BaseResponse<AuthenticationResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, responseDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
