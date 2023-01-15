package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.PersonHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonHistoryRepository extends JpaRepository<PersonHistory, Long> {
    List<PersonHistory> findByPersonId(Long personId);
}
