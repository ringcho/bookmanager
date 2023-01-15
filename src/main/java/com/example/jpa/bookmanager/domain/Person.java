package com.example.jpa.bookmanager.domain;

import com.example.jpa.bookmanager.domain.listener.Auditable;
import com.example.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(name = "person", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@EntityListeners(value = {UserEntityListener.class})
@EqualsAndHashCode(callSuper=true)
@ToString(callSuper = true)
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
//    @Column(nullable = false)
    private String name;

    @NonNull
//    @Column(unique = true) // column에 유니크 속성 추가
    private String email;

    @Enumerated(value = EnumType.STRING) // ORDINAL로 하면 인덱스로 DB에 저장되므로, 스트링으로 하는 것이 에러 방지에 용이
    private Gender gender;


//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<Address> address;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<PersonHistory> personHistories = new ArrayList<>();
    // null point exception 방지

    @OneToMany
    @JoinColumn(name = "person_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


//    @Column(name = "created_at", updatable = false) // name 속성으로 사용된 것으로 매핑됨, not_null field를 만들 때 사용
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @Transient // 영속성 처리 제외
//    private String testData;

//    @PrePersist // insert 이전
//    public void prePersist(){
//        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//    @PostPersist // insert 이후
//    public void postPersist(){
//        System.out.println(">>> postPersist");
//    }
//    @PreUpdate
//    public void preUpdate(){
//        System.out.println(">>> preUpdate");
//    }
//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>> postUpdate");
//    }
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>> preRemove");
//    }
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>> postRemove");
//    }
//    @PostLoad // select이후
//    public void postLoad(){
//        System.out.println(">>> PostLoad");
//    }

}
