package org.example;

import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HibernateRunnerTest {

    @Test
    public void checkManyToMany(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            Chat chat = Chat.builder()
                    .name("workingChat")
                    .build();
            User user = session.get(User.class, 3);
            user.addChat(chat);

            session.persist(chat);

            session.getTransaction().commit();
        }
    }


    @Test
    public void checkOneToOne(){
        Profile profile1 = Profile.builder()
                .street("Мира 14")
                .language("RU")
                .build();
        Profile profile2 = Profile.builder()
                .street("Свободы 67")
                .language("ML")
                .build();
        Profile profile3 = Profile.builder()
                .street("Независимости 34")
                .language("UA")
                .build();
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            List<User> users = session.createNativeQuery("SELECT * FROM users", User.class).list();
            profile1.setUser(users.get(0));
            profile2.setUser(users.get(1));
            profile3.setUser(users.get(2));

            session.persist(profile1);
            session.persist(profile2);
            session.persist(profile3);

            session.getTransaction().commit();
        }
    }

    @Test
    public void checkOrphalRemoval(){
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            Company company = session.get(Company.class, 6);
            company.getUsers().removeIf(user -> user.getId().equals(5));

            session.getTransaction().commit();
        }
    }

    @Test
    public void addNewUserAndCompany() {
        Company company = Company.builder()
                .name("2K Czech")
                .build();
        User user = User.builder()
                .username("mafia@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Vito")
                        .lastName("Scaletta")
                        .birthday(new Birthday(LocalDate.of(1926,7,21)))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
        var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            company.addUser(user);
            session.persist(company);

            session.getTransaction().commit();
        }

    }

    @Test
    public void checkOneToMany() {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession())
        {
            session.beginTransaction();

            var company = session.get(Company.class, 3);
            System.out.println(company.getUsers());

            session.getTransaction().commit();
        }

    }
}
