package com.example.jpa.bookmanager.domain.listener;

import com.example.jpa.bookmanager.domain.Person;
import com.example.jpa.bookmanager.domain.PersonHistory;
import com.example.jpa.bookmanager.repository.PersonHistoryRepository;
import com.example.jpa.bookmanager.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void perPersistAndPreUpdate(Object obj){
        PersonHistoryRepository userHistoryRepository = BeanUtils.getBean(PersonHistoryRepository.class);
        Person person = (Person) obj;
        PersonHistory personHistory = new PersonHistory();
//        personHistory.setPersonId(person.getId());
        personHistory.setName(person.getName());
        personHistory.setEmail(person.getEmail());
        personHistory.setPerson(person);

        userHistoryRepository.save(personHistory);
    }
}
