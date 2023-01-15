package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewIInfoRepository extends JpaRepository<BookReviewInfo, Long> {
}
