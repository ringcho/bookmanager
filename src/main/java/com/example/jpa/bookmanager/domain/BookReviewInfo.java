package com.example.jpa.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false) // optional = false 이면 not null, select 쿼리는 inner join
    private Book book;

    private float reviewScore; // 원시타입과 참조타입의 차이는 어떤 값이 null이 가능한지의 차이 원시타입으로 쓰면 not null

    private int reviewCount;

}
