package org.example;

import lombok.Cleanup;
import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HibernateRunnerTest {

    @Test
    public void checkHQL(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var users = session.createQuery("select u from User u").list();

        users.forEach(System.out::println);

        session.getTransaction().commit();
    }

    @Test
    public void saveUser(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Meta")
                .build();

        var user = User.builder()
                .username("admin")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Nikita")
                        .lastName("Lazurchenko")
                        .birthday(new Birthday(LocalDate.EPOCH))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();

        company.addUser(user);

        session.persist(company);

        session.getTransaction().commit();
    }

//    @Test
//    public void checkInheritence(){
//        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
//        @Cleanup Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        var company = Company.builder()
//                .name("Google")
//                .build();
//
//        session.persist(company);
//
//        var programmer = Programmer.builder()
//                .username("ivan@gmail.com")
//                .personalInfo(PersonalInfo.builder()
//                        .firstName("Ivan")
//                        .lastName("Ivanov")
//                        .birthday(new Birthday(LocalDate.of(2002,4,23))).build())
//                .role(Role.USER)
//                .language(Language.JAVA)
//                .build();
//
//        session.persist(programmer);
//
//        var manager = Manager.builder()
//                .username("petrov@gmail.com")
//                .personalInfo(PersonalInfo.builder()
//                        .firstName("Petr")
//                        .lastName("Petrov")
//                        .birthday(new Birthday(LocalDate.of(2002,4,23))).build())
//                .role(Role.USER)
//                .project("Java EE")
//                .build();
//
//        session.persist(manager);
//
//        session.flush();
//        session.clear();
//
//        var programmer1 = session.find(Programmer.class,1L);
//        var manager1 = session.find(Manager.class,2L);
//
//        session.getTransaction().commit();
//    }

    @Test
    public void checkH2(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        var company = Company.builder()
                .name("Meta")
                .build();

        session.persist(company);

        session.getTransaction().commit();
    }

}
