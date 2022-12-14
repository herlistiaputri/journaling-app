package com.spring.journalingapp.module.journal.mapper;

import com.spring.journalingapp.module.journal.model.Journal;
import com.spring.journalingapp.module.journal.model.JournalRequest;
import com.spring.journalingapp.module.journal.model.JournalResponse;
import com.spring.journalingapp.module.pictures.service.PictureService;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JournalMapper {

    private final PictureService pictureService;

    public JournalMapper(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    public Journal modelMapper(JournalRequest request) throws IOException {
        Journal entity = new Journal();
        entity.setTitle(request.getTitle());
        entity.setContent(request.getContent());
        return entity;
    }

    public JournalResponse responseMapper(Journal entity) {
        JournalResponse response = new JournalResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setContent(entity.getContent());
        response.setCreatedAt(entity.getCreatedAt());
        response.setCreatedBy(entity.getCreatedBy());
        response.setPictureResponseList(pictureService.getPictures(entity.getId()));
        return response;
    }
}
