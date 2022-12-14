package com.spring.journalingapp.module.pictures.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PictureRequest {

    private String picture;

    private MultipartFile file;

}
