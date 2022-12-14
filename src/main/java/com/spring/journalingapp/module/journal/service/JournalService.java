package com.spring.journalingapp.module.journal.service;

import com.spring.journalingapp.module.journal.model.Journal;
import com.spring.journalingapp.module.journal.model.JournalRequest;
import com.spring.journalingapp.module.journal.model.JournalResponse;

import java.io.IOException;
import java.util.List;

public interface JournalService {

    JournalResponse createNewPost(JournalRequest request) throws IOException;

    JournalResponse getPost(String id);

    List<JournalResponse> getAllPosts();

    JournalResponse updatePost(String id, JournalRequest request) throws IOException;

}
