package com.spring.journalingapp.module.journal.service;

import com.spring.journalingapp.core.BaseException;
import com.spring.journalingapp.core.exception.MessageConstant;
import com.spring.journalingapp.module.journal.mapper.JournalMapper;
import com.spring.journalingapp.module.journal.model.Journal;
import com.spring.journalingapp.module.journal.model.JournalRequest;
import com.spring.journalingapp.module.journal.model.JournalResponse;
import com.spring.journalingapp.module.pictures.service.PictureService;
import com.spring.journalingapp.module.journal.repository.JournalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepository;
    private final JournalMapper journalMapper;
    private final PictureService pictureService;

    public JournalServiceImpl(JournalRepository journalRepository, JournalMapper journalMapper, PictureService pictureService) {
        this.journalRepository = journalRepository;
        this.journalMapper = journalMapper;
        this.pictureService = pictureService;
    }


    @Override
    public JournalResponse createNewPost(JournalRequest request) throws IOException {

        Journal journal = journalMapper.modelMapper(request);
        journal.setPictures(pictureService.savePictures(request.getPictureRequests(), journal));
        journalRepository.save(journal);

        return journalMapper.responseMapper(journal);
    }

    @Override
    public JournalResponse getPost(String id) {

        Optional<Journal> journalOptional = journalRepository.findById(id);

        if(journalOptional.isPresent()) {
            JournalResponse response = journalMapper.responseMapper(journalOptional.get());
            response.setPictureResponseList(pictureService.getPictures(id));
            return response;
        } else {
            throw new BaseException(MessageConstant.POST_NOT_FOUND);
        }

    }

    @Override
    public List<JournalResponse> getAllPosts() {

        List<Journal> journals = journalRepository.findAll();
        List<JournalResponse> responses = new ArrayList<>();
        for(Journal journal : journals) {
            JournalResponse response = journalMapper.responseMapper(journal);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public JournalResponse updatePost(String id, JournalRequest request) throws IOException {
        Optional<Journal> journalOptional = journalRepository.findById(id);

        if(journalOptional.isPresent()) {
            Journal journal = journalOptional.get();
            journal = journalMapper.modelMapper(request);
            journalRepository.save(journal);

            return journalMapper.responseMapper(journal);
        } else {
            throw new BaseException(MessageConstant.POST_NOT_FOUND);
        }
    }
}
