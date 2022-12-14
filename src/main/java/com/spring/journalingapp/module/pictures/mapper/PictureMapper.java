package com.spring.journalingapp.module.pictures.mapper;

import com.spring.journalingapp.module.journal.model.Journal;
import com.spring.journalingapp.module.pictures.model.Picture;
import com.spring.journalingapp.module.pictures.model.PictureRequest;
import com.spring.journalingapp.module.pictures.model.PictureResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PictureMapper {

    public Picture entityMapper(PictureRequest request, Journal journal) throws IOException {
        Picture entity = new Picture();
        if(request.getFile() != null) {
            String fileName = StringUtils.cleanPath(request.getFile().getOriginalFilename());
            entity.setFileName(fileName);
            entity.setFile(request.getFile().getBytes());
            entity.setFileType(request.getFile().getContentType());
        }
        entity.setPicture(request.getPicture());
        entity.setJournal(journal);
        return  entity;
    }

    public List<Picture> entitiesMapper(List<PictureRequest> requestList, Journal journal) throws IOException {
        List<Picture> entities = new ArrayList<>();
        for(PictureRequest request : requestList) {
            entities.add(entityMapper(request, journal));
        }
        return  entities;
    }

    public PictureResponse responseMapper(Picture entity) {
        PictureResponse response = new PictureResponse();
        response.setId(entity.getId());
        response.setPicture(entity.getPicture());
        return response;
    }

    public List<PictureResponse> responsesMapper(List<Picture> entities) {
        List<PictureResponse> responses = new ArrayList<>();
        for(Picture entity : entities) {
            responses.add(responseMapper(entity));
        }
        return responses;
    }

}
