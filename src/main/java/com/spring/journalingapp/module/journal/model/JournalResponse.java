package com.spring.journalingapp.module.journal.model;

import com.spring.journalingapp.module.pictures.model.PictureResponse;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class JournalResponse {

    private String id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private String createdBy;

    private List<PictureResponse> pictureResponseList;
}
