package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String name;

    private String category;

    private Long authorId;

    @OneToOne(mappedBy = "book") // mappedBy: 연관키를 해당 테이블에서는 가지지않음
    @ToString.Exclude // ToString은 순환참조이므로 해당 어노테이션이 없으면, Stackoverflow
    private BookReviewInfo bookReviewInfo;

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

    @OneToMany
    @JoinColumn(name="book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

//    @ManyToMany
//    @ToString.Exclude
//    private List<Author> authors = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }


//    @Column(updatable = false)
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        this.updatedAt = LocalDateTime.now();
//    }
}
