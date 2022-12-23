package com.spring.journalingapp.module.authentication.service;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.exception.MessageConstant;
import com.spring.journalingapp.core.security.JwtUtil;
import com.spring.journalingapp.core.security.TokenUtil;
import com.spring.journalingapp.module.authentication.dto.AuthenticationRequest;
import com.spring.journalingapp.module.authentication.dto.AuthenticationResponse;
import com.spring.journalingapp.module.user.model.User;
import com.spring.journalingapp.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenUtil tokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenUtil.generateToken(authentication);

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new BaseException(MessageConstant.USER_NOT_FOUND));
        user.setAccessToken(token);
        user.setLastOnline(LocalDateTime.now());
        userRepository.save(user);

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(token);
        return response;
    }
}
