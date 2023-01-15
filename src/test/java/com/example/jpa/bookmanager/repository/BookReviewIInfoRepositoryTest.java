package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewIInfoRepositoryTest {
    @Autowired
    private BookReviewIInfoRepository bookReviewIInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crud(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewIInfoRepository.save(bookReviewInfo);

        System.out.println(bookReviewIInfoRepository.findAll());
    }

    @Test
    void Test2(){
        givenBookReviewInfo();

        System.out.println(bookRepository.findAll());
        Book result =
                bookReviewIInfoRepository
                        .findById(1l)
                        .orElseThrow(RuntimeException::new)
                        .getBook();

        System.out.println(result);

        BookReviewInfo res2 = bookRepository
                .findById(1l)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println(res2);
    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("new");
        book.setAuthorId(1l);
//        book.setPublisherId(1l);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();

        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewIInfoRepository.save(bookReviewInfo);
    }
}