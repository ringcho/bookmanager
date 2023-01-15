package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<Person, Long> {
   List<Person> findByName(String name);

   Person findByEmail(String email);

   Person getByEmail(String email);

   Person readByEmail(String email);

   Person queryByEmail(String email);

   Person searchByEmail(String email);

   Person findPersonByEmail(String email);

   Person findSomethingByEmail(String email);

   List<Person> findFirstByName(String name);

   List<Person> findTop1ByName(String name);

   List<Person> findByEmailAndName(String email, String name);

   List<Person> findByEmailOrName(String email, String name);

   List<Person> findByCreatedAtAfter(LocalDateTime yesterday);

   List<Person> findByCreatedAtGreaterThan(LocalDateTime yesterday);

   List<Person> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

   List<Person> findByIdBetween(Long id1, Long id2);

   List<Person> findByIdNotNull();

//   List<Person> findByAddressIsNotEmpty();

   List<Person> findByNameIn(List<String> names);

   List<Person> findByNameStartingWith(String name);

   List<Person> findByNameEndingWith(String name);

   List<Person> findByNameContaining(String name);

   List<Person> findTop1ByNameOrderByIdDesc(String name);

   List<Person> findFirstByNameOrderByIdDescEmailAsc(String name);

   List<Person> findFirstByName(String name, Sort sort);

   Page<Person> findByName(String name, Pageable pageable);
   @Query(value = "select * from person limit 1;", nativeQuery = true)
   Map<String, Object> findRawRecord();
}
