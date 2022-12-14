package com.spring.journalingapp.module.pictures.service;

import com.spring.journalingapp.module.journal.model.Journal;
import com.spring.journalingapp.module.pictures.model.Picture;
import com.spring.journalingapp.module.pictures.model.PictureRequest;
import com.spring.journalingapp.module.pictures.model.PictureResponse;

import java.io.IOException;
import java.util.List;

public interface PictureService {

    List<Picture> savePictures(List<PictureRequest> requestList, Journal journal) throws IOException;

    List<PictureResponse> getPictures(String journalId);

    PictureResponse getPicture(String id);

    void deletePicture(String id);
}
