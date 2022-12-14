package com.spring.journalingapp.module.pictures.repository;

import com.spring.journalingapp.module.pictures.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {

    List<Picture> findAllByJournalId(String id);
}
