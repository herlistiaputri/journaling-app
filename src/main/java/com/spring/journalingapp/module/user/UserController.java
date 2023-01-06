package com.spring.journalingapp.module.user;

import com.spring.journalingapp.core.BaseResponse;
import com.spring.journalingapp.module.pictures.model.PictureResponse;
import com.spring.journalingapp.module.user.model.UserRequest;
import com.spring.journalingapp.module.user.model.UserResponse;
import com.spring.journalingapp.module.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        UserResponse userResponse = userService.createUser(request);
        BaseResponse<UserResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, userResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        UserResponse userResponse = userService.getUser(userId);
        BaseResponse<UserResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, userResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> getUpdateUser(@PathVariable String userId, @RequestBody UserRequest request) {
        UserResponse userResponse = userService.updateUser(userId, request);
        BaseResponse<UserResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, userResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        UserResponse userResponse = userService.getProfile();
        BaseResponse<UserResponse> response = new BaseResponse<>(String.valueOf(HttpStatus.OK.value()), "SUCCESS", null, userResponse);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
