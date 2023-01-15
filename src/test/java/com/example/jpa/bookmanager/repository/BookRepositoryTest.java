package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Book;
import com.example.jpa.bookmanager.domain.Person;
import com.example.jpa.bookmanager.domain.Publisher;
import com.example.jpa.bookmanager.domain.Review;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository personRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("Jpa basic");
        book.setAuthorId(1l);
//        book.setPublisherId(1l);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        reviewRepository.findAll().forEach(System.out::println);

        Person person = personRepository.findByEmail("kim@google.com");

        System.out.println(person.getReviews());
        System.out.println(person.getReviews().get(0).getBook());
        System.out.println(person.getReviews().get(0).getBook().getPublisher());
    }
    private Person givenPerson(){
        return personRepository.findByEmail("kim@google.com");
    }
    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("hellojpa");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }
    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("expensiveBooks");

        return publisherRepository.save(publisher);
    }
    private void givenBookAndReview(){
        givenReview(givenBook(givenPublisher()), givenPerson());
    }

    private void givenReview(Book book, Person person){
        Review review = new Review();
        review.setTitle("``````");
        review.setContent("asdfasdfsadf");
        review.setScore(4.5f);

        review.setBook(book);
        review.setPerson(person);

        reviewRepository.save(review);
    }
}