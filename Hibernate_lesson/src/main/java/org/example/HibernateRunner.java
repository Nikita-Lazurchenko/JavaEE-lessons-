package org.example;

import org.example.entity.Birthday;
import org.example.entity.PersonalInfo;
import org.example.entity.Role;
import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.hibernate.ReplicationMode.OVERWRITE;

public class HibernateRunner {
    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        User user = User.builder()
                .username("poshta@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Vladimir")
                        .lastName("Ivanov")
                        .birthday(new Birthday(LocalDate.of(1988,7,12)))
                        .build())
                .role(Role.USER)
                .build();

        log.info("User object in transient state: {}", user);

        try(SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()){
            try(Session session1 = sessionFactory.openSession()){
                session1.beginTransaction();

                session1.persist(user);
                session1.getTransaction().commit();
            }
            try(Session session2 = sessionFactory.openSession()){
                session2.beginTransaction();

//                user.setFirstName("Daniil");
//
//                log.warn("User firstname was changed: {}", user.getFirstName());

                session2.replicate(user, OVERWRITE);
                session2.merge(user);

                session2.getTransaction().commit();
            }
        }

    }
}
