package com.spring.journalingapp.module.user.service;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.config.KeycloakProfile;
import com.spring.journalingapp.core.exception.MessageConstant;
import com.spring.journalingapp.module.user.mapper.UserMapper;
import com.spring.journalingapp.module.user.model.User;
import com.spring.journalingapp.module.user.model.UserRequest;
import com.spring.journalingapp.module.user.model.UserResponse;
import com.spring.journalingapp.module.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakProfile keycloakProfile;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, KeycloakProfile keycloakProfile) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.keycloakProfile = keycloakProfile;
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.modelMapper(request);
        userRepository.save(user);
        return userMapper.responseMapper(user);
    }

    @Override
    public UserResponse getUser(String id) {
        Optional<User> userOp = userRepository.findById(id);
        if(userOp.isPresent()) {
            return userMapper.responseMapper(userOp.get());
        } else {
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }
    }

    @Override
    public UserResponse updateUser(String id, UserRequest request) {
        Optional<User> userOp = userRepository.findById(id);
        if(userOp.isPresent()) {
            User user = userOp.get();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setIsActive(request.getIsActive());
            userRepository.save(user);
            return userMapper.responseMapper(user);
        } else {
            throw new BaseException(MessageConstant.USER_NOT_FOUND);
        }
    }

    @Override
    public UserResponse getProfile() {
        User currentUser = userRepository.findByEmail(keycloakProfile.getEmail()).orElseThrow(() -> new BaseException(MessageConstant.USER_NOT_FOUND));
        log.info("{}", keycloakProfile.getEmail());
        return userMapper.responseMapper(currentUser);
    }
}
