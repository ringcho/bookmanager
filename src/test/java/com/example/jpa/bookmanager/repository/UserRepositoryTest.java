package com.example.jpa.bookmanager.repository;

import com.example.jpa.bookmanager.domain.Gender;
import com.example.jpa.bookmanager.domain.Person;
import com.example.jpa.bookmanager.domain.PersonHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PersonHistoryRepository personHistoryRepository;

    @Test
    void search(){
//        userRepository.save(new Person());
        // 1. 일렬로 나옴
        System.out.println(">>> "+ userRepository.findAll());
        // 2. forEach with lambda func
        userRepository.findAll().forEach(System.out::println);
        // User List
//        List<Person> people = userRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        // assertj라는 테스트 전용 라이브러리 활용 실제로는 리스트에 하나씩 add
        List<Person> people = userRepository.findAllById(Lists.newArrayList(1L,3L,5L));
        people.forEach(System.out::println);
        // count
        long count = userRepository.count();
        System.out.println(count);
    }
    @Test
    void save(){
        Person person1 = new Person("jack","jack@gmail.com");
        Person person2 = new Person("park","park@gmail.com");
        userRepository.saveAll(Lists.newArrayList(person1,person2));
        // flush
        userRepository.saveAndFlush(new Person("lee", "lee@naver.com"));

//        userRepository.flush();

        List<Person> people = userRepository.findAll();

        people.forEach(System.out::println);
    }
    @Test
    @Transactional
    void getone(){
        //lazy fetch
        Person person = userRepository.getOne(1L);
        System.out.println(person);
    }
    @Test
    void findbyid(){
        //Eager fetch : entity를 직접 entity manage를 통해 가져옴
//      Optional<Person> person = userRepository.findById(1L);
        Person person = userRepository.findById(1L).orElse(null);
        System.out.println(person);
    }
    @Test
    void exist(){
        boolean exists = userRepository.existsById(1L);
        System.out.println(exists);
    }
    @Test
    void deleteTest(){
        // 조회 2번
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        // 조회 1번
        userRepository.deleteById(2L);
        // 성능이슈 발생 - findAll 이후 for를 통해 하나씩 제거
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(3L,4L)));
        // 해결하기 위해 deleteInBatch
        userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(5L)));
    }
    @Test
    void pageTest(){
        Page<Person> people = userRepository.findAll(PageRequest.of(1,3));

        System.out.println("page : " + people);
        System.out.println("total elements : "+people.getTotalElements());
        System.out.println("total pages : "+people.getTotalPages());
        System.out.println("numOfElements : "+people.getNumberOfElements());
        System.out.println("sort : "+people.getSort());
        System.out.println("size : "+people.getSize());
        people.getContent().forEach(System.out::println);
    }
    @Test
    void queryByExampleTest(){
        //querymatcher
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());
        Example<Person> example = Example.of(new Person("k", "google.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }
    @Test
    void updateTest(){
        userRepository.save(new Person("david", "david@kakao.com"));

        Person person = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        person.setEmail("update@gmail.com");

        userRepository.save(person);
    }
    @Test
    void selectTest(){
//        System.out.println(userRepository.findByName("kim"));
//        System.out.println("findByEmail"+userRepository.findByEmail("kim@google.com"));
//        System.out.println("getByEmail"+userRepository.getByEmail("kim@google.com"));
//        System.out.println("readByEmail"+userRepository.readByEmail("kim@google.com"));
//        System.out.println("queryByEmail"+userRepository.queryByEmail("kim@google.com"));
//        System.out.println("searchByEmail"+userRepository.searchByEmail("kim@google.com"));
//        System.out.println("findPersonByEmail"+userRepository.findPersonByEmail("kim@google.com"));
//        System.out.println("findSomethingByEmail"+userRepository.findSomethingByEmail("kim@google.com"));
//
//        System.out.println("findTop1ByName : "+userRepository.findTop1ByName("kim"));
//        System.out.println("findFirstByName : "+userRepository.findFirstByName("kim"));

//        System.out.println("findByEmailAndName : "+userRepository.findByEmailAndName("kim@google.com", "kim"));
//        System.out.println("findByEmailOrName : "+userRepository.findByEmailOrName("kim@google.com", "lee"));
//
//        System.out.println("findByCreatedAtAfter : "+userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1)));
//        System.out.println("findByCreatedAtGreaterThan : "+userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1)));
//
//        System.out.println("findByCreatedAtBetween : "+userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1)));
//        System.out.println("findByIdBetween : "+userRepository.findByIdBetween(1L,3L));

//        System.out.println("findByIdNotNull : "+userRepository.findByIdNotNull());
//        System.out.println("findByAddressIsNotEmpty : "+userRepository.findByAddressIsNotEmpty());
//
//        System.out.println("findByNameIn : "+userRepository.findByNameIn(Lists.newArrayList("kim","lee")));

        System.out.println("findByNameStartingWith : "+userRepository.findByNameStartingWith("k"));
        System.out.println("findByNameEndingWith : "+userRepository.findByNameEndingWith("im"));
        System.out.println("findByNameContaining : "+userRepository.findByNameContaining("i"));
    }
    @Test
    void pagingAndSortingTest(){
        System.out.println("findTop1ByNameOrderByIdDesc :"+userRepository.findTop1ByNameOrderByIdDesc("kim"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc"+userRepository.findFirstByNameOrderByIdDescEmailAsc("kim"));
        System.out.println("findFirstByNameWithParams : " +userRepository.findFirstByName("kim", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println("findFirstByNameWithParams : " +userRepository.findFirstByName("kim", getSort()));

        System.out.println("findByNamePaging : "+userRepository.findByName("kim",PageRequest.of(0,1, Sort.by(Sort.Order.desc("id")))).getContent());
    }
    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt")
        );
    }

    @Test
    void insertAndUpdateTest(){
        Person person = new Person();

        person.setName("kim");
        person.setEmail("kim@gmail.com");
        userRepository.save(person);

        Person person1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        person1.setName("kiiiiiiiiiiim");

        userRepository.save(person1);
    }

    @Test
    void enumTest(){
        Person person = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        person.setGender(Gender.MALE);

        userRepository.save(person);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }
    @Test
    void listenerTest(){
        Person person = new Person();
        person.setEmail("kim@gmail1.com");
        person.setName("kim");

        userRepository.save(person);

        Person person1 = userRepository.findById(1l).orElseThrow(RuntimeException::new);
        person1.setName("kiiiiiiim");

        userRepository.save(person1);

        userRepository.deleteById(4l);
    }

    @Test
    void prePersistTest(){
        Person person = new Person();
        person.setEmail("kim2@google.com");
        person.setName("kim");

        // Don't Repeat Your Self
//        person.setCreatedAt(LocalDateTime.now());
//        person.setUpdatedAt(LocalDateTime.now());

        userRepository.save(person);

        System.out.println(userRepository.findByEmail("kim2@google.com"));
    }

    @Test
    void preUpdateTest(){
        Person person = userRepository.findById(1l).orElseThrow(RuntimeException::new);
        System.out.println("as-is : " + person);

        person.setName("whowhowho");
        userRepository.save(person);

        // 영속성 캐시 사용
        System.out.println("to-be :"+userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest(){
        Person person = new Person();
        person.setEmail("kim-new@gmail.com");
        person.setName("kim-new");

        userRepository.save(person);

        person.setName("kim-new-new");

        userRepository.save(person);

        personHistoryRepository.findAll().forEach(System.out::println);

    }

    @Test
    void userRelationTest()
    {
        Person person = new Person();
        person.setEmail("david@google.com");
        person.setName("david");
        person.setGender(Gender.MALE);

        userRepository.save(person);

        person.setName("daniel");

        userRepository.save(person);

        person.setEmail("daniel@google.com");

        userRepository.save(person);

//        userHistoryRepository.findAll().forEach(System.out::println);
//        List<UserHistory> res = userHistoryRepository.findByPersonId(
//                userRepository.findByEmail("daniel@google.com").getId()
//        );

        List<PersonHistory> res = userRepository.findByEmail("daniel@google.com").getPersonHistories();

        res.forEach(System.out::println);
        System.out.println("-------------------------------------------------------");
//        personHistoryRepository.findAll().forEach(System.out::println);
        System.out.println(personHistoryRepository.findAll().get(0).getPerson());
//        userHistoryRepository.findByPersonId(6l).forEach(System.out::println);
    }

}