package com.example.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

class PersonTest {
    @Test
    void test() {
        Person person = new Person();
        person.setEmail("minchul@gmail.com");
        person.setName("minchul");

//        Person person1 = new Person(null, "hello", "hello@naver.com", LocalDateTime.now(), LocalDateTime.now(), null);
        Person person2 = new Person("kim", "kim@gmail.com");

        Person person3 = Person.builder()
                .name("new")
                .email("new@gmail.com")
                .build();

        System.out.println(">>> " + person.toString());
//        System.out.println(">>> " + person1);
        System.out.println(">>> " + person2);
    }
}