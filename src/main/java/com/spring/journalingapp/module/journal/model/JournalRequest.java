package com.spring.journalingapp.module.journal.model;

import com.spring.journalingapp.module.pictures.model.PictureRequest;
import lombok.Data;

import java.util.List;

@Data
public class JournalRequest {

    private String title;

    private String content;

    private List<PictureRequest> pictureRequests;

}
