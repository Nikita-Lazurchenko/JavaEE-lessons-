package org.example;

import org.example.entity.*;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


public class HibernateRunner {
    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        Company company = Company.builder()
                .name("Apple")
                .build();
        User user = User.builder()
                .username("example@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Andrey")
                        .lastName("Petrov")
                        .birthday(new Birthday(LocalDate.of(1978,2,23)))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            try(Session session1 = sessionFactory.openSession()){
                session1.beginTransaction();

                session1.persist(user);

                session1.getTransaction().commit();
            }
        }

    }
}
