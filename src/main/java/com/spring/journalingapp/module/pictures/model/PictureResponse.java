package com.spring.journalingapp.module.pictures.model;

import lombok.Data;

@Data
public class PictureResponse {

    private String id;

    private String picture;

    private String name;

    private String url;

    private String type;

    private long size;

    private byte[] file;

}
