package com.spring.journalingapp.module.user.mapper;

import com.spring.journalingapp.module.user.model.User;
import com.spring.journalingapp.module.user.model.UserRequest;
import com.spring.journalingapp.module.user.model.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User modelMapper(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setIsActive(request.getIsActive());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setPin(new BCryptPasswordEncoder().encode(request.getPin()));
        return user;
    }

    public UserResponse responseMapper(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setIs_active(user.getIsActive());
        response.setLastOnline(user.getLastOnline());
        return response;
    }

}
