package com.spring.journalingapp.module.user.service;

import com.spring.journalingapp.module.user.model.User;
import com.spring.journalingapp.module.user.model.UserRequest;
import com.spring.journalingapp.module.user.model.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUser(String id);

    UserResponse updateUser(String id, UserRequest request);

}
