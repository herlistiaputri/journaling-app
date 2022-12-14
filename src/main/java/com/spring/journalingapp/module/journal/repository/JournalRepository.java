package com.spring.journalingapp.module.journal.repository;

import com.spring.journalingapp.module.journal.model.Journal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends JpaRepository<Journal, String> {
}
