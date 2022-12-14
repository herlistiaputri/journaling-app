package com.spring.journalingapp.core.exception;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handlerException(BaseException baseException) {
        BaseResponse<?> baseResponse = new BaseResponse<>
                (String.valueOf(HttpStatus.BAD_REQUEST), baseException.getMessage(), baseException.getErrorMessage(), null);
        return new ResponseEntity<>(baseResponse, HttpStatus.BAD_REQUEST);
    }

}
