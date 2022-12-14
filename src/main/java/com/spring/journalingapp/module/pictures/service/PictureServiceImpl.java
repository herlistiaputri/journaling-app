package com.spring.journalingapp.module.pictures.service;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.exception.MessageConstant;
import com.spring.journalingapp.module.journal.model.Journal;
import com.spring.journalingapp.module.pictures.mapper.PictureMapper;
import com.spring.journalingapp.module.pictures.model.Picture;
import com.spring.journalingapp.module.pictures.model.PictureRequest;
import com.spring.journalingapp.module.pictures.model.PictureResponse;
import com.spring.journalingapp.module.pictures.repository.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PictureServiceImpl implements PictureService{

    private final PictureMapper pictureMapper;
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureMapper pictureMapper, PictureRepository pictureRepository) {
        this.pictureMapper = pictureMapper;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> savePictures(List<PictureRequest> requestList, Journal journal) throws IOException {
        List<Picture> pictures = pictureMapper.entitiesMapper(requestList, journal);
        pictureRepository.saveAll(pictures);
        return pictures;
    }

    @Override
    public List<PictureResponse> getPictures(String journalId) {
        List<Picture> pictures = pictureRepository.findAllByJournalId(journalId);
        List<PictureResponse> pictureResponses = pictureMapper.responsesMapper(pictures);
        return pictureResponses;
    }

    @Override
    public PictureResponse getPicture(String id) {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);
        if(pictureOptional.isPresent()) {
            return pictureMapper.responseMapper(pictureOptional.get());
        } else {
            throw new BaseException(MessageConstant.PICTURE_NOT_FOUND);
        }
    }

    @Override
    public void deletePicture(String id) {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);
        if(pictureOptional.isPresent()) {
            pictureRepository.delete(pictureOptional.get());
        } else {
            throw new BaseException(MessageConstant.PICTURE_NOT_FOUND);
        }
    }
}
